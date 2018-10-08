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

	<link href="https://fonts.googleapis.com/css?family=Oswald|Roboto" rel="stylesheet">
	<link rel="/AgriSoft/staticContent/css/Progressbar.css">
	
	<script type="text/javascript"
	src="/AgriSoft/staticContent/js/Progressbar.js"></script>
	
	

<script type="text/javascript"
	src="/AgriSoft/staticContent/js/bootbox.min.js"></script>

<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/TransferStockToGodown.js"></script>

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
<body onload="hideProgressbar()">
		<div class="row header_margin_top">
		<div align="center" style="margin-top: 25px;">
			<h2 class="form-name style_heading">Transfer Stock</h2>
		</div>

	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-md-10" style="margin-top: 3px;">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>



	<div class="container col-sm-offset-1" style="margin-top: -25;">
	<form class="form-horizontal" method="post" action="" name="trfs">
		
		<div class="row form-group">
							<label class="col-md-3 control-label" for="godown">
								From Godown<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span> 
									<%
									
									GodownEntryDao ged=new GodownEntryDao();
									List gList =ged.getAllGodown();
								
							
							%>

								<input list="godown_drop" id="godown" class="form-control" onchange="checkfromgodown()" ><!-- <span class="input-group-addon"> 
								 <button  type="button" onclick="supplierDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								<button type="button" onclick="autoRefresh_div()" ><img src="/AgriSoft/staticContent/images/refresh.png" width="16" heigth="13"></button> 
								</span> -->
								<datalist id="godown_drop">
									
									<%
					           for(int i=0;i<gList.size();i++){
					        	   GodownEntry bean=(GodownEntry)gList.get(i);
					        	
							%>		
									
									 <option data-value="<%=bean.getPkGodownId()%>"
										value="<%=bean.getGodownName() %>" >
									</option>
										<%
				      			}
				    		%>
								</datalist>
									
									
									
								</div>
							</div>
							
								<label class="col-md-2 control-label" for="togodown"> To Godown <sup>*</sup>
							</label>
						
								<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span> 
									<%
									
									GodownEntryDao ged1=new GodownEntryDao();
									List gList1 =ged1.getAllGodown();
								
							
							%>

								<input list="togodown_drop" id="togodown" class="form-control" onchange="checktogodown()"><!-- <span class="input-group-addon"> 
								 <button  type="button" onclick="supplierDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								<button type="button" onclick="autoRefresh_div()" ><img src="/AgriSoft/staticContent/images/refresh.png" width="16" heigth="13"></button> 
								</span> -->
								<datalist id="togodown_drop">
									
									<%
					           for(int i=0;i<gList1.size();i++){
					        	   GodownEntry bean=(GodownEntry)gList1.get(i);
					        	
							%>		
									
									 <option data-value="<%=bean.getPkGodownId()%>"
										value="<%=bean.getGodownName() %>" >
									</option>
										<%
				      			}
				    		%>
								</datalist>
									
									
								</div>
							</div>
						</div>
				
					<div class="row form-group">
						<label class="col-md-3 control-label" for="fk_cat_id">Product
							Category<sup>*</sup>
						</label>
						<div class="col-md-3" id="cat">
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
								<input list="cat_drop" id="category" class="form-control"
									onchange="fetchProductName();">
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
						<label class="col-md-3 control-label" for="proName">Product
							Name<sup>*</sup>
						
						</label>
						<div class="col-md-8">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span> 
								
								<input list="Product_list_drop" class="form-control" id='proName' name="proName"
									onchange="getAllFertilizerProductForGrid()" onfocus="this.value=''">
									
									<!-- <span class="input-group-addon"> <button  type="button" onclick="customerDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button></i> -->
								</span>
								
								<datalist id="Product_list_drop"></datalist>
								
								<!-- <select class="form-control" id='proName' name="proName"
									onchange="productDetailInGrid()">
								</select> -->
							</div>
						</div>
					</div>
						
			</div>
	
					<center>
					<table id="jqGrid"></table>
					<div id="jqGridPager"></div>
					</center>
					
					<br><br>
					<div class="form-group row">
						<div class="col-md-6 text-center col-md-offset-3">

							<input type="button" id="transfer" style="font-size: 25"
								class="btn btn-large btn-success button-height-width"
								name="transfer" onclick="validationStockTransfer();"
								value="Transfer"> <input type="button" id="save"
								style="font-size: 25"
								class="btn btn-large btn-danger   button-height-width"
								name="btn1" onclick="refreshPage()" value="Cancel">
						</div>
					</div>
					
					<div class="container"  id="stockprogress" >
						<div class="col-md-6 col-md-offset-3">
							<div class="well">

								<p>Stock Transfer...!</p>
								<div id="progressbar"><div class="progress-label">Transfer...</div></div>

							</div>
					</div>
				
			</form>	
			</div>
			
</body>
