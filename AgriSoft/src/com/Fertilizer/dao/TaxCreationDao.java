package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.GetTaxDetails;
import com.Fertilizer.hibernate.TaxCreationBean;
import com.Fertilizer.utility.HibernateUtility;

public class TaxCreationDao {

	public void valTaxCreation(TaxCreationBean tcb) {

		System.out.println("In DAO");

		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("got session ");
			transaction = session.beginTransaction();

			System.out.println("Tx started");
			session.save(tcb);
			transaction.commit();
			System.out.println("Successful");
		}

		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Error in tax creation",rbe);
			}	
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
	}

	public List getAllMainTax()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from TaxCreationBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllMainTax", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	//Tax details for Tax list
	
	public List getTaxDetails(){

		HibernateUtility hbu=null;
		Session session=null;
		List<GetTaxDetails> taxList=null;
		try{	

			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT tax_name, percentage from tax_details");
			List<Object[]> list = query.list();
			taxList= new ArrayList<GetTaxDetails>(0);
			for (Object[] o : list) {	
				GetTaxDetails p = new GetTaxDetails();
				p.setTaxType(o[0].toString());
				p.setTaxPercentage(Double.parseDouble(o[1].toString()));

				taxList.add(p);

			}}catch(RuntimeException e){	
			}
		finally{
			hbu.closeSession(session);	
		}
		return taxList;
	}
	
	// get All Tax Names
	
	public List getAllTax()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from TaxCreationBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllTax", e);
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
