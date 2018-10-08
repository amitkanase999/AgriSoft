package com.Fertilizer.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.LoanCashBookReportBean;
import com.Fertilizer.utility.HibernateUtility;

public class LoanCashBookReportDao {

	public List<LoanCashBookReportBean> getCustomerNameWiseLoanReport(String custname,String cloaserdate,String interestrate) throws ParseException 
	{
		Date cldate=null;
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		cldate = dateFormat3.parse(cloaserdate);
		
		Double interRate=Double.parseDouble(interestrate);
		HibernateUtility hbu=null;
		Session session=null;
		List<LoanCashBookReportBean> custList = null;
		try
		{
			Double Loanamount=0.0d;
			Double interestrate1=0.0d;
			Double interestamount=.0d;
			Double interestperday=0.0d;
			Date loandate=null;
			Date paiddate=null;
			Double paidamount=0.0d;
			Double paidamountinterest=0.0d;
			Double paitinterestamt=0.0d;
			//Double tempIR=3.0d;
			Double Totalpaid=0.0d;
			System.out.println("==================In DAO");
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT cl.customer_name,cl.loan_no,cl.loan_date,cl.loan_amount,cl.interest_rate ,'2018-05-16' as PaidDate,'0.0' as Amount  FROM credit_loan cl WHERE cl.customer_name='"+custname+"'  UNION ALL SELECT bc.customer_name,bc.bill_no,bc.bill_date,bc.gross_total,bc.interest_rate,'2018-05-16' as paidDate,'0.0' as Amount  FROM billing_collection bc where bc.customer_name='"+custname+"' UNION ALL SELECT lp.cust_name as dummy1,'0' as dummy2,lp.payment_date,'0.0' as dummy4,'0.0' as dummy5,lp.payment_date,lp.amount FROM loanpaymentdetails lp WHERE lp.cust_name='"+custname+"'");
			//query.setParameter("custname", custname);
			
			List<Object[]> list = query.list();
			custList = new ArrayList<LoanCashBookReportBean>(0);

			for (Object[] o : list) {

				Date date1=null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				date1 = dateFormat.parse(o[2].toString());
				
				loandate=date1;
				Loanamount=Double.parseDouble(o[3].toString());
				interestrate1=Double.parseDouble(o[4].toString());
				
				/*== Paid Date ==*/
				Date date2=null;
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				date2 = dateFormat2.parse(o[5].toString());
				paiddate=date2;
				
				//paid amount
				paidamount=Double.parseDouble(o[6].toString());
				System.out.println("============Paid Amount"+paidamount);
				
			
				/*Getting payment date from User*/
				Date date=null;
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				date = dateFormat1.parse(cloaserdate);
				System.out.println("pay date :"+date);
				/*End of Getting payment date from User*/
				
				
				/*Calculate date Difference day wise*/
		        Long diffInDays = (Long) ((date.getTime() - loandate.getTime()) / (1000 * 60 * 60 * 24));
		        diffInDays=diffInDays+1;
		        System.out.println("===============No of Days :"+diffInDays);
		       // diffInDays=diffInDays+1;
		        System.out.println("===============No of Days After Adding 1 day :"+diffInDays);
		        
		        /*Interest Amount Calculation*/
		        interestperday= (Loanamount * (interRate/100))/30;
		        interestamount=interestperday*diffInDays;
		        double IterestPerroundOff = Math.round(interestperday * 100.0) / 100.0;
		        double interestamountroundOff = Math.round(interestamount * 100.0) / 100.0;
		        System.out.println("===============Interest Amount :"+interestamountroundOff);
		        /*End of Interest Amount Calculation*/
		        
		        
		        
		        /*Calculate date Difference day wise for paid Amount*/
		        Long diffInDays1 = (Long) ((cldate.getTime() - paiddate.getTime()) / (1000 * 60 * 60 * 24));
		        diffInDays1=diffInDays+1;
		        System.out.println("===============No of Days After Adding 1 day :"+diffInDays1);
		    
		        /*Interest Amount Calculation*/
		        System.out.println("===========paid amount"+paidamount);
		        System.out.println("=========== "+interestrate);
		        paidamountinterest= (paidamount * (interRate/100))/30;
		        paitinterestamt=paidamountinterest*diffInDays1;
		        Totalpaid=paidamount+paitinterestamt;
		        double paidamountroundOff = Math.round(paidamount * 100.0) / 100.0;
		        double TotalpaidroundOff = Math.round(Totalpaid * 100.0) / 100.0;
		        System.out.println("==========interest on paid Amount : "+paitinterestamt);
		        
		        
		        LoanCashBookReportBean reports=new LoanCashBookReportBean();
				reports.setCustname(o[0].toString());
				reports.setLoanno(Long.parseLong(o[1].toString()));
				reports.setLoandate(loandate);
				reports.setLoanamount(Loanamount);
				reports.setInterest_rate(interRate);
				reports.setDays(diffInDays);
				reports.setInterest_amount(interestamountroundOff);
				reports.setInterest_per_day(IterestPerroundOff);
				
				reports.setPaiddate(paiddate);
				reports.setPaidamount(paidamountroundOff);
				reports.setPaidamountinterest(TotalpaidroundOff);
				
				custList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return custList;	
	}
	
}
