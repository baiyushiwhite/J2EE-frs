package edu.nju.frs.action;

import edu.nju.frs.model.User;
import edu.nju.frs.service.ExpenseClaimService;

public class CreateExpenseClaimAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExpenseClaimService expenseClaimService;
	private String error;
	private String projectId;
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private String info;
	
	public ExpenseClaimService getExpenseClaimService() {
		return expenseClaimService;
	}

	public void setExpenseClaimService(ExpenseClaimService expenseClaimService) {
		this.expenseClaimService = expenseClaimService;
	}
	
	public String execute() throws Exception{
		String message = "";
		String expenseName = this.getRequest().getParameter("expenseName");
		String expenseDescription = this.getRequest().getParameter("description");
		String expense = this.getRequest().getParameter("expense");
		String userId = ((User)this.getRequest().getSession().getAttribute("user")).getUserId();
		projectId = this.getRequest().getParameter("projectId");
		message = expenseClaimService.reimbursed(expenseName, expenseDescription, expense, userId, projectId);
		if (message!=null) {
			error = "create claim fail!";
			return SUCCESS;
		}
		info = "create success!";
		return SUCCESS;
	}
	
	
}
