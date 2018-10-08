<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>
<%@page import="com.Fertilizer.bean.AllBillNoBean"%>


<%
	boolean isHome = false;
%>

<%@include file="commons/header.jsp"%>

<head>
<script src="/AgriSoft/staticContent/js/AllGSTReport.js"></script>
<script src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/AgriSoft/staticContent/js/selectjj.js"></script>
<script type="text/javascript" src="/AgriSoft/staticContent/js/buttom.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
<script src="/AgriSoft/staticContent/js/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="/AgriSoft/staticContent/js/jqueryUi.js"></script>

<script type="text/javascript"	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script type="text/javascript"	src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript"	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script type="text/javascript"	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>

<link href="/AgriSoft/WebContent/staticContent/css/dataTa.css"	rel="stylesheet" type="text/css" media="all" />
<link href="/AgriSoft/staticContent/css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css" media="all">
<link href="/AgriSoft/staticContent/css/select.css" rel="stylesheet" type="text/css" media="all">
<link href="/AgriSoft/staticContent/css/button.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">

<link rel="stylesheet"	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet"	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">


</head>

<div class="row" style="min-height: 300px;">
	<div class="col-md-12">
		<h3>Left Tabs</h3>

		<div class="col-md-2" style="margin-top: 35px;">
			<ul class="nav nav-tabs tabs-left">
				<li class="active"><a href="#home" data-toggle="tab">GST
						Purchase Reports</a></li>
				<li><a href="#GSTSale" data-toggle="tab">GST Sale Reports</a></li>

			</ul>
		</div>

		<div class="col-xs-9">
			<!-- Tab panes -->
			<div class="tab-content">
				<!---------- 	Category Wise Sale reports -------------->
				<div class="tab-pane active" id="home">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">GST Purchase Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -41px;">
						<li class="active"><a data-toggle="tab"
							href="#RangeWisePurchaseReport"><h4 style="color: blue">Range Wise</h4></a></li>
						<!-- <li><a data-toggle="tab" href="#supplierBetweenTwoDate"><h4
									style="color: blue">Range</h4></a></li> -->
					</ul>

					<div class="tab-content" style="float: left">
						<div id="RangeWisePurchaseReport" class="tab-pane active">

							<form class="form-horizontal" method="post" action=""
								name="saleReportForm1">
								<fieldset>

									<div class="row form-group">


										<label class="col-md-2 control-label" for="startDate">Start
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="pstartDate" placeholder="Start Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>


										<label class="col-md-2 control-label" for="endDate">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="penddate" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<div class="col-md-3 col-md-offset-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getRangeWiseGSTPurchaseReport()" value="Search" />
											</div>
										</div>
									</div>

									<div class="table-responsive">
										<table id="RangewisePurchaseGstReport" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Sales Taxable</th>
													<th>Taxable Value</th>
													<th>CGST</th>
													<th>SGST</th>
													<th>IGST</th>
													<th>Total Tax Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="1" style="text-align: right">Total
														Laibility:</th>
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

						<!------ 	Between Two Dates  ----->

						<div id="supplierSingleDate" class="tab-pane fade">

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
															CategoryDetailsBean catBean = (CategoryDetailsBean) cList1.get(i);
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
										<div class="col-md-3 col-md-offset-3">
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




				<!---------- 	GST Sale reports -------------->
				<div class="tab-pane" id="GSTSale">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">GST Sale Reports</h2>
						</div>


					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab"
							href="#RangeWiseSaleReport"><h4 style="color: blue">Range
									Wise</h4></a></li>
						<!-- <li class="active"><a data-toggle="tab" href="#supplierSingleDate"><h4 style="color: blue">Datewise</h4></a></li> -->
						<li><a data-toggle="tab" href="#WhosaleCustInRangeSaleReport"><h4
									style="color: blue">Whosale In Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left; padding-top: 25px;">
						<div id="RangeWiseSaleReport" class="tab-pane active">

							<form class="form-horizontal" method="post" action=""
								name="saleReportForm1">
								<fieldset>

									<div class="row form-group">


										<label class="col-md-2 control-label" for="startDate">Start
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="startDate" placeholder="Start Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>


										<label class="col-md-2 control-label" for="endDate">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="enddate" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<div class="col-md-3 col-md-offset-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getRangeWiseGSTSaleReport()" value="Search" />
											</div>
										</div>
									</div>

									<div class="table-responsive">
										<table id="RangewiseSaleGstReport" class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Type</th>
													<th>Sales Taxable</th>
													<th>Taxable Value</th>
													<th>CGST</th>
													<th>SGST</th>
													<th>Total Tax Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="1" style="text-align: right">Total
														Laibility:</th>
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


						<div id="WhosaleCustInRangeSaleReport" class="tab-pane">

							<form class="form-horizontal" method="post" action=""
								name="saleReportForm1">
								<fieldset>

									<div class="row form-group">


										<label class="col-md-2 control-label" for="startDate">Start
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="startDate1" placeholder="Start Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>


										<label class="col-md-2 control-label" for="endDate">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="enddate1" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<div class="col-md-3 col-md-offset-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getGstSaleReportPercentageWise()" value="Search" />
											</div>
										</div>
									</div>

									<div class="table-responsive">
										<table id="RangewiseAndPercentageWiseSaleGstReport1"
											class="display"
											style="border: 2px solid black; border-collapse: collapse;">
											<thead>
												<tr>
													<th>Customer Name</th>
													<th>GST Number</th>
													<th>5%</th>
													<th>12%</th>
													<th>18%</th>
													<th>28%</th>
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="1" style="text-align: right">Total
														Laibility:</th>
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

			</div>
		</div>
		<%@include file="commons/newFooter.jsp"%>