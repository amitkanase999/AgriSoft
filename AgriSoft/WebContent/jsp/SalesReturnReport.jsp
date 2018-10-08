<%@page import="com.Fertilizer.bean.SupplierPaymentDetail"%>
<%@page import="com.Fertilizer.dao.SupplierPaymentDao"%>
<%@page import="com.Fertilizer.bean.SaleReports"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.dao.PurchaseRetunDao"%>
<%@page import="com.Fertilizer.hibernate.PurchaseReturnBean"%>

<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>

<head>
<script src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/selectjj.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/buttom.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.dataTables.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
<link href="/AgriSoft/staticContent/css/dataTa.css" rel="stylesheet"
	type="text/css" media="all" />
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

<!-- For datatable to pdf,print,excel etc conversion -->

<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript"
	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script type="text/javascript"
	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
<script src="/AgriSoft/staticContent/js/goodsReceive.js"
	type="text/javascript"></script>
<script src="/AgriSoft/staticContent/js/PurchaseReturnReport.js"
	type="text/javascript"></script>
</head>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Sales Return Reports</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home"><h4
					style="color: blue">Datewise</h4></a></li>
		<li><a data-toggle="tab" href="#sup"><h4 style="color: blue">Supplier
					Name Wise</h4></a></li>
	</ul>

	<div class="tab-content" style="float: left">

		<!-------- Single Date ---------->

		<div id="home" class="tab-pane fade in active">
			<div class="row"></div>

			<form class="form-horizontal" method="post" action="" name="">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-3 control-label" for=""> Enter Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="fDate" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>


						<div class="col-md-3">
							<div class="input-group">
								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="purchaseReportForSingleDate()" value="Search" />
							</div>
						</div>
					</div>

					<div class="table-responsive">
						<table id="purchase1" class="display">
							<thead>
								<tr>
									<th>Supplier Name</th>
									<th>Bill No</th>
									<th>Product Name</th>
									<th>Category</th>
									<th>Batch No</th>
									<th>Packing</th>
									<th>Buy Price</th>
									<th>Sale Price</th>
									<th>MRP</th>
									<th>Tax Per</th>
									<th>Quantity</th>
									<th>Return Quantity</th>
									<th>DC No</th>
									<th>Barcode</th>
									<th>Purchase Date</th>
									<th>Return Date</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="12" style="text-align: right">Total:</th>
									<th></th>
								</tr>
							</tfoot>
						</table>
					</div>
				</fieldset>
			</form>
		</div>

		<!-- 		Supplier Wise  -->
	
		<div id="sup" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action=""
				name="fertiBill">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-2 control-label" for="supplier">Supplier
							Name<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span>

								<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
								<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

								<%
										PurchaseRetunDao prd=new PurchaseRetunDao();
										List plist=prd.getAllSupplier();
		           						
									%>

								<input list="sup_drop" id="fkSupplierId" class="form-control">
								<datalist id="sup_drop">

									<%
							           for(int i=0;i<plist.size();i++){
							        	   PurchaseReturnBean bean=(PurchaseReturnBean)plist.get(i);
									%>

									<option data-value="<%=bean.getPurchase_return_pk()%>"
										value="<%=bean.getSupplier_name() %>">
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
									onclick="supplierWisePurchaseReport()" value="Search" />
							</div>
						</div>
					</div>
					
					<table id="purchase3" class="display">
						<thead>
							<tr>
								<th>Supplier Name</th>
								<th>Bill No</th>
								<th>Product Name</th>
								<th>Category</th>
								<th>Batch No</th>
								<th>Packing</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Tax Per</th>
								<th>Quantity</th>
								<th>Return Quantity</th>
								<th>DC No</th>
								<th>Barcode</th>
								<th>Purchase Date</th>
								<th>Return Date</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th colspan="13" style="text-align: right">Total:</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>
<%@include file="commons/newFooter.jsp"%>
