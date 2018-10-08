package com.Fertilizer.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.dao.BillCopyDao;
import com.Fertilizer.hibernate.BillCopyBean;

public class BillCopyHelper {

	public Map getAllFertilzerBill(String ptype,String custtype ) {
		int count = 1;
		
		BillCopyDao dao=new BillCopyDao();
		List list= dao.getAllBillNoForFertilizerBilling(ptype,custtype );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			BillCopyBean bean=new BillCopyBean();
			bean.setBillno(Long.parseLong(o[0].toString()));
			bean.setCustname(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	public Map getAllSeedBill(String ptype,String custtype ) {
		int count = 1;
		
		BillCopyDao dao=new BillCopyDao();
		List list= dao.getAllBillNoForSeedBilling(ptype,custtype );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			BillCopyBean bean=new BillCopyBean();
			bean.setBillno(Long.parseLong(o[0].toString()));
			bean.setCustname(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	public Map getAllPesticideBill(String ptype,String custtype ) {
		int count = 1;
		
		BillCopyDao dao=new BillCopyDao();
		List list= dao.getAllBillNoForPesticideBilling(ptype,custtype );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			BillCopyBean bean=new BillCopyBean();
			bean.setBillno(Long.parseLong(o[0].toString()));
			bean.setCustname(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
}
