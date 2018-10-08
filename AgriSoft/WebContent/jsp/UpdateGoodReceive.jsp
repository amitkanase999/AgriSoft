<%@page import="com.Fertilizer.hibernate.ExpenseDetailForBillingAndGoodsReceiveBean"%>
<%@page import="com.Fertilizer.dao.ExpenseDetailForBillingAndGoodsReceiveDao"%>
<%@page import="com.Fertilizer.hibernate.GodownEntry"%>
<%@page import="com.Fertilizer.dao.GodownEntryDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>


<%@page import="com.Fertilizer.dao.UpdateGoodReceiveDao"%>
<%@page import="com.Fertilizer.hibernate.GoodsReceiveBean"%>



<% boolean isHome=false;%>

<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.dao.TaxCreationDao"%>
<%@page import="com.Fertilizer.hibernate.TaxCreationBean"%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/UpdateGoodReceive.js"></script>
	
	
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/goodsReceive.js"></script>
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
	src="/AgriSoft/staticContent/js/jquery-ui.min.js"></script> 
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>

<script type="text/javascript">
</script>
</head>
<body>
		<div class="row header_margin_top">
		<div align="center" style="margin-top: 25px;">
			<h2 class="form-name style_heading">Update Goods-Receive</h2>
		</div>

	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-md-10" style="margin-top: 3px;">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>



	<div class="container col-sm-offset-1" style="margin-top: -25;">
	<form class="form-horizontal" method="post" action="" name="ugr">
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
           						List supList =sdd.getAllSupplier();
							
							%>

								<input list="sup_drop" id="suppliername" class="form-control" onchange="fetchChallanNo()" onfocus="this.value=''">
								<datalist id="sup_drop">

									<%
					           for(int i=0;i<supList.size();i++){
					        	   SupplierDetailsBean sup =(SupplierDetailsBean)supList.get(i);
							%>

									<option data-value="<%=sup.getSupId()%>"
										value="<%=sup.getDealerName() %>">
										<%
				      			}
				    		%>
								</datalist>
							</div>
						</div>
					</div>
	
	
	
		<div class="row form-group">
			<label class="col-md-2 control-label" for="challanno">Challan No <sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span>

								<input list="challan_drop" id="challanNo" class="form-control" onchange="getproductDetailInGrid()" onfocus="this.value=''">
								<datalist id="challan_drop"></datalist>

					</div>
				</div>
				
			</div>
	
				<div class="row form-group">
					<label class="col-md-2 control-label" for="billNum"><b>Bill
								Number<sup>*</sup>
						</b></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No </span> <input id="billNum"
									name="billNum" placeholder="Enter Bill Number"
									class="form-control input-md" type="text">
							</div>
						</div>
						
						<label class="col-md-2 control-label" for="purchasedate"><b>Purchase Date<sup>*</sup>
						</b></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No </span> <input id="purchaseDate"
									name="purchaseDate" placeholder="Bill Date"
									class="form-control input-md" type="Date">
							</div>
						</div>
						
						
					</div>
					
					<table id="jqGrid"></table>
					<div id="jqGridPager"></div>
			
					<br><br>
					<div class="form-group row">
						<div class="col-md-10 text-center">

							<input type="button" id="update" style="font-size: 25"
								class="btn btn-large btn-success button-height-width"
								name="btn1" onclick="updateGoodreciveValidation()"
								value="Update"> <input type="button" id="save"
								style="font-size: 25"
								class="btn btn-large btn-danger   button-height-width"
								name="btn1" onclick="refreshPage()" value="Cancel">
						</div>
					</div>
				
			</form>	
			</div>
			
</body>
