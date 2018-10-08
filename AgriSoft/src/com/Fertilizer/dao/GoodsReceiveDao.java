package com.Fertilizer.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.BillBean;
import com.Fertilizer.bean.CustomerBean;
import com.Fertilizer.bean.GoodsReceiveDetail;
import com.Fertilizer.bean.ProductStockDetailsBean;
import com.Fertilizer.bean.PurchaseDeatilsResultBean;
import com.Fertilizer.bean.PurchaseDetailsFromGoodsReceive;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.bean.StockDetail;
import com.Fertilizer.hibernate.CategoryDetailsBean;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductDetailsBean;
import com.Fertilizer.hibernate.SaveDailyStock;
import com.Fertilizer.hibernate.SupplierDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class GoodsReceiveDao {


	public void addGoodsReceive(GoodsReceiveBean bean) {

		System.out.println("In gr DAo");
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();

		Long fk_supplier_id = bean.getSupplier();
		Long fk_product_id = bean.getPkPOId();
		Long fkCategoryId = bean.getFkCategoryId();

		SupplierDetailsBean supdetail = (SupplierDetailsBean) session.load(SupplierDetailsBean.class, fk_supplier_id);
		bean.setSupplierDetailsBean(supdetail);
		System.out.println("In gr DAo sup");
		System.out.println("In gr DAo godown");
		CategoryDetailsBean categoryDetailsBean = (CategoryDetailsBean) session.load(CategoryDetailsBean.class, fkCategoryId);
		bean.setCategoryDetailsBean(categoryDetailsBean);
		System.out.println("In gr DAo cat");
		ProductDetailsBean proDetail = (ProductDetailsBean) session.load(ProductDetailsBean.class, fk_product_id);
		bean.setProductDetailsBean(proDetail);

		System.out.println("Tx started");

		session.save(bean);
		transaction.commit();
		System.out.println("Successful");
		hbu.closeSession(session);
	}

	public List<CustomerBean> getAllItemDetails(String key){

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{

			System.out.println("shreemant");
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "SELECT pk_goods_receive_id , fk_supplier_id, fkCategoryId, product_name , sale_price, weight, dupQuantity, mrp, company_Name, barcodeNo, tax_percentage,batch_no,iGstPercentage,hsn FROM goods_receive WHERE barcodeNo ="+key;

			Query query=session.createSQLQuery(sqlQuery);
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				System.out.println("itemlist");
				bean.setPkGoodreceiveId(Long.parseLong(objects[0].toString()));
				bean.setSupplier_id(Long.parseLong(objects[1].toString()));
				bean.setCat_id(Long.parseLong(objects[2].toString()));
				bean.setItemName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setWeight(Double.parseDouble(objects[5].toString()));
				bean.setQuantity(0d);
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setCompanyName(objects[8].toString());
				bean.setBarcodeNo(Long.parseLong(objects[9].toString()));
				Double tax = Double.parseDouble(objects[10].toString());
				Double halfTax = tax/2;
				bean.setcGst(halfTax);
				bean.setsGst(halfTax);
				bean.setUnitName("Kg");
				bean.setBatchNumber(objects[11].toString());
				bean.setiGst(Double.parseDouble(objects[12].toString()));
				bean.setHsn(objects[13].toString());
				System.out.println("itemlist");

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}

		return itemlist;


	}

	//seed billing by barcode
	public List<CustomerBean> getPesticideDetailByBarocde(String key){

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{

			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "SELECT pk_goods_receive_id , fk_supplier_id, fkCategoryId, product_name , sale_price, weight, dupQuantity, mrp, company_Name, barcodeNo, tax_percentage,batch_no,hsn,iGstPercentage FROM goods_receive WHERE barcodeNo ="+key;

			Query query=session.createSQLQuery(sqlQuery);
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				System.out.println("itemlist");
				bean.setPkGoodreceiveId(Long.parseLong(objects[0].toString()));
				bean.setSupplier_id(Long.parseLong(objects[1].toString()));
				bean.setCat_id(Long.parseLong(objects[2].toString()));
				bean.setItemName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setWeight(Double.parseDouble(objects[5].toString()));
				bean.setQuantity(0d);
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setCompanyName(objects[8].toString());
				bean.setBarcodeNo(Long.parseLong(objects[9].toString()));
				Double tax = Double.parseDouble(objects[10].toString());
				if(tax != null || tax != 0){
					Double halfTax = tax/2;
					bean.setcGst(halfTax);
					bean.setsGst(halfTax);
					bean.setiGst(0.0);
				}
				Double iGst = Double.parseDouble(objects[13].toString());
				if(iGst != null || iGst != 0){
					bean.setcGst(0.0);
					bean.setsGst(0.0);
					bean.setiGst(iGst);
				}
				bean.setUnitName("Ml");
				bean.setBatchNumber(objects[11].toString());
				bean.setHsn(objects[12].toString());
				System.out.println("itemlist");

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}

		return itemlist;


	}


	//to get barcode no in goodrecive
	public List getLastBarcodeNo(){

		HibernateUtility hbu=null;
		Session session=null;
		List<BillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT pk_goods_receive_id , barcodeNo FROM goods_receive ORDER BY barcodeNo DESC LIMIT 1");

			List<Object[]> list = query.list();
			saleList= new ArrayList<BillBean>(0);
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				BillBean reports = new BillBean();
				reports.setBarcodeNo(Long.parseLong(object[1].toString()));
				saleList.add(reports);	 
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}finally
		{if(session!=null){
			session.close();	
		}
		}
		return saleList;	
	}


	public void updateProductStatus(Long prodctId ){
		System.out.println(prodctId+"PRODUCT ID");
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		Long status = 2l;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			String hql = "UPDATE product_details set status ="+status +"  " + 
					"WHERE pk_product_id =:prodctId";
			Query query = session.createSQLQuery(hql);
			query.setParameter("prodctId", prodctId);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);


			transaction.commit();
			System.out.println("Updated Successfully");
		}catch (Exception e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}

	public List getAllDcNumbersBySuppliers(String supplierId) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select p.dc_number,p.insert_date from purchase_order p where p.fk_supplier_id="+supplierId);
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

	public List getPODetailsForGoodsReceive(String dcNum, String supplier) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("select  p.fk_product_id, p.product_name, p.buy_price, p.sale_price, p.quantity, p.weight from purchase_order p where p.fk_supplier_id= " + supplier+"  "+" and"+ "  "+ "  p.dc_number= "+dcNum);

			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}

	public PurchaseDeatilsResultBean getPurchaseReportsBetweenTwoDates(
			String fDate, String tDate) {

		System.out.println(fDate+"first Date In dao");
		System.out.println(tDate+"Second Date In dao");
		HibernateUtility hbu=null;
		Session session=null;
		PurchaseDeatilsResultBean bean = new PurchaseDeatilsResultBean();
		PurchaseDetailsFromGoodsReceive lastbean = new PurchaseDetailsFromGoodsReceive();
		List<PurchaseDetailsFromGoodsReceive> purchaseList=null;
		double totalAmt = 0.0;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query2 = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount from goods_receive where purchaseDate between '" + fDate +"' and '"+tDate+"'");

			List<Object[]> list = query2.list();
			purchaseList= new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));
				totalAmt = totalAmt + Double.parseDouble(object[12].toString()) ;
				purchaseList.add(reports); 

			}
			bean.setList(purchaseList );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bean;
	}

	/*Upcoming expiry Date seed products*/
	
	public List upcomingExpirySeedProducts(){
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodsReceiveDetail> expiryList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
			Date dateobj = new Date();
			String todayDate = dateFormat1.format(dateobj);

			Query query2 = session.createSQLQuery("SELECT product_name, company_Name, weight, batch_no,expiry_date,dupQuantity FROM goods_receive WHERE DATEDIFF(expiry_date,"+todayDate+")<=10 AND DATEDIFF(expiry_date,"+todayDate+")>0");

			List<Object[]> list = query2.list();
			System.out.println("Expiry List size = ="+list.size());
			expiryList= new ArrayList<GoodsReceiveDetail>(0);

			for (Object[] o : list) {

				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(o[0].toString());
				reports.setCompany(o[1].toString());
				reports.setWeight(o[2].toString());
				reports.setBatchNumber(o[3].toString());
				reports.setExpiryDate(o[4].toString());
				reports.setStock(o[5].toString());
				expiryList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return expiryList;
	}

	/*Upcoming expiry Date pesticide products*/
	
	public List upcomingExpiryPesticideProducts(){
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodsReceiveDetail> expiryList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
			Date dateobj = new Date();
			String todayDate = dateFormat1.format(dateobj);

			Query query2 = session.createSQLQuery("SELECT product_name, company_Name, weight, batch_no,expiry_date,dupQuantity FROM goods_receive WHERE fkCategoryId=2 AND DATEDIFF(expiry_date,"+todayDate+")<=10 AND DATEDIFF(expiry_date,"+todayDate+")>0");

			List<Object[]> list = query2.list();
			System.out.println("Expiry List size = ="+list.size());
			expiryList= new ArrayList<GoodsReceiveDetail>(0);

			for (Object[] o : list) {

				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(o[0].toString());
				reports.setCompany(o[1].toString());
				reports.setWeight(o[2].toString());
				reports.setBatchNumber(o[3].toString());
				reports.setExpiryDate(o[4].toString());
				reports.setStock(o[5].toString());
				expiryList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return expiryList;
	}

	public List getAllProductForNotification()
	{
		List<GoodsReceiveDetail> List = new ArrayList<GoodsReceiveDetail> ();
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost:3306/fertilizer";

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		String todayDate = dateFormat1.format(dateobj);

		Connection conn = null;
		PreparedStatement  stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,"root","root");

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql;
			sql = "SELECT g.product_name, g.batch_no, g.expiry_date,i.stock FROM goods_receive g RIGHT JOIN item_stockforpestiandseed i ON g.batch_no = i.batchNo WHERE g.fkCategoryId in (2,3) AND DATEDIFF(g.expiry_date,"+todayDate+")<=10 AND DATEDIFF(g.expiry_date,"+todayDate+")>0";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(rs.getString(1));
				reports.setBatchNumber(rs.getString(2));
				reports.setExpiryDate(rs.getString(3).toString());
				reports.setStock(rs.getString(4).toString());

				List.add(reports); 

			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
		return List;
	}

	/*	Seed stock less than 10 for Notification*/
	
	public List getAllSeedAndPestiForStockNotification()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List<GoodsReceiveDetail> productList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("select product_name, company_name, packing,batchno,quantity from product_stock_details WHERE quantity < 10");

			List<Object[]> List = query.list();
			productList = new ArrayList<GoodsReceiveDetail>(0);

			System.out.println(List.size()+"List Size");

			for (Object[] object : List) {

				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(object[0].toString());
				reports.setCompany(object[1].toString());
				reports.setWeight(object[2].toString());
				reports.setBatchNumber(object[3].toString());
				reports.setStock(object[4].toString());
				productList.add(reports); 
				System.out.println(reports);

			}
		} catch (Exception e) {
			Log.error("Error in getAllProductForBilling", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return productList;
	}

	/*	Pesticide stock less than 10 for notification*/
	
	public List getPestiStockForStockNotification()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List<GoodsReceiveDetail> productList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("select ProductName, CompanyName, Weight,batch_number,Quantity from stock_detail WHERE Quantity < 10 AND FkCatId= 2");

			List<Object[]> List = query.list();
			productList = new ArrayList<GoodsReceiveDetail>(0);

			System.out.println(List.size()+"List Size");

			for (Object[] object : List) {

				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(object[0].toString());
				reports.setCompany(object[1].toString());
				reports.setWeight(object[2].toString());
				reports.setBatchNumber(object[3].toString());
				reports.setStock(object[4].toString());

				productList.add(reports); 
				System.out.println(reports);
			}
		} catch (Exception e) {
			Log.error("Error in getAllProductForBilling", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return productList;

	}

	public List getAllFertilizerForStockNotification()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List<GoodsReceiveDetail> productList = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createSQLQuery("select ProductName, CompanyName, Weight,Quantity from stock_detail WHERE Quantity < 10 AND FkCatId= 1");

			List<Object[]> List = query.list();
			productList = new ArrayList<GoodsReceiveDetail>(0);

			System.out.println(List.size()+"List Size");

			for (Object[] object : List) {

				GoodsReceiveDetail reports = new GoodsReceiveDetail();
				reports.setProductName(object[0].toString());
				reports.setCompany(object[1].toString());
				reports.setWeight(object[2].toString());
				reports.setStock(object[4].toString());
				productList.add(reports); 
				System.out.println(reports);

			}
		} catch (Exception e) {
			Log.error("Error in getAllProductForBilling", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return productList;
	}

	public List<StockDetail> getStockDetailsForReportAsGodown(String fkGodownId) {

		HibernateUtility hbu=null;
		Session session=null;
		List<StockDetail> stockList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT p.product_name,i.stock, g.godownName, i.batchNo, c.cat_name from item_stockforpestiandseed i LEFT JOIN product_details p ON p.pk_product_id = i.fk_product_id LEFT JOIN godownentry g ON i.fk_godown_id = g.pkGodownId LEFT JOIN categories c ON i.fk_cat_id = c.pk_cat_id WHERE i.fk_godown_id =:fkGodownId ");
			query.setParameter("fkGodownId", fkGodownId);
			System.out.println("fkGodownId = = ="+fkGodownId);
			List<Object[]> list = query.list();
			stockList = new ArrayList<StockDetail>(0);

			for (Object[] object : list) {

				StockDetail reports = new StockDetail();

				reports.setProductName(object[0].toString());
				reports.setStock((BigDecimal)object[1]);
				reports.setGodownName(object[2].toString());
				reports.setBatchNo(object[3].toString());
				reports.setCategoryName(object[4].toString());
				stockList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	

	}

	public List getAllBillNo()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from GoodsReceiveBean group by billNum");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupllier", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}


	// get All Purchase Item Name In Purchase Return Form
	
	public List getAllIetmByBillNo(String bill_no, String supplier) {
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;

		try{
			hbu=HibernateUtility.getInstance();
			session=hbu.getHibernateSession();

			Query query=session.createSQLQuery("SELECT p.pk_goods_receive_id ,s.supplier_name,p.dc_number,p.product_name,p.buy_price,p.sale_price,p.weight,p.dupQuantity,p.batch_no,p.fkCategoryId,p.purchaseDate,p.mrp,p.tax_percentage,p.barcodeNo,p.company_Name,p.dupQuantity from goods_receive p left JOIN supplier_details s on p.fk_supplier_id = s.pk_supplier_id WHERE p.bill_number=:bill_no AND p.fk_supplier_id=:supplier");

			query.setParameter("bill_no", bill_no);
			query.setParameter("supplier", supplier);

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

	public List<CustomerBean> getAllProductDetailsForFrtiBillAsPerProductName(
			String proName, String company, String weight) {

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{
			System.out.println("shreemant");
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "SELECT pk_goods_receive_id , fk_supplier_id, fkCategoryId, product_name , sale_price, weight, dupQuantity, mrp, company_Name, barcodeNo, tax_percentage FROM goods_receive WHERE product_name =:proName AND company_Name =:company AND weight =:weight";

			Query query=session.createSQLQuery(sqlQuery);
			query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				System.out.println("itemlist");
				bean.setPkGoodreceiveId(Long.parseLong(objects[0].toString()));
				bean.setSupplier_id(Long.parseLong(objects[1].toString()));
				bean.setCat_id(Long.parseLong(objects[2].toString()));
				bean.setItemName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setWeight(Double.parseDouble(objects[5].toString()));
				bean.setQuantity(Double.parseDouble(objects[6].toString()));
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setCompanyName(objects[8].toString());
				bean.setBarcodeNo(Long.parseLong(objects[9].toString()));
				bean.setVatPercentage(Double.parseDouble(objects[10].toString()));

				System.out.println("itemlist");

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}

	//fetching product detail as per batch for seed pesti bill
	
	public List<CustomerBean> getAllProductDetailsForSeedBillAsPerBatchAndStock(
			String batchNum, String stock) {

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{

			System.out.println("shreemant");
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "SELECT pk_goods_receive_id , fk_supplier_id, fkCategoryId, product_name , sale_price, weight, dupQuantity, mrp, company_Name, barcodeNo, tax_percentage, expiry_date FROM goods_receive WHERE batch_no=:batchNum";

			Query query=session.createSQLQuery(sqlQuery);
			query.setParameter("batchNum", batchNum);
			query.setParameter("stock", stock);
			System.out.println("batchNum in dao = ="+batchNum);
			System.out.println("stock in dao = ="+stock);

			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				System.out.println("itemlist");
				bean.setPkGoodreceiveId(Long.parseLong(objects[0].toString()));
				bean.setSupplier_id(Long.parseLong(objects[1].toString()));
				bean.setCat_id(Long.parseLong(objects[2].toString()));
				bean.setItemName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setWeight(Double.parseDouble(objects[5].toString()));
				bean.setQuantity(Double.parseDouble(objects[6].toString()));
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setCompanyName(objects[8].toString());
				bean.setBarcodeNo(Long.parseLong(objects[9].toString()));
				bean.setVatPercentage(Double.parseDouble(objects[10].toString()));
				bean.setExpiryDate(objects[11].toString());
				System.out.println("itemlist");

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}

	public List<CustomerBean> getAllProductDetailsForFrtiBillAsPerProductName1(
			String proName, String company, String weight, String batchNum,String availableQty) {

		String companyname=company.trim();
		String productname=proName.trim();
		String pweight=weight.trim();
		
		System.out.println("@@@@@@@@@@@##################"+proName);
		System.out.println(company);
		System.out.println(weight);

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{

			System.out.println("Amit");
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "SELECT product_name, sale_price, weight, mrp, manufacturing_company, tax_percentage, s.unit_name,fk_cat_id,hsn FROM product_details LEFT JOIN sold_units s ON fk_unit_id = s.pk_unit_id  WHERE product_name ='"+productname+"' AND manufacturing_company ='"+companyname+"' AND weight ='"+pweight+"'";
			Query query=session.createSQLQuery(sqlQuery);
			/*query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);*/
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				System.out.println("itemlist");

				bean.setItemName(objects[0].toString());
				bean.setSalePrice(Double.parseDouble(objects[1].toString()));
				bean.setWeight(Double.parseDouble(objects[2].toString()));
				bean.setMrp(Double.parseDouble(objects[3].toString()));
				bean.setCompanyName(objects[4].toString());
				Double tax = Double.parseDouble(objects[5].toString());
				Double half = tax/2;
				bean.setcGst(half);
				bean.setiGst(0.0);
				bean.setsGst(half);
				bean.setUnitName(objects[6].toString());
				bean.setCat_id(Long.parseLong(objects[7].toString()));
				bean.setHsn(objects[8].toString());
				bean.setAvailableQty(availableQty);
				System.out.println("Cat id in dao"+Long.parseLong(objects[7].toString()));
				bean.setBatchNumber(batchNum);
				System.out.println("itemlist");

				itemlist.add(bean);
			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}

	public List<CustomerBean> getAllProductDetailsForPesticideBilling(
			String proName, String batchno,String quantity,String packing,String unit) { 

		String productname=proName.trim();
		String Batchnum=batchno.trim();
		String Quantity =quantity.trim();
		String Packing=packing.trim();
		String Unit=unit.trim();
		
		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "select ps.product_name,ps.batchno,ps.expiry_date,pd.manufacturing_company,pd.sale_price,pd.hsn,ps.packing,pd.mrp,ps.unit,pd.tax_percentage  from product_stock_details ps  LEFT JOIN product_details pd ON ps.product_name=pd.product_name  WHERE ps.product_name='"+productname+"' and ps.batchno='"+Batchnum+"' and ps.quantity='"+Quantity+"' and ps.packing='"+Packing+"' and ps.unit='"+Unit+"'";

			Query query=session.createSQLQuery(sqlQuery);
			/*query.setParameter("proName", proName);
			query.setParameter("batchno", batchno);
			//query.setParameter("expdate", expdate);
			query.setParameter("quantity", quantity);*/
			
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				bean.setItemName(objects[0].toString());
				//bean.setQuantityl(Long.parseLong(objects[1].toString()));
				bean.setBatchNumber(objects[1].toString());
				bean.setExpdate((Date)objects[2]);
				bean.setCompanyName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setHsn(objects[5].toString());
				bean.setWeight(Double.parseDouble(objects[6].toString()));
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setUnitName(objects[8].toString());
				Double gst=Double.parseDouble(objects[9].toString());
				
				Double scgst=gst/2;
				bean.setcGst(scgst);
				bean.setsGst(scgst);
				bean.setAvailableQty(quantity);

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}

	
	public List<CustomerBean> getAllProductDetailsForSeed(
			String proName, String batchno,String quantity) { 

		String productname=proName.trim();
		String batchnum=batchno.trim();
		String Qty=quantity.trim();
		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "select ps.product_name,ps.batchno,ps.expiry_date,pd.manufacturing_company,pd.sale_price,pd.hsn,pd.weight,pd.mrp,su.unit_name,pd.tax_percentage  from product_stock_details ps  LEFT JOIN product_details pd ON ps.product_name=pd.product_name LEFT JOIN sold_units su ON pd.fk_unit_id=su.pk_unit_id WHERE ps.product_name='"+productname+"' and ps.batchno='"+batchnum+"' and ps.quantity='"+Qty+"'";

			Query query=session.createSQLQuery(sqlQuery);
			/*query.setParameter("proName", proName);
			query.setParameter("batchno", batchno);
			//query.setParameter("expdate", expdate);
			query.setParameter("quantity", quantity);*/
			
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				bean.setItemName(objects[0].toString());
				//bean.setQuantityl(Long.parseLong(objects[1].toString()));
				bean.setBatchNumber(objects[1].toString());
				bean.setExpdate((Date)objects[2]);
				bean.setCompanyName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setHsn(objects[5].toString());
				bean.setWeight(Double.parseDouble(objects[6].toString()));
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setUnitName(objects[8].toString());
				Double gst=Double.parseDouble(objects[9].toString());
				
				Double scgst=gst/2;
				bean.setcGst(scgst);
				bean.setsGst(scgst);
				bean.setAvailableQty(quantity);
				
				
				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}
	
	
	//get product details for pesticide grid
	public List<CustomerBean> getAllProductDetailsForpesticide(
			String proName, String batchno,String quantity) { 

		HibernateUtility hbu=null;
		Session session=null;
		List<CustomerBean> itemlist=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			String sqlQuery = "select ps.product_name,ps.batchno,ps.expiry_date,pd.manufacturing_company,pd.sale_price,pd.hsn,pd.weight,pd.mrp,su.unit_name from product_stock_details ps  LEFT JOIN product_details pd ON ps.product_name=pd.product_name LEFT JOIN sold_units su ON pd.fk_unit_id=su.pk_unit_id WHERE ps.product_name='"+proName+"' and ps.batchno='"+batchno+"' and ps.quantity="+quantity;

			Query query=session.createSQLQuery(sqlQuery);
			/*query.setParameter("proName", proName);
			query.setParameter("batchno", batchno);
			//query.setParameter("expdate", expdate);
			query.setParameter("quantity", quantity);*/
			
			List<Object[]> list = query.list();

			itemlist = new ArrayList<CustomerBean>(0);
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
				CustomerBean bean = new CustomerBean();
				bean.setItemName(objects[0].toString());
				//bean.setQuantityl(Long.parseLong(objects[1].toString()));
				bean.setBatchNumber(objects[1].toString());
				bean.setExpdate((Date)objects[2]);
				bean.setCompanyName(objects[3].toString());
				bean.setSalePrice(Double.parseDouble(objects[4].toString()));
				bean.setHsn(objects[5].toString());
				bean.setWeight(Double.parseDouble(objects[6].toString()));
				bean.setMrp(Double.parseDouble(objects[7].toString()));
				bean.setUnitName(objects[8].toString());
				
				/*bean.setItemName(objects[0].toString());
				bean.setSalePrice(Double.parseDouble(objects[1].toString()));
				System.out.println("sale price = = = ="+objects[1].toString());
				bean.setWeight(Double.parseDouble(objects[2].toString()));
				bean.setMrp(Double.parseDouble(objects[3].toString()));
				bean.setCompanyName(objects[4].toString());
				Double tax = Double.parseDouble(objects[5].toString());
				Double half = tax/2;
				bean.setcGst(half);
				bean.setsGst(half);
				bean.setiGst(0.0);
				bean.setUnitName(objects[6].toString());
				bean.setCat_id(Long.parseLong(objects[7].toString()));
				bean.setHsn(objects[8].toString());
				bean.setBatchNumber(objects[9].toString());
				bean.setExpiryDate(objects[10].toString());
				System.out.println("itemlist");*/

				itemlist.add(bean);

			}
		}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllItemDetails(String key)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return itemlist;
	}

	
	
	public List<ProductStockDetailsBean> getStockDetailsForReportAsPerCategory(String cat) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		String yesterday1 = null;
		final Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		yesterday1 = dateFormat.format(cal.getTime());
		System.out.println("Yesterday Date if save_daily_stock is empty "+yesterday1);

		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			//String catageory = Integer.parseInt(cat);
			Query query = session.createSQLQuery("select psd.product_name,sd.supplier_name,psd.company_name,psd.batchno,psd.packing,sds.dsquantity ,psd.quantity from product_stock_details psd LEFT JOIN supplier_details sd ON psd.supplier_name=sd.pk_supplier_id LEFT JOIN  save_daily_stock sds ON sds.product_name=psd.product_name AND sds.batch_number=psd.batchno AND sds.insert_date='"+yesterday1+"' WHERE psd.product_category='"+cat+"'");
			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);

			for (Object[] object : list) {

				
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setSupplier_name(object[1].toString());
				reports.setCompany_name(object[2].toString());
				reports.setBatchno(object[3].toString());
				reports.setPacking(Double.parseDouble(object[4].toString()));
				Long openingQuantity=Long.parseLong(object[5].toString());
				Long closingQuantity=Long.parseLong(object[6].toString());
				Long sale=openingQuantity-closingQuantity;
				reports.setOpeningQty(openingQuantity);
				reports.setClosingQty(closingQuantity);
				reports.setSale(sale);
				
				stockList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		System.out.println("@@@@@@@@@@@ DAO Stock Report List :: "+stockList);
		return stockList;	
	}

	public List<StockDetail> getStockDetailsAsPerProductName(String proName) {

		HibernateUtility hbu=null;
		Session session=null;
		List<StockDetail> stockList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select ProductName, CompanyName, weight, quantity from stock_detail where ProductName ='" + proName +"'");
			List<Object[]> list = query.list();
			stockList = new ArrayList<StockDetail>(0);

			for (Object[] object : list) {

				StockDetail reports = new StockDetail();

				reports.setProductName(object[0].toString());
				reports.setCompanyName(object[1].toString());
				reports.setWeight((Double)object[2]);
				reports.setQuantity((Double)object[3]);

				stockList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}

	public List<ProductStockDetailsBean> getStockDetailsAsPerCompanyName(String companyName) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		String yesterday1 = null;
		final Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		yesterday1 = dateFormat.format(cal.getTime());
		System.out.println("Yesterday Date if save_daily_stock is empty "+yesterday1);
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select psd.product_name,sd.supplier_name,psd.company_name,psd.packing,sds.dsquantity ,psd.quantity from product_stock_details psd LEFT JOIN supplier_details sd ON psd.supplier_name=sd.pk_supplier_id LEFT JOIN  save_daily_stock sds ON sds.product_name=psd.product_name AND sds.batch_number=psd.batchno AND sds.insert_date='"+yesterday1+"' WHERE psd.company_name ='" + companyName +"'");
			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);
			System.out.println("===================list"+list);
			for (Object[] object : list) {

				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setSupplier_name(object[1].toString());
				reports.setCompany_name(object[2].toString());
				reports.setPacking(Double.parseDouble(object[3].toString()));
				
				Long openingQuantity=Long.parseLong(object[4].toString());
				Long closingQuantity=Long.parseLong(object[5].toString());
				Long sale=openingQuantity-closingQuantity;
				
				reports.setOpeningQty(openingQuantity);
				reports.setClosingQty(closingQuantity);
				reports.setSale(sale);
				Date currentDate=new Date();
				reports.setCurrentDate(currentDate);
				
				stockList.add(reports);
			
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}

	public PurchaseDeatilsResultBean getPurchaseDetailsForSingleDateFromGoodsReceive(
			String fDate) {

		HibernateUtility hbu=null;
		Session session=null;
		PurchaseDeatilsResultBean bean = new PurchaseDeatilsResultBean();
		PurchaseDetailsFromGoodsReceive lastbean = new PurchaseDetailsFromGoodsReceive();
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		Double totalAmt = 0.0  ;  
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount from goods_receive where purchaseDate ='" + fDate +"'");
			List<Object[]> list = query.list();
			purchaseList = new LinkedList<PurchaseDetailsFromGoodsReceive>();

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));
				totalAmt = totalAmt + Double.parseDouble(object[12].toString()) ;
				purchaseList.add(reports); 

			}
			bean.setList(purchaseList );
		}

		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return bean;	
	}

	public List<PurchaseDetailsFromGoodsReceive> getPurchaseDetailsAsPerProduct(
			String cat, String product, String company, String weight) {

		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			System.out.println("Product name in dao = = = "+product);
			System.out.println("company name in dao = = = "+company);
			System.out.println("weight in dao = = = "+weight);

			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select product_name, buy_price, sale_price, purchaseDate, quantity, weight, expenses, gross_total, bill_number FROM goods_receive RIGHT JOIN supplier_details  ON  goods_receive.fk_supplier_id = supplier_details.pk_supplier_id WHERE goods_receive.product_name ='"+product+"' And goods_receive.company_Name ='"+company+"' AND goods_receive.weight ="+weight);

			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setProductName(object[0].toString());
				reports.setBuyPrice(Double.parseDouble(object[1].toString()));
				reports.setSalePrice(Double.parseDouble(object[2].toString()));
				reports.setPurchaseDate(object[3].toString());
				reports.setQuantity2(Double.parseDouble(object[4].toString()));
				reports.setWeight(Double.parseDouble(object[5].toString()));
				Double buyPrice = Double.parseDouble(object[1].toString());
				Double quanty = Double.parseDouble(object[4].toString());
				Double total = buyPrice * quanty;
				reports.setTotalAmount(total);
				reports.setBillNo(object[8].toString());
				purchaseList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	public List<PurchaseDetailsFromGoodsReceive> getPurchaseDetailsForSupplier(
			String supplier) {

		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount, supplier_details.supplier_name from goods_receive  left join supplier_details on goods_receive.fk_supplier_id = supplier_details.pk_supplier_id where fk_supplier_id = " + supplier);
			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));
				reports.setSupplier(object[13].toString());

				purchaseList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	
	public List<PurchaseDetailsFromGoodsReceive> getPurchaseDetailsForBillNoWise(
			String supplier,String billno) {

		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount, supplier_details.supplier_name from goods_receive  left join supplier_details on goods_receive.fk_supplier_id = supplier_details.pk_supplier_id where bill_number='"+billno+"' AND fk_supplier_id='"+supplier+"'");
			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));
				reports.setSupplier(object[13].toString());

				purchaseList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	
	public List<PurchaseDetailsFromGoodsReceive> getPurchaseDetailsForCategory(
			String cat) {

		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount from goods_receive where goods_receive.fkCategoryId ="+cat);
			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));

				purchaseList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	public List<SaleReports> getSaleDetailsAsPerCategoryForSingleDate(
			String cat, String fDate) {
		Double totalWithGST = 0.0;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no,product_name, DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM fertilizer_billing WHERE cat_id =:cat AND DATE(insert_date)=:fDate UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM seed_pesticide_billing WHERE cat_id =:cat AND DATE(insert_date)=:fDate UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM pesticide_billing WHERE cat_id =:cat AND DATE(insert_date)=:fDate");
			query.setParameter("fDate", fDate);
			query.setParameter("cat", cat);
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				Double saleprice=Double.parseDouble(object[3].toString());
				reports.setQuantity1(((BigInteger) object[4]));
				String qty=object[4].toString();
				Double quantity=Double.parseDouble(qty);
				Double Total=saleprice*quantity;
				reports.setFivePercentageGST(Double.parseDouble(object[8].toString()));
				reports.setiGSTFivePercentage(Double.parseDouble(object[9].toString()));
				Double GST = (Double.parseDouble(object[8].toString()));
				Double IGST = (Double.parseDouble(object[9].toString()));
				Double salePrice  = (Double.parseDouble(object[3].toString()));
				Double quanti = (Double.parseDouble(object[4].toString()));

				DecimalFormat df = new DecimalFormat("#.##");

				if(GST == 0){
					totalWithGST = salePrice * quanti;

				}
				if(IGST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(GST != 0){
					Double taxAmt = (salePrice)*(GST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;

				}
				if(IGST != 0){
					Double taxAmt = (salePrice)*(IGST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}

				String tota=df.format(totalWithGST);
				//reports.setTotalAmount(Double.parseDouble(tota));
				reports.setTotalAmount(Total);
				saleList.add(reports);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsPerCategoryBetweeenTwoDates(
			String cat, String fDate, String sDate) {
		Double totalWithGST=0.0;
		System.out.println(cat+"Category in dao");
		System.out.println(fDate+"fDate in dao");
		System.out.println(sDate+"sDate in dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no,product_name, DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM fertilizer_billing WHERE cat_id =:cat AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM seed_pesticide_billing WHERE cat_id =:cat AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage FROM pesticide_billing WHERE cat_id =:cat AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"'");
			query.setParameter("cat", cat);	

			System.out.println(cat+"Category in dao");
			System.out.println(fDate+"fDate in dao");
			System.out.println(sDate+"sDate in dao");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				Double saleprice=Double.parseDouble(object[3].toString());
				reports.setQuantity1(((BigInteger) object[4]));
				String qty= object[4].toString();
				Double quantity=Double.parseDouble(qty);
				Double total=saleprice*quantity;
				reports.setFivePercentageGST(Double.parseDouble(object[8].toString()));
				reports.setiGSTFivePercentage(Double.parseDouble(object[9].toString()));
				Double GST = (Double.parseDouble(object[8].toString()));
				Double IGST = (Double.parseDouble(object[9].toString()));
				Double salePrice  = (Double.parseDouble(object[3].toString()));
				Double quanti = (Double.parseDouble(object[4].toString()));

				DecimalFormat df = new DecimalFormat("#.##");

				if(GST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(IGST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(GST != 0){
					Double taxAmt = (salePrice)*(GST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}
				if(IGST != 0){
					Double taxAmt = (salePrice)*(IGST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}

				String tota=df.format(totalWithGST);
				reports.setTotalAmount(Double.parseDouble(tota));
				reports.setTotalAmount(total);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsPerProductNameForSingleDate(
			String cat, String fDate, String productName) {
		Double totalWithGST=0.0;
		System.out.println(cat+"Category in dao");
		System.out.println(fDate+"fDate in dao");
		System.out.println(productName+"productName in dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no,product_name, DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM fertilizer_billing WHERE cat_id ='"+cat+"' AND product_name='"+productName+"' AND insert_date='"+fDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM seed_pesticide_billing WHERE cat_id ='"+cat+"' AND product_name='"+productName+"' AND insert_date='"+fDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM pesticide_billing WHERE cat_id ='"+cat+"'  AND product_name='"+productName+"'  AND insert_date='"+fDate+"'");
			/*query.setParameter("cat", cat);
			query.setParameter("productName", productName);
			query.setParameter("fDate", fDate);*/
			System.out.println(cat+"Category in dao");
			System.out.println(fDate+"fDate in dao");
			System.out.println(productName+"productName in dao");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				Double saleprice=Double.parseDouble(object[3].toString());
				reports.setQuantity1(((BigInteger) object[4]));
				String qty=object[4].toString();
				Double quantity=Double.parseDouble(qty);
				Double total=saleprice*quantity;
				reports.setPaymentMode(object[10].toString());
				reports.setFivePercentageGST(Double.parseDouble(object[8].toString()));
				reports.setiGSTFivePercentage(Double.parseDouble(object[9].toString()));
				Double GST = (Double.parseDouble(object[8].toString()));
				Double IGST = (Double.parseDouble(object[9].toString()));
				Double salePrice  = (Double.parseDouble(object[3].toString()));
				Double quanti = (Double.parseDouble(object[4].toString()));

				if(GST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(IGST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(GST != 0){
					Double taxAmt = (salePrice)*(GST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}
				if(IGST != 0){
					Double taxAmt = (salePrice)*(IGST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}
				//reports.setTotalAmount(totalWithGST);
				reports.setTotalAmount(total);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	
	public List<SaleReports> getGrossTotalAsPerBillNo(String category,String billno) {
		Double totalWithGST=0.0;
		System.out.println(category+"category in dao");
		System.out.println(billno+"grossbill in dao");
		
		String fb="fertilizer_billing";
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			List<Object[]> list=null;
			
			if(category.equals("Fertilizer"))
			{
				Query query = session.createSQLQuery("select bill_no,customer_name,gross_total from fertilizer_billing WHERE bill_no='"+billno+"'");
				 list = query.list();
			}
			
			if(category.equals("Seed"))
			{
				Query query = session.createSQLQuery("select bill_no,customer_name,gross_total from seed_pesticide_billing WHERE bill_no='"+billno+"'");
				 list = query.list();
			}
			
			if(category.equals("Pesticide"))
			{
				Query query = session.createSQLQuery("select bill_no,customer_name,gross_total from pesticide_billing WHERE bill_no='"+billno+"'");
				 list = query.list();
			}
			
					saleList= new ArrayList<SaleReports>(0);
		
					for (int i = 0; i < list.size(); i++) {
					Object[] o = (Object[]) list.get(i);
					
					SaleReports reports = new SaleReports();
					
					Long billnum=Long.parseLong(o[0].toString());
					String custname= (o[1].toString());
					Double grossTotal = Double.valueOf(o[2].toString());
					
					if(i == (list.size()-1)){
							
							reports.setCusomerName(custname);
							reports.setBillNo(billnum);
							reports.setGrossTotal(grossTotal);
						}
						else
						{
							continue;
						}
					System.out.println("***************" + o[0]);
					saleList.add(reports);
				
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	
	
	
	public List<SaleReports> getSaleDetailsAsPerProductNamesBetweeenTwoDates(
			String cat, String fDate, String sDate, String product) {

		Double totalWithGST =0.0;
		System.out.println(cat+"Category in dao");
		System.out.println(fDate+"fDate in dao");
		System.out.println(product+"productName in dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no,product_name, DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM fertilizer_billing WHERE cat_id =:cat AND product_name=:productName AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM seed_pesticide_billing WHERE cat_id =:cat AND product_name=:productName AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product, tax_percentage, igstPercentage,payment_mode FROM pesticide_billing WHERE cat_id =:cat AND product_name=:productName AND DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"'");
			query.setParameter("cat", cat);
			query.setParameter("productName", product);

			System.out.println(cat+"Category in dao");
			System.out.println(fDate+"fDate in dao");
			System.out.println(product+"productName in dao");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				Double saleprice=Double.parseDouble(object[3].toString());
				reports.setQuantity1(((BigInteger) object[4]));
				String qty=object[4].toString();
				Double quantity=Double.parseDouble(qty);
				Double total=saleprice*quantity;
				reports.setPaymentMode(object[10].toString());
				reports.setFivePercentageGST(Double.parseDouble(object[8].toString()));
				reports.setiGSTFivePercentage(Double.parseDouble(object[9].toString()));
				Double GST = (Double.parseDouble(object[8].toString()));
				Double IGST = (Double.parseDouble(object[9].toString()));
				Double salePrice  = (Double.parseDouble(object[3].toString()));
				Double quanti = (Double.parseDouble(object[4].toString()));

				if(GST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(IGST == 0){
					totalWithGST = salePrice * quanti;
				}
				if(GST != 0){
					Double taxAmt = (salePrice)*(GST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;

				}
				if(IGST != 0){
					Double taxAmt = (salePrice)*(IGST/100);
					Double saleWithTaxAmt = salePrice + taxAmt; 
					totalWithGST = saleWithTaxAmt * quanti;
				}
				//reports.setTotalAmount(totalWithGST);
				reports.setTotalAmount(total);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsPerSupplierName(String fkSupplierId) {

		Double trans;
		Double hamali;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery(" SELECT bill_no,product_name, DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product FROM fertilizer_billing WHERE supplier_id=:fkSupplierId UNION SELECT bill_no, product_name,DATE(insert_date), sale_price, quantity, transportation_expense, hamali_expense, total_per_product FROM seed_pesticide_billing WHERE supplier_id=:fkSupplierId");
			query.setParameter("fkSupplierId", fkSupplierId);
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				reports.setQuantity1(((BigInteger) object[4]));
				reports.setTransExpense(((BigDecimal) object[5]));
				reports.setHamaliexpense(((BigDecimal) object[6]));
				Double trans1 = (Double.parseDouble(object[5].toString()));
				if(trans1 != null){
					trans = (Double.parseDouble(object[5].toString()));
				}else{
					trans = 0.0;
				}

				Double hamali1 = (Double.parseDouble(object[6].toString()));
				if(hamali1 != null){
					hamali = (Double.parseDouble(object[6].toString()));
				}else{
					hamali = 0.0;
				}

				Double totalWithoutExpense = (Double.parseDouble(object[7].toString()));
				Double total = totalWithoutExpense + hamali + trans;
				reports.setTotalAmount(total);
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<PurchaseDetailsFromGoodsReceive> geTaxDetailsAsPerCategoryForSingleDate(
			String cat, String fDate, String sDate) {

		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT supplier_name, tin_no, bill_number, product_name, company_Name, weight,buy_price,mrp, tax_percentage, quantity FROM goods_receive RIGHT JOIN supplier_details on fk_supplier_id=pk_supplier_id WHERE tax_percentage>0 AND fkCategoryId =:cat AND DATE(insertDate) BETWEEN :fDate AND :sDate");
			query.setParameter("cat", cat);
			query.setParameter("fDate", fDate);
			query.setParameter("sDate", sDate);

			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] o : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();

				reports.setSupplier(o[0].toString());
				reports.setTinNo(o[1].toString());
				reports.setBillNo(o[2].toString());
				reports.setProductName(o[3].toString());
				reports.setCompanyName(o[4].toString());
				reports.setWeight(Double.parseDouble(o[5].toString()));
				reports.setBuyPrice(Double.parseDouble(o[6].toString()));
				reports.setMrp(Double.parseDouble(o[7].toString()));
				reports.setTaxPercentage(Double.parseDouble(o[8].toString()));
				reports.setQuantity2(Double.parseDouble(o[9].toString()));
				Double quantity = Double.parseDouble(o[9].toString());
				Double taxPercentage = Double.parseDouble(o[8].toString());
				Double buyPrice = Double.parseDouble(o[6].toString());
				Double taxAmt = (taxPercentage/100)*buyPrice;
				Double newBuyPrice = buyPrice + taxAmt;
				Double totalTaxAmt = quantity *taxAmt ;

				reports.setTaxAmount(totalTaxAmt);
				purchaseList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	public List<ProductStockDetailsBean> getStockDetailsAsPerProductName(String proName, String weight, String company) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		String yesterday1 = null;
		final Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		yesterday1 = dateFormat.format(cal.getTime());

		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select psd.product_name,sd.supplier_name,psd.company_name,psd.batchno,psd.packing,sds.dsquantity ,psd.quantity from product_stock_details psd LEFT JOIN supplier_details sd ON psd.supplier_name=sd.pk_supplier_id LEFT JOIN  save_daily_stock sds ON sds.product_name=psd.product_name AND sds.batch_number=psd.batchno AND sds.insert_date='"+yesterday1+"' WHERE psd.product_name='"+proName+"'");
			/*query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);*/

			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);
			for (Object[] object : list) {

				
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setSupplier_name(object[1].toString());
				reports.setCompany_name(object[2].toString());
				reports.setBatchno(object[3].toString());
				reports.setPacking(Double.parseDouble(object[4].toString()));
				
				Long openingQuantity=Long.parseLong(object[5].toString());
				Long closingQuantity=Long.parseLong(object[6].toString());
				Long sale=openingQuantity-closingQuantity;
				reports.setOpeningQty(openingQuantity);
				reports.setClosingQty(closingQuantity);
				reports.setSale(sale);
				
				//StockDetail reports = new StockDetail();
				/*reports.setProductName(object[0].toString());
				reports.setCompanyName(object[1].toString());
				reports.setWeight((Double)object[2]);
				reports.setQuantity((Double)object[3]);*/

				stockList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}
	
	
	public List<ProductStockDetailsBean> getStockDetailsBasedOnAvailableStockAsPerProductName(String proName) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select ps.product_name,ps.company_name,ps.batchno,ps.packing,ps.quantity from product_stock_details ps WHERE ps.product_name='"+proName+"'");
			/*query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);*/

			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);
			for (Object[] object : list) {

				
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setCompany_name(object[1].toString());
				reports.setBatchno(object[2].toString());
				reports.setPacking(Double.parseDouble(object[3].toString()));
				reports.setQuantity(Long.parseLong(object[4].toString()));
				
			
				stockList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}

	public List<ProductStockDetailsBean> getStockDetailsBasedOnAvailableStockAsCategory(String category) {

		HibernateUtility hbu=null;
		Session session=null;
		List<ProductStockDetailsBean> stockList = null;
		
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select ps.product_name,ps.company_name,ps.batchno,ps.packing,ps.quantity from product_stock_details ps WHERE ps.product_category='"+category+"'");
			
			/*query.setParameter("proName", proName);
			query.setParameter("company", company);
			query.setParameter("weight", weight);*/

			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);
			for (Object[] object : list) {

				
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setCompany_name(object[1].toString());
				reports.setBatchno(object[2].toString());
				reports.setPacking(Double.parseDouble(object[3].toString()));
				reports.setQuantity(Long.parseLong(object[4].toString()));
				
			
				stockList.add(reports); 
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}

	

	//sale detail as per payment mode
	public List<SaleReports> getSaleDetailsAsPerPaymentMode(String paymentMode, String fk_cat_id) {

		Double trans;
		Double hamali;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT bill_no,customer_name,gross_total from fertilizer_billing where cat_id='"+fk_cat_id+"' AND payment_mode ='"+paymentMode+"' GROUP BY bill_no UNION SELECT bill_no,customer_name,gross_total from seed_pesticide_billing where cat_id='"+fk_cat_id+"' AND payment_mode ='"+paymentMode+"' GROUP BY bill_no  UNION SELECT bill_no,customer_name,gross_total from pesticide_billing where cat_id='"+fk_cat_id+"' AND payment_mode ='"+paymentMode+"' GROUP BY bill_no");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setCusomerName(object[1].toString());
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsPerPaymentModeForSingleDate(
			String singleDate, String fk_cat_id) {
		Double trans;
		Double hamali;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT bill_no, gross_total,payment_mode FROM fertilizer_billing WHERE cat_id='"+fk_cat_id+"' AND insert_date ='"+singleDate+"' UNION SELECT bill_no, gross_total,payment_mode FROM seed_pesticide_billing WHERE cat_id='"+fk_cat_id+"' AND insert_date ='"+singleDate+"' UNION SELECT bill_no, gross_total,payment_mode FROM pesticide_billing WHERE cat_id='"+fk_cat_id+"' AND insert_date ='"+singleDate+"'");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] object : list) {
				SaleReports reports = new SaleReports();
				String mode = object[2].toString();

				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				if(mode.equals("cash")){
					reports.setCashAmount(Double.parseDouble(object[1].toString()));
					reports.setChequeAmount(0.0);
					reports.setCardAmount(0.0);
					reports.setNeftAmount(0.0);
				}
				else if(mode.equals("cheque")){
					reports.setChequeAmount(Double.parseDouble(object[1].toString()));
					reports.setCardAmount(0.0);
					reports.setNeftAmount(0.0);
					reports.setCashAmount(0.0);

				}
				else if(mode.equals("card")){
					reports.setCardAmount(Double.parseDouble(object[1].toString()));
					reports.setNeftAmount(0.0);
					reports.setCashAmount(0.0);
					reports.setChequeAmount(0.0);
				}
				else if(mode.equals("neft")){
					reports.setNeftAmount(Double.parseDouble(object[1].toString()));
					reports.setCashAmount(0.0);
					reports.setChequeAmount(0.0);
					reports.setCardAmount(0.0);
				}

				saleList.add(reports);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsGST(String fk_cat_id, String endDate, String startDate) {
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			Long k = 0l;
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT insert_date, customer_name, product_name,salePriceWithoutTax,quantity,tax_percentage,gross_total,bill_no,igstPercentage,hsn,credit_customer_name FROM fertilizer_billing WHERE cat_id ='"+fk_cat_id+"' AND insert_date between '" + startDate +"' and '"+endDate+"' UNION SELECT insert_date, customer_name, product_name,salePriceWithoutTax,quantity,tax_percentage,gross_total,bill_no,igstPercentage,hsn,credit_customer_name FROM seed_pesticide_billing WHERE cat_id ='"+fk_cat_id+"'AND insert_date between '" + startDate +"' and '"+endDate+"' UNION SELECT insert_date, customer_name, product_name,salePriceWithoutTax,quantity,tax_percentage,gross_total,bill_no,igstPercentage,hsn,credit_customer_name FROM pesticide_billing WHERE cat_id ='"+fk_cat_id+"'AND insert_date between '" + startDate +"' and '"+endDate+"'");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] o : list) {
				SaleReports reports = new SaleReports();
				k++;
				String tax = o[5].toString();
				String igstTax = o[8].toString();
				String creditCustomerName = o[10].toString();
				String custName = o[1].toString();
				System.out.println("tax = = ="+tax);
				System.out.println("igstTax = = ="+igstTax);
				reports.setSerialnumber(k);
				reports.setSaleDate(o[0].toString());
				if(custName.equals("N/A")){
					reports.setCusomerName(creditCustomerName);
				}
				else{
					reports.setCusomerName(custName);
				}
				reports.setBillNo(Long.parseLong(o[7].toString()));
				reports.setItemName(o[2].toString());
				reports.setSalePrice(Double.parseDouble(o[3].toString()));
				reports.setQuantity1((BigInteger)o[4]);
				reports.setGstNumber("27AIHPL6690E1Z0");
				reports.setHsnNumber(o[9].toString());
				Double igstPercent = Double.parseDouble(o[8].toString());
				Double sp = Double.parseDouble(o[3].toString());
				Double qunti = Double.parseDouble(o[4].toString());
				Double total = sp * qunti;
				reports.setTotalAmount((double)Math.round(total*100.0)/100.0);

				System.out.println("igstTax = = ="+igstTax);

				if(igstTax.equals("0.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = 0.0;
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage((double)Math.round(taxAmt*100.0)/100.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalAmount*100.0)/100.0);
					System.out.println("5 % igstTax Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}
				if(igstTax.equals("5.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage((double)Math.round(taxAmt*100.0)/100.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalAmount*100.0)/100.0);
					System.out.println("5 % igstTax Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);

				}
				if(igstTax.equals("12.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmt = qunti * newSalePrice;
					
					DecimalFormat f = new DecimalFormat("##.00");
					String ta=f.format(totalAmt);
					Double totalAmount=Double.parseDouble(ta);
					
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage((double)Math.round(taxAmt*100.0)/100.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalAmount*100.0)/100.0);
					System.out.println("12 % GST Amount"+taxAmt);
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);

				}
				if(igstTax.equals("18.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage((double)Math.round(taxAmt*100.0)/100.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount));
					System.out.println("18 % GST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}
				if(igstTax.equals("28.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage((double)Math.round(taxAmt*100.0)/100.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount*100.0)/100.0);
					System.out.println("28 % GST Amount"+taxAmt);


					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}

				if(tax.equals("5.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST((double)Math.round(taxAmt*100.0)/100.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount*100.0)/100.0);
					System.out.println("5 % GST Amount"+taxAmt);

				}
				if(tax.equals("12.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmt = qunti * newSalePrice;
					
					double totalAmount = Math.round(totalAmt * 100.0) / 100.0;
					
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST((double)Math.round(taxAmt*100.0)/100.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount*100.0)/100.0);
					System.out.println("12 % GST Amount"+taxAmt);

				}
				if(tax.equals("18.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST((double)Math.round(taxAmt*100.0)/100.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount*100.0)/100.0);
					System.out.println("18 % GST Amount"+taxAmt);

				}
				if(tax.equals("28.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST((double)Math.round(taxAmt*100.0)/100.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount*100.0)/100.0); 
					reports.setNetAmount((double)Math.round(totalTaxAmount*100.0)/100.0);
					System.out.println("28 % GST Amount"+taxAmt);

				}
				saleList.add(reports);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}

		return saleList;	
	}

	public List getgoodsReceiveForGSTReport(){
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			Long k = 0l;
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT purchaseDate,s.supplier_name,product_name,buy_price,quantity,tax_percentage,bill_number,iGstPercentage,hsn,s.tin_no FROM goods_receive LEFT JOIN supplier_details s on s.pk_supplier_id = fk_supplier_id");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] o : list) {
				SaleReports reports = new SaleReports();
				k++;
				String tax = o[5].toString();
				String igstTax = o[7].toString();

				System.out.println("tax = = ="+tax);
				reports.setSerialnumber(k);
				reports.setSaleDate(o[0].toString());
				reports.setSupplierName(o[1].toString());
				reports.setBillNo(Long.parseLong(o[6].toString()));
				reports.setItemName(o[2].toString());
				reports.setBuyPrice(Double.parseDouble(o[3].toString()));
				reports.setQuanti(Double.parseDouble(o[4].toString()));
				reports.setGstNumber(o[9].toString());
				reports.setHsnNumber(o[8].toString());
				Double sp = Double.parseDouble(o[3].toString());
				Double qunti = Double.parseDouble(o[4].toString());
				Double total = sp * qunti;
				reports.setTotalAmount(total);
				System.out.println("igstTax 22= = ="+igstTax);
				if(igstTax.equals("5.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(taxAmt);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("5 % igstTax Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);

				}
				else if(igstTax.equals("12.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(taxAmt);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("12 % GST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);

				}
				else if(igstTax.equals("18.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(taxAmt);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("18 % GST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}
				else if(igstTax.equals("28.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(taxAmt);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("28 % GST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}

				if(tax.equals("5.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(taxAmt);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("5 % GST Amount"+taxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				else if(tax.equals("12.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(taxAmt);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("12 % GST Amount"+taxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);

				}
				else if(tax.equals("18.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(taxAmt);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("18 % GST Amount"+taxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				else if(tax.equals("28.00")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(taxAmt);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("28 % GST Amount"+taxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				saleList.add(reports);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getSaleDetailsAsPerPaymentModeForTwoDate(
			String singleDate, String fk_cat_id, String secondDate) {

		Double trans;
		Double hamali;
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT bill_no, gross_total,payment_mode FROM fertilizer_billing WHERE cat_id='"+fk_cat_id+"' AND DATE(insert_date) BETWEEN '"+singleDate+"' AND '"+secondDate+"' UNION SELECT bill_no, gross_total,payment_mode FROM seed_pesticide_billing WHERE cat_id='"+fk_cat_id+"' AND DATE(insert_date) BETWEEN '"+singleDate+"' AND '"+secondDate+"' UNION SELECT bill_no, gross_total,payment_mode FROM pesticide_billing WHERE cat_id='"+fk_cat_id+"' AND DATE(insert_date) BETWEEN '"+singleDate+"' AND '"+secondDate+"'");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] object : list) {
				SaleReports reports = new SaleReports();
				String mode = object[2].toString();

				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				if(mode.equals("cash")){
					reports.setCashAmount(Double.parseDouble(object[1].toString()));
					reports.setChequeAmount(0.0);
					reports.setCardAmount(0.0);
					reports.setNeftAmount(0.0);
				}
				else if(mode.equals("cheque")){
					reports.setChequeAmount(Double.parseDouble(object[1].toString()));
					reports.setCardAmount(0.0);
					reports.setNeftAmount(0.0);
					reports.setCashAmount(0.0);

				}
				else if(mode.equals("card")){
					reports.setCardAmount(Double.parseDouble(object[1].toString()));
					reports.setNeftAmount(0.0);
					reports.setCashAmount(0.0);
					reports.setChequeAmount(0.0);
				}
				else if(mode.equals("neft")){
					reports.setNeftAmount(Double.parseDouble(object[1].toString()));
					reports.setCashAmount(0.0);
					reports.setChequeAmount(0.0);
					reports.setCardAmount(0.0);
				}
				saleList.add(reports);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	

	}

	public List getPurchaseReportsASPerGST(
			String fDate, String tDate) {

		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> purchaseList=null;
		try
		{
			Long k = 0l;
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT purchaseDate,s.supplier_name,product_name,buy_price,quantity,tax_percentage,bill_number,iGstPercentage,hsn,s.tin_no FROM goods_receive LEFT JOIN supplier_details s on s.pk_supplier_id = fk_supplier_id WHERE purchaseDate between '" + fDate +"' and '"+tDate+"'");
			List<Object[]> list = query.list();
			purchaseList= new ArrayList<SaleReports>(0);
			for (Object[] o : list) {
				SaleReports reports = new SaleReports();
				k++;
				String tax = o[5].toString();
				String igstTax = o[7].toString();

				System.out.println("tax = = ="+tax);
				reports.setSerialnumber(k);
				reports.setSaleDate(o[0].toString());
				reports.setSupplierName(o[1].toString());
				reports.setBillNo(Long.parseLong(o[6].toString()));
				reports.setItemName(o[2].toString());
				reports.setBuyPrice(Double.parseDouble(o[3].toString()));
				reports.setQuanti(Double.parseDouble(o[4].toString()));
				reports.setGstNumber(o[9].toString());
				reports.setHsnNumber(o[8].toString());
				Double sp = Double.parseDouble(o[3].toString());
				Double qunti = Double.parseDouble(o[4].toString());
				Double total = sp * qunti;
				reports.setTotalAmount(total);
				System.out.println("igstTax = = ="+igstTax);

				if(igstTax.equals("5.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(taxAmt);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("5 % igstTax Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}
				if(igstTax.equals("12.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(taxAmt);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("12 % iGST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);

				}
				if(igstTax.equals("18.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(taxAmt);
					reports.setiGSTTwentyeightPercentage(0.0);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("18 % iGST Amount"+taxAmt);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}
				if(igstTax.equals("28.00")){
					Double tx = Double.parseDouble(igstTax);
					Double taxAmt = (tx/100)*(sp);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(taxAmt);
					reports.setTotalTaxAmount(totalTaxAmount); 
					reports.setNetAmount(totalAmount);
					System.out.println("28 % iGST Amount"+taxAmt);
					System.out.println("28 % iGST totalTaxAmount"+totalTaxAmount);

					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
				}

				if(tax.equals("5")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double roundedTaxAmt = (double )Math.round(taxAmt);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(roundedTaxAmt);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount)); 
					reports.setNetAmount(totalAmount);
					System.out.println("5 % GST Amount"+roundedTaxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				if(tax.equals("12")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double roundedTaxAmt = (double )Math.round(taxAmt);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(roundedTaxAmt);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount)); 
					reports.setNetAmount(totalAmount);
					System.out.println("12 % GST Amount"+roundedTaxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);

				}
				if(tax.equals("18")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double roundedTaxAmt = (double )Math.round(taxAmt);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(roundedTaxAmt);
					reports.setTwentyEightPercentageGST(0.0);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount)); 
					reports.setNetAmount(totalAmount);
					System.out.println("18 % GST Amount"+roundedTaxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				if(tax.equals("28")){
					Double tx = Double.parseDouble(tax);
					Double taxAmt = (tx/100)*(sp);
					Double roundedTaxAmt = (double )Math.round(taxAmt);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = sp + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(roundedTaxAmt);
					reports.setTotalTaxAmount((double)Math.round(totalTaxAmount)); 
					reports.setNetAmount(totalAmount);
					System.out.println("28 % GST Amount"+roundedTaxAmt);

					reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);
				}
				purchaseList.add(reports);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	public List<PurchaseDetailsFromGoodsReceive> getPurchaseReturnDetailsForSupplier(
			String supplier, String firstDate, String secondDate) {
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseDetailsFromGoodsReceive> purchaseList = null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select bill_number, purchaseDate, product_name, company_Name,  dc_number, batch_no, barcodeNo, buy_price, sale_price, mrp, weight, abs(quantity), total_amount, supplier_details.supplier_name, dupQuantity from goods_receive  left join supplier_details on goods_receive.fk_supplier_id = supplier_details.pk_supplier_id where goods_receive.fk_supplier_id = '"+ supplier+"' And goods_receive.return_amount >0");
			List<Object[]> list = query.list();
			purchaseList = new ArrayList<PurchaseDetailsFromGoodsReceive>(0);

			for (Object[] object : list) {

				PurchaseDetailsFromGoodsReceive reports = new PurchaseDetailsFromGoodsReceive();
				Double quantityAfterReturn = Double.parseDouble(object[14].toString());
				Double originalQuantity = Double.parseDouble(object[11].toString());
				Double returnQuantity = originalQuantity - quantityAfterReturn;
				System.out.println("originalQuantity = = = = "+originalQuantity);
				System.out.println("returnQuantity = = = = "+returnQuantity);
				System.out.println("quantityAfterReturn = = = = "+quantityAfterReturn);

				reports.setBillNo(object[0].toString());
				reports.setPurchaseDate(object[1].toString());
				reports.setProductName(object[2].toString());
				reports.setCompanyName(object[3].toString());
				reports.setDcNo(object[4].toString());
				reports.setBatchNo(object[5].toString());
				reports.setBarcodeNo(object[6].toString());
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setSalePrice(Double.parseDouble(object[8].toString()));
				reports.setMrp(Double.parseDouble(object[9].toString()));
				reports.setWeight(Double.parseDouble(object[10].toString()));
				reports.setQuantity2(Double.parseDouble(object[11].toString()));
				reports.setTotalAmount(Double.parseDouble(object[12].toString()));
				reports.setSupplier(object[13].toString());
				reports.setReturnQuantity(returnQuantity);
				reports.setRemainingQuantity(quantityAfterReturn);
				purchaseList.add(reports); 

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return purchaseList;	
	}

	public List getStockDetailToSaveDailyStock() {

		HibernateUtility hbu1=null;
		Session session1 =null;
		Transaction transaction1 =null;
		List list  = null;
		try
		{
			System.out.println("in logout try block");
			hbu1 = HibernateUtility.getInstance();
			session1 = hbu1.getHibernateSession();
			transaction1 = session1.beginTransaction();
			Query query = session1.createSQLQuery("select  supplier_name,product_category,company_name,product_name,quantity,batchno,expiry_date FROM product_stock_details");
			list = query.list();

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return list;	
	}

	public void addDailyStockDetail(SaveDailyStock saveBean) {

		System.out.println("In saveDailyStock DAO");

		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			session.save(saveBean);
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

	//Stock report as per date
	
	public List<ProductStockDetailsBean> getStockDetailsAsPerDate(String firstDate) {

		HibernateUtility hbu=null;
		Session session=null;
		
		List<ProductStockDetailsBean> stockList = null;
		
		
		String yesterday1 = null;
		final Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		yesterday1 = dateFormat.format(cal.getTime());
		System.out.println("Yesterday Date if save_daily_stock is empty "+yesterday1);
		
		
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select psd.product_name,psd.packing,sd.supplier_name,psd.company_name,sds.dsquantity ,psd.quantity from product_stock_details psd LEFT JOIN supplier_details sd ON psd.supplier_name=sd.pk_supplier_id LEFT JOIN  save_daily_stock sds ON sds.product_name=psd.product_name AND sds.batch_number=psd.batchno AND sds.insert_date='"+yesterday1+"' WHERE psd.UpdateDate ='" + firstDate +"'");
			List<Object[]> list = query.list();
			stockList = new ArrayList<ProductStockDetailsBean>(0);
			
			


			for (Object[] object : list) {

				//StockDetail reports = new StockDetail();
				ProductStockDetailsBean reports=new ProductStockDetailsBean();
				reports.setProduct_name(object[0].toString());
				reports.setPacking(Double.parseDouble(object[1].toString()));
				reports.setSupplier_name(object[2].toString());
				reports.setCompany_name(object[3].toString());
				
				Long openingStock=Long.parseLong(object[4].toString());
				Long closingStock=Long.parseLong(object[5].toString());
				
				reports.setOpeningQty(openingStock);
				reports.setClosingQty(closingStock);
				
				System.out.println("=========opening stock"+openingStock);
				System.out.println("=========closing stock"+closingStock);
				
				Long sale=openingStock-closingStock;
				
				
				reports.setSale(sale);
				
				
				/*reports.setProductName(object[0].toString());
				reports.setCompanyName(object[1].toString());
				reports.setWeight(Double.parseDouble(object[2].toString()));
				reports.setBatchNo(object[3].toString());
				reports.setQuantity(Double.parseDouble(object[4].toString()));*/

				stockList.add(reports); 

			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return stockList;	
	}

	public List<SaleReports> getSaleReturnDetailsAsPerCategory(String cat) {
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT customer_name,bill_no,return_amount,c.first_name,c.last_name FROM fertilizer_billing LEFT JOIN customer_details c on c.pk_customer_id=fk_customer_id WHERE cat_id = '"+cat+"' AND return_amount>0 UNION SELECT customer_name,bill_no,return_amount,c.first_name,c.last_name FROM seed_pesticide_billing LEFT JOIN customer_details c on c.pk_customer_id=fk_customer_id WHERE cat_id = '"+cat+"' AND return_amount>0 UNION SELECT customer_name,bill_no,return_amount,c.first_name,c.last_name FROM pesticide_billing LEFT JOIN customer_details c on c.pk_customer_id=fk_customer_id WHERE cat_id = '"+cat+"' AND return_amount>0;");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);


			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				String custName = object[0].toString();
				System.out.println(object[2].toString()+"========= return amount");
				if(custName.equals("N/A")){
					reports.setCusomerName(object[3].toString() +" "+object[4].toString());
				}
				else{
					reports.setCusomerName(custName);
				}
				reports.setCustomerBill(Integer.parseInt(object[1].toString()));
				reports.setReturnedAmount(Double.parseDouble(object[2].toString()));;
				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> gstSummaryReportsBetweenTwoDates(String fDate, String sDate) {

		Double trans;
		Double hamali;
		System.out.println(fDate+"fDate in dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery(" SELECT bill_no,product_name, DATE(insert_date), sale_price,sum(quantity), total_per_product,sum(Tax_Amount) , total_per_product,tax_percentage,igstPercentage,cat_id FROM fertilizer_billing WHERE DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"'  group by tax_percentage,igstPercentage union SELECT bill_no,product_name, DATE(insert_date), sale_price,sum(quantity), total_per_product,sum(Tax_Amount) , total_per_product,tax_percentage,igstPercentage,cat_id FROM seed_pesticide_billing WHERE DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' group by tax_percentage,igstPercentage  union SELECT bill_no,product_name, DATE(insert_date), sale_price,sum(quantity), total_per_product,sum(Tax_Amount) , total_per_product,tax_percentage,igstPercentage,cat_id FROM pesticide_billing WHERE DATE(insert_date) BETWEEN '"+fDate+"' AND '"+sDate+"' group by tax_percentage,igstPercentage  ");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);

			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				reports.setQuantity3(((BigDecimal) object[4]));
				reports.setTax(Double.parseDouble(object[8].toString()));
				reports.setiGSTPerc(Double.parseDouble(object[9].toString()));
				reports.setCatId(Long.parseLong(object[10].toString()));

				Long catid=reports.getCatId();
				if(catid == 1){
					reports.setType("Fertilizer");
				}
				else if(catid == 2){
					reports.setType("Pesticide");
				}
				else if(catid == 3){
					reports.setType("Seed");
				}

				Double gstPerc=reports.getTax();
				Double igstPerc=reports.getiGSTPerc();
				if(gstPerc != 0.00){
					reports.setTaxAmnt(Double.parseDouble(object[6].toString()));
					Double taxAmnt=reports.getTaxAmnt();
					Double sgstAmnt=taxAmnt/2;
					reports.setSgstAmnt(sgstAmnt);
					reports.setCgstAmnt(sgstAmnt);
					reports.setIgstAmnt(0.0);
				}
				else if(igstPerc != 0.00){
					reports.setTaxAmnt(Double.parseDouble(object[6].toString()));
					Double taxAmnt=reports.getTaxAmnt();
					reports.setIgstAmnt(taxAmnt);
					reports.setSgstAmnt(0.0);
					reports.setCgstAmnt(0.0);

				}
				else{
					continue;
				}

				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	

	}

	public List<SaleReports> gstPurchaseSummaryReportsBetweenTwoDates(String fDate, String sDate) {

		Double trans;
		Double hamali;
		System.out.println(fDate+"fDate in dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery(" SELECT bill_number,product_name, DATE(purchaseDate), sale_price,sum(quantity), gross_total,sum(Tax_Amount) , discount_amount,tax_percentage,igstPercentage FROM goods_receive WHERE DATE(purchaseDate) BETWEEN '"+fDate+"' AND '"+sDate+"' group by tax_percentage,igstPercentage");

			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);


			for (Object[] object : list) {

				SaleReports reports = new SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setSoldDate(object[2].toString());
				reports.setSalePrice(Double.parseDouble(object[3].toString()));
				reports.setQnty((object[4]).toString());
				reports.setTax(Double.parseDouble(object[8].toString()));
				reports.setiGSTPerc(Double.parseDouble(object[9].toString()));

				Double gstPerc=reports.getTax();
				Double igstPerc=reports.getiGSTPerc();
				if(gstPerc != 0.00){
					reports.setTaxAmnt(Double.parseDouble(object[6].toString()));
					Double taxAmnt=reports.getTaxAmnt();
					Double sgstAmnt=taxAmnt/2;
					reports.setSgstAmnt(sgstAmnt);
					reports.setCgstAmnt(sgstAmnt);
					reports.setIgstAmnt(0.0);
				}
				else if(igstPerc != 0.00){
					reports.setTaxAmnt(Double.parseDouble(object[6].toString()));
					Double taxAmnt=reports.getTaxAmnt();
					reports.setIgstAmnt(taxAmnt);
					reports.setSgstAmnt(0.0);
					reports.setCgstAmnt(0.0);

				}
				else{
					continue;
				}

				saleList.add(reports);
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}

	public List<SaleReports> getExpenseDetailsAsGST(String endDate, String startDate) {

		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReports> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			Query query = session.createSQLQuery("SELECT s.supplier_name, g.bill_number, g.expenses,g.GST_For_Trans, g.hamali_expense,g.GST_For_Hamali,g.insertDate FROM goods_receive g LEFT JOIN supplier_details s on g.fk_supplier_id = s.pk_supplier_id WHERE insertDate between '" + startDate +"' and '"+endDate+"' GROUP BY bill_number");
			List<Object[]> list = query.list();
			saleList= new ArrayList<SaleReports>(0);
			for (Object[] o : list) {
				SaleReports reports = new SaleReports();
				reports.setCusomerName(o[0].toString());
				reports.setBillNo(Long.parseLong(o[1].toString()));
				reports.setTransExpense((BigDecimal)o[2]);
				reports.setFivePercentageGST(Double.parseDouble(o[3].toString()));
				reports.setiGSTFivePercentage(Double.parseDouble(o[5].toString()));
				reports.setHamaliexpense((BigDecimal)o[4]);
				reports.setSaleDate(o[6].toString());

				double trans = Double.parseDouble(o[2].toString());
				double transGST = Double.parseDouble(o[3].toString());
				double hamali = Double.parseDouble(o[4].toString());
				double hamaliGST = Double.parseDouble(o[5].toString());
				double transTaxAmount = (trans)*(transGST/100);
				double hamaliTaxAmount = (hamali)*(hamaliGST/100);
				double totalTax = transTaxAmount + hamaliTaxAmount;

				reports.setTransTaxAmount(transTaxAmount);
				reports.setHamaliTransExpense(hamaliTaxAmount);
				reports.setTotalTaxAmount(totalTax);

				saleList.add(reports);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return saleList;	
	}
	
	public List getAllEntry()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from GoodsReceiveBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getAllLoanDetails", e);
		}

		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		return list;
	}
	
	
	public List getAllPurchaseBillNo()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from GoodsReceiveBean");
			list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getPurchaseBillNo", e);
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
