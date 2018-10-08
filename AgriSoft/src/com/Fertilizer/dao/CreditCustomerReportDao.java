package com.Fertilizer.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.CreditCustomerSaleReportBean;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.utility.HibernateUtility;

public class CreditCustomerReportDao {

	public List<CreditCustomerSaleReportBean> getCreditCustomerSaleReport(String StartDate,String EndDate) {
		Double totalWithGST=0.0;
		System.out.println(StartDate+"StartDate in dao");
		System.out.println(EndDate+"EndDate in dao");
		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustomerSaleReportBean> saleList=null;
		List<CreditCustomerSaleReportBean> saleList1=null;
		List<CreditCustomerSaleReportBean> totalList=new ArrayList<CreditCustomerSaleReportBean>();
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("(SELECT fb.customer_name,fb.insert_date,c.cat_name,fb.bill_no,fb.gross_total,sum(COALESCE(lp.amount,0)) from fertilizer_billing fb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON fb.customer_name=lp.cust_name LEFT JOIN categories c ON fb.cat_id=c.pk_cat_id  where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  fb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"(SELECT sb.customer_name,sb.insert_date,c.cat_name,sb.bill_no,sb.gross_total,sum(COALESCE(lp.amount,0))from seed_pesticide_billing sb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON sb.customer_name=lp.cust_name LEFT JOIN categories c ON sb.cat_id=c.pk_cat_id  where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  sb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(SELECT pb.customer_name,pb.insert_date,c.cat_name,pb.bill_no,pb.gross_total,sum(COALESCE(lp.amount,0)) from pesticide_billing pb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON pb.customer_name=lp.cust_name LEFT JOIN categories c ON pb.cat_id=c.pk_cat_id where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  pb.gross_total)\r\n" + 
					"");
					
			/*query.setParameter("cat", cat);
			query.setParameter("productName", productName);
			query.setParameter("fDate", fDate);*/
			

			List<Object[]> list = query.list();
			saleList= new ArrayList<CreditCustomerSaleReportBean>(0);
			Double billtotal=0.0d;
			for (Object[] object : list) {

				
				
				CreditCustomerSaleReportBean reports=new CreditCustomerSaleReportBean();
				
				reports.setCustname(object[0].toString());
				reports.setDate((Date) object[1]);
				reports.setCatname(object[2].toString());
				reports.setBillno(object[3].toString());
				reports.setBillAmount(Double.parseDouble(object[4].toString()));
				reports.setPaidAmt(Double.parseDouble(object[5].toString()));
				
				
				saleList.add(reports);
			}
			
			//Modification
			
//			Query query1 = session.createSQLQuery("SELECT customer_name,SUM(DISTINCT(gross_total)),sum(DISTINCT(COALESCE(amount,0)))FROM((SELECT fb.customer_name,fb.gross_total,sum(COALESCE(lp.amount,0)) from fertilizer_billing fb \r\n" + 
//				"LEFT JOIN loanpaymentdetails lp ON fb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  fb.gross_total)\r\n" + 
//				"\r\n" + 
//				"UNION ALL\r\n" + 
//				"(SELECT sb.customer_name,sb.gross_total,sum(COALESCE(lp.amount,0))from seed_pesticide_billing sb \r\n" + 
//				"LEFT JOIN loanpaymentdetails lp ON sb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  sb.gross_total)\r\n" + 
//				"\r\n" + 
//				"UNION ALL\r\n" + 
//				"\r\n" + 
//				"(SELECT pb.customer_name,pb.gross_total,sum(COALESCE(lp.amount,0)) from pesticide_billing pb \r\n" + 
//				"LEFT JOIN loanpaymentdetails lp ON pb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  pb.gross_total))x LEFT JOIN loanpaymentdetails ON loanpaymentdetails.cust_name=x.customer_name GROUP BY customer_name\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"");
//				
//
//			List<Object[]> list1 = query1.list();
//			saleList1= new ArrayList<CreditCustomerSaleReportBean>(0);
//			CreditCustomerSaleReportBean reports1=new CreditCustomerSaleReportBean();
//			for (Object[] object : list1) {
//
//				
//				
//				
//				billtotal=Double.parseDouble(object[1].toString());
////				Double TotalPaidAmt=Double.parseDouble(object[2].toString());
////				Double BalanceAmt=TotalBillAmt-TotalPaidAmt;
//				reports1.setBillAmount(billtotal);
//				saleList.add(reports1);				
//			}	
//			
//			
//				
//		//*******************************************
			
			
			CreditCustomerSaleReportBean reports2=new CreditCustomerSaleReportBean();
			//reports2.setBillAmount(billtotal);
			totalList.addAll(saleList);
			//totalList.add(reports2);
			//totalList.add(reports);
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return totalList;
	}

	
	public List<CreditCustomerSaleReportBean> getCreditCustomerSaleBalanceReport(String StartDate,String EndDate) {
		Double totalWithGST=0.0;
		System.out.println(StartDate+"StartDate in dao");
		System.out.println(EndDate+"EndDate in dao");
		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustomerSaleReportBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT customer_name,SUM(DISTINCT(gross_total)),sum(DISTINCT(COALESCE(amount,0)))FROM((SELECT fb.customer_name,fb.gross_total,sum(COALESCE(lp.amount,0)) from fertilizer_billing fb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON fb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  fb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"(SELECT sb.customer_name,sb.gross_total,sum(COALESCE(lp.amount,0))from seed_pesticide_billing sb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON sb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  sb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(SELECT pb.customer_name,pb.gross_total,sum(COALESCE(lp.amount,0)) from pesticide_billing pb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON pb.customer_name=lp.cust_name where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  pb.gross_total))x LEFT JOIN loanpaymentdetails ON loanpaymentdetails.cust_name=x.customer_name GROUP BY customer_name\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"");
			/*query.setParameter("cat", cat);
			query.setParameter("productName", productName);
			query.setParameter("fDate", fDate);*/
			

			List<Object[]> list = query.list();
			saleList= new ArrayList<CreditCustomerSaleReportBean>(0);

			for (Object[] object : list) {

				
				CreditCustomerSaleReportBean reports=new CreditCustomerSaleReportBean();
				
				Double TotalBillAmt=Double.parseDouble(object[1].toString());
				Double TotalPaidAmt=Double.parseDouble(object[2].toString());
				Double BalanceAmt=TotalBillAmt-TotalPaidAmt;
				
				reports.setCustname(object[0].toString());
				reports.setBillAmount(TotalBillAmt);
				reports.setPaidAmt(TotalPaidAmt);
				reports.setBalanceAmt(BalanceAmt);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}
	
	
	
	public List<CreditCustomerSaleReportBean> getCreditCustomerSaleNameAndRangeReport(String StartDate,String EndDate,String Customername) {
		Double totalWithGST=0.0;
		System.out.println(StartDate+"StartDate in dao");
		System.out.println(EndDate+"EndDate in dao");
		System.out.println(Customername+"EndDate in dao");
		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustomerSaleReportBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("(SELECT fb.customer_name,fb.insert_date,c.cat_name,fb.bill_no,fb.gross_total,sum(COALESCE(lp.amount,0)) from fertilizer_billing fb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON fb.customer_name=lp.cust_name LEFT JOIN categories c ON fb.cat_id=c.pk_cat_id  where fb.customer_name='"+Customername+"' AND (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  fb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"(SELECT sb.customer_name,sb.insert_date,c.cat_name,sb.bill_no,sb.gross_total,sum(COALESCE(lp.amount,0))from seed_pesticide_billing sb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON sb.customer_name=lp.cust_name LEFT JOIN categories c ON sb.cat_id=c.pk_cat_id  where sb.customer_name='"+Customername+"' AND (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  sb.gross_total)\r\n" + 
					"\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(SELECT pb.customer_name,pb.insert_date,c.cat_name,pb.bill_no,pb.gross_total,sum(COALESCE(lp.amount,0)) from pesticide_billing pb \r\n" + 
					"LEFT JOIN loanpaymentdetails lp ON pb.customer_name=lp.cust_name LEFT JOIN categories c ON pb.cat_id=c.pk_cat_id where pb.customer_name='"+Customername+"' AND (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND PayCustType=\"credit\" GROUP BY  pb.gross_total)\r\n" + 
					"");
			/*query.setParameter("cat", cat);
			query.setParameter("productName", productName);
			query.setParameter("fDate", fDate);*/
			

			List<Object[]> list = query.list();
			saleList= new ArrayList<CreditCustomerSaleReportBean>(0);

			for (Object[] object : list) {

				
				CreditCustomerSaleReportBean reports=new CreditCustomerSaleReportBean();
				
				reports.setCustname(object[0].toString());
				reports.setDate((Date) object[1]);
				reports.setCatname(object[2].toString());
				reports.setBillno(object[3].toString());
				reports.setBillAmount(Double.parseDouble(object[4].toString()));
				reports.setPaidAmt(Double.parseDouble(object[5].toString()));
				
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	
}
