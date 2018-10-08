package com.Fertilizer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.hibernate.CategoryDetailsBean;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductDetailsBean;
import com.Fertilizer.hibernate.SupplierDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class UpdateGoodReceiveDao {

	//Get all Challan no from Good Receive
	public List getChallanNo(String fk_supplier_id)
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("select bill_number,gross_total from goods_receive where fk_supplier_id='"+fk_supplier_id+"' AND billType='challan'GROUP BY bill_number");
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
	
	//get all product from goodRecive for Grid
	public List getProductDetailsFoGridInUpdateGoodsReceive(String challanNo) {
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;

		try{
			hbu=HibernateUtility.getInstance();
			session=hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT fkCategoryId,product_name,company_Name,hsn,buy_price,mrp,sale_price,weight,quantity,total_amount,batch_no,expiry_date FROM goods_receive WHERE bill_number='"+challanNo+"'");
			
			list = query.list();

			System.out.println(list.size()+"*************");
		}catch(RuntimeException e){

			Log.error("Error in getProductDetails",e);
		}finally{
			if(session!=null){

				hbu.closeSession(session);
			}
		}
		return list;
	}

	//get All Entry  in good receive For Update
	public List getAllEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from GoodsReceiveBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllLoanDetails", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	
}
