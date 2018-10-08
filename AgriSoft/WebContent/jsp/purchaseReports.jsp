<%@page import="com.Fertilizer.bean.SupplierPaymentDetail"%>
<%@page import="com.Fertilizer.dao.SupplierPaymentDao"%>
<%@page import="com.Fertilizer.bean.SaleReports"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>		
<%@page import="com.Fertilizer.hibernate.GoodsReceiveBean"%>
<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>

<head>
<!-- For datatable to pdf,print,excel etc conversion -->
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
	<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
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


</head>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Purchase Reports</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home"><h4
					style="color: black">Datewise</h4></a></li>
		<li><a data-toggle="tab" href="#twoDates"><h4
					style="color: black">Range</h4></a></li>
		<li><a data-toggle="tab" href="#pro"><h4 style="color: black">Product
					Detail Wise</h4></a></li>
		<li><a data-toggle="tab" href="#sup"><h4 style="color: black">Supplier
					Name Wise</h4></a></li>
		<li><a data-toggle="tab" href="#cat"><h4 style="color: black">Category
					Wise</h4></a></li>
		<li><a data-toggle="tab" href="#gst"><h4 style="color: black">GST
					Wise</h4></a></li>
		<li><a data-toggle="tab" href="#purchaseReturn"><h4
					style="color: black">Purchase Return</h4></a></li>
		<li><a data-toggle="tab" href="#paymentDue"><h4
					style="color: black">Payment Due Date</h4></a></li>	
		<li><a data-toggle="tab" href="#billnowise"><h4
					style="color: black">Bill No Wise</h4></a></li>
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
									<th>Bill Number</th>
									<th>Purchase Date</th>
									<th>Product Name</th>
									<th>Company Name</th>
									<th>DC No</th>
									<th>Batch No</th>
									<th>Barcode No</th>
									<th>Buy Price</th>
									<th>Sale Price</th>
									<th>MRP</th>
									<th>Packing</th>
									<th>Quantity</th>
									<th>Total Amount</th>
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

		<!-- ---	Between Two Dates	---- -->
		
		<div id="twoDates" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action=""
				name="fertiBill">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-2 control-label" for=""> Start
							Purchase Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="fisDate2" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>

						<label class="col-md-2 control-label" for="">End Purchase
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
								onclick="purchaseReportBetweenTwoDate()" value="Search" />

						</div>
					</div>
					<table id="purchase2" class="display">
						<thead>
							<tr>
								<th>Bill Number</th>
								<th>Purchase Date</th>
								<th>Product Name</th>
								<th>Company Name</th>
								<th>DC No</th>
								<th>Batch No</th>
								<th>Barcode No</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Packing</th>
								<th>Quantity</th>
								<th>Total Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th colspan="12" style="text-align: right">Total:</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>

		<!------  	Product wise ------->
	
		<div id="pro" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action=""
				name="fertiBill">
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
							   CategoryDetailsDao cdd9 = new CategoryDetailsDao();
           						List cList9 =cdd9.getAllMainCat();
							
							%>
								<input list="cat_drop" id="fk_cat_id" class="form-control"
									onchange="fetchproductname()">
								<datalist id="cat_drop">
									<%
					           for(int i=0;i<cList9.size();i++){
					        	   CategoryDetailsBean cat9=(CategoryDetailsBean)cList9.get(i);
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
								</span> 
								
								<input list="Product_list_drop" class="form-control" id='proName' name="proName">
								<datalist id="Product_list_drop"></datalist>
								</select>
								
								<!-- <select class="form-control" id='proName' name="proName">
								</select> -->
							</div>
						</div>
					</div>
					
					<div class="row form-group">
						<div class="col-md-3 col-md-offset-4">
							<div class="input-group">
								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="purchaseReportAsPerProductName()" value="Search" />
							</div>
						</div>
					</div>
					
					<table id="purchaseAsPerProduct" class="display">
						<thead>
							<tr>
								<th>Bill Number</th>
								<th>Product Name</th>
								<th>Purchase Date</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>Packing</th>
								<th>Quantity</th>
								<!-- <th>Extra Expenses</th> -->
								<th>Total Amount</th>
							</tr>
						<tfoot>
							<tr>
								<th colspan="7" style="text-align: right">Total:</th>
								<th></th>
							</tr>
						</tfoot>
						</thead>
					</table>
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
										SupplierDetailsDao sdd = new SupplierDetailsDao();
		           						List sList =sdd.getAllSupplier();
									
									%>

								<input list="sup_drop" id="fkSupplierId" class="form-control">
								<datalist id="sup_drop">

									<%
							           for(int i=0;i<sList.size();i++){
							        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
									%>

									<option data-value="<%=sup.getSupId()%>"
										value="<%=sup.getDealerName() %>">
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
								<th>Supplier</th>
								<th>Bill Number</th>
								<th>Purchase Date</th>
								<th>Product Name</th>
								<th>Company Name</th>
								<th>DC No</th>
								<th>Batch No</th>
								<th>Barcode No</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Packing</th>
								<th>Quantity</th>
								<th>Total Amount</th>
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

		<!-- 		Category Wise -->

		<div id="cat" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action=""
				name="fertiBill">
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
			           						List cList =cdd.getAllMainCat();
										
										%>
								<input list="cat_drop6" id="fk_cat_id6" class="form-control">
								<datalist id="cat_drop6">
									<%
								           for(int i=0;i<cList.size();i++){
								        	   CategoryDetailsBean cat=(CategoryDetailsBean)cList.get(i);
										%>

									<option data-value="<%=cat.getCatId()%>"
										value="<%=cat.getCategoryName()%>">
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
									onclick="purchaseReportAsPerCat()" value="Search" />
							</div>
						</div>
					</div>
				
					<table id="purchaseCatWise" class="display">
						<thead>
							<tr>
								<th>Bill Number</th>
								<th>Purchase Date</th>
								<th>Product Name</th>
								<th>Company Name</th>
								<th>DC No</th>
								<th>Batch No</th>
								<th>Barcode No</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Packing</th>
								<th>Quantity</th>
								<th>Total Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th colspan="12" style="text-align: right">Total:</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>

		<!-- GST wise -->
		
		<div id="gst" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action="" name="gstRepo">
				<fieldset>
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

					<div class="row form-group buttons_margin_top ">
						<div align="center">

							<input type="button" id="gstBtn" name="save"
								class="btn btn-lg btn-success btn-md button_hw button_margin_right"
								onclick="purchaseReportAsPerGST()" value="Search" />
						</div>
					</div>

					<div class="table-responsive">
						<table id="gstPurchaseReportTable" class="table table-boardered"
							style="border: 2px solid black; border-collapse: collapse;">
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Date</th>
									<th>Name Of Supplier</th>
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
					<!-- </div> -->
				</fieldset>
			</form>
		</div>

		<!-- 	Purchase Return  -->
		
		<div id="purchaseReturn" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action=""
				name="fertiBill">
				<fieldset>
					<div class="row form-group">
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
										SupplierDetailsDao supDao = new SupplierDetailsDao();
		           						List suplierList =supDao.getAllSupplier();
									
									%>

								<input list="sup_drop_Purchase_return"
									id="fkSupplierId_Purchase_Return" class="form-control">
								<datalist id="sup_drop_Purchase_return">

									<%
							           for(int i=0;i<suplierList.size();i++){
							        	   SupplierDetailsBean supBean =(SupplierDetailsBean)suplierList.get(i);
									%>

									<option data-value="<%=supBean.getSupId()%>"
										value="<%=supBean.getDealerName() %>">
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
									onclick="supplierWisePurchaseReturnReport()" value="Search" />
							</div>
						</div>
					</div>
					
					<table id="purchaseReturnTable" class="display">
						<thead>
							<tr>
								<th>Supplier</th>
								<th>Bill Number</th>
								<th>Purchase Date</th>
								<th>Product Name</th>
								<th>Company Name</th>
								<th>DC No</th>
								<th>Batch No</th>
								<th>Barcode No</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Packing</th>
								<th>Quantity</th>
								<th>Return Quantity</th>
								<th>Quantity After Return</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>


		<!-- 		Bill No  Wise  -->
		
		<div id="billnowise" class="tab-pane ">
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
										SupplierDetailsDao sdd1 = new SupplierDetailsDao();
		           						List sList1 =sdd1.getAllSupplier();
									
									%>

								<input list="sup_drop_list" id="supplierid" class="form-control" onchange="fetchbillno()">
								<datalist id="sup_drop_list">

									<%
							           for(int i=0;i<sList.size();i++){
							        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
									%>

									<option data-value="<%=sup.getSupId()%>"
										value="<%=sup.getDealerName() %>">
										<%
						      			}
						    		%>
									
								</datalist>
							</div>
						</div>
					
					
					
					
					
						<label class="col-md-2 control-label" for="supplier">
							Bill No<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span>


								<%-- <%
								
										GoodsReceiveDao dao1=new GoodsReceiveDao();
		           						List billList =dao1.getAllPurchaseBillNo();
									
									%>
 --%>
								<input list="bill_drop" id="billNumber" class="form-control">
								 <datalist id="bill_drop"></datalist>
								<%--
									<%
							           for(int i=0;i<billList.size();i++){
							        	   GoodsReceiveBean bean =(GoodsReceiveBean)billList.get(i);
									%>

									<option data-value="<%=bean.getPkGoodsReceiveId()%>"
										value="<%=bean.getBillNum() %>">
										<%
						      			}
						    		%>
									
								</datalist> --%>
							</div>
						</div>

						<div class="col-md-3">
							<div class="input-group">

								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="BillNoWisePurchaseReport()" value="Search" />
							</div>
						</div>
					</div>
					
					<table id="billnowisereport" class="display">
						<thead>
							<tr>
								<th>Supplier</th>
								<th>Bill Number</th>
								<th>Purchase Date</th>
								<th>Product Name</th>
								<th>Company Name</th>
								<th>DC No</th>
								<th>Batch No</th>
								<th>Barcode No</th>
								<th>Buy Price</th>
								<th>Sale Price</th>
								<th>MRP</th>
								<th>Packing</th>
								<th>Quantity</th>
								<th>Total Amount</th>
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
		



		<div id="paymentDue" class="tab-pane ">
			<div class="row"></div>
			<div id="report" style="text-align: center">
				<label id="demo"></label>
				<script>
		   var date = new Date();
		   document.getElementById("demo").innerHTML = date.toDateString();
		</script>
			</div>
			<%
				SupplierPaymentDao dao=new SupplierPaymentDao();
				List Lis1=dao.getPurchaseDueDatesDetailsForReport();
	
			%>
			<div>
				<table id="DueDateList" class="display" border="1">
					<thead>
						<tr>
							<th>Supplier Name</th>
							<th>Bill Number</th>
							<th>Purchase Date</th>
							<th>Purchase Amount</th>
							<th>Due Date</th>

						</tr>
					</thead>
					<tfoot>
						<tr>
							<th></th>
						</tr>
					</tfoot>

					<tbody>
						<%
					for(int i=0;i<Lis1.size();i++){
						SupplierPaymentDetail pro = (SupplierPaymentDetail)Lis1.get(i);
				%>
						<tr>
							<td class="align"><%=pro.getSupplierName()%></td>
							<td class="align"><%=pro.getBillNumber()%></td>
							<td class="align"><%=pro.getPaymentDate()%></td>
							<td class="align"><%=pro.getTotalAmount()%></td>
							<td class="align"><%=pro.getDueDate()%></td>

						</tr>
						<%
					}
				%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>
<!-- For datatable to pdf,print,excel etc conversion -->

<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src=/AgriSoft/staticContent/js/buttons.flash.min.js></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jszip.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/pdfmake.min.js"></script>
<script type="text/javascript"
	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>
<script src="/AgriSoft/staticContent/js/goodsReceive.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/goodsreceiveFetchProduct.js"></script>
