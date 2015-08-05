package edu.nju.frs.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.frs.dao.UserDao;
import edu.nju.frs.model.User;
import edu.nju.frs.util.UserType;

public interface UserService {

	public UserDao getUserDao();
	public void setUserDao(UserDao userDao);
	public User validateUser(String userId, String password);
	public List getUserList();
	public String registerUser(String userId, String password, String realName,
			String bankNum, String userType);
	public User getUserByUserId(String userId);
	public void sentErrorMessage(String message,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	public String deleteUser(String userId);
	public String updateUser(String userId,
			String password, String realName, String bankNum, String userType);
	public List<?> getAllUserByType(UserType userType);	
}
