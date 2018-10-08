
<%@page import="com.Fertilizer.dao.GoodsReceiveDao"%>
<%@page import="com.Fertilizer.hibernate.GoodsReceiveBean"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>

<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
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
	src="/AgriSoft/staticContent/js/purchaseReturn.js"></script>

<script type="text/javascript">
     $(document).on('change', 'select', function(){
    	 fetchDataForPurchase();
    	});
     </script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Purchase Return</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<form class="form-horizontal" method="post" name="purchasereturn"
	action="">
	<fieldset>
		<div class="container">
			<div class="row form-group">
				<label class="col-md-3 control-label" for="supplier">Supplier
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

						<input list="sup_drop" id="supplier" class="form-control"
							onchange="getAllBills()">
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

				<label class="col-md-2 control-label" for="bill_no"> Bill No<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No </span> <select
							class="form-control input-md" id='bill_no' name="bill_no">
						</select>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row form-group " style="padding-left: 50px;">
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>

		<div class="form-group row">
			<div class="col-md-10 text-center">
				<input type="button" id="btn" style="font-size: 25"
					class="btn btn-large btn-success button-height-width" name="btn"
					onclick="purchaseReturnVlidation()" value="Submit"> <input
					type="reset" id="btn1" style="font-size: 25"
					class="btn btn-large btn-danger   button-height-width" name="btn1"
					onclick="window.location.reload()" value="Cancel">
			</div>
		</div>
	</fieldset>
</form>
<%@include file="commons/newFooter.jsp"%>
