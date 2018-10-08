package com.Fertilizer.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.GetSupplierDetails;
import com.Fertilizer.hibernate.CashBankBookDataDateWise;
import com.Fertilizer.hibernate.SupplierAccountDetailsBean;
import com.Fertilizer.hibernate.SupplierDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class SupplierAccountDetailsDao {

	public void supplierAccount(SupplierAccountDetailsBean sadb) {

		System.out.println("In DAO");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			System.out.println("Tx started");

			Long fkSupplierId = sadb.getFk_supplier_id();
			SupplierDetailsBean detail = (SupplierDetailsBean) session.load(SupplierDetailsBean.class, fkSupplierId);
			System.out.println(detail);
			sadb.setSupplierDetailsBean(detail);
			session.save(sadb);
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

	public List getAllBillBySuppliers(String supplierId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select s.bill_number,s.insertDate from goods_receive s where s.fk_supplier_id="+supplierId);
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

	public List getTotalAmtByBillNoForCustomer(String supplierId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(sg.gross_total),sg.billno FROM supplier_gross sg where fk_supplier_id=:supplierId");
			query.setParameter("supplierId",supplierId);
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

	public List getbalanceAmtByBillNo(String supplier) {
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT balance , sum(balance), total_amount from supplier_payment WHERE fk_supplier_id ='"+supplier+"'  group by pk_supplier_payment_id DESC LIMIT 1");
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

	public List checkSupplierPayment(String supplier) {
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT balance , total_amount from supplier_payment WHERE fk_supplier_id ='"+supplier+"' ORDER BY pk_supplier_payment_id DESC LIMIT 1");
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

	public Double getTotalAmt(String supplier) {

		HibernateUtility hbu = null ;
		Session session = null;
		List<Object[]> list  = null;
		Double totalAmt = null;
		List<GetSupplierDetails> itemlist = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select SUM(s.gross_total),s.billno from supplier_gross s where s.fk_supplier_id=:supplier");
			query.setParameter("supplier",supplier);
			list = query.list();
			itemlist = new ArrayList<GetSupplierDetails>(0);

			for (Object[] objects : list) {

				GetSupplierDetails bean = new GetSupplierDetails();

				String newBal = (objects[0].toString());
				totalAmt = Double.valueOf(newBal);
				itemlist.add(bean);
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
		return totalAmt;
	}

	public List getYesterdarTotalAmount() {
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT onDate , totalAmount FROM cashbankbooktable ORDER BY pkLastCashId DESC LIMIT 1");
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

	public List getYesterdaySuppCredit(String yesterday) {
		return null;
	}

	public void registeryesterdayTotal(CashBankBookDataDateWise cs) {
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			System.out.println("Tx started");

			session.save(cs);
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

	public List getDiffBetDates() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		String todayDate = dateFormat1.format(dateobj);

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("SELECT date , totalAmount FROM CashBankBookDataDateWise ORDER BY date DESC LIMIT 1");
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

	public List getTodaySaleTotalAmount() {
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String date = (dateFormat1.format(date1));

		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select DISTINCT COUNT(DISTINCT bill_no), sum(DISTINCT  gross_total) from fertilizer_billing where insert_date=:date");
			query.setParameter("date",date);
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

	public List getTodaySaleTotalAmount1() {
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String date = (dateFormat1.format(date1));

		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select DISTINCT COUNT(DISTINCT bill_no), sum(DISTINCT  gross_total) from seed_pesticide_billing where insert_date=:date");
			query.setParameter("date",date);
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
