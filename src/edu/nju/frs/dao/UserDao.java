package edu.nju.frs.dao;

import java.util.List;

import edu.nju.frs.model.User;
import edu.nju.frs.util.UserType;

public interface UserDao {

	public User find(String column, String value);

	public String insertUser(User user);

	public void updateByUserid(User user);

	public List getAllUser();

	public String deleteUser(String userId);

	public String updateUser(User user);

	List<?> findUserByUserType(UserType userType);
}
