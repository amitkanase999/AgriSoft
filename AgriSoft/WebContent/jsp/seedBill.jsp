<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/ItemDetail.js"></script>
<script src="/AgriSoft/staticContent/js/vatpercentage.js"
	type="text/javascript"></script>
</head>

<body onload="myOnLoadFunction()">
	<div class="container col-sm-offset-2 ">
		<form class="form-horizontal" method="post" action="">
			<fieldset>
				<div class="row form-group">
					<div class="col-md-6">
						<%@include file="commons/clock.jsp"%>
					</div>
				</div>
				<h3 align="center">
					<b>Seed Bill</b>
				</h3>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="last_name">
						Customer Name</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input id="last_name" name="last_name" placeholder=""
								class="form-control input-md" type="text" required="">
						</div>
					</div>

					<label class="col-md-2 control-label" for="district">Village/City</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-map-marker"></i>
							</span> <input id="district" name="district" placeholder=""
								class="form-control input-md ac_district" required=""
								type="text">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="productName">Product
						Name</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <select class="form-control input-md" id="productName">
								<option>select product</option>
							</select>
						</div>
					</div>


					<label class="col-md-2 control-label" for="last_name">Barcode
						no.</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> No. </span> <input
								id="last_name" name="last_name" placeholder=""
								class="form-control input-md" type="text" required="">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="last_name">
						Contact No</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-phone"></i>
							</span> <input id="last_name" name="last_name" placeholder=""
								class="form-control input-md" type="number" required="">
						</div>
					</div>

					<label class="col-md-2 control-label" for="paymentMode">Payment
						mode</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <select class="form-control input-md" id="paymentMode">
								<option>select Mode</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="customertype">Customer
						Type</label>
					<div class="col-md-5">
						<div class="col-xs-6 col-md-2">
							<label class="radio-inline"> <input type="radio"
								name="customertype" id="customertype" checked="checked"
								onchange="hideTextBox()">Cash
							</label>
						</div>
						<div class="col-xs-6 col-md-ffset-1 col-md-2">
							<label class="radio-inline"> <input type="radio"
								name="customertype" id="customertype" onchange="showTextBox()">Credit
							</label>
						</div>
					</div>

					<div class="col-md-3" id="HideShowText">
						<div class="input-group">
							<span class="input-group-addon"> % </span> <input
								id="creditPercentage" name="creditPercentage"
								placeholder="Credit Percentage" class="form-control input-md"
								type="number" required="">
						</div>
					</div>
				</div>


				<div class="row form-group">
					<label class="col-md-2 control-label" for="advancepayment">Advance
						Payment</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								id="advancepayment" name="advancepayment" placeholder=""
								class="form-control input-md ac_district" required=""
								type="number">
						</div>
					</div>
				
					<label class="col-md-3 control-label" for="expenses">
						Expenses </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input id="expenses"
								name="expenses" placeholder="" class="form-control input-md"
								type="number" required="">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="remainPayment">Remaining
						Payment</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								id="remainPayment" name="remainPayment" placeholder=""
								class="form-control input-md ac_district" required=""
								type="text">
						</div>
					</div>
					
					<label class="col-md-3 control-label" for="last_name">
						Total </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input id="last_name"
								name="last_name" placeholder="" class="form-control input-md"
								type="text" required="">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-md-10 text-center">
						<button id="save" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
							onclick="">
							<h4>Submit</h4>
						</button>
						<button id="" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-print  button-height-width"
							onclick="">
							<h4>Print</h4>
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
</body>
<%@include file="commons/newFooter.jsp"%>
