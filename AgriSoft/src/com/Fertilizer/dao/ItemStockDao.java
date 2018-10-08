package com.Fertilizer.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.Fertilizer.hibernate.ItemStock;
import com.Fertilizer.utility.HibernateUtility;

public class ItemStockDao {

	public List getCurrentStockDetails()
	{
		HibernateUtility hbu=null;
		Session session=null;
		List list  = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();

			String sql = "SELECT i.stock ,p.product_name FROM item_stock i LEFT JOIN product_details p ON i.fk_product_id = p.pk_product_id";
			Query query = session.createSQLQuery(sql);
			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
