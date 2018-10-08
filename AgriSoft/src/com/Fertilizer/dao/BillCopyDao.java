package com.Fertilizer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.utility.HibernateUtility;

public class BillCopyDao {
	
	public List getAllBillNoForFertilizerBilling(String ptype,String custtype) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("ptype === == ="+ptype+"Custtype"+custtype);
			Query query = session.createSQLQuery("SELECT bill_no,customer_name from fertilizer_billing WHERE customer_type='"+custtype+"' AND PayCustType='"+ptype+"'");

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
	
	public List getAllBillNoForSeedBilling(String ptype,String custtype) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("ptype === == ="+ptype+"Custtype"+custtype);
			Query query = session.createSQLQuery("SELECT bill_no,customer_name from seed_pesticide_billing WHERE customer_type='"+custtype+"' AND PayCustType='"+ptype+"'");

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

	public List getAllBillNoForPesticideBilling(String ptype,String custtype) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			System.out.println("ptype === == ="+ptype+"Custtype"+custtype);
			Query query = session.createSQLQuery("SELECT bill_no,customer_name from pesticide_billing WHERE customer_type='"+custtype+"' AND PayCustType='"+ptype+"'");

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
