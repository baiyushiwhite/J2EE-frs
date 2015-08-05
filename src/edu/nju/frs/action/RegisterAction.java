package edu.nju.frs.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import edu.nju.frs.service.UserService;

@Controller
public class RegisterAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Map<String, String> user;
	

	private String userId = "";
	private String password = "";
	private String realName = "";
	private String bankNum = "";
	private String userType = "";
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Map<String, String> getUser() {
		return user;
	}

	public void setUser(Map<String, String> user) {
		this.user = user;
	}
	public String execute() throws Exception{
		String message = null;
		user = new HashMap<String, String>();
		
		message = userService.registerUser(userId, password, realName, bankNum, userType);
		if(message != null){
			userService.sentErrorMessage(message, this.getRequest(), this.response());
			user = null;
			return SUCCESS;
		}
		user.put("userId", userId);
		user.put("password", password);
		user.put("realName", realName);
		user.put("bankNum", bankNum);
		user.put("userType", userType);
		
		return SUCCESS;
	}
}
