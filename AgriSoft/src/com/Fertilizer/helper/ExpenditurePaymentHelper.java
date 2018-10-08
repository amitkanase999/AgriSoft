package com.Fertilizer.helper;

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

import com.Fertilizer.bean.CreditDebitReportBean;
import com.Fertilizer.bean.EmployeePaymentDetail;
import com.Fertilizer.bean.ExpenditurePaymentDetail;
import com.Fertilizer.dao.EmployeePaymentDao;
import com.Fertilizer.dao.ExpenditurePaymentDao;
import com.Fertilizer.hibernate.ExpenditurePaymentBean;
import com.Fertilizer.utility.HibernateUtility;

public class ExpenditurePaymentHelper {

	Double bal;
	public void regExpensePayment(HttpServletRequest request,
			HttpServletResponse response) {

		String expenseName = request.getParameter("expenseName");
		String serviceProvider = request.getParameter("serviceProvider");
		String expCredit = request.getParameter("expCredit");
		String expDebit = request.getParameter("expDebit");
		String contactNumber = request.getParameter("contactNumber");
		String accountantName = request.getParameter("accountantName");

		ExpenditurePaymentBean bean = new ExpenditurePaymentBean();

		HibernateUtility hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();
		Query query = session
				.createSQLQuery("SELECT total_amount ,fk_expense_detail_id from expenditure_payment ORDER BY  pk_expenditure_payment_id  DESC LIMIT 1 ;");
		List<Object[]> list = query.list();
		System.out.println(list.size());
		int listSize = list.size();
		System.out.println("list size in helper"+listSize);

		if(listSize==0){
			System.out.println("if block 1");
			if(!"".equals(expCredit))
			{
				bal = Double.parseDouble(expCredit);
				bean.setExpcredit(bal);
				bean.setTotalAmount(bal);
				bean.setExpDebit(0.0);
				System.out.println(expCredit);
			}

			else if(!"".equals(expDebit))
			{
				bean.setTotalAmount(Double.parseDouble(expDebit));
				System.out.println(expDebit);
			}
			else{
				bean.setTotalAmount(0.0);
			}
			bean.setFkExpDetailId(Long.parseLong(expenseName));
			bean.setServiceProvider(serviceProvider);
			bean.setAccountantName(accountantName);
			bean.setContactNumber(Long.parseLong(contactNumber));

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));

			bean.setInsertDate(dateobj);
			ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
			dao.addExpensePayment(bean);
		}
		else if(listSize > 0) {
			System.out.println("else block 1");

			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();

				bal= (Double) objects[0];

				System.out.println("balance"+bal);

			}
			if(!"".equals(expCredit)){

				bal = bal + Double.parseDouble(expCredit);
				bean.setTotalAmount(bal);
				bean.setExpcredit(Double.parseDouble(expCredit));
				if("".equals(expDebit)){
					bean.setExpDebit(0.0d);	 
				}
				else{
					bean.setExpDebit(Double.parseDouble(expDebit));
				}
				System.out.println(expCredit);

			}
			else if(!"".equals(expDebit)){
				bal = bal - Double.parseDouble(expDebit);
				bean.setTotalAmount(bal);
				bean.setExpDebit(Double.parseDouble(expDebit));
				if("".equals(expCredit)){
					bean.setExpcredit(0.0d);
				}
				else{
					bean.setExpcredit(Double.parseDouble(expCredit));
				}
			}

			bean.setFkExpDetailId(Long.parseLong(expenseName));
			bean.setServiceProvider(serviceProvider);
			bean.setAccountantName(accountantName);
			bean.setContactNumber(Long.parseLong(contactNumber));

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));

			bean.setInsertDate(dateobj);
			ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
			dao.addExpensePayment(bean);
		}
	}

	public List getExpensePaymentDetailsForSingleDate(
			HttpServletRequest request, HttpServletResponse response) {

		String fDate = request.getParameter("fDate");
		System.out.println(fDate+"Single Date");

		Map<Long,ExpenditurePaymentDetail> map = new HashMap<Long,ExpenditurePaymentDetail>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<ExpenditurePaymentDetail> expList = dao.getExpensePaymentDetailsForSingleDate(fDate);

		return expList;
	}
	public List getExpensePaymentDetailByTwoDate(HttpServletRequest request,
			HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,ExpenditurePaymentDetail> map = new HashMap<Long,ExpenditurePaymentDetail>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<ExpenditurePaymentDetail> exp1List = dao.getExpensePaymentDetailByTwoDates(fDate,tDate);

		return exp1List;
	}

	public List getTodayCreditDebitReport(HttpServletRequest request,
			HttpServletResponse response) {

		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.getTodayCreditDebitReport();

		return exp1List;
	}
	public List getTodayCreditDebitReport1(HttpServletRequest request,
			HttpServletResponse response) {
		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.getTodayCreditDebitReport1();

		return exp1List;
	}

	public List creditdebitForsingleDate(HttpServletRequest request,
			HttpServletResponse response) {
		String fDate = request.getParameter("fDate");
		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.creditdebitForsingleDate(fDate);

		return exp1List;
	}

	public List creditdebitForsingleDate1(HttpServletRequest request,
			HttpServletResponse response) {
		String fDate = request.getParameter("fDate");
		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.creditdebitForsingleDate1(fDate);

		return exp1List;
	}
	public List creditdebitForBetTowDate(HttpServletRequest request,
			HttpServletResponse response) {
		String fDate = request.getParameter("fisDate");
		String nDate = request.getParameter("endDate");

		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.creditdebitForBetTowDate(fDate,nDate);

		return exp1List;
	}

	public List creditdebitForBetTowDate1(HttpServletRequest request,
			HttpServletResponse response) {
		String fDate = request.getParameter("fisDate");
		String nDate = request.getParameter("endDate");

		Map<Long,CreditDebitReportBean> map = new HashMap<Long,CreditDebitReportBean>();

		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<CreditDebitReportBean> exp1List = dao.creditdebitForBetTowDate1(fDate,nDate);

		return exp1List;
	}

	public List getExpensePaymentDetailByTwoDateAsPerExpenseName(
			HttpServletRequest request, HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");
		String expenseName = request.getParameter("expenseName");

		Map<Long,ExpenditurePaymentDetail> map = new HashMap<Long,ExpenditurePaymentDetail>();
		ExpenditurePaymentDao dao = new ExpenditurePaymentDao();
		List<ExpenditurePaymentDetail> exp1List = dao.getExpensePaymentDetailByTwoDatesAsPerName(fDate,tDate,expenseName);

		return exp1List;
	}
}
