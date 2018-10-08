package com.Fertilizer.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.UserDetailsDao;
import com.Fertilizer.hibernate.UserDetailsBean;
import java.util.List;

public class UserDetailsHelper {

	public void userDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String address1 = request.getParameter("address1");
		String contactNo = request.getParameter("contactNo");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String pan = request.getParameter("pan");
		String city = request.getParameter("city");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("password2");
		String typeId = request.getParameter("typeId");

		UserDetailsBean udb = new UserDetailsBean();

		if(!"".equals(contactNo)){
			udb.setContactNo(Long.parseLong(contactNo));
		} else
		{
			udb.setContactNo(Long.parseLong("00"));
		}

		udb.setFirstName(firstName);
		udb.setLastName(lastName);
		udb.setAddress1(address1);
		udb.setCity(city);
		udb.setPan(pan);
		udb.setUserName(userName);
		udb.setPassword(password);
		udb.setTypeId(typeId);

		if(rePassword == null){
			udb.setRePassword("N/A");
		}
		else{
			udb.setRePassword(rePassword);
		}
		UserDetailsDao udd = new UserDetailsDao();
		udd.addUser(udb);
	}

	public List getAlluserName()
	{
		UserDetailsDao dao = new UserDetailsDao();
		return dao.getAllUsers();
	}
}
