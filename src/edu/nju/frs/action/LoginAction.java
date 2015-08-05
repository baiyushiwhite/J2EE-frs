package edu.nju.frs.action;

import org.springframework.stereotype.Controller;

import edu.nju.frs.model.User;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;
@Controller
public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String realName;
	private String userId;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String execute() throws Exception{
		String message = "";
		String userIdStr = this.getRequest().getParameter("userId");
		String password = this.getRequest().getParameter("password");
		User user = userService.validateUser(userIdStr, password);
		if(user==null) {
			message = "user not exist!";
			userService.sentErrorMessage(message, this.getRequest(),this.response());
			return "fail";
		}else{
			userId = user.getUserId();
			realName = user.getRealName();
			
			this.getRequest().getSession().setAttribute("user", user);
			
			UserType type = user.getUserType();
			String result = "";
			switch (type) {
			case CommonUser:
				result = "CommonUser";
				break;
			case ManagerUser:
				result = "ManagerUser";
				break;
			case FinancialExaminers:
				result = "FinancialExaminers";
				break;
			case FinancialManager:
				result = "FinancialManager";
				break;
			}
			return result;
		}
	}
}
