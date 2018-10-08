package com.Fertilizer.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
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

import com.Fertilizer.bean.BillBean;
import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.bean.SaleReportBean;
import com.Fertilizer.dao.GoodsReceiveDao;
import com.Fertilizer.dao.ProductDetailsDao;
import com.Fertilizer.dao.ProductStockDetailsDao;
import com.Fertilizer.dao.SaleReportDao;
import com.Fertilizer.dao.UpdateGoodReceiveDao;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.hibernate.UpdateGoodReceiveBean;
import com.Fertilizer.utility.HibernateUtility;

public class UpdateGoodReceiveHelper {

	public Map getProductDetailsForGoodsReceiveUpdate(String challanNo) {

		UpdateGoodReceiveDao dao = new UpdateGoodReceiveDao();
		List list = dao.getProductDetailsFoGridInUpdateGoodsReceive(challanNo);
		System.out.println(list.size());
		Map map1 = new HashMap();

		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);

			UpdateGoodReceiveBean bean = new UpdateGoodReceiveBean();
			bean.setCatId(Long.parseLong(o[0].toString()));
			bean.setProductName(o[1].toString());
			bean.setCompanyName(o[2].toString());
			bean.setHsn(o[3].toString());
			bean.setBuyPrice(Double.parseDouble(o[4].toString()));
			bean.setMrp(Double.parseDouble(o[5].toString()));
			bean.setSalePrice(Double.parseDouble(o[6].toString()));
			bean.setPacking(Double.parseDouble(o[7].toString()));
			bean.setcGst(0.0);
			bean.setsGst(0.0);
			bean.setiGst(0.0);
			bean.setQuantity(Double.parseDouble(o[8].toString()));
			bean.setTotal(Double.parseDouble(o[9].toString()));
			bean.setBatchNo(o[10].toString());
			bean.setExpDate((Date) o[11]);

			map1.put(bean.getProductName(), bean);
		}
		return map1;
	}

	public Map getAllChallanNoAgainstSupplier(String fk_supplier_id) {
		int count = 1;
		UpdateGoodReceiveDao dao = new UpdateGoodReceiveDao();
		List list = dao.getChallanNo(fk_supplier_id);

		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			GoodsReceiveBean bean = new GoodsReceiveBean();
			bean.setBillNum(o[0].toString());
			bean.setGrossTotal(Double.parseDouble(o[1].toString()));

			System.out.println("***************" + o[0]);
			map.put(count, bean);
			count++;
		}
		return map;

	}

	// code for Update Good Receive

	public void updateGoodsReceived(HttpServletRequest request, HttpServletResponse response) throws ParseException {

		String supplier = "";
		String billNum = "";
		String grossTotal = "";
		int myCOunt = 1;
		int v;

		UpdateGoodReceiveDao dao = new UpdateGoodReceiveDao();

		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("Count in helper  ==" + count);

		
		
	

			// Get Details from Out Side Grid
			supplier = request.getParameter("fk_supplier_id");
			String challanNo = request.getParameter("challanNo");

			/*----------- Update Good receive Table for Challan to Bill --------------------*/

			HibernateUtility hbu3 = HibernateUtility.getInstance();
			Session session3 = hbu3.getHibernateSession();

			System.out.println("=====supplier" + supplier);
			System.out.println("=====challanNo" + challanNo);
			

			// Query to get PkGoodreceiveID from good_receive table
			Query query = session3
					.createSQLQuery("select pk_goods_receive_id,bill_number FROM goods_receive where fk_supplier_id='"
							+ supplier + "' AND billType='challan' AND bill_number='" + challanNo + "'");

			List<Object[]> list = query.list();

			System.out.println("====================list size" + list.size());

			System.out.println("========List :" + list);
			System.out.println("Calc total");

			String pkGoodReceiveId = "";
			String billno = "";

			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();

				pkGoodReceiveId = objects[0].toString();
				billno = objects[1].toString();

				System.out.println("------------ pkGoodReceiveId" + pkGoodReceiveId);
				System.out.println(billno + "  billno");
			

			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();


			
			for (int i = 0; i < count; i++) {

						billNum = request.getParameter("billNum");
						String billtype = "Bill";
						String UpurchaseDate = request.getParameter("purchaseDate");
						String UbuyPrice = request.getParameter("buyPrice" + i);
						String UcGst = request.getParameter("cGst" + i);
						String UsGst = request.getParameter("sGst" + i);
						String UiGst = request.getParameter("iGst" + i);
						String Utotal = request.getParameter("total" + i);

						Double cgst = Double.parseDouble(UcGst);
						Double sgst = Double.parseDouble(UsGst);
						Double igst = Double.parseDouble(UiGst);
						Double taxpercent = cgst + sgst;

						System.out.println("inside if");

						
						GoodsReceiveBean updateGoodReceive = (GoodsReceiveBean) session.get(GoodsReceiveBean.class,new Long(pkGoodReceiveId));
						
						updateGoodReceive.setBillNum(billNum);
						updateGoodReceive.setBillType(billtype);
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date purchDate = null;
						purchDate = dateFormat.parse(UpurchaseDate);
						updateGoodReceive.setPurchaseDate(purchDate);

						updateGoodReceive.setBuyPrice(Double.parseDouble(UbuyPrice));
						updateGoodReceive.setTaxPercentage(taxpercent);
						updateGoodReceive.setTotalAmount(Double.parseDouble(Utotal));

						session.update(updateGoodReceive);
						

				}
				
			transaction.commit();
			//}

			/*-----------END OF  Update Good receive Table for Challan to Bill --------------------*/

		}

	}

}
