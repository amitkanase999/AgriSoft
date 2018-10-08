<%@page import="com.Fertilizer.bean.GetBillDetails"%>
<%@page import="com.Fertilizer.bean.CreditCustBillNoAndName"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>

<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>

<script src="/AgriSoft/staticContent/js/generateSeedAndPesticidePDF.js"></script>
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

<div class="container" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Seed Bill Copy</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#NomCust"><h4
					style="color: blue">All SEED Customer Bill Copy</h4></a></li>
		<!-- <li><a data-toggle="tab" href="#creditCustomer"><h4
					style="color: blue">Credit Customer Bill Copy</h4></a></li> -->
	</ul>

	<div class="tab-content" style="float: left">
		<!-- Normal Cutomer Bill COPY -->

		<div id="NomCust" class="tab-pane fade in active">
			<form action="" method="post" name="genIn">
				<%
							FertilizerBillDao fd = new FertilizerBillDao();
							List list = fd.getNorCustBillAndClientName();
						%>
				<div class="row" style="margin-top: 25px;">
					<div class="col-md-offset-2">
						
						
						
						<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
									
									</span> <select class="form-control" id="custtype">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="Regular">Regular</option>
										<option value="Wholesale">Wholesale</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
				
									</span> <select class="form-control" id="paycusttype" onchange="getbillnoForSeed()">
										 <option value="selected">-Select Type--</option>
										<option value="cash">Cash</option>
										<option value="credit">Credit</option>
									</select>
								</div>
							</div>
						
						<div class="col-md-2">
							<label class="control-label"> Bill Number:</label>
						</div>
						<div class="col-md-3">
							<input list="seedBillNo_drop" id="BillNo" class="form-control">
							<datalist id="seedBillNo_drop">
								<%-- <%
					               		for(int i=0;i<list.size();i++){
					               			GetBillDetails billList=(GetBillDetails)list.get(i);
									%>
								<option data-value="<%=billList.getBillNo()%>"
									value="<%=billList.getBillNo()%>          <%=billList.getClientName()%>">
									<%
										}
									%> --%>
							</datalist>
						</div>
						
						
						<div class="col-md-4 col-md-offset-3" style="margin-top: 25px;"
							align="center">
							<button type="button" onclick="billGeneration()" name="btn"
								class="btn btn-success" style="width: 125px; height: 45px;">Print
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- Credit Cutomer Bill COPY -->

		<%-- <div id="creditCustomer" class="tab-pane">
			<form action="" method="post" name="genIn">
				<%
							FertilizerBillDao ccfd = new FertilizerBillDao();
							List cclist = ccfd.getCreditCustBIllAndClientName();
						%>
				<div class="row" style="margin-top: 25px;">
					<div class="col-md-offset-2">
						<div class="col-md-4">
							<label class="control-label"> Bill Number:</label>
						</div>
						<div class="col-md-7">
							<input list="creditCustBillNo" id="CreditBillNo"
								class="form-control">
							<datalist id="creditCustBillNo">
								<%
					               		for(int i=0;i<cclist.size();i++){
					               			CreditCustBillNoAndName ccbillList=(CreditCustBillNoAndName)cclist.get(i);
									%>
								<option data-value="<%=ccbillList.getBillNo()%>"
									value="<%=ccbillList.getBillNo()%>   <%=ccbillList.getClientName()%>">
									<%
										}
									%>
							</datalist>
						</div>

						<div class="col-md-4 col-md-offset-3" style="margin-top: 25px;"
							align="center">
							<button type="button" onclick="CreditCustBillGeneration()"
								name="btn" class="btn btn-success"
								style="width: 125px; height: 45px;">Print</button>
						</div>
					</div>
				</div>
			</form>
		</div> --%>
	</div>
</div>
</br>
<%@include file="commons/newFooter.jsp"%>
