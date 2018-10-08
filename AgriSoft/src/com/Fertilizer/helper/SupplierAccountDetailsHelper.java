package com.Fertilizer.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.bean.GetSupplierDetails;
import com.Fertilizer.bean.TadayRemainingBalance;
import com.Fertilizer.dao.SupplierAccountDetailsDao;
import com.Fertilizer.hibernate.CashBankBookDataDateWise;
import com.Fertilizer.hibernate.SupplierAccountDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class SupplierAccountDetailsHelper {

	public void suppAccDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String fk_supplier_id = request.getParameter("fk_supplier_id");
		String billNo = request.getParameter("billNo");
		String totalAmt = request.getParameter("totalAmt");
		SupplierAccountDetailsBean sadb = new SupplierAccountDetailsBean();
		sadb.setFk_supplier_id(Long.parseLong(fk_supplier_id));
		sadb.setBillNo(Long.parseLong(billNo));
		sadb.setTotalAmt(Double.parseDouble(totalAmt));
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));
		sadb.setInsertDate(dateobj);


		SupplierAccountDetailsDao sadd = new SupplierAccountDetailsDao();
		sadd.supplierAccount(sadb);
	}

	public Map getAllBillBySuppliers(String supplierId) {

		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getAllBillBySuppliers(supplierId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetSupplierDetails bean = new GetSupplierDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo(o[0].toString());
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getBillNo(),bean);
		}
		return map;
	}

	//total amount  on cash
	
	public Map getTotalAmtByBillNo(String supplierId) {

		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list = dao.getTotalAmtByBillNoForCustomer(supplierId);
		Map  map =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetSupplierDetails bean = new GetSupplierDetails();
			String newTotal = (o[0].toString());
			Double newTotal1 = Double.valueOf(newTotal);
			bean.setTotalAmt(Double.parseDouble(o[0].toString()));
			System.out.println("***************"+o[0]);
			map.put(bean.getTotalAmt(),bean);
		}
		return map;
	}

	public Map getRemainingAmtByBillNo(String supplier, String totalAmount) {

		System.out.println("supplier in helper = "+supplier);

		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list = dao.getbalanceAmtByBillNo(supplier);
		List list1 = dao.checkSupplierPayment(supplier);
		Map  map =  new HashMap();
		System.out.println(list1.size()+"LIST SIZE");
		int sic = list1.size();
		if(sic==0)
		{

			GetSupplierDetails bean = new GetSupplierDetails();
			Double totalAmt = dao.getTotalAmt(supplier);
			bean.setBalance(totalAmt);
			map.put(bean.getBalance(),bean);
		}
		else {
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				GetSupplierDetails bean = new GetSupplierDetails();
				Double newBal = (Double.parseDouble(o[1].toString()));
				Double newTotal = (Double.parseDouble(o[2].toString()));
				Double uiTotal = dao.getTotalAmt(supplier);
				Double diff =uiTotal - newTotal ;
				Double updatedBal=newBal+diff;

				if(uiTotal == newTotal){
					System.out.println(newBal+"NEW BALANCE");

					bean.setBalance(newBal);
				}
				else {

					bean.setBalance(updatedBal);
				}
				System.out.println("***************"+o[1]);
				map.put(bean.getBalance(),bean);
			}
		}
		return map;
	}

	public Map getYesterdarTotalAmount() {
		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getYesterdarTotalAmount();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			CashBankBookDataDateWise bean = new CashBankBookDataDateWise();
			System.out.println(Arrays.toString(o));
			//bean.setBillNo(o[0].toString());
			bean.setTotalAmount(Double.parseDouble(o[1].toString()));
			//bean.setTotalAmount((Double)o[1]);
			//System.out.println("***************"+o[0]);
			map.put(bean.getTotalAmount(),bean);

		}
		return map;
	}

	public void creditDebitAmount(){

		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getYesterdarTotalAmount();

		if(list.size() == 0){

			String yesterday = null;
			final Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE, -1);
			yesterday = dateFormat.format(cal.getTime());
			System.out.println("Yesterday Date ===+++ "+yesterday);

			CashBankBookDataDateWise cs = new CashBankBookDataDateWise();

			cs.setDate(yesterday);
			cs.setTotalAmount(0.0d);

			SupplierAccountDetailsDao doa = new SupplierAccountDetailsDao();
			doa.registeryesterdayTotal(cs);
			// List list1= dao.getYesterdaySuppCredit(yesterday);



		}
		else{
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				/*TadayRemainingBalance bean = new TadayRemainingBalance();
			System.out.println(Arrays.toString(o));
			bean.setDate(o[0].toString());
			bean.setTotalAmt(Double.parseDouble(o[1].toString()));*/

				String yesDate = o[0].toString();
				Double yesTotal = Double.parseDouble(o[1].toString());

				/*SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));
			String currDate = dateFormat1.format(dateobj);*/

				String yesterday = null;
				final Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				cal.add(Calendar.DATE, -1);

				yesterday = dateFormat.format(cal.getTime());

				System.out.println("Yesterday Datjhgjhe ===+++ "+yesterday);

				if(yesDate.equals(yesterday)){

					System.out.println("hello shreemant");
					break;

				}
				else{
					//List list2= dao.getDiffBetDates();
					String credit = "credit";
					String debit = "debit";
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
					Date dateobj = new Date();
					String todayDate = dateFormat1.format(dateobj);
					System.out.println("Today Date :::: "+todayDate);
					HibernateUtility hbu = HibernateUtility.getInstance();
					Session session = hbu.getHibernateSession();


					Query query = session.createSQLQuery("select onDate , DATEDIFF(:todayDate,:yesDate) from cashbankbooktable");

					query.setParameter("yesDate",yesDate);
					query.setParameter("todayDate",todayDate);
					List<Object[]> list1 = query.list();
					String diff = null;
					for (Object[] objects : list1) {

						diff = (objects[1].toString());
						System.out.println("date Difference is ==++ "+diff);

					}
					Integer datediff = Integer.parseInt(diff);

					for(int j=1 ;j < datediff;j++){

						String diffDate = null;
						int abc = datediff - j;
						System.out.println("shree == ++"+abc);
						final Calendar calc = Calendar.getInstance();
						SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
						calc.add(Calendar.DATE, -abc);
						diffDate = dateFormat2.format(calc.getTime());
						System.out.println("diff Dates ::::  "+diffDate);

						Query query1 = session.createSQLQuery("select bill_no, sum(payment) from supplier_payment where paymentType=:credit and insert_date=:diffDate");

						query1.setParameter("credit",credit);
						query1.setParameter("diffDate",diffDate);
						Double suppTotal = 0d;
						List<Object[]> list2 = query1.list();
						//String diff = null;
						for (Object[] objects : list2) {
							System.out.println("objects  ==  "+Arrays.toString(objects));
							System.out.println("Size  ==  "+  list2.size());
							if(objects[1] == null){
								suppTotal =  0.0d;
							}
							else
							{	
								suppTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Supplier Credit Amount ==++ "+suppTotal);
						}

						Query query2 = session.createSQLQuery("select fk_employee_id, sum(payment) from employee_payment where paymentType=:credit and insert_date=:diffDate");

						query2.setParameter("credit",credit);
						query2.setParameter("diffDate",diffDate);
						Double empTotal = 0d;
						List<Object[]> list3 = query2.list();
						//String diff = null;
						for (Object[] objects : list3) {

							if(objects[1] == null){
								empTotal =  0.0d;
							}
							else
							{	
								empTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Employee Credit Amount ==++ "+empTotal);
						}

						Query query3 = session.createSQLQuery("select bill_no, sum(payment) from credit_customer_payment where paymentType=:credit and insert_date=:diffDate");

						query3.setParameter("credit",credit);
						query3.setParameter("diffDate",diffDate);
						Double custTotal = 0d;
						List<Object[]> list4 = query3.list();
						//String diff = null;
						for (Object[] objects : list4) {

							if(objects[1] == null){
								custTotal =  0.0d;
							}
							else
							{	
								custTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Customer Credit Amount ==++ "+custTotal);
						}

						Query query4 = session.createSQLQuery("select fk_expense_detail_id, sum(credit) from expenditure_payment where insert_date=:diffDate");


						query4.setParameter("diffDate",diffDate);
						Double expanTotal = 0.0d;
						List<Object[]> list5 = query4.list();
						//String diff = null;
						for (Object[] objects : list5) {

							if(objects[1] == null){
								expanTotal =  0.0d;
							}
							else
							{	
								expanTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("expandecture Credit Amount ==++ "+expanTotal);
						}

						Query query5 = session.createSQLQuery("select DISTINCT COUNT(DISTINCT bill_no), sum(DISTINCT  gross_total) from fertilizer_billing where insert_date=:diffDate");


						query5.setParameter("diffDate",diffDate);
						Double saleTotal = 0d;
						List<Object[]> list6 = query5.list();
						//String diff = null;
						for (Object[] objects : list6) {

							if(objects[1] == null){
								saleTotal =  0.0d;
							}
							else
							{	
								saleTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("fertilizer Sale Amount ==++ "+saleTotal);
						}

						Query query6 = session.createSQLQuery("select DISTINCT COUNT(DISTINCT bill_no), sum(DISTINCT  gross_total) from seed_pesticide_billing where insert_date=:diffDate");


						query6.setParameter("diffDate",diffDate);
						Double saleTotal1 = 0.0d;
						List<Object[]> list7 = query6.list();
						//String diff = null;
						for (Object[] objects : list7) {

							if(objects[1] == null){
								saleTotal1 =  0.0d;
							}
							else
							{	
								saleTotal1 =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("seed Pestiside Amount ==++ "+saleTotal1);
						}



						Query query10 = session.createSQLQuery("select bill_no, sum(payment) from supplier_payment where paymentType=:debit and insert_date=:diffDate");

						query10.setParameter("debit",debit);
						query10.setParameter("diffDate",diffDate);
						Double suppdebitTotal = 0.0d;
						List<Object[]> list10 = query10.list();
						//String diff = null;
						for (Object[] objects : list10) {

							if(objects[1] == null){
								suppdebitTotal =  0.0d;
							}
							else
							{	
								suppdebitTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Supplier debit Amount ==++ "+suppdebitTotal);
						}

						Query query11 = session.createSQLQuery("select fk_employee_id, sum(payment) from employee_payment where paymentType=:debit and insert_date=:diffDate");

						query11.setParameter("debit",debit);
						query11.setParameter("diffDate",diffDate);
						Double empdebitTotal = 0.0d;
						List<Object[]> list11 = query11.list();
						//String diff = null;
						for (Object[] objects : list11) {

							if(objects[1] == null){
								empdebitTotal =  0.0d;
							}
							else
							{	
								empdebitTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Employee debit Amount ==++ "+empdebitTotal);
						}

						Query query12 = session.createSQLQuery("select bill_no, sum(payment) from credit_customer_payment where paymentType=:debit and insert_date=:diffDate");

						query12.setParameter("debit",debit);
						query12.setParameter("diffDate",diffDate);
						Double custdebitTotal = 0.0d;
						List<Object[]> list12 = query12.list();
						//String diff = null;
						for (Object[] objects : list12) {

							if(objects[1] == null){
								custdebitTotal =  0.0d;
							}
							else
							{	
								custdebitTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("Customer debit Amount ==++ "+custdebitTotal);
						}

						Query query13 = session.createSQLQuery("select fk_expense_detail_id, sum(debit) from expenditure_payment where insert_date=:diffDate");


						query13.setParameter("diffDate",diffDate);
						Double expandebitTotal = 0d;
						List<Object[]> list13 = query13.list();
						//String diff = null;
						for (Object[] objects : list13) {

							if(objects[1] == null){
								expandebitTotal =  0.0d;
							}
							else
							{	
								expandebitTotal =  Double.parseDouble(objects[1].toString());
							}
							System.out.println("expandecture debit Amount ==++ "+expandebitTotal);
						}




						Query query7 = session.createSQLQuery("SELECT onDate , totalAmount FROM cashbankbooktable ORDER BY pkLastCashId DESC LIMIT 1");

						Double previousTotal = 0.0d;
						List<Object[]> list8 = query7.list();
						//String diff = null;
						for (Object[] objects : list8) {


							previousTotal =  Double.parseDouble(objects[1].toString());

							System.out.println("seed Pestiside Amount ==++ "+saleTotal1);
						}


						Double finalTotal = (previousTotal + saleTotal1 + saleTotal + suppdebitTotal + empdebitTotal + custdebitTotal + expandebitTotal) - (suppTotal + empTotal + custTotal + expanTotal);

						CashBankBookDataDateWise cs1 = new CashBankBookDataDateWise();

						cs1.setDate(diffDate);
						cs1.setTotalAmount(finalTotal);

						SupplierAccountDetailsDao doa = new SupplierAccountDetailsDao();
						doa.registeryesterdayTotal(cs1);
					}



				}

			}
		}
	}



	public Map getTodaySaleTotalAmount() {

		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getTodaySaleTotalAmount();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			CashBankBookDataDateWise bean = new CashBankBookDataDateWise();
			System.out.println(Arrays.toString(o));

			if(o[1] == null){
				bean.setTotalAmount(0.0d);
			}
			else{
				bean.setTotalAmount(Double.parseDouble(o[1].toString()));
			}

			map.put(bean.getTotalAmount(),bean);

		}
		return map;
	}



	public Map getTodaySaleTotalAmount1() {
		// TODO Auto-generated method stub
		SupplierAccountDetailsDao dao = new SupplierAccountDetailsDao();
		List list= dao.getTodaySaleTotalAmount1();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			CashBankBookDataDateWise bean = new CashBankBookDataDateWise();
			System.out.println(Arrays.toString(o));
			if(o[1] == null){
				bean.setTotalAmount(0.0d);
			}
			else{
				bean.setTotalAmount(Double.parseDouble(o[1].toString()));
			}
			map.put(bean.getTotalAmount(),bean);

		}
		return map;
	}


}
