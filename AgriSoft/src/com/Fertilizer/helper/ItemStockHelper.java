package com.Fertilizer.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.util.Log;

import com.Fertilizer.bean.CurrentStockDetails;
import com.Fertilizer.dao.ItemStockDao;

public class ItemStockHelper {

	public List getCurrentStockBYColor()
	{
		ItemStockDao dao1 = new ItemStockDao();

		List list = dao1.getCurrentStockDetails();
		List<CurrentStockDetails> itBean = new ArrayList<CurrentStockDetails>(0);
		for(int i=0;i<list.size();i++)
		{Object[]object = (Object[])list.get(i);
		CurrentStockDetails currentStockDetails = new CurrentStockDetails();
		currentStockDetails.setTotalQuantityForCurrentStock((BigDecimal)object[0]);
		currentStockDetails.setItemName((String)object[1]);
		itBean.add(currentStockDetails);
		}
		return itBean;
	}
}
