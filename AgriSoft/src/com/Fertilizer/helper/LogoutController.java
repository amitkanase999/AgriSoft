package com.Fertilizer.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void logoutUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("thank you!!, Your are successfully logged out!!");
		HttpSession session=request.getSession(false);  
		response.sendRedirect("/AgriSoft/jsp/login.jsp");
		session.setAttribute("user", null);
		session.removeAttribute("user");
	}
	
	
	public boolean tbBackup(String dbName, String dbUserName, String dbPassword, String path) {
		
		

		String executeCmd = "";
		executeCmd = "C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + "fertilizer" + " -r " + path;
		Process runtimeProcess;
		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main1() {

		LogoutController bb = new LogoutController();
		Date date = new Date();
				
		//bb.tbBackup("cloth", "root", "root", "F:/databackup/cloth.sql");
		// bb.tbBackup("cloth", "root", "root", "E:/databackup/cloth.sql");
		bb.tbBackup("fertilizer", "root", "root", "D:/databackup/fertilizer.sql");

	}
	
}
