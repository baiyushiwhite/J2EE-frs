package edu.nju.frs.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;

import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;
@Controller
public class ListProjectAction extends BaseAction{

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
		List<?> allList = projectService.getAllProjectByBeginTime();
		List<?> projectNotFinish = projectService.getProjectNotFinishByBeginTime();
		List<?> projectFinish = projectService.getProjectFinishByBeginTime();
		//TODO commonUsers' userId realName
		@SuppressWarnings("unchecked")
		List<User> commonUsers = (List<User>) userService.getAllUserByType(UserType.CommonUser);
		HashMap<String,String> commonUsersInfo = new HashMap<String, String>();
		for(int i=0;i<commonUsers.size();i++){
			commonUsersInfo.put(commonUsers.get(i).getUserId(), commonUsers.get(i).getRealName());
		}
		
		this.getRequest().setAttribute("allList", allList);
		this.getRequest().setAttribute("projectNotFinish", projectNotFinish);
		this.getRequest().setAttribute("projectFinish", projectFinish);
		this.getRequest().setAttribute("commonUsersInfo", commonUsersInfo);
		
		return SUCCESS;
	}
	
	
}
