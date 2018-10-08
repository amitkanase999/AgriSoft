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

import com.Fertilizer.bean.CreditLoanPaymentReportBean;
import com.Fertilizer.bean.SupplierPaymentDetail;
import com.Fertilizer.dao.SupplierPaymentDao;
import com.Fertilizer.hibernate.CreditLoanBean;
import com.Fertilizer.hibernate.SupplierPaymentBean;
import com.Fertilizer.utility.HibernateUtility;

public class SupplierCashBankHelper {

	Double bal  ;

	public void regSupplierCashBank(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In helper");

		String supplier = request.getParameter("supplier");
		String totalAmount = request.getParameter("totalAmount");
		String balAmount = request.getParameter("balanceAmount");
		String bankName = request.getParameter("bankName");
		String paymentType = request.getParameter("paymentType");
		String paymentMode = request.getParameter("paymentMode");
		String chequeNum = request.getParameter("chequeNum");
		String cardNum = request.getParameter("cardNum");
		String accNum = request.getParameter("accNum");
		String creditnotenumber = request.getParameter("creditnotenumber");
		String cramount = request.getParameter("cramount");
		String personname = request.getParameter("personname");
		String nameOnCheck = request.getParameter("nameOnCheck");
		String supPay = request.getParameter("supPay");
		String receiptno = request.getParameter("receiptno");
		SupplierPaymentBean bean = new SupplierPaymentBean();

		bean.setSupplier(Long.parseLong(supplier));

		bean.setPersonname(personname);

		System.out.println("paymentMode "+paymentMode);

		//payment details

		if(paymentMode==null){
			bean.setPaymentMode("N/A");
		}
		else{
			bean.setPaymentMode(paymentMode);
		}
		if(receiptno==null)
		{
			bean.setReceiptno(Long.parseLong("0"));
		}
		else
		{
			bean.setReceiptno(Long.parseLong(receiptno));
		}

		if(paymentMode.equals("cheque")){

			if(chequeNum==null){
				bean.setChequeNum("N/A");
			}
			else{
				bean.setChequeNum(chequeNum);
			}

			if(nameOnCheck==null){
				bean.setNameOnCheck("N/A");
			}
			else{
				bean.setNameOnCheck(nameOnCheck);
			}
		}
		else if(paymentMode.equals("card")){

			int cardNumLength = cardNum.length();
			if(cardNumLength > 0){

				bean.setCardNum(Long.parseLong(cardNum));
			}
			else{
				bean.setCardNum(null);
			}
		}

		else if(paymentMode.equals("neft")){
			if(bankName==null){
				bean.setBankName("N/A");
			}
			else{
				bean.setBankName(bankName);
			}

			int accNumLength = accNum.length();
			if(accNumLength > 0){
				bean.setAccNum(Long.parseLong(accNum));	

			}
			else{
				bean.setAccNum(null);
			}
		}

		if(paymentMode.equals("creditnote")){

			if(creditnotenumber==null){
				bean.setCreditnotenumber("N/A");
			}
			else{
				bean.setCreditnotenumber(creditnotenumber);
			}

			if(cramount==null){
				bean.setCramount(0.0d);
			}
			else{
				bean.setCramount(Double.parseDouble(cramount));
			}
		}

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));

		bean.setInsertDate(dateobj);

		HibernateUtility hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();

		//Query to get latest paid amount

		Query query = session
				.createSQLQuery("SELECT balance ,bill_no from supplier_payment ORDER BY  pk_supplier_payment_id  DESC LIMIT 1 ;");
		List<Object[]> list = query.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			String newBal = objects[0].toString();
			bal= Double.valueOf(newBal);
			System.out.println(bal);
		}

		if (bal==null) {

			Double balance = Double.parseDouble(totalAmount);

			if(paymentType.equals("credit"))
			{
				Double newBal = balance - Double.parseDouble(supPay);

				bean.setBalance(newBal);
				bean.setPaymentType(paymentType);
				bean.setCredit(Double.parseDouble(supPay));
			}

			else{
				if(paymentType.equals("debit"))
				{
					Double newBal = balance + Double.parseDouble(supPay);

					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(supPay));
				}
				else{

					bean.setCredit(0.0);
				}
			}

			bean.setTotalAmount(Double.parseDouble(totalAmount));

			SupplierPaymentDao dao = new SupplierPaymentDao();
			dao.regSupPayment(bean);
		}
		else{

			Double balance = Double.parseDouble(balAmount);
			if(paymentType.equals("credit"))
			{
				Double newBal = balance - Double.parseDouble(supPay);

				bean.setBalance(newBal);
				bean.setPaymentType(paymentType);
				bean.setCredit(Double.parseDouble(supPay));
			}

			else{
				if(paymentType.equals("debit"))
				{
					Double newBal = balance + Double.parseDouble(supPay);
					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(supPay));
				}
				else{

					bean.setCredit(0.0);
				}
			}

			bean.setTotalAmount(Double.parseDouble(totalAmount));

			SupplierPaymentDao dao = new SupplierPaymentDao();
			dao.regSupPayment(bean);
		}
	}

	public List getSupplierPaymentDetailsBySingleDate(
			HttpServletRequest request, HttpServletResponse response) {
		String fDate = request.getParameter("fDate");
		System.out.println(fDate+"Single Date");

		Map<Long,SupplierPaymentDetail> map = new HashMap<Long,SupplierPaymentDetail>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<SupplierPaymentDetail> supList = dao.getCreditCustPaymentDetailsForSingleDate(fDate);

		return supList;
	}

	public List getSupplierPaymentByTwoDate(HttpServletRequest request,
			HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,SupplierPaymentDetail> map = new HashMap<Long,SupplierPaymentDetail>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<SupplierPaymentDetail> sup1List = dao.getSupplierDetailsDateWise(fDate,tDate);

		return sup1List;
	}

	public List getSupplierPaymentDetailsByBillNumber(
			HttpServletRequest request, HttpServletResponse response) {

		String billNo = request.getParameter("billNo");
		String fkSupplierId = request.getParameter("fkSupplierId");

		Map<Long,SupplierPaymentDetail> map = new HashMap<Long,SupplierPaymentDetail>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<SupplierPaymentDetail> supList = dao.getCreditCustPaymentDetailsAsBill(billNo,fkSupplierId);

		return supList;
	}

	public List getSupplierPaymentDetailsByNames(HttpServletRequest request,
			HttpServletResponse response) {
		String fkSupplierId = request.getParameter("fkSupplierId");

		Map<Long,SupplierPaymentDetail> map = new HashMap<Long,SupplierPaymentDetail>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<SupplierPaymentDetail> supList = dao.getCreditCustPaymentDetailsAsBill(fkSupplierId);

		return supList;
	}
	
	
	public List getCustLoanPaymentDetailsAsPerName(HttpServletRequest request,
			HttpServletResponse response) {
		String custname = request.getParameter("custname");

		Map<Long,CreditLoanBean> map = new HashMap<Long,CreditLoanBean>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<CreditLoanBean> supList = dao.getCustLoanPaymentDetailsAsPerName(custname);

		return supList;
	}
	
	public List CustWiseLoanPaymentReport(HttpServletRequest request,
			HttpServletResponse response) {
		String custname = request.getParameter("custname");

		Map<Long,CreditLoanPaymentReportBean> map = new HashMap<Long,CreditLoanPaymentReportBean>();

		SupplierPaymentDao dao = new SupplierPaymentDao();
		List<CreditLoanPaymentReportBean> supList = dao.getCustomerNameWiseLoanPaymentReport(custname);

		return supList;
	}
}
