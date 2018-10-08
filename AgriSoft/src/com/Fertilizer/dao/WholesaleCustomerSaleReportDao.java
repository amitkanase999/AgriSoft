package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.CreditCustomerSaleReportBean;
import com.Fertilizer.bean.WholesaleCustomerSaleReportBean;
import com.Fertilizer.utility.HibernateUtility;

public class WholesaleCustomerSaleReportDao {

	
	//wholesale Customer Range wise sale report
	public List<WholesaleCustomerSaleReportBean> getWholesaleCustomerSaleReport(String StartDate,String EndDate) {
		Double totalWithGST=0.0;
		System.out.println(StartDate+"StartDate in dao");
		System.out.println(EndDate+"EndDate in dao");
		
		HibernateUtility hbu=null;
		Session session=null;
		List<WholesaleCustomerSaleReportBean> saleList=null;
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("(SELECT fb.customer_name,fb.insert_date,c.cat_name,fb.bill_no,fb.gross_total,sum(COALESCE(lp.amount,0)) from fertilizer_billing fb\r\n" + 
					"					LEFT JOIN loanpaymentdetails lp ON fb.customer_name=lp.cust_name LEFT JOIN categories c ON fb.cat_id=c.pk_cat_id  where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND customer_type=\"Wholesale\" GROUP BY  fb.gross_total) \r\n" + 
					"					\r\n" + 
					"					UNION ALL \r\n" + 
					"					(SELECT sb.customer_name,sb.insert_date,c.cat_name,sb.bill_no,sb.gross_total,sum(COALESCE(lp.amount,0))from seed_pesticide_billing sb \r\n" + 
					"					LEFT JOIN loanpaymentdetails lp ON sb.customer_name=lp.cust_name LEFT JOIN categories c ON sb.cat_id=c.pk_cat_id  where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND customer_type=\"Wholesale\" GROUP BY  sb.gross_total) \r\n" + 
					"					 \r\n" + 
					"					UNION ALL \r\n" + 
					"					\r\n" + 
					"					(SELECT pb.customer_name,pb.insert_date,c.cat_name,pb.bill_no,pb.gross_total,sum(COALESCE(lp.amount,0)) from pesticide_billing pb \r\n" + 
					"					LEFT JOIN loanpaymentdetails lp ON pb.customer_name=lp.cust_name LEFT JOIN categories c ON pb.cat_id=c.pk_cat_id where (insert_date BETWEEN '"+StartDate+"' AND '"+EndDate+"') AND customer_type=\"Wholesale\" GROUP BY  pb.gross_total)\r\n" + 
					"					");
					
			
			List<Object[]> list = query.list();
			saleList= new ArrayList<WholesaleCustomerSaleReportBean>(0);
			Double billtotal=0.0d;
			for (Object[] object : list) {

				
				WholesaleCustomerSaleReportBean reports=new WholesaleCustomerSaleReportBean();
				//CreditCustomerSaleReportBean reports=new CreditCustomerSaleReportBean();
				
				reports.setCustName(object[0].toString());
				reports.setDate((Date) object[1]);
				reports.setCategory(object[2].toString());
				reports.setBillNo(object[3].toString());
				reports.setBillAmount(Double.parseDouble(object[4].toString()));
				reports.setPaidAmount(Double.parseDouble(object[5].toString()));
				
				
				saleList.add(reports);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;
	}
}
