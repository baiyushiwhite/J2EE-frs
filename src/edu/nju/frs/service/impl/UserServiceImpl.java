package edu.nju.frs.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import edu.nju.frs.dao.UserDao;
import edu.nju.frs.model.BankCard;
import edu.nju.frs.model.User;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;
@Service
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private UserServiceImpl(){
		System.out.println("UserServiceImpl初始化");
	}
	@Override
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDao getUserDao() {
		
		return userDao;
	}
	
	@Override
	public User validateUser(String userId, String password) {
		System.out.println("validate user");
		User user = userDao.find("userId",userId);
		if (user==null) {
			return null;
		}
		if(!user.getPassword().equals(password)){
			System.out.println(password);
			return null;
		}
		return user;
	}

	@Override
	public String registerUser(String userId, String password, String realName,
			String bankNum, String userType) {
		String message = null;
		BankCard bankCard = new BankCard();
		bankCard.setBankNum(bankNum);
		bankCard.setAmount(0);
		bankCard.setIncome(0);
		bankCard.setExpense(0);
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setRealName(realName);
		user.setBankCard(bankCard);
		
		if(userType.equals("CommonUser")){
			user.setUserType(UserType.CommonUser);
		}else if(userType.equals("ManagerUser")){
			user.setUserType(UserType.ManagerUser);
		}else if(userType.equals("FinancialExaminers")){
			user.setUserType(UserType.FinancialExaminers);
		}else {
			user.setUserType(UserType.FinancialManager);
		}
		message = userDao.insertUser(user);
		return message;
	}
	
	@Override
	public List getUserList() {
		List userList = userDao.getAllUser();
		return userList;
	}

	@Override
	public void sentErrorMessage(String message, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		
	}
	@Override
	public String deleteUser(String userId) {
		String message = null;
		message = userDao.deleteUser(userId);
		return message;
	}
	@Override
	public String updateUser(String userId,
			String password, String realName, String bankNum, String userType) {
		String message = null;
		User user = userDao.find("userId", userId);
		if (user == null) {
			message = "the user " + userId + " not exist!";
			return message;
		}
		
		user.setUserId(userId);
		user.setPassword(password);
		user.setRealName(realName);
		user.getBankCard().setBankNum(bankNum);
		
		if(userType.equals("CommonUser")){
			user.setUserType(UserType.CommonUser);
		}else if(userType.equals("ManagerUser")){
			user.setUserType(UserType.ManagerUser);
		}else if(userType.equals("FinancialExaminers")){
			user.setUserType(UserType.FinancialExaminers);
		}else {
			user.setUserType(UserType.FinancialManager);
		}
		
		message = userDao.updateUser(user);
		return message;
	}
	@Override
	public User getUserByUserId(String userId) {
		User user = userDao.find("userId", userId);
		return user;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<?> getAllUserByType(UserType userType) {
		List<?> users = userDao.findUserByUserType(userType);
		return users;
	}

}
