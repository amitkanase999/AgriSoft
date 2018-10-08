package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.CustomerBean;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class TransferStockToGodownDao {

	// Fetch Product Name For Fertilizer from ProductStock Details for transfer Stock
	public List getAllProductName(String godown,String category)
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("select company_name,product_name,packing,unit,quantity from product_stock_details where  godown_name='"+godown+"' AND product_category='"+category+"'");
			list = query.list(); 
			System.out.println("List===="+list);
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllierBillNo", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
	
	// Fetch Product Name For Seed pesticide from ProductStock Details for transfer Stock
		public List getAllProductNameForSeedPesticide(String godown,String category)
		{
			
			HibernateUtility hbu = null;
			Session session =  null;
			Query query = null;
			List list = null;
			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				query = session.createSQLQuery("select company_name,product_name,batchno,expiry_date,packing,unit,quantity from product_stock_details where  godown_name='"+godown+"' AND product_category='"+category+"'");
				list = query.list(); 
				System.out.println("List===="+list);
			} catch (RuntimeException e) {
				Log.error("Error in getAllSupllierBillNo", e);
			}

			finally
			{
				if (session!=null) {
					hbu.closeSession(session);
				}
			}
			return list;
		}
		
		//Fetching Fertilizer Product In Grid
		public List getAllFertilizerProductDetailsInGrid(String godown,String category,String proName, String company, String weight, String batchNum,String availableQty) {

			
			String companyname=company.trim();
			String productname=proName.trim();
			String pweight=weight.trim();
			
			System.out.println("--------------"+proName);
			System.out.println(company);
			System.out.println(weight);

			
			
			HibernateUtility hbu = null;
			Session session =  null;
			Query query = null;
			List list = null;
			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				query = session.createSQLQuery("select supplier_name,company_name,product_category,product_name,packing,unit,quantity,batchno,expiry_date from product_stock_details  WHERE godown_name='"+godown+"' AND product_category='"+category+"' AND product_name ='"+productname+"' AND company_name ='"+companyname+"' AND packing ='"+pweight+"'");
				list = query.list(); 
				System.out.println("List===="+list);
			} catch (RuntimeException e) {
				Log.error("Error in getAllSupllierBillNo", e);
			}

			finally
			{
				if (session!=null) {
					hbu.closeSession(session);
				}
			}
			return list;
			
		}
		
		
		//Fetching Seed Pesticider Product In Grid
				public List getAllSeedPesticideProductDetailsInGrid(String godown,String category,String proName, String company, String weight, String batchNum,String unit,String availableQty) {

					
					String companyname=company.trim();
					String productname=proName.trim();
					String pweight=weight.trim();
					String punit=unit.trim();
					String quantity=availableQty.trim();
					System.out.println("--------------"+proName);
					System.out.println(company);
					System.out.println(weight);

					
					
					HibernateUtility hbu = null;
					Session session =  null;
					Query query = null;
					List list = null;
					try {
						hbu = HibernateUtility.getInstance();
						session = hbu.getHibernateSession();
						query = session.createSQLQuery("select supplier_name,company_name,product_category,product_name,packing,unit,quantity,batchno,expiry_date from product_stock_details  WHERE godown_name='"+godown+"' AND product_category='"+category+"' AND product_name ='"+productname+"' AND company_name ='"+companyname+"' AND packing ='"+pweight+"' AND unit='"+punit+"' AND quantity='"+quantity+"' ");
						list = query.list(); 
						System.out.println("List===="+list);
					} catch (RuntimeException e) {
						Log.error("Error in getAllSupllierBillNo", e);
					}

					finally
					{
						if (session!=null) {
							hbu.closeSession(session);
						}
					}
					return list;
					
				}
				
				
	// Transfer Stock  Godown  to Godown
	public void transferStockToGodown(ProductStockDetailsBean bean) {

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

		
}
