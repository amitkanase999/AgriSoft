package com.Fertilizer.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class ProductStockDetailsDao {
	
	public void addProductInProductStockDetails(ProductStockDetailsBean bean){
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
		hbu.closeSession(session);
	}
	
	public List getAllEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from ProductStockDetailsBean");
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


	public void registerStock(ProductStockDetailsBean psbean) {

		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			psbean.setUpdateDate(date);

			session.save(psbean);
			transaction.commit();
		}

		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(Exception rbe)
			{
				rbe.printStackTrace();
			}	
		}finally{
			hbu.closeSession(session);
		}
	}

	
	
}
