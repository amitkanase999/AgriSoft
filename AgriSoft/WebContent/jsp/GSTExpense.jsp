
<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>
<head>
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
<script src="/AgriSoft/staticContent/js/goodsReceive.js"></script>
</head>

<div class="row"></div>

<div class="row">
	<div align="center" style="margin-top: 75px;">
		<h2 class="form-name style_heading">GST Wise Expense Reports</h2>
	</div>

	<div class="row">
		<div class="col-sm-offset-1 col-md-10">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>
</div>

<form class="form-horizontal" method="post" action="" name="gstExpense"
	style="margin-top: -40px;">
	<div class="row form-group" style="margin-top: 20px">
		<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-user"></i>
				</span> <input type="date" id="gstFisDate" placeholder="Start Date"
					class="form-control input-md" type="text">
			</div>
		</div>

		<label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-map-marker"></i>
				</span> <input type="date" id="gstEndDate" placeholder="End Date"
					class="form-control input-md ac_district" type="text">
			</div>
		</div>
	</div>

	<div class="row form-group" style="margin-top: 20px">

		<div class="col-md-3 Col-md-offset-5">
			<div class="input-group">
				<input type="button" id="btn" name="save"
					class="btn btn-lg btn-success btn-md button_hw button_margin_right"
					onclick="gstWiseExpenseReport()" value="Search" />
			</div>
		</div>
	</div>
	
	<div class="table-responsive">
		<table id="gstSale" class="display table table-boardered"
			 style="border: 2px solid black; border-collapse: collapse;">
			<thead>
				<tr>
					<th>Date</th>
					<th>Name Of Supplier</th>
					<th>Bill No.</th>
					<th>Transportation Expense</th>
					<th>GST On Transportation Expense</th>
					<th>Hamali Expense</th>
					<th>GST On Hamali Expense</th>
					<th>Tax Amount On Transportation</th>
					<th>Tax Amount On Hamali</th>
					<th>Total Tax Amount</th>
				</tr>
			</thead>
			<tfoot>
			</tfoot>
		</table>
	</div>
</form>