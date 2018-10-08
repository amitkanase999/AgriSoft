package com.Fertilizer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.GodownWiseStockReportDao;
import com.Fertilizer.hibernate.ProductStockDetailsBean;

public class GodownWiseStockReportHelper {

	public List getStockGodownWise(HttpServletRequest request,
			HttpServletResponse response) {

		String godownName = request.getParameter("godownName");
		System.out.println(godownName + "godownName in Helper");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		
		GodownWiseStockReportDao dao=new GodownWiseStockReportDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao.getGodownWiseStock(godownName);

		System.out.println("@@@ stock report Helper :: " + stockList);

		return stockList;

	}
}
