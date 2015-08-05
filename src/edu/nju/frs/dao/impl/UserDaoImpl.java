package edu.nju.frs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.nju.frs.dao.UserDao;
import edu.nju.frs.model.User;
import edu.nju.frs.util.UserType;

public class UserDaoImpl implements UserDao {
	
	private UserDaoImpl()
	{
		System.out.println("UserDaoImpl 初始化");
	}
	@Override
	public User find(String column, String value) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.User as us where us."
					+ column + "='" + value + "'";
			Query query = session.createQuery(hql);
			List list = query.list();
			session.close();
			sessionFactory.close();
			if ((list.size()) == 0)
				return null;
			else
				return (User) list.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String insertUser(User user) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			session.close();
			sessionFactory.close();
		} catch (Exception e) {
			message = "register user fail";
			e.printStackTrace();
			return message;
		}
		return message;
	}
	@Override
	public void updateByUserid(User user) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			session.close();
			sessionFactory.close();
			//this.getHibernateTemplate().update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List getAllUser() {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.User as us";
			Query query = session.createQuery(hql);
			List list = query.list();
			if ((list.size()) != 0)
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String deleteUser(String userId) {
		String message = null;
		try {
			
		} catch (Exception e) {
			message = "delete User " + userId + " fail!";
			e.printStackTrace();
		}
		return message;
	}
	@Override
	public String updateUser(User user) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			session.close();
			sessionFactory.close();
		} catch (Exception e) {
			message = "update user " + user.getUserId() + " fail!";
			e.printStackTrace();
			return message;
		}
		return message;
	}
	@Override
	public List<?> findUserByUserType(UserType userType) {
		try {
			Configuration configuration = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.User as user where user.userType = " + "'" + userType +"'";
			Query query = session.createQuery(hql);
			List<?> users = query.list();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
