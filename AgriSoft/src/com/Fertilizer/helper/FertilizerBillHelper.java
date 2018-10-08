package com.Fertilizer.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.jfree.util.Log;

import com.Fertilizer.bean.CustomerBillBean;
import com.Fertilizer.bean.DayCloseReports;
import com.Fertilizer.bean.FertiSaleReturn;
import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.bean.StockDetail;
import com.Fertilizer.dao.FertilizerBillDao;
import com.Fertilizer.dao.ProductStockDetailsDao;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.FertilizerBillingCollectionBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.hibernate.SaleReturnBean;
import com.Fertilizer.hibernate.Stock;
import com.Fertilizer.utility.HibernateUtility;


public class FertilizerBillHelper {
	Long customerBill;
	public void fertilizerBilling(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		Long billNoForPdf;

		FertilizerBillDao dao = new FertilizerBillDao();
		List bill = dao.getCustomerBill();

		for(int i=0;i<bill.size();i++){
			CustomerBillBean sa=(CustomerBillBean)bill.get(i);
			customerBill= sa.getBillNo();
			System.out.println(customerBill);
			customerBill++;
			System.out.println(customerBill);

		}
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println(count);

		FertilizerBillBean bean = new FertilizerBillBean();
		for(int i =0 ; i<count;i++)
		{

			if(customerBill == null){
				bean.setBillNo(1l);
				billNoForPdf = 1l;
			}
			else
			{
				bean.setBillNo(customerBill);
				billNoForPdf = customerBill;
			}
			//String custname = request.getParameter("custname");
			//String customerName = request.getParameter("customerName");
			String village = request.getParameter("village");
			String contactNum = request.getParameter("contactNo");
			String aadhar = request.getParameter("aadhar");
			String billdate = request.getParameter("billdate");
			String transExpense = request.getParameter("transExpense");
			String hamaliExpense = request.getParameter("hamaliExpense");
			String total = request.getParameter("total");
			String grossTotal = request.getParameter("grossTotal");
			String fkCustomerId = request.getParameter("fkCreditCustomerID");
			String customerHiddenName = request.getParameter("creditCustomerHiddenName");

			String creditCustomerName = request.getParameter("creditCustomerName");
			String customertype=request.getParameter("customertype");
			String paycusttype=request.getParameter("paycusttype");

			bean.setPayCustType(paycusttype);
			bean.setCustname(creditCustomerName);
			bean.setCustomertype(customertype);
			bean.setNewbalance_status(Double.parseDouble(grossTotal));
			
			
			if(fkCustomerId != null){
				bean.setFkCreditCustomerId(Long.parseLong(fkCustomerId));
			}
			else{
				bean.setFkCreditCustomerId(0l);
			}
			if(customerHiddenName != null){
				bean.setCustomerHiddenName(customerHiddenName);
			}
			else{
				bean.setCustomerHiddenName("N/A");
			}

			if(creditCustomerName != null){
				bean.setCustomerName(creditCustomerName);
			}
			else{
				bean.setCustomerName("N/A");
			}
			if(village == null){

				bean.setVillage("N/A");
			}
			else{
				bean.setVillage(village);
			}
			if(contactNum == null){
				bean.setContact(0l);

			}
			else{
				bean.setContact(Long.parseLong(contactNum));
			}
			if(aadhar == null){

				bean.setAadhar(0l);
			}
			else{
				bean.setAadhar(Long.parseLong(aadhar));
			}
			if(transExpense != null){
				bean.setTransExpense(Double.parseDouble(transExpense));
			}
			else{
				bean.setTransExpense(0.0);
			}
			if(hamaliExpense != null){
				bean.setHamaliExpense(Double.parseDouble(hamaliExpense));
			}
			else{
				bean.setHamaliExpense(0.0);
			}
			if(total != null){
				bean.setTotal(Double.parseDouble(total));
			}
			else{
				bean.setTotal(0.0);
			}
			if(grossTotal != null){
				bean.setGrossTotal(Double.parseDouble(grossTotal));
			}
			else{
				bean.setGrossTotal(0.0);
			}

			// Grid Content

			String catId = request.getParameter("cat_id"+i);
			String proName = request.getParameter("itemName"+i);
			String quantity = request.getParameter("quantity"+i);
			String unitName = request.getParameter("unitName"+i);
			String mrp = request.getParameter("mrp"+i);
			String salePrice = request.getParameter("salePrice"+i);
			String totalPerProductInGrid = request.getParameter("total"+i);
			String company = request.getParameter("companyName"+i);
			String weight = request.getParameter("weight"+i);
			String hsn = request.getParameter("hsn"+i);
			String cGst = request.getParameter("cGst"+i);
			String sGst = request.getParameter("sGst"+i);
			String iGst = request.getParameter("iGst"+i);
			Double taxPercentage = (Double.parseDouble(cGst)) + (Double.parseDouble(sGst));
			if(catId != null){
				bean.setCatId(Long.parseLong(catId));
			}
			else{
				bean.setCatId(0l);
			}
			if(proName != null){
				bean.setProductName(proName);
			}
			else{
				bean.setProductName("N/A");
			}
			if(hsn != null){
				bean.setHsn(hsn);
			}
			else{
				bean.setHsn("N/A");
			}
			
			if(unitName != null){
				bean.setUnit(unitName);
			}
			else{
				bean.setUnit("N/A");
			}
			
			if(quantity != null){
				bean.setQuantity(Long.parseLong(quantity));
			}
			else{
				bean.setQuantity(0l);
			}

			if(quantity != null){
				bean.setQuantityAfterReturn(Long.parseLong(quantity));
			}
			else{
				bean.setQuantityAfterReturn(0l);
			}

			if(mrp != null){
				bean.setMrp(Double.parseDouble(mrp));
			}
			else{
				bean.setMrp(0.0);
			}
			if(salePrice != null){
				bean.setSalePrice(Double.parseDouble(salePrice));
			}
			else{
				bean.setSalePrice(0.0);
			}
			if(totalPerProductInGrid != null){
				bean.setTotalInGrid(Double.parseDouble(totalPerProductInGrid));
			}
			else{
				bean.setTotalInGrid(0.0);
			}
			if(taxPercentage != null){
				bean.setTaxPercentage(taxPercentage);
			}
			else{
				bean.setTaxPercentage(0.0);
			}
			if(iGst != null){
				bean.setiGst(Double.parseDouble(iGst));
			}
			else{
				bean.setiGst(0.0);
			}

			if(company != null){
				bean.setCompany(company);
			}
			else{
				bean.setCompany("N/A");
			}
			if(weight != null){
				bean.setWeight(Double.parseDouble(weight));
			}
			else{
				bean.setWeight(0.0);
			}
			if(cGst.equals("0")){
				double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(iGst))));
				double netPrice = Double.parseDouble(salePrice) - igstAmt;
				bean.setNewSalePrice(netPrice);
			}
			if(iGst.equals("0")){
				double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + (taxPercentage))));
				double netPrice = Double.parseDouble(salePrice) - igstAmt;
				bean.setNewSalePrice(netPrice);
			}

			//payment mode

			String paymentMode = request.getParameter("paymentMode");
			String checkNum = request.getParameter("chequeNum");
			String nameOnCheck = request.getParameter("nameOnCheck");
			String cardNo = request.getParameter("cardNum");
			String accNo = request.getParameter("accNum");
			String bankName = request.getParameter("bankName");

			if(customerHiddenName == null){
				System.out.println("paymentMode is "+paymentMode);
				if(paymentMode==null){
					bean.setPaymentMode("N/A");
				}
				else{
					bean.setPaymentMode(paymentMode);
				}

				if(paymentMode.equals("cheque")){
					if(checkNum != null){
						bean.setCheckNo(Long.parseLong(checkNum));
					}
					else{
						bean.setCheckNo(0l);
					}
					if(nameOnCheck != null){
						bean.setNameOnCheck(nameOnCheck);
					}
					else{
						bean.setNameOnCheck("N/A");
					}

				}

				else if(paymentMode.equals("card")){
					if(cardNo != null){
						bean.setCardNo(Long.parseLong(cardNo));
					}
					else{
						bean.setCardNo(0l);
					}
				}

				else if(paymentMode.equals("neft")){

					if(accNo != null){
						bean.setAccNo(accNo);
					}
					else{
						bean.setAccNo("N/A");
					}
					if(bankName != null){
						bean.setBankName(bankName);
					}
					else{
						bean.setBankName("N/A");
					}
				}
			}	

			Double gstPerc=taxPercentage;
			Double igstPerc=Double.parseDouble(iGst);
			if(gstPerc != 0.0){
				Double taxAmnt=((gstPerc/100)*Double.parseDouble(total));
				bean.setTaxAmount(taxAmnt);
			}
			else if(igstPerc != 0.0){
				Double taxAmnt=((igstPerc/100)*Double.parseDouble(total));
				bean.setTaxAmount(taxAmnt);
			}	

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			//Date dateobj = new Date();
			//System.out.println(dateFormat1.format(dateobj));
			Date saledate1=dateFormat1.parse(billdate);
			bean.setInsertDate(saledate1);
			dao.addFertilizerBillingInDAO(bean);

			/*update values to billing_collection table*/
			FertilizerBillingCollectionBean cbean=new FertilizerBillingCollectionBean();
			
			cbean.setCustomerName(creditCustomerName);
			
			if(customerBill == null){
				cbean.setBillno(1l);
			}
			else
			{
				cbean.setBillno(customerBill);
			}
		
			cbean.setBilldate(saledate1);
			cbean.setGrosstotal(Double.parseDouble(grossTotal));
			
			System.out.println("==============creditCustomerName "+creditCustomerName);
			System.out.println("==============customerBill "+customerBill);
			System.out.println("==============dateobj "+saledate1);
		
			
			FertilizerBillDao dao1=new FertilizerBillDao();
			dao1.addFertilizerBillingInDAOInBillingCollection(cbean);
			
			
			
/*----------- Update quantity in Product_Stock_Details Table --------------------*/
	        
			HibernateUtility hbu3 = HibernateUtility.getInstance();
			Session session3 = hbu3.getHibernateSession();
			
			System.out.println("=====proname"+proName);
			System.out.println("=====company"+company);
			System.out.println("=====expiry"+weight);
			//Query to get latest paid amount
			Query query = session3.createSQLQuery("SELECT pk_stock_id,quantity from product_stock_details where product_name=:proName and company_name=:company and packing=:weight");
			query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);
			
			System.out.println("====proName p1 : "+proName);
			System.out.println("====company p2:"+company);
			System.out.println("====weight p3:"+weight);
			List<Object[]> list = query.list();
			
			System.out.println("========List :"+list);
			System.out.println("Calc total");
			String pkStockId="";
			Double qty=0.0d;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
			    pkStockId = objects[0].toString();
			    qty=Double.parseDouble(objects[1].toString());
				
			    System.out.println("------------ StockID"+pkStockId);
	            //updatedqty = Double.valueOf(qty);
				System.out.println(qty+"  bal");
			}
			
			Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        SessionFactory factory = configuration.buildSessionFactory();
	        Session session = factory.openSession();
	       
			Transaction transaction = session.beginTransaction();
			
			ProductStockDetailsDao dao2=new ProductStockDetailsDao();
			List allpro=dao2.getAllEntry();
		
	        
	        System.out.println("List size"+allpro.size());
			
	        for(int j=0;j<allpro.size();j++){
	        	
	        	
	        ProductStockDetailsBean psd = (ProductStockDetailsBean)allpro.get(j);
			Long pk_stock_id=psd.getPk_stock_id();
	       
			
			System.out.println("=======pkStockId :"+pkStockId);
			System.out.println("=======pk_stock_id :"+pk_stock_id);
			
			if(pkStockId.equals(String.valueOf(pk_stock_id)))
			{
				Date updateDate=new Date();
			String quantity1 = request.getParameter("quantity"+i);
			Double uquantity=Double.parseDouble(quantity1);
					
				Double	updatedqty=qty-uquantity;
				System.out.println("==================update qty"+updatedqty);
				Long l = (new Double(updatedqty)).longValue();
				System.out.println("inside if");
				
				ProductStockDetailsBean updatequantity = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class, new Long(pkStockId));
				updatequantity.setQuantity(l);
				updatequantity.setUpdateDate(updateDate);
			 
				session.saveOrUpdate(updatequantity);
				transaction.commit();
				System.out.println("updated sucessfully");
			}
	      
	    }
	        /*-----------END OF  Update quantity in Product_Stock_Details Table --------------------*/

			

			//code for set billno and name in application context for BIll PDF

			HttpSession billNoSession = request.getSession();
			billNoSession.setAttribute("fertilizerBillNo",billNoForPdf);
			billNoSession.setAttribute("creditCustomerName",creditCustomerName);

			System.out.println("---------Bill No for pdf in session::"+ billNoForPdf + "----------------");

			//stock minus from stock detail table
			HibernateUtility hbu1=null;
			Session session1=null;
			Transaction transaction1 = null;

			try
			{
				Long PkStockId;
				Double quantity1;
				hbu1 = HibernateUtility.getInstance();
				session1 = hbu1.getHibernateSession();
				transaction1 = session1.beginTransaction();

				Query query1 = session1.createSQLQuery("select PkStockId , Quantity from stock_detail where ProductName=:product_name AND CompanyName=:company_Name And Weight =:weight");
				query1.setParameter("product_name", proName);
				query1.setParameter("company_Name", company);
				query1.setParameter("weight", weight);

				List<Object[]> list1 = query1.list();

				for (Object[] object : list1) {
					System.out.println(Arrays.toString(object));  
					PkStockId = Long.parseLong(object[0].toString());
					quantity1 = Double.parseDouble(object[1].toString());
					System.out.println("PkStockId " +PkStockId);
					System.out.println("quantity " +quantity);

					Double updatequnty = (double) (quantity1 - Double.parseDouble(quantity));
					System.out.println("updatequnty " +updatequnty);

					Stock Stock = (Stock) session1.load(Stock.class, new Long(PkStockId));

					Stock.setQuantity(updatequnty);

					session1.saveOrUpdate(Stock);
					transaction1.commit();
					System.out.println("Success ");	 
				}

			}
			catch(RuntimeException e){
				try{
					transaction1.rollback();
				}catch(RuntimeException rbe)
				{
					Log.error("Couldn't roll back tranaction",rbe);
				}	
			}finally{
				hbu1.closeSession(session1);
			}
		}
	}	

	//Adding Cash Fertilizer Billing
	
	public void fertilizerCashBilling(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		Long billNoForPdf;

		FertilizerBillDao dao = new FertilizerBillDao();
		List bill = dao.getCustomerBill();

		for(int i=0;i<bill.size();i++){
			CustomerBillBean sa=(CustomerBillBean)bill.get(i);
			customerBill= sa.getBillNo();
			System.out.println(customerBill);
			customerBill++;
			System.out.println(customerBill);

		}
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println(count);

		FertilizerBillBean bean = new FertilizerBillBean();
		for(int i =0 ; i<count;i++)
		{

			if(customerBill == null){
				bean.setBillNo(1l);
				billNoForPdf = 1l;
			}
			else
			{
				bean.setBillNo(customerBill);
				billNoForPdf = customerBill;
			}
			
			String paycusttype = request.getParameter("paycusttype");
			String custname = request.getParameter("custname");
			String customerName = request.getParameter("customerName");
			String village = request.getParameter("village");
			String contactNum = request.getParameter("contactNo");
			String aadhar = request.getParameter("aadhar");
			String billdate = request.getParameter("billdate");
			String transExpense = request.getParameter("transExpense");
			String hamaliExpense = request.getParameter("hamaliExpense");
			String total = request.getParameter("total");
			String grossTotal = request.getParameter("grossTotal");
			String fkCustomerId = request.getParameter("fkCreditCustomerID");
			String customerHiddenName = request.getParameter("creditCustomerHiddenName");

			String creditCustomerName = request.getParameter("creditCustomerName");
			String customertype=request.getParameter("customertype");
			
			bean.setPayCustType(paycusttype);
			bean.setCustname(custname);
			bean.setCustomertype(customertype);
			bean.setNewbalance_status(Double.parseDouble(grossTotal));
			
			if(fkCustomerId != null){
				bean.setFkCreditCustomerId(Long.parseLong(fkCustomerId));
			}
			else{
				bean.setFkCreditCustomerId(0l);
			}
			if(customerHiddenName != null){
				bean.setCustomerHiddenName(customerHiddenName);
			}
			else{
				bean.setCustomerHiddenName("N/A");
			}

			if(customerName != null){
				bean.setCustomerName(customerName);
			}
			else{
				bean.setCustomerName("N/A");
			}
			if(village == null){

				bean.setVillage("N/A");
			}
			else{
				bean.setVillage(village);
			}
			if(contactNum == null){
				bean.setContact(0l);

			}
			else{
				bean.setContact(Long.parseLong(contactNum));
			}
			if(aadhar == null){

				bean.setAadhar(0l);
			}
			else{
				bean.setAadhar(Long.parseLong(aadhar));
			}
			if(transExpense != null){
				bean.setTransExpense(Double.parseDouble(transExpense));
			}
			else{
				bean.setTransExpense(0.0);
			}
			if(hamaliExpense != null){
				bean.setHamaliExpense(Double.parseDouble(hamaliExpense));
			}
			else{
				bean.setHamaliExpense(0.0);
			}
			if(total != null){
				bean.setTotal(Double.parseDouble(total));
			}
			else{
				bean.setTotal(0.0);
			}
			if(grossTotal != null){
				bean.setGrossTotal(Double.parseDouble(grossTotal));
			}
			else{
				bean.setGrossTotal(0.0);
			}

			// Grid Content

			String catId = request.getParameter("cat_id"+i);
			String proName = request.getParameter("itemName"+i);
			String quantity = request.getParameter("quantity"+i);
			String unitName = request.getParameter("unitName"+i);
			String mrp = request.getParameter("mrp"+i);
			String salePrice = request.getParameter("salePrice"+i);
			String totalPerProductInGrid = request.getParameter("total"+i);
			String company = request.getParameter("companyName"+i);
			String weight = request.getParameter("weight"+i);
			String hsn = request.getParameter("hsn"+i);
			String cGst = request.getParameter("cGst"+i);
			String sGst = request.getParameter("sGst"+i);
			String iGst = request.getParameter("iGst"+i);
			Double taxPercentage = (Double.parseDouble(cGst)) + (Double.parseDouble(sGst));
			if(catId != null){
				bean.setCatId(Long.parseLong(catId));
			}
			else{
				bean.setCatId(0l);
			}
			if(proName != null){
				bean.setProductName(proName);
			}
			else{
				bean.setProductName("N/A");
			}
			if(hsn != null){
				bean.setHsn(hsn);
			}
			else{
				bean.setHsn("N/A");
			}
			if(quantity != null){
				bean.setQuantity(Long.parseLong(quantity));
			}
			else{
				bean.setQuantity(0l);
			}

			if(unitName != null){
				bean.setUnit(unitName);
			}
			else{
				bean.setUnit("N/A");
			}
			
			if(quantity != null){
				bean.setQuantityAfterReturn(Long.parseLong(quantity));
			}
			else{
				bean.setQuantityAfterReturn(0l);
			}

			if(mrp != null){
				bean.setMrp(Double.parseDouble(mrp));
			}
			else{
				bean.setMrp(0.0);
			}
			if(salePrice != null){
				bean.setSalePrice(Double.parseDouble(salePrice));
			}
			else{
				bean.setSalePrice(0.0);
			}
			if(totalPerProductInGrid != null){
				bean.setTotalInGrid(Double.parseDouble(totalPerProductInGrid));
			}
			else{
				bean.setTotalInGrid(0.0);
			}
			if(taxPercentage != null){
				bean.setTaxPercentage(taxPercentage);
			}
			else{
				bean.setTaxPercentage(0.0);
			}
			if(iGst != null){
				bean.setiGst(Double.parseDouble(iGst));
			}
			else{
				bean.setiGst(0.0);
			}

			if(company != null){
				bean.setCompany(company);
			}
			else{
				bean.setCompany("N/A");
			}
			if(weight != null){
				bean.setWeight(Double.parseDouble(weight));
			}
			else{
				bean.setWeight(0.0);
			}
			if(cGst.equals("0")){
				double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(iGst))));
				double netPrice = Double.parseDouble(salePrice) - igstAmt;
				bean.setNewSalePrice(netPrice);
			}
			if(iGst.equals("0")){
				double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + (taxPercentage))));
				double netPrice = Double.parseDouble(salePrice) - igstAmt;
				bean.setNewSalePrice(netPrice);
			}

			//payment mode

			String paymentMode = request.getParameter("paymentMode");
			String checkNum = request.getParameter("chequeNum");
			String nameOnCheck = request.getParameter("nameOnCheck");
			String cardNo = request.getParameter("cardNum");
			String accNo = request.getParameter("accNum");
			String bankName = request.getParameter("bankName");

			if(customerHiddenName == null){
				System.out.println("paymentMode is "+paymentMode);
				if(paymentMode==null){
					bean.setPaymentMode("N/A");
				}
				else{
					bean.setPaymentMode(paymentMode);
				}

				if(paymentMode.equals("cheque")){
					if(checkNum != null){
						bean.setCheckNo(Long.parseLong(checkNum));
					}
					else{
						bean.setCheckNo(0l);
					}
					if(nameOnCheck != null){
						bean.setNameOnCheck(nameOnCheck);
					}
					else{
						bean.setNameOnCheck("N/A");
					}

				}

				else if(paymentMode.equals("card")){
					if(cardNo != null){
						bean.setCardNo(Long.parseLong(cardNo));
					}
					else{
						bean.setCardNo(0l);
					}
				}

				else if(paymentMode.equals("neft")){

					if(accNo != null){
						bean.setAccNo(accNo);
					}
					else{
						bean.setAccNo("N/A");
					}
					if(bankName != null){
						bean.setBankName(bankName);
					}
					else{
						bean.setBankName("N/A");
					}
				}
			}	

			Double gstPerc=taxPercentage;
			Double igstPerc=Double.parseDouble(iGst);
			if(gstPerc != 0.0){
				Double taxAmnt=((gstPerc/100)*Double.parseDouble(total));
				bean.setTaxAmount(taxAmnt);
			}
			else if(igstPerc != 0.0){
				Double taxAmnt=((igstPerc/100)*Double.parseDouble(total));
				bean.setTaxAmount(taxAmnt);
			}	

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			//Date dateobj = new Date();
			//System.out.println(dateFormat1.format(dateobj));
			Date saledate=dateFormat1.parse(billdate);
			
			bean.setInsertDate(saledate);
			dao.addFertilizerBillingInDAO(bean);

			
			
/*----------- Update quantity in Product_Stock_Details Table --------------------*/
	        
			HibernateUtility hbu3 = HibernateUtility.getInstance();
			Session session3 = hbu3.getHibernateSession();
			
			System.out.println("=====proname"+proName);
			System.out.println("=====company"+company);
			System.out.println("=====expiry"+weight);
			//Query to get latest paid amount
			Query query = session3.createSQLQuery("SELECT pk_stock_id,quantity from product_stock_details where product_name=:proName and company_name=:company and packing=:weight");
			query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);
			
			System.out.println("====proName p1 : "+proName);
			System.out.println("====company p2:"+company);
			System.out.println("====weight p3:"+weight);
			List<Object[]> list = query.list();
			
			System.out.println("========List :"+list);
			System.out.println("Calc total");
			String pkStockId="";
			Double qty=0.0d;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
			    pkStockId = objects[0].toString();
			    qty=Double.parseDouble(objects[1].toString());
				
			    System.out.println("------------ StockID"+pkStockId);
	            //updatedqty = Double.valueOf(qty);
				System.out.println(qty+"  bal");
			}
			
			Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        SessionFactory factory = configuration.buildSessionFactory();
	        Session session = factory.openSession();
	       
			Transaction transaction = session.beginTransaction();
			
			ProductStockDetailsDao dao2=new ProductStockDetailsDao();
			List allpro=dao2.getAllEntry();
		
	        
	        System.out.println("List size"+allpro.size());
			
	        for(int j=0;j<allpro.size();j++){
	        	
	        	
	        ProductStockDetailsBean psd = (ProductStockDetailsBean)allpro.get(j);
			Long pk_stock_id=psd.getPk_stock_id();
	       
			
			System.out.println("=======pkStockId :"+pkStockId);
			System.out.println("=======pk_stock_id :"+pk_stock_id);
			
			if(pkStockId.equals(String.valueOf(pk_stock_id)))
			{
				Date updateDate=new Date();
			String quantity1 = request.getParameter("quantity"+i);
			Double uquantity=Double.parseDouble(quantity1);
					
				Double	updatedqty=qty-uquantity;
				System.out.println("==================update qty"+updatedqty);
				Long l = (new Double(updatedqty)).longValue();
				System.out.println("inside if");
				
				ProductStockDetailsBean updatequantity = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class, new Long(pkStockId));
				updatequantity.setQuantity(l);
				updatequantity.setUpdateDate(updateDate);
			 
				session.saveOrUpdate(updatequantity);
				transaction.commit();
				System.out.println("updated sucessfully");
			}
	      
	    }
	        /*-----------END OF  Update quantity in Product_Stock_Details Table --------------------*/

			

			//code for set billno and name in application context for BIll PDF

			HttpSession billNoSession = request.getSession();
			billNoSession.setAttribute("fertilizerBillNo",billNoForPdf);
			billNoSession.setAttribute("creditCustomerName",creditCustomerName);

			System.out.println("---------Bill No for pdf in session::"+ billNoForPdf + "----------------");

			//stock minus from stock detail table
			HibernateUtility hbu1=null;
			Session session1=null;
			Transaction transaction1 = null;

			try
			{
				Long PkStockId;
				Double quantity1;
				hbu1 = HibernateUtility.getInstance();
				session1 = hbu1.getHibernateSession();
				transaction1 = session1.beginTransaction();

				Query query1 = session1.createSQLQuery("select PkStockId , Quantity from stock_detail where ProductName=:product_name AND CompanyName=:company_Name And Weight =:weight");
				query1.setParameter("product_name", proName);
				query1.setParameter("company_Name", company);
				query1.setParameter("weight", weight);

				List<Object[]> list1 = query1.list();

				for (Object[] object : list1) {
					System.out.println(Arrays.toString(object));  
					PkStockId = Long.parseLong(object[0].toString());
					quantity1 = Double.parseDouble(object[1].toString());
					System.out.println("PkStockId " +PkStockId);
					System.out.println("quantity " +quantity);

					Double updatequnty = (double) (quantity1 - Double.parseDouble(quantity));
					System.out.println("updatequnty " +updatequnty);

					Stock Stock = (Stock) session1.load(Stock.class, new Long(PkStockId));

					Stock.setQuantity(updatequnty);

					session1.saveOrUpdate(Stock);
					transaction1.commit();
					System.out.println("Success ");	 
				}

			}
			catch(RuntimeException e){
				try{
					transaction1.rollback();
				}catch(RuntimeException rbe)
				{
					Log.error("Couldn't roll back tranaction",rbe);
				}	
			}finally{
				hbu1.closeSession(session1);
			}
		}
	}	

	
	
	
	
	
	
	public List getSaleDetailsBYDate(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getSaleDetailsDateWise(fDate,tDate);

		return saleList;
	}

	public List getSaleDetailsBYSingalDate(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fDate");
		System.out.println(fDate+"vxvdfvdf");

		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getSaleDetailsBySingalDateWise(fDate);

		return saleList;
	}

	public void billGeneration(HttpServletRequest request, HttpServletResponse response) {
		String billNo = request.getParameter("billNo");
		String custtype = request.getParameter("custtype");
		String paycusttype = request.getParameter("paycusttype");
		
		
		System.out.println("----------------Bill No before session create::"+billNo);
		HttpSession session3 = request.getSession();
		Long billNo2 = Long.parseLong(billNo);
		session3.setAttribute("BillNoForCopy", billNo2);
		session3.setAttribute("custtype", custtype);
		session3.setAttribute("paycusttype", paycusttype);
		System.out.println("----------------Bill No before session create::"+session3.getAttribute("BillNoForCopy"));

	}


	public void CreditCustmerBillCOPY(HttpServletRequest request, HttpServletResponse response) {
		String billNum = request.getParameter("billNo");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||"+billNum);
		HttpSession session = request.getSession();
		session.setAttribute("billNum", billNum);
		
	}


	public List getCreditAmountByCreditCust(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getAmountByCreditCustPayment(fDate,tDate);

		return saleList;
	}

	public List getAllCreditAmtFromBilling(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");
		String cat = request.getParameter("cat");
		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getAmountByCustBilling(fDate,tDate,cat);

		return saleList;
	}


	public List getPaidAmountToSupplier(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getPaidAmountToSupplier(fDate,tDate);

		return saleList;
	}


	public List getPaidAmountToEmployee(HttpServletRequest request, HttpServletResponse response) 
	{
		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<SaleReports> saleList = dao.getPaidAmountToEmployee(fDate,tDate);

		return saleList;
	}


	public void fertilizerBilling_28_5_17(HttpServletRequest request,
			HttpServletResponse response) {

		FertilizerBillDao dao = new FertilizerBillDao();

		List bill = dao.getCustomerBill();

		for(int i=0;i<bill.size();i++){
			CustomerBillBean sa=(CustomerBillBean)bill.get(i);
			customerBill= sa.getBillNo();
			System.out.println(customerBill);

			customerBill++;
			System.out.println(customerBill);

		}
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println(count);


		FertilizerBillBean bean = new FertilizerBillBean();

		for(int i =0 ; i<count;i++)
		{

			if(customerBill == null){
				bean.setBillNo(1l);
			}
			else
			{
				bean.setBillNo(customerBill);	
			}

			//Out Of Grid Content
			String customerName = request.getParameter("customerName");
			String village = request.getParameter("village");
			String contactNum = request.getParameter("contactNo");
			String aadhar = request.getParameter("aadhar");
			String transExpense = request.getParameter("transExpense");
			String hamaliExpense = request.getParameter("hamaliExpense");
			String total = request.getParameter("total");
			String grossTotal = request.getParameter("grossTotal");
			String fkCustomerId = request.getParameter("fkCreditCustomerID");
			String customerHiddenName = request.getParameter("creditCustomerHiddenName");


			if(fkCustomerId != null){
				bean.setFkCreditCustomerId(Long.parseLong(fkCustomerId));
			}
			else{
				bean.setFkCreditCustomerId(0l);
			}

			if(customerHiddenName != null){
				bean.setCustomerHiddenName(customerHiddenName);
			}
			else{
				bean.setCustomerHiddenName("N/A");
			}

			if(customerName != null){
				bean.setCustomerName(customerName);
			}
			else{
				bean.setCustomerName("N/A");
			}
			if(village != null){
				bean.setVillage(village);
			}
			else{
				bean.setVillage("N/A");
			}
			if(contactNum != null){
				bean.setContact(Long.parseLong(contactNum));
			}
			else{
				bean.setContact(0l);
			}
			if(aadhar != null){
				bean.setAadhar(Long.parseLong(aadhar));
			}
			else{
				bean.setAadhar(0l);
			}
			if(transExpense != null){
				bean.setTransExpense(Double.parseDouble(transExpense));
			}
			else{
				bean.setTransExpense(0.0);
			}
			if(hamaliExpense != null){
				bean.setHamaliExpense(Double.parseDouble(hamaliExpense));
			}
			else{
				bean.setHamaliExpense(0.0);
			}
			if(total != null){
				bean.setTotal(Double.parseDouble(total));
			}
			else{
				bean.setTotal(0.0);
			}
			if(grossTotal != null){
				bean.setGrossTotal(Double.parseDouble(grossTotal));
			}
			else{
				bean.setGrossTotal(0.0);
			}


			// Grid Content
			String proName = request.getParameter("itemName"+i);
			String quantity = request.getParameter("quantity"+i);
			String mrp = request.getParameter("mrp"+i);
			String salePrice = request.getParameter("salePrice"+i);
			String totalPerProductInGrid = request.getParameter("total"+i);
			String taxPercentage = request.getParameter("vatPercentage"+i);
			String company = request.getParameter("companyName"+i);
			String weight = request.getParameter("weight"+i);

			if(proName != null){
				bean.setProductName(proName);
			}
			else{
				bean.setProductName("N/A");
			}

			if(quantity != null){
				bean.setQuantity(Long.parseLong(quantity));
			}
			else{
				bean.setQuantity(0l);
			}
			if(mrp != null){
				bean.setMrp(Double.parseDouble(mrp));
			}
			else{
				bean.setMrp(0.0);
			}
			if(salePrice != null){
				bean.setSalePrice(Double.parseDouble(salePrice));
			}
			else{
				bean.setSalePrice(0.0);
			}
			if(totalPerProductInGrid != null){
				bean.setTotalInGrid(Double.parseDouble(totalPerProductInGrid));
			}
			else{
				bean.setTotalInGrid(0.0);
			}
			if(taxPercentage != null){
				bean.setTaxPercentage(Double.parseDouble(taxPercentage));
			}
			else{
				bean.setTaxPercentage(0.0);
			}
			if(company != null){
				bean.setCompany(company);
			}
			else{
				bean.setCompany("N/A");
			}
			if(weight != null){
				bean.setWeight(Double.parseDouble(weight));
			}
			else{
				bean.setWeight(0.0);
			}


			//payment mode
			String paymentMode = request.getParameter("paymentMode");
			String checkNum = request.getParameter("chequeNum");
			String nameOnCheck = request.getParameter("nameOnCheck");
			String cardNo = request.getParameter("cardNum");
			String accNo = request.getParameter("accNum");
			String bankName = request.getParameter("bankName");

			if(customerHiddenName == null){
				System.out.println("paymentMode is "+paymentMode);
				if(paymentMode==null){
					bean.setPaymentMode("N/A");
				}
				else{
					bean.setPaymentMode(paymentMode);
				}

				if(paymentMode.equals("cheque")){
					if(checkNum != null){
						bean.setCheckNo(Long.parseLong(checkNum));
					}
					else{
						bean.setCheckNo(0l);
					}
					if(nameOnCheck != null){
						bean.setNameOnCheck(nameOnCheck);
					}
					else{
						bean.setNameOnCheck("N/A");
					}

				}

				else if(paymentMode.equals("card")){
					if(cardNo != null){
						bean.setCardNo(Long.parseLong(cardNo));
					}
					else{
						bean.setCardNo(0l);
					}
				}

				else if(paymentMode.equals("neft")){

					if(accNo != null){
						bean.setAccNo(accNo);
					}
					else{
						bean.setAccNo("N/A");
					}
					if(bankName != null){
						bean.setBankName(bankName);
					}
					else{
						bean.setBankName("N/A");
					}
				}
			}	



			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));

			bean.setInsertDate(dateobj);


			dao.addFertilizerBillingInDAO(bean);

			/*		// update (minus stock from good receive)
		HibernateUtility hbu = HibernateUtility.getInstance();
		 Session session = hbu.getHibernateSession();
		 Transaction transaction = session.beginTransaction();
		try
		{
		String pkGoodsReceiveId = request.getParameter("PkGoodreceiveId"+i);
		System.out.println(pkGoodsReceiveId+"pkGoodsReceiveId in helper");
		 Query query = session.createSQLQuery("select quantity , dupQuantity from goods_receive where pk_goods_receive_id ="+pkGoodsReceiveId);
		// query.setParameter("pkGoodsReceiveId", pkGoodsReceiveId);

		 List<Object[]> list = query.list();

		  for (Object[] object : list) {
			  System.out.println(Arrays.toString(object));  
			  Double orgQuantity = Double.parseDouble(object[0].toString());
			  Double dupquantity = Double.parseDouble(object[1].toString());
			  System.out.println("orgQuantity " +orgQuantity);
			  System.out.println("dupquantity " +dupquantity);

		    Double updatequantity = (double)(dupquantity -  Double.parseDouble(quantity));
			System.out.println("after minus qunt : "+ quantity);
	        GoodsReceiveBean updateStock = (GoodsReceiveBean) session.get(GoodsReceiveBean.class, new Long(pkGoodsReceiveId));

	        updateStock.setDupQuantity(updatequantity);

	        session.saveOrUpdate(updateStock);
	        transaction.commit();
		  }
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
			}*/




			//stock minus from stock detail table
			HibernateUtility hbu1=null;
			Session session1=null;
			Transaction transaction1 = null;

			try
			{
				Long PkStockId;
				Double quantity1;
				hbu1 = HibernateUtility.getInstance();
				session1 = hbu1.getHibernateSession();
				transaction1 = session1.beginTransaction();

				Query query1 = session1.createSQLQuery("select PkStockId , Quantity from stock_detail where ProductName=:product_name AND CompanyName=:company_Name And Weight =:weight");
				query1.setParameter("product_name", proName);
				query1.setParameter("company_Name", company);
				query1.setParameter("weight", weight);

				List<Object[]> list1 = query1.list();

				for (Object[] object : list1) {
					System.out.println(Arrays.toString(object));  
					PkStockId = Long.parseLong(object[0].toString());
					quantity1 = Double.parseDouble(object[1].toString());
					System.out.println("PkStockId " +PkStockId);
					System.out.println("quantity " +quantity);

					Double updatequnty = (double) (quantity1 - Double.parseDouble(quantity));
					System.out.println("updatequnty " +updatequnty);

					Stock Stock = (Stock) session1.load(Stock.class, new Long(PkStockId));

					Stock.setQuantity(updatequnty);

					session1.saveOrUpdate(Stock);
					transaction1.commit();
					System.out.println("Success ");	 
				}

			}
			catch(RuntimeException e){
				try{
					transaction1.rollback();
				}catch(RuntimeException rbe)
				{
					Log.error("Couldn't roll back tranaction",rbe);
				}	
			}finally{
				hbu1.closeSession(session1);
			}


		}



	}

	public void normalCustFerilizerBillCopy(HttpServletRequest request, HttpServletResponse response) {
		String billNo = request.getParameter("billNo");
		String custname = request.getParameter("custname");
		String custtype = request.getParameter("custtype");
		String paycusttype = request.getParameter("paycusttype");
		System.out.println("----------------Bill No before session create::"+billNo);
		HttpSession session3 = request.getSession();
		Long billNo2 = Long.parseLong(billNo);
		session3.setAttribute("NormalCustFertilizerBillNo", billNo2);
		session3.setAttribute("custname", custname);
		session3.setAttribute("custtype", custtype);
		session3.setAttribute("paycusttype", paycusttype);
		System.out.println("----------------Bill No before session create::"+session3.getAttribute("NormalCustFertilizerBillNo"));

	}


	public void fertilizerCreditCustmerBillCOPY(HttpServletRequest request, HttpServletResponse response) {
		String billNo = request.getParameter("creditCustbillNo");
		System.out.println("----------------Credit cust Bill No before session create::"+billNo);
		HttpSession session3 = request.getSession();
		Long billNo2 = Long.parseLong(billNo);
		session3.setAttribute("FertilizerCrditCustBillNo", billNo2);
		System.out.println("----------------Credit cust Bill No before session create::"+session3.getAttribute("FertilizerCrditCustBillNo"));

	}


	public List getTaxDetailsAsPerCategoryFromSaleBetTwoDate(
			HttpServletRequest request, HttpServletResponse response) {


		String cat = request.getParameter("cat");
		String fDate = request.getParameter("fDate");
		String sDate = request.getParameter("sDate");
		System.out.println(cat+"Category in Helper");
		System.out.println(fDate+"fDate in Helper");
		System.out.println(sDate+"sDate in Helper");

		Map<Long,GetProductDetails> map = new HashMap<Long,GetProductDetails>();

		FertilizerBillDao dao = new FertilizerBillDao();
		List<GetProductDetails> expList = dao.getPurchaseDetailsForCategoryBetTwoDate(cat,fDate,sDate);


		return expList;

	}


	public Map getAllFertiIetmByBillNo(String bill_no) {
		// TODO Auto-generated method stub
		FertilizerBillDao dao = new FertilizerBillDao();
		List list = dao.getAllFertiIetmByBillNo(bill_no);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			FertiSaleReturn bean = new FertiSaleReturn();


			bean.setPkfertilizerBillId((BigInteger)o[0]);
			bean.setFkGoodsReceive((BigInteger)o[1]);
			bean.setCatId((BigInteger)o[2]);
			bean.setCustomerName(o[3].toString());
			bean.setProductName(o[4].toString());
			bean.setCompany(o[5].toString());
			bean.setWeight(Double.parseDouble(o[6].toString()));
			bean.setSalePrice(Double.parseDouble(o[7].toString()));
			bean.setMrp(Double.parseDouble(o[8].toString()));
			//bean.setQuantity(0l);
			bean.setAvailbleQuantity((BigInteger)o[9]);
			bean.setTotalInGrid(Double.parseDouble(o[10].toString()));
			bean.setBarcode((BigInteger)o[11]);
			bean.setAadhar((BigInteger)o[12]);
			bean.setInsertDate(o[13].toString());
			bean.setCustomerHiddenName(o[14].toString());
			bean.setTaxPercentage(Double.parseDouble(o[15].toString()));
			/*bean.setFk_unit_id((BigInteger)o[5]);*/
			//bean.setQuantity(0l);
			//System.out.println("***************"+o[0]+"\t"+o[5]);
			map1.put(bean.getPkfertilizerBillId(),bean);
		}
		return map1;
	}


	public void saleReturnAsPerBillNo(HttpServletRequest request,
			HttpServletResponse response) {

		// TODO Auto-generated method stub
		Integer count = Integer.parseInt(request.getParameter("count"));
		for(int i =0 ; i <count;i++)
		{


			Long billNoForPdf;

			FertilizerBillDao dao = new FertilizerBillDao();

			List bill = dao.getCustomerBillNo();

			for(int j=0;j<bill.size();j++){
				CustomerBillBean sa=(CustomerBillBean)bill.get(j);
				customerBill= sa.getBillNo();
				System.out.println(customerBill);

				customerBill++;
				System.out.println(customerBill);

			}
			Integer count1 = Integer.parseInt(request.getParameter("count"));
			System.out.println(count);

			FertilizerBillBean bean1 = new FertilizerBillBean();

			for(int k =0 ; k<count;k++)
			{

				if(customerBill == null){
					bean1.setBillNo(1l);
					billNoForPdf = 1l;
				}
				else
				{
					bean1.setBillNo(customerBill);
					billNoForPdf = customerBill;
				}
			}

			HttpSession session1 = request.getSession();
			String bill_no = request.getParameter("bill_no");
			session1.setAttribute("bill_no", bill_no);

			System.out.println("===========bill No :"+bill_no);
			//String bill_no = request.getParameter("bill_no");
			String pkfertilizerBillId = request.getParameter("pkfertilizerBillId"+i); 
			String availbleQuantity = request.getParameter("availbleQuantity"+i);
			String productName = request.getParameter("productName"+i);
			String company = request.getParameter("company"+i);
			String weight = request.getParameter("weight"+i);
			String quantity = request.getParameter("quantity1"+i);
			String salePrice = request.getParameter("salePrice"+i);
			String mrp = request.getParameter("mrp"+i);
			String taxPercentage = request.getParameter("taxPercentage"+i);
			String aadhar = request.getParameter("aadhar"+i);
			String customerName = request.getParameter("customerName"+i);
			String saleDate = request.getParameter("insertDate"+i);
			System.out.println("currnt qunt : "+ availbleQuantity);
			System.out.println("return qunt : "+ quantity);
			System.out.println("pkfertilizerBillId  : "+ pkfertilizerBillId);
			HibernateUtility hbu=null;
			Session session = null;
			Transaction transaction = null;

			try{
				Double grossTotal=0.0;
				Double stockQuantity =0.0;
				String QuantityFromStock=null;
				Double newStockQuantity = 0.0;
				Long pkStockId = 0l;
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				transaction = session.beginTransaction();
				List<Object[]> list  = null;
				List<Object[]> list2  = null;
				List<StockDetail> stockList = null;
				List<FertilizerBillBean> fertiList = null;
				Long remainingQuantity  = (Long)(Long.parseLong(availbleQuantity) -  Long.parseLong(quantity));
				System.out.println("remaining qunt : "+ remainingQuantity);
				FertilizerBillBean updateStock = (FertilizerBillBean) session.get(FertilizerBillBean.class, new Long(pkfertilizerBillId));
				updateStock.setReturnQuantity(Long.parseLong(quantity));    
				updateStock.setQuantityAfterReturn(remainingQuantity);


				//update stock
				Query query = session.createSQLQuery("select Quantity,PkStockId FROM stock_detail WHERE ProductName='"+productName+"' AND CompanyName='"+company+"' and Weight ="+weight);

				list = query.list();
				stockList = new ArrayList<StockDetail>();
				for (Object[] objects : list) {
					QuantityFromStock = objects[0].toString();
					stockQuantity = Double.parseDouble(QuantityFromStock);

					String pkstock = objects[1].toString();
					pkStockId = Long.parseLong(pkstock);
				}

				Double q = Double.parseDouble(quantity);
				newStockQuantity = stockQuantity + q ;
				Query query1 = session.createSQLQuery("UPDATE stock_detail SET Quantity ="+newStockQuantity+" WHERE PkStockId="+pkStockId);
				query1.executeUpdate();

				//Update fertilizer bill table
				//fetch gross total as per bill number(fkFertilizerBillId)

				Query query2 = session.createSQLQuery("SELECT gross_total,bill_no FROM fertilizer_billing WHERE pk_fertilizer_bill_id="+pkfertilizerBillId);
				list2 = query2.list();
				fertiList = new ArrayList<FertilizerBillBean>();
				for (Object[] objects : list2) {
					grossTotal = Double.parseDouble(objects[0].toString());

				}

				Double salePriceFromGrid = Double.parseDouble(salePrice);
				Double tax = Double.parseDouble(taxPercentage);
				Double taxAmount = (tax/100)*salePriceFromGrid;
				Double newSalePrice = salePriceFromGrid + taxAmount;
				Double total = Double.parseDouble(quantity) * newSalePrice;
				Double newGrossTotal = grossTotal - total;
				System.out.println("grossTotal = = ="+grossTotal);
				System.out.println("taxAmount = = ="+taxAmount);
				System.out.println("newSalePrice = = ="+newSalePrice);
				System.out.println("newGrossTotal = = ="+newGrossTotal);
				updateStock.setTotalAfterSaleReturn(newGrossTotal);
				updateStock.setReturnAmount(total);
				session.saveOrUpdate(updateStock);
				//add Sale Return to sale_return table
				SaleReturnBean bean = new SaleReturnBean();
				bean.setBillNo(Long.parseLong(bill_no));
				bean.setAadhar(Long.parseLong(aadhar));
				bean.setCustomerName(customerName);
				bean.setFkFertilizerBillId(Long.parseLong(pkfertilizerBillId));
				bean.setProductName(productName);
				bean.setFkCatId(1l);
				bean.setSalePrice(Double.parseDouble(salePrice));
				bean.setMrp(Double.parseDouble(mrp));
				bean.setTaxPercentage(Double.parseDouble(taxPercentage));
				bean.setAvailableQuantity(Double.parseDouble(availbleQuantity));
				bean.setReturnQuantity(Double.parseDouble(quantity));
				bean.setWeight(Double.parseDouble(weight));
				bean.setCompany(company);
				bean.setReturnAmount(total);
				bean.setSaleDate(saleDate);
				bean.setFkPesticideBillId(0l);
				bean.setFkSeedBillId(0l);
				bean.setBatchNumber("N/A");
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				Date dateobj = new Date();
				System.out.println(dateFormat1.format(dateobj));
				bean.setReturnDate(dateobj);

				session.save(bean);
				transaction.commit(); 
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


	}


	public Map getSaleByCash() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCashSaleDetail();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getSaleByCash(),bean);

		}
		bean.setSaleByCash(total);
		return map;

	}


	public Map getSaleByCredit() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCreditSaleDetail();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getSaleByCredit(),bean);

		}
		bean.setSaleByCredit(total);
		return map;


	}


	public Map getSaleByCashForSeed() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCashSaleDetailForSeed();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getSeedSaleByCash(),bean);

		}
		bean.setSeedSaleByCash(total);
		return map;


	}


	public Map getSaleByCreditForSeed() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCreditSaleDetailForSeed();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getSeedSaleByCredit(),bean);

		}
		bean.setSeedSaleByCredit(total);
		return map;


	}


	public Map getSaleByCashForPesticide() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCashSaleDetailForPesti();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getPesticideSaleByCash(),bean);

		}
		bean.setPesticideSaleByCash(total);
		return map;


	}


	public Map getSaleByCreditForPesticide() {
		Double total = 0.0;
		DayCloseReports bean = new DayCloseReports();
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getCreditSaleDetailForPesti();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;

			map.put(bean.getPesticideSaleByCredit(),bean);

		}
		bean.setPesticideSaleByCredit(total);
		return map;



	}


	public Map getPurchaseAmountForFerti() {
		Double total = 0.0;

		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getpurchaseForFerti();
		System.out.println("list size in goods receive purchase total amt = = ="+list.size());
		Map  map =  new HashMap();
		DayCloseReports bean = new DayCloseReports();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			Double tot = o.doubleValue();
			total = total + tot;
			map.put(bean.getFertilizerPurchase(),bean);
		}
		bean.setFertilizerPurchase(total);
		return map;

	}


	public Map getPurchaseAmountForSeed() {
		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getpurchaseForSeed();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			DayCloseReports bean = new DayCloseReports();
			//System.out.println(Arrays.toString(o));
			bean.setSeedPurchase(o);
			map.put(bean.getSeedPurchase(),bean);

		}
		return map;
	}


	public Map getPurchaseAmountForPesti() {

		FertilizerBillDao dao = new FertilizerBillDao();
		List list= dao.getpurchaseForPesti();
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			BigDecimal o = (BigDecimal)list.get(i);
			DayCloseReports bean = new DayCloseReports();
			//System.out.println(Arrays.toString(o));
			bean.setPesticidePurchase(o);
			map.put(bean.getPesticidePurchase(),bean);

		}
		return map;

	}
}





