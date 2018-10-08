
<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>
<head>
<meta charset="utf-8">
</head>
<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Quotation</h2>
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

				<label class="col-md-2 control-label" for="district">Village/City</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-map-marker"></i>
						</span> <input id="district" name="district" placeholder=""
							class="form-control input-md ac_district" required="" type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="last_name"> Total
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="last_name"
							name="last_name" placeholder="" class="form-control input-md"
							type="number" required="">
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
