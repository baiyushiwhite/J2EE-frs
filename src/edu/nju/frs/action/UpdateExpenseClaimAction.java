package edu.nju.frs.action;


import edu.nju.frs.service.ExpenseClaimService;

public class UpdateExpenseClaimAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExpenseClaimService expenseClaimService;
	
	private String projectId;
	private String info;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	private String error;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public ExpenseClaimService getExpenseClaimService() {
		return expenseClaimService;
	}
	public void setExpenseClaimService(ExpenseClaimService expenseClaimService) {
		this.expenseClaimService = expenseClaimService;
	}
	public String execute() throws Exception{
		String message = null;
		String expenseId = this.getRequest().getParameter("expenseClaimId");
		String comment = this.getRequest().getParameter("comment");
		String approve = this.getRequest().getParameter("approve");
		projectId = this.getRequest().getParameter("projectId");
		
		message = expenseClaimService.updateExpenseClaim(expenseId, approve, comment);
		if(message!=null){
			error = message;
			return "HOST";
		}
		info = "update success!";
		if (projectId==null) {
			//财务审核用户
			return "FE";
		}
		return "HOST";
	}
	
}
