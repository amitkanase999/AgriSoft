package com.Fertilizer.helper;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.bean.GetSupplierDetails;
import com.Fertilizer.dao.SupplierDetailsDao;
import com.Fertilizer.hibernate.CustomerDetailsBean;
import com.Fertilizer.hibernate.SupplierDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class SupplierDetailsHelper {

	public void supplierDetails(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In helper");
		String city = request.getParameter("city");
		String contactNo = request.getParameter("contactNo");
		String emailId = request.getParameter("emailId");
		String dealerName = request.getParameter("dealerName");
		String personName = request.getParameter("personName");
		String tinNo = request.getParameter("tinNo");
		String landline = request.getParameter("landline");
		String address = request.getParameter("address");
		SupplierDetailsBean sdb = new SupplierDetailsBean();

		if(!"".equals(contactNo)){
			sdb.setContactNo(Long.parseLong(contactNo));
		} else
		{
			sdb.setContactNo(Long.parseLong("00"));
		}

		if(!"".equals(landline)){
			sdb.setLandline(Long.parseLong(landline));
		} else
		{
			sdb.setLandline(Long.parseLong("00"));
		}

		if(!"".equals(emailId)){
			sdb.setEmailId(emailId);
		} else
		{
			sdb.setEmailId("N/A");
		}

		if(!"".equals(tinNo)){
			sdb.setGstNum(tinNo);
		} else
		{
			sdb.setGstNum("N/A");
		}

		sdb.setPersonName(personName);
		sdb.setDealerName(dealerName);
		sdb.setCity(city);
		sdb.setAddress(address);

		SupplierDetailsDao sdo = new SupplierDetailsDao();
		sdo.valSupplierDetails(sdb);
	}

	public Map getSupplierDetailsForEdit(String supplierID) {

		System.out.println("into helper class");
		SupplierDetailsDao dao1 = new SupplierDetailsDao();
		List catList = dao1.getAllSupplierSetailsForEdit(supplierID);

		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);

			GetSupplierDetails bean = new GetSupplierDetails();
			bean.setDealerName(o[0].toString());
			bean.setCity(o[1].toString());
			bean.setContactNo((BigInteger)o[2]);
			bean.setLandline((BigInteger)o[3]);
			bean.setPersonName(o[4].toString());
			bean.setTin(o[5].toString());
			bean.setAddress(o[6].toString());
			bean.setEmail(o[7].toString());
			map.put(bean.getDealerName(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	}

	public void editSupplierDetail(HttpServletRequest request,
			HttpServletResponse response) {

		String supplierId = request.getParameter("supplierId");

		String dealerName = request.getParameter("dealerName");
		String personName = request.getParameter("personName");
		String contactNo = request.getParameter("contactNo");
		String landline = request.getParameter("landline");
		String emailId = request.getParameter("emailId");
		String city = request.getParameter("city");
		String tinNo = request.getParameter("tinNo");
		String address = request.getParameter("address");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();

		long supplierID =Long.parseLong(supplierId);
		SupplierDetailsBean det = (SupplierDetailsBean) session.get(SupplierDetailsBean.class, supplierID);

		det.setDealerName(dealerName);
		det.setAddress(address);
		det.setContactNo(Long.parseLong(contactNo));
		det.setEmailId(emailId);
		det.setCity(city);
		det.setPersonName(personName);
		det.setLandline(Long.parseLong(landline));
		//det.setTinNo(tinNo);
		det.setGstNum(tinNo);

		session.saveOrUpdate(det);
		transaction.commit();

		System.out.println("Record updated successfully.");
	}
}
