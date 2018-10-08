<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.bean.SupplierPaymentDetail"%>
<%@page import="com.Fertilizer.dao.SupplierPaymentDao"%>

<% boolean isHome = false; %>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>

<head>
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
<script src="/AgriSoft/staticContent/js/goodsReceive.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/podetails.js"></script>
</head>


<div class="container col-md-offset-1" style="float: left">

	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Debit Note And Credit Note
				Report</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#debit"><h4
					style="color: black">Debit Note</h4></a></li>
		<li><a data-toggle="tab" href="#credit"><h4
					style="color: black">Credit Note</h4></a></li>
	</ul>

	<div class="tab-content" style="float: left">

		<!-------- Debit Note ---------->

		<div id="debit" class="tab-pane fade in active">
			<div class="row"></div>
			<div id="report" style="text-align: center">
				<label id="demo"></label>
				<script>
					var date = new Date();
					document.getElementById("demo").innerHTML = date
							.toDateString();
				</script>
			</div>

			<%
				SupplierPaymentDao dao = new SupplierPaymentDao();
				List Lis1 = dao.getReturnAmountAfterPurchaseReturnForReport();
			%>
			<div>
				<table id="DueDateList" class="display" border="1">
					<thead>
						<tr>
							<th>Supplier Name</th>
							<th>Bill Number</th>
							<th>Returned Amount</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th></th>
						</tr>
					</tfoot>

					<tbody>
						<%
							for (int i = 0; i < Lis1.size(); i++) {
								SupplierPaymentDetail pro = (SupplierPaymentDetail) Lis1.get(i);
						%>
						<tr>
							<td class="align"><%=pro.getSupplierName()%></td>
							<td class="align"><%=pro.getBillNumber()%></td>
							<td class="align"><%=pro.getTotalAmount()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>

		<!-------- Credit Note ---------->
		
		<div id="credit" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-offset-1 col-md-10">
					<hr style="border-top-color: #c1b1b1;">
				</div>
			</div>

			<form class="form-horizontal" method="post" action=""
				name="saleReportForm">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-2 control-label" for="fk_cat_id">Product
							Category<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span>

								<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
								<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
								<%
									CategoryDetailsDao cdd = new CategoryDetailsDao();
									List cList = cdd.getAllMainCat();
								%>
								<input list="cat_drop6" id="fk_cat_id6" class="form-control">
								<datalist id="cat_drop6">
									<%
										for (int i = 0; i < cList.size(); i++) {
											CategoryDetailsBean cat = (CategoryDetailsBean) cList.get(i);
									%>
									<option data-value="<%=cat.getCatId()%>"
										value="<%=cat.getCategoryName()%>">
										<%
											}
										%>
									
								</datalist>
							</div>
						</div>

					</div>
					<div class="row form-group">
						<div class="col-md-3 col-md-offset-3">
							<div class="input-group">
								<div align="center">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="getSaleReturnDetails()" value="Search" />
								</div>
							</div>
						</div>
					</div>

					<div class="table-responsive">
						<table id="sale1" class="display"
							style="border: 2px solid black; border-collapse: collapse;">
							<thead>
								<tr>
									<th>Customer Bill No</th>
									<th>Customer Name</th>
									<th>Returned Amount</th>
								</tr>
							</thead>
							<tfoot>
							</tfoot>
						</table>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>