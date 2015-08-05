package edu.nju.frs.action;

import java.util.ArrayList;

import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ExpenseClaimService;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.UserType;

public class FinancialExaminerPageAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExpenseClaimService expenseClaimService;
	
	public ExpenseClaimService getExpenseClaimService() {
		return expenseClaimService;
	}

	public void setExpenseClaimService(ExpenseClaimService expenseClaimService) {
		this.expenseClaimService = expenseClaimService;
	}

	public String execute() throws Exception{
		String info = this.getRequest().getParameter("info");
		String error = this.getRequest().getParameter("error");
		if (info==null||info=="") {
			this.getRequest().setAttribute("info", null);
		}else{
			this.getRequest().setAttribute("info", info);
		}
		if (error==null||error=="") {
			this.getRequest().setAttribute("error", null);
		}else{
			this.getRequest().setAttribute("error", error);
		}
		
		User currentUser = (User) this.getRequest().getSession().getAttribute("user");
		if (currentUser == null||currentUser.getUserType()!=UserType.FinancialExaminers) {
			return ERROR;
		}
		
		ArrayList<ExpenseClaim> waitFEList = expenseClaimService.getWaitFEClaims();
		this.getRequest().setAttribute("waitFEList", waitFEList);
		return SUCCESS;
	}
}
