<!DOCTYPE html>

<%@page import="org.hibernate.Session"%>
<%@page import="com.Fertilizer.utility.HibernateUtility"%>
<%@page import="com.Fertilizer.hibernate.UserDetailsBean"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.0.4/js/bootstrap-alert.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<title>Dash Board</title>
<!-- Bootstrap Core CSS -->
<link href="/AgriSoft/staticContent/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/AgriSoft/staticContent/css/dashboard.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/AgriSoft/staticContent/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/AgriSoft/staticContent/css/morris.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/AgriSoft/staticContent/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="/AgriSoft/staticContent/css/timeline.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/AgriSoft/staticContent/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="/AgriSoft/staticContent/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/AgriSoft/staticContent/js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/AgriSoft/staticContent/js/sb-admin-2.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/AgriSoft/staticContent/js/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/AgriSoft/staticContent/js/raphael-min.js"></script>
<script src="/AgriSoft/staticContent/js/morris.min.js"></script>
<script src="/AgriSoft/staticContent/js/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/AgriSoft/staticContent/js/sb-admin-2.js"></script>
<script src="/AgriSoft/staticContent/js/logout.js"></script>

<script src="/AgriSoft/staticContent/js/graph.js"></script>
<script src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script src="/AgriSoft/staticContent/js/highcharts.js"></script>
<script src="/AgriSoft/staticContent/js/exporting.js"></script>
<style>
.alert {
	padding: 20px;
	background-color: #f44336;
	color: white;
}

.closebtn {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>
</head>

<div class="row">
	<div class="col-md-10">
		<marquee direction="right" onmouseover="this.stop();"
			onmouseout="this.start();">
			<font color="crimson" style="font-size: 25px;"> Dudulwar
				Fertilizer </font>
		</marquee>
	</div>
	<div id="report">
		<label id="demo"></label>
		<script>
				   var date = new Date();
			   	document.getElementById("demo").innerHTML = date.toDateString();;
				</script>
	</div>

</div>
<body>
	<div id="wrapper">

		<% String type1= "";
                     String name1 = "";
		             if (session != null) {
			
			         if (session.getAttribute("user") != null) {
				     name1 = (String) session.getAttribute("user");
				    
			            
	            	  
		              HibernateUtility hbu1=HibernateUtility.getInstance();
		              Session session2=hbu1.getHibernateSession();
		   
		              org.hibernate.Query query1 = session2.createQuery("from UserDetailsBean where userName =:usr");
		              query1.setParameter("usr", name1);
		              UserDetailsBean userDetail1 = (UserDetailsBean) query1.uniqueResult();
		              type1 = userDetail1.getUserName();
			         } 
			         else {
							
					     response.sendRedirect("/AgriSoft/jsp/login.jsp");
					     out.println("Please Login ");
				        }
			         
		           }
		             else {
							
					     response.sendRedirect("/AgriSoft/jsp/login.jsp");
					     out.println("Please Login ");
				        }
	           %>

		<!-- Navigation -->
		
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

			<div class="wrapper row">
				<button type="button" class="btn btn-lg btn-md btn-success"
					onclick="openHome()">
					<span class="glyphicon glyphicon-home"></span> Home
				</button>
				<button id="blinker"
					class="btn btn-lg btn-md btn-danger col-md-offset-3"
					onclick="openNotifications()">Upcoming Expiry Date
					Products And Low Stock Products</button>
				<button type="button"
					class="btn btn-lg btn-md btn-danger col-md-offset-2"
					onclick="Logout()">
					<span class="glyphicon glyphicon-off"></span> Logout
				</button>
			</div>

			<div class="navbar-default sidebar container" role="navigation">
				<div class="sidebar-nav navbar-collapse">

					<h4>Shortcuts</h4>

					<ul class="nav nav-pills nav-stacked">

						<li class="dropdown"><a href="purchaseReports.jsp"
							style="font-size: 26px;">Purchase Report </a></li>

						<li class="dropdown"><a href="saleReports.jsp"
							style="font-size: 26px;">Sale Report </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2 style="color: crimson">Graph</h2>
						</div>
						<div class="panel-body" align="center">
							<div class="flot-chart">
								<div class="flot-chart">
									<input class="btn  list-group-item-success" type="button"
										value="DayWise Sale" onclick="stock.day();"> <input
										class="btn  list-group-item-danger" type="button"
										value="Weekly Sale" onclick="stock.graph();"> <input
										class="btn  list-group-item-info" type="button"
										value="Monthly Sale" onclick="stock.month();">
									<div id="container"
										style="min-width: 310px; height: 400px; margin: 0 auto"></div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bell fa-fw"></i> Notifications Panel
						</div>

						<div class="row form-group">
							<button class="dropbtn" onclick="openDayCloserReport()">Day
								Close Report</button>
							</div> -->
						</div>
						<div class="row form-group">

							<script type="text/javascript">
						function openHome(){
							 window.location = '/AgriSoft/jsp/Billing.jsp';
						}
						function openDayCloserReport(){
							 window.location = '/AgriSoft/jsp/DayCloseReport.jsp';
						}
						
						function openNotifications(){
							 window.location = '/AgriSoft/jsp/notification.jsp';
						}
						
						var blink_speed = 850; var t = setInterval(function () { var ele = document.getElementById('blinker'); 
						  ele.style.visibility = (ele.style.visibility == 'hidden' ? '' : 'hidden');
						  }, blink_speed);
						</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
