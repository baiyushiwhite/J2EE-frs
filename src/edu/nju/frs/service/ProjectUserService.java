package edu.nju.frs.service;

import edu.nju.frs.dao.ProjectUserDao;

public interface ProjectUserService {

	
	
	//通过projectId添加对应Project的成员列表
	public String addMembersByProjectId(long projectId,String[] memberIds);
	
	//.....
	public ProjectUserDao getProjectUserDao();
	public void setProjectUserDao(ProjectUserDao projectUserDao);
}
