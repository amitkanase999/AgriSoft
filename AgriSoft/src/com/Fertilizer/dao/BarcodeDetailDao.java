package com.Fertilizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.util.Log;

import com.Fertilizer.bean.GetBarcode;
import com.Fertilizer.hibernate.BarcodeDetailsBean;
import com.Fertilizer.hibernate.ProductDetailsBean;
import com.Fertilizer.utility.HibernateUtility;
import com.itextpdf.text.log.SysoLogger;

public class BarcodeDetailDao {

	public void generateBarcodeForProduct(BarcodeDetailsBean bean1) {
		System.out.println("In barcode dao");
		HibernateUtility hbu=null;
		Session session=null;
		org.hibernate.Transaction transaction=null;
		try{
			hbu=HibernateUtility.getInstance();
			session=hbu.getHibernateSession();
			transaction=session.beginTransaction();
			//for mapping
			Long prodctId = bean1.getFkProductId();

			System.out.println("in barcode dao fk ="+prodctId);

			ProductDetailsBean productsId = (ProductDetailsBean) session.get(ProductDetailsBean.class, prodctId);

			bean1.setProductDetailsBean(productsId);

			System.out.println("in barcode dao fk ="+productsId);

			session.save(bean1);
			transaction.commit();
			System.out.println("Barcode added");
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


	public List getBarcodeNoPrint(){
		System.out.println("In Dao method to check barcode");
		HibernateUtility hbu=null;
		Session session=null;
		List<GetBarcode> barcodeList=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT barcode_number , fk_product_id FROM barcode_details ORDER BY barcode_number DESC LIMIT 1");

			List<Object[]> list = query.list();
			barcodeList= new ArrayList<GetBarcode>(0);

			for (Object[] object : list) {		
				System.out.println("Output"+Arrays.toString(object));
				GetBarcode reports = new GetBarcode();

				reports.setBarcodeNo(Long.parseLong(object[0].toString()));
				barcodeList.add(reports);

			}}catch(RuntimeException e){	
				Log.error("Error in getBarcodeNoPrint",e);
			}
		finally{

			hbu.closeSession(session);	
		}
		return barcodeList;
	}

}
