<%@page import="com.Fertilizer.dao.TaxCreationDao"%>
<%@page import="com.Fertilizer.hibernate.TaxCreationBean"%>
<%@page import="java.util.List"%>

<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<title>Tax Creation</title>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/taxCreation.js"></script>
<script type="text/javascript">
				function ChooseContact(data)
					{
						document.getElementById("taxtypename").value=(data.options[data.selectedIndex].getAttribute("typenm"));
						document.getElementById("taxPercentage").value=(data.options[data.selectedIndex].getAttribute("myid"));
					}
</script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Edit Tax Details</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action="" name="txc">
		<!-- Value of 'name' attribute is used in  taxCreation.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>
	
			<div class="row form-group">
				<label class="col-md-2 control-label" for="taxType">Tax Type<sup>*</sup></label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-hand-right"></i>
						</span>

						<%
							TaxCreationDao tcd = new TaxCreationDao();
							List tList = tcd.getAllMainTax();
		            	    %>

						<select class="form-control input-md" autofocus name="fk_tax_id"
							id="fk_tax_id" onChange="ChooseContact(this)">
							<option value="selected">--Select Tax--</option>
							<%
					           for(int i=0;i<tList.size();i++){
					        	   TaxCreationBean tax=(TaxCreationBean)tList.get(i);
							%>

							<option value="<%=tax.getTxId()%>"
								myid="<%=tax.getTaxPercentage()%>"
								typenm="<%=tax.getTaxType()%>"><%=tax.getTaxType()%>
							</option>
							<%
				      			}
				    		%>
						</select>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="taxType">Tax Type<sup>*</sup></label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-hand-right"></i>
						</span> <input type="text" name="taxtypename" id="taxtypename"
							class="form-control" placeholder="Tax Type Name">
					</div>
				</div>

				<label class="col-md-2 control-label" for="taxPercentage">Tax
					Percentage<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> % </span> <input type="text"
							name="taxPercentage" id="taxPercentage" class="form-control"
							placeholder="Tax Percentage">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<!--  txCreation() function is implemented in  taxCreation.js -->
					<input type="button" id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="editTax()" value="Update"> <input id="save"
						name="btn" style="font-size: 25"
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()" value="Cancel"> <input
						id="save" name="btn" style="font-size: 25"
						class="glyphicon glyphicon-arrow-left btn btn-info btn-lg  button-height-width"
						type="reset" onclick="back()" value=" <<Back">
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
