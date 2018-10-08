package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.util.Log;

import com.Fertilizer.bean.ShopeSaleGraph;
import com.Fertilizer.bean.WeekSaleBean;
import com.Fertilizer.utility.HibernateUtility;

public class DailySaleDao {

	public List getDay()
	{
		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(gross_total),insert_date FROM (SELECT gross_total,insert_date FROM fertilizer_billing group by insert_date,bill_no)A");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setPrice(Double.parseDouble(object[0].toString()));
				weekBean.setWeekDays(object[1].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getDay",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getWeek()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  WEEK(insert_date) week, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(7-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM fertilizer_billing GROUP BY YEARWEEK(insert_date)");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getWeek",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getMonth()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  MONTH(insert_date) month, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(31-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM fertilizer_billing GROUP BY MONTH");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getMonth",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	//for SEED

	public List getDayForSeed()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(gross_total),insert_date FROM (SELECT gross_total,insert_date FROM seed_pesticide_billing group by insert_date,bill_no)A");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setPrice(Double.parseDouble(object[0].toString()));
				weekBean.setWeekDays(object[1].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getDay",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getWeekForSeed()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  WEEK(insert_date) week, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(7-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM seed_pesticide_billing GROUP BY YEARWEEK(insert_date)");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getWeek",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getMonthForSeed()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  MONTH(insert_date) month, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(31-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM seed_pesticide_billing GROUP BY MONTH");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getMonth",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	//pesticide
	public List getDayForPesti()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(gross_total),insert_date FROM (SELECT gross_total,insert_date FROM pesticide_billing group by insert_date,bill_no)A");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setPrice(Double.parseDouble(object[0].toString()));
				weekBean.setWeekDays(object[1].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getDay",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getWeekForPesti()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  WEEK(insert_date) week, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(7-DAYOFWEEK(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM pesticide_billing GROUP BY YEARWEEK(insert_date)");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getWeek",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

	public List getMonthForPesti()
	{

		HibernateUtility hbu=null;
		Session session=null;
		List<WeekSaleBean> weekSaleBeans=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT  MONTH(insert_date) month, sum(gross_total) as Price, CONCAT(DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(1-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e'), ' TO ', DATE_FORMAT(DATE_ADD(insert_date, INTERVAL(31-DAYOFMONTH(insert_date)) DAY),'%Y-%m-%e')) AS DateRange FROM pesticide_billing GROUP BY MONTH");
			List<Object[]>  list=query.list();
			weekSaleBeans=new ArrayList<WeekSaleBean>(0);

			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				WeekSaleBean weekBean=new WeekSaleBean();
				weekBean.setWeek(object[0].toString());
				weekBean.setPrice(Double.parseDouble(object[1].toString()));
				weekBean.setWeekDays(object[2].toString());
				weekSaleBeans.add(weekBean);
			}}catch(RuntimeException e){

				Log.error("Error in getMonth",e);
			}
		finally{
			hbu.closeSession(session);	
		}

		return weekSaleBeans;
	}

}
