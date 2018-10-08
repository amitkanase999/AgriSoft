
<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>

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
<script src="/AgriSoft/staticContent/js/cashbankbook.js"></script>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 20px">
			<h2 class="form-name style_heading">Credit Debit Reports</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home"><h4 style="color: blue">Datewise</h4></a></li>
		<li><a data-toggle="tab" href="#twoDates"><h4 style="color: blue">Range</h4></a></li>
		<!-- <li><a data-toggle="tab" href="#twoYears"><h4 style="color:blue">Between Two Years</h4></a></li> -->
	</ul>

	<div class="tab-content" style="float: left">

		<!-------- Single Date ---------->

		<div id="home" class="tab-pane fade in active">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action="" name="">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-3 control-label" for=""> Enter Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="fDate" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>


						<div class="col-md-3">
							<div class="input-group">
								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="creditdebitForsingleDate(); creditdebitForsingleDate1();"
									value="Search" />
							</div>
						</div>
					</div>

					<div class="table-responsive">
						<div class="col-md-offset-1 col-md-5">
							<table id="example3" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Credit</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>

						<div class="col-md-5">
							<table id="example4" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Debit</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<!-- ---	Between Two Dates	---- -->

		<div id="twoDates" class="tab-pane ">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action="">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-2 control-label" for="customerName">
							Start Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="fisDate" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>

						<label class="col-md-2 control-label" for="village">End
							Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="date" id="endDate" placeholder="End Date"
									class="form-control input-md ac_district" type="text">
							</div>
						</div>
					</div>

					<div class="row form-group buttons_margin_top ">
						<div align="center">
							<input type="button" id="btn" name="save"
								class="btn btn-lg btn-success btn-md button_hw button_margin_right"
								onclick="creditdebitForBetTowDate(); creditdebitForBetTowDate1();"
								value="Search" />
						</div>
					</div>

					<div class="table-responsive">
						<div class="col-md-offset-1 col-md-5">
							<table id="example5" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Credit</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th></th>

									</tr>
								</tfoot>
							</table>
						</div>

						<div class="col-md-5">
							<table id="example6" class="display	">
								<thead>
									<tr>
										<th>Name</th>
										<th>Debit</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>tml>
