package edu.nju.frs.action;


import java.util.HashMap;
import java.util.List;

import edu.nju.frs.model.Project;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

public class ToEditProjectAction extends BaseAction{
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

	public String execute() throws Exception{
		String message = null;
		String projectId = this.getRequest().getParameter("projectId");
		Project project =projectService.getProjectById(projectId);
		this.getRequest().setAttribute("project", project);
		
		//TODO commonUsers' userId realName
		@SuppressWarnings("unchecked")
		List<User> commonUsers = (List<User>) userService.getAllUserByType(UserType.CommonUser);
		HashMap<String,String> commonUsersInfo = new HashMap<String, String>();
		for(int i=0;i<commonUsers.size();i++){
			commonUsersInfo.put(commonUsers.get(i).getUserId(), commonUsers.get(i).getRealName());
		}
		this.getRequest().setAttribute("commonUsersInfo", commonUsersInfo);
		return SUCCESS;
	}

}
