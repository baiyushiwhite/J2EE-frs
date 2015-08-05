package edu.nju.frs.service;

import java.util.ArrayList;
import java.util.List;

import edu.nju.frs.dao.ExpenseClaimDao;
import edu.nju.frs.model.ExpenseClaim;

/**
 * 项目参与人员申请报销
 */
public interface ExpenseClaimService {

	//申请报销
	public String reimbursed(String expenseName, String expenseDescription, String expense, String userId, String projectId);
	
	//.............
	public void setExpenseClaimDao(ExpenseClaimDao expenseClaimDao);
	public ExpenseClaimDao getExpenseClaimDao();



	public String updateExpenseClaim(String expenseId, String approve,
			String comment);



	public ArrayList<ExpenseClaim> getWaitFEClaims();

}
