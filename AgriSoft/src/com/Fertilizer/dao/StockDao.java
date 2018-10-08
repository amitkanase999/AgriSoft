package com.Fertilizer.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.bean.PurchaseStockMinus;
import com.Fertilizer.bean.StockDetail;
import com.Fertilizer.hibernate.SaveDailyStock;
import com.Fertilizer.hibernate.Stock;
import com.Fertilizer.utility.HibernateUtility;

public class StockDao {

	/*To Fetch ItemName From Stock Table */
	
	public List getAllStockEntry()
	{
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("from ProductStockDetailsBean");
			list = query.list();
		}
		catch(Exception e){	
			e.printStackTrace();
		}
		finally
		{
			if(session!=null){
				hbu.closeSession(session);
			}
		}
		return list;
	}

	/*To register New ItemName In Stock Table*/
	
	public void registerStock(com.Fertilizer.hibernate.Stock newEntry) {

		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;

		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			newEntry.setUpdateDate(date);

			session.save(newEntry);
			transaction.commit();
		}

		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(Exception rbe)
			{
				rbe.printStackTrace();
			}	
		}finally{
			hbu.closeSession(session);
		}
	}


	public void minusMainStock(PurchaseStockMinus minus) {

		String product_name = minus.getProductName();
		String company_Name = minus.getCompany();
		Double weight = minus.getWeightof();
		Double dupQuantity = minus.getDupQuantity();
		StockDao dao1 = new StockDao();
		List stkList2  = dao1.getAllStockEntry();  
		
		for(int j=0;j<stkList2.size();j++){

			Stock st = (Stock)stkList2.get(j);
			String itemName = st.getProductName();
			Long catId = st.getCatID();
			String company = st.getCompanyName();
			Double wight = st.getWeight();

			if(itemName.equals(product_name) && company.equals(company_Name) && wight.equals(weight)){
				Double qunty = st.getQuantity();
				Long PkStockId = st.getPkStockId();
				Double updatequnty = (double) (qunty - dupQuantity);

				HibernateUtility hbu1=null;
				Session session1=null;
				Transaction transaction1=null;

				try{
					hbu1 = HibernateUtility.getInstance();
					session1 = hbu1.getHibernateSession();
					transaction1 = session1.beginTransaction();
					System.out.println("minus data = "+updatequnty);
					Stock Stock = (Stock) session1.get(Stock.class, new Long(PkStockId));

					Stock.setQuantity(updatequnty);

					session1.saveOrUpdate(Stock);
					transaction1.commit();
				}
				catch(RuntimeException e){
					try{
						transaction1.rollback();
					}catch(Exception rbe)
					{
						rbe.printStackTrace();
					}	
				}finally{
					hbu1.closeSession(session1);
				}
			}
		}
	}

	public List getAllPkStockId(String productName, String companyName,
			String weight) {

		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select PkStockId, Quantity FROM stock_detail WHERE ProductName =: productName AND CompanyName =:companyName AND Weight=:weight");
			query.setParameter("productName", productName);
			query.setParameter("companyName", companyName);
			query.setParameter("weight", weight);

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

	public List getAllDailyStockEntry() {

		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("from SaveDailyStock");
			list = query.list();
		}
		catch(Exception e){	
			e.printStackTrace();
		}
		finally
		{
			if(session!=null){
				hbu.closeSession(session);
			}
		}
		return list;
	}

	public List getInsertDateAndQuantityFromStockEntry() {
		System.out.println("In getInsertDateAndQuantityFromStockEntry dao");
		HibernateUtility hbu = null ;
		Session session = null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT dsquantity,pk_daily_stock_id,insert_date FROM save_daily_stock ORDER BY pk_daily_stock_id DESC LIMIT 1");
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

}
