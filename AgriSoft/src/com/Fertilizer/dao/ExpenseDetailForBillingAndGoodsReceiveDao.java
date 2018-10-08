package com.Fertilizer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.hibernate.ExpenseDetailForBillingAndGoodsReceiveBean;
import com.Fertilizer.utility.HibernateUtility;

public class ExpenseDetailForBillingAndGoodsReceiveDao {

	public void addExpenseDetails(
			ExpenseDetailForBillingAndGoodsReceiveBean bean) {

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			System.out.println("Tx started");
			session.save(bean);
			transaction.commit();
			System.out.println("Successful");
		}
		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}finally{
			hbu.closeSession(session);
		}

	}

	public List getAllExpenses()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from ExpenseDetailForBillingAndGoodsReceiveBean");
			list = query.list(); 
			System.out.println("Expense List size == ="+list.size());
		} catch (RuntimeException e) {
			Log.error("Error in getAllExpenses", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
}
