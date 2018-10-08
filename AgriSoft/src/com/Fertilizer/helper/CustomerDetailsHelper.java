package com.Fertilizer.helper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.bean.GetCreditCustomerDetails;
import com.Fertilizer.dao.CustomerDetailsDao;
import com.Fertilizer.hibernate.CustomerDetailsBean;
import com.Fertilizer.hibernate.WholesaleCustomerDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class CustomerDetailsHelper {

	public void customerDetails(HttpServletRequest request, HttpServletResponse response ){

		System.out.println("In helper");

		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");
		String emailId = request.getParameter("emailId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String middleName = request.getParameter("middleName");
		String zipCode = request.getParameter("zipCode");
		String aadhar = request.getParameter("aadhar");
		String gstno = request.getParameter("gstno");
		System.out.println(aadhar+"aadhar number");

		CustomerDetailsBean cdb = new CustomerDetailsBean();

		if(!"".equals(contactNo)){
			cdb.setContactNo(Long.parseLong(contactNo));
		} else
		{
			cdb.setContactNo(Long.parseLong("00"));
		}

		if(!"".equals(zipCode)){
			cdb.setZipCode(Long.parseLong(zipCode));
		} else
		{
			cdb.setZipCode(Long.parseLong("00"));
		}

		if(!"".equals(aadhar)){
			cdb.setAadhar(Long.parseLong(aadhar));
		} else
		{
			cdb.setAadhar(0l);
		}
		if(!"".equals(emailId)){
			cdb.setEmailId(emailId);
		} else
		{
			cdb.setEmailId("N/A");
		}
		cdb.setGstno(gstno);
		cdb.setFirstName(firstName.trim());
		cdb.setMiddleName(middleName.trim());
		cdb.setLastName(lastName.trim());
		cdb.setAddress(address);

		CustomerDetailsDao cdo = new CustomerDetailsDao();
		cdo.valCustomerDetails(cdb);
	}

	public Map getVillage(String creditCustomerId) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list = dao.getVillageByCustomerName(creditCustomerId);
		Map  map =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			bean.setVillage(o[0].toString());
			bean.setFirstName(o[1].toString());
			bean.setContactNo((BigInteger)o[2]);
			bean.setAadhar((BigInteger)o[3]);
			System.out.println("***************"+o[0]);
			map.put(bean.getVillage(),bean);
		}
		return map;
	}

	
	//wholesale customer registration
	public void wholesalecustDetails(HttpServletRequest request, HttpServletResponse response ){

		System.out.println("In helper");
		
		String shopname = request.getParameter("shopname");
		String address = request.getParameter("address");
		String mobileno = request.getParameter("mobileno");
		String emailid = request.getParameter("emailid");
		String gstno = request.getParameter("gstno");
		String aadharNo = request.getParameter("aadharNo");
		String zipCode = request.getParameter("zipCode");
		
		WholesaleCustomerDetailsBean bean=new WholesaleCustomerDetailsBean();
		bean.setShopname(shopname);
		bean.setAddress(address);
		bean.setContactno(Long.parseLong(mobileno));
		bean.setEmailid(emailid);
		bean.setGstno(gstno);
		bean.setAdharno(aadharNo);
		bean.setZipcode(Long.parseLong(zipCode));
		
		
		CustomerDetailsDao cdo = new CustomerDetailsDao();
		cdo.wholesaleCustomerDetails(bean);
	}

	
	
	
	public Map getAllBillByCustomers(String fkCustomerId, String catId) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getAllBillByCreditCustomer(fkCustomerId,catId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo((BigInteger)o[0]);
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getBillNo(),bean);

		}
		return map;
	}

	public Map getTotalAmtByBillNo(String billNo, String catId, String creditCustomer) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getTotalAmountByBill(billNo,catId,creditCustomer);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			System.out.println(Arrays.toString(o));
			String total = o[0].toString();
			Double total1 = Double.valueOf(total);
			System.out.println(total1);
			bean.setTotalAmount(total1);
			System.out.println("***************"+o[0]);
			System.out.println("Ih helper method getTotal");
			map.put(bean.getBillNo(),bean);

		}
		return map;
	}

	public Map getBalanceAmtByBillNo(String billNo, String creditCustomer, String catId) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getRemainingBalanceAmountByBill(billNo,creditCustomer,catId);
		Map  map =  new HashMap();
		System.out.println(list.size()+"LIST SIZE");
		int sic = list.size();
		if(sic==0)
		{
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			Double totalAmt = dao.getTotalAmt(billNo,catId);
			bean.setBalance(totalAmt);
			map.put(bean.getBalance(),bean);
		}
		else {
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
				String newBal = (o[0].toString());
				Double newBal1 = Double.valueOf(newBal);
				System.out.println(newBal1+"NEW BALANCE");

				bean.setBalance(newBal1);

				System.out.println("***************"+o[0]);
				map.put(bean.getBalance(),bean);
			}
		}
		return map;
	}

	public Map getCreditCustomerDetailsForEdit(Long customerId) {

		System.out.println("into helper class");
		CustomerDetailsDao dao1 = new CustomerDetailsDao();
		List catList = dao1.getCreditCustomerForEdit(customerId);

		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			bean.setFirstName(o[0].toString());
			bean.setMiddleName(o[1].toString());
			bean.setLastName(o[2].toString());
			bean.setEmail(o[3].toString());
			bean.setAddress(o[4].toString());
			bean.setContactNo((BigInteger)o[5]);
			bean.setZipCode((BigInteger)o[6]);
			bean.setAadhar((BigInteger)o[7]);

			map.put(bean.getFirstName(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	}

	public void editCreditCustomer(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("in EditEmployee helper");
		String strcustomerId = request.getParameter("customerId");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");
		String aadharNo = request.getParameter("aadharNo");
		String emailId = request.getParameter("emailId");
		String zipCode = request.getParameter("zipCode");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();

		long customerId =Long.parseLong(strcustomerId);
		CustomerDetailsBean det = (CustomerDetailsBean) session.get(CustomerDetailsBean.class, customerId);

		det.setFirstName(firstName);
		det.setMiddleName(middleName);
		det.setLastName(lastName);
		det.setAddress(address);
		det.setContactNo(Long.parseLong(contactNo));
		det.setAadhar(Long.parseLong(aadharNo));
		det.setEmailId(emailId);
		det.setZipCode(Long.parseLong(zipCode));

		session.saveOrUpdate(det);
		transaction.commit();

		System.out.println("Record updated successfully.");
	}
}
