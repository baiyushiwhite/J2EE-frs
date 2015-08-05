package edu.nju.frs.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 表示一个项目的所有成员 【一个项目有多个成员，一个成员可以在多个项目中】
 * 有联合主键{pid,uid}
 */

@Entity
@Table(name="project_user")
public class ProjectUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private long puId;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="userId")  //外键
	private User user;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="projectId") //外键
	private Project project;
	
	
	private boolean isHost;
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	private String task;
	
	public long getPuId() {
		return puId;
	}
	public void setPuId(long puId) {
		this.puId = puId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public boolean isHost() {
		return isHost;
	}
	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}
	

}
