package edu.nju.frs.dao;

import java.util.ArrayList;
import java.util.List;

import edu.nju.frs.model.Project;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.model.User;

public interface ProjectDao {

	public String insertProject(Project project);

	public List getProjectNotFinishByBeginTime();

	public List getAllProjectByBeginTime();

	public List getProjectFinishByBeginTime();

	public Project getProjectById(Long projectId);

	public List<?> getAllMyManageProjectByBeginTime(String userId);

	public List<?> getAllMyJoinProjectByBeginTime(String userId);

	public List<?> findAllUsersByProjectId(long projectId);

	public List<?> getMyClaimAmountByProjectId(long projectId, String userId);

	public String addMembers(Project project, ArrayList<User> users);

	public String updateProjectUserTask(String userId, String projectId,
			String task);

	public String insertProjectUser(ProjectUser pu);

	public String updateProjectUser(Project project);
}
