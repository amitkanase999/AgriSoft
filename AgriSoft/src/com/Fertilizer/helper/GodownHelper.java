package com.Fertilizer.helper;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.dao.GodownDao;
import com.Fertilizer.hibernate.ProductStockDetailsBean;

public class GodownHelper {
	
	// Fetch fertilizer product Against  Godown
	public Map getFertilizerAllProductAgainstGodownForDropDown(String godownName) {
		int count = 1;
		
		GodownDao dao=new GodownDao();
		List list= dao.getAllFertilizerProductAgainstGodown(godownName);
		
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			ProductStockDetailsBean bean=new ProductStockDetailsBean();
			bean.setProduct_name(o[0].toString());
			bean.setCompanyname(o[1].toString());
			bean.setPacking(Double.parseDouble(o[2].toString()));
			bean.setQuantity(Long.parseLong(o[3].toString()));
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	/*// Fetch fertilizer product Against Default Godown
	public Map getFertilizerAllProductAgainstDefaultGodownForDropDown(String godownName) {
		int count = 1;
		
		GodownDao dao=new GodownDao();
		List list= dao.getAllFertilizerProductAgainstDefaultGodown(godownName);
		
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			ProductStockDetailsBean bean=new ProductStockDetailsBean();
			bean.setProduct_name(o[0].toString());
			bean.setCompanyname(o[1].toString());
			bean.setPacking(Double.parseDouble(o[2].toString()));
			bean.setQuantity(Long.parseLong(o[3].toString()));
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}*/
	
	// Fetch Seed product Against  Godown
		public Map getSeedAllProductAgainstGodownForDropDown(String godownName) {
			int count = 1;
			
			GodownDao dao=new GodownDao();
			List list= dao.getAllSeedProductAgainstGodown(godownName);
			
			Map  map =  new HashMap();
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				ProductStockDetailsBean bean=new ProductStockDetailsBean();
				bean.setProduct_name(o[0].toString());
				bean.setBatchno(o[1].toString());
				bean.setQuantity(Long.parseLong(o[2].toString()));
				bean.setExpiryDate((Date)o[3]);
				bean.setPacking(Double.parseDouble(o[4].toString()));
				bean.setUnitName(o[5].toString());
				
				
				System.out.println("***************"+o[0]);
				map.put(count,bean);
				count++;
			}
			return map;
		}
		
		// Fetch Pesticide product Against  Godown
		public Map getPesticideAllProductAgainstGodownForDropDown(String godownName) {
			int count = 1;
			
			GodownDao dao=new GodownDao();
			List list= dao.getAllPesticideProductAgainstGodown(godownName);
			
			Map  map =  new HashMap();
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				ProductStockDetailsBean bean=new ProductStockDetailsBean();
				bean.setProduct_name(o[0].toString());
				bean.setBatchno(o[1].toString());
				bean.setQuantity(Long.parseLong(o[2].toString()));
				bean.setExpiryDate((Date)o[3]);
				bean.setPacking(Double.parseDouble(o[4].toString()));
				bean.setUnitName(o[5].toString());
				
				System.out.println("***************"+o[0]);
				map.put(count,bean);
				count++;
			}
			return map;
		}

}
