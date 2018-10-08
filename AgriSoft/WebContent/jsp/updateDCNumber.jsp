<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>

<% boolean isHome=false;%>

<%@page import="com.Fertilizer.dao.PurchaseOrderDao"%>
<%@page import="com.Fertilizer.bean.PoDetailsForGetPoNumber"%>
<%@page import="java.util.List"%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/dcNumberUpdate.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.css">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Update DC Number</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action="" name="dc">
		<!-- Value of 'name' attribute is used in  productDetail.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-3 control-label" for="poNum">PO Number<sup>*</sup></label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<!-- Following code is to get PO Number from "purchase_order" table of "fertilizer" DB -->
						<!-- getPONumberForDCUpdate() is implemented in  PurchaseOrderDao with return type List-->

						<%
							PurchaseOrderDao pod = new PurchaseOrderDao();
           				     List cList = pod.getPONumberForDCUpdate();
							
							%>
						<input list="pro_drop" id="poNum" class="form-control"
							onchange="podetails.getPurchaseOrderdetails()">
						<datalist id="pro_drop">
							<%
					           for(int i=0;i<cList.size();i++){
					        	   PoDetailsForGetPoNumber po = (PoDetailsForGetPoNumber)cList.get(i);
							%>

							<option data-value="<%=po.getPonumber()%>"
								value="<%=po.getPonumber()%>"></option>
							<%
				      			}
				    		%>

						</datalist>
					</div>
				</div>
			</div>

			<!--    	GRID -->
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
			<div class="row form-group"></div>
			<div class="row form-group">
				<label class="col-md-3 control-label" for="dcNum">Enter DC
					Number<sup>*</sup>
				</label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"> No </span> <input type="text"
							id="dcNum" name="dcNum" class="form-control input-md"
							placeholder="DC Number">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<button id="save" name="btn"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="updateDcNumber()">
						<h4>Submit</h4>
					</button>
					<button
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()">
						<h4>Cancel</h4>
					</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>
