<% boolean isHome=false;%>

<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="java.util.List"%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/supplierAccountDetails.js"></script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Supplier Account Details</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action="" name="splAcc">
		<!-- Value of 'name' attribute is used in  supplierDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="fk_supplier_id">Supplier
					Name<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <select class="form-control input-md" id="fk_supplier_id">
							<option value="">Select Supplier</option>

							<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
							<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

							<%
								SupplierDetailsDao sdd = new SupplierDetailsDao();
           						List sList =sdd.getAllSupplier();
							
							%>

							<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
							%>

							<option value="<%=sup.getSupId()%>"><%=sup.getDealerName() %>
							</option>
							<%
				      			}
				    		%>
						</select>
					</div>
				</div>

				<label class="col-md-2 control-label" for="billNo">Bill No.<sup>*</sup></label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="billNo"
							name="billNo" placeholder="Bill Number"
							class="form-control input-md " type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="totalAmt"> Total
					Amount<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="totalAmt"
							name="totalAmt" placeholder="Total Amount"
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-9 text-center">
					<!--  () function is implemented in  -->
					<button id="save" name="btn"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="supAccount()">
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
<%@include file="commons/newFooter.jsp"%>
