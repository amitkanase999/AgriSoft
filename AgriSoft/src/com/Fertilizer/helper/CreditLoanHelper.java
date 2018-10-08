package com.Fertilizer.helper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.CreditLoanDao;
import com.Fertilizer.hibernate.CreditLoanBean;
import com.Fertilizer.hibernate.LoanPaymentBean;

public class CreditLoanHelper {

	public void creditLoanDetail(HttpServletRequest request, HttpServletResponse response ) throws ParseException
	{
		String loanno=request.getParameter("loanno");
		String custname=request.getParameter("custname");
		String date=request.getParameter("date");
		String type=request.getParameter("type");
		String loanpaymentMode=request.getParameter("loanpaymentMode");
		//String interestrate=request.getParameter("interestrate");
		String loanamount=request.getParameter("loanamount");
		String chequeno2=request.getParameter("chequeno2");
		String nameOnCheque2=request.getParameter("nameOnCheque2");
		String cardNum3=request.getParameter("cardNum3");
		String accNum3=request.getParameter("accNum3");
		String bankName3=request.getParameter("bankName3");

		CreditLoanBean bean=new CreditLoanBean();
		
		Date date1=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date1 = dateFormat.parse(date);

		if(loanno!=null)
		{
			bean.setLoanno(loanno);
		}
		else
		{
			bean.setLoanno("N/A");
		}

		if(custname!=null)
		{
			bean.setCust_name(custname);
		}
		else
		{
			bean.setCust_name("N/A");
		}

		if(date!=null)
		{
			
			bean.setLoan_date(date1);
		}

		if(type!=null)
		{
			bean.setType(type);
		}
		else
		{
			bean.setType("N/A");
		}
		if(loanpaymentMode!=null)
		{
			bean.setPayment_mode(loanpaymentMode);
		}
		else
		{
			bean.setPayment_mode("N/A");
		}

		/*if(interestrate!=null)
		{

			bean.setInterest_rate(Double.parseDouble(interestrate));
		}
		else
		{
			bean.setInterest_rate(0.0d);
		}*/

		if(loanamount!=null)
		{

			bean.setLoan_amt(Double.parseDouble(loanamount));
		}
		else
		{
			bean.setLoan_amt(0.0d);
		}

		if(chequeno2!=null)
		{

			bean.setChequeno(chequeno2);
		}
		else
		{
			bean.setChequeno("N/A");
		}
		if(nameOnCheque2!=null)
		{

			bean.setNameoncheque(nameOnCheque2);
		}
		else
		{
			bean.setNameoncheque("N/A");
		}
		if(cardNum3!=null)
		{

			bean.setCardno(cardNum3);
		}
		else
		{
			bean.setCardno("N/A");
		}
		if(accNum3!=null)
		{

			bean.setAccountno(accNum3);
		}
		else
		{
			bean.setAccountno("N/A");
		}
		if(bankName3!=null)
		{

			bean.setBankname(bankName3);
		}
		else
		{
			bean.setBankname("N/A");
		}
		
		bean.setBalance_status(Double.parseDouble(loanamount));
		bean.setPaid_date(date1);
		
		CreditLoanDao dao=new CreditLoanDao();
		dao.creditLoanDetails(bean);
	}
	
	//Loan Payment Details Helper
	public void loanPayment(HttpServletRequest request, HttpServletResponse response ) throws ParseException
	{
		String loanCustname=request.getParameter("loanCustname");
		String paydate=request.getParameter("paydate");
		String paytype=request.getParameter("paytype");
		String loanpaymentMode1=request.getParameter("loanpaymentMode1");
		String payamount=request.getParameter("payamount");
		String chequeno3=request.getParameter("chequeno3");
		String nameOnCheque3=request.getParameter("nameOnCheque3");
		String cardNum4=request.getParameter("cardNum4");
		String accountNum4=request.getParameter("accountNum4");
		String nameofbank4=request.getParameter("nameofbank4");
	

		LoanPaymentBean bean=new LoanPaymentBean();
		
		Date date1=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date1 = dateFormat.parse(paydate);

	
		bean.setCustname(loanCustname);
		bean.setPaydate(date1);
		bean.setType(paytype);
		bean.setPaymentmode(loanpaymentMode1);
		bean.setAmount(Double.parseDouble(payamount));
		bean.setChequeno(chequeno3);
		bean.setNameoncheque(nameOnCheque3);
		bean.setCardno(cardNum4);
		bean.setAccountno(accountNum4);
		bean.setBankname(nameofbank4);
		
		CreditLoanDao dao=new CreditLoanDao();
		dao.LoanPaymentDetails(bean);
	}
	
	public Map getAllCustLoanNos(String loancustomer) {

		CreditLoanDao dao=new CreditLoanDao();
		//SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getAllCustLoanNumbers(loancustomer);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			CreditLoanBean bean=new CreditLoanBean();
			bean.setLoanno(o[0].toString());
			bean.setLoan_amt(Double.parseDouble(o[1].toString()));
			
			System.out.println("***************"+o[0]);
			map.put(bean.getLoanno(),bean);
			
		}
		return map;
	}
	
	public Map generateLonno() {

		CreditLoanDao dao=new CreditLoanDao();
		List list= dao.getMaxLoaNo();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			CreditLoanBean bean=new CreditLoanBean();
			
			System.out.println("***************"+o[0]);
			
			
			if(o[0]==null)
			{
				Long defaultval=1001L;
				Double amt=0.0;
				bean.setMaxLoanNo(defaultval);
				bean.setLoan_amt(amt);
				map.put(bean.getMaxLoanNo(),bean);
			}
			else
			{
				
				String lonno=o[0].toString();
				Calendar now = Calendar.getInstance();   // Gets the current date and time
				int year = now.get(Calendar.YEAR);
				int nextyear=year+1;
				String stryear1=Integer.toString(year);
				String stryear2=Integer.toString(nextyear);
			
				String lasttwodigit1 = stryear1.substring(2,4);
				String lasttwodigit2 = stryear2.substring(2,4);
			
				//String newloanno="DULOAN/"+lonno+"/"+lasttwodigit1+"-"+lasttwodigit2;
				Long lonNum=Long.parseLong(lonno);
				Long newloanno=lonNum+1;
			
				System.out.println("=============================================New Loan no :"+newloanno);
			
				bean.setMaxLoanNo(newloanno);
				bean.setLoan_amt(Double.parseDouble(o[1].toString()));
				map.put(bean.getMaxLoanNo(),bean);
			}
		
			
		
			
		}
		return map;
	}
	
	
	public Map getAllLoanNoWiseDetails(String loannum,String paymentdate) throws ParseException {

		
		CreditLoanDao dao=new CreditLoanDao();
		List list= dao.getAllLoanDetails(loannum);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			CreditLoanBean bean=new CreditLoanBean();
			bean.setLoan_amt(Double.parseDouble(o[0].toString()));
			bean.setInterest_rate(Double.parseDouble(o[1].toString()));
			bean.setLoan_date((Date)o[2]);
			bean.setBalance_status(Double.parseDouble(o[3].toString()));
			
			Double loanamount=(Double.parseDouble(o[0].toString()));
			Double interestrate=(Double.parseDouble(o[1].toString()));
			Date date1=((Date)o[2]);
			Double status_amt=(Double.parseDouble(o[3].toString()));
			
			System.out.println("***************"+o[0]);
			/*Getting System Date */
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println("current date is :"+date);
			System.out.println(dateFormat.format(date));*/
			/*End Of Getting System Date */
			
			
			/*Getting payment date from User*/
			Date date=null;
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			date = dateFormat1.parse(paymentdate);
			System.out.println("pay date :"+date);
			/*End of Getting payment date from User*/
			
			
			/*Calculate date Difference day wise*/
	        Long diffInDays = (Long) ((date.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
	        diffInDays=diffInDays+1;
	        bean.setDays(diffInDays);
	        System.out.println("No of Days :"+diffInDays);
	        
	        /*convert days to month*/
			Double ratePerDay=interestrate/30;
			System.out.println("Days To Month :  "+ratePerDay);
			
			/*total interest rate as per day*/
			Double totalRate=ratePerDay*diffInDays;
			
			/*calculate Interest Amount */
			Double interestamount=(status_amt * totalRate)/100;
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			String intamt=numberFormat.format(interestamount);
			Double interestamt=(Double.parseDouble(intamt));
			bean.setInterestamount(interestamt);
			System.out.println("Interest Amount  : "+interestamt);
			
			/*Calculate Total Loan Amount (with interest)*/
			Double Totalamount=(status_amt+interestamount);
			
			DecimalFormat numberFormat1 = new DecimalFormat("#.00");
			String totamt=numberFormat1.format(Totalamount);
			Double totalamt=(Double.parseDouble(totamt));
			bean.setTotalamount(totalamt);
			
		
/*			Calendar now = Calendar.getInstance();   // Gets the current date and time
			int year = now.get(Calendar.YEAR);   
			System.out.println("=============================================Year :"+year);
			int nextyear=year+1;
			System.out.println("=============================================next year :"+nextyear);
			System.out.println("=============================================next year :"+year+"-"+nextyear);
			
			String stryear1=Integer.toString(year);
			String stryear2=Integer.toString(nextyear);
			
			String lasttwodigit1 = stryear1.substring(2,4);
			String lasttwodigit2 = stryear2.substring(2,4);
			System.out.println("=============================================Current year :"+lasttwodigit1+"-"+lasttwodigit2);
			*/
			
			map.put(bean.getLoan_amt(),bean);
			
		}
		return map;
	}

}
