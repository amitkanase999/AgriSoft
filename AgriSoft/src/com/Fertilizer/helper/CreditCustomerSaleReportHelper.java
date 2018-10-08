package com.Fertilizer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.CreditCustomerSaleReportBean;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.dao.CreditCustomerReportDao;

public class CreditCustomerSaleReportHelper {

	
	public List getCreditCustomerSaleReport(HttpServletRequest request, HttpServletResponse response) {

		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		
		System.out.println(StartDate + "StartDate in Helper");

		Map<Long, CreditCustomerSaleReportBean> map = new HashMap<Long, CreditCustomerSaleReportBean>();

		CreditCustomerReportDao dao=new CreditCustomerReportDao();
		//GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CreditCustomerSaleReportBean> expList = dao.getCreditCustomerSaleReport(StartDate,EndDate);

		return expList;
	}
	
	public List getCreditCustomerSaleBalanceReport(HttpServletRequest request, HttpServletResponse response) {

		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		
		System.out.println(StartDate + "StartDate in Helper");

		Map<Long, CreditCustomerSaleReportBean> map = new HashMap<Long, CreditCustomerSaleReportBean>();

		CreditCustomerReportDao dao=new CreditCustomerReportDao();
		//GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CreditCustomerSaleReportBean> expList = dao.getCreditCustomerSaleBalanceReport(StartDate,EndDate);

		return expList;
	}
	
	public List getCreditCustomerSaleNameAndRangeWiseReport(HttpServletRequest request, HttpServletResponse response) {

		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		String Customername = request.getParameter("Customername");
		
		System.out.println(StartDate + "StartDate in Helper");

		Map<Long, CreditCustomerSaleReportBean> map = new HashMap<Long, CreditCustomerSaleReportBean>();

		CreditCustomerReportDao dao=new CreditCustomerReportDao();
		//GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CreditCustomerSaleReportBean> expList = dao.getCreditCustomerSaleNameAndRangeReport(StartDate,EndDate,Customername);

		return expList;
	}
}
