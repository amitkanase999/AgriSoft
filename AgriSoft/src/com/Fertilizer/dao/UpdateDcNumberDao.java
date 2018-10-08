package com.Fertilizer.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Fertilizer.hibernate.PurchaseOrderBean;
import com.Fertilizer.hibernate.UpdateDcNumberBean;
import com.Fertilizer.utility.HibernateUtility;

public class UpdateDcNumberDao {

	public void updateDcNumber(UpdateDcNumberBean bean) {

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction(); 
			Long poNum = bean.getPoNum();
			Long dcNum = bean.getDcNum();
			Long productID = bean.getPkPOId();
			Query query2 = session.createQuery("from PurchaseOrderBean where productID=:productID AND poNumber="+poNum);
			query2.setParameter("productID", productID);
			PurchaseOrderBean uniqueResult = (PurchaseOrderBean) query2.uniqueResult();
			Long PkForUpdate = uniqueResult.getPkPoId();
			PurchaseOrderBean gReceipt = (PurchaseOrderBean) session.load(PurchaseOrderBean.class, PkForUpdate);
			gReceipt.setDcNum(dcNum);
			session.saveOrUpdate(gReceipt);
			transaction.commit();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
	}
}
