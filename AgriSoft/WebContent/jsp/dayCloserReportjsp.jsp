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
<link href="/AgriSoft/staticContent/css/dataTa.css" rel="stylesheet"
	type="text/css" media="all" />
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
<link
	href="/AgriSoft/staticContent/css/jquery.dataTables.tableTools.css"
	rel="stylesheet" type="text/css" media="all" />
<script
	src="/AgriSoft/staticContent/js/jquery.dataTables.tableTools.min.js"
	type="text/javascript"></script>
<script src="/AgriSoft/staticContent/js/dayCloserReport.js"></script>

<script type="text/javascript">
$(document).ready(function () {
	
	getCashSaleAmount();
	getCreditSaleAmount();
	getCashSaleAmountForSeed();
	getCreditSaleAmountForSeed();
	getCashSaleAmountForPesti();
	getCreditSaleAmountForPesti();
	
	getTodayPurchaseAmountForFertilizer();
	getTodayPurchaseAmountForSeed();
	getTodayPurchaseAmountForPesti();
	});
</script>

<script type="text/javascript">
function openBilling() {
		 window.location = '/AgriSoft/jsp/stockReport.jsp';
}

var blink_speed = 850; var t = setInterval(function () { var ele = document.getElementById('blinker'); 
ele.style.visibility = (ele.style.visibility == 'hidden' ? '' : 'hidden');
}, blink_speed);
</script>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Day Closure Report</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<!-- 		Sale -->
	
	<div class="row form-group">
		<label class="col-md-2 control-label" for="cashSale"
			style="font-size: 30px; color: red;">Sale</label> <label
			class="col-md-offset-2 col-md-2 control-label" for="cashSale"
			style="font-size: 30px;">CASH</label> <label
			class="col-md-offset-2 control-label" for="cashSale"
			style="font-size: 30px;">CREDIT</label>
	</div>

	<!-- 	Fertilizer -->
	
	<div class="row form-group">
		<label class="col-md-1 col-md-offset-1 control-label" for="cashSale"
			style="font-size: 20px;">FERTILIZER</label>
		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="cashSale" id="cashSale"
					class="form-control input-md ac_district">
			</div>
		</div>

		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="creditSale" id="creditSale"
					class="form-control input-md ac_district">
				<!-- <input readonly="readonly" id="dupsaletotal" name="dupsaletotal"  class="form-control input-md" type="hidden" > -->
			</div>
		</div>
	</div>

	<!-- Seed -->
	
	<div class="row form-group">
		<label class="col-md-1 col-md-offset-1 control-label"
			for="seedCashSale" style="font-size: 20px;">SEED</label>
		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="seedCashSale" id="seedCashSale"
					class="form-control input-md ac_district">
			</div>
		</div>

		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="seedCreditSale" id="seedCreditSale"
					class="form-control input-md ac_district">
			</div>
		</div>
	</div>

	<!-- Pesticide -->

	<div class="row form-group">
		<label class="col-md-1 col-md-offset-1 control-label"
			for="pestiCashSale" style="font-size: 20px;">PESTICIDE</label>
		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="pestiCashSale" id="pestiCashSale"
					class="form-control input-md ac_district">
			</div>
		</div>

		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="pestiCreditSale" id="pestiCreditSale"
					class="form-control input-md ac_district">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-offset-1 col-md-10">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>

	<!-- Purchase -->
	
	<div class="row form-group">
		<label class="col-md-2 control-label" for="cashSale"
			style="font-size: 30px; color: red;">Purchase</label>
	</div>

	<!-- 	Fertilizer -->

	<div class="row form-group">
		<label class="col-md-3 control-label" for="fertiPurchase"
			style="font-size: 20px;">Todays Total Purchase</label>
		<div class="col-md-3 col-md-offset-1">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input type="text"
					readonly="readonly" name="fertiPurchase" id="fertiPurchase"
					class="form-control input-md ac_district">
			</div>
		</div>
	</div>

	<div class="row form-group buttons_margin_top ">
		<div align="center">
			<input type="button" style="font-size: 25; width: 282px;"
				class="btn btn-large btn-success button-height-width" name="btn"
				onclick="openBilling()" value="Click Here For Stock">
		</div>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>tml>
