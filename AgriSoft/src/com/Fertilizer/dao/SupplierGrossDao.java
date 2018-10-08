package com.Fertilizer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.hibernate.SupplierGrossBean;
import com.Fertilizer.utility.HibernateUtility;

public class SupplierGrossDao {

	public void addGrossInSupplierGross(SupplierGrossBean sgb) {

		System.out.println("In gr DAo");
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();

		System.out.println("Tx started");

		session.save(sgb);
		transaction.commit();
		System.out.println("Successful");
		hbu.closeSession(session);
	}
	
}
