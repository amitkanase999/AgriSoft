package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.PurchaseDeatilsResultBean;
import com.Fertilizer.bean.PurchaseDetailsFromGoodsReceive;
import com.Fertilizer.hibernate.CustomerDetailsBean;
import com.Fertilizer.hibernate.PurchaseReturnBean;
import com.Fertilizer.utility.HibernateUtility;

public class PurchaseRetunDao {

	public void insertPurchaseRetun(PurchaseReturnBean bean)
	{
		System.out.println("In DAO");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("got session ");
			transaction = session.beginTransaction();

			System.out.println("Tx started");
			session.save(bean);
			transaction.commit();
			System.out.println("Successful");
		}
		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}finally{
			hbu.closeSession(session);
		}
	}

	public List getAllSupplier()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from PurchaseReturnBean");	
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllier", e);
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}	

	public List<com.Fertilizer.bean.PurchaseReturnBean> getPurchaseDetailsForSupplier(String supplier) {

		HibernateUtility hbu=null;
		Session session=null;
		List<com.Fertilizer.bean.PurchaseReturnBean> purchaseeturnList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select supplier_name,bill_no,product_name,category,batch_no,packing,buy_price,sale_price,MRP,tax_per,quantity,return_quantity,dc_no,barcode_no,purchase_date,return_date from purchase_return where supplier_name =: " + supplier);
			List<Object[]> list = query.list();
			purchaseeturnList = new ArrayList<com.Fertilizer.bean.PurchaseReturnBean>(0);

			for (Object[] object : list) {

				com.Fertilizer.bean.PurchaseReturnBean reports = new com.Fertilizer.bean.PurchaseReturnBean();

				reports.setSupplier_name(object[0].toString());
				reports.setBill_no(Long.parseLong(object[1].toString()));
				reports.setProduct_name(object[2].toString());
				reports.setCategory(object[3].toString());
				reports.setBatch_no(Long.parseLong(object[4].toString()));
				reports.setPacking(Long.parseLong(object[5].toString()));
				reports.setBuy_price(Double.parseDouble(object[6].toString()));
				reports.setSale_price(Double.parseDouble(object[7].toString()));
				reports.setMRP(Double.parseDouble(object[8].toString()));
				reports.setTax_per(Double.parseDouble(object[9].toString()));
				reports.setQuantity(Long.parseLong(object[10].toString()));
				reports.setRetun_quantity(Long.parseLong(object[11].toString()));
				reports.setDc_no(object[12].toString());
				reports.setBarcode_no(Long.parseLong(object[13].toString()));
				reports.setPurchase_date((Date)object[14]);
				reports.setReturn_date((Date)object[15]);

				purchaseeturnList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseeturnList;	
	}

	public List<com.Fertilizer.bean.PurchaseReturnBean> getPurchaseReportsBetweenTwoDates(String fDate, String tDate) {

		System.out.println(fDate+"first Date In dao");
		System.out.println(tDate+"Second Date In dao");
		HibernateUtility hbu=null;
		Session session=null;
		com.Fertilizer.bean.PurchaseReturnBean bean = new com.Fertilizer.bean.PurchaseReturnBean();
		List<com.Fertilizer.bean.PurchaseReturnBean> purchaseeturnList=null;
		double totalAmt = 0.0;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select supplier_name,bill_no,product_name,category,batch_no,packing,buy_price,sale_price,MRP,tax_per,quantity,return_quantity,dc_no,barcode_no,purchase_date,return_date from purchase_return where purchase_date between '" + fDate +"' and '"+tDate+"'");

			List<Object[]> list = query2.list();
			purchaseeturnList= new ArrayList<com.Fertilizer.bean.PurchaseReturnBean>(0);

			for (Object[] object : list) {

				com.Fertilizer.bean.PurchaseReturnBean reports = new com.Fertilizer.bean.PurchaseReturnBean();

				reports.setSupplier_name(object[0].toString());
				reports.setBill_no(Long.parseLong(object[1].toString()));
				reports.setProduct_name(object[2].toString());
				reports.setCategory(object[3].toString());
				reports.setBatch_no(Long.parseLong(object[4].toString()));
				reports.setPacking(Long.parseLong(object[5].toString()));
				reports.setBuy_price(Double.parseDouble(object[6].toString()));
				reports.setSale_price(Double.parseDouble(object[7].toString()));
				reports.setMRP(Double.parseDouble(object[8].toString()));
				reports.setTax_per(Double.parseDouble(object[9].toString()));
				reports.setQuantity(Long.parseLong(object[10].toString()));
				reports.setRetun_quantity(Long.parseLong(object[11].toString()));
				reports.setDc_no(object[12].toString());
				reports.setBarcode_no(Long.parseLong(object[13].toString()));
				reports.setPurchase_date((Date)object[14]);
				reports.setReturn_date((Date)object[15]);

				purchaseeturnList.add(reports); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return purchaseeturnList;
	}
}
