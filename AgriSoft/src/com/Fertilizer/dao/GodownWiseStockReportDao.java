package com.Fertilizer.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.ProductStockDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class GodownWiseStockReportDao {

	
	public List<ProductStockDetailsBean> getGodownWiseStock(String godownName) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			//String catageory = Integer.parseInt(cat);
			Query query = session.createSQLQuery("SELECT product_name,product_category,packing,batchno,expiry_date,quantity from product_stock_details where godown_name='"+godownName+"'");
			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);

			for (Object[] object : list) {

				
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				
				reports.setProduct_name(object[0].toString());
				reports.setProduct_category(object[1].toString());
				reports.setPacking(Double.parseDouble(object[2].toString()));
				reports.setBatchno(object[3].toString());
				reports.setExpiryDate((Date)object[4]);
				reports.setQuantity(Long.parseLong(object[5].toString()));
				
				stockList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		System.out.println("@@@@@@@@@@@ DAO Stock Report List :: "+stockList);
		return stockList;	
	}
	
}
