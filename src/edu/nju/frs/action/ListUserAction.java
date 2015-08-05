package edu.nju.frs.action;

import java.util.ArrayList;
import java.util.List;

import edu.nju.frs.model.User;
import edu.nju.frs.service.UserService;

public class ListUserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String execute() throws Exception{
//		String currentUserId = this.getRequest().getParameter("userId");
//		String realName = this.getRequest().getParameter("realName");
//		
//		this.getRequest().setAttribute("userId", currentUserId);
//		this.getRequest().setAttribute("realName", realName);
//		
		List allList = userService.getUserList();
		ArrayList<User> commonUserList = new ArrayList<User>();
		ArrayList<User> managerUserList = new ArrayList<User>();
		ArrayList<User> feUserList = new ArrayList<User>();
		ArrayList<User> fmUserList = new ArrayList<User>();
		for (int i = 0; i < allList.size(); i++) {
			User user = (User)allList.get(i);
			switch (user.getUserType()) {
			case CommonUser:
				commonUserList.add(user);
				break;
			case ManagerUser:
				managerUserList.add(user);
				break;
			case FinancialExaminers:
				feUserList.add(user);
				break;
			case FinancialManager:
				fmUserList.add(user);
				break;
			}
		}
		this.getRequest().setAttribute("commonUserList", commonUserList);
		this.getRequest().setAttribute("managerUserList", managerUserList);
		this.getRequest().setAttribute("feUserList", feUserList);
		this.getRequest().setAttribute("fmUserList", fmUserList);
//		List userList = userService.getUserList();
		this.getRequest().setAttribute("userList", allList);
		return SUCCESS;
	}
}
