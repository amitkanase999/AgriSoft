package com.Fertilizer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.GSTPurchaseReportBean;
import com.Fertilizer.bean.GSTSaleReportBean;
import com.Fertilizer.bean.SaleGstPercentageWiseBean;
import com.Fertilizer.dao.GSTReportDao;

public class GSTReportHelper {
	
	//Sale GST report for Regular Customer
	public List getGSTSaleReportRangeWise(HttpServletRequest request, HttpServletResponse response) {

		String startdate = request.getParameter("startdate");
		String endDate = request.getParameter("endDate");
		System.out.println(startdate + "start Date in Helper");
		System.out.println(endDate + "End Date in Helper");
		Map<Double, GSTSaleReportBean> map = new HashMap<Double, GSTSaleReportBean>();

		GSTReportDao dao=new GSTReportDao();
		List<Object> expList = dao.getGSTSaleReportRangeWise1(startdate,endDate);
		return expList;
	}
	

	//Sale GST report for Percentage Wise
		public List getPercentageInRangeWiseSaleGstReport(HttpServletRequest request, HttpServletResponse response) {

			String startdate = request.getParameter("startdate");
			String endDate = request.getParameter("endDate");
			System.out.println(startdate + "start Date in Helper");
			System.out.println(endDate + "End Date in Helper");
			Map<Double, GSTSaleReportBean> map = new HashMap<Double, GSTSaleReportBean>();

			GSTReportDao dao=new GSTReportDao();
			List<SaleGstPercentageWiseBean> expList = dao.getGSTSalePercentageAndRangeWiseReport(startdate,endDate);
			return expList;
		}
		
		//Sale GST report for Regular Customer
		public List getGSTPurchaseReportRangeWise(HttpServletRequest request, HttpServletResponse response) {

			String startdate = request.getParameter("startdate");
			String endDate = request.getParameter("endDate");
			System.out.println(startdate + "start Date in Helper");
			System.out.println(endDate + "End Date in Helper");
			Map<Double, GSTPurchaseReportBean> map = new HashMap<Double, GSTPurchaseReportBean>();

			GSTReportDao dao=new GSTReportDao();
			List<Object> expList = dao.getGSTPurchaseReportRangeWise(startdate,endDate);
			return expList;
		}

}
