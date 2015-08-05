package edu.nju.frs.dao;

import java.util.List;

import edu.nju.frs.model.ProjectUser;

/**
 * project_user的数据库操作接口
 */

public interface ProjectUserDao {
	//插入一条ProjectUser记录
	public String insertProjectUser(ProjectUser projectUser);
}
