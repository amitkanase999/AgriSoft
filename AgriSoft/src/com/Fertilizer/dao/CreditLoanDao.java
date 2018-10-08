package com.Fertilizer.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.CreditProductReportBean;
import com.Fertilizer.hibernate.CreditLoanBean;
import com.Fertilizer.hibernate.LoanPaymentBean;
import com.Fertilizer.utility.HibernateUtility;

public class CreditLoanDao {

	public void creditLoanDetails(CreditLoanBean bean){
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
		hbu.closeSession(session);
	}

	
	
	public void LoanPaymentDetails(LoanPaymentBean bean){
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
		hbu.closeSession(session);
	}
	
	
	public List getAllCustomer()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from CreditLoanBean GROUP BY customer_name");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllCustomer", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
	
	
	public List getAllCustLoanNumbers(String loancustomer) {
		
		 HibernateUtility hbu = null ;
   	 Session session = null;
   	 List list  = null;
   	 System.out.println("Loan Customer"+loancustomer);
   	 try {
   		 hbu = HibernateUtility.getInstance();
   		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select loan_no,loan_amount FROM credit_loan where customer_name=:loancustomer");
			query.setParameter("loancustomer", loancustomer);
			list = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
   	 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
			return list;
	
	}
	
	
	public List getMaxLoaNo() {
		
		 HibernateUtility hbu = null ;
  	 Session session = null;
  	 List list  = null;
  	// System.out.println("Loan Customer"+loancustomer);
  	 try {
  		 hbu = HibernateUtility.getInstance();
  		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT MAX(loan_no),loan_amount FROM credit_loan");
			//query.setParameter("loancustomer", loancustomer);
			list = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
  	 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
			return list;
	
	}

	public List getAllLoanDetails(String loannum) {
		
		 HibernateUtility hbu = null ;
  	 Session session = null;
  	 List list  = null;
  	 System.out.println("Loan Customer"+loannum);
  	 try {
  		 hbu = HibernateUtility.getInstance();
  		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT loan_amount,interest_rate,loan_date,balance_status from credit_loan WHERE loan_no=:loannum");
			query.setParameter("loannum", loannum);
			list = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
  	 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
			return list;
	
	}
	
	public List getAllLoanEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from CreditLoanBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllLoanDetails", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
	
	// All Loan payment details for Cashbook report

		public List getAllLoanPaymentDetailForReport(){
			HibernateUtility hbu=null;
			Session session=null;
			List<CreditLoanBean> productList=null;
			try
			{
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("SELECT  loan_no,customer_name,type,payment_mode,loan_date,interest_rate,loan_amount,balance_status,paid_date from credit_loan");
				List<Object[]> list = query.list();
				productList= new ArrayList<CreditLoanBean>(0);	
				for (Object[] o : list) {
					System.out.println(Arrays.toString(o));

					CreditLoanBean reports = new CreditLoanBean();
					String paymentType = o[4].toString();
					reports.setLoanno(o[0].toString());
					reports.setCust_name((o[1].toString()));
					reports.setType((o[2].toString()));
					reports.setPayment_mode((o[3].toString()));
					reports.setLoanDate((o[4].toString()));
					reports.setInterest_rate(Double.parseDouble(o[5].toString()));
					
					Double loanAmount=Double.parseDouble(o[6].toString());
					Double balance=Double.parseDouble(o[7].toString());
					Double paid=loanAmount-balance;
					NumberFormat formatter = new DecimalFormat("#0.00");  
					
					reports.setLoan_amt(Double.parseDouble(o[6].toString()));
					reports.setBalance_status(Double.parseDouble(o[7].toString()));
					reports.setPaid_amt(Double.parseDouble(formatter.format(paid)));
					reports.setPaidDate((o[8].toString()));

					productList.add(reports);		
				}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{

				if(session!=null){
					hbu.closeSession(session);	
				}
			}
			return productList;
		}
		
		
		public List getAllLoanAndPaymentCustomerWiseReport(String custname1 ){
			HibernateUtility hbu=null;
			Session session=null;
			List<CreditLoanBean> loanList=null;
			try
			{
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("select cl.loan_no,cl.customer_name,cl.loan_date,cl.loan_amount,cl.interest_rate,cl.balance_status,cl.paid_date,clp.pay_amt from credit_loan cl INNER JOIN creditloan_payment clp ON cl.loan_no=clp.loannum where customer_name=:custname1");
				query.setParameter("custname1", custname1);
				
				List<Object[]> list = query.list();
				loanList= new ArrayList<CreditLoanBean>(0);	
				for (Object[] o : list) {
					System.out.println(Arrays.toString(o));

					CreditLoanBean reports = new CreditLoanBean();
					
					reports.setLoanno(o[0].toString());
					reports.setCust_name(o[1].toString());
					reports.setLoan_date((Date)o[2]);
					reports.setLoan_amt(Double.parseDouble(o[3].toString()));
					reports.setInterest_rate(Double.parseDouble(o[4].toString()));
					reports.setBalance_status(Double.parseDouble(o[5].toString()));
					reports.setPaid_date((Date)o[6]);
					reports.setPaid_amt(Double.parseDouble(o[7].toString()));
					
					

					loanList.add(reports);		
				}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{

				if(session!=null){
					hbu.closeSession(session);	
				}
			}
			return loanList;
		}
		
		
		public List getCreditProductDetailsCustomerWiseReport(String custname1 ){
			HibernateUtility hbu=null;
			Session session=null;
			List<CreditProductReportBean> loanList=null;
			try
			{
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("SELECT fb.Cust_Name,fb.newbalance_status,sb.seed_balance_status,pb.Pestcide_Balance_Status from fertilizer_billing fb INNER JOIN  seed_pesticide_billing sb ON fb.Cust_Name=sb.Cust_Name INNER JOIN pesticide_billing pb ON fb.Cust_Name=pb.Cust_Name where fb.Cust_Name=:custname1");   //:custname1      
				query.setParameter("custname1", custname1);
				
				List<Object[]> list = query.list();
				loanList= new ArrayList<CreditProductReportBean>(0);	
				for (Object[] o : list) {
					System.out.println(Arrays.toString(o));

					CreditProductReportBean reports=new CreditProductReportBean();
					
					Double fertilizerbalance=Double.parseDouble(o[1].toString());
					Double Seedbalance=Double.parseDouble(o[1].toString());
					Double Pesticidebalance=Double.parseDouble(o[1].toString());
					
					System.out.println("fetilizer balance =========="+fertilizerbalance);
					
					reports.setCustname(o[0].toString());
					if(fertilizerbalance !=null)
					{
						reports.setFertilizerBalance(Double.parseDouble(o[1].toString()));
					}
					else
					{
						reports.setFertilizerBalance(0.0);
					}
					if(Seedbalance !=null)
					{
						reports.setSeedBalance(Double.parseDouble(o[2].toString()));
					}
					else
					{
						reports.setSeedBalance(0.0);
					}
					
					if(Pesticidebalance !=null)
					{
						reports.setPesticideBalancel(Double.parseDouble(o[3].toString()));
					}
					else
					{
						reports.setSeedBalance(0.0);
					}

					loanList.add(reports);		
				}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{

				if(session!=null){
					hbu.closeSession(session);	
				}
			}
			return loanList;
		}

}
