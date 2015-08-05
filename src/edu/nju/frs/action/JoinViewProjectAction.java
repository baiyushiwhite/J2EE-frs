package edu.nju.frs.action;

import java.util.ArrayList;

import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.util.UserType;

public class JoinViewProjectAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	
	
	
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
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
		if (currentUser == null||currentUser.getUserType()!=UserType.CommonUser) {
			return ERROR;
		}
		
		String projectIdStr = (String)this.getRequest().getParameter("projectId");
		
		Long projectId = Long.parseLong(projectIdStr);
		ArrayList<ProjectUser> userList = projectService.getUsersByProjectId(projectId);
		ArrayList<ExpenseClaim> myExpenseList = projectService.getMyExpencesByProjectId(projectId, currentUser.getUserId());
		double myClaimAmount = projectService.getMyClaimAmountByProjectId(projectId, currentUser.getUserId());
		String myClaimAmountString = String.valueOf(myClaimAmount);
		Project project = projectService.getProjectById(projectIdStr);
		
		if(project == null){
			message = "the project not exist!";
			projectService.sentErrorMessage(message, getRequest(), response());
			return ERROR;
		}
		this.getRequest().setAttribute("myClaimAmount", myClaimAmountString);
		this.getRequest().setAttribute("userList", userList);
		this.getRequest().setAttribute("expenseList", myExpenseList);
		this.getRequest().setAttribute("project", project);
		
		return SUCCESS;
	}
	
}