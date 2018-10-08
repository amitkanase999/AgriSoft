<%@page import="com.Fertilizer.hibernate.CreditLoanBean"%>
<%@page import="com.Fertilizer.dao.CreditLoanDao"%>
<%@page import="com.Fertilizer.hibernate.EmployeeDetailsBean"%>
<%@page import="com.Fertilizer.dao.EmployeeDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.ExpenditureDetailsBean"%>
<%@page import="com.Fertilizer.dao.ExpenditureDetailsDao"%>
<%@page import="com.Fertilizer.bean.CreditCustPaymentDetail"%>
<%@page import="com.Fertilizer.dao.CustomerPaymentDao"%>
<%@page import="com.Fertilizer.dao.SupplierPaymentDao"%>
<%@page import="com.Fertilizer.bean.SupplierPaymentDetail"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
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

<!-- For datatable to pdf,print,excel etc conversion -->

<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script type="text/javascript"	src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript"	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script type="text/javascript"	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>

<link rel="stylesheet"	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet"	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
<script src="/AgriSoft/staticContent/js/cashbankbook.js"></script>
</head>
<<style>
.footer{
  background: #ffa500b3;
}
.fotter1{
background: #dbe4e6;
}
</style>

<div class="row" style="min-height: 300px;">
	<div class="col-md-12">
		<h3>Left Tabs</h3>
		<hr />
		<div class="col-md-2">
			<ul class="nav nav-tabs tabs-left">
				<li class="active"><a href="#home" data-toggle="tab">Supplier
						Payment Reports</a></li>
				<li><a href="#profile" data-toggle="tab">Customer Payment
						Reports</a></li>
				<li><a href="#loan" data-toggle="tab">Loan Payment
						Reports</a></li>
				<li><a href="#messages" data-toggle="tab">Employee Payment
						Reports</a></li>
				<li><a href="#settings" data-toggle="tab">Expenditure
						Payment Reports</a></li>
			</ul>
		</div>
		
		<div class="col-xs-9">
			<!-- Tab panes -->
			<div class="tab-content">

				<!---------- 	supplier Payment reports -------------->

				<div class="tab-pane active" id="home">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Supplier Payment Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#AllSupplier"><h4
									style="color: black">All Supplier's</h4></a></li>
						<li><a data-toggle="tab" href="#supplierNAme"><h4
									style="color: black">Supplier Name Wise</h4></a></li>
						<!-- <li ><a data-toggle="tab" href="#supplierBillWise"><h4 style="color:black">Bill Number Wise</h4></a></li> -->
						<li><a data-toggle="tab" href="#supplierSingleDate"><h4
									style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#supplierBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">
						<!--   ALL -->
						<div id="AllSupplier" class="tab-pane fade in active">
							<div id="report" style="text-align: center">
								<label id="demo"></label>
								<script>
									var date = new Date();
									document.getElementById("demo").innerHTML = date
											.toDateString();
								</script>
							</div>

							<script type="text/javascript">
								$(document)
										.ready(
												function() {
													var table = $("#list")
															.dataTable(
																	{

																		fnRowCallback : function(
																				nRow,
																				aData,
																				iDisplayIndex) {
																			$(
																					"th:first",
																					nRow)
																					.html(
																							iDisplayIndex + 1);
																			return nRow;
																		},

																		"footerCallback" : function(
																				row,
																				data,
																				start,
																				end,
																				display) {
																			var api = this
																					.api(), data;

																			// Remove the formatting to get integer data for summation
																			var intVal = function(
																					i) {
																				return typeof i === 'string' ? i
																						.replace(
																								/[\$,]/g,
																								'') * 1
																						: typeof i === 'number' ? i
																								: 0;
																			};

																			// Total over all pages
																			total = api
																					.column(
																							5)
																					.data()
																					.reduce(
																							function(
																									a,
																									b) {
																								return intVal(a)
																										+ intVal(b);
																							},
																							0);
																			console
																					.log(total);

																			// Update footer
																			$(
																					api
																							.column(
																									5)
																							.footer())
																					.html(
																							'Rs'
																									+ ' '
																									+ total);
																			console
																					.log(total);
																		}

																	});

												});
				</script>

							<%
								SupplierPaymentDao dao = new SupplierPaymentDao();
								List Lis1 = dao.getSupplierPaymentDetailForReport();
							%>
							<div id="demo_jui" style="text-align: center">
								<table id="list" class="display" border="1">
									<thead>
										<tr>
											<th>Supplier Name</th>
											<th>Bill Number</th>
											<th>Payment Mode</th>
											<th>Accountant Name</th>
											<th>Credit Amount</th>
											<th>Debit Amount</th>
											<th>Balance Amount</th>
											<th>Total Amount</th>

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
											<td class="align"><%=pro.getBillNo()%></td>
											<td class="align"><%=pro.getPaymentMode()%></td>
											<td class="align"><%=pro.getAccountantName()%></td>
											<td class="align"><%=pro.getCreditAmount()%></td>
											<td class="align"><%=pro.getDebitAmount()%></td>
											<td class="align"><%=pro.getBalanceAmount()%></td>
											<td class="align"><%=pro.getTotalAmount()%></td>
										</tr>
										<%
											}
										%>

									</tbody>
								</table>
							</div>
						</div>

						<!-- Supplier Name Wise -->
						<div id="supplierNAme" class="tab-pane ">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for="supplier">Supplier
											Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
													SupplierDetailsDao sdd88 = new SupplierDetailsDao();
													List sList88 = sdd88.getAllSupplier();
												%>

												<input list="sup_drop7" id="supplier7" class="form-control">
												<datalist id="sup_drop7">

													<%
														for (int i = 0; i < sList88.size(); i++) {
															SupplierDetailsBean sup88 = (SupplierDetailsBean) sList88
																	.get(i);
													%>

													<option data-value="<%=sup88.getSupId()%>"
														value="<%=sup88.getDealerName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>

										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getSupNameWiseReport()" value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="supplierNameWiseTable" class="display">
											<thead>
												<tr>
													<th>Supplier Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Accountant Name</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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
						
						<!-- Bill number wise -->
						
						<div id="supplierBillWise" class="tab-pane ">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for="supplier">Supplier
											Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
													SupplierDetailsDao sdd = new SupplierDetailsDao();
													List sList = sdd.getAllSupplier();
												%>

												<input list="sup_drop" id="supplier"
													onchange="bill.getAllBills();" class="form-control">
												<datalist id="sup_drop">

													<%
														for (int i = 0; i < sList.size(); i++) {
															SupplierDetailsBean sup = (SupplierDetailsBean) sList.get(i);
													%>

													<option data-value="<%=sup.getSupId()%>" value="<%=sup.getDealerName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>

										<label class="col-md-2 control-label" for="bill_no"> Bill No </label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> No </span> <select
													class="form-control input-md" id='billNo' name="billNo">
												</select>
											</div>
										</div>
									</div>
								
									<div class="row form-group">
										<div class="col-md-3 col-md-offset-4">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getBillWiseReport()" value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="supplierBillWiseData" class="display">
											<thead>
												<tr>
													<th>Supplier Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Accountant Name</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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

						<!--    for single date -->
					
						<div id="supplierSingleDate" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
						
							<form class="form-horizontal" method="post" action=""
								name="supReport">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for=""> Enter
											Date:<sup>*</sup>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate11" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="supplierReportForSingleDate()" value="Search" />
											</div>
										</div>
									</div>
							
									<div class="table-responsive">
										<table id="supplierSingleDatetable" class="display">
											<thead>
												<tr>
													<th>Supplier Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Accountant Name</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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
					
						<div id="supplierBetweenTwoDate" class="tab-pane fade">

							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>

							<form class="form-horizontal" method="post" action=""
								name="supReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label" for="startDate">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDate1" placeholder="Start Date"
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
												</span> <input type="date" id="endDate1" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group buttons_margin_top ">
										<div align="center">

											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="getSupplierDetailsBetweenTwoDates()" value="Search" />

										</div>
									</div>

									<table id="supplierBetweenTwoDatestable" class="display">
										<thead>
											<tr>
												<th>Supplier Name</th>
												<th>Bill Number</th>
												<th>Payment Mode</th>
												<th>Accountant Name</th>
												<th>Payment Date</th>
												<th>Credit Amount</th>
												<th>Debit Amount</th>
												<th>Total Amount</th>
												<th>Balance Amount</th>

											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="5" style="text-align: right">Total:</th>
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

				<!---------   Customer Payment Reports--------->

				<div class="tab-pane" id="profile">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Credit Customer Payment
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab"
							href="#customerNameWise"><h4 style="color: black">Customer
									Name wise</h4></a></li>
						<li><a data-toggle="tab" href="#customerBillWise"><h4
									style="color: black">Bill Number Wise</h4></a></li>
						<li><a data-toggle="tab" href="#customerSingleDate"><h4
									style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#customerBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>

					</ul>

					<div class="tab-content" style="float: left">

						<!-- 	Customer Name wise -->

						<div id="customerNameWise" class="tab-pane fade in active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-3 control-label" for="customerName">Customer
											Name</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span>

												<%
													CustomerDetailsDao custdao1 = new CustomerDetailsDao();
													List custList2 = custdao1.getAllCustomer();
												%>
												<input list="cust_drop5" id="creditCustomer5"
													class="form-control">
												<datalist id="cust_drop5">

													<%
														for (int i = 0; i < custList2.size(); i++) {
															CustomerDetailsBean cust2 = (CustomerDetailsBean) custList2
																	.get(i);
													%>

													<option data-value="<%=cust2.getCustId()%>"><%=cust2.getFirstName()%>
														<%=cust2.getLastName()%>
													</option>
													<%
														}
													%>
												</datalist>
											</div>
										</div>

										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCreditCustomerReportNameWise()" value="Search" />
											</div>
										</div>
									</div>
						
									<div class="table-responsive">
										<table id="customerNameWiseData2" class="display">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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


						<!-- Customer Bill number wise -->
					
						<div id="customerBillWise" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label" for="customerName">Customer
											Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span>

												<%
													CustomerDetailsDao cdd = new CustomerDetailsDao();
													List cList = cdd.getAllCustomer();
												%>
												<input list="cust_drop" id="creditCustomer"
													class="form-control">
												<datalist id="cust_drop">

													<%
														for (int i = 0; i < cList.size(); i++) {
															CustomerDetailsBean cust = (CustomerDetailsBean) cList.get(i);
													%>

													<option data-value="<%=cust.getCustId()%>"><%=cust.getFirstName()%>
														<%=cust.getLastName()%>
													</option>
													<%
														}
													%>
												</datalist>
											</div>
										</div>
										<label class="col-md-2 control-label" for="fk_cat_id0">
											<%
												if (abc.equals("marathi")) {
											%>
											<%=PropertiesHelper.marathiProperties.getProperty("productCat")%> 
											<%
											 	}
											 %> 
											 <%
											 	if (abc.equals("english")) {
											 %>Product Category<%
											 	}
											 %><sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
												<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
												<%
													CategoryDetailsDao cdd0 = new CategoryDetailsDao();
													List cList0 = cdd0.getAllMainCat();
												%>
												<input list="cat_drop0" id="fk_cat_id0" class="form-control"
													onchange="getBillByCustomerForReport()">
												<datalist id="cat_drop0">
													<%
														for (int i = 0; i < cList0.size(); i++) {
															CategoryDetailsBean cat0 = (CategoryDetailsBean) cList0.get(i);
													%>

													<option data-value="<%=cat0.getCatId()%>"
														value="<%=cat0.getCategoryName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>
									</div>
								
									<div class="row form-group">
										<label class="col-md-2 control-label" for="bill_no">
											Bill No </label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> No </span> <select
													class="form-control input-md" id='billNo1' name="billNo">
												</select>
											</div>
										</div>

										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getBillWiseCreditReport()" value="Search" />
											</div>
										</div>
									</div>
							
									<div class="table-responsive">
										<table id="customerNameWiseData" class="display">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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


						<!--    for single date -->
						
						<div id="customerSingleDate" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="custReport">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-3 control-label" for=""> Enter
											Date:<sup>*</sup>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate1" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="creditCustReportForSingleDate()" value="Search" />
											</div>
										</div>
									</div>
							
									<div class="table-responsive">
										<table id="customerSingleDatetable" class="display">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Bill Number</th>
													<th>Payment Mode</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>Debit Amount</th>
													<th>Total Amount</th>
													<th>Balance Amount</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="5" style="text-align: right">Total:</th>
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
					
						<div id="customerBetweenTwoDate" class="tab-pane fade">

							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="custReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label" for="startDate">
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

										<label class="col-md-2 control-label" for="village">End
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

									<div class="row form-group buttons_margin_top ">
										<div align="center">

											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="getCreditCustomerDetailsBetweenTwoDates()"
												value="Search" />
										</div>
									</div>

									<table id="customerBetweenTwoDates" class="display">
										<thead>
											<tr>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Bill Number</th>
												<th>Payment Mode</th>
												<th>Payment Date</th>
												<th>Credit Amount</th>
												<th>Debit Amount</th>
												<th>Total Amount</th>
												<th>Balance Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="5" style="text-align: right">Total:</th>
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
				
				
				
				<!----- 	Loan Payment reports ------>
				
					<div class="tab-pane" id="loan">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Loan Payment Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs">
						<!-- <li class="active"><a data-toggle="tab" href="#Allloan"><h4 style="color: black">All Loan Payment</h4></a></li>
						<li><a data-toggle="tab" href="#loanNameWise"><h4 style="color: black">Customer Name Wise</h4></a></li> -->
						<!-- <li class="active"><a data-toggle="tab" href="#productandloannamewise"><h4 style="color: black">Product & Loan, Name Wise</h4></a></li>
						<li><a data-toggle="tab" href="#customernamewisepaymentreport"><h4 style="color: black">Customer Name Wise Loan Payment Report</h4></a></li> -->
						<li class="active"><a data-toggle="tab" href="#customernamewiseloanreport"><h4 style="color: black">Customer Name Wise Loan Report</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">
						<!--   ALL -->
					<%-- 	<div id="Allloan" >
							<div id="report" style="text-align: center">
								<label id="demo"></label>
								<script>
									var date = new Date();
									document.getElementById("demo").innerHTML = date
											.toDateString();
								</script>
							</div>

							<script type="text/javascript">
								$(document)
										.ready(
												function() {
													var table = $("#list")
															.dataTable(
																	{

																		fnRowCallback : function(
																				nRow,
																				aData,
																				iDisplayIndex) {
																			$(
																					"th:first",
																					nRow)
																					.html(
																							iDisplayIndex + 1);
																			return nRow;
																		},

																		"footerCallback" : function(
																				row,
																				data,
																				start,
																				end,
																				display) {
																			var api = this
																					.api(), data;

																			// Remove the formatting to get integer data for summation
																			var intVal = function(
																					i) {
																				return typeof i === 'string' ? i
																						.replace(
																								/[\$,]/g,
																								'') * 1
																						: typeof i === 'number' ? i
																								: 0;
																			};

																			// Total over all pages
																			total = api
																					.column(
																							5)
																					.data()
																					.reduce(
																							function(
																									a,
																									b) {
																								return intVal(a)
																										+ intVal(b);
																							},
																							0);
																			console
																					.log(total);

																			// Update footer
																			$(
																					api
																							.column(
																									5)
																							.footer())
																					.html(
																							'Rs'
																									+ ' '
																									+ total);
																			console
																					.log(total);
																		}

																	});

												});
				</script>

							<%
								CreditLoanDao dao2 = new CreditLoanDao();
								List Lis12 = dao2.getAllLoanPaymentDetailForReport();
							%>
							<div id="demo_jui" style="text-align: center">
								<table id="list" class="display" border="1">
									<thead>
										<tr>
											<th>Loan No</th>
											<th>Customer Name</th>
											<th>Payment Type</th>
											<th>Payment Mode</th>
											<th>Loan Credited Date</th>
											<th>interest Rate</th>
											<th>Loan Amount</th>
											<th>Balance Amount</th>
											<th>Paid Amount</th>
											<th>Paid Date</th>

										</tr>
									</thead>
									<tfoot>
										<tr>
											<th></th>
										</tr>
									</tfoot>

									<tbody>
										<%
											for (int i = 0; i < Lis12.size(); i++) {
												CreditLoanBean pro = (CreditLoanBean) Lis12.get(i);
										%>
										<tr>
											<td class="align"><%=pro.getLoanno()%></td>
											<td class="align"><%=pro.getCust_name()%></td>
											<td class="align"><%=pro.getType()%></td>
											<td class="align"><%=pro.getPayment_mode()%></td>
											<td class="align"><%=pro.getLoanDate()%></td>
											<td class="align"><%=pro.getInterest_rate()%></td>
											<td class="align"><%=pro.getLoan_amt()%></td>
											<td class="align"><%=pro.getBalance_status()%></td>
											<td class="align"><%=pro.getPaid_amt()%></td>
											<td class="align"><%=pro.getPaidDate()%></td>
										</tr>
										<%
											}
										%>

									</tbody>
								</table>
							</div>
						</div> --%>

						<!-- Supplier Name Wise -->
						<div id="loanNameWise" class="tab-pane ">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for="supplier">Customer
											Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
												CustomerDetailsDao cdd1 = new CustomerDetailsDao();
												List cList1 = cdd1.getAllCustomer();
												%>

												<input list="cust_drop6" id="custname" class="form-control">
										<datalist id="cust_drop6">

													<%
														for (int i = 0; i < cList1.size(); i++) {
															CustomerDetailsBean cust2 = (CustomerDetailsBean) cList1
																	.get(i);
													%>

													<option data-value="<%=cust2.getCustId()%>"><%=cust2.getFirstName()%>
												
													</option>
													<%
														}
													%>
												</datalist>

											</div>
										</div>

										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCustNameWiseReport()" value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="custNameWiseTable1" class="display">
											<thead>
												<tr>
											<th>Loan No</th>
											<th>Customer Name</th>
											<th>Payment Type</th>
											<th>Payment Mode</th>
											<th>Loan Credited Date</th>
											<th>interest Rate</th>
											<th>Loan Amount</th>
											<th>Balance Amount</th>
											<th>Paid Amount</th>
											<th>Paid Date</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="6" style="text-align: right">Total:</th>
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
						
		<!-- Product And Loan Name Wise Report -->
<%-- 		<div id="productandloannamewise" class="tab-pane fade in active" >
			<div class="row"></div>
			<form class="form-horizontal" method="post" action="" name="gst">
				<fieldset>
				<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-3 control-label" for="supplier">Customer Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
												CustomerDetailsDao cdd2 = new CustomerDetailsDao();
												List cList2 = cdd1.getAllCustomer();
												%>

												<input list="cust_drop7" id="custname1" class="form-control">
										<datalist id="cust_drop7">

													<%
														for (int i = 0; i < cList2.size(); i++) {
															CustomerDetailsBean cust3 = (CustomerDetailsBean) cList2
																	.get(i);
													%>

													<option data-value="<%=cust3.getCustId()%>"><%=cust3.getFirstName()%> <%=cust3.getLastName()%></option>
													<%
														}
													%>
												</datalist>

											</div>
										</div>
				
					
						<div class="col-md-3">
							<div class="input-group">
								<input type="button" style="align: center" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="LoanReport();getCreditReport();loanGrandTotal()"
									value="Search" />
							</div>
						</div>
					</div>
				
					<div class="table-responsive">
						<div class="col-md-6">
							<h3 align="center" class="form-name style_heading">Credit Product</h3>
							<table
								class="table table-bordered table-striped table-condensed cf"
								id="CreditProductReport" class="display table table-boardered"
								style="border: 2px solid black; border-collapse: collapse;">
								<thead>
									<tr>
										<th>Customer Name</th>
										<th>Fertilizer Balance</th>
										<th>Seed Balance</th>
										<th>Pesticide Balance</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th colspan="1" style="text-align: right">Total Rs:</th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>

						<div class="col-md-offset-1  col-md-5">
							<h3 align="center" class="form-name style_heading">Loan Details</h3>
							<table
								class="table table-bordered table-striped table-condensed cf"
								id="LoanReportDetails" class="display table table-boardered"
								 style="border: 2px solid black; border-collapse: collapse;">
								<thead>
									<tr>
										<th>Loan No</th>
										<th>Customer Name</th>
										<th>Loan Date</th>
										<th>Loan Amount</th>
										<th>Interest Rate</th>
										<th>Balance status</th>
										<th>Paid Date</th>
										<th>Last Paid Amount</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th colspan="3" style="text-align: right">Total Rs:</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</fieldset>
				
					<br>
						
							<label class="col-md-1 control-label" for="loanreport">Product Total</label>
								<div class="col-md-3 ">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="glyphicon glyphicon-map-marker"></i>
										</span> <input id="producttotal" name="producttotal " placeholder="Product Total"
										class="form-control input-md" type="text">
									</div>
								</div>
							<label class="col-md-1 control-label" for="loanreport">Loan Total</label>
								<div class="col-md-3 ">
									<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-map-marker"></i>
										</span> <input id="loantotal" name="loantotal " placeholder="Loan Total"
										class="form-control input-md" type="text">
									</div>
							</div>
							<label class="col-md-1 control-label" for="loanreport">Grand Total</label>
								<div class="col-md-3 ">
									<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-map-marker"></i>
										</span> <input id="grandtotal" name="grandtotal " placeholder="Grand Total"
										class="form-control input-md" type="text">
									</div>
							</div>
			</form>
		</div> --%>
					
					<!-- Customer Name Wise Loan Payment Report-->
			<%-- 			<div id="customernamewisepaymentreport" class="tab-pane ">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="supReportBill">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for="supplier">Customer Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
								CustomerDetailsDao cdd3 = new CustomerDetailsDao();
           						List cList3 =cdd3.getAllCustomer();
							
							%>

												<input list="cust_drop12" id="customername" class="form-control">
										<datalist id="cust_drop12">

							<%
					           for(int i=0;i<cList3.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList3.get(i);
							%>

										<option data-value="<%=cust.getCustId()%>" ><%=cust.getFirstName()%> <%=cust.getLastName() %></option>
							<%
				      			}
				    		%>
									</datalist>

											</div>
										</div>

										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCustNameWiseLoanPaymentReport()" value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="CustNameWiseLoanPaymentReportTable" class="display" border="2">
											<thead>
												<tr>
											<th>paid Date</th>
											<th>Description</th>
											<th>Amount</th>
									
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th></th>
													<th colspan="1" style="text-align: right">Total:</th>
													<th ></th>
												</tr>
											</tfoot>
										</table>
									</div>
				
						</fieldset>		
						</form>
					</div> --%>
					
				

				<!-- ****** Customer Name Wise Loan Report ******* -->
						<div id="customernamewiseloanreport" class="tab-pane-active ">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="loanpaymentform">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for="customername">Customer Name</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
												<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

												<%
								CustomerDetailsDao cdd4 = new CustomerDetailsDao();
           						List cList4 =cdd4.getAllCustomer();
							
							%>

												<input list="cust_drop12" id="CustName1" class="form-control">
										<datalist id="cust_drop12">

							<%
					           for(int i=0;i<cList4.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList4.get(i);
							%>

										<option data-value="<%=cust.getCustId()%>" ><%=cust.getFirstName()%> <%=cust.getLastName() %></option>
							<%
				      			}
				    		%>
									</datalist>

											</div>
										</div>

										
									<label class="col-md-2 control-label" for=""> Closer Date:<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="cloaserdate" placeholder="cloaserdate"
													class="form-control input-md" type="text">
											</div>
										</div>
										
										
										
								</div>
								
								<div class="row form-group">
									<label class="col-md-3 control-label" for=""> Interest Rate<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="text" id="interestrate" placeholder="Interest Rate %"
													class="form-control input-md" type="text">
											</div>
										</div>
								</div>
										
								 <div class="row form-group">
									<label class="col-md-4 control-label" for=""> </label>
										<div class="col-md-3 ">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="getCustNameWiseLoanReportValidation()" value="Search" />
											</div>
										</div>
								</div>
								
									<div class="table-responsive">
										<table id="CustNameWiseLoanReportTable" class="display" border="2">
											<thead>
												<tr class="fotter1">
											<th>Customer Name</th>
											<th>Loan No</th>
											<th>Bill Date</th>
											<th>Loan Amount</th>
											<th>Interest Rate</th>
											<th>Days</th>
											<th>Interest Amount</th>
											<th>Interest amt Per Day</th>
											<th>Paid Date</th>
											<th>Paid Amount</th>
											<th>Paid Amount + interest</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													
													<th class="fotter1"></th>
													<th class="fotter1"></th>
													<th colspan="1" style="text-align: right">Total:</th>
													<th class="footer" ></th>
													<th class="fotter1"></th>
													<th class="fotter1"></th>
													<th class="footer"></th>
													<th class="fotter1"></th>
													<th class="fotter1"></th>
													<th class="footer"></th>
													<th class="footer"></th>
												</tr>
											</tfoot>
										</table>
									</div>
									<br>
									<div class="row form-group">
										<label class="col-md-2 control-label" for=""> Credit Amount<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="text" id="creditamount" placeholder=""
														class="form-control input-md" type="text" readonly="readonly">
												</div>
											</div>
											
											<label class="col-md-2 control-label" for=""> Debit Amount<sup>*</sup></label>
												<div class="col-md-3">
													<div class="input-group">
														<span class="input-group-addon"> <i
															class="glyphicon glyphicon-user"></i>
														</span> <input type="text" id="debitamount" placeholder=""
															class="form-control input-md" type="text" readonly="readonly">
													</div>
												</div>
											
									</div>
									
									
									
									<div class="row form-group">
										<label class="col-md-2 control-label" for=""> Remaining Amount<sup>*</sup></label>
											<div class="col-md-3">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i>
													</span> <input type="text" id="remamt" placeholder=""
														class="form-control input-md" type="text" readonly="readonly">
												</div>
											</div>
									</div>
					
						</fieldset>		
						</form>
						</div>
					</div>
				</div>



				<!----- 	Employee Payment reports ------>
				
				<div class="tab-pane" id="messages">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Employee Payment Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab"
							href="#employeeSingleDate"><h4 style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#empBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>
						<li><a data-toggle="tab" href="#empBetweenTwoDateAsName"><h4
									style="color: black">Employee Name Wise</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->
						<div id="employeeSingleDate" class="tab-pane fade in active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="empReport">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for=""> Enter
											Date:<sup>*</sup>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate2" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="employeePaymentReportForSingleDate()"
													value="Search" />
											</div>
										</div>
									</div>
								
									<div class="table-responsive">
										<table id="employeeSingleDatetable" class="display">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Payment Date</th>
													<th>Payment Mode</th>
													<th>Accountant Name</th>
													<th>Reason</th>
													<th>Credit Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="6" style="text-align: right">Total:</th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</fieldset>
							</form>
						</div>

						<!-------- 	Between Two Days	 -------->
					
						<div id="empBetweenTwoDate" class="tab-pane">

							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="empReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label" for="startDate">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDate2" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<label class="col-md-2 control-label" for="village">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDate2" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group buttons_margin_top ">
										<div align="center">

											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="getEmpPaymentDetailsBetTwoDays()" value="Search" />

										</div>
									</div>

									<table id="empBetweenTwoDates" class="display">
										<thead>
											<tr>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Payment Date</th>
												<th>Payment Mode</th>
												<th>Accountant Name</th>
												<th>Reason</th>
												<th>Credit Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="6" style="text-align: right">Total:</th>
												<th></th>


											</tr>
										</tfoot>
									</table>
								</fieldset>
							</form>
						</div>

						<!---------- Employee Payment Reports As Per Name------->
					
						<div id="empBetweenTwoDateAsName" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="expReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label"
											for="firstDateForEmpName"> Start Date:<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="firstDateForEmpName"
													placeholder="Start Date" class="form-control input-md"
													type="text">
											</div>
										</div>

										<label class="col-md-2 control-label" for="endDateForEmpName">End
											Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDateForEmpName"
													placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<label class="col-md-2 control-label" for="employeename">Employee
											Name<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span>

												<%
													EmployeeDetailsDao eedd = new EmployeeDetailsDao();
													List mList = eedd.getAllMainEmployee();
												%>
												<input list="emp_drop" id="employeeId" class="form-control">
												<datalist id="emp_drop">

													<%
														for (int i = 0; i < mList.size(); i++) {
															EmployeeDetailsBean detailsBean = (EmployeeDetailsBean) mList
																	.get(i);
													%>

													<option data-value="<%=detailsBean.getEmpId()%>"><%=detailsBean.getFirstName()%>
														<%=detailsBean.getLastName()%></option>
													<%
														}
													%>
												</datalist>
											</div>
										</div>
										<div class="col-md-offset-1">
											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="empNameWiseBetweenTwoDate()" value="Search" />
										</div>
									</div>
									<table id="empBetweenTwoDatesPerName" class="display">
										<thead>
											<tr>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Payment Date</th>
												<th>Payment Mode</th>
												<th>Accountant Name</th>
												<th>Reason</th>
												<th>Credit Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="6" style="text-align: right">Total:</th>
												<th></th>
											</tr>
										</tfoot>
									</table>

								</fieldset>
							</form>
						</div>
					</div>
				</div>
				
				<!---------- Expenditure Payment Reports ------->

				<div class="tab-pane" id="settings">
					<div class="row">
						<div align="center">
							<h2 class="form-name style_heading">Expenditure Payment
								Reports</h2>
						</div>

						<div class="row">
							<div class="col-sm-offset-1 col-md-10">
								<hr style="border-top-color: #c1b1b1;">
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab"
							href="#expenseSingleDate"><h4 style="color: black">Datewise</h4></a></li>
						<li><a data-toggle="tab" href="#expenseBetweenTwoDate"><h4
									style="color: black">Range</h4></a></li>
						<li><a data-toggle="tab"
							href="#expenseBetweenTwoDateAsPerExpense"><h4
									style="color: black">Expenditure Name Wise</h4></a></li>
					</ul>

					<div class="tab-content" style="float: left">

						<!--    for single date -->

						<div id="expenseSingleDate" class="tab-pane fade in active">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="expnsReport">
								<fieldset>

									<div class="row form-group">
										<label class="col-md-3 control-label" for=""> Enter
											Date:<sup>*</sup>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate4" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>

										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="expensePaymentReportForSingleDate()"
													value="Search" />
											</div>
										</div>
									</div>
					
									<div class="table-responsive">
										<table id="expenseSingleDatetable" class="display">
											<thead>
												<tr>
													<th>Expense Name</th>
													<th>Service Provider</th>
													<th>Accountant Name</th>
													<th>Payment Date</th>
													<th>Credit Amount</th>
													<th>debit Amount</th>
													<th>Total Amount</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th colspan="4" style="text-align: right">Total:</th>
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

						<!------- between Two dates ------->

						<div id="expenseBetweenTwoDate" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
							<form class="form-horizontal" method="post" action=""
								name="expReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label" for="startDate">
											Start Date:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fisDate4" placeholder="Start Date"
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
												</span> <input type="date" id="endDate4" placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group buttons_margin_top ">
										<div align="center">

											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="getExpensePaymentDetailsBetTwoDays()"
												value="Search" />
										</div>
									</div>

									<table id="expenseBetweenTwoDates" class="display">
										<thead>
											<tr>
												<th>Expense Name</th>
												<th>Service Provider</th>
												<th>Accountant Name</th>
												<th>Payment Date</th>
												<th>Credit Amount</th>
												<th>debit Amount</th>
												<th>Total Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="4" style="text-align: right">Total:</th>
												<th></th>
												<th></th>
												<th></th>
											</tr>
										</tfoot>
									</table>
								</fieldset>
							</form>
						</div>

						<!-------Expenditure Name between Two dates ------->

						<div id="expenseBetweenTwoDateAsPerExpense" class="tab-pane">
							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>

							<form class="form-horizontal" method="post" action=""
								name="expReport1">
								<fieldset>
									<div class="row form-group">
										<label class="col-md-2 control-label"
											for="firstDateForExpenseName"> Start Date:<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="firstDateForExpenseName"
													placeholder="Start Date" class="form-control input-md"
													type="text">
											</div>
										</div>

										<label class="col-md-2 control-label"
											for="endDateForExpenseName">End Date:<sup>*</sup></label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-map-marker"></i>
												</span> <input type="date" id="endDateForExpenseName"
													placeholder="End Date"
													class="form-control input-md ac_district" type="text">
											</div>
										</div>
									</div>

									<div class="row form-group">
										<label class="col-md-2 control-label" for="expenditureName">Expenditure
											Name<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="	glyphicon glyphicon-hand-right"></i>
												</span>

												<!-- Following code is to get expense name from "expenditure_details" table of "fertilizer" DB -->
												<!-- getAllExpenseName() is implemented in  SupplierDetailsDao with return type List-->

												<%
													ExpenditureDetailsDao exdd = new ExpenditureDetailsDao();
													List exList = exdd.getAllExpenseName();
												%>

												<input list="exp_drop" id="expenseName" class="form-control">
												<datalist id="exp_drop">

													<%
														for (int i = 0; i < exList.size(); i++) {
															ExpenditureDetailsBean expbean = (ExpenditureDetailsBean) exList
																	.get(i);
													%>

													<option data-value="<%=expbean.getPkExpenseDetailsId()%>"
														value="<%=expbean.getExpenseName()%>">
														<%
															}
														%>
													
												</datalist>
											</div>
										</div>
							
										<div class="col-md-offset-1">
											<input type="button" id="btn" name="save"
												class="btn btn-lg btn-success btn-md button_hw button_margin_right"
												onclick="expenseReportAsPerExpenditureName()" value="Search" />
										</div>
									</div>

									<table id="expenseBetweenTwoDatesAsPerSelectedName"
										class="display">
										<thead>
											<tr>
												<th>Expense Name</th>
												<th>Service Provider</th>
												<th>Accountant Name</th>
												<th>Payment Date</th>
												<th>Credit Amount</th>
												<th>debit Amount</th>
												<th>Total Amount</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th colspan="4" style="text-align: right">Total:</th>
												<th></th>
												<th></th>
												<th></th>
											</tr>
										</tfoot>
									</table>
								</fieldset>
							</form>
							
							
													<div class="row form-group">
										<label class="col-md-2 control-label" for=""> Product Total:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="date" id="fDate4" placeholder="Start Date"
													class="form-control input-md" type="text">
											</div>
										</div>
								</div>
						</div>
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="commons/newFooter.jsp"%>
