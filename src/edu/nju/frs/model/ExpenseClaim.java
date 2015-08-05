package edu.nju.frs.model;

import java.io.Serializable;

import javax.persistence.*;

import edu.nju.frs.util.ApproveState;
@Entity
@Table(name="user_expense_claim")
public class ExpenseClaim implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long expenseId;

	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="userId")
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="projectId")
	private Project project;
	

	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	private String expenseName;
	private String expenseDescription;
	private ApproveState approveState;
	private String comment;
	private double expense;
	
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public long getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}
	
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public ApproveState getApproveState() {
		return approveState;
	}
	public void setApproveState(ApproveState approveState) {
		this.approveState = approveState;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
