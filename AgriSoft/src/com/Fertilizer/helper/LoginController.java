package com.Fertilizer.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.Fertilizer.hibernate.UserDetailsBean;
import com.Fertilizer.utility.HibernateUtility;
import com.Fertilizer.utility.ScheduledBackup;

public class LoginController extends HttpServlet {

	public void loginUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String un = request.getParameter("uname");
		String pwd = request.getParameter("pass");
		HibernateUtility hbu=HibernateUtility.getInstance();
		Session session1=hbu.getHibernateSession();
		org.hibernate.Query query = session1.createQuery("from UserDetailsBean where userName = :username AND password = :pwd ");
		query.setParameter("username", un);
		query.setParameter("pwd", pwd);
		UserDetailsBean uniqueResult = (UserDetailsBean) query.uniqueResult();

		String userName = uniqueResult.getUserName();
		String password = uniqueResult.getPassword();
		System.out.println(userName);

		if(un.equals(userName) && pwd.equals(password)){
			ScheduledBackup.backupOnTime();
			out.print("Welcome, " + un);
			HttpSession session = request.getSession(true); // reuse existing
		
			// session if exist
			
			response.sendRedirect("/AgriSoft/jsp/index1.jsp");					// or create one
			SupplierAccountDetailsHelper help = new SupplierAccountDetailsHelper();
			help.creditDebitAmount();
			session.setAttribute("user", un);
			
			// 30 seconds

			GoodsReceiveHelper goods = new GoodsReceiveHelper();
			goods.saveDailyStock();
		} 
		else {

			response.sendRedirect("/AgriSoft/jsp/login.jsp");
			out.println("<font color=red>Either user name or password is wrong.</font>");
		}
	}	

	public void language(HttpServletRequest request,
			HttpServletResponse response) {
		String lan = request.getParameter("language");
		HttpSession session = request.getSession(true); // reuse existing
		// session if exist
		// or create one
		session.setAttribute("lan", lan);
	}
}