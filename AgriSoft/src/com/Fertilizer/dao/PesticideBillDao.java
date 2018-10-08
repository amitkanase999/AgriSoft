package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.CustomerBillBean;
import com.Fertilizer.hibernate.FertilizerBillingCollectionBean;
import com.Fertilizer.hibernate.PesticideBillBean;
import com.Fertilizer.utility.HibernateUtility;

public class PesticideBillDao {

	//to get latest bill number
	public List getPesticideCustomerBill() {

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no , customer_name FROM pesticide_billing ORDER BY bill_no DESC LIMIT 1");

			List<Object[]> list = query.list();
			saleList= new ArrayList<CustomerBillBean>(0);
			for (Object[] object : list) {
				CustomerBillBean reports = new CustomerBillBean();
				reports.setBillNo(Long.parseLong(object[0].toString()));
				saleList.add(reports);	 
			}}
		catch(RuntimeException e)
		{
			Log.error("Error in getCustomerBill()", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;	
	}

	public void addPesticideBillingInDAO(PesticideBillBean bean) {
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

	//Adding Entry to Billing_Collection
	public void addPesticideBillingInDAOBillingCollection(FertilizerBillingCollectionBean pbean) {
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
			session.save(pbean);
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
	
	
	
	// get All bill No to sale return
	public List getAllPestiBillNoForSaleReturn() {
		HibernateUtility hbu = null;
		Session session = null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from PesticideBillBean group by billNo");
			list = query.list();
		} catch (RuntimeException e) {
			Log.error("Error in getAllPestiBillNoForSaleReturn", e);
		}
		finally {
			if (session != null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	public List getAllPestiBilingDetailByBillNo(String bill_no) {
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;

		try{
			hbu=HibernateUtility.getInstance();
			session=hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT p.pk_pesticide_bill_id,p.cat_id,p.customer_name,p.product_name,p.company,p.weight,p.batch_number,p.sale_price,p.mrp,p.quantity_after_return,p.total_per_product,p.barcode,p.aadhar,p.insert_date,p.credit_customer_name,p.tax_percentage from pesticide_billing p WHERE p.bill_no=:bill_no");

			query.setParameter("bill_no", bill_no);

			list = query.list();

			System.out.println(list.size()+"===List size");
		}catch(RuntimeException e){

			Log.error("Error in getProductDetails",e);
		}finally{
			if(session!=null){

				hbu.closeSession(session);
			}
		}
		return list;
	}
	
	
	public List getAllPesticideBillEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from PesticideBillBean");  
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllSEEDBillDetails", e);
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

	

