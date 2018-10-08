<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Purchase order for seed</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action="">
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="orderNo">Order No</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="orderNo" name="orderNo" placeholder="Order No"
							class="form-control input-md" type="text" required="">
					</div>
				</div>

				<label class="col-md-2 control-label" for="categoryName">
					Category </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-hand-right"></i>
						</span> <select id="categoryName" class="form-control input-md">
							<option>select Category</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="subcategoryName">
					Sub Category</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-hand-right"></i>
						</span> <select id="categoryName" class="form-control input-md">
							<option>select Sub Category</option>
						</select>
					</div>
				</div>

				<label class="col-md-2 control-label" for="supplierName">Supplier</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <select id="supplierName" class="form-control input-md">
							<option>select Supplier</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="productName">
					Product Name </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-font"></i>
						</span> <input id="productName" name="productName"
							placeholder="Product Name" class="form-control input-md"
							type="text" required="">
					</div>
				</div>

				<label class="col-md-2 control-label" for="mname">
					Manufacturer Name</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-home"></i>
						</span> <input id="mname" name="mname" placeholder="Manufacturer Name"
							class="form-control input-md" type="text" required="">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="contactNo">Contact
					No</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-phone"></i>
						</span> <input id="contactNo" name="contactNo" placeholder="Contact No"
							class="form-control input-md" type="number" required="">
					</div>
				</div>

				<label class="col-md-2 control-label" for="bookdate">
					Booking Date </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-calendar"></i>
						</span> <input id="bookdate" name="bookdate" placeholder=""
							class="form-control input-md" type="date" required="">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="paymentType">
					Payment Mode </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-piggy-bank"></i>
						</span> <select class="form-control input-md" id="paymentType">
							<option>select type</option>
						</select>
					</div>
				</div>

				<label class="col-md-2 control-label" for="tamount">Total
					Amount </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="tamount"
							name="tamount" placeholder="Total Amount"
							class="form-control input-md" type="number" required="">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="advancepayment">Advance
					Payment</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input
							id="advancepayment" name="advancepayment"
							placeholder="Advance Payment"
							class="form-control input-md ac_district" required=""
							type="number">
					</div>
				</div>

				<label class="col-md-2 control-label" for="remainPayment">Remaining
					Payment</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input
							id="remainPayment" name="remainPayment"
							placeholder="Remaining Payment"
							class="form-control input-md ac_district" required=""
							type="number">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="expirydate">
					Expiry Date </label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-calendar"></i>
						</span> <input id="expirydate" name="expirydate"
							placeholder="Expiry Date" class="form-control input-md"
							type="date" required="">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<button id="save" name="save" class="btn btn-large btn-success">
						Submit</button>
					<button class="btn btn-large btn-danger" type="reset">
						Cancel</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
