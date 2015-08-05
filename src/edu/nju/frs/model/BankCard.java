package edu.nju.frs.model;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name="bankcard")
public class BankCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String bankNum;
	@OneToOne(mappedBy="bankCard") 
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	private double amount;
	private double income;
	private double expense;
}
