<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>
<%@page import="com.Fertilizer.bean.AllBillNoBean"%>
<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>

<% boolean isHome = false; %>

<%@include file="commons/header.jsp"%>

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
<script src="/AgriSoft/staticContent/js/saleReports.js"></script>
</head>

<div class="row" style="min-height: 300px;">
	<div class="col-md-12">
		<h3>Left Tabs</h3>
		<hr />
		<div class="col-md-2">
			<ul class="nav nav-tabs tabs-left">
				<li class="active"><a href="#home" data-toggle="tab">Category
						Wise Sale Reports</a></li>
				<li><a href="#profile" data-toggle="tab">Product Name Wise
						Sale Reports</a></li>
				<li><a href="#billnowisegrosstotalreport" data-toggle="tab">Bill No Wise Gross Total Report</a></li>
				<li><a href="#payment" data-toggle="tab">Payment Mode Wise
						Sale Reports</a></li>
				<li><a href="#GSTWise" data-toggle="tab">GST Wise Reports</a></li>
				
				<li><a href="#CreditCustomerReport" data-toggle="tab">Credit Customer Reports</a></li>
				<li><a href="#WholesaleCustomerReport" data-toggle="tab">Wholesale Customer Reports</a></li>
			</ul>
		</div>
		
		<div class="col-xs-9">
			<!-- Tab panes -->
			<div class="tab-content">
				<!---------- 	Category Wise Sale reports -------------->
				<div class="tab-pane active" id="home">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Category Wise Sale
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab"
							href="#supplierSingleDate"><h4 style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#supplierBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">
						<div id="supplierSingleDate" class="tab-pane active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportForm">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2" style="font-size:15px;width: 166px;padding-top: 10px;" for="fk_cat_id">Product
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

										<label class="col-md-2" style="font-size:15px;width: 120px;padding-top: 10px;" for=""> Enter
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>
										<div class="col-md-2">
											<div class="input-group">
												<div align="center">
													<input type="button" id="btn" name="save"
														class="btn btn-lg btn-success btn-md button_hw button_margin_right"
														onclick="saleReportForSingleDateAsPerCategory()"
														value="Search" style="width: 110px;font-size: 17px;" />
												</div>
											</div>
										</div>
									</div>
								
									<div class="row form-group">
										
									</div>

									<div class="table-responsive">
										<table id="sale1" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Product Name</th>
													<th>Sold Date</th>
													<th>Sale Price</th>
													<th>Quantity</th>
													<th>GST %</th>
													<th>IGST %</th>
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="7" style="text-align: right">Total Rs:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>

						<!------ 	Between Two Dates  ----->
					
						<div id="supplierBetweenTwoDate" class="tab-pane fade">

							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportForm1">
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
													CategoryDetailsDao cdd1 = new CategoryDetailsDao();
													List cList1 = cdd1.getAllMainCat();
												%>
												<input list="cat_drop7" id="fk_cat_id7" class="form-control">
												<datalist id="cat_drop7">
													<%
														for (int i = 0; i < cList1.size(); i++) {
															CategoryDetailsBean catBean = (CategoryDetailsBean) cList1
																	.get(i);
													%>

													<option data-value="<%=catBean.getCatId()%>"
														value="<%=catBean.getCategoryName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>
									</div>
							
									<div class="row form-group">
										<label class="col-md-2 control-label" for="customerName">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDate" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<label class="col-md-2 control-label" for="endDate">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDate" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="saleReportBetweenTwoDatesAsPerCategory()"
													value="Search" />
											</div>
										</div>
									</div>
							
									<div class="table-responsive">
										<table id="saleBetTwoDates" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Product Name</th>
													<th>Sold Date</th>
													<th>Sale Price</th>
													<th>Quantity</th>
													<th>GST %</th>
													<th>IGST %</th>
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="7" style="text-align: right">Total Rs:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>

				<!---------   Item Wise Reports--------->

				<div class="tab-pane" id="profile">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Product Name Wise Sale
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab"
							href="#customerSingleDate"><h4 style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#customerBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->
						
						<div id="customerSingleDate" class="tab-pane active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 col-md-offset-1 control-label"
											for="fk_cat_id">Product Category<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
												<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
												<%
													CategoryDetailsDao cdd9 = new CategoryDetailsDao();
													List cList9 = cdd9.getAllMainCat();
												%>
												<input list="cat_drop" id="fk_cat_id" class="form-control"
													onchange="getAllProduct()">
												<datalist id="cat_drop">
													<%
														for (int i = 0; i < cList9.size(); i++) {
															CategoryDetailsBean cat9 = (CategoryDetailsBean) cList9.get(i);
													%>

													<option data-value="<%=cat9.getCatId()%>"
														value="<%=cat9.getCategoryName()%>">
														<%
															}
														%>
												</datalist>
											</div>
										</div>

										<label class="col-md-2 control-label" for="product">Product
											Name<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span> <select class="form-control" id='proName' name="proName">
												</select>

											</div>
										</div>
									</div>
								
									<div class="row form-group">
										<label class="col-md-3 control-label"> Enter Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate1" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<div class="col-md-3 col-md-offset-1">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="saleReportforSingleDateAsPerPro()"
													value="Search" />
											</div>
										</div>
									</div>
									
									<div class="table-responsive">
										<table id="sale3" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Product Name</th>
													<th>Payment Mode</th>
													<th>Sold Date</th>
													<th>Sale Price</th>
													<th>Quantity</th>
													<th>GST %</th>
													<th>IGST %</th>
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="8" style="text-align: right">Total Rs:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>

						<!------ 	Between Two Dates  ----->
						
						<div id="customerBetweenTwoDate" class="tab-pane fade">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>

							<form class="form-horizontal" method="post" action=""
								name="saleReportTwoDates">
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
													CategoryDetailsDao cdao = new CategoryDetailsDao();
													List catList = cdao.getAllMainCat();
												%>
												<input list="cat_drop_For_sale" id="fk_cat_id_for_sale"
													class="form-control" onchange="getAllProductForSale()">
												<datalist id="cat_drop_For_sale">
													<%
														for (int i = 0; i < catList.size(); i++) {
															CategoryDetailsBean catB = (CategoryDetailsBean) catList.get(i);
													%>

													<option data-value="<%=catB.getCatId()%>"
														value="<%=catB.getCategoryName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>

										<label class="col-md-2 control-label" for="product">Product
											Name<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span> <select class="form-control" id='proName1' name="proName1">
												</select>

											</div>
										</div>
									</div>
							
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label" for="customerName">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDateForSale"
													placeholder="Start Date" class="form-control input-md"
													type="text">
											</div>
										</div>

										<label class="col-md-2 control-label" for="village">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDateForSale"
													placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group buttons_margin_top ">
										<div align="center">

											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="saleReportforBetweenTwoAsPerProName()"
												value="Search" />

										</div>
									</div>
							
									<table id="sale4" class="display"
										style="border: 2px solid black; border-collapse: collapse;">
										<thead>
											<tr>
												<th>Customer Bill No</th>
												<th>Product Name</th>
												<th>Payment Mode</th>
												<th>Sold Date</th>
												<th>Sale Price</th>
												<th>Quantity</th>
												<th>GST %</th>
												<th>IGST %</th>
												<th>Total Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="8" style="text-align: right">Total Rs:</th>
												<th></th>
											</tr>
										</tfoot>
									</table>
								</fieldset>
							</form>
						</div>
					</div>
				</div>

				<!-- Payment Mode Wise -->
			
				<div class="tab-pane" id="payment">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Payment Mode Wise
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab"
							href="#catWisePaymentMode"><h4 style="color: black">Category
									Wise</h4></a></li>
						<li><a data-toggle="tab" href="#dateWisePaymentMode"><h4
									style="color: black">DateWise</h4></a></li>
						<li><a data-toggle="tab" href="#rangeWisePaymentMode"><h4
									style="color: black">Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">
						<!--  Category Wise -->
						<div id="catWisePaymentMode" class="tab-pane active">
							<form class="form-horizontal" method="post" action="" name="pay">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-3  control-label"
											for="fk_cat_id_for_payment">Product Category<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group ">

												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
												<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
												<%
													CategoryDetailsDao catdao = new CategoryDetailsDao();
													List catListPay = catdao.getAllMainCat();
												%>
												<input list="cat_drop_For_payment"
													id="fk_cat_id_for_payment" class="form-control">
												<datalist id="cat_drop_For_payment">
													<%
														for (int i = 0; i < catListPay.size(); i++) {
															CategoryDetailsBean catPaybean = (CategoryDetailsBean) catListPay
																	.get(i);
													%>

													<option data-value="<%=catPaybean.getCatId()%>"
														value="<%=catPaybean.getCategoryName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>
										<div class="col-md-2 control-label">
											<label for="paymentMode"> Payment Mode<sup>*</sup></label>
										</div>

										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-usd"></i>
												</span> <select class="form-control" id="paymentModeId">
													<option value="selected">-Select Type--</option>
													<option value="cash">Cash</option>
													<option value="cheque">Cheque</option>
													<option value="card">Card</option>
													<option value="neft">NEFT</option>
												</select>
											</div>
										</div>
									</div>
							
									<div class="row form-group">
										<div class="col-md-3 Col-md-offset-4">
											<div class="input-group">

												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="paymentModeReport()" value="Search" />

											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="paymentModeSelect" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Customer Name</th>
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="2" style="text-align: right">Total Rs:</th>
													<th></th>

												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>

						<!-- Date wise -->
						
						<div id="dateWisePaymentMode" class="tab-pane">
							<form class="form-horizontal" method="post" action=""
								name="datepaymentMode">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label"> Enter Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDateForPaymentMode"
													placeholder="Start Date" class="form-control input-md"
													type="text">
											</div>
										</div>

										<label class="col-md-3  control-label"
											for="fk_cat_id_for_payment_mode">Product Category<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group ">

												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
												<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
												<%
													CategoryDetailsDao paymentModecatdao = new CategoryDetailsDao();
													List catListPayment = paymentModecatdao.getAllMainCat();
												%>
												<input list="cat_drop_For_payment_mode"
													id="fk_cat_id_for_payment_mode" class="form-control">
												<datalist id="cat_drop_For_payment_mode">
													<%
														for (int i = 0; i < catListPayment.size(); i++) {
															CategoryDetailsBean catPaymentbean = (CategoryDetailsBean) catListPayment
																	.get(i);
													%>

													<option data-value="<%=catPaymentbean.getCatId()%>"
														value="<%=catPaymentbean.getCategoryName()%>">
														<%
															}
														%>
												</datalist>
											</div>
										</div>
									</div>
							
									<div class="row form-group">
										<div class="col-md-3 Col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="paymentModeReportAsPerSingleDate()" value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="SinglePayment" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Cash Payment Amount</th>
													<th>Cheque Payment Amount</th>
													<th>NEFT Payment Amount</th>
													<th>Card Payment Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="1" style="text-align: right">Total Rs:</th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>

												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>

						<!-- Range -->
					
						<div id="rangeWisePaymentMode" class="tab-pane">
							<form class="form-horizontal" method="post" action=""
								name="twoDatepaymentMode">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">

										<label class="col-md-2 control-label" for="customerName">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDateForPay"
													placeholder="Start Date" class="form-control input-md"
													type="text">
											</div>
										</div>

										<label class="col-md-2 control-label" for="village">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDateForPay"
													placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>
					
									<div class="row form-group">
										<label class="col-md-2  control-label"
											for="fk_cat_id_for_payment_mode_two">Product Category<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group ">

												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
												<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
												<%
													CategoryDetailsDao paymentModecatdao1 = new CategoryDetailsDao();
													List catListPayment1 = paymentModecatdao1.getAllMainCat();
												%>
												<input list="cat_drop_For_payment_mode_two"
													id="fk_cat_id_for_payment_mode_two" class="form-control">
												<datalist id="cat_drop_For_payment_mode_two">
													<%
														for (int i = 0; i < catListPayment1.size(); i++) {
															CategoryDetailsBean catPaymentbean1 = (CategoryDetailsBean) catListPayment1
																	.get(i);
													%>

													<option data-value="<%=catPaymentbean1.getCatId()%>"
														value="<%=catPaymentbean1.getCategoryName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>

										<div class="col-md-3 Col-md-offset-1">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="paymentModeReportForTwoDates()" value="Search" />
											</div>
										</div>
									</div>
						
									<div class="table-responsive">
										<table id="TwoPayment" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Bill No</th>
													<th>Cash Payment Amount</th>
													<th>Cheque Payment Amount</th>
													<th>NEFT Payment Amount</th>
													<th>Card Payment Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="1" style="text-align: right">Total Rs:</th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>

												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				
				<!--  GST wise -->
				
				<div class="tab-pane" id="GSTWise">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">GST Wise Sale Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>
					<form class="form-horizontal" method="post" action="" name="gst"
						style="margin-top: -40px;">
						<div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="date" id="gstFisDate" placeholder="Start Date"
										class="form-control input-md" type="text">
								</div>
							</div>

							<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-map-marker"></i>
									</span> <input type="date" id="gstEndDate" placeholder="End Date"
										class="form-control input-md ac_district" type="text">
								</div>
							</div>
						</div>

						<div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2  control-label"
								for="fk_cat_id_for_payment_mode">Product Category<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group ">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span>

									<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
									<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
									<%
										CategoryDetailsDao GSTcatdao = new CategoryDetailsDao();
										List catListGST = GSTcatdao.getAllMainCat();
									%>
									<input list="cat_drop_For_GST" id="fk_cat_id_for_GST"
										class="form-control">
									<datalist id="cat_drop_For_GST">
										<%
											for (int i = 0; i < catListGST.size(); i++) {
												CategoryDetailsBean catGSTbean = (CategoryDetailsBean) catListGST
														.get(i);
										%>

										<option data-value="<%=catGSTbean.getCatId()%>"
											value="<%=catGSTbean.getCategoryName()%>">
											<%
												}
											%>
										
									</datalist>
								</div>
							</div>

							<div class="col-md-3 Col-md-offset-1">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="gstWiseSaleReport()" value="Search" />
								</div>
							</div>
						</div>
			
						<div class="table-responsive">
							<table id="gstSale" class="display table table-boardered"
								 style="border: 2px solid black; border-collapse: collapse;">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>date</th>
										<th>Name Of Customer</th>
										<th>Bill No.</th>
										<th>GST No</th>
										<th>HSN No</th>
										<th>Item Description</th>
										<th>Item Rate</th>
										<th>Quantity</th>
										<th>Amount</th>
										<th>GST& SGST 5% Amount</th>
										<th>GST& SGST 12% Amount</th>
										<th>GST& SGST 18% Amount</th>
										<th>GST& SGST 28% Amount</th>
										<th>IGST 5% Amount</th>
										<th>IGST 12% Amount</th>
										<th>IGST 18% Amount</th>
										<th>IGST 28% Amount</th>
										<th>Total Tax Amount</th>
										<th>Total Amount including Tax</th>

									</tr>
								</thead>
								<tfoot>
									<tr>
										<th colspan="7" style="text-align: right">Total Rs:</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</form>
				</div>
				
				<!---------   Bill No Wise Gross Total Reports--------->

				<div class="tab-pane" id="billnowisegrosstotalreport">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Bill No Wise Sale Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab" href="#customerSingleDate"><h4 style="color: black">Bill No Wise</h4></a></li>
						<!-- <li><a data-toggle="tab" href="#customerBetweenTwoDate"><h4 style="color: black">Range</h4></a></li> -->
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->
						
						<div id="customerSingleDate" class="tab-pane active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
									
										<label class="col-md-2" style="
    
    font-size: 18px;
    margin-top: 0px;
" for="paymentMode">
								Category<sup>*</sup>
							</label>
							<div class="col-md-3" style="
    margin-left: -60px;
">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> <select class="form-control" id="category" onchange="getAllBillNoCategoryWise()">
										<option value="selected">-Select Category--</option>
										<option value="Fertilizer">Fertilizer</option>
										<option value="Seed">Seed</option>
										<option value="Pesticide">Pesticide</option>
									</select>
								</div>
							</div>
									
									
									
										<label class="col-md-2" style="
    text-align:  right;
    font-size: 18px;
    margin-top: 4px;
"
											for="fk_cat_id">Bill No<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<%-- <%
												
													FertilizerBillDao fbd=new FertilizerBillDao();
													List billlist=fbd.getAllBillNo();
												%> --%>
												<input list="billno_drop" id="billno" class="form-control"
													onchange="getAllBill_drop()">
												<datalist id="billno_drop"> </datalist>
												<%-- 	<%
														for (int i = 0; i < billlist.size(); i++) {
															
															System.out.println("list of bill no in jsp"+i);
															AllBillNoBean billno = (AllBillNoBean) billlist.get(i);
													%>

													<option data-value="<%=billno.getBillno()%>"
														value="<%=billno.getBillno()%>">
														<%
															}
														%>
												</datalist> --%>
											</div>
											
										</div>

											<div class="col-md-2">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="grossTotalAsPerBillno()"
													value="Search" />
											</div>
										</div>
										
									</div>
									
									<div class="table-responsive">
										<table id="grosstotalasperbillno" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Bill No </th>
													<th>Customer Name</th>
													<th>Gross Total</th>
																									</tr>
											</thead>
											<tfoot>
												<tr>
													<!-- <th colspan="" style="text-align: right">Total Rs:</th> -->
													<th></th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
			</div>
		</div>
		
		
		<!---------   Credit Customer Reports Range Wise--------->

				<div class="tab-pane" id="CreditCustomerReport">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Credit Customer Sale Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab" href="#CreditcustRangeWiseSaleReport"><h4 style="color: black">Range Wise</h4></a></li>
						 <li><a data-toggle="tab" href="#CreditCustRangeWiseSaleReport"><h4 style="color: black">Range Wise Balance</h4></a></li>
						  <li><a data-toggle="tab" href="#CreditCustNamAndRangeWiseSaleReport"><h4 style="color: black">Name & Range Wise</h4></a></li> 
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->
						
						<div id="CreditcustRangeWiseSaleReport" class="tab-pane active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										
										
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
														</span> <input type="date" id="StartDate" placeholder="Start Date"
														class="form-control input-md" type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="EndDate" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
												</div>
											</div>
									</div>
										
											<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditCustomerSaleReport()"
													value="Search" />
											</div>
										</div>
										
									</div>
									
									<div class="table-responsive">
										<table id="creditcustrangewisetsalereportable" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Credit Cust Name </th>
													<th>Date</th>
													<th>Category</th>
													<th>Bill No</th>
													<th>Bill amount</th>
													<th>Paid Amount</th>
																									</tr>
											</thead>
											<tfoot>
												<tr>
													<!-- <th colspan="" style="text-align: right">Total Rs:</th> -->
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
					
					<!-- Credit Customer Sale Balance Report -->
						<div id="CreditCustRangeWiseSaleReport" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										
										
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
														</span> <input type="date" id="StartDate1" placeholder="Start Date"
														class="form-control input-md" type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="EndDate1" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
												</div>
											</div>
									</div>
										
											<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditCustomerSaleBalanceReport()"
													value="Search" />
											</div>
										</div>
										
									</div>
									
									<div class="table-responsive">
										<table id="creditcustrangewisetsaleBalancereportable" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Name </th>
													<th>Total Amount</th>
													<th>Paid Amount</th>
													<th>Balance Amount</th>
													
																									</tr>
											</thead>
											<tfoot>
												<tr>
													<!-- <th colspan="" style="text-align: right">Total Rs:</th> -->
													<th></th>
													<th></th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
			
						<!-- Name And Range Wise Credit Customer Sale Report-->
						<div id="CreditCustNamAndRangeWiseSaleReport" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="saleReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										
										<div id="RegularCustDropDown" class="form-group">
						<label class="col-md-2 control-label" for="creditCustomerName">
							Customer Name<sup>*</sup>
						</label>
						<div class="col-md-2" style="margin-left: 8px;width: 343px;">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span>

								<%
							CustomerDetailsDao dao = new CustomerDetailsDao();
           						List cust =dao.getAllCustomer();
							
							%>

								<input type="text" id="Customername" list="cust_drop1" class="form-control">
								<datalist id="cust_drop1">

									<%
					           for(int i=0;i<cust.size();i++){
					        	   CustomerDetailsBean bean =(CustomerDetailsBean)cust.get(i);
							%>
									<option data-value="<%=bean.getCustId()%>"><%=bean.getFirstName() %>
										<%=bean.getLastName() %>
									</option>
									<%
				      			}
				    		%>
								</datalist>
							</div>
					</div>
					
				</div>	
										
										
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
														</span> <input type="date" id="StartDate2" placeholder="Start Date"
														class="form-control input-md" type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="EndDate2" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
												</div>
											</div>
									</div>
										
											<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditCustomerSaleNameAndRangeReport()"
													value="Search" />
											</div>
										</div>
										
									</div>
									
									<div class="table-responsive">
										<table id="creditcustrangewisetsalenameandrangereportable" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Credit Cust Name </th>
													<th>Date</th>
													<th>Category</th>
													<th>Bill No</th>
													<th>Bill amount</th>
													<th>Paid Amount</th>
																									</tr>
											</thead>
											<tfoot>
												<tr>
													<!-- <th colspan="" style="text-align: right">Total Rs:</th> -->
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
						
			</div>
	</div>
	
	
		<!---------   Wholesale Customer Report--------->

				<div class="tab-pane" id="WholesaleCustomerReport">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Credit Customer Sale Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab" href="#WholecustRangeWiseSaleReport"><h4 style="color: black">Range Wise</h4></a></li>
						<!--  <li><a data-toggle="tab" href="#CreditCustRangeWiseSaleReport"><h4 style="color: black">Range Wise Balance</h4></a></li>
						  <li><a data-toggle="tab" href="#CreditCustNamAndRangeWiseSaleReport"><h4 style="color: black">Name & Range Wise</h4></a></li>  -->
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->
						
						<div id="WholecustRangeWiseSaleReport" class="tab-pane active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="wholesaleCustReportFormItem">
								<fieldset>
									<div class="row form-group" style="margin-top: 20px">
										
										
									<div class="row form-group" style="margin-top: 20px">
										<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
														</span> <input type="date" id="StartDate11" placeholder="Start Date"
														class="form-control input-md" type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="EndDate11" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
												</div>
											</div>
									</div>
										
											<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getWholesaleCustomerSaleReportRangewise()"
													value="Search" />
											</div>
										</div>
										
									</div>
									
									<div class="table-responsive">
										<table id="WholesaleCustRangeWiseSaleReport" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Credit Cust Name </th>
													<th>Date</th>
													<th>Category</th>
													<th>Bill No</th>
													<th>Bill amount</th>
													<th>Paid Amount</th>
																									</tr>
											</thead>
											<tfoot>
												<tr>
													<!-- <th colspan="" style="text-align: right">Total Rs:</th> -->
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>
	</div>
	
</div>
<%@include file="commons/newFooter.jsp"%>
