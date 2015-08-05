package edu.nju.frs.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 项目
 */

@Entity
@Table(name="project")
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=true)
	private long projectId;
	@Column(nullable=false)
	private String projectName;
	@Column(nullable=true)
	private String description;
	
	@Column(nullable=false)
	private Date beginTime;
	@Column(nullable=false)
	private Date endTime;
	@Column(nullable=true)
	private double expense;
	@Column(nullable=true)
	private double supplement;
	@Column(nullable=true)
	private double scale;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(
	name="project_user",
	joinColumns=
	@JoinColumn(name="projectId", referencedColumnName="projectId"),
	inverseJoinColumns=
	@JoinColumn(name="userId", referencedColumnName="userId")
	)
	private Set<User> projectUsers = new HashSet<User>();
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy(value="expenseId ASC")
	private Set<ExpenseClaim> claims = new HashSet<ExpenseClaim>();
	
	private String hostId;
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	private String hostName;
	public Set<ExpenseClaim> getClaims() {
		return claims;
	}
	public void setClaims(Set<ExpenseClaim> claims) {
		this.claims = claims;
	}
	
	
	public Set<User> getProjectUsers() {
		return projectUsers;
	}
	public void setProjectUsers(Set<User> projectUsers) {
		this.projectUsers = projectUsers;
	}
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getBeginDate() {
		return beginTime;
	}
	public void setBeginDate(Date beginDate) {
		this.beginTime = beginDate;
	}
	public Date getEndDate() {
		return endTime;
	}
	public void setEndDate(Date endDate) {
		this.endTime = endDate;
	}
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public double getSupplement() {
		return supplement;
	}
	public void setSupplement(double supplement) {
		this.supplement = supplement;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
}
