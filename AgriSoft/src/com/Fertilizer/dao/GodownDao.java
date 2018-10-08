package com.Fertilizer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.utility.HibernateUtility;

public class GodownDao {

	// Fetch fertilizer product Against  Godown
	public List getAllFertilizerProductAgainstGodown(String godownName) {

		System.out.println("godownName in Dao ==="+godownName);
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("category === == ="+godownName);
			Query query = session.createSQLQuery("select product_name,company_name,packing,quantity from product_stock_details where  godown_name='"+godownName+"' AND product_category='fertilizer'");

			list = query.list();

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
	
/*	// Fetch fertilizer product Against  Godown
	public List getAllFertilizerProductAgainstDefaultGodown(String godownName) {

		System.out.println("godownName in Dao ==="+godownName);
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("category === == ="+godownName);
			Query query = session.createSQLQuery("select product_name,company_name,packing,quantity from product_stock_details where  godown_name='"+godownName+"' AND product_category='fertilizer'");

			list = query.list();

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
	}*/
	
	// Fetch Seed product Against  Godown
		public List getAllSeedProductAgainstGodown(String godownName) {

			System.out.println("godownName in Dao ==="+godownName);
			HibernateUtility hbu = null ;
			Session session = null;
			List list  = null;
			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				System.out.println("category === == ="+godownName);
				Query query = session.createSQLQuery("select product_name,batchno,quantity,expiry_date,packing,unit  from product_stock_details where  godown_name='"+godownName+"' AND product_category='Seed'");

				list = query.list();

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
	
		// Fetch Pesticide product Against  Godown
		public List getAllPesticideProductAgainstGodown(String godownName) {

			System.out.println("godownName in Dao ==="+godownName);
			HibernateUtility hbu = null ;
			Session session = null;
			List list  = null;
			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				System.out.println("category === == ="+godownName);
				Query query = session.createSQLQuery("select product_name,batchno,quantity,expiry_date,packing,unit from product_stock_details where  godown_name='"+godownName+"' AND product_category='pesticide'");

				list = query.list();

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
