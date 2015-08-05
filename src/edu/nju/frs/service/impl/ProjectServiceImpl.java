package edu.nju.frs.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.frs.dao.ExpenseClaimDao;
import edu.nju.frs.dao.ProjectDao;
import edu.nju.frs.dao.UserDao;
import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.util.ApproveState;

public class ProjectServiceImpl implements ProjectService{

	private ProjectDao projectDao;
	private ExpenseClaimDao expenseClaimDao;
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ExpenseClaimDao getExpenseClaimDao() {
		return expenseClaimDao;
	}

	public void setExpenseClaimDao(ExpenseClaimDao expenseClaimDao) {
		this.expenseClaimDao = expenseClaimDao;
	}

	private ProjectServiceImpl(){
		System.out.println("ProjectServiceImpl初始化！！！！");
	}
	
	@Override
	public ProjectDao getProjectDao() {
		return projectDao;
	}
	@Override
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	//TODO
	@Override
	public String createProject(String projectName, String projectDescription,String hostId, double expense, Date beginTime, Date endTime) {
		User host = userDao.find("userId", hostId);
		
		Project project = new Project();
		project.setProjectName(projectName);
		project.setDescription(projectDescription);
		project.setHostId(hostId);
		project.setExpense(expense);
		project.setBeginDate(beginTime);
		project.setEndDate(endTime);
		project.setSupplement(0);
		project.setScale(0);
		project.setHostId(hostId);
		project.setHostName(host.getRealName());
		
		ProjectUser pu = new ProjectUser();
		pu.setProject(project);
		pu.setHost(true);
		pu.setTask("");
		pu.setUser(host);
		return projectDao.insertProjectUser(pu);
	}
	
	@Override
	public List<?> getAllProjectByBeginTime() {
		List<?> projectList = projectDao.getAllProjectByBeginTime();
 		return projectList;
	}
	
	@Override
	public List<?> getProjectNotFinishByBeginTime() {
		List<?> projectList = projectDao.getProjectNotFinishByBeginTime();
 		return projectList;
	}
	
	@Override
	public List<?> getProjectFinishByBeginTime() {
		List<?> projectList = projectDao.getProjectFinishByBeginTime();
		return projectList;
	}
	@Override
	public void sentErrorMessage(String message, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		
	}
	@Override
	public Project getProjectById(String projectId) {
		Project project = projectDao.getProjectById(Long.valueOf(projectId));
		return project;
	}

	@Override
	public List<?> getAllMyManageProjectByBeginTime(String userId) {
		return projectDao.getAllMyManageProjectByBeginTime(userId);
	}

	@Override
	public List<?> getAllMyJoinProjectByBeginTime(String userId) {
		return projectDao.getAllMyJoinProjectByBeginTime(userId);
	}
	
	/**
	 * 通过projectId获得指定项目的成员列表
	 * @param projectId
	 * @return
	 */
	@Override
	public ArrayList<ProjectUser> getUsersByProjectId(long projectId) {
		ArrayList<ProjectUser> puList = (ArrayList<ProjectUser>)projectDao.findAllUsersByProjectId(projectId);
		return puList;
	}
	/**
	 * 获得指定Project的所有待处理的报销款项ExpenseClaim
	 * @param projectId
	 * @return
	 */
	@Override
	public ArrayList<ExpenseClaim> getExpencesByProjectId(long projectId) {
		ArrayList<ExpenseClaim> expences = (ArrayList<ExpenseClaim>)expenseClaimDao.findExpencesByProId(projectId);
		return expences;
	}

	@Override
	public ArrayList<ExpenseClaim> getWaitHostExpenseClaim(
			ArrayList<ExpenseClaim> expenseList) {
		ArrayList<ExpenseClaim> claims = new ArrayList<ExpenseClaim>();
		for (ExpenseClaim expenseClaim : expenseList) {
			if (expenseClaim.getApproveState()==ApproveState.WaitHostPass) {
				claims.add(expenseClaim);
			}
		}
		return claims;
	}

	@Override
	public ArrayList<ExpenseClaim> getWaitFEExpenseClaim(
			ArrayList<ExpenseClaim> expenseList) {
		ArrayList<ExpenseClaim> claims = new ArrayList<ExpenseClaim>();
		for (ExpenseClaim expenseClaim : expenseList) {
			if (expenseClaim.getApproveState()==ApproveState.WaitFEPass) {
				claims.add(expenseClaim);
			}
		}
		return claims;
	}

	@Override
	public ArrayList<ExpenseClaim> getMyExpencesByProjectId(long projectId,
			String userId) {
		ArrayList<ExpenseClaim> expenses = (ArrayList<ExpenseClaim>)expenseClaimDao.getMyExpencesByProjectId(projectId, userId);
		return expenses;
	}

	@Override
	public double getMyClaimAmountByProjectId(long projectId, String userId) {
		List<ExpenseClaim> expenseClaims = (List<ExpenseClaim>) projectDao.getMyClaimAmountByProjectId(projectId, userId);
		double amount = 0;
		if (expenseClaims==null) {
			return amount;
		}
		for (ExpenseClaim expenseClaim : expenseClaims) {
			amount += expenseClaim.getExpense();
		}
		return amount;
	}

	@Override
	public String addMembersByProjectId(Project project, String[] memberIds) {
		ArrayList<User> users = new ArrayList<User>();
		if (memberIds!=null) {
			for (int i = 0; i < memberIds.length; i++) {
				User user = userDao.find("userId", memberIds[i]);
				users.add(user);
			}
		}
		
		return projectDao.addMembers(project, users);
	}

	@Override
	public String updateProjectUserTask(String userId, String projectId,
			String task) {
		return projectDao.updateProjectUserTask(userId, projectId, task);
	}

	@Override
	public String updateProject(String projectId, String projectName, String projectDescription,
			String hostId, double expense, Date beginTime, Date endTime) {
		User host = userDao.find("userId", hostId);
		
		Project project = projectDao.getProjectById(Long.valueOf(projectId));
		
		project.setProjectName(projectName);
		project.setDescription(projectDescription);
		project.setExpense(expense);
		project.setBeginDate(beginTime);
		project.setEndDate(endTime);
		project.setSupplement(0);
		project.setScale(0);
		project.setHostId(hostId);
		project.setHostName(host.getRealName());
		
//		ProjectUser pu = new ProjectUser();
//		pu.setProject(project);
//		pu.setHost(true);
//		pu.setTask("");
//		pu.setUser(host);
		return projectDao.updateProjectUser(project);
	}
	
}
