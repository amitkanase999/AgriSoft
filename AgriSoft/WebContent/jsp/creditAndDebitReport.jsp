<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>

<%boolean isHome = false;%>

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
<script src="/AgriSoft/staticContent/js/customerbillreport.js"></script>
<script src="/AgriSoft/staticContent/js/cashbankbook.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getYesterdarTotalAmount();
		getTodayCreditDebitReport();
		getTodayCreditDebitReport1();
		getTodaySaleTotalAmount();
	});
</script>
</head>

<div class="row" style="min-height: 300px;">
	<div class="col-md-12">
		<h3>Left Tabs</h3>
		<hr />
		<div class="col-md-2">
			<ul class="nav nav-tabs tabs-left">
				<li class="active"><a href="#homeToday" data-toggle="tab">Todays Credit and Debit</a></li>
				<li><a href="#profile" data-toggle="tab">Day Wise Credit and Debit</a></li>
				<li><a href="#payment" data-toggle="tab">All Credit and Debit</a></li>
				<!--  <li><a href="#GSTWise" data-toggle="tab">GST Wise Reports</a></li> -->
			</ul>
		</div>
		<div class="col-xs-9" style="margin-top: -38px;">
			<!-- Tab panes -->
			<div class="tab-content">

				<!---------- 	Todays Credit and Debit -------------->

				<div class="tab-pane active" id="homeToday">
					<div class="row">
						<div align="center" style="margin-top: 20px">
							<h2 class="form-name style_heading">Today Credit Debit Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<div style="margin-top: -40px;">
						<div class="col-md-offset-1 col-md-5">
							<table id="example1" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Credit</th>

									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>

						<div class="col-md-5">
							<table id="example2" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Debit</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<!-- <th colspan="0" style="text-align: right">Total:</th> -->
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>

					<div class="row form-group" style="margin-top: 25px;">
						<label class="col-md-2 control-label" for="village">Yesterday Amount:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="text" readonly="readonly" name="yesAmt"
									id="yesAmt" placeholder="YesterDay Amount"
									class="form-control input-md ac_district">
							</div>
						</div>


						<label class="col-md-2 control-label" for="village">Today Credit Amount:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="text" readonly="readonly" name="creAmt"
									id="creAmt" placeholder="Credit Amount"
									class="form-control input-md ac_district"> <input
									readonly="readonly" id="dupsaletotal" name="dupsaletotal"
									class="form-control input-md" type="hidden">
							</div>
						</div>
					</div>

					<div class="row form-group" style="margin-top: 25px;">
						<label class="col-md-2 control-label" for="village">Today Debit Amount:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="text" readonly="readonly" name="debAmt"
									id="debAmt" placeholder="YesterDay Amount"
									class="form-control input-md ac_district"> <input
									readonly="readonly" id="dupsaletotal1" name="dupsaletotal1"
									class="form-control input-md" type="hidden">
							</div>
						</div>

					<label class="col-md-2 control-label" for="village">Today Sale Amount:<sup>*</sup>
						</label>
							<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="text" readonly="readonly" name="totsaleAmt"
									id="totsaleAmt" placeholder="Credit Amount"
									class="form-control input-md ac_district">
							</div>
						</div>
					</div>

					<div class="row form-group" style="margin-top: 25px;">
						<label class="col-md-2 control-label" for="village">Today Remaining Amount:<sup>*</sup></label>
						 <div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="text" readonly="readonly" name="remAmt"
									id="remAmt" placeholder="YesterDay Amount"
									class="form-control input-md ac_district">
							</div>
						</div>
					</div>
				</div>

				<!---------   Day Wise Credit Debit Reports--------->

				<div class="tab-pane" id="profile">
					<div class="row">
						<div align="center" style="margin-top: 20px">
							<h2 class="form-name style_heading">Day Wise Credit Debit
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -40px;">
						<li class="active"><a data-toggle="tab" href="#home"><h4 style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#twoDates"><h4 style="color: black">Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">

						<!-------- Single Date ---------->

						<div id="home" class="tab-pane fade in active">
							<div class="row"></div>
							<form class="form-horizontal" method="post" action="" name="">
								<div class="row form-group" style="margin-top: 20px">
									<label class="col-md-3 control-label" for=""> Ente Date:<sup>*</sup>
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
												onclick="creditdebitForsingleDate(); creditdebitForsingleDate1();"
												value="Search" />
										</div>
									</div>
								</div>
								
								<div class="table-responsive">
									<div class="col-md-offset-1 col-md-5">
										<table id="example3" class="display	">
											<thead>
												<tr>
													<th>Name</th>
													<th>Credit</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th></th>
													<th></th>

												</tr>
											</tfoot>
										</table>
									</div>

									<div class="col-md-5">
										<table id="example4" class="display	">
											<thead>
												<tr>
													<th>Name</th>
													<th>Debit</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</form>
						</div>

						<!-- ---	Between Two Dates	---- -->
						
						<div id="twoDates" class="tab-pane ">
							<div class="row"></div>
							<form class="form-horizontal" method="post" action="">
								<div class="row form-group" style="margin-top: 20px">
									<label class="col-md-2 control-label" for="customerName">Start Date:<sup>*</sup></label>
									<div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="glyphicon glyphicon-user"></i>
											</span> <input type="date" id="fisDate" placeholder="Start Date"
												class="form-control input-md" type="text">
										</div>
									</div>

									<label class="col-md-2 control-label" for="village">End Date:<sup>*</sup>
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

								<div class="row form-group buttons_margin_top ">
									<div align="center">
										<input type="button" id="btn" name="save"
											class="btn btn-lg btn-success btn-md button_hw button_margin_right"
											onclick="creditdebitForBetTowDate(); creditdebitForBetTowDate1();"
											value="Search" />
									</div>
								</div>
								
								<div class="table-responsive">
									<div class="col-md-offset-1 col-md-5">
										<table id="example5" class="display	">
											<thead>
												<tr>
													<th>Name</th>
													<th>Credit</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>

									<div class="col-md-5">
										<table id="example6" class="display	">
											<thead>
												<tr>
													<th>Name</th>
													<th>Debit</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- Payment Mode Wise -->
				<div class="tab-pane" id="payment">
					<div class="row">
						<div align="center" style="margin-top: 20px">
							<h2 class="form-name style_heading">All Credit/Debit Reports</h2>
						</div>
						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" style="margin-top: -40px;">
						<li class="active"><a data-toggle="tab" href="#credit"><h4 style="color: black">All Credit Amount</h4></a></li>
						<li><a data-toggle="tab" href="#debit"><h4 style="color: black">All Debit Amount</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left; margin-top: 12px;">
						<div id="credit" class="tab-pane fade in active">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#homeCredit"><h4 style="color: black">Credit By Credit Customer</h4></a></li>
								<li><a data-toggle="tab" href="#twoDatesBillilng"><h4 style="color: black">Credit By Billilng</h4></a></li>
							</ul>

							<div class="tab-content" style="float: left; margin-top: 12px;">
							
								<!-------- Credit Amount By credit customer ---------->
							
								<div id="homeCredit" class="tab-pane fade in active">
									<div class="row"></div>
									<form class="form-horizontal" method="post" action="" name="fertiBill">
										<div class="row form-group" style="margin-top: 20px">
											<label class="col-md-2 control-label" for="customerName">Start Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="date" id="fisDate1" placeholder="Start Date"
														class="form-control input-md" type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="village">End Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="endDate1" placeholder="End Date"
														class="form-control input-md ac_district" type="text">
												</div>
											</div>
										</div>

										<div class="row form-group buttons_margin_top ">
											<div align="center">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditAmountByCreditCust()" value="Search" />
											</div>
										</div>

										<table id="exampleForCreditAmountByCreditCust" class="display">
											<thead>
												<tr>
													<th>Payment date</th>
													<th>Customer Bill No</th>
													<th>Customer Name</th>
													<th>Total Paid Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="3" style="text-align: right">Total:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</form>
								</div>

								<!-- ---	Credit Amount By custome Normal Custoomer billing	---->
								
								<div id="twoDatesBillilng" class="tab-pane ">
									<div class="row"></div>
									<form class="form-horizontal" method="post" action=""
										name="fertiBill">
										<div class="row form-group" style="margin-top: 20px">
											<label class="col-md-2 control-label" for="fk_cat_id">Product Category<sup>*</sup>
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
													<input list="cat_drop7" id="fk_cat_id7"
														class="form-control">
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
										
										<div class="row form-group" style="margin-top: 20px">
											<label class="col-md-2 control-label" for="customerName">Start Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="date" id="fisDatetwoDatesBillilng"
														placeholder="Start Date" class="form-control input-md"
														type="text">
												</div>
											</div>
											
											<label class="col-md-2 control-label" for="village">End	Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="endDatetwoDatesBillilng"
														placeholder="End Date"
														class="form-control input-md ac_district" type="text">
												</div>
											</div>
										</div>

										<div class="row form-group buttons_margin_top ">
											<div align="center">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditAmountByBilling()" value="Search" />
											</div>
										</div>
										
										<table id="exampletwoDatesBillilng" class="display">
											<thead>
												<tr>
													<th>Sold Date</th>
													<th>Customer Bill</th>
													<th>Customer Name</th>
													<!-- <th>Payment Mode</th> -->
													<th>Total Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="3" style="text-align: right">Total:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</form>
								</div>
							</div>
						</div>

						<!-- credit div -->
						
						<div id="debit" class="tab-pane">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#supp"><h4 style="color: black">Paid Amount To Supplier</h4></a></li>
								<li><a data-toggle="tab" href="#emp"><h4 style="color: black">Paid Amount To Employee</h4></a></li>
							</ul>

							<div class="tab-content" style="float: left; margin-top: 12px;">

								<!-------- Paid Amout ot supplier Report ---------->

								<div id="supp" class="tab-pane fade in active">
									<div class="row"></div>
									<form class="form-horizontal" method="post" action=""
										name="fertiBill">

										<div class="row form-group" style="margin-top: 20px">
											<label class="col-md-2 control-label" for="customerName"> Start Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="date" id="fisDateSup"
														placeholder="Start Date" class="form-control input-md"
														type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="village">End Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="endDateSup" placeholder="End Date"
														class="form-control input-md ac_district" type="text">
												</div>
											</div>
										</div>

										<div class="row form-group buttons_margin_top ">
											<div align="center">

												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getPaidAmountToSupplier()" value="Search" />
											</div>
										</div>
										
										<table id="exampleSup" class="display">
											<thead>
												<tr>
													<th>Payment date</th>
													<th>Supplier Bill No</th>
													<th>Account Holder Name</th>
													<th>Total Paid Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="3" style="text-align: right">Total:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</form>
								</div>

								<!-- ---	Paid Amout ot emp Report	---->
						
								<div id="emp" class="tab-pane ">
									<div class="row"></div>
									<form class="form-horizontal" method="post" action="" name="fertiBill">
										<div class="row form-group" style="margin-top: 20px">
											<label class="col-md-2 control-label" for="customerName">Start Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="date" id="fisDateEmp"
														placeholder="Start Date" class="form-control input-md"
														type="text">
												</div>
											</div>

											<label class="col-md-2 control-label" for="village">End Date:<sup>*</sup>
											</label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-map-marker"></i>
													</span> <input type="date" id="endDateEmp" placeholder="End Date"
														class="form-control input-md ac_district" type="text">
												</div>
											</div>
										</div>

										<div class="row form-group buttons_margin_top ">
											<div align="center">

												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getPaidAmountToEmployee()" value="Search" />
											</div>
										</div>
										
										<table id="exampleEmp" class="display">
											<thead>
												<tr>
													<th>Payment Date</th>
													<th>Employee Name</th>
													<th>Payment Reason</th>
													<th>Paind Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="3" style="text-align: right">Total:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- credit tab content div -->
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>
