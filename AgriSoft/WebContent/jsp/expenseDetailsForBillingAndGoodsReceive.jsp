
<%boolean isHome = false;%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/expenditureDetails.js"></script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Expenditure details For
			Billing And Goods Receive</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action=""
		name="expenseDetails1">
		<!-- Value of 'name' attribute is used in customerDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="expenseName">Expense
					Name<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="expenseNameForBilling" name="expenseName"
							placeholder="Expense Name" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<input type="button" id="save" name="btn1" style="font-size: 25"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="addExpenseForBillingAndGoodsReceive()" value="Submit">
					<input id="save" name="btn1" style="font-size: 25"
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()" value="Cancel">
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
