package edu.nju.frs.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.frs.dao.ProjectDao;
import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;

public interface ProjectService {

	public ProjectDao getProjectDao();

	public void setProjectDao(ProjectDao projectDao);

	public String createProject(String projectName, String projectDescription,
			String hostId, double expense, Date beginTime, Date endTime);
	
	public void sentErrorMessage(String message, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException;

	public List<?> getProjectNotFinishByBeginTime();

	public List<?> getAllProjectByBeginTime();

	public List<?> getProjectFinishByBeginTime();

	public Project getProjectById(String projectId);

	public List<?> getAllMyManageProjectByBeginTime(String userId);

	public List<?> getAllMyJoinProjectByBeginTime(String userId);
	//通过projectId获得指定项目的成员列表
	public ArrayList<ProjectUser> getUsersByProjectId(long projectId);
	//获得指定Project的所有待处理的报销款项ExpenseClaim
	public ArrayList<ExpenseClaim> getExpencesByProjectId(long projectId);

	public ArrayList<ExpenseClaim> getWaitHostExpenseClaim(
			ArrayList<ExpenseClaim> expenseList);

	public ArrayList<ExpenseClaim> getWaitFEExpenseClaim(
			ArrayList<ExpenseClaim> expenseList);

	public ArrayList<ExpenseClaim> getMyExpencesByProjectId(long projectId,
			String userId);

	public double getMyClaimAmountByProjectId(long projectId, String userId);

	public String addMembersByProjectId(Project project, String[] memberIds);

	public String updateProjectUserTask(String userId, String projectId,
			String task);

	public String updateProject(String projectId, String projectName, String projectDescription,
			String hostId, double expense, Date beginTime, Date endTime);
}
