package com.Fertilizer.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Fertilizer.bean.PurchaseDeatilsResultBean;
import com.Fertilizer.bean.PurchaseDetailsFromGoodsReceive;
import com.Fertilizer.dao.GoodsReceiveDao;
import com.Fertilizer.dao.ProductStockDetailsDao;
import com.Fertilizer.dao.PurchaseRetunDao;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.hibernate.PurchaseReturnBean;
import com.Fertilizer.utility.HibernateUtility;

public class PurchaseReturnHelper {

	public void  insertPurchaseRetun(HttpServletRequest request,HttpServletResponse response) throws ParseException
	{
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		int i=0;
		String bill_no=request.getParameter("bill_no");
		System.out.println("======================="+bill_no);
		String supplier_name=request.getParameter("supplier_name"+i);
		System.out.println(supplier_name);
		String product_name=request.getParameter("product_name"+i);
		String category=request.getParameter("catName"+i);
		System.out.println(category);
		String batch_no=request.getParameter("batch_no"+i);
		String packing=request.getParameter("weight"+i);
		String buy_price=request.getParameter("buy_price"+i);
		String sale_price=request.getParameter("sale_price"+i);
		String MRP=request.getParameter("mrp"+i);
		String tax_per=request.getParameter("tax_percentage"+i);
		String quantity=request.getParameter("quantity"+i);
		String retun_quantity=request.getParameter("dupQuantity"+i);
		String dc_no=request.getParameter("dc_number"+i);
		String barcode_no=request.getParameter("barcodeNo"+i);
		String purchase_date=request.getParameter("strDate"+i);
		System.out.println("Purchase Date before parsing"+purchase_date);

		System.out.println("===="+supplier_name+" "+product_name+" "+category);
		PurchaseReturnBean bean=new PurchaseReturnBean();

		if(bill_no != null){
			bean.setBill_no(Long.parseLong(bill_no));
		}
		else{
			bean.setBill_no(0l);
		}

		if(supplier_name != null){
			bean.setSupplier_name(supplier_name);
		}
		else{
			bean.setSupplier_name("N/A");
		}

		if(product_name != null){
			bean.setProduct_name(product_name);
		}
		else{
			bean.setProduct_name("N/A");
		}
		if(category != null){
			bean.setCategory(category);
		}
		else{
			bean.setCategory("N/A");
		}
		if(batch_no != null){
			bean.setBatch_no(batch_no);
		}
		else{
			bean.setBatch_no("N/A");
		}
		if(packing != null){
			bean.setPacking(Long.parseLong(packing));
		}
		else{
			bean.setPacking(0l);
		}
		if(buy_price != null){
			bean.setBuy_price(Double.parseDouble(buy_price));
		}
		else{
			bean.setBuy_price(0.0d);
		}
		if(sale_price != null){
			bean.setSale_price(Double.parseDouble(sale_price));
		}
		else{
			bean.setSale_price(0.0d);
		}
		if(MRP != null){
			bean.setMRP(Double.parseDouble(MRP));
		}
		else{
			bean.setMRP(0.0d);
		}
		if(tax_per != null){
			bean.setTax_per(Double.parseDouble(tax_per));
		}
		else{
			bean.setTax_per(0.0d);
		}
		if(quantity != null){
			bean.setQuantity(Long.parseLong(quantity));
		}
		else{
			bean.setQuantity(0l);
		}
		if(retun_quantity != null){
			bean.setRetun_quantity(Long.parseLong(retun_quantity));
		}
		else{
			bean.setRetun_quantity(0l);
		}
		if(dc_no != null){
			bean.setDc_no(dc_no);
		}
		else{
			bean.setDc_no("N/A");
		}
		if(barcode_no != null){
			bean.setBarcode_no(Long.parseLong(barcode_no));
		}
		else{
			bean.setBarcode_no(0l);  
		}
		Date aDate = null;
		aDate = dateFormat1.parse(purchase_date);
		if(purchase_date != null){
			bean.setPurchase_date(aDate);
			System.out.println("Purchase Date"+purchase_date);
		}
		else{

			bean.setPurchase_date(null);
		}

		
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));

		bean.setReturn_date(dateobj);
		System.out.println("Return Date"+dateobj);

		PurchaseRetunDao dao=new PurchaseRetunDao();
		dao.insertPurchaseRetun(bean);
		
		
		/*----------- Update quantity in Product_Stock_Details Table --------------------*/
        
		HibernateUtility hbu3 = HibernateUtility.getInstance();
		Session session3 = hbu3.getHibernateSession();
		
		System.out.println("=====proname"+product_name);
		System.out.println("=====batchNum"+batch_no);
		//System.out.println("=====expiry"+expiry);
		//Query to get latest paid amount
		Query query = session3.createSQLQuery("SELECT pk_stock_id,quantity from product_stock_details where product_name=:product_name and batchno=:batch_no");
		query.setParameter("product_name", product_name);
		query.setParameter("batch_no", batch_no);
		//query.setParameter("expiry", expiry);
		
		System.out.println("====product_name p1 : "+product_name);
		System.out.println("====batch_no p2:"+batch_no);
		//System.out.println("====expiry p3:"+expiry);
		List<Object[]> list = query.list();
		
		System.out.println("========List : "+list);
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
		
		ProductStockDetailsDao dao1=new ProductStockDetailsDao();
		List allpro=dao1.getAllEntry();
	
        
        System.out.println("List size"+allpro.size());
		
        for(int j=0;j<allpro.size();j++){
        	
        	
        ProductStockDetailsBean psd = (ProductStockDetailsBean)allpro.get(j);
		Long pk_stock_id=psd.getPk_stock_id();
       
		
		System.out.println("=======pkStockId : "+pkStockId);
		System.out.println("=======pk_stock_id : "+pk_stock_id);
		
		if(pkStockId.equals(String.valueOf(pk_stock_id)))
		{
		String quantity1 = request.getParameter("dupQuantity"+i);
		System.out.println("=============="+quantity1);
		Double uquantity=Double.parseDouble(quantity1);
		System.out.println("==================================grid qty"+quantity1);
		System.out.println("==================================db stock qty"+qty);
		
			Double	updatedqty=qty-uquantity;
			System.out.println("==================update qty"+updatedqty);
			Long l = (new Double(updatedqty)).longValue();
			System.out.println("inside if");
			
			ProductStockDetailsBean updatequantity = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class, new Long(pkStockId));
			updatequantity.setQuantity(l);
		 
			session.saveOrUpdate(updatequantity);
			transaction.commit();
			System.out.println("updated sucessfully");
		}
      
    }
        /*-----------END OF  Update quantity in Product_Stock_Details Table --------------------*/

		
		
		/*----------- Update quantity in GoodReceive Table --------------------*/
        
		HibernateUtility hbu4 = HibernateUtility.getInstance();
		Session session4 = hbu4.getHibernateSession();
		
		System.out.println("=====proname"+product_name);
		System.out.println("=====batchNum"+batch_no);
		//System.out.println("=====expiry"+expiry);
		//Query to get latest paid amount
		Query query1 = session4.createSQLQuery("SELECT pk_goods_receive_id,quantity from goods_receive where product_name=:product_name and batch_no=:batch_no");
		query1.setParameter("product_name", product_name);
		query1.setParameter("batch_no", batch_no);
		//query.setParameter("expiry", expiry);
		
		System.out.println("====product_name p1 : "+product_name);
		System.out.println("====batch_no p2:"+batch_no);
		//System.out.println("====expiry p3:"+expiry);
		List<Object[]> list1 = query1.list();
		
		System.out.println("========List : "+list1);
		System.out.println("Calc total");
		String pk_goods_receive_id="";
		Double qty1=0.0d;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			pk_goods_receive_id = objects[0].toString();
		    qty1=Double.parseDouble(objects[1].toString());
			
		    System.out.println("------------ StockID"+pk_goods_receive_id);
            //updatedqty = Double.valueOf(qty);
			System.out.println(qty+"  bal");
		}
		
		Configuration configuration1 = new Configuration();
        configuration1.configure("hibernate.cfg.xml");
        SessionFactory factory1 = configuration.buildSessionFactory();
        Session session1 = factory1.openSession();
       
		Transaction transaction1 = session1.beginTransaction();
		
		//ProductStockDetailsDao dao2=new ProductStockDetailsDao();
		//List allpro1=dao2.getAllEntry();
		
		GoodsReceiveDao dao2=new GoodsReceiveDao();
		List allpro1=dao2.getAllEntry();
	
        
        System.out.println("List size"+allpro1.size());
		
        for(int j=0;j<allpro1.size();j++){
        	
        	
        GoodsReceiveBean grb = (GoodsReceiveBean)allpro1.get(j);
		Long pk_stock_id=grb.getPkGoodsReceiveId();
       
		
		System.out.println("=======pk_goods_receive_id : "+pk_goods_receive_id);
		System.out.println("=======pk_stock_id : "+pk_stock_id);
		
		if(pk_goods_receive_id.equals(String.valueOf(pk_stock_id)))
		{
		String quantity1 = request.getParameter("dupQuantity"+i);
		Double uquantity=Double.parseDouble(quantity1);
		System.out.println("==================================grid qty"+quantity1);
		System.out.println("==================================db stock qty"+qty);
		
			Double	updatedqty=qty-uquantity;
			System.out.println("==================update qty"+updatedqty);
			//Long l = (new Double(updatedqty)).longValue();
			System.out.println("inside if");
			
			GoodsReceiveBean updatequantity = (GoodsReceiveBean) session.get(GoodsReceiveBean.class, new Long(pkStockId));
			updatequantity.setQuantity(updatedqty);
		 
			session1.saveOrUpdate(updatequantity);
			transaction1.commit();
			System.out.println("good receive updated sucessfully");
		}
      
    }
        /*-----------END OF  Update quantity in GoodReceive Table --------------------*/
        
        

	}

	/*purchase Return Report supplier name wise*/

	public List getPurchaseReturnDetailsAsPerSupplierName(HttpServletRequest request,
			HttpServletResponse response) {

		String supplier = request.getParameter("supplier");

		System.out.println(supplier+"Supplier in Helper");

		Map<Long,com.Fertilizer.bean.PurchaseReturnBean> map = new HashMap<Long,com.Fertilizer.bean.PurchaseReturnBean>();

		PurchaseRetunDao dao=new PurchaseRetunDao();
		List<com.Fertilizer.bean.PurchaseReturnBean> expList = dao.getPurchaseDetailsForSupplier(supplier);

		return expList;
	}

	public List getPurchaseDetailByTwoDate(HttpServletRequest request,HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long,com.Fertilizer.bean.PurchaseReturnBean> map = new HashMap<Long,com.Fertilizer.bean.PurchaseReturnBean>();

		PurchaseRetunDao dao=new PurchaseRetunDao();
		List<com.Fertilizer.bean.PurchaseReturnBean> exp1List =  dao.getPurchaseReportsBetweenTwoDates(fDate,tDate);  

		return exp1List;
	}
}
