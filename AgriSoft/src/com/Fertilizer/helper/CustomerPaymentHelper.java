package com.Fertilizer.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Fertilizer.bean.CreditCustPaymentDetail;
import com.Fertilizer.dao.CustomerPaymentDao;
import com.Fertilizer.dao.FertilizerBillDao;
import com.Fertilizer.dao.PesticideBillDao;
import com.Fertilizer.dao.SeedPesticideBillDAO;
import com.Fertilizer.hibernate.CustomerPaymentBean;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.PesticideBillBean;
import com.Fertilizer.hibernate.SeedPesticideBillBean;
import com.Fertilizer.utility.HibernateUtility;

public class CustomerPaymentHelper {

	Double bal  ;
	public void regCreditCustomerCashBank(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In helper");

		String customer = request.getParameter("customer");
		String billNo = request.getParameter("billNo");
		String totalAmount = request.getParameter("totalAmount");
		String bankName = request.getParameter("bankName");
		String custPay = request.getParameter("custPay");
		String paymentMode = request.getParameter("paymentMode");
		String chequeNum = request.getParameter("chequeNum");
		String cardNum = request.getParameter("cardNum");
		String accNum = request.getParameter("accNum");
		String personname = request.getParameter("personname");
		String nameOnCheck = request.getParameter("nameOnCheck");
		String paymentType = request.getParameter("paymentType");
		String catId = request.getParameter("catId");

		CustomerPaymentBean bean = new CustomerPaymentBean();

		bean.setCatId(Long.parseLong(catId));
		if(customer==null){
			bean.setCustomer(0l);
		}
		else{
			bean.setCustomer(Long.parseLong(customer));
		}

		bean.setBillNo(Long.parseLong(billNo));
		bean.setPersonname(personname);

		System.out.println("paymentMode "+paymentMode);

		//payment details
		
		if(paymentMode==null){
			bean.setPaymentMode("N/A");
		}
		else{
			bean.setPaymentMode(paymentMode);
		}

		if(paymentMode.equals("cheque1")){

			if(chequeNum==null){
				bean.setChequeNum("N/A");
			}
			else{
				bean.setChequeNum(chequeNum);
			}

			if(nameOnCheck==null){
				bean.setNameOnCheck("N/A");
			}
			else{
				bean.setNameOnCheck(nameOnCheck);
			}
		}
		else if(paymentMode.equals("card1")){

			int cardNumLength = cardNum.length();
			if(cardNumLength > 0){

				bean.setCardNum(Long.parseLong(cardNum));
			}
			else{
				bean.setCardNum(null);
			}
		}

		else if(paymentMode.equals("neft1")){
			if(bankName==null){
				bean.setBankName("N/A");
			}
			else{
				bean.setBankName(bankName);
			}

			int accNumLength = accNum.length();
			if(accNumLength > 0){
				bean.setAccNum(Long.parseLong(accNum));	

			}
			else{
				bean.setAccNum(null);
			}
		}	

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));

		bean.setInsertDate(dateobj);
		String newDate = dateFormat1.format(dateobj);
		HibernateUtility hbu = HibernateUtility.getInstance();
		Session session = hbu.getHibernateSession();

		//Query to get latest paid amount
		
		Query query = session.createSQLQuery("SELECT balance ,bill_no from credit_customer_payment WHERE bill_no =:billNo ORDER BY  pk_credit_customer_id  DESC LIMIT 1 ;");
		query.setParameter("billNo",billNo);
		List<Object[]> list = query.list();

		System.out.println("Calc total");

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			String newBal = objects[0].toString();
			bal= Double.valueOf(newBal);
			System.out.println(bal+"  bal");
			System.out.println("Calc balance");
		}

		if (bal==null) {

			Double balance = Double.parseDouble(totalAmount);

			if(paymentType.equals("credit"))
			{
				Double newBal = balance - Double.parseDouble(custPay);

				bean.setBalance(newBal);
				bean.setPaymentType(paymentType);
				bean.setCredit(Double.parseDouble(custPay));
			}

			else{
				if(paymentType.equals("debit"))
				{
					Double newBal = balance + Double.parseDouble(custPay);

					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(custPay));
				}
				else{

					bean.setCredit(0.0);
				}
			}
			bean.setTotalAmount(Double.parseDouble(totalAmount));


			HttpSession custPaymentSession = request.getSession();
			custPaymentSession.setAttribute("fkCustomerId", customer);
			custPaymentSession.setAttribute("catId", catId);
			custPaymentSession.setAttribute("paymentType", paymentType);
			custPaymentSession.setAttribute("paymentDate", newDate);
			custPaymentSession.setAttribute("billNumber", billNo);

			CustomerPaymentDao dao = new CustomerPaymentDao();
			dao.regCustomerPayment(bean);
		}

		else{

			Double balance = Double.parseDouble(totalAmount);

			if(paymentType.equals("credit"))
			{
				Double newBal = bal - Double.parseDouble(custPay);

				bean.setBalance(newBal);
				bean.setPaymentType(paymentType);
				bean.setCredit(Double.parseDouble(custPay));
				
				System.out.println("=============cat iD :"+catId);
				String cat1="1";
				String cat2="2";
				String cat3="3";
				if(catId.equals(cat1))
				{
				/*----------- Update Balance Status in Fertilizer Table --------------------*/
		        
				HibernateUtility hbu2 = HibernateUtility.getInstance();
				Session session1 = hbu2.getHibernateSession();
				
				Configuration configuration = new Configuration();
		        configuration.configure("hibernate.cfg.xml");
		        SessionFactory factory = configuration.buildSessionFactory();
		        Session session2 = factory.openSession();
		       
				Transaction transaction = session.beginTransaction();
				
				
					
				
				FertilizerBillDao dao2=new FertilizerBillDao();
				List billlist=dao2.getAllBillEntry();
				System.out.println("List size"+billlist.size());
				
				for(int j=0;j<billlist.size();j++){
		        	
				FertilizerBillBean fbb=(FertilizerBillBean)billlist.get(j);
				Long billno=fbb.getBillNo();
				Long pk_fertilizer_bill_id=fbb.getPkfertilizerBillId();
		      
				System.out.println("==================abobe bill if");
				System.out.println("===========bill 1"+billNo);
				System.out.println("===========bill 2"+billno);
				System.out.println("==================New Bill amt inside if"+newBal);
				String BillNum=billno.toString();
				if(billNo.equals(BillNum))
				{
				
					System.out.println("inside if");
					System.out.println("==================New Bill amt inside if"+newBal);
					FertilizerBillBean updatebalance = (FertilizerBillBean) session.get(FertilizerBillBean.class, new Long(pk_fertilizer_bill_id));
					System.out.println("==================New Bill amt inside if"+newBal);
					updatebalance.setNewbalance_status(newBal);
					
				 
					session.saveOrUpdate(updatebalance);
					transaction.commit();
				}
				}
		        /*-----------END OF  Update Status in Fertilizer Table --------------------*/
				}
				
				
				 /*-----------  Update  balance Status in seed  Table --------------------*/
				if(catId.equals(cat2))
				{
					System.out.println("=============== In cat 2 else part");
					HibernateUtility hbu3 = HibernateUtility.getInstance();
					Session session1 = hbu3.getHibernateSession();
					
					Configuration configuration = new Configuration();
			        configuration.configure("hibernate.cfg.xml");
			        SessionFactory factory = configuration.buildSessionFactory();
			        Session session3 = factory.openSession();
			       
					Transaction transaction = session.beginTransaction();
					
					
						
					SeedPesticideBillDAO dao3=new SeedPesticideBillDAO();
					List billlist=dao3.getAllBillSeedEntry();
					
				
					System.out.println("List size"+billlist.size());
					
					for(int j=0;j<billlist.size();j++){
			        	
					SeedPesticideBillBean spbb=(SeedPesticideBillBean)billlist.get(j);
					Long  billno=spbb.getBillNo();
					Long pk_seed_pesticide_bill_id=spbb.getPkSeedPesticideBillId();
					
			      
					System.out.println("==================abobe bill if");
					System.out.println("===========bill 1"+billNo);
					System.out.println("===========bill 2"+billno);
					System.out.println("==================New Bill amt inside if"+newBal);
					
					String BillNum=billno.toString();
					
					if(billNo.equals(BillNum))
					{
					
						System.out.println("inside if");
						System.out.println("==================New Bill amt inside if"+newBal);
						SeedPesticideBillBean updatebalance = (SeedPesticideBillBean) session.get(SeedPesticideBillBean.class, new Long(pk_seed_pesticide_bill_id));
						System.out.println("==================New Bill amt inside if"+newBal);
						updatebalance.setSeedBalance_status(newBal);
						//updatebalance.setNewbalance_status(newBal);
						
					 
						session.saveOrUpdate(updatebalance);
						transaction.commit();
					}
					}
			        /*-----------END OF  Update Status in seed Table --------------------*/

					
				}
				
				 /*-----------  Update  balance Status in Pesticide  Table --------------------*/
				if(catId.equals(cat3))
				{
					System.out.println("=============== In cat 3 else part");
					HibernateUtility hbu3 = HibernateUtility.getInstance();
					Session session1 = hbu3.getHibernateSession();
					
					Configuration configuration = new Configuration();
			        configuration.configure("hibernate.cfg.xml");
			        SessionFactory factory = configuration.buildSessionFactory();
			        Session session3 = factory.openSession();
			       
					Transaction transaction = session.beginTransaction();
					
					
						
					PesticideBillDao dao4=new PesticideBillDao();
					List billlist=dao4.getAllPesticideBillEntry();
					
					System.out.println("List size"+billlist.size());
					
					for(int j=0;j<billlist.size();j++){
			        	
					PesticideBillBean  pbb=(PesticideBillBean)billlist.get(j);	
					Long PkPesticideBillId=pbb.getPkPesticideBillId();
					Long billno=pbb.getBillNo();
					
					System.out.println("==================abobe bill if");
					System.out.println("===========bill 1"+billNo);
					System.out.println("===========bill 2"+billno);
					System.out.println("==================New Bill amt inside if"+newBal);
					
					String BillNum=billno.toString();
					
					if(billNo.equals(BillNum))
					{
					
						System.out.println("inside if");
						System.out.println("==================New Bill amt inside if"+newBal);
						PesticideBillBean updatebalance = (PesticideBillBean) session.get(PesticideBillBean.class, new Long(PkPesticideBillId));
						System.out.println("==================New Bill amt inside if"+newBal);
						updatebalance.setPesticideBalance_Status(newBal);
					
						session.saveOrUpdate(updatebalance);
						transaction.commit();
					}
					}
			        /*-----------END OF  Update Status in Pesticide Table --------------------*/

					
				}

				
				
			
			}

			else{
				if(paymentType.equals("debit"))
				{
					Double newBal = bal + Double.parseDouble(custPay);
					bean.setBalance(newBal);
					bean.setPaymentType(paymentType);
					bean.setCredit(Double.parseDouble(custPay));
				}
				else{
					bean.setCredit(0.0);
				}
			}

			bean.setTotalAmount(Double.parseDouble(totalAmount));

			HttpSession custPaymentSession = request.getSession();
			custPaymentSession.setAttribute("fkCustomerId", customer);
			custPaymentSession.setAttribute("catId", catId);
			custPaymentSession.setAttribute("paymentType", paymentType);
			custPaymentSession.setAttribute("paymentDate", newDate);
			custPaymentSession.setAttribute("billNumber", billNo);
		
			CustomerPaymentDao dao = new CustomerPaymentDao();
			dao.regCustomerPayment(bean);
		}
	}
	
	public List getCustDetailsByDate(HttpServletRequest request,
			HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();

		CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> custList = dao.getCreditCustomerDetailsDateWise(fDate,tDate);

		return custList;
	}
	
	public List getCustPaymentDetailsBySingleDate(HttpServletRequest request,
			HttpServletResponse response) {

		String fDate = request.getParameter("fDate");

		Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();

		CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsForSingleDate(fDate);
		
		return cust1List;
	}
	
	public List getCustPaymentDetailsByBill(HttpServletRequest request,
			HttpServletResponse response) {

		String billNo = request.getParameter("billNo");
		String fkCustomerId = request.getParameter("fkCustomerId");
		String catId = request.getParameter("cat");

		Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();

		CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsAsPerBillNum(billNo,fkCustomerId,catId);

		return cust1List;
	}
	
	public List getCustPaymentDetailsByNames(HttpServletRequest request,
			HttpServletResponse response) {
		String fkCustomerId = request.getParameter("fkCustomerId");

		Map<Long,CreditCustPaymentDetail> map = new HashMap<Long,CreditCustPaymentDetail>();

		CustomerPaymentDao dao = new CustomerPaymentDao();
		List<CreditCustPaymentDetail> cust1List = dao.getCreditCustPaymentDetailsAsPerName(fkCustomerId);

		return cust1List;
	}
}
