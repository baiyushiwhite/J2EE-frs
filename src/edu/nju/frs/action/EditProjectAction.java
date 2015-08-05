package edu.nju.frs.action;
import java.util.HashMap;
import java.util.List;

import edu.nju.frs.model.Project;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ExpenseClaimService;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.ProjectUserService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

public class EditProjectAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public ProjectService getProjectService() {
		return projectService;
	}


	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	private String projectId;
	
	
	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public String execute() throws Exception{
		String message = null;
		projectId = this.getRequest().getParameter("projectId");
		String[] memberIds = this.getRequest().getParameterValues("selectedUser");
		
		Project project = projectService.getProjectById(projectId);
		projectService.addMembersByProjectId(project, memberIds);
		
		if(project == null){
			message = "the project not exist!";
			projectService.sentErrorMessage(message, getRequest(), response());
			return ERROR;
		}
		//TODO commonUsers' userId realName
		@SuppressWarnings("unchecked")
		List<User> commonUsers = (List<User>) userService.getAllUserByType(UserType.CommonUser);
		HashMap<String,String> commonUsersInfo = new HashMap<String, String>();
		for(int i=0;i<commonUsers.size();i++){
			commonUsersInfo.put(commonUsers.get(i).getUserId(), commonUsers.get(i).getRealName());
		}
			
		this.getRequest().setAttribute("commonUsersInfo", commonUsersInfo);
		this.getRequest().setAttribute("projectId", projectId);
		return SUCCESS;
	}
}
