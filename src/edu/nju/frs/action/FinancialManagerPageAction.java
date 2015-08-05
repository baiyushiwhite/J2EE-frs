package edu.nju.frs.action;

import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

public class FinancialManagerPageAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private ProjectService projectService;
	
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
		if (currentUser == null||currentUser.getUserType()!=UserType.FinancialManager) {
			return ERROR;
		}
		//获得在本月进行的项目
		//获得该项目在该月所有报销记录和报销总数
		//获得每个用户在该月累计报销数额
		
		return SUCCESS;
	}
}
