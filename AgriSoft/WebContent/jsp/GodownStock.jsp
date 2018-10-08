<%@page import="com.Fertilizer.bean.StockDetail"%>
<%@page import="com.Fertilizer.hibernate.Stock"%>
<%@page import="com.Fertilizer.hibernate.GodownEntry"%>
<%@page import="com.Fertilizer.dao.GodownEntryDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.ProductStockDetailsBean"%>
<%@page import="com.Fertilizer.dao.ProductStockDetailsDao"%>
<%@page import="java.util.List"%>



<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>

<head>
<script src="/AgriSoft/staticContent/js/GodownWiseStockReport.js"></script> 
<script src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/selectjj.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/buttom.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.dataTables.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
<link href="/AgriSoft/WebContent/staticContent/css/dataTa.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/AgriSoft/staticContent/css/dataTables.jqueryui.min.css"
	rel="stylesheet" type="text/css" media="all">
<link href="/AgriSoft/staticContent/css/select.css" rel="stylesheet"
	type="text/css" media="all">
<link href="/AgriSoft/staticContent/css/button.css" rel="stylesheet"
	type="text/css" media="all">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">

<script type="text/javascript"> 
function openBilling() {
		 window.location = '/AgriSoft/jsp/Billing.jsp';
}
</script>
</head>

<body onload="getProductName()">
	<div class="container col-md-offset-1" style="float: left">

		<div class="row">
			<div align="center" style="margin-top: 75px">
				<h2 class="form-name style_heading">Godown Wise Stock</h2>
			</div>

			<div class="row">
				<div class="col-sm-offset-1 col-md-10">
					<hr style="border-top-color: #c1b1b1;">
				</div>
			</div>
		</div>

		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#godownwisestock"><h4
						style="color: black">Godown Wise Stock</h4></a></li>
			
		</ul>

		<div class="tab-content" style="float: left">

			<!-------- Category Wise ---------->

			<div id="godownwisestock" class="tab-pane fade in active">
				<div class="row"></div>
				<form class="form-horizontal" method="post" action=""
					name="fertiBill">
					<fieldset>
						<div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2 control-label" for="godown">Godown<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span>

									<%
											GodownEntryDao ged=new GodownEntryDao();
											List gList =ged.getAllGodown();
										
										%>
									<input list="godown_drop" id="godownName" class="form-control">
									<datalist id="godown_drop">
												<%
					           for(int i=0;i<gList.size();i++){
					        	   GodownEntry bean=(GodownEntry)gList.get(i);
					        	
							%>
					
									<option data-value="<%=bean.getPkGodownId()%>"
										value="<%=bean.getGodownName() %>">
										<%
				      			}
				    		%>
									</datalist>
								</div>
							</div>

							<div class="col-md-3">
								<div class="input-group">

									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="StockDetailsReportAsPerCat()" value="Search" />
								</div>
							</div>
						</div>
				
						<table id="godownstock" class="display">
							<thead>
								<tr>
									<th>Product Name</th>
									<th>Category</th>
									<th>Packing</th>
									<th>Batch No</th>
									<th>Expiry Date</th>
									<th>Available Qty</th>
								
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</tfoot>
						</table>
						
					
					</fieldset>
				</form>
			</div>

			
						
			
			
		</div>
	</div>
	<%@include file="commons/newFooter.jsp"%>
</body>