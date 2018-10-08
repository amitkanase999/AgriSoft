package com.Fertilizer.quartz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.utility.HibernateUtility;

public class DataToMail {

	public Double getTotalSaleAmountOfTheDay(){
		Double total = null ;
		HibernateUtility	hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select SUM(gross_total)  , bill_no from fertilizer_billing");

		List<Object[]> list = query.list();
		List<SaleReports > reports = new ArrayList<SaleReports>(0);
		for (Object[] objects : list) {

			total = Double.parseDouble(objects[0].toString());

		}
		return total;

	}

	public Double getTotalSaleAmountOfPesticide(){
		Double total = null ;
		HibernateUtility	hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select SUM(gross_total)  , bill_no from pesticide_billing");

		List<Object[]> list = query.list();
		List<SaleReports > reports = new ArrayList<SaleReports>(0);
		for (Object[] objects : list) {

			total = Double.parseDouble(objects[0].toString());

		}
		return total;

	}

	public Double getTotalSaleAmountOfSeed(){
		Double total = null ;
		HibernateUtility	hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select SUM(gross_total)  , bill_no from seed_pesticide_billing");

		List<Object[]> list = query.list();
		List<SaleReports > reports = new ArrayList<SaleReports>(0);
		for (Object[] objects : list) {

			total = Double.parseDouble(objects[0].toString());
		}
		return total;
	}
}
