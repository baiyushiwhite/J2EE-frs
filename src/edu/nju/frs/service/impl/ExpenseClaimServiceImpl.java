package edu.nju.frs.service.impl;

import java.util.ArrayList;
import edu.nju.frs.dao.ExpenseClaimDao;
import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.model.Project;
import edu.nju.frs.model.User;
import edu.nju.frs.service.ExpenseClaimService;
import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;
import edu.nju.frs.util.ApproveState;

public class ExpenseClaimServiceImpl implements ExpenseClaimService{

	private ExpenseClaimDao expenseClaimDao;
	private ProjectService projectService;
	private UserService userService;
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	/**
	 * 申请报销 [新增报销款项]
	 * @return
	 */	
	@Override
	public String reimbursed(String expenseName, String expenseDescription, String expense, String userId, String projectId) {
		String message = null;
		ExpenseClaim expenseClaim = new ExpenseClaim();
		expenseClaim.setExpenseName(expenseName);
		expenseClaim.setExpenseDescription(expenseDescription);
		expenseClaim.setExpense(Double.valueOf(expense));
		expenseClaim.setApproveState(ApproveState.WaitHostPass);
		expenseClaim.setComment("");
		Project project = projectService.getProjectById(projectId);
		User user = userService.getUserByUserId(userId);
		
		expenseClaim.setProject(project);
		expenseClaim.setUser(user);
		message = expenseClaimDao.insertExpense(expenseClaim);
		return message;
	}
	
	@Override
	public ExpenseClaimDao getExpenseClaimDao() {
		return expenseClaimDao;
	}
	@Override
	public void setExpenseClaimDao(ExpenseClaimDao expenseClaimDao) {
		this.expenseClaimDao = expenseClaimDao;
	}

	@Override
	public String updateExpenseClaim(String expenseIdStr, String approveStr,
			String comment) {
		long expenseId = Long.valueOf(expenseIdStr);
		ExpenseClaim ec = expenseClaimDao.getExpenseClaimById(expenseId);
		
		if (ec!=null) {
			int approve = Integer.valueOf(approveStr);
			switch (approve) {
			case 0:
				ec.setApproveState(ApproveState.WaitHostPass);
				break;
			case 1:
				ec.setApproveState(ApproveState.WaitFEPass);
				break;
			case 2:
				ec.setApproveState(ApproveState.FENotPass);
				break;
			case 3:
				ec.setApproveState(ApproveState.Finish);
				break;
			default:
				break;
			}
			ec.setComment(comment);
			return expenseClaimDao.updateExpenseClaim(ec);
		}
		return "update fail！";
	}

	@Override
	public ArrayList<ExpenseClaim> getWaitFEClaims() {
		return expenseClaimDao.getWaitFEClaims();
	}


}
