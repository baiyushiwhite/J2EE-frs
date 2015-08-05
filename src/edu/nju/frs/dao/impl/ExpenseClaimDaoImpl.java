package edu.nju.frs.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.nju.frs.dao.ExpenseClaimDao;
import edu.nju.frs.model.BankCard;
import edu.nju.frs.model.ExpenseClaim;
import edu.nju.frs.util.ApproveState;
import edu.nju.frs.util.CommonHandle;

public class ExpenseClaimDaoImpl implements ExpenseClaimDao{

	/**
	 * 通过projectId来查找指定Project的报销款项
	 * @param projectId
	 * @return
	 */
	@Override
	public List<?> findExpencesByProId(long projectId) {
		
		try {
			Configuration configuration = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ExpenseClaim as ec where ec.project.projectId='"+ projectId +"'";
			Query query = session.createQuery(hql);
			List<?> allExpenceItem = query.list();
			return allExpenceItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 项目参与人员添加报销款项
	 * @param expenseClaim
	 * @return
	 */
	@Override
	public String insertExpense(ExpenseClaim expenseClaim) {
		try {
			Configuration configuration = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			Transaction transaction = session.beginTransaction();
			session.save(expenseClaim);
			transaction.commit();
			
			session.close();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "create expense claim fail";
		}
		return null;
	}

	@Override
	public String updateExpenseClaim(ExpenseClaim ec) {
		String message = null;
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx1=session.beginTransaction();
			session.update(ec);
			tx1.commit();
			
			if (ec.getApproveState()==ApproveState.Finish) {
				BankCard bankCard = ec.getUser().getBankCard();
				bankCard.setAmount(bankCard.getAmount() + ec.getExpense());
				bankCard.setIncome(bankCard.getIncome() + ec.getExpense());
				Transaction tx2 = session.beginTransaction();
				session.update(bankCard);
				tx2.commit();
				session.close();
				sessionFactory.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "update fail!";
			return message;
		}
		
		return message;
	}

	@Override
	public ExpenseClaim getExpenseClaimById(long expenseId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ExpenseClaim as ec where ec.expenseId ='"+ expenseId + "'";
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			if ((list.size()) == 0)
				return null;
			else 
				return (ExpenseClaim) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ExpenseClaim> getWaitFEClaims() {
		try {
			Configuration configuration = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ExpenseClaim as ec where ec.approveState=1";
			Query query = session.createQuery(hql);
			List<?> allExpenceItem = query.list();
			ArrayList<ExpenseClaim> unfinished = new ArrayList<ExpenseClaim>();
			String now = CommonHandle.getNowDate();
			for(int i=0;i<allExpenceItem.size();i++){
				ExpenseClaim ec = (ExpenseClaim) allExpenceItem.get(i);
				if(ec.getProject().getEndDate().after(Date.valueOf(now))){
					//结束日期大于当前日期
					unfinished.add(ec);
				}
			}
			return unfinished;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ExpenseClaim> getMyExpencesByProjectId(long projectId,
			String userId) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = "from edu.nju.frs.model.ExpenseClaim as ec where ec.project.projectId ="+ projectId + " and ec.user.userId=" + userId;
			Query query = session.createQuery(hql);
			List<?> list = query.list();
			session.close();
			sessionFactory.close();
			return (ArrayList<ExpenseClaim>) list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
