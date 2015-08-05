package edu.nju.frs.dao.impl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.nju.frs.dao.ProjectDao;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;
import edu.nju.frs.util.CommonHandle;

public class ProjectDaoImpl implements ProjectDao{

	private ProjectDaoImpl(){
		System.out.println("ProjectDaoImpl 初始化！！");
	}

	@Override
	public String insertProjectUser(ProjectUser pu) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			session.save(pu);
			tx.commit();
			session.close();
			sessionFactory.close();
			
		} catch (Exception e) {
			message = "create project fail";
			e.printStackTrace();
			return message;
		}
		return message;
	}

	/**
	 * 获得还没有完成的项目
	 * @return
	 */
	@Override
	public List<?> getProjectNotFinishByBeginTime() {
		List<?> allProject = getAllProjectByBeginTime();
		List<Project> unfinished = new ArrayList<Project>();
		if (allProject!=null) {
			String now = CommonHandle.getNowDate();
			for(int i=0;i<allProject.size();i++){
				Project project = (Project) allProject.get(i);
				if(project.getEndDate().after(Date.valueOf(now))){
					//结束日期大于当前日期
					unfinished.add(project);
				}
			}
		}
		return unfinished;
	}

	/**
	 * 获得所有的项目 【返回的结果要通过begin time来排序】
	 * @return
	 */
	@Override
	public List<?> getAllProjectByBeginTime() {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.Project as p order by p.beginTime asc";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			if ((list.size()) != 0)return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得所有的已经结束了的项目【返回的结果要通过begin time来排序】
	 * @return
	 */
	@Override
	public List<?> getProjectFinishByBeginTime() {

		List<?> allProject = getAllProjectByBeginTime();
		List<Project> finished = new ArrayList<Project>();
		if (allProject!=null) {
			String now = CommonHandle.getNowDate();
			for(int i=0;i<allProject.size();i++){
				Project project = (Project) allProject.get(i);
				if(project.getEndDate().before(Date.valueOf(now))){
					//结束日期小于当前日期
					finished.add(project);
				}
			}
		}
		
		return finished;
		
	}

	/**
	 * 通过id查询项目
	 * @param projectId
	 * @return
	 */
	@Override
	public Project getProjectById(Long projectId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.Project as project where projectId ='"+ projectId + "'";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			if ((list.size()) == 0)
				return null;
			else 
				return (Project) list.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> getAllMyManageProjectByBeginTime(String userId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.Project as p where p.hostId='"+ userId + "' order by p.beginTime asc";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> getAllMyJoinProjectByBeginTime(String userId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ProjectUser as pu where pu.user.userId='"+ userId + "' order by pu.project.beginTime asc";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过projectId查找project
	 * @param projectId
	 * @return
	 */
	@Override
	public List<?> findAllUsersByProjectId(long projectId) {
		try {
			Configuration configuration = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ProjectUser as pu where pu.project.projectId = '" + projectId +"'"; 
			
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<?> getMyClaimAmountByProjectId(long projectId, String userId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ExpenseClaim as ec where ec.approveState=3 and ec.user.userId='"+ userId + "' and ec.project.projectId="+ projectId;
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addMembers(Project project, ArrayList<User> users) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ProjectUser as pu where pu.project.projectId='" + project.getProjectId() + "' and pu.isHost=0";
			Query query = session.createQuery(hql);
			List<ProjectUser> list = (List<ProjectUser>)query.list();
			for (int j = 0; j < list.size(); j++) {
				Transaction tx = session.beginTransaction();
				session.delete(list.get(j));
				tx.commit();
			}
			for (int i = 0; i < users.size(); i++) {
				ProjectUser pu = new ProjectUser();
				pu.setUser(users.get(i));
				pu.setProject(project);
				pu.setHost(false);
				pu.setTask("");
				Transaction tx = session.beginTransaction();
				session.save(pu);
				tx.commit();
			}
			session.close();
			sessionFactory.close();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return "add members fail!";
		}
		
	}

	@Override
	public String updateProjectUserTask(String userId, String projectId,
			String task) {
		String message;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ProjectUser as pu where pu.project.projectId='"+projectId+"' and pu.user.userId='"+userId+"'";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			if (list.size()>0) {
				ProjectUser pu = (ProjectUser) list.get(0);
				pu.setTask(task);
				Transaction tx = session.beginTransaction();
				session.update(pu);
				tx.commit();
			}
			session.close();
			sessionFactory.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "update task fail!";
		}
	}

	@Override
	public String insertProject(Project project) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			session.save(project);
			tx.commit();
			session.close();
			sessionFactory.close();
			
		} catch (Exception e) {
			message = "create project fail";
			e.printStackTrace();
			return message;
		}
		return message;
	}

	@Override
	public String updateProjectUser(Project pu) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			session.update(pu);
			tx.commit();
			session.close();
			sessionFactory.close();
			
		} catch (Exception e) {
			message = "create project fail";
			e.printStackTrace();
			return message;
		}
		return message;
	}
	
}
