package edu.nju.frs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

/**
 * 单个Project的处理
 */
public class ViewProjectAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private UserService userService;
	
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute() throws Exception{
		String message = null;
		String info = this.getRequest().getParameter("info");
		String error = this.getRequest().getParameter("error");
		if (info==null||info=="") {
			this.getRequest().setAttribute("info", null);
		}else{
			this.getRequest().setAttribute("info", info);
		}
		if (error==null||error=="") {
			this.getRequest().setAttribute("error", null);
		}else{
			this.getRequest().setAttribute("error", error);
		}
		
		User currentUser = (User) this.getRequest().getSession().getAttribute("user");
		if (currentUser == null||(currentUser.getUserType()!=UserType.CommonUser&&currentUser.getUserType()!=UserType.ManagerUser)) {
			return ERROR;
		}
		
		String projectIdStr = this.getRequest().getParameter("projectId");
		long projectId = Long.valueOf(projectIdStr);
		ArrayList<ProjectUser> userList = projectService.getUsersByProjectId(projectId);
		ArrayList<ExpenseClaim> expenseList = projectService.getExpencesByProjectId(projectId);
		
		
		Project project = projectService.getProjectById(projectIdStr);
		
		if(project == null){
			message = "the project not exist!";
			projectService.sentErrorMessage(message, getRequest(), response());
			return ERROR;
		}
		
		List<User> commonUsers = (List<User>)userService.getAllUserByType(UserType.CommonUser);
		this.getRequest().setAttribute("allUser", commonUsers);
		
		this.getRequest().setAttribute("userList", userList);
		this.getRequest().setAttribute("expenseList", expenseList);
		this.getRequest().setAttribute("project", project);
		ArrayList<ExpenseClaim> waitHostList = projectService.getWaitHostExpenseClaim(expenseList);
		this.getRequest().setAttribute("waitHostList", waitHostList);
		return SUCCESS;
	}
	
}
