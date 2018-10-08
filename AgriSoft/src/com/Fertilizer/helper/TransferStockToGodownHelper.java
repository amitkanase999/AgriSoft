package com.Fertilizer.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.Fertilizer.bean.CustomerBean;
import com.Fertilizer.bean.CustomerBillBean;
import com.Fertilizer.dao.FertilizerBillDao;
import com.Fertilizer.dao.GoodsReceiveDao;
import com.Fertilizer.dao.ProductStockDetailsDao;
import com.Fertilizer.dao.StockDao;
import com.Fertilizer.dao.TransferStockToGodownDao;
import com.Fertilizer.hibernate.FertilizerBillBean;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.hibernate.Stock;
import com.Fertilizer.utility.HibernateUtility;

public class TransferStockToGodownHelper {
	
	// Fetch Product Name For Fertilizer from ProductStock Details for transfer Stock
	public Map getProductName(String godown,String category) {
		int count = 1;
		
		TransferStockToGodownDao dao=new TransferStockToGodownDao();
		List list = dao.getAllProductName(godown,category);

		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			
			ProductStockDetailsBean bean=new ProductStockDetailsBean();
			bean.setCompanyname(o[0].toString());
			bean.setProduct_name(o[1].toString());
			bean.setPacking(Double.parseDouble(o[2].toString()));
			bean.setUnitName(o[3].toString());
			bean.setQuantity(Long.parseLong(o[4].toString()));

			System.out.println("***************" + o[0]);
			map.put(count, bean);
			count++;
		}
		return map;

	}

	// Fetch Product Name For Seed Pesticide from ProductStock Details for transfer Stock
		public Map getProductNameForSeedPesticide(String godown,String category) {
			int count = 1;
			
			TransferStockToGodownDao dao=new TransferStockToGodownDao();
			List list = dao.getAllProductNameForSeedPesticide(godown,category);

			Map map = new HashMap();
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				
				ProductStockDetailsBean bean=new ProductStockDetailsBean();
				bean.setCompanyname(o[0].toString());
				bean.setProduct_name(o[1].toString());
				bean.setBatchno(o[2].toString());
				bean.setExpiryDate((Date)o[3]);
				bean.setPacking(Double.parseDouble(o[4].toString()));
				bean.setUnitName(o[5].toString());
				bean.setQuantity(Long.parseLong(o[6].toString()));

				System.out.println("***************" + o[0]);
				map.put(count, bean);
				count++;
			}
			return map;

		}
		
		//fetch fertilizer productDetails in Grid
		public Map getFertilizerProductInGrid(
				HttpServletRequest request, HttpServletResponse response) throws ParseException {

			String godown= request.getParameter("godown");
			String category = request.getParameter("category");
			String proName = request.getParameter("proName");
			String company = request.getParameter("company");
			String weight = request.getParameter("weight");
			String batchNum = request.getParameter("batchNum");
			String availableQty = request.getParameter("availableQty");

			System.out.println(proName + "pro name in gr helper");
			int count = 1;

			TransferStockToGodownDao dao=new TransferStockToGodownDao();
			List list = dao.getAllFertilizerProductDetailsInGrid(godown,category,proName,
							company, weight, batchNum,availableQty);

			Map map = new HashMap();
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				
				ProductStockDetailsBean bean=new ProductStockDetailsBean();
				bean.setSupplier_name(o[0].toString());
				bean.setCompanyname(o[1].toString());
				bean.setProduct_category(o[2].toString());
				bean.setProduct_name(o[3].toString());
				bean.setPacking(Double.parseDouble(o[4].toString()));
				bean.setUnitName(o[5].toString());
				bean.setQuantity(Long.parseLong(o[6].toString()));
				bean.setBatchno(o[7].toString());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date expdate =(Date)o[8];
				String expdate1 = dateFormat.format(expdate);
				bean.setExpDate(expdate1);
				
				
				System.out.println("***************" + o[0]);
				map.put(count, bean);
				count++;
			}
			return map;

		}
		
		
//fetch Seed Pesticide productDetails in Grid
public Map getSeedPesticideProductInGrid(
						HttpServletRequest request, HttpServletResponse response) {

					String godown= request.getParameter("godown");
					String category = request.getParameter("category");
					String proName = request.getParameter("proName");
					String company = request.getParameter("company");
					String weight = request.getParameter("weight");
					String batchNum = request.getParameter("batchNum");	
					String unit = request.getParameter("unit");
					String availableQty = request.getParameter("availableQty");

					System.out.println(proName + "pro name in gr helper");
					int count = 1;

					TransferStockToGodownDao dao=new TransferStockToGodownDao();
					List list = dao.getAllSeedPesticideProductDetailsInGrid(godown,category,proName,
									company, weight, batchNum,unit,availableQty);

					Map map = new HashMap();
					for (int i = 0; i < list.size(); i++) {
						Object[] o = (Object[]) list.get(i);
						
						ProductStockDetailsBean bean=new ProductStockDetailsBean();
						bean.setSupplier_name(o[0].toString());
						bean.setCompanyname(o[1].toString());
						bean.setProduct_category(o[2].toString());
						bean.setProduct_name(o[3].toString());
						bean.setPacking(Double.parseDouble(o[4].toString()));
						bean.setUnitName(o[5].toString());
						bean.setQuantity(Long.parseLong(o[6].toString()));
						bean.setBatchno(o[7].toString());
						//bean.setExpiryDate((Date)o[8]);
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Date expdate =(Date)o[8];
						String expdate1 = dateFormat.format(expdate);
						bean.setExpDate(expdate1);
						
						
						System.out.println("***************" + o[0]);
						map.put(count, bean);
						count++;
					}
					return map;

				}


	//------------ Transfer Stock to Godown --------------------------------
				
@SuppressWarnings("deprecation")
public void transferGodownStock(HttpServletRequest request,HttpServletResponse response) throws ParseException 
{

					TransferStockToGodownDao dao=new TransferStockToGodownDao();
					ProductStockDetailsBean bean=new ProductStockDetailsBean();
					
					Integer count = Integer.parseInt(request.getParameter("count"));
					System.out.println(count);
					
					
					
					for(int i =0 ; i<count;i++)
					{

					
						
						String fromgodown = request.getParameter("fromgodown");
						String togodown = request.getParameter("togodown");
						String category = request.getParameter("category");
						
						bean.setGodownName(togodown);
						bean.setProduct_category(category);
						

						// Grid Content

						String supplier_name = request.getParameter("supplier_name"+i);
						String companyname = request.getParameter("companyname"+i);
						String product_category = request.getParameter("product_category"+i);
						String product_name = request.getParameter("product_name"+i);
						Double packing = Double.parseDouble(request.getParameter("packing"+i));							
						String unitName = request.getParameter("unitName"+i);
						String quantity = request.getParameter("quantity"+i);
						String TRF_Qty = request.getParameter("TRF_Qty"+i);
						String leftover = request.getParameter("leftover"+i);
						String batchno = request.getParameter("batchno"+i);
						String expiryDate = request.getParameter("expDate"+i);
						

						
					System.out.println("Date-----------------"+expiryDate);
						/*SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
						Date dateobj = new Date();
						//System.out.println(dateFormat1.format(dateobj));
						Date insertdate=dateFormat1.parse(dateobj);*/
						
						
						/*bean.setUpdateDate(insertdate);
						dao.transferStockToGodown(bean);*/
						
			StockDao dao1 = new StockDao();
			List stkList = dao1.getAllStockEntry();
						
			if(category.equalsIgnoreCase("fertilizer"))
			{
					
				//Update transfered quantity in alredy exist in fertilizer
				for (int j = 0; j < stkList.size(); j++) {

					

					ProductStockDetailsBean st = (ProductStockDetailsBean) stkList.get(j);
					
					String stgodown=st.getGodownName();
					String stitemName = st.getProduct_name();
					String stCompanyname = st.getCompanyname();
					Double stpacking = st.getPacking();
					//String  =pkg.toString();
					Long pkstockid = st.getPk_stock_id();

					/* If ItemName Is Already Exists In Stock Table */

					System.out.println("pkstockid in stock = = "
							+ pkstockid);
					// System.out.println("batchNumber from stock table"+batchNumber);
					/*System.out.println("batchNo from goods receive"
							+ batchNo);*/
					System.out.println("In else Part");
					
					if (togodown.equals(stgodown) && product_name.equals(stitemName) && companyname.equals(companyname) && packing.equals(stpacking))
					{
						Long qunty = st.getQuantity();

						Long updatequnty = (qunty + Long
								.parseLong(TRF_Qty));

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

						if (j + 1 == stkList.size()) {

		

							Date updateDate = new Date();
							ProductStockDetailsBean psbean = new ProductStockDetailsBean();

							psbean.setSupplier_name(supplier_name);
							psbean.setGodownName(togodown);
							psbean.setCompanyname(companyname);
							psbean.setProduct_category(category);
							psbean.setProduct_name(product_name);
							psbean.setPacking(packing);
							psbean.setQuantity(Long.parseLong(TRF_Qty));
							psbean.setUnitName(unitName);
							psbean.setBatchno(batchno);
							psbean.setUpdateDate(updateDate);

						/*	SimpleDateFormat dateFormat1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date expiryDate1 = null;
							try {
								expiryDate1 = dateFormat1.parse(expiryDate);
								psbean.setExpiryDate(expiryDate1);
							} catch (ParseException e) {
								e.printStackTrace();
							}*/
							
							// it convert Date String dd/MM/yyyy to Sql yyyy-MM-dd
							
							String expDate=expiryDate;
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date date = sdf1.parse(expDate);
							System.out.println("Java Date : ======="+date);
							java.sql.Date sqlExpDate = new java.sql.Date(date.getTime());  
							System.out.println("SQL Date : ======="+sqlExpDate);
							psbean.setExpiryDate(sqlExpDate);

							ProductStockDetailsDao psdao = new ProductStockDetailsDao();
							psdao.addProductInProductStockDetails(psbean);

						}
					}
					
			}
						
		}
		else
		{
			System.out.println("code for seed pesticide");

			
			//Update transfered quantity in alredy exist in seed or pesticide
			for (int j = 0; j < stkList.size(); j++) {

				

				ProductStockDetailsBean st = (ProductStockDetailsBean) stkList.get(j);
				
				String stgodown=st.getGodownName();
				String stcategory=st.getProduct_category();
				String stitemName = st.getProduct_name();
				String stCompanyname = st.getCompanyname();
				String stbatchno=st.getBatchno();
				Double stpacking = st.getPacking();
				String stunit=st.getUnitName();
				Long pkstockid = st.getPk_stock_id();

				
				
				/* If ItemName Is Already Exists In Stock Table */

				System.out.println("pkstockid in stock = = "
						+ pkstockid);
				
				System.out.println("In else Part");
				
				if (togodown.equals(stgodown) && product_category.equalsIgnoreCase(stcategory) && companyname.equals(stCompanyname)  && product_name.equals(stitemName) && batchno.equals(stbatchno) && packing.equals(stpacking) && unitName.equals(stunit))
				{
					Long qunty = st.getQuantity();

					Long updatequnty = (qunty + Long
							.parseLong(TRF_Qty));

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

					if (j + 1 == stkList.size()) {

	

						Date updateDate = new Date();
						ProductStockDetailsBean psbean = new ProductStockDetailsBean();
						
						
						psbean.setSupplier_name(supplier_name);
						psbean.setGodownName(togodown);
						psbean.setCompanyname(companyname);
						psbean.setProduct_category(category);
						psbean.setProduct_name(product_name);
						psbean.setPacking(packing);
						psbean.setQuantity(Long.parseLong(TRF_Qty));
						psbean.setUnitName(unitName);
						psbean.setBatchno(batchno);
						
						psbean.setUpdateDate(updateDate);

						// it convert Date String dd/MM/yyyy to Sql yyyy-MM-dd
						String expDate=expiryDate;
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date date = sdf1.parse(expDate);
						System.out.println("Java Date : ======="+date);
						java.sql.Date sqlExpDate = new java.sql.Date(date.getTime());  
						System.out.println("SQL Date : ======="+sqlExpDate);
						psbean.setExpiryDate(sqlExpDate);
						
						
				

						ProductStockDetailsDao psdao = new ProductStockDetailsDao();
						psdao.addProductInProductStockDetails(psbean);

					}
				}
				
		}
					
	
			
			
		}

						
			if(category.equalsIgnoreCase("fertilizer"))
			{
				/*----------- Update From-Godown Stock From ProductStockDetails Table  --------------------*/

						HibernateUtility hbu3 = HibernateUtility.getInstance();
						Session session3 = hbu3.getHibernateSession();


						// Query to get PkGoodreceiveID from good_receive table
						Query query = session3.createSQLQuery("SELECT pk_stock_id,product_name from product_stock_details WHERE godown_name='"+fromgodown+"' AND product_category='"+category+"' AND company_name='"+companyname+"' AND product_name='"+product_name+"' AND quantity='"+quantity+"'"); 

						List<Object[]> list = query.list();

						System.out.println("====================list size" + list.size());

						System.out.println("========List :" + list);
					

						String pkstockid = "";
						String productname = "";

						for (Iterator iterator = list.iterator(); iterator.hasNext();) {
							Object[] objects = (Object[]) iterator.next();

							pkstockid = objects[0].toString();
							productname = objects[1].toString();

							System.out.println("------------ pkstockid" + pkstockid);
							System.out.println("------------ productname" + productname);
						

						Configuration configuration = new Configuration();
						configuration.configure("hibernate.cfg.xml");
						SessionFactory factory = configuration.buildSessionFactory();
						Session session = factory.openSession();

						Transaction transaction = session.beginTransaction();

				
						
						for (int s = 0; s < count; s++) {

								String  remQty=request.getParameter("leftover"+ s);
								

									System.out.println("inside if");

									
									ProductStockDetailsBean updateGodownStock = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class,new Long(pkstockid));
									
									updateGodownStock.setQuantity(Long.parseLong(remQty));
									
									
									/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									Date purchDate = null;
									purchDate = dateFormat.parse(UpurchaseDate);
									updateGoodReceive.setPurchaseDate(purchDate);*/

									
								
									session.update(updateGodownStock);

							}
						transaction.commit();
								
					}	
						
				/*-----------Update From-Godown Stock From ProductStockDetails Table --------------------*/
						
			}
			else
			{
				System.out.println("Write code for seed pesticide");
				/*----------- Update From-Godown Stock From ProductStockDetails Table for seed pesticide  --------------------*/

				HibernateUtility hbu3 = HibernateUtility.getInstance();
				Session session3 = hbu3.getHibernateSession();


				// Query to get PkGoodreceiveID from good_receive table
				Query query = session3.createSQLQuery("SELECT pk_stock_id,product_name from product_stock_details WHERE godown_name='"+fromgodown+"' AND product_category='"+category+"' AND company_name='"+companyname+"' AND product_name='"+product_name+"' AND quantity='"+quantity+"' AND packing='"+packing+"' AND unit='"+unitName+"' AND batchno='"+batchno+"' "); 
				

				List<Object[]> list = query.list();

				System.out.println("====================list size" + list.size());

				System.out.println("========List :" + list);
			

				String pkstockid = "";
				String productname = "";

				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Object[] objects = (Object[]) iterator.next();

					pkstockid = objects[0].toString();
					productname = objects[1].toString();

					System.out.println("------------ pkstockid" + pkstockid);
					System.out.println("------------ productname" + productname);
				

				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
				SessionFactory factory = configuration.buildSessionFactory();
				Session session = factory.openSession();

				Transaction transaction = session.beginTransaction();

		
				
				for (int s = 0; s < count; s++) {

						String  remQty=request.getParameter("leftover"+ s);
						

							System.out.println("inside if");

							
							ProductStockDetailsBean updateGodownStock = (ProductStockDetailsBean) session.get(ProductStockDetailsBean.class,new Long(pkstockid));
							
							updateGodownStock.setQuantity(Long.parseLong(remQty));
							
							
							/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date purchDate = null;
							purchDate = dateFormat.parse(UpurchaseDate);
							updateGoodReceive.setPurchaseDate(purchDate);*/

							
						
							session.update(updateGodownStock);

					}
				transaction.commit();
						
			}	
				
		/*-----------Update From-Godown Stock From ProductStockDetails Table for seed pesticide --------------------*/
				
				
			}
						
						

						

					
				
					}
				}	
				
	}
