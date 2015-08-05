package edu.nju.frs.dao;

import java.util.ArrayList;
import java.util.List;

import edu.nju.frs.model.ExpenseClaim;

/**
 * 报销申请数据项的数据库操作
 */
public interface ExpenseClaimDao {

	//通过projectId来查找指定Project的报销款项
	public List<?> findExpencesByProId(long projectId);
	
	//项目参与人员添加报销款项
	public String insertExpense(ExpenseClaim expenseClaim);

	public ExpenseClaim getExpenseClaimById(long expenseId);

	public String updateExpenseClaim(ExpenseClaim ec);

	public ArrayList<ExpenseClaim> getWaitFEClaims();

	public ArrayList<ExpenseClaim> getMyExpencesByProjectId(long projectId,
			String userId);
}
