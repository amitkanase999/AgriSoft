package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.GSTPurchaseReportBean;
import com.Fertilizer.bean.GSTSaleReportBean;
import com.Fertilizer.bean.SaleGstPercentageWiseBean;
import com.Fertilizer.utility.HibernateUtility;

public class GSTReportDao {

	public List<Object> getGSTSaleReportRangeWise1(String startdate,String endDate) {

		System.out.println("Start date +++++"+startdate);
		System.out.println("End date +++++"+endDate);
		Double ferti5total=0.0d;
		Double pestti5total=0.0d;
		Double sum=0.0d;
		Double trans;
		Double hamali;
		HibernateUtility hbu=null;
		Session session=null;
		List mixedList = null;
		List<GSTSaleReportBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("select tax_percentage, sum(total_per_product),customer_type from (  \r\n" + 
					" select tax_percentage,total_per_product,customer_type from pesticide_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Regular' union all \r\n" + 
					" select tax_percentage,total_per_product,customer_type from seed_pesticide_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Regular' union all\r\n" + 
					" select tax_percentage,total_per_product,customer_type from fertilizer_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Regular') x  group by tax_percentage ORDER BY tax_percentage ASC");
			List<Object[]> list = query.list();
			saleList= new ArrayList<GSTSaleReportBean>(0);	
			Double totalTaxPercentage=0.0,totalTaxableValue=0.0,totalGst=0.0,totalCsgst=0.0,totaloftaxamout=0.0;
			
			
			for (Object[] object : list) {
				Double taxpercentage=Double.parseDouble(object[0].toString());
				Double taxablevalue=Double.parseDouble(object[1].toString());	
				Double gst=(taxablevalue*taxpercentage)/100;
				Double csgst=gst/2;
				
				totalTaxableValue=totalTaxableValue+taxablevalue;
				totalGst=totalGst+csgst;
				totalCsgst=totalCsgst+csgst;				
				totaloftaxamout=totaloftaxamout+gst;
				
				GSTSaleReportBean reports = new GSTSaleReportBean();
				reports.setCustomerType(object[2].toString());
				reports.setSalesTaxable(Math.round(taxpercentage*100.0)/100.0);
				reports.setTaxableValue(Math.round(taxablevalue*100.0)/100.0);
				reports.setCgst(Math.round(csgst*100.0)/100.0);
				reports.setSgst(Math.round(csgst*100.0)/100.0);
				reports.setTotalTaxAmount(gst);		
				saleList.add(reports);
			}
			List<GSTSaleReportBean> totalSaleList= new ArrayList<GSTSaleReportBean>();
			GSTSaleReportBean totalReports = new GSTSaleReportBean();
			totalReports.setCustomerType("RTotal");
			totalReports.setSalesTaxable(0.0);
			totalReports.setTaxableValue(Math.round(totalTaxableValue*100.0)/100.0);
			totalReports.setCgst(Math.round(totalGst*100.0)/100.0);
			totalReports.setSgst(Math.round(totalCsgst*100.0)/100.0);
			totalReports.setTotalTaxAmount(Math.round(totaloftaxamout*100.0)/100.0);
			totalSaleList.add(totalReports);
			
			
			
			
			
			List<GSTSaleReportBean> saleList1=null;
			Query query1 = session.createSQLQuery("select tax_percentage, sum(total_per_product),customer_type from (  \r\n" + 
					" select tax_percentage,total_per_product,customer_type from pesticide_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Wholesale' union all \r\n" + 
					" select tax_percentage,total_per_product,customer_type from seed_pesticide_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Wholesale' union all\r\n" + 
					" select tax_percentage,total_per_product,customer_type from fertilizer_billing WHERE (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')AND customer_type='Wholesale') x  group by tax_percentage ORDER BY tax_percentage ASC");
			List<Object[]> list1 = query1.list();
			saleList1= new ArrayList<GSTSaleReportBean>(0);	
			Double totalTaxPercentage1=0.0,totalTaxableValue1=0.0,totalGst1=0.0,totalCsgst1=0.0,totaloftaxamout1=0.0;
			
			for (Object[] object : list1) {
				Double taxpercentage=Double.parseDouble(object[0].toString());
				Double taxablevalue=Double.parseDouble(object[1].toString());	
				Double gst=(taxablevalue*taxpercentage)/100;
				Double csgst=gst/2;
			
				totalTaxableValue1=totalTaxableValue1+taxablevalue;
				totalGst1=totalGst1+csgst;
				totalCsgst1=totalCsgst1+csgst;				
				totaloftaxamout1=totaloftaxamout1+gst;
				
				GSTSaleReportBean reports1 = new GSTSaleReportBean();
				reports1.setCustomerType(object[2].toString());
				reports1.setSalesTaxable(Math.round(taxpercentage*100.0)/100.0);
				reports1.setTaxableValue(Math.round(taxablevalue*100.0)/100.0);
				reports1.setCgst(Math.round(csgst*100.0)/100.0);
				reports1.setSgst(Math.round(csgst*100.0)/100.0);
				reports1.setTotalTaxAmount(Math.round(gst*100.0)/100.0);
			
				
				saleList1.add(reports1);
			}
			
			List<GSTSaleReportBean> totalSaleListWhosale= new ArrayList<GSTSaleReportBean>();
			GSTSaleReportBean totalreports2 = new GSTSaleReportBean();
			totalreports2.setCustomerType("WTotal");
			totalreports2.setSalesTaxable(0.0);
			totalreports2.setTaxableValue(Math.round(totalTaxableValue1*100.0)/100.0);
			totalreports2.setCgst(Math.round(totalGst1*100.0)/100.0);
			totalreports2.setSgst(Math.round(totalCsgst1*100.0)/100.0);
			totalreports2.setTotalTaxAmount(Math.round(totaloftaxamout1*100.0)/100.0);
			totalSaleListWhosale.add(totalreports2);
			
			
			/*Total of Total Regular And Wholesale Customer*/
			List<GSTSaleReportBean> totalRegularWhosale= new ArrayList<GSTSaleReportBean>();
			GSTSaleReportBean totalreports3 = new GSTSaleReportBean();
			totalreports3.setCustomerType("ZTotal");
			totalreports3.setSalesTaxable(0.0);
			totalreports3.setTaxableValue(Math.round((totalTaxableValue1+totalTaxableValue)*100.0)/100.0);
			totalreports3.setCgst(Math.round((totalGst1+totalGst)*100.0)/100.0);
			totalreports3.setSgst(Math.round((totalCsgst1+totalCsgst)*100.0)/100.0);
			totalreports3.setTotalTaxAmount(Math.round((totaloftaxamout1+totaloftaxamout)*100.0)/100.0);
			totalRegularWhosale.add(totalreports3);
			
			
			mixedList = new ArrayList<Object>(saleList1.size() + saleList.size()+totalSaleList.size()+totalSaleListWhosale.size());
			mixedList.addAll(saleList);
			mixedList.addAll(totalSaleList);
			mixedList.addAll(saleList1);
			mixedList.addAll(totalSaleListWhosale);
			mixedList.addAll(totalRegularWhosale);
			
			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return mixedList;	

	}
	
	
	
	public List<SaleGstPercentageWiseBean> getGSTSalePercentageAndRangeWiseReport(String startdate,String endDate) {

		System.out.println("Start date +++++"+startdate);
		System.out.println("End date +++++"+endDate);
		Double fivePercent=0.0d;
		Double twelvePercent=0.0d;
		Double eighteenPercent=0.0d;
		Double twentyEightPercent=0.0d;
		Double totalamount=0.0d;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleGstPercentageWiseBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("(SELECT \r\n" + 
					"  DISTINCT(p.customer_name) AS custname,wcd.gstno,\r\n" + 
					"  SUM(IF(p.tax_percentage='5',total_per_product,0)) AS gst5,\r\n" + 
					"  SUM(IF(p.tax_percentage='12',total_per_product,0)) AS GST12,\r\n" + 
					"	SUM(IF(p.tax_percentage='18',total_per_product,0)) AS GST18,\r\n" + 
					"  SUM(IF(p.tax_percentage='28',total_per_product,0)) AS GST28\r\n" + 
					"\r\n" + 
					"FROM\r\n" + 
					"  pesticide_billing AS p INNER JOIN wholesale_customer_details wcd ON p.customer_name=wcd.shopname WHERE  customer_type=\"wholesale\" AND (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')\r\n" + 
					"GROUP BY p.customer_name)\r\n" + 
					"UNION ALL\r\n" + 
					"(SELECT\r\n" + 
					"DISTINCT(f.customer_name) AS custname,wcd.gstno,\r\n" + 
					"  SUM(IF(f.tax_percentage='5',total_per_product,0)) AS gst5,\r\n" + 
					"  SUM(IF(f.tax_percentage='12',total_per_product,0)) AS GST12,\r\n" + 
					"	SUM(IF(f.tax_percentage='18',total_per_product,0)) AS GST18,\r\n" + 
					"  SUM(IF(f.tax_percentage='28',total_per_product,0)) AS GST28\r\n" + 
					" \r\n" + 
					"FROM\r\n" + 
					"  fertilizer_billing AS f INNER JOIN wholesale_customer_details wcd ON f.customer_name=wcd.shopname WHERE customer_type=\"wholesale\" AND (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')\r\n" + 
					"GROUP BY f.customer_name)\r\n" + 
					"UNION ALL\r\n" + 
					"(SELECT\r\n" + 
					"DISTINCT(s.customer_name) AS custname,wcd.gstno,\r\n" + 
					"  SUM(IF(s.tax_percentage='5',total_per_product,0)) AS gst5,\r\n" + 
					"  SUM(IF(s.tax_percentage='12',total_per_product,0)) AS GST12,\r\n" + 
					"	SUM(IF(s.tax_percentage='18',total_per_product,0)) AS GST18,\r\n" + 
					"  SUM(IF(s.tax_percentage='28',total_per_product,0)) AS GST28\r\n" + 
					"\r\n" + 
					"FROM\r\n" + 
					"  seed_pesticide_billing AS s  INNER JOIN wholesale_customer_details wcd ON s.customer_name=wcd.shopname WHERE customer_type=\"wholesale\" AND (insert_date BETWEEN '"+startdate+"' AND '"+endDate+"')\r\n" + 
					"GROUP BY s.customer_name)");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleGstPercentageWiseBean>(0);	
			for (Object[] object : list) {
				
				String custname=object[0].toString();
				String gstno=object[1].toString();
				fivePercent=Double.parseDouble(object[2].toString());
				twelvePercent=Double.parseDouble(object[3].toString());
				eighteenPercent=Double.parseDouble(object[4].toString());
				twentyEightPercent=Double.parseDouble(object[5].toString());
				totalamount=fivePercent+twelvePercent+eighteenPercent+twentyEightPercent;
				
				SaleGstPercentageWiseBean reports=new SaleGstPercentageWiseBean();
				reports.setCustname(custname);
				reports.setGstno(gstno);
				reports.setFivePercent(Math.round(fivePercent*100.0)/100.0);
				reports.setTwelvePercent(Math.round(twelvePercent*100.0)/100.0);
				reports.setEighteenPercent(Math.round(eighteenPercent*100.0)/100.0);
				reports.setTwentyEightPercent(Math.round(twentyEightPercent*100.0)/100.0);
				reports.setTotalamount(Math.round(totalamount*100.0)/100.0);
							
				
				saleList.add(reports);
			}
			

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	

	}

	//GST Purchase Report for range wise
	
	public List<Object> getGSTPurchaseReportRangeWise(String startdate,String endDate) {

		System.out.println("Start date +++++"+startdate);
		System.out.println("End date +++++"+endDate);
		HibernateUtility hbu=null;
		Session session=null;
		List<GSTPurchaseReportBean> saleList=null;
		List<GSTPurchaseReportBean> saleList1=null;
		List<Object> mixedList=null;
		Double tax_percentage, totalTaxableValue,gst,cgst;
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("(SELECT COALESCE(tax_percentage,5),COALESCE(SUM(total_amount),0) from goods_receive WHERE tax_percentage=5 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION ALL\r\n" + 
					"(SELECT COALESCE(tax_percentage,12),COALESCE(SUM(total_amount),0) from goods_receive WHERE tax_percentage=12 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION  ALL\r\n" + 
					"(SELECT COALESCE(tax_percentage,18),COALESCE(SUM(total_amount),0) from goods_receive WHERE tax_percentage=18 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION ALL\r\n" + 
					"(SELECT COALESCE(tax_percentage,28),COALESCE(SUM(total_amount),0) from goods_receive WHERE tax_percentage=28 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) ");
			List<Object[]> list = query.list();
			saleList= new ArrayList<GSTPurchaseReportBean>(0);	
			for (Object[] object : list) {
				
				//String custname=object[0].toString();
				String gstno=object[1].toString();
				tax_percentage=Double.parseDouble(object[0].toString());
				totalTaxableValue=Double.parseDouble(object[1].toString());
				gst=(totalTaxableValue*tax_percentage)/100;
				cgst=gst/2;
				
				GSTPurchaseReportBean reports=new GSTPurchaseReportBean();
				reports.setPurchaseTaxable(Math.round(tax_percentage*100.0)/100.0);
				reports.setTaxableValue(Math.round(totalTaxableValue*100.0)/100.0);
				reports.setCgstTotalValue(Math.round(cgst*100.0)/100.0);
				reports.setSgstTotalValue(Math.round(cgst*100.0)/100.0);
				reports.setTotalTaxAmount(Math.round(gst*100.0)/100.0);
				reports.setIgstTotalValue(0.0);
				
				
				saleList.add(reports);
			}
			
			
			Query query1 = session.createSQLQuery("(SELECT COALESCE(iGstPercentage,5),COALESCE(SUM(total_amount),0) from goods_receive WHERE iGstPercentage=5 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION ALL\r\n" + 
					"(SELECT COALESCE(iGstPercentage,12),COALESCE(SUM(total_amount),0) from goods_receive WHERE iGstPercentage=12 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION  ALL\r\n" + 
					"(SELECT COALESCE(iGstPercentage,18),COALESCE(SUM(total_amount),0) from goods_receive WHERE iGstPercentage=18 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) UNION ALL\r\n" + 
					"(SELECT COALESCE(iGstPercentage,28),COALESCE(SUM(total_amount),0) from goods_receive WHERE iGstPercentage=28 AND (purchaseDate BETWEEN '"+startdate+"' and '"+endDate+"')) ");
			List<Object[]> list1 = query1.list();
			saleList1= new ArrayList<GSTPurchaseReportBean>(0);	
			for (Object[] object : list1) {
				
				//String custname=object[0].toString();
				String gstno=object[1].toString();
				Double itax_percentage=Double.parseDouble(object[0].toString());
				Double itotalTaxableValue=Double.parseDouble(object[1].toString());
				Double igst=(itotalTaxableValue*itax_percentage)/100;
				
				GSTPurchaseReportBean reports=new GSTPurchaseReportBean();
				
				reports.setPurchaseTaxable(itax_percentage);
				reports.setTaxableValue(Math.round(itotalTaxableValue*100.0)/100.0);
				reports.setIgstTotalValue(Math.round(igst*100.0)/100.0);
				reports.setTotalTaxAmount(Math.round(igst*100.0)/100.0);
				reports.setCgstTotalValue(0.0);
				reports.setSgstTotalValue(0.0);
				
				saleList1.add(reports);
			}
		
			
			mixedList = new ArrayList<Object>(saleList1.size() + saleList.size());
			mixedList.addAll(saleList);
			mixedList.addAll(saleList1);
			
			//saleList.addAll(saleList1);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return mixedList;	

	}
	
}
