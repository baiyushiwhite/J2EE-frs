package edu.nju.frs.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.nju.frs.dao.ProjectUserDao;
import edu.nju.frs.model.ProjectUser;

public class ProjectUserDaoImpl implements ProjectUserDao{

	/**
	 * 插入一条ProjectUser记录
	 * @param projectUser
	 * @return
	 */
	@Override
	public String insertProjectUser(ProjectUser projectUser) {
		Configuration configuration = new Configuration().configure();
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		session.save(projectUser);
		transaction.commit();
		
		session.close();
		sessionFactory.close();
		return null;
	}

}
