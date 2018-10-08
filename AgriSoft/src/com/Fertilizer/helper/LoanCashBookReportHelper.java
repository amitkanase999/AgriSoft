package com.Fertilizer.helper;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.CreditLoanPaymentReportBean;
import com.Fertilizer.bean.LoanCashBookReportBean;
import com.Fertilizer.dao.LoanCashBookReportDao;

public class LoanCashBookReportHelper {
	
	public List CustWiseLoanPaymentReport(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		String custname = request.getParameter("custname");
		String cloaserdate = request.getParameter("cloaserdate");
		String interestrate = request.getParameter("interestrate");
		Map<Long,CreditLoanPaymentReportBean> map = new HashMap<Long,CreditLoanPaymentReportBean>();

		//SupplierPaymentDao dao = new SupplierPaymentDao();
		LoanCashBookReportDao dao=new LoanCashBookReportDao();
		List<LoanCashBookReportBean> supList = dao.getCustomerNameWiseLoanReport(custname,cloaserdate,interestrate);

		return supList;
	}

}
