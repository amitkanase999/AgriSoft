package com.Fertilizer.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Fertilizer.bean.CreditProductReportBean;
import com.Fertilizer.dao.CreditLoanDao;
import com.Fertilizer.dao.CreditLoanPaymentDao;
import com.Fertilizer.hibernate.CreditLoanBean;
import com.Fertilizer.hibernate.CreditLoanPaymentBean;
import com.Fertilizer.utility.HibernateUtility;

public class CreditLoanPayment 
{
	 
	public void creditLoanPaymentDetail(HttpServletRequest request, HttpServletResponse response ) throws ParseException
	{	
		 Double updatedbalance=0.0;
		 
		String Customername=request.getParameter("Customername");
		String paymentdate=request.getParameter("paymentdate");
		String loannum=request.getParameter("loannum");
		String loanamt=request.getParameter("loanamt");
		
		String interestrate1=request.getParameter("interestrate1");
		String day=request.getParameter("day");
		String interestamt=request.getParameter("interestamt");
		String totalamt=request.getParameter("totalamt");
		
		String ptype=request.getParameter("ptype");
		String balance=request.getParameter("balance");
		String loanpaymentMode1=request.getParameter("loanpaymentMode1");
		String payamt=request.getParameter("payamt");
		String chequeno3=request.getParameter("chequeno3");
		String nameOnCheque3=request.getParameter("nameOnCheque3");
		String cardNum4=request.getParameter("cardNum4");
		String accNum4=request.getParameter("accNum4");
		String bankName4=request.getParameter("bankName4");
		

		CreditLoanPaymentBean bean=new CreditLoanPaymentBean(); 
		
		bean.setCustname(Customername);
		Date date1=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date1 = dateFormat.parse(paymentdate);
		bean.setPaymentdate(date1);
		
		bean.setLoanno(loannum);
		
		bean.setLoanamt(Double.parseDouble(loanamt));
		bean.setDay(Long.parseLong(day));
		bean.setInterestamount(Double.parseDouble(interestamt));
		bean.setTotalamount(Double.parseDouble(totalamt));
		
		bean.setInterestrate(Double.parseDouble(interestrate1));
		bean.setDay(Long.parseLong(day));
		
		bean.setPtype(ptype);
		bean.setBalance(Double.parseDouble(balance));
		bean.setLoanpaymentmode(loanpaymentMode1);
		bean.setPayamt(Double.parseDouble(payamt));
		bean.setChequeno(chequeno3);
		bean.setNameoncheque(nameOnCheque3);
		bean.setCardno(cardNum4);
		bean.setAccountno(accNum4);
		bean.setBankname(bankName4);
		
		
		/*----------- Update Status in Credit Loan Table --------------------*/
		        
		HibernateUtility hbu = HibernateUtility.getInstance();
		Session session1 = hbu.getHibernateSession();
		
		//Query to get latest paid amount
		Query query = session1.createSQLQuery("SELECT balance_status,paid_date from credit_loan where loan_no=:loannum");
		query.setParameter("loannum",loannum);
		List<Object[]> list = query.list();
		
		System.out.println("Calc total");
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			String newBalance = objects[0].toString();
			String paiddate=objects[1].toString();
            updatedbalance = Double.valueOf(newBalance);
			System.out.println(updatedbalance+"  bal");
			System.out.println("Calc balance");
		}
		
		
		//Double bal=Double.parseDouble(balance);
        Double pamt=Double.parseDouble(payamt);
        Double totalAmount=Double.parseDouble(totalamt);
        Double  remainingAmt = totalAmount-pamt;
		
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
       
		Transaction transaction = session.beginTransaction();
		
		CreditLoanDao dao1=new CreditLoanDao();
		List loanlist=dao1.getAllLoanEntry();
        
        System.out.println("List size"+loanlist.size());
		
        for(int j=0;j<loanlist.size();j++){
        	
        	
        CreditLoanBean clb = (CreditLoanBean)loanlist.get(j);
		String loanNum=clb.getLoanno();
		Long pk_loan_id=clb.getPk_loan_id();
		
		if(loannum.equals(loanNum))
		{
		
			System.out.println("inside if");
			
			CreditLoanBean updateloanamount = (CreditLoanBean) session.get(CreditLoanBean.class, new Long(pk_loan_id));
			updateloanamount.setBalance_status(remainingAmt);
			updateloanamount.setPaid_date(date1);
		 
			session.saveOrUpdate(updateloanamount);
			transaction.commit();
		}
        }
        /*-----------END OF  Update Status in Credit Loan Table --------------------*/
        
        
		
		CreditLoanPaymentDao dao=new CreditLoanPaymentDao();
		dao.creditLoanPaymentDetails(bean);
		
		
	}
	
	// Loan Report customer wise
	public List loanReportCustWise(
			HttpServletRequest request, HttpServletResponse response) {

		String custname1 = request.getParameter("custname1");

		System.out.println(custname1+"Customer in Helper");

		Map<Long,CreditLoanBean> map = new HashMap<Long,CreditLoanBean>();

		CreditLoanDao dao=new CreditLoanDao();
		List<CreditLoanBean> expList = dao.getAllLoanAndPaymentCustomerWiseReport(custname1);

		return expList;
	}
	
	// Loan Report customer wise
	public List creditReportCustWise(HttpServletRequest request, HttpServletResponse response) {

		String custname1 = request.getParameter("custname1");

		System.out.println(custname1+"Customer in Helper");

		Map<Long,CreditProductReportBean> map = new HashMap<Long,CreditProductReportBean>();

		CreditLoanDao dao=new CreditLoanDao();
		List<CreditProductReportBean> expList = dao.getCreditProductDetailsCustomerWiseReport(custname1);

		return expList;
	}
	
}
