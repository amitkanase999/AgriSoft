<%@page import="com.Fertilizer.hibernate.UserDetailsBean"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.Fertilizer.utility.HibernateUtility"%>
<%@page import="com.Fertilizer.dao.GoodsReceiveDao"%>
<%@page import="com.Fertilizer.bean.GoodsReceiveDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.hibernate.ProductDetailsBean"%>
<%@page import="com.Fertilizer.dao.ProductDetailsDao"%>

<%
	response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>

<%
	String type1= "";
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
<html>
<head>
<title>Embel Technologies Pvt Ltd</title>
<link href="/AgriSoft/staticContent/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/notofication.css">
</head>

<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/notofication.css">
<script type="text/javascript">
</script>
</head>

<body background="/AgriSoft/staticContent/images/all.jpg">
	<div class="row header_margin_top">
		<div align="center">
			<h2 class="form-name style_heading">Welcome</h2>
		</div>

		<div class="col-md-offset-9" id="report">
			<label id="demo"></label>
			<script>
				var date = new Date();
				document.getElementById("demo").innerHTML = date.toDateString();
				;
			</script>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-offset-1 col-md-10">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>

	<div class="container col-sm-offset-2">
		<div class="row form-group col-md-offset-2 ">
			<button style="height: 150px; width: 285px" id="blinker"
				class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#expiryProducts">
				Upcoming Expiry Date Products<br>click to view
			</button>

			<!-- Modal -->
			<div class="modal fade" id="expiryProducts" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<!-- Modal Header -->
						<div class="modal-header" style="background-color: white;">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Products Expire
								Within 10 Days</h4>
						</div>
						<!-- Modal Body -->
						<div class="modal-body">
							<h5 class="text-center" style="font-weight: bold;">Re-Order
								Information</h5>
							<%
								GoodsReceiveDao productForNotification = new GoodsReceiveDao();
								List<GoodsReceiveDetail> notificationProducts =productForNotification.getAllProductForNotification();
							%>

							<table>
								<tr>
									<th>Product Name</th>
									<th>Stock</th>
									<th>Batch Number</th>
									<th>Expiry Date</th>
								</tr>
								<%
									for(int i=0;i<notificationProducts.size();i++){
									GoodsReceiveDetail goodsForNotification =(GoodsReceiveDetail)notificationProducts.get(i);
								%>
								<tr>
									<td><%=goodsForNotification.getProductName()%></td>
									<td><%=goodsForNotification.getStock()%></td>
									<td><%=goodsForNotification.getBatchNumber()%></td>
									<td><%=goodsForNotification.getExpiryDate()%></td>
									<%
										}
									%>

								</tr>
							</table>

							<div style="text-align: center" class="modal-footer">
								<button type="button" class="btn btn-default btn-custom"
									data-dismiss="modal">Ignore</button>
								<button type="button" class="btn btn-primary"
									onclick="openAdvanceBooking()">Place Order</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- For Stock for seed and pesticide -->
			<button style="height: 150px; width: 285px" id="blinker1"
				class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#reOrderProducts">
				Low Stock Products<br>click to view
			</button>
		</div>
		
		<!-- Modal -->
		
		<div class="modal fade" id="reOrderProducts" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header" style="background-color: white;">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Low Inventory
							Warning!!!!!</h4>
					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#seed"><h4
									style="color: blue">Seed</h4></a></li>
						<li><a data-toggle="tab" href="#pesti"><h4
									style="color: blue">Pesticide</h4></a></li>
						<li><a data-toggle="tab" href="#ferti"><h4
									style="color: blue">Fertilizer</h4></a></li>
					</ul>

					<!------ Seed ------->
				
					<div id="seed" class="tab-pane fade in active">
						<!-- Modal Body -->
						<div class="modal-body">
							<h5 class="text-center" style="font-weight: bold;">Re-Order
								Information</h5>
							<%
								GoodsReceiveDao productForNotificationForSeed = new GoodsReceiveDao();
								           						List<GoodsReceiveDetail> notificationProducts1 =productForNotificationForSeed.getAllSeedAndPestiForStockNotification();
							%>

							<table>
								<tr>
									<th>Product Name</th>
									<th>Company Name</th>
									<th>Weight</th>
									<th>Batch Number</th>
									<th>Stock</th>

								</tr>
								<%
									for(int i=0;i<notificationProducts1.size();i++){
														        	   GoodsReceiveDetail goodsForNotification1 =(GoodsReceiveDetail)notificationProducts1.get(i);
								%>
								<tr>
									<td><%=goodsForNotification1.getProductName()%></td>
									<td><%=goodsForNotification1.getCompany()%></td>
									<td><%=goodsForNotification1.getWeight()%></td>
									<td><%=goodsForNotification1.getBatchNumber()%></td>
									<td><%=goodsForNotification1.getStock()%></td>

									<%
										}
									%>
								</tr>
							</table>

							<div style="text-align: center" class="modal-footer">
								<button type="button" class="btn btn-default btn-custom"
									data-dismiss="modal">Ignore</button>
								<button type="button" class="btn btn-primary"
									onclick="openAdvanceBooking()">Place Order</button>

								<script type="text/javascript">
									function openAdvanceBooking() {
										location
												.replace("/AgriSoft/jsp/purchaseOrderDetails.jsp");
									}
								</script>
							</div>
						</div>
					</div>

					<!--  Pesticide -->
					<div id="pesti" class="tab-pane fade">
						<div class="modal-body">
							<h5 class="text-center" style="font-weight: bold;">Re-Order
								Information</h5>
							<%
								GoodsReceiveDao productForNotificationForferti = new GoodsReceiveDao();
								List<GoodsReceiveDetail> notificationProducts2 =productForNotificationForferti.getPestiStockForStockNotification();
							%>
							<table>
								<tr>
									<th>Product Name</th>
									<th>Company Name</th>
									<th>Weight</th>
									<th>Batch Number</th>
									<th>Stock</th>
								</tr>
								<%
									for(int i=0;i<notificationProducts2.size();i++){
									GoodsReceiveDetail goodsForNotification2 =(GoodsReceiveDetail)notificationProducts2.get(i);
								%>
								<tr>
									<td><%=goodsForNotification2.getProductName()%></td>
									<td><%=goodsForNotification2.getCompany()%></td>
									<td><%=goodsForNotification2.getWeight()%></td>
									<td><%=goodsForNotification2.getBatchNumber()%></td>
									<td><%=goodsForNotification2.getStock()%></td>
									<%
										}
									%>
								</tr>
							</table>

							<div style="text-align: center" class="modal-footer">
								<button type="button" class="btn btn-default btn-custom"
									data-dismiss="modal">Ignore</button>
								<button type="button" class="btn btn-primary"
									onclick="openAdvanceBooking()">Place Order</button>

								<script type="text/javascript">
									function openAdvanceBooking() {
										location
												.replace("/AgriSoft/jsp/purchaseOrderDetails.jsp");
									}
								</script>
							</div>
						</div>
					</div>

					<!-- Fertilizer -->
					<div id="ferti" class="tab-pane fade">
						<div class="modal-body">
							<h5 class="text-center" style="font-weight: bold;">Re-Order
								Information</h5>
							<%
								GoodsReceiveDao productForNotificationForferti2 = new GoodsReceiveDao();
								List<GoodsReceiveDetail> notificationProducts3 =productForNotificationForferti2.getAllFertilizerForStockNotification();
							%>
							<table>
								<tr>
									<th>Product Name</th>
									<th>Company Name</th>
									<th>Weight</th>
									<th>Stock</th>
								</tr>
								<%
									for(int i=0;i<notificationProducts3.size();i++){
									GoodsReceiveDetail goodsForNotificationFerti =(GoodsReceiveDetail)notificationProducts3.get(i);
								%>
								<tr>
									<td><%=goodsForNotificationFerti.getProductName()%></td>
									<td><%=goodsForNotificationFerti.getCompany()%></td>
									<td><%=goodsForNotificationFerti.getWeight()%></td>
									<td><%=goodsForNotificationFerti.getStock()%></td>
									<%
										}
									%>
								</tr>
							</table>

							<div style="text-align: center" class="modal-footer">
								<button type="button" class="btn btn-default btn-custom"
									data-dismiss="modal">Ignore</button>
								<button type="button" class="btn btn-primary"
									onclick="openAdvanceBooking()">Place Order</button>

								<script type="text/javascript">
									function openAdvanceBooking() {
										location
												.replace("/AgriSoft/jsp/purchaseOrderDetails.jsp");
									}
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row form-group col-md-offset-2 ">
			<button style="height: 150px; width: 285px"
				class="btn btn-primary btn-lg" onclick="goToHome()">Home</button>
			<button style="height: 150px; width: 285px"
				class="btn btn-primary btn-lg" onclick="goToDashBoard()">DashBoard</button>
		</div>
		<script type="text/javascript">
			function goToHome() {
				window.location = '/AgriSoft/jsp/Billing.jsp';
			}
			function goToDashBoard() {
				window.location = '/AgriSoft/jsp/index.jsp';
			}
		</script>
	</div>
</body>