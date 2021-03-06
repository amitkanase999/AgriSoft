package com.Fertilizer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.hibernate.CreditLoanPaymentBean;
import com.Fertilizer.utility.HibernateUtility;

public class CreditLoanPaymentDao {
	
	public void creditLoanPaymentDetails(CreditLoanPaymentBean bean){
		System.out.println("In DAO");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("got session ");
			transaction = session.beginTransaction();

			System.out.println("Transation started");
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
}
