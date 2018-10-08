<% boolean isHome = false; %>
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
<!-- For datatable to pdf,print,excel etc conversion -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript"
	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script type="text/javascript"
	src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
<script src="/AgriSoft/staticContent/js/saleReports.js"></script>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">GST Summary Report</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>
	<ul class="nav nav-tabs">
		<!--  <li><a data-toggle="tab" href="#home"><h4 style="color:black">Datewise</h4></a></li> -->
		<li class="active"><a data-toggle="tab" href="#twoDates"><h4
					style="color: black">Range</h4></a></li>
		<!-- <li><a data-toggle="tab" href="#twoYears"><h4 style="color:black">Between Two Years</h4></a></li> -->
	</ul>

	<div class="tab-content" style="float: left">

		<!-- ---	Between Two Dates	---- -->
		
		<div id="twoDates" class="tab-pane fade in active">
			<div class="row"></div>
			<form class="form-horizontal" method="post" action="" name="gst">
				<fieldset>

					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="gstFisDate1" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>

						<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input type="date" id="gstEndDate1" placeholder="End Date"
									class="form-control input-md ac_district" type="text">
							</div>
						</div>
					</div>
				
					<div class="row form-group" style="margin-top: 20px">
						<div class="col-md-3 Col-md-offset-5">
							<div class="input-group">
								<input type="button" style="align: center" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="gstSummaryReportRange();gstPurchaseSummaryReportRange()"
									value="Search" />
							</div>
						</div>
					</div>
				
					<div class="table-responsive">
						<div class="col-md-6">
							<h3 align="center" class="form-name style_heading">Sale</h3>
							<table
								class="table table-bordered table-striped table-condensed cf"
								id="gstSummary" class="display table table-boardered"
								style="border: 2px solid black; border-collapse: collapse;">
								<thead>
									<tr>
										<th>Product Type</th>
										<th>Quantity</th>
										<th>GST %</th>
										<th>IGST %</th>
										<th>CGST Amount</th>
										<th>SGST Amount</th>
										<th>IGST Amount</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th colspan="4" style="text-align: right">Total Rs:</th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>

						<div class="col-md-offset-1  col-md-5">
							<h3 align="center" class="form-name style_heading">Purchase</h3>
							<table
								class="table table-bordered table-striped table-condensed cf"
								id="gstPurchaseSummary" class="display table table-boardered"
								 style="border: 2px solid black; border-collapse: collapse;">
								<thead>
									<tr>
										<th>Quantity</th>
										<th>GST %</th>
										<th>IGST %</th>
										<th>CGST Amount</th>
										<th>SGST Amount</th>
										<th>IGST Amount</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th colspan="3" style="text-align: right">Total Rs:</th>
										<th></th>
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
<%@include file="commons/footer.jsp"%>