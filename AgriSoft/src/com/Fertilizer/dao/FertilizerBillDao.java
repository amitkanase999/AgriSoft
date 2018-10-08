package com.Fertilizer.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.AllBillNoBean;
import com.Fertilizer.bean.CreditCustBillNoAndName;
import com.Fertilizer.bean.CustomerBillBean;
import com.Fertilizer.bean.DayWiseSale;
import com.Fertilizer.bean.GetBillDetails;
import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.bean.LoanCashBookReportBean;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.FertilizerBillingCollectionBean;
import com.Fertilizer.utility.HibernateUtility;

public class FertilizerBillDao {

	public void addFertilizerBillingInDAO(FertilizerBillBean bean) {

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
	}

	
	
	public void addFertilizerBillingInDAOInBillingCollection(FertilizerBillingCollectionBean cbean) {

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
			session.save(cbean);
			transaction.commit();
			System.out.println("Successful");
		}
		catch(RuntimeException e){
			try{
				e.printStackTrace();
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				rbe.printStackTrace();
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}finally{
			hbu.closeSession(session);
		}
	}
	
	
	
	
	
	public List getAllBillNoOnSaleReturn() {
		HibernateUtility hbu = null;
		Session session = null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from FertilizerBillBean group by billNo");
			list = query.list();
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllier", e);
		}
		finally {
			if (session != null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	public List getAllFertiIetmByBillNo(String bill_no) {
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;

		try{
			hbu=HibernateUtility.getInstance();
			session=hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT p.pk_fertilizer_bill_id,p.fk_goods_receive_id ,p.cat_id,p.customer_name,p.product_name,p.company,p.weight,p.sale_price,p.mrp,p.quantity_after_return,p.total_per_product,p.barcode,p.aadhar,p.insert_date,p.credit_customer_name,p.tax_percentage from fertilizer_billing p WHERE p.bill_no=:bill_no");

			query.setParameter("bill_no", bill_no);

			list = query.list();

			System.out.println(list.size()+"===List size");
		}catch(RuntimeException e){

			Log.error("Error in getProductDetails",e);
		}finally{
			if(session!=null){

				hbu.closeSession(session);
			}
		}
		return list;
	}


	public List getCustomerBill(){	
		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no , customer_name FROM fertilizer_billing ORDER BY bill_no DESC LIMIT 1");

			List<Object[]> list = query.list();
			saleList= new ArrayList<CustomerBillBean>(0);
			for (Object[] object : list) {
				CustomerBillBean reports = new CustomerBillBean();
				reports.setBillNo(Long.parseLong(object[0].toString()));
				saleList.add(reports);	 
			}}
		catch(RuntimeException e)
		{
			Log.error("Error in getCustomerBill()", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;
	}


	public List getAllClosingReport(){
		java.util.Date date = new java.util.Date();  
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String data = (dateFormat.format(date)); //2014/08/06 15:59:4
		HibernateUtility hbu=null;
		Session session=null;
		List<DayWiseSale> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select customerBill, product_name , quantity , sale_price ,gross_total, payment_mode from fertilizer_bill where  insert_date="+data);	
			List<Object[]> list = query.list();
			saleList= new ArrayList<DayWiseSale>(0);	
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));

				DayWiseSale reports = new DayWiseSale();
				reports.setCustomerBill(Long.parseLong(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setQuantity1((BigInteger) object[2]);
				reports.setPrice(Double.parseDouble(object[3].toString()));

				reports.setTotAmount(Double.parseDouble(object[4].toString()));
				reports.setPaymentMode(object[5].toString());

				saleList.add(reports);		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return saleList;
	}

	public List getSaleDetailsDateWise(String startDate,String endDate){

		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select DATE(c.insert_date) , c.quantity , c.sale_price ,c.gross_total,c.customerBill,c.expenses from fertilizer_bill c  where c.insert_date BETWEEN :stDate AND :edDate");
			query2.setParameter("stDate", startDate);
			query2.setParameter("edDate", endDate);
			List<Object[]> list = query2.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setSoldDate(object[0].toString());
				System.out.println(Arrays.toString(object));
				reports.setSalePriceforsale((BigDecimal)object[2]);
				reports.setQuantity1((BigInteger)object[1]);
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));
				reports.setCustomerBill(Integer.parseInt(object[4].toString()));
				reports.setExpenses((BigDecimal)object[5]);
				saleList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return saleList;
	}

	public List getSaleDetailsBySingalDateWise(String isInsertDate){

		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery(" select customerBill,  DATE(insert_date) , quantity  ,sale_price , gross_total ,expenses from fertilizer_bill where DATE(insert_date)=:isInsertDate ");
			query.setParameter("isInsertDate", isInsertDate);
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setSoldDate(object[1].toString());
				reports.setQuantity1(((BigInteger) object[2]));
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				reports.setTotalAmount(Double.parseDouble(object[4].toString()));
				reports.setExpenses((BigDecimal)object[5]);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	//this method is used to get the all bill no of fertilizer and sedds and pesticides 

	public List getNorCustBillAndClientName()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List<GetBillDetails> billList=null;
		List<Object[]> list = null;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select bill_no, customer_name from seed_pesticide_billing where customer_name != 'N/A' group by bill_no;");
			list = query.list();
			billList = new ArrayList<GetBillDetails>(0);

			for (Object[] objects : list) {
				GetBillDetails bean = new GetBillDetails();

				bean.setBillNo(Long.parseLong(objects[0].toString()));
				bean.setClientName((String) objects[1]);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}

	public List getNorCustBillAndClientNameForPestiBillCopy()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List<GetBillDetails> billList=null;
		List<Object[]> list = null;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select bill_no, customer_name from pesticide_billing where customer_name != 'N/A' group by bill_no;");
			list = query.list();
			billList = new ArrayList<GetBillDetails>(0);

			for (Object[] objects : list) {
				GetBillDetails bean = new GetBillDetails();

				bean.setBillNo(Long.parseLong(objects[0].toString()));
				bean.setClientName((String) objects[1]);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}


	//this method is used to get the all bill no of fertilizer and sedds and pesticides 

	public List getCreditCustBIllAndClientName()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List<CreditCustBillNoAndName> billList=null;
		List<Object[]> list = null;

		String fname;
		String lname;
		String clientName;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select seed_pesticide_billing.bill_no, customer_details.first_name, customer_details.last_name from seed_pesticide_billing left join customer_details on seed_pesticide_billing.fk_customer_id = customer_details.pk_customer_id where seed_pesticide_billing.fk_customer_id != 0 OR seed_pesticide_billing.fk_customer_id != null group by bill_no");
			list = query.list();
			billList = new ArrayList<CreditCustBillNoAndName>(0);

			for (Object[] objects : list) {
				CreditCustBillNoAndName bean = new CreditCustBillNoAndName();

				bean.setBillNo(Long.parseLong(objects[0].toString()));

				fname = (String) objects[1];
				lname = (String) objects[2];
				clientName = fname+" "+lname;
				bean.setClientName(clientName);

				System.out.println("creit cust Bill No and name++++++"+objects[0].toString()+"++++++++++"+(String) objects[1]);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}

	public List getCreditCustBIllAndClientNameFoerPestiBillCopy()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List<CreditCustBillNoAndName> billList=null;
		List<Object[]> list = null;

		String fname;
		String lname;
		String clientName;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select p.bill_no, customer_details.first_name, customer_details.last_name from pesticide_billing p left join customer_details on p.fk_customer_id = customer_details.pk_customer_id where p.fk_customer_id != 0 OR p.fk_customer_id != null group by p.bill_no");
			list = query.list();
			billList = new ArrayList<CreditCustBillNoAndName>(0);

			for (Object[] objects : list) {
				CreditCustBillNoAndName bean = new CreditCustBillNoAndName();

				bean.setBillNo(Long.parseLong(objects[0].toString()));

				fname = (String) objects[1];
				lname = (String) objects[2];
				clientName = fname+" "+lname;
				bean.setClientName(clientName);

				System.out.println("creit cust Bill No and name++++++"+objects[0].toString()+"++++++++++"+(String) objects[1]);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}

	public List getAmountByCreditCustPayment(String startDate, String endDate) {

		HibernateUtility hbu = null;
		Session session = null;
		List<SaleReports> saleList = null;
		try {
			System.out.println(" startDate ===++++ "+startDate);
			System.out.println(" endDate ===++++ "+endDate);
			String type = "credit";
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select insert_date,bill_no, first_name, payment, paymentType from credit_customer_payment left join customer_details on credit_customer_payment.fk_customer_id = customer_details.pk_customer_id where insert_date BETWEEN '"+startDate+"' AND '"+endDate+"' AND  paymentType = '"+type+"' ");
			List<Object[]> list = query2.list();
			saleList = new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setSoldDate(object[0].toString());
				reports.setCustomerBill(Integer.parseInt(object[1].toString()));
				reports.setCusomerName(object[2].toString());
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));

				saleList.add(reports);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}


	// this method is used to get the credit amount by customer billing

	public List getAmountByCustBilling(String startDate, String endDate, String cat) {

		HibernateUtility hbu = null;
		Session session = null;
		List<SaleReports> saleList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select date(insert_date), bill_no, credit_customer_name, payment_mode,gross_total from fertilizer_billing where insert_date BETWEEN '"+startDate+"' AND '"+endDate+"' AND customer_name = 'N/A' AND cat_id='"+cat+"' UNION select date(insert_date), bill_no, credit_customer_name, payment_mode,gross_total from pesticide_billing where insert_date BETWEEN '"+startDate+"' AND '"+endDate+"' AND customer_name = 'N/A' AND cat_id='"+cat+"' UNION select date(insert_date), bill_no, credit_customer_name, payment_mode,gross_total from seed_pesticide_billing where insert_date BETWEEN '"+startDate+"' AND '"+endDate+"' AND customer_name = 'N/A' AND cat_id='"+cat+"'");
			List<Object[]> list = query2.list();
			saleList = new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setSoldDate(object[0].toString());
				reports.setCustomerBill(Integer.parseInt(object[1].toString()));
				reports.setCusomerName(object[2].toString());
				reports.setTotalAmount(Double.parseDouble(object[4].toString()));

				saleList.add(reports);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

	public List getPaidAmountToSupplier(String startDate, String endDate) {

		HibernateUtility hbu = null;
		Session session = null;
		List<SaleReports> saleList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select insert_date, bill_no, person_name, payment from supplier_payment WHERE paymentType ='debit' AND insert_date BETWEEN :stDate AND :edDate");
			query2.setParameter("stDate", startDate);
			query2.setParameter("edDate", endDate);
			List<Object[]> list = query2.list();
			saleList = new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setSoldDate(object[0].toString());
				reports.setCustomerBill(Integer.parseInt(object[1].toString()));
				reports.setCusomerName(object[2].toString());
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));

				saleList.add(reports);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

	public List getPaidAmountToEmployee(String startDate, String endDate) {

		HibernateUtility hbu = null;
		Session session = null;
		List<SaleReports> saleList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select insert_date, first_name, reason, payment from employee_payment left join employee_details on employee_payment.fk_employee_id = employee_details.pk_empoyee_id WHERE paymentType='debit' AND insert_date BETWEEN :stDate AND :edDate");
			query2.setParameter("stDate", startDate);
			query2.setParameter("edDate", endDate);
			System.out.println(startDate);
			System.out.println(endDate);
			List<Object[]> list = query2.list();
			saleList = new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setSoldDate(object[0].toString());
				reports.setCusomerName(object[1].toString());
				reports.setReason(object[2].toString());
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));

				saleList.add(reports);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

	public List getNormalCustFertilizerBillNos()
	{
		HibernateUtility hbu=null;
		Session session=null;

		List<GetBillDetails> billList=null;
		List<Object[]> list = null;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select bill_no, customer_name from fertilizer_billing where customer_name != 'N/A' group by bill_no;");
			list = query.list();
			billList = new ArrayList<GetBillDetails>(0);

			for (Object[] objects : list) {
				GetBillDetails bean = new GetBillDetails();

				bean.setBillNo(Long.parseLong(objects[0].toString()));
				bean.setClientName((String) objects[1]);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}

	// Get Credit Customer  Fertilizer Bill Nos from Fertilizer_billing table

	public List getCreditCustFertilizerBillNos()
	{
		HibernateUtility hbu=null;
		Session session=null;

		String fname;
		String lname;
		String clientName;

		List<GetBillDetails> billList=null;
		List<Object[]> list;

		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("select fertilizer_billing.bill_no, customer_details.first_name, customer_details.last_name from fertilizer_billing left join customer_details on fertilizer_billing.fk_customer_id = customer_details.pk_customer_id where fertilizer_billing.fk_customer_id != 0 OR fertilizer_billing.fk_customer_id != null group by bill_no");
			list = query.list();
			billList = new ArrayList<GetBillDetails>(0);

			for (Object[] objects : list) {
				GetBillDetails bean = new GetBillDetails();

				bean.setBillNo(Long.parseLong(objects[0].toString()));
				fname = (String) objects[1];
				lname = (String) objects[2];
				clientName = fname+" "+lname;
				bean.setClientName(clientName);

				billList.add(bean);
			}
		}
		catch(RuntimeException  e)
		{

		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return billList;
	}

	// Tax details as per cat from sale Two Date

	public List<GetProductDetails> getPurchaseDetailsForCategoryBetTwoDate(
			String cat, String fDate, String sDate) {
		HibernateUtility hbu=null;
		Session session=null;
		List<GetProductDetails> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery(" SELECT bill_no,product_name, company ,weight,sale_price, mrp,quantity,tax_percentage,total_per_product FROM fertilizer_billing WHERE cat_id =:cat AND tax_percentage>0 AND DATE(insert_date) BETWEEN :fDate AND :sDate UNION SELECT bill_no,product_name, company ,weight,sale_price, mrp,quantity,tax_percentage,total_per_product FROM seed_pesticide_billing WHERE cat_id =:cat AND tax_percentage>0 AND DATE(insert_date) BETWEEN :fDate AND :sDate");
			query.setParameter("cat", cat);
			query.setParameter("fDate", fDate);
			query.setParameter("sDate", sDate);
			List<Object[]> list = query.list();
			saleList= new ArrayList<GetProductDetails>(0);

			for (Object[] o : list) {

				GetProductDetails reports = new GetProductDetails();
				reports.setBillNo(Long.parseLong(o[0].toString()));
				reports.setProduct(o[1].toString());
				reports.setManufacturer(o[2].toString());
				reports.setWeight(Double.parseDouble(o[3].toString()));
				reports.setSalePrice(Double.parseDouble(o[4].toString()));
				reports.setMrp(Double.parseDouble(o[5].toString()));
				reports.setQuantity(Long.parseLong(o[6].toString()));
				reports.setTaxPercentage(Double.parseDouble(o[7].toString()));

				Double salePrice = Double.parseDouble(o[4].toString());
				Double TaxPercentage = Double.parseDouble(o[7].toString());
				Double taxAmountPerunit = (TaxPercentage/100)*salePrice;
				Double newSale = salePrice + taxAmountPerunit;
				Double quantity = Double.parseDouble(o[6].toString());
				Double totalTaxAmount = quantity * taxAmountPerunit;

				reports.setTaxAmount(totalTaxAmount);

				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}


	public List getCashSaleDetail() {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from fertilizer_billing WHERE  PayCustType='cash' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getCreditSaleDetail() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from fertilizer_billing WHERE  PayCustType='credit' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getCashSaleDetailForSeed() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from seed_pesticide_billing WHERE PayCustType='cash' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getCreditSaleDetailForSeed() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from seed_pesticide_billing WHERE PayCustType='credit' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getCashSaleDetailForPesti() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from pesticide_billing WHERE PayCustType='cash' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getCreditSaleDetailForPesti() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total from pesticide_billing WHERE PayCustType='credit' AND insert_date ='"+onlyDate+"' GROUP BY bill_no");
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

	public List getpurchaseForFerti() {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT gross_total From goods_receive where insertDate ='"+onlyDate+"' GROUP BY bill_number");
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

	public List getpurchaseForSeed() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(gross_total) From goods_receive where fkCategoryId = '3' AND insertDate ='"+onlyDate+"'");
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

	public List getpurchaseForPesti() {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String onlyDate = dateFormat1.format(dateobj);
		System.out.println(dateFormat1.format(dateobj));

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT SUM(gross_total) From goods_receive where fkCategoryId = '2' AND insertDate ='"+onlyDate+"'");
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

	public List getCustomerBillNo()
	{	
		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_number , sum(customer_name) FROM sale_return ORDER BY bill_number DESC LIMIT 1");

			List<Object[]> list = query.list();
			saleList= new ArrayList<CustomerBillBean>(0);
			for (Object[] object : list) {
				CustomerBillBean reports = new CustomerBillBean();
				reports.setBillNo(Long.parseLong(object[0].toString()));
				saleList.add(reports);	 
			}}
		catch(RuntimeException e)
		{
			Log.error("Error in getCustomerBill()", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;
	}
	
	public List getAllBillEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from FertilizerBillBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllBillDetails", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
	//get all bill no  for sale report bill no wise
	public List getAllBillNo()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		// List list = null;
		
		List<AllBillNoBean> billno = null;
		 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 query = session.createSQLQuery("select bill_no from fertilizer_billing UNION  SELECT bill_no from seed_pesticide_billing UNION  SELECT bill_no from pesticide_billing GROUP BY bill_no");
			 List<Object[]> list = query.list();
			 //List list2 = query.list();
			billno = new ArrayList<AllBillNoBean>(0);
			 for(Object[] o:list)
			 {
				 AllBillNoBean bean=new AllBillNoBean();
				 bean.setBillno(o[0].toString());
				 billno.add(bean);
			 }
			 System.out.println("List in Dao For all bill no"+list);
		} catch (RuntimeException e) {
			Log.error("Error in getAllMainCat", e);
			e.printStackTrace();
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
				return billno;
		
	}
	
	
}
