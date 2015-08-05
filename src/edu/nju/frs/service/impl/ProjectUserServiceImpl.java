package edu.nju.frs.service.impl;

import edu.nju.frs.dao.ProjectUserDao;
import edu.nju.frs.model.ProjectUser;
import edu.nju.frs.service.ProjectUserService;

public class ProjectUserServiceImpl implements ProjectUserService{

	private ProjectUserDao projectUserDao;
	
	
	
	
	/**
	 * 通过projectId添加对应Project的成员列表
	 * @param projectId
	 * @return
	 */
	@Override
	public String addMembersByProjectId(long projectId,String[] memberIds) {
		ProjectUser projectUserItem = null;
		String message = null;
		
		for(int i=0;i<memberIds.length;i++){
			projectUserItem = new ProjectUser();
//			ProjectUserPK projectUserPK = new ProjectUserPK();
//			projectUserPK.setProjectId(projectId);
//			projectUserPK.setUserId(memberIds[i]);
//			projectUserItem.setProjectUserPK(projectUserPK);
			message = projectUserDao.insertProjectUser(projectUserItem);
			if(message != null){
				return message;
			}
		}
		return null;
	}

	//.............
	@Override
	public ProjectUserDao getProjectUserDao() {
		return projectUserDao;
	}
	@Override
	public void setProjectUserDao(ProjectUserDao projectUserDao) {
		this.projectUserDao = projectUserDao;
	}

}
