package com.Fertilizer.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.bean.BillBean;
import com.Fertilizer.bean.CustomerBean;
import com.Fertilizer.bean.GetPODetailsForDcUpdate;
import com.Fertilizer.bean.GetPODetailsForGoodsReceive;
import com.Fertilizer.bean.GetPurchaseProduct;
import com.Fertilizer.bean.PurchaseDeatilsResultBean;
import com.Fertilizer.bean.PurchaseDetailsFromGoodsReceive;
import com.Fertilizer.bean.SaleReports;
import com.Fertilizer.bean.StockDetail;
import com.Fertilizer.dao.GoodsReceiveDao;
import com.Fertilizer.dao.ProductStockDetailsDao;
import com.Fertilizer.dao.StockDao;
import com.Fertilizer.dao.SupplierGrossDao;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.hibernate.SaveDailyStock;
import com.Fertilizer.hibernate.Stock;
import com.Fertilizer.hibernate.SupplierGrossBean;
import com.Fertilizer.utility.HibernateUtility;

public class GoodsReceiveHelper {

	Long barcodeNo;
	String catName;
	String productName;
	String companyName;
	Long PkStockId;
	Double quantityFromStockTable;
	Double interGST;
	Double taxPercentage;
	
	

	public void goodsReceived(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		
		String supplier="";
		String godownid="";
		String godownName="";
		String billNum="";
		String grossTotal="";

		GoodsReceiveDao dao = new GoodsReceiveDao();
		
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("Count in helper  = =" + count);

		GoodsReceiveBean bean = new GoodsReceiveBean();
		for (int i = 0; i < count; i++) {
			HttpSession session3 = request.getSession();
			GoodsReceiveDao data = new GoodsReceiveDao();
			List stkList = data.getLastBarcodeNo();
			System.out.println("Count in helper  For()= =" + count);
			for (int j = 0; j < stkList.size(); j++) {

				BillBean st = (BillBean) stkList.get(j);
				barcodeNo = st.getBarcodeNo();
				barcodeNo++;
			}

			godownid = request.getParameter("godownid");
			System.out.println("============================fk_godown_id="+ godownid);
			godownName = request.getParameter("godown");
			supplier = request.getParameter("supplier");
			String fk_cat_id = request.getParameter("fk_cat_id");
			System.out.println("============================product category="+ fk_cat_id);
			String catName = request.getParameter("catName");
			System.out.println("============================product category Name="+ catName);
			String dcNum = request.getParameter("dcNum");
			String transExpence = request.getParameter("transExpence");
			String hamaliExpence = request.getParameter("hamaliExpence");
		    grossTotal = request.getParameter("grossTotal");
			String dueDate = request.getParameter("dueDate");
			String purchaseDate = request.getParameter("purchaseDate");
			String productID = request.getParameter("productID" + i);
			String mrp = request.getParameter("mrp" + i);
			String iGst = request.getParameter("iGst" + i);
			String cGst = request.getParameter("cGst" + i);
			String sGst = request.getParameter("sGst" + i);
			String hsn = request.getParameter("hsn" + i);

			System.out.println("cGst==" + cGst);
			System.out.println("sGst==" + sGst);
			System.out.println("iGst==" + iGst);
			String companyName = request.getParameter("companyName" + i);
			System.out.println(companyName + "companyName");
			fk_cat_id = request.getParameter("catId" + i);
			String productName = request.getParameter("productName" + i);
			String buyPrice = request.getParameter("buyPrice" + i);
			String salePrice = request.getParameter("salePrice" + i);
			String weight = request.getParameter("weight" + i);
			System.out.println("weight ====="+weight);
			String quantity = request.getParameter("quantity" + i);
			String unitName = request.getParameter("unitName" + i);
			String batchNo = request.getParameter("batchNo" + i);
			String expiryDate = request.getParameter("expiryDate" + i);
			String Total = request.getParameter("Total" + i);

			taxPercentage = (Double.parseDouble(cGst))
					+ (Double.parseDouble(sGst));
			interGST = Double.parseDouble(iGst);

			
			System.out.println(productID + "poID");
			if (productID == null) {

				break;
			} else {
				bean.setPkPOId(Long.parseLong(productID));
			}

			if (companyName == null) {
				break;
			} else {
				bean.setCompanyName(companyName);
			}

			if (fk_cat_id == null) {
				break;
			} else {
				bean.setFkCategoryId(Long.parseLong(fk_cat_id));
			}

			System.out.println(fk_cat_id + "fk_cat_id");

			if (productName == null) {
				break;
			} else {
				bean.setProductName(productName);
			}

			if (buyPrice == null) {
				break;
			} else {
				bean.setBuyPrice(Double.parseDouble(buyPrice));
			}

			if (salePrice == null) {
				break;
			} else {
				bean.setSalePrice(Double.parseDouble(salePrice));
			}

			if (weight == null) {
				break;
			} else {
				bean.setWeight(Double.parseDouble(weight));
			}

			if (quantity == null) {
				break;
			} else {
				bean.setQuantity(Double.parseDouble(quantity));
				bean.setDupQuantity(Double.parseDouble(quantity));
			}

			if (batchNo == null) {
				bean.setBatchNo("N/A");
			} else {
				bean.setBatchNo(batchNo);
			}

			if (hsn == null) {
				bean.setHsn("N/A");
			} else {
				bean.setHsn(hsn);
			}

			System.out.println("Expiry Date" + expiryDate);
			if (expiryDate == "" | expiryDate == "undefined"
					| expiryDate == null) {

				/*
				 * SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); Date pdd2=null;
				 */
				// pdd2 = dateFormat.parse(expiryDate);
				// Date expiryDate1=new Date();
				Date dt = new Date(2010, 3, 5, 0, 0);
				bean.setExpiryDate(dt);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date pDD = null;
				try {
					pDD = dateFormat.parse(expiryDate);
					bean.setExpiryDate(pDD);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			session3.setAttribute("barcodeNo", barcodeNo);

			if (barcodeNo == null) {
				bean.setBarcodeNo(1000l);
			} else {
				bean.setBarcodeNo(barcodeNo);
			}

			String discount = request.getParameter("discount");
			String discountAmount = request.getParameter("discountAmount");
			billNum = request.getParameter("billNum");

			Date date = new Date();
			bean.setInsertDate(date);

			if (mrp == null) {
				bean.setMrp(0.0);
			} else {
				bean.setMrp(Double.parseDouble(mrp));
			}
			System.out.println("taxPercentage = = =" + taxPercentage);
			if (taxPercentage == null || taxPercentage == 0) {
				bean.setTaxPercentage(0.0);
			} else {
				bean.setTaxPercentage(taxPercentage);

			}
			System.out.println("interGST = = =" + interGST);
			if (interGST == null || interGST == 0) {
				bean.setiGstPercentage(0.0);
			} else {
				bean.setiGstPercentage(interGST);

			}
			System.out.println("Total = = =" + Total);
			if (Total == null) {
				bean.setTotalAmount(0.0);
			} else {
				bean.setTotalAmount(Double.parseDouble(Total));
			}
			System.out.println("grossTotal = = =" + grossTotal);
			if (grossTotal == null) {
				bean.setGrossTotal(0.0);
			} else {
				bean.setGrossTotal(Double.parseDouble(grossTotal));
			}

			Double gstPerc = taxPercentage;
			Double igstPerc = Double.parseDouble(iGst);
			if (gstPerc != 0.0) {
				Double taxAmnt = ((gstPerc / 100) * Double.parseDouble(Total));
				bean.setTaxAmount(taxAmnt);
			} else if (igstPerc != 0.0) {
				Double taxAmnt = ((igstPerc / 100) * Double.parseDouble(Total));
				bean.setTaxAmount(taxAmnt);
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date purchaseDateq = null;
			try {
				purchaseDateq = dateFormat.parse(purchaseDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			bean.setPurchaseDate(purchaseDateq);

			Date dueDateq = null;
			try {
				dueDateq = dateFormat.parse(dueDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int quant = Integer.parseInt(quantity);

			try {

				for (int x = 0; x < quant; x++) {

					FileInputStream fstream = new FileInputStream(
							"D:/barcose/input.prn");

					BufferedReader br = new BufferedReader(
							new InputStreamReader(fstream));
					FileWriter fw = new FileWriter("D:/barcose/Output.prn");

					BufferedWriter bw = new BufferedWriter(fw);
					String strLine;
					String str1;
					while ((strLine = br.readLine()) != null) {

						if (strLine.equals("~product")) {
							str1 = br.readLine();
							strLine = str1 + "\"" + productName + "\"";
						} else if (strLine.equals("~company")) {
							str1 = br.readLine();
							strLine = str1 + "\"" + companyName + "\"";

						} else if (strLine.equals("~quanti")) {
							str1 = br.readLine();
							strLine = str1 + "\"" + quantity + "\"";

						} else if (strLine.equals("~bar")) {
							str1 = br.readLine();
							strLine = str1 + "\"" + barcodeNo + "\"";
						}

						System.out.println(strLine);
						bw.write(strLine + "\r\n");
					}

					bw.close();
					br.close();

					List cmdAndArgs = Arrays.asList("cmd", "/c",
							"printbatch.bat");
					File dir = new File("C:/barcose");

					ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
					pb.directory(dir);
					Process p = pb.start();
				}
			} catch (Exception e) {
			}
			if (billNum == null) {
				bean.setBillNum("N/A");
			} else {
				bean.setBillNum(billNum);
			}

			if (discount == null) {
				bean.setDiscount(0.0);
			} else {
				bean.setDiscount(Double.parseDouble(discount));
			}

			if (discountAmount == null) {
				bean.setDiscountAmount(0.0);
			} else {
				bean.setDiscountAmount(Double.parseDouble(discountAmount));
			}
			String GSTHamali = request.getParameter("GSThamaliExpence");
			String GSTTrans = request.getParameter("GSTtransExpence");
			String billtype = request.getParameter("billtype");
			bean.setGSTforHamali(Double.parseDouble(GSTHamali));
			bean.setGSTForTrans(Double.parseDouble(GSTTrans));
			bean.setDueDate(dueDateq);
			bean.setDcNum(Long.parseLong(dcNum));
			bean.setBillType(billtype);
			bean.setExpenses(Double.parseDouble(transExpence));
			bean.setHamali(Double.parseDouble(hamaliExpence));
			bean.setSupplier(Long.parseLong(supplier));
			bean.setFk_Godown_Id(Long.parseLong(godownid));
			bean.setGodownName(godownName);
			Long newIdForUpdate = Long.parseLong(productID);
			
			dao.updateProductStatus(newIdForUpdate);
			dao.addGoodsReceive(bean);

		
			if (catName.equals("Fertilizer")) {

				StockDao dao1 = new StockDao();
				List stkList2 = dao1.getAllStockEntry();

				/* If Stock Is Empty */

				if (stkList2.size() == 0) {
					System.out.println("In if block of stock empty");
			

					Date updateDate = new Date();
					ProductStockDetailsBean psbean = new ProductStockDetailsBean();

					psbean.setSupplier_name(supplier);
					psbean.setGodownName(godownName);
					psbean.setCompanyname(companyName);
					psbean.setProduct_category(catName);
					psbean.setProduct_name(productName);
					psbean.setPacking(Double.parseDouble(weight));
					psbean.setQuantity(Long.parseLong(quantity));
					psbean.setUnitName(unitName);
					psbean.setBatchno(batchNo);
					psbean.setUpdateDate(updateDate);

					SimpleDateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date expiryDate1 = null;
					try {
						expiryDate1 = dateFormat1.parse(expiryDate);
						psbean.setExpiryDate(expiryDate1);
					} catch (ParseException e) {
						e.printStackTrace();
					}

					ProductStockDetailsDao psdao = new ProductStockDetailsDao();
					psdao.addProductInProductStockDetails(psbean);

				} else

				/* To Update Stock Table If It is Not Empty */
				{
					// for fertilizer

					for (int j = 0; j < stkList2.size(); j++) {

					

						ProductStockDetailsBean st = (ProductStockDetailsBean) stkList2.get(j);
						String itemName = st.getProduct_name();
						String Companyname = st.getCompanyname();
						Double wight = st.getPacking();
						Long pkstockid = st.getPk_stock_id();

						/* If ItemName Is Already Exists In Stock Table */

						System.out.println("fk_cat_id in stock = = "
								+ fk_cat_id);
						// System.out.println("batchNumber from stock table"+batchNumber);
						System.out.println("batchNo from goods receive"
								+ batchNo);
						System.out.println("In else Part");
						if (productName.equals(itemName)
								&& Companyname.equals(companyName)
								&& wight.equals(Double.parseDouble(weight))) {
							Long qunty = st.getQuantity();

							Long updatequnty = (qunty + Long
									.parseLong(quantity));

							HibernateUtility hbu = HibernateUtility
									.getInstance();
							Session session = hbu.getHibernateSession();
							Transaction transaction = session
									.beginTransaction();


							ProductStockDetailsBean updateStock = (ProductStockDetailsBean) session
									.get(ProductStockDetailsBean.class,
											new Long(pkstockid));
							updateStock.setQuantity(updatequnty);

							session.update(updateStock);
							transaction.commit();
							break;
						} else {
							/* ItemName is Not Exists */

							if (j + 1 == stkList2.size()) {

			

								Date updateDate = new Date();
								ProductStockDetailsBean psbean = new ProductStockDetailsBean();

								psbean.setSupplier_name(supplier);
								psbean.setGodownName(godownName);
								psbean.setCompanyname(companyName);
								psbean.setProduct_category(catName);
								psbean.setProduct_name(productName);
								psbean.setPacking(Double.parseDouble(weight));
								psbean.setQuantity(Long.parseLong(quantity));
								psbean.setUnitName(unitName);
								psbean.setBatchno(batchNo);
								psbean.setUpdateDate(updateDate);

								SimpleDateFormat dateFormat1 = new SimpleDateFormat(
										"yyyy-MM-dd");
								Date expiryDate1 = null;
								try {
									expiryDate1 = dateFormat1.parse(expiryDate);
									psbean.setExpiryDate(expiryDate1);
								} catch (ParseException e) {
									e.printStackTrace();
								}

								ProductStockDetailsDao psdao = new ProductStockDetailsDao();
								psdao.addProductInProductStockDetails(psbean);

							}
						}
					}
				}

			}
			else
			{
				
				//code for seed and Pesticide to add new entry and if it is available then update it
				
				StockDao dao1 = new StockDao();
				List stkList2 = dao1.getAllStockEntry();
				
				if (stkList2.size() == 0) {
					
					/* Add Entery to Product_Stock_Dedtails table  if product stock is empty*/
					Date updateDate = new Date();
					ProductStockDetailsBean psbean = new ProductStockDetailsBean();

					psbean.setSupplier_name(supplier);
					psbean.setGodownName(godownName);
					psbean.setCompanyname(companyName);
					psbean.setProduct_category(catName);
					psbean.setProduct_name(productName);
					psbean.setPacking(Double.parseDouble(weight));
					psbean.setQuantity(Long.parseLong(quantity));
					psbean.setUnitName(unitName);
					psbean.setBatchno(batchNo);
					psbean.setUpdateDate(updateDate);

					SimpleDateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date expiryDate1 = null;
					try {
						expiryDate1 = dateFormat1.parse(expiryDate);
						psbean.setExpiryDate(expiryDate1);
					} catch (ParseException e) {
						e.printStackTrace();
					}

					ProductStockDetailsDao psdao = new ProductStockDetailsDao();
					psdao.addProductInProductStockDetails(psbean);

					/* End Add Entery to Product_Stock_Dedtails table  if product stock is empty*/

					
				}
				else
				{
						for (int j = 0; j < stkList2.size(); j++) 
						{

								ProductStockDetailsBean st = (ProductStockDetailsBean) stkList2.get(j);
								String itemName = st.getProduct_name();
								String Companyname = st.getCompanyname();
								Double wight = st.getPacking();
								String batchno=st.getBatchno(); 
								Long pkstockid = st.getPk_stock_id();

								/* If ItemName Is Already Exists In Stock Table */

								System.out.println("fk_cat_id in stock = = "+ fk_cat_id);		
								// System.out.println("batchNumber from stock table"+batchNumber);
								System.out.println("batchNo from goods receive"+ batchNo);
								System.out.println("In else Part");
								if (productName.equals(itemName) && Companyname.equals(companyName) && batchno.equals(batchNo) && wight.equals(Double.parseDouble(weight))) 
								{
									Long qunty = st.getQuantity();

									Long updatequnty = (qunty + Long.parseLong(quantity));

									HibernateUtility hbu = HibernateUtility.getInstance();
									Session session = hbu.getHibernateSession();
									Transaction transaction = session.beginTransaction();


									ProductStockDetailsBean updateStock = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class,new Long(pkstockid));
									updateStock.setQuantity(updatequnty);

									session.update(updateStock);
									transaction.commit();
									break;

								}
								else 
								{
						/* ItemName is Not Exists */

						if (j + 1 == stkList2.size()) {
							
							/* Add Entery to Product_Stock_Dedtails table */
							Date updateDate = new Date();
							ProductStockDetailsBean psbean = new ProductStockDetailsBean();

							psbean.setSupplier_name(supplier);
							psbean.setGodownName(godownName);
							psbean.setCompanyname(companyName);
							psbean.setProduct_category(catName);
							psbean.setProduct_name(productName);
							psbean.setPacking(Double.parseDouble(weight));
							psbean.setQuantity(Long.parseLong(quantity));
							psbean.setUnitName(unitName);
							psbean.setBatchno(batchNo);
							psbean.setUpdateDate(updateDate);

							SimpleDateFormat dateFormat1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date expiryDate1 = null;
							try {
								expiryDate1 = dateFormat1.parse(expiryDate);
								psbean.setExpiryDate(expiryDate1);
							} catch (ParseException e) {
								e.printStackTrace();
							}

							ProductStockDetailsDao psdao = new ProductStockDetailsDao();
							psdao.addProductInProductStockDetails(psbean);

							/* End Add Entery to Product_Stock_Dedtails table */


						}
				
					
					}
				
				}
			}

		}
	}
		/*------Start Add One Entry for gross total in supplier_gross Table-----*/
		SupplierGrossBean sgb = new SupplierGrossBean();
		sgb.setFk_supplier_id(Long.parseLong(supplier));
		sgb.setBillno(billNum);
		sgb.setGrosstotal(Double.parseDouble(grossTotal));
		SupplierGrossDao sgdao=new SupplierGrossDao();
		sgdao.addGrossInSupplierGross(sgb);
		
		/*------End Add One Entry for gross total in supplier_gross Table-----*/

		
	}

	public Map getAllDcNumbers(String supplierId) {
		GoodsReceiveDao dao = new GoodsReceiveDao();
		List list = dao.getAllDcNumbersBySuppliers(supplierId);
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			GetPODetailsForGoodsReceive bean = new GetPODetailsForGoodsReceive();
			System.out.println(Arrays.toString(o));
			bean.setDcNum(o[0].toString());
			bean.setInsertDate(o[1].toString());
			System.out.println("***************" + o[0]);
			map.put(bean.getDcNum(), bean);
		}
		return map;
	}

	public Map getPODetails(String dcNum, String supplier) {
		System.out.println("In Helper");
		GoodsReceiveDao dao = new GoodsReceiveDao();
		List list = dao.getPODetailsForGoodsReceive(dcNum, supplier);
		System.out.println(list.size());
		Map map1 = new HashMap();

		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			GetPODetailsForDcUpdate bean = new GetPODetailsForDcUpdate();

			bean.setPkPOId((BigInteger) o[0]);
			bean.setProductName((String) o[1]);
			bean.setBuyPrice((BigDecimal) o[2]);
			bean.setSalePrice((BigDecimal) o[3]);
			bean.setQuantity((BigInteger) o[4]);
			bean.setWeight((BigDecimal) o[5]);
			map1.put(bean.getPkPOId(), bean);
		}
		return map1;
	}

	public PurchaseDeatilsResultBean getPurchaseDetailByTwoDate(
			HttpServletRequest request, HttpServletResponse response) {

		String fDate = request.getParameter("fisDate");
		String tDate = request.getParameter("endDate");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		PurchaseDeatilsResultBean exp1List = dao
				.getPurchaseReportsBetweenTwoDates(fDate, tDate);

		return exp1List;
	}

	public List getStockDetailsAsPerGodownName(HttpServletRequest request,
			HttpServletResponse response) {

		String fkGodownId = request.getParameter("fkGodownId");

		Map<Long, StockDetail> map = new HashMap<Long, StockDetail>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<StockDetail> stockList = dao
				.getStockDetailsForReportAsGodown(fkGodownId);

		return stockList;

	}

	public CustomerBean getDetailsById(HttpServletRequest request,
			HttpServletResponse response) {

		String key = request.getParameter("key");
		System.out.println(key + "barcode");
		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao.getAllItemDetails(key);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	public CustomerBean getPesticideDetailsByBarcode(
			HttpServletRequest request, HttpServletResponse response) {

		String key = request.getParameter("key");
		System.out.println(key + "barcode");
		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao.getPesticideDetailByBarocde(key);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	// get purchase Item By Bill NoWise

	public Map getAllIetmByBillNo(String bill_no, String supplier) {

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List list = dao.getAllIetmByBillNo(bill_no, supplier);
		System.out.println(list.size());
		Map map1 = new HashMap();

		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			GetPurchaseProduct bean = new GetPurchaseProduct();

			bean.setPk_goods_receive_id((BigInteger) o[0]);
			bean.setSupplier_name((String) o[1]);
			bean.setDc_number((BigInteger) o[2]);
			bean.setProduct_name((String) o[3]);
			bean.setBuy_price((BigDecimal) o[4]);
			bean.setSale_price((BigDecimal) o[5]);
			bean.setWeight((BigDecimal) o[6]);
			bean.setQuantity((Double) o[7]);
			bean.setBatch_no((String) o[8]);
			BigInteger big1 = new BigInteger("1");
			BigInteger big2 = new BigInteger("2");
			BigInteger big3 = new BigInteger("3");
			System.out.println("cat Id  ===  " + o[9]);
			if (o[9].equals(big1)) {
				String ferti = "Fertilizer";
				bean.setCatName(ferti);
			}
			if (o[9].equals(big2)) {
				String Pesticide = "Pesticide";
				bean.setCatName(Pesticide);
			}
			if (o[9].equals(big3)) {
				String Seed = "Seed";
				bean.setCatName(Seed);
			}
			String strDate = o[10].toString();
			bean.setStrDate(strDate);
			bean.setMrp((BigDecimal) o[11]);
			bean.setTax_percentage((BigDecimal) o[12]);
			bean.setBarcodeNo((BigInteger) o[13]);
			bean.setCompany_Name((String) o[14]);
			bean.setDupQuantity1((Double) o[15]);
			map1.put(bean.getPk_goods_receive_id(), bean);
		}
		return map1;
	}

	// to purchase Return

	public void returntPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		Integer count = Integer.parseInt(request.getParameter("count"));
		for (int i = 0; i < count; i++) {
			String pkGoodsReceiveId = request
					.getParameter("pk_goods_receive_id" + i);
			String dupQuantity = request.getParameter("dupQuantity" + i);
			String product_name = request.getParameter("product_name" + i);
			String company_Name = request.getParameter("company_Name" + i);
			String weight = request.getParameter("weight" + i);
			String duplicateQuantity = request.getParameter("duplicateQuantity"
					+ i);
			String tax_percentage = request.getParameter("tax_percentage" + i);
			String buy_price = request.getParameter("buy_price" + i);
			System.out.println("currnt qunt : " + duplicateQuantity);
			System.out.println("minus qunt : " + dupQuantity);
			HibernateUtility hbu = null;
			Session session = null;
			Transaction transaction = null;

			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				transaction = session.beginTransaction();
				List<Object[]> list2 = null;
				List<GoodsReceiveBean> goodsList = null;
				Double grossTotal = 0.0;

				Double quantity = (double) (Double
						.parseDouble(duplicateQuantity) - Double
						.parseDouble(dupQuantity));
				System.out.println("after minus qunt : " + quantity);
				GoodsReceiveBean updateStock = (GoodsReceiveBean) session.get(
						GoodsReceiveBean.class, new Long(pkGoodsReceiveId));
				updateStock.setDupQuantity(quantity);

				Query query2 = session
						.createSQLQuery("SELECT gross_total,bill_number FROM goods_receive WHERE pk_goods_receive_id="
								+ pkGoodsReceiveId);
				list2 = query2.list();
				goodsList = new ArrayList<GoodsReceiveBean>();
				for (Object[] objects : list2) {
					grossTotal = Double.parseDouble(objects[0].toString());

				}
				Double buyPrice = Double.parseDouble(buy_price);
				Double taxPercentage = Double.parseDouble(tax_percentage);
				Double taxAmount = (taxPercentage / 100) * buyPrice;
				Double newBuyPrice = buyPrice + taxAmount;
				Double total = Double.parseDouble(dupQuantity) * newBuyPrice;
				Double newGrossTotal = grossTotal - total;
				System.out.println("buyPrice = =" + buyPrice);
				System.out.println("taxAmount = =" + taxAmount);
				System.out.println("newBuyPrice = =" + newBuyPrice);
				System.out.println("total = =" + total);
				System.out.println("newGrossTotal = =" + newGrossTotal);

				updateStock.setReturnAmount(total);
				updateStock.setTotalAfterReturn(newGrossTotal);
				session.saveOrUpdate(updateStock);
				transaction.commit();
			} catch (RuntimeException e) {
				try {
					transaction.rollback();
				} catch (RuntimeException rbe) {
					Log.error("Couldn't roll back tranaction", rbe);
				}
			} finally {
				hbu.closeSession(session);
			}
		}

	}

	public void returntMinusFromStockPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		Integer count = Integer.parseInt(request.getParameter("count"));
		for (int i = 0; i < count; i++) {
			String pkGoodsReceiveId = request
					.getParameter("pk_goods_receive_id" + i);
			String dupQuantity = request.getParameter("dupQuantity" + i);
			String product_name = request.getParameter("product_name" + i);
			String company_Name = request.getParameter("company_Name" + i);
			String weight = request.getParameter("weight" + i);
			System.out.println("minus qunt : " + dupQuantity);
			System.out.println("product_name : " + product_name);
			System.out.println(" company_Name : " + company_Name);
			System.out.println(" weight : " + weight);

			HibernateUtility hbu1 = null;
			Session session1 = null;
			Transaction transaction1 = null;

			try {
				Long PkStockId;
				Double quantity;
				hbu1 = HibernateUtility.getInstance();
				session1 = hbu1.getHibernateSession();
				transaction1 = session1.beginTransaction();

				Query query = session1
						.createSQLQuery("select PkStockId , Quantity from stock_detail where ProductName=:product_name AND CompanyName=:company_Name And Weight =:weight");
				query.setParameter("product_name", product_name);
				query.setParameter("company_Name", company_Name);
				query.setParameter("weight", weight);

				List<Object[]> list = query.list();

				for (Object[] object : list) {
					System.out.println(Arrays.toString(object));
					PkStockId = Long.parseLong(object[0].toString());
					quantity = Double.parseDouble(object[1].toString());
					System.out.println("PkStockId " + PkStockId);
					System.out.println("quantity " + quantity);

					Double updatequnty = (double) (quantity - Double
							.parseDouble(dupQuantity));
					System.out.println("updatequnty " + updatequnty);

					Stock Stock = (Stock) session1.load(Stock.class, new Long(
							PkStockId));

					Stock.setQuantity(updatequnty);

					session1.saveOrUpdate(Stock);
					transaction1.commit();
					System.out.println("Success ");
				}

			} catch (RuntimeException e) {
				try {
					transaction1.rollback();
				} catch (RuntimeException rbe) {
					Log.error("Couldn't roll back tranaction", rbe);
				}
			} finally {
				hbu1.closeSession(session1);
			}
		}

	}

	// fetching pro details from goods receive for ferti bill

	public CustomerBean getDetailsByProNameForzFertiBill(
			HttpServletRequest request, HttpServletResponse response) {

		String proName = request.getParameter("proName");
		String company = request.getParameter("company");
		String weight = request.getParameter("weight");

		System.out.println(proName + "pro name in gr helper");

		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao
				.getAllProductDetailsForFrtiBillAsPerProductName(proName,
						company, weight);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	// fetching product detail as per batch for seed bill

	public CustomerBean getDetailsByBatchNadStockForSeedBill(
			HttpServletRequest request, HttpServletResponse response) {

		String batchNum = request.getParameter("batchNum");
		String stock = request.getParameter("stock");
		System.out.println(" batchNum=== == =in helper" + batchNum);
		System.out.println(" stock=== == =in helper" + stock);
		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao
				.getAllProductDetailsForSeedBillAsPerBatchAndStock(batchNum,
						stock);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	public CustomerBean getProductDetailsForFertiBill(
			HttpServletRequest request, HttpServletResponse response) {

		String proName = request.getParameter("proName");
		String company = request.getParameter("company");
		String weight = request.getParameter("weight");
		String batchNum = request.getParameter("batchNum");
		String availableQty = request.getParameter("availableQty");

		System.out.println(proName + "pro name in gr helper");

		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao
				.getAllProductDetailsForFrtiBillAsPerProductName1(proName,
						company, weight, batchNum,availableQty);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	public CustomerBean getProductDetailsForPesticideBill(
			HttpServletRequest request, HttpServletResponse response) {

		String proName = request.getParameter("proName");
		String batchno = request.getParameter("batchno");
		String quantity = request.getParameter("quantity");
		String packing = request.getParameter("packing");
		String unit = request.getParameter("unit");
		
		System.out.println(proName + "pro name in gr helper");

		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao.getAllProductDetailsForPesticideBilling(proName,batchno, quantity,packing,unit);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	//get seed product
	public CustomerBean getProductDetailsForSeed(
			HttpServletRequest request, HttpServletResponse response) {

		String proName = request.getParameter("proName");
		String batchno = request.getParameter("batchno");
		String quantity = request.getParameter("quantity");
		//String packing = request.getParameter("packing");
		//String unit = request.getParameter("unit");
		
		System.out.println(proName + "pro name in gr helper");

		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao.getAllProductDetailsForSeed(proName,batchno, quantity);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}
	
	public CustomerBean getProductDetailsForPesticide(
			HttpServletRequest request, HttpServletResponse response) {

		String proName = request.getParameter("proName");
		String batchno = request.getParameter("batchno");
		String quantity = request.getParameter("quantity");

		System.out.println(proName + "pro name in gr helper");

		Map<Long, CustomerBean> map = new HashMap<Long, CustomerBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<CustomerBean> catList = dao.getAllProductDetailsForpesticide(
				proName, batchno, quantity);

		CustomerBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (CustomerBean) catList.get(0);
		}
		return cs;
	}

	public List getSaleDetailsAsPerCategoryForSingleDate(
			HttpServletRequest request, HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String fDate = request.getParameter("fDate");
		System.out.println(cat + "Category in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerCategoryForSingleDate(cat, fDate);

		return expList;
	}

	public List getSaleDetailsPerPaymentMode(HttpServletRequest request,
			HttpServletResponse response) {

		String paymentMode = request.getParameter("paymentMode");
		String fk_cat_id = request.getParameter("fk_cat_id");
		System.out.println(paymentMode + "paymentMode in Helper");
		System.out.println(fk_cat_id + "fk_cat_id in Helper");
		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.getSaleDetailsAsPerPaymentMode(
				paymentMode, fk_cat_id);
		return expList;
	}

	public List getSaleDetailsPerPaymentModeGorSingleDate(
			HttpServletRequest request, HttpServletResponse response) {

		String singleDate = request.getParameter("singleDate");
		String fk_cat_id = request.getParameter("fk_cat_id");
		System.out.println(singleDate + "singleDate in Helper");
		System.out.println(fk_cat_id + "fk_cat_id in Helper");
		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerPaymentModeForSingleDate(singleDate,
						fk_cat_id);
		return expList;
	}

	public List getSaleDetailsPerGSTPercentage(HttpServletRequest request,
			HttpServletResponse response) {

		String fk_cat_id = request.getParameter("fk_cat_id");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.getSaleDetailsAsGST(fk_cat_id, endDate,
				startDate);
		return expList;
	}

	public List getSaleDetailsAsPerCategoryBetTwoDates(
			HttpServletRequest request, HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String fDate = request.getParameter("fDate");
		String sDate = request.getParameter("sDate");
		System.out.println(cat + "Category in Helper");
		System.out.println(fDate + "fDate in Helper");
		System.out.println(sDate + "sDate in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerCategoryBetweeenTwoDates(cat, fDate, sDate);

		return expList;

	}

	public List getSaleDetailsAsPerProNameForSingleDate(
			HttpServletRequest request, HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String productName = request.getParameter("product");
		String fDate = request.getParameter("fDate");
		System.out.println(cat + "Category in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerProductNameForSingleDate(cat, fDate,
						productName);

		return expList;
	}

	public List getGrossTotal(HttpServletRequest request, HttpServletResponse response) {

		String category = request.getParameter("category");
		String billno = request.getParameter("billno");
		
		System.out.println(billno + "grosstotal in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.getGrossTotalAsPerBillNo(category,billno);

		return expList;
	}
	
	public List getStockDetailsAsPerCategory(HttpServletRequest request,
			HttpServletResponse response) {

		String cat = request.getParameter("fk_cat_id");
		System.out.println(cat + "Category in Helper");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsForReportAsPerCategory(cat);

		System.out.println("@@@ stock report Helper :: " + stockList);

		return stockList;

	}

	public List getStockDetailsAsCompanyName(HttpServletRequest request,
			HttpServletResponse response) {
		String companyName = request.getParameter("companyName");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsAsPerCompanyName(companyName);

		return stockList;

	}

	public PurchaseDeatilsResultBean getPurchaseDetailsForSingleDate(
			HttpServletRequest request, HttpServletResponse response) {

		String fDate = request.getParameter("fDate");
		System.out.println(fDate + "Single Date");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		PurchaseDeatilsResultBean bean = dao
				.getPurchaseDetailsForSingleDateFromGoodsReceive(fDate);

		return bean;
	}

	public List getPurchaseDetailsAsPerProduct(HttpServletRequest request,
			HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String productName = request.getParameter("proName");
		String company = request.getParameter("company");
		String weight = request.getParameter("weight");

		System.out.println(cat + "Category in Helper");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao
				.getPurchaseDetailsAsPerProduct(cat, productName, company,
						weight);

		return expList;
	}

	public List getPurchaseDetailsAsPerBillNo(HttpServletRequest request,
			HttpServletResponse response) {

		String supplier = request.getParameter("fk_supplier_id");	
		String billno = request.getParameter("billno");
		System.out.println(supplier + "Supplier in Helper");
		System.out.println(supplier + "Supplier in Helper");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao.getPurchaseDetailsForBillNoWise(supplier,billno);

		return expList;
	}
	public List getPurchaseDetailsAsPerSupplierName(HttpServletRequest request,
			HttpServletResponse response) {

		String supplier = request.getParameter("supplier");
		System.out.println(supplier + "Supplier in Helper");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao
				.getPurchaseDetailsForSupplier(supplier);

		return expList;
	}

	public List getPurchaseDetailsAsPerCategory(HttpServletRequest request,
			HttpServletResponse response) {

		String cat = request.getParameter("cat");
		System.out.println(cat + "Category in Helper");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao
				.getPurchaseDetailsForCategory(cat);

		return expList;
	}

	public List getSaleDetailsAsPerProductNameBetTwoDates(
			HttpServletRequest request, HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String fDate = request.getParameter("fDate");
		String sDate = request.getParameter("sDate");
		String product = request.getParameter("product");
		System.out.println(cat + "Category in Helper");
		System.out.println(fDate + "fDate in Helper");
		System.out.println(sDate + "sDate in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerProductNamesBetweeenTwoDates(cat, fDate,
						sDate, product);

		return expList;
	}

	public List getSaleDetailsAsPerSup(HttpServletRequest request,
			HttpServletResponse response) {

		String fkSupplierId = request.getParameter("fkSupplierId");
		System.out.println(fkSupplierId + "fkSupplierId in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerSupplierName(fkSupplierId);

		return expList;
	}

	public List getTaxDetailsFromPurchaseForSingleDateAsPerCategory(
			HttpServletRequest request, HttpServletResponse response) {

		String cat = request.getParameter("cat");
		String fDate = request.getParameter("fDate");
		String sDate = request.getParameter("sDate");
		System.out.println(cat + "Category in Helper");
		System.out.println(fDate + "fDate in Helper");
		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao
				.geTaxDetailsAsPerCategoryForSingleDate(cat, fDate, sDate);
		return expList;
	}

	public List getStockDetailsAsProductName(HttpServletRequest request,
			HttpServletResponse response) {
		String proName = request.getParameter("proName");
		String company = request.getParameter("company");
		String weight = request.getParameter("weight");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsAsPerProductName(proName, weight, company);

		return stockList;

	}

	public List getStockDetailsOnAvailableStockAsProductName(
			HttpServletRequest request, HttpServletResponse response) {
		String proName = request.getParameter("proName");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsBasedOnAvailableStockAsPerProductName(proName);

		return stockList;

	}

	public List getStockDetailsOnAvailableStockAsCategory(
			HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsBasedOnAvailableStockAsCategory(category);

		return stockList;

	}

	public List getSaleDetailsPerPaymentModeForTwoDate(
			HttpServletRequest request, HttpServletResponse response) {

		String singleDate = request.getParameter("singleDate");
		String secondDate = request.getParameter("secondDate");
		String fk_cat_id = request.getParameter("fk_cat_id");
		System.out.println(singleDate + "singleDate in Helper");
		System.out.println(fk_cat_id + "fk_cat_id in Helper");
		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.getSaleDetailsAsPerPaymentModeForTwoDate(singleDate,
						fk_cat_id, secondDate);
		return expList;
	}

	public List getPurchaseDetailByGST(HttpServletRequest request,
			HttpServletResponse response) {
		String fDate = request.getParameter("gstFisDate");
		String tDate = request.getParameter("gstEndDate");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> exp1List = dao
				.getPurchaseReportsASPerGST(fDate, tDate);

		return exp1List;
	}

	public List getPurchaseReturnDetailsAsPerSupplierName(
			HttpServletRequest request, HttpServletResponse response) {
		String supplier = request.getParameter("supplier");
		String firstDate = request.getParameter("firstDate");
		String secondDate = request.getParameter("secondDate");

		System.out.println(supplier + "= = = Supplier in Helper");

		Map<Long, PurchaseDetailsFromGoodsReceive> map = new HashMap<Long, PurchaseDetailsFromGoodsReceive>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<PurchaseDetailsFromGoodsReceive> expList = dao
				.getPurchaseReturnDetailsForSupplier(supplier, firstDate,
						secondDate);

		return expList;
	}

	// save daily stock on Login

	public void saveDailyStock() {

		String product_category = null;
		String supplier_name = null;
		Date expdate = null;
		String companyname = null;
		String productName = null;
		String company = null;
		String batchNumber = null;
		Double weight = null;
		Double quantity = null;
		String dateYesterday = null;
		String yesterday = null;
		GoodsReceiveDao dao = new GoodsReceiveDao();
		List stockList = dao.getStockDetailToSaveDailyStock();

		// to check save_daily_stock table
		StockDao sdao = new StockDao();
		List stkList = sdao.getAllDailyStockEntry();
		// System.out.println("stock list in daily stock save  = = "+stkList.size());

		// if save_daily_stock table is not empty
		if (stkList.size() >= stockList.size()) {

			StockDao stdao = new StockDao();
			List listStock = stdao.getInsertDateAndQuantityFromStockEntry();
			System.out.println("stokTable size = = =" + listStock.size());
			for (int k = 0; k < listStock.size(); k++) {
				Object[] o1 = (Object[]) listStock.get(k);
				dateYesterday = o1[2].toString();

				final Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				cal.add(Calendar.DATE, -1);

				yesterday = dateFormat.format(cal.getTime());

				System.out
						.println("dateYesterday in save Daily stock from databse "
								+ dateYesterday);
				System.out
						.println("Yesterday in save Daily stock today minus one "
								+ yesterday);

				int stockListSize = stockList.size();
				int totalSize = stockListSize + stkList.size();

				if (dateYesterday.equals(yesterday)) {

					System.out.println("hello java");
					break;
				} else {

					GoodsReceiveDao dao1 = new GoodsReceiveDao();
					List stockList1 = dao1.getStockDetailToSaveDailyStock();

					for (int i = 0; i < stockList1.size(); i++) {
						Object[] o = (Object[]) stockList1.get(i);

						StockDetail bean = new StockDetail();
						bean.setSupplier_name(o[0].toString());
						bean.setProductCategory(o[1].toString());
						bean.setCompanyName(o[2].toString());
						bean.setProductName(o[3].toString());
						bean.setQuantity(Double.parseDouble(o[4].toString()));
						bean.setBatchNo(o[5].toString());
						bean.setExpdate((Date) o[6]);
						// bean.setCompanyName(o[1].toString());
						// bean.setWeight(Double.parseDouble(o[2].toString()));
						// bean.setQuantity(Double.parseDouble(o[4].toString()));
						supplier_name = bean.getSupplier_name();
						product_category = bean.getProductCategory();
						expdate = bean.getExpdate();
						companyname = bean.getCompanyName();
						productName = bean.getProductName();
						batchNumber = bean.getBatchNo();
						quantity = bean.getQuantity();

						// company = bean.getCompanyName();
						// weight = bean.getWeight();

						SaveDailyStock saveBean = new SaveDailyStock();
						saveBean.setSupplier_name(supplier_name);
						saveBean.setProductCategory(product_category);
						saveBean.setCompanyName(companyname);
						saveBean.setProductName(productName);
						saveBean.setBatchNo(batchNumber);
						saveBean.setQuantity(quantity);
						saveBean.setExpdate(expdate);
						// saveBean.setCompanyName(company);
						// saveBean.setWeight(weight);

						String yesterday2 = null;
						final Calendar cal2 = Calendar.getInstance();
						SimpleDateFormat dateFormat1 = new SimpleDateFormat(
								"yyyy-MM-dd");
						cal2.add(Calendar.DATE, -1);
						yesterday2 = dateFormat1.format(cal2.getTime());
						System.out
								.println("Guru Yesterday Date if yesDate and yesterday mismatch "
										+ yesterday2);
						saveBean.setInsertDate(yesterday2);

						GoodsReceiveDao gDao = new GoodsReceiveDao();
						gDao.addDailyStockDetail(saveBean);
					}
				}
			}
		}

		// if save_daily_stock is empty

		else {

			GoodsReceiveDao dao1 = new GoodsReceiveDao();
			List stockList1 = dao1.getStockDetailToSaveDailyStock();

			for (int i = 0; i < stockList1.size(); i++) {
				Object[] o = (Object[]) stockList1.get(i);

				StockDetail bean = new StockDetail();
				bean.setSupplier_name(o[0].toString());
				bean.setProductCategory(o[1].toString());
				bean.setCompanyName(o[2].toString());
				bean.setProductName(o[3].toString());
				bean.setQuantity(Double.parseDouble(o[4].toString()));
				bean.setBatchNo(o[5].toString());
				bean.setExpdate((Date) o[6]);

				// bean.setCompanyName(o[1].toString());
				// bean.setWeight(Double.parseDouble(o[2].toString()));

				supplier_name = bean.getSupplier_name();
				product_category = bean.getProductCategory();
				companyname = bean.getCompanyName();
				productName = bean.getProductName();
				batchNumber = bean.getBatchNo();
				quantity = bean.getQuantity();
				expdate = bean.getExpdate();
				// company = bean.getCompanyName();
				// weight = bean.getWeight();
				SaveDailyStock saveBean = new SaveDailyStock();
				saveBean.setSupplier_name(supplier_name);
				saveBean.setProductCategory(product_category);
				saveBean.setCompanyName(companyname);
				saveBean.setProductName(productName);
				saveBean.setBatchNo(batchNumber);
				saveBean.setQuantity(quantity);
				saveBean.setExpdate(expdate);
				// saveBean.setCompanyName(company);
				// saveBean.setWeight(weight);

				String yesterday1 = null;
				final Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				cal.add(Calendar.DATE, -1);
				yesterday1 = dateFormat.format(cal.getTime());
				System.out
						.println("Yesterday Date if save_daily_stock is empty "
								+ yesterday1);
				saveBean.setInsertDate(yesterday1);

				GoodsReceiveDao gDao = new GoodsReceiveDao();
				gDao.addDailyStockDetail(saveBean);
			}
		}
	}

	public List getStockDetailsAsDate(HttpServletRequest request,
			HttpServletResponse response) {

		String firstDate = request.getParameter("firstDate");

		Map<Long, ProductStockDetailsBean> map = new HashMap<Long, ProductStockDetailsBean>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<com.Fertilizer.bean.ProductStockDetailsBean> stockList = dao
				.getStockDetailsAsPerDate(firstDate);

		return stockList;

	}

	public List getSaleReturnAsPerCategory(HttpServletRequest request,
			HttpServletResponse response) {

		String cat = request.getParameter("cat");
		System.out.println(cat + "Category in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.getSaleReturnDetailsAsPerCategory(cat);

		return expList;

	}

	public List gstSummaryReportsBetweenTwoDates(HttpServletRequest request,
			HttpServletResponse response) {

		String fDate = request.getParameter("gstFisDate1");
		String sDate = request.getParameter("gstEndDate1");

		System.out.println(fDate + "fDate in Helper");
		System.out.println(sDate + "sDate in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.gstSummaryReportsBetweenTwoDates(fDate,
				sDate);

		return expList;
	}

	public List gstPurchaseSummaryReportsBetweenTwoDates(
			HttpServletRequest request, HttpServletResponse response) {

		String fDate = request.getParameter("gstFisDate1");
		String sDate = request.getParameter("gstEndDate1");

		System.out.println(fDate + "fDate in Helper");
		System.out.println(sDate + "sDate in Helper");

		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao
				.gstPurchaseSummaryReportsBetweenTwoDates(fDate, sDate);

		return expList;
	}

	public List getExpensesDetailsPerGSTPercentage(HttpServletRequest request,
			HttpServletResponse response) {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Map<Long, SaleReports> map = new HashMap<Long, SaleReports>();

		GoodsReceiveDao dao = new GoodsReceiveDao();
		List<SaleReports> expList = dao.getExpenseDetailsAsGST(endDate,
				startDate);
		return expList;
	}
}