package com.Fertilizer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.WholesaleCustomerSaleReportBean;
import com.Fertilizer.dao.CreditCustomerReportDao;
import com.Fertilizer.dao.WholesaleCustomerSaleReportDao;

public class WholesaleCustomerSaleReportHelper {

	public List getWholesaleCustomerSaleReport(HttpServletRequest request, HttpServletResponse response) {

		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		
		System.out.println(StartDate + "StartDate in Helper");

		Map<Long, WholesaleCustomerSaleReportBean> map = new HashMap<Long, WholesaleCustomerSaleReportBean>();

		WholesaleCustomerSaleReportDao dao=new WholesaleCustomerSaleReportDao();
		//CreditCustomerReportDao dao=new CreditCustomerReportDao();
		//GoodsReceiveDao dao = new GoodsReceiveDao();
		List<WholesaleCustomerSaleReportBean> expList = dao.getWholesaleCustomerSaleReport(StartDate,EndDate);

		return expList;
	}
}
