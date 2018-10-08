<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="java.util.List"%>

<head>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/y_css/jquery-ui.css">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.css">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-ui-min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/podetails.js"></script>

<script>
			function cancle(){
				location.reload();
			}
	</script>
</head>

<body onload="tokenAmountBox()">
	<div class="row header_margin_top">
		<div align="center">
			<h2 class="form-name style_heading">Advance Booking</h2>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-md-10">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>
	
	<div class="container col-sm-offset-1">
		<form class="form-horizontal" method="post" action="" name="poferti">
			<!-- Value of 'name' attribute is used in employeeDetails.js  -->
			<fieldset>
				<div class="row form-group">
					<div class="col-md-6">
						<%@include file="commons/clock.jsp"%>
					</div>
				</div>

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
								SupplierDetailsDao sdd = new SupplierDetailsDao();
           						List sList =sdd.getAllSupplier();
							
							%>

							<input list="sup_drop" id="supplier" class="form-control">
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
							<input list="cat_drop" id="fk_cat_id" class="form-control"
								onchange="pro.getAllProduct()">
							<datalist id="cat_drop">
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
				</div>
				
				<div class="row form-group">
					<label class="col-md-2 control-label" for="billType">Bill
						Type<sup>*</sup>
					</label>
					<div class="col-md-7">
						<div class="col-xs-6 col-md-4">
							<label class="radio-inline"> <input type="radio"
								name="customertype" id="token" checked="checked">Token
								Per Bag
							</label>
						</div>
						<div class="col-xs-6 col-md-ffset-1 col-md-4">
							<label class="radio-inline"> <input type="radio"
								name="customertype" id="advanceAmount">Advance Amount
							</label>
						</div>
					</div>
				</div>
				
				<div class="row form-group">
					<label class="col-md-2 control-label" for="product">Product
						Name<sup>*</sup>
					</label>
					<div class="col-md-9">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="	glyphicon glyphicon-hand-right"></i>
							</span> <select class="form-control" id='proName' name="proName"
								onchange="prodetails.getProductdetails()">
							</select>
						</div>
					</div>
				</div>

				<table id="jqGrid"></table>
				<div id="jqGridPager"></div>
				
				<div class="row row form-group"></div>
				<div id="gross" class="row form-group">
					<label class="col-md-offset-6 col-md-2 control-label"
						for="grossTotal"><h4>
							<b>Gross Total</b>
						</h4></label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								id="grossTotal" name="grossTotal" placeholder="Gross Total"
								class="form-control input-md" type="text"
								style="font-size: 25px; height: 55px;">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-md-10 text-center">
						<input type="button" id="save" style="font-size: 25"
							class="btn btn-large btn-success button-height-width" name="btn"
							onclick="addAdvanceBook()" value="Submit"> <input
							type="button" id="save" style="font-size: 25"
							class="btn btn-large btn-danger   button-height-width" name="btn"
							value="Cancel" onclick="cancle()">
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
<%@include file="commons/newFooter.jsp"%>