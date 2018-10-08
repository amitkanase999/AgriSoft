package com.Fertilizer.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.GetCreditCustomerDetails;
import com.Fertilizer.bean.GetEmployeeDetails;
import com.Fertilizer.hibernate.EmployeeDetailsBean;
import com.Fertilizer.utility.HibernateUtility;


public class EmployeeDetailsDao {

	public void valEmployeeDetails(EmployeeDetailsBean ed1){
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

			session.save(ed1);
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

	public List getAllMainEmployee()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List list=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("from EmployeeDetailsBean");
			list = query.list();
		}catch(Exception e){	
			Log.error("Error in getAllMainEmployee",e);
		}
		finally{
			if(session!=null){
				hbu.closeSession(session);	
			}
		}
		return list;
	}

	public List getAllEmployeeDetailsForEdit(Long empId) {

		System.out.println("into dao employee : "+empId);
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("SELECT first_name, middle_name, last_name, joining_date, email_id, salary, contact_no, address, pin_code FROM employee_details WHERE pk_empoyee_id ="+empId);
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

	public List getEmployeeList(){

		HibernateUtility hbu=null;
		Session session=null;
		List<GetEmployeeDetails> empList=null;
		try{	

			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT first_name, middle_name, last_name, joining_date, email_id, salary, contact_no, address, pin_code FROM employee_details;");
			List<Object[]> list = query.list();

			empList= new ArrayList<GetEmployeeDetails>(0);

			for (Object[] object : list) {	
				GetEmployeeDetails reports = new GetEmployeeDetails();

				reports.setFirstName(object[0].toString());
				reports.setMiddleName(object[1].toString());
				reports.setLastName(object[2].toString());
				reports.setJoiningDate((Date)object[3]);
				reports.setEmail(object[4].toString());
				reports.setSalary(Double.parseDouble(object[5].toString()));
				reports.setContactNo((BigInteger)object[6]);
				reports.setAddresst(object[7].toString());
				reports.setZipCode((BigInteger)object[8]);

				empList.add(reports);

			}}catch(RuntimeException e){	
			}
		finally{

			hbu.closeSession(session);	
		}
		return empList;
	}
}
