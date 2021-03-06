package com.Fertilizer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.utility.HibernateUtility;

public class SaleReportDao {

	public List getAllBillNoCategoryWise(String category) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("cattable === == ="+category);
			
			if(category.equals("Fertilizer"))
			{
				Query query = session.createSQLQuery("SELECT bill_no,customer_name from fertilizer_billing");
				list = query.list();
			}
			
			if(category.equals("Seed"))
			{
				Query query = session.createSQLQuery("SELECT bill_no,customer_name from seed_pesticide_billing");
				list = query.list();
			}
			
			if(category.equals("Pesticide"))
			{
				Query query = session.createSQLQuery("SELECT bill_no,customer_name from pesticide_billing");
				list = query.list();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
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
