<!DOCTYPE html>
<%@page import="com.Fertilizer.utility.HibernateUtility"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.Fertilizer.utility.PropertiesHelper"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<% 
   String abc = "english";
   if (session != null) {
   
   if (session.getAttribute("lan") != null) {
	   abc = (String) session.getAttribute("lan");
   }}
   else{
	   abc="english";
   }
 %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="" />

<title>Login Page</title>
<script src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script src="/AgriSoft/staticContent/js/logout.js"></script>
<!-- Bootstrap Core CSS -->
<link href="/AgriSoft/staticContent/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/AgriSoft/staticContent/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/AgriSoft/staticContent/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/AgriSoft/staticContent/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="/AgriSoft/staticContent/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/AgriSoft/staticContent/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/AgriSoft/staticContent/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/AgriSoft/staticContent/js/sb-admin-2.js"></script>
</head>

<body background="/AgriSoft/staticContent/images/7.jpg">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="font-weight: bold;">
							<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("please") %>
							:<%}%>
							<%if(abc.equals("english")){%>Please Login :<%}%>
						</h3>
					</div>
					
					<div class="panel-body">
						<form action="Login" method="post">
							<div>
								<div class="form-group">
									<label><span class="glyphicon glyphicon-user"></span> <%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("user_name")%>:<%}%>
										<%if(abc.equals("english")){%>User Name :<%}%></label> <input
										class="form-control" placeholder="Username" name="uname"
										id="uname" type="text" autofocus>
								</div>
								<div class="form-group">
									<label><span class="glyphicon glyphicon-eye-open"></span>
										<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("password")%>:<%}%>
										<%if(abc.equals("english")){%>Password :<%}%></label> <input
										class="form-control" placeholder="Password" name="pass"
										id="pass" type="password" value="">
								</div>

								<!-- Change this to a button or input when using this as a form -->
								<div class="wrapper">
									<button type="button" onclick="login()"
										class="btn btn-md btn-lg btn-success">
										<span class="glyphicon glyphicon-ok-circle"></span>
										<%if(abc.equals("marathi")){%>
										<%=PropertiesHelper.marathiProperties.getProperty("login")%>
										<%}%>
										<%if(abc.equals("english")){%>Login<%}%>
									</button>
									
									<button type="reset" class="btn btn-md btn-lg btn-danger">
										<span class="glyphicon glyphicon-remove-circle"></span>
										<%if(abc.equals("marathi")){%>
										<%=PropertiesHelper.marathiProperties.getProperty("Reset")%>
										<%}%>
										<%if(abc.equals("english")){%>Reset<%}%>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-4" style="float: right;">
				<div class="col-md-2">
					<label>Select language:</label>
				</div>
				<div class="col-md-2">
					<select class="selectpicker" id="language" name="language"
						data-style="btn-primary" onchange="language()">
						<option value="select">select Language</option>
						<option value="marathi">Marathi</option>
						<option value="english">English</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</body>
</html>