package com.Fertilizer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Fertilizer.bean.SaleReportBean;
import com.Fertilizer.dao.BillCopyDao;
import com.Fertilizer.dao.SaleReportDao;
import com.Fertilizer.hibernate.BillCopyBean;

public class SaleReportHelper {

	public Map getAllBillnumCatWise(String category ) {
		int count = 1;
		
		SaleReportDao dao=new SaleReportDao();
		List list= dao.getAllBillNoCategoryWise(category );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			SaleReportBean bean=new SaleReportBean();
			bean.setBillNo(Long.parseLong(o[0].toString()));
			bean.setCustname(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
}
