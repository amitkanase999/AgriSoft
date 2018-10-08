package com.Fertilizer.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.GetCreditCustomerDetails;
import com.Fertilizer.hibernate.CustomerDetailsBean;
import com.Fertilizer.hibernate.WholesaleCustomerDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class CustomerDetailsDao {

	public void valCustomerDetails(CustomerDetailsBean cdb){
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
			session.save(cdb);
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

	
	public void wholesaleCustomerDetails(WholesaleCustomerDetailsBean bean){	
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

	
	public List<CustomerDetailsBean> getAllCustomer()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List<CustomerDetailsBean> list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from CustomerDetailsBean");
			list = query.list(); 
		} catch (Exception e) {
			Log.error("Error in getAllCustomer", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	/*get All Wholesale Customer*/
	public List<CustomerDetailsBean> getAllWholesaleCustomer()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List<CustomerDetailsBean> list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from WholesaleCustomerDetailsBean");	
			list = query.list(); 
		} catch (Exception e) {
			Log.error("Error in getAllwholesaleCustomer", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	
	
	
	public List getVillageByCustomerName(String creditCustomerId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select c.address , c.first_name, c.contact_no, c.aadhar_no from customer_details c where c.pk_customer_id ="+creditCustomerId);
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

	public List getAllBillByCreditCustomer(String fkCustomerId, String catId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select f.bill_no, f.insert_date from fertilizer_billing f where f.fk_customer_id='"+fkCustomerId+"' AND f.cat_id ='"+catId+"' UNION select s.bill_no, s.insert_date from seed_pesticide_billing s where s.fk_customer_id='"+fkCustomerId+"' AND s.cat_id ='"+catId+"' UNION select p.bill_no, p.insert_date from pesticide_billing p where p.fk_customer_id='"+fkCustomerId+"' AND p.cat_id ="+catId);
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

	public List getTotalAmountByBill(String billNo, String catId, String creditCustomer) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select f.gross_total, f.insert_date from fertilizer_billing f where f.bill_no='"+billNo+"'AND f.fk_customer_id='"+creditCustomer+"' AND f.cat_id='"+catId+"' UNION select s.gross_total, s.insert_date from seed_pesticide_billing s where s.bill_no='"+billNo+"'AND s.fk_customer_id='"+creditCustomer+"' AND s.cat_id='"+catId+"' UNION select p.gross_total, p.insert_date from pesticide_billing p where p.bill_no='"+billNo+"'AND p.fk_customer_id='"+creditCustomer+"' AND p.cat_id="+catId);
			System.out.println("In dao get total");
			list = query.list();
			System.out.println("List size in DAO of total = == ="+list.size());
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

	public List getRemainingBalanceAmountByBill(String billNo, String creditCustomer,String catId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT balance ,bill_no from credit_customer_payment WHERE bill_no ='"+billNo+"' AND cat_id='"+catId+"' AND fk_customer_id='"+creditCustomer+"' ORDER BY  pk_credit_customer_id  DESC LIMIT 1 ;");
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

	public Double getTotalAmt(String billNo,String catId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List<Object[]> list  = null;
		Double totalAmount = null;
		Double cat=Double.parseDouble(catId);
		List<GetCreditCustomerDetails> itemlist = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			if(cat==1){
				Query query = session.createSQLQuery("select f.gross_total, f.insert_date from fertilizer_billing f where f.bill_no="+billNo);
				list = query.list();
				itemlist = new ArrayList<GetCreditCustomerDetails>(0);

				for (Object[] objects : list) {

					GetCreditCustomerDetails bean = new GetCreditCustomerDetails();

					String newBal = (objects[0].toString());
					totalAmount = Double.valueOf(newBal);

					itemlist.add(bean);
				}

			}
			if(cat==2){
				Query query = session.createSQLQuery("select f.gross_total, f.insert_date from pesticide_billing f where f.bill_no="+billNo);
				list = query.list();
				itemlist = new ArrayList<GetCreditCustomerDetails>(0);

				for (Object[] objects : list) {

					GetCreditCustomerDetails bean = new GetCreditCustomerDetails();

					String newBal = (objects[0].toString());
					totalAmount = Double.valueOf(newBal);

					itemlist.add(bean);
				}

			}
			if(cat==3){
				Query query = session.createSQLQuery("select f.gross_total, f.insert_date from seed_pesticide_billing f where f.bill_no="+billNo);
				list = query.list();
				itemlist = new ArrayList<GetCreditCustomerDetails>(0);

				for (Object[] objects : list) {

					GetCreditCustomerDetails bean = new GetCreditCustomerDetails();

					String newBal = (objects[0].toString());
					totalAmount = Double.valueOf(newBal);

					itemlist.add(bean);
				}

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
		return totalAmount;
	}

	public Double getTotalAmtForPesti(String billNo) {

		HibernateUtility hbu = null ;
		Session session = null;
		List<Object[]> list  = null;
		Double totalAmount = null;
		List<GetCreditCustomerDetails> itemlist = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select f.gross_total, f.insert_date from pesticide_billing f where f.bill_no="+billNo);
			list = query.list();
			itemlist = new ArrayList<GetCreditCustomerDetails>(0);

			for (Object[] objects : list) {

				GetCreditCustomerDetails bean = new GetCreditCustomerDetails();

				String newBal = (objects[0].toString());
				totalAmount = Double.valueOf(newBal);

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
		return totalAmount;
	}

	public Double getTotalAmtForSeed(String billNo) {
		HibernateUtility hbu = null ;
		Session session = null;
		List<Object[]> list  = null;
		Double totalAmount = null;
		List<GetCreditCustomerDetails> itemlist = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select f.gross_total, f.insert_date from seed_pesticide_billing f where f.bill_no="+billNo);
			list = query.list();
			itemlist = new ArrayList<GetCreditCustomerDetails>(0);

			for (Object[] objects : list) {

				GetCreditCustomerDetails bean = new GetCreditCustomerDetails();

				String newBal = (objects[0].toString());
				totalAmount = Double.valueOf(newBal);

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
		return totalAmount;
	}

	public List getCreditCustomerForEdit(Long customerId) {

		System.out.println("into dao Credit Customer : "+customerId);
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("SELECT c.first_name, c.middle_name, c.last_name,c.email_id, c.address, c.contact_no, c.pin_code, c.aadhar_no FROM customer_details c where c.pk_customer_id="+customerId);
			list = query.list(); 
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		System.out.println("out of dao - return credit customer List : "+list);
		return list;
	}

	public List getCreditCustomerList(){

		HibernateUtility hbu=null;
		Session session=null;
		List<GetCreditCustomerDetails> custList=null;
		try{	
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("SELECT first_name, middle_name, last_name, email_id, address, contact_no, pin_code, aadhar_no FROM customer_details");
			List<Object[]> list = query.list();
			custList= new ArrayList<GetCreditCustomerDetails>(0);
			for (Object[] object : list) {	
				GetCreditCustomerDetails reports = new GetCreditCustomerDetails();

				reports.setFirstName(object[0].toString());
				reports.setMiddleName(object[1].toString());
				reports.setLastName(object[2].toString());
				reports.setAddress(object[4].toString());
				reports.setEmail(object[3].toString());
				reports.setContactNo((BigInteger)object[5]);
				reports.setZipCode((BigInteger)object[6]);
				reports.setAadhar((BigInteger)object[7]);

				custList.add(reports);

			}}catch(RuntimeException e){	
			}
		finally{

			hbu.closeSession(session);	
		}
		return custList;
	}
}
