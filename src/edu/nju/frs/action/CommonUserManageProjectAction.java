package edu.nju.frs.action;

import java.util.List;

import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.ProjectUserService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

public class CommonUserManageProjectAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private ProjectService projectService;
	private ProjectUserService projectUserService;
	
	public ProjectUserService getProjectUserService() {
		return projectUserService;
	}
	public void setProjectUserService(ProjectUserService projectUserService) {
		this.projectUserService = projectUserService;
	}
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
		User currentUser = (User) this.getRequest().getSession().getAttribute("user");
		if (currentUser == null||currentUser.getUserType()!=UserType.CommonUser) {
			return ERROR;
		}
		//返回当前userId所管理的所有项目列表
		
		List<?> allList = projectService.getAllMyManageProjectByBeginTime(((User)this.getRequest().getSession().getAttribute("user")).getUserId());
		this.getRequest().setAttribute("allList", allList);
		return SUCCESS;
	}
}
