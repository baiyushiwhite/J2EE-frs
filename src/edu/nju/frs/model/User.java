package edu.nju.frs.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import edu.nju.frs.util.UserType;
@Entity
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	private String password;
	private String realName;
	private UserType userType;
	@OneToOne(cascade=CascadeType.ALL)  
	@JoinColumn(name="bankNum")
	private BankCard bankCard;
	
	public BankCard getBankCard() {
		return bankCard;
	}
	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="expenseId ASC")
	private Set<ExpenseClaim> claims = new HashSet<ExpenseClaim>();
	@ManyToMany(mappedBy="projectUsers")
	private Set<Project> projectSet = new HashSet<Project>();
	
	public Set<ExpenseClaim> getClaims() {
		return claims;
	}
	public void setClaims(Set<ExpenseClaim> claims) {
		this.claims = claims;
	}
	
	public Set<Project> getProjectSet() {
		return projectSet;
	}
	public void setProjectSet(Set<Project> projectSet) {
		this.projectSet = projectSet;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
