<%@page import="com.Fertilizer.dao.PesticideBillDao"%>
<%@page import="com.Fertilizer.hibernate.PesticideBillBean"%>
<%@page import="com.Fertilizer.hibernate.SeedPesticideBillBean"%>
<%@page import="com.Fertilizer.dao.SeedPesticideBillDAO"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>
<%@page import="com.Fertilizer.hibernate.FertilizerBillBean"%>
<%@page import="java.util.List"%>

<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>
								
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.11.1.min.js"></script>
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
	src="/AgriSoft/staticContent/js/fertiSaleReturn.js"></script>

<script type="text/javascript">
   
     $('#bill_no').on('change','input', function() { 
    	 fetchDataForSale();  // get the current value of the input field.
    	});
     </script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Sale Return</h2>
	</div>

</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>
<ul class="nav nav-tabs col-md-offset-1">
	<li class="active"><a data-toggle="tab" href="#home"><h4 style="color: blue">Fertilizer</h4></a></li>
	<li><a data-toggle="tab" href="#twoDates"><h4 style="color: blue">Seed</h4></a></li>
	<li><a data-toggle="tab" href="#pro"><h4 style="color: blue">Pesticide</h4></a></li>
</ul>

<div class="tab-content" style="float: left">
	<!-- Fertilizer sale return -->
	<div id="home" class="tab-pane fade in active">
		<form class="form-horizontal" name="salereturn" method="post"
			action="">
			<fieldset>
				<div class="container col-sm-offset-2">
					<div class="row form-group">
						<div class="col-md-6">
							<%@include file="commons/clock.jsp"%>
						</div>
					</div>
					
					<div class="row form-group">
						<label class="col-md-2 control-label" for="billNo">Bill No</label>
						<div class="col-md-3">
							<div class="input-group "">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-hand-right"></i>
								</span>

								<%
								  FertilizerBillDao sdd = new FertilizerBillDao();
           			              List pList = sdd.getAllBillNoOnSaleReturn();
							
				                 %>
								<input list="bill_no_drop" id="bill_no" class="form-control"
									onchange="fetchDataForSale()">
								<datalist id="bill_no_drop"
									style="overflow-x: hidden; overflow: scroll; width: 100%; height: 500px">

									<%
					               for(int i=0;i<pList.size();i++){
					            	FertilizerBillBean sup =(FertilizerBillBean)pList.get(i);
							    %>

									<option data-value="<%=sup.getBillNo()%>"
										value="<%=sup.getBillNo() %> ">
										<%
				      			  }
				    		     %>
									
								</datalist>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row form-group" style="padding-left: 50px;">
					<table id="jqGrid"></table>
					<div id="jqGridPager"></div>
				</div>
				<div class="form-group row">
					<div class="col-md-10 text-center">
						<input type="button" id="save" name="save" style="font-size: 25"
							class="btn btn-large btn-success button-height-width"
							onclick="fertilizerSale()" value="Submit"> <input
							type="reset" id="btn1" style="font-size: 25"
							class="btn btn-large btn-danger   button-height-width"
							name="btn1" onclick="window.location.reload()" value="Cancel">
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<!-- 	 Seed return -->
	
	<div id="twoDates" class="tab-pane ">
		<form class="form-horizontal" name="seedform" method="post" action="">
			<fieldset>
				<div class="container col-sm-offset-2">
					<div class="row form-group">
						<div class="col-md-6">
							<%@include file="commons/clock.jsp"%>
						</div>
					</div>
				
					<div class="row form-group">
						<label class="col-md-2 control-label" for="seedBillNo">Bill
							No</label>
						<div class="col-md-3">
							<div class="input-group "
								style="overflow-x: hidden; overflow: scroll;">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-hand-right"></i>
								</span>

								<%
								SeedPesticideBillDAO seedDao = new SeedPesticideBillDAO();
           			              List seedList = seedDao.getAllSeedBillNoForSaleReturn();
							
				                 %>
								<input list="seedBillNo_drop" id="seedBillNo"
									class="form-control" onchange="fetchSeedDataForSale()">
								<datalist id="seedBillNo_drop">

									<%
					               for(int i=0;i<seedList.size();i++){
					            	   SeedPesticideBillBean seedBean =(SeedPesticideBillBean)seedList.get(i);
							    %>

									<option data-value="<%=seedBean.getBillNo()%>"
										value="<%=seedBean.getBillNo() %>">
										<%
				      			  }
				    		     %>
									
								</datalist>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row form-group" style="padding-left: 50px;">
					<table id="jqGridSeed"></table>
					<div id="jqGridPagerSeed"></div>
				</div>
				<div class="form-group row">
					<div class="col-md-10 text-center">
						<input type="button" id="save1" name="save" style="font-size: 25"
							class="btn btn-large btn-success button-height-width"
							onclick="seedSale()" value="Submit"> <input type="reset"
							id="btn1" style="font-size: 25"
							class="btn btn-large btn-danger   button-height-width"
							name="btn1" onclick="window.location.reload()" value="Cancel">
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<!-- 	 Pesticide return -->
	
	<div id="pro" class="tab-pane ">
		<form class="form-horizontal" name="Pesticideform" method="post"
			action="">
			<fieldset>
				<div class="container col-sm-offset-2">
					<div class="row form-group">
						<div class="col-md-6">
							<%@include file="commons/clock.jsp"%>
						</div>
					</div>
					<div class="row form-group">
						<label class="col-md-2 control-label" for="pestiBillNo">Bill
							No</label>
						<div class="col-md-3">
							<div class="input-group "
								style="overflow-x: hidden; overflow: scroll;">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-hand-right"></i>
								</span>

								<%
								PesticideBillDao pestiDao = new PesticideBillDao();
           			              List pestiList = pestiDao.getAllPestiBillNoForSaleReturn();
							
				                 %>
								<input list="pestiBillNo_drop" id="pestiBillNo"
									class="form-control" onchange="fetchPesticideDataForSale()">
								<datalist id="pestiBillNo_drop">

									<%
					               for(int i=0;i<pestiList.size();i++){
					            	   PesticideBillBean pestiBean =(PesticideBillBean)pestiList.get(i);
							    %>

									<option data-value="<%=pestiBean.getBillNo()%>"
										value="<%=pestiBean.getBillNo() %>">
										<%
				      			  }
				    		     %>
									
								</datalist>
							</div>
						</div>
					</div>
				</div>
		
				<div class="row form-group" style="padding-left: 50px;">
					<table id="jqGridPesti"></table>
					<div id="jqGridPagerPesti"></div>
				</div>
			
				<div class="form-group row">
					<div class="col-md-10 text-center">
						<input type="button" id="save2" name="save" style="font-size: 25"
							class="btn btn-large btn-success button-height-width"
							onclick="PesticideSale()" value="Submit"> <input
							type="reset" id="btn1" style="font-size: 25"
							class="btn btn-large btn-danger   button-height-width"
							name="btn1" onclick="window.location.reload()" value="Cancel">
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>
