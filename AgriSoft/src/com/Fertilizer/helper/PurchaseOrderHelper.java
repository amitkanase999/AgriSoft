package com.Fertilizer.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.GetPODetailsForDcUpdate;
import com.Fertilizer.bean.GetProductDetailPO;
import com.Fertilizer.bean.GetPurchaseOrderDetails;
import com.Fertilizer.dao.PurchaseOrderDao;
import com.Fertilizer.hibernate.PurchaseOrderBean;

public class PurchaseOrderHelper {

	Long poNumber;

	public void pODetails(HttpServletRequest request,
			HttpServletResponse response) {

		PurchaseOrderBean bean = new PurchaseOrderBean();
		PurchaseOrderDao dao1=new PurchaseOrderDao();
		List bill = dao1.getPONumber();
		int billsize = bill.size();
		for(int i=0;i<billsize;i++){
			GetPurchaseOrderDetails pod = (GetPurchaseOrderDetails)bill.get(i);
			poNumber = pod.getpONumber();
			System.out.println(poNumber);

			poNumber++;
			System.out.println(poNumber);
		}

		Integer count = Integer.parseInt(request.getParameter("count"));
		Double totalAmount=0d;
		for(int i =0 ; i<=count;i++)
		{
			Date dateobj = new Date();
			bean.setInsertDate(dateobj);
			String supplier = request.getParameter("fk_supplier_id");
			bean.setSupplier(Long.parseLong(supplier));
			String fk_cat_id = request.getParameter("fk_cat_id");
			bean.setFk_cat_id(Long.parseLong(fk_cat_id));
			String gross = request.getParameter("grossTotal");
			String advanceTokenAmount = request.getParameter("advanceTokenAmount");
			if(gross != null){
				bean.setGross(Double.parseDouble(gross));
			}
			else{
				bean.setGross(0.0);
			}

			String productID = request.getParameter("productID"+i);
			if(productID==null){
				break;
			}
			else{
				bean.setProductID(Long.parseLong(productID));
			}

			String unitName = request.getParameter("unitName"+i);
			if(unitName != null){
				bean.setUnitName(unitName);
			}
			else{
				bean.setUnitName("N/A");
			}

			String productName = request.getParameter("productName"+i);
			if(productName==null){
				break;
			}
			else{
				bean.setProductName(productName);
			}

			String buyPrice = request.getParameter("buyPrice"+i);
			if(buyPrice==null){
				break;
			}
			else{
				bean.setBuyPrice(Double.parseDouble(buyPrice));
			}

			String salePrice = request.getParameter("salePrice"+i);
			if(salePrice==null){
				break;
			}
			else{
				bean.setSalePrice(Double.parseDouble(salePrice));
			} 

			String weight = request.getParameter("weight"+i);
			if(weight==null){
				break;
			}
			else{
				bean.setWeight(Double.parseDouble(weight));
			}

			String quantity = request.getParameter("quantity"+i);
			if(quantity==null){
				break;
			}
			else{
				bean.setQuantity(Long.parseLong(quantity));
			}

			String manufacturer = request.getParameter("manufacturer"+i);
			if(manufacturer==null){
				break;
			}
			else{
				bean.setManufacturer(manufacturer);;;
			}

			String mrp = request.getParameter("mrp"+i);
			System.out.println("MRP +++++"+mrp);
			if(mrp != null){
				bean.setMrp(Double.parseDouble(mrp));
			}
			else{
				bean.setMrp(0.0);
			}

			String token = request.getParameter("token"+i);

			if(token != null){

				Double token1 = Double.parseDouble(token);
				Long quantity1 = Long.parseLong(quantity);

				Double totalamont = (double)(token1 * quantity1);

				bean.setTokenRate(token1);
				bean.setTotalAmount(totalamont);
				System.out.println(totalAmount+" is total amount With tax*******************");
			}
			else{
				bean.setTokenRate(0.0);
				bean.setTotalAmount(0.0);
				System.out.println(totalAmount+" is total amount*******************");
			}

			if(poNumber == null){
				bean.setPoNumber(1l);
			}
			else
			{
				bean.setPoNumber(poNumber);	
			}

			dao1.addPurchaseOrderDetails(bean);
		}
	}

	public Map getProductDetailsForPO(String productId) {

		PurchaseOrderDao dao = new PurchaseOrderDao();
		List list = dao.getPODetails(productId);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setQuantity(1l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getPODetailsForDcUpdate(String poNumber) {

		System.out.println("In Helper");
		PurchaseOrderDao dao = new PurchaseOrderDao();
		List list = dao.getPODetails(poNumber);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetPODetailsForDcUpdate bean = new GetPODetailsForDcUpdate();

			bean.setPkPOId((BigInteger)o[0]);
			bean.setDcNum((BigInteger)o[1]);
			bean.setProductName((String)o[2]);
			bean.setBuyPrice((BigDecimal)o[3]);
			bean.setSalePrice((BigDecimal)o[4]);
			bean.setWeight((BigDecimal)o[5]);
			bean.setQuantity((BigInteger)o[6]);
			bean.setTotalAmount((BigDecimal)o[7]);
			map1.put(bean.getPkPOId(),bean);
		}
		return map1;
	}
}
