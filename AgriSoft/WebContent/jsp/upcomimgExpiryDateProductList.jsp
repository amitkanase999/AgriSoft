
<% boolean isHome=false; %>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>

<head>
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

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var table = $("#list").dataTable(
								{

									fnRowCallback : function(nRow, aData,
											iDisplayIndex) {
										$("th:first", nRow).html(
												iDisplayIndex + 1);
										return nRow;
									},

									"footerCallback" : function(row, data,
											start, end, display) {
										var api = this.api(), data;

										// Remove the formatting to get integer data for summation
										var intVal = function(i) {
											return typeof i === 'string' ? i
													.replace(/[\$,]/g, '') * 1
													: typeof i === 'number' ? i
															: 0;
										};

										// Total over all pages
										total = api.column(5).data().reduce(
												function(a, b) {
													return intVal(a)
															+ intVal(b);
												}, 0);
										console.log(total);

										// Update footer
										$(api.column(5).footer()).html(
												'Rs' + ' ' + total);
										console.log(total);

									}

								});
						var tableTools = new $.fn.dataTable.TableTools(
								table,
								{
									'sSwfPath' : '//cdn.datatables.net/tabletools/2.2.4/swf/copy_csv_xls_pdf.swf',
									'aButtons' : [ 'copy', 'print', 'csv', {
										'sExtends' : 'xls',
										'sFileName' : 'Data.xls',
										'sButtonText' : 'Save to Excel'
									} ]
								});
						$(tableTools.fnContainer()).insertBefore(
								'#list_wrapper');
					});

	/* 	for pesticide */

	$(document)
			.ready(
					function() {
						var table = $("#list1").dataTable(
								{

									fnRowCallback : function(nRow, aData,
											iDisplayIndex) {
										$("th:first", nRow).html(
												iDisplayIndex + 1);
										return nRow;
									},

									"footerCallback" : function(row, data,
											start, end, display) {
										var api = this.api(), data;

										// Remove the formatting to get integer data for summation
										var intVal = function(i) {
											return typeof i === 'string' ? i
													.replace(/[\$,]/g, '') * 1
													: typeof i === 'number' ? i
															: 0;
										};

										// Total over all pages
										total = api.column(5).data().reduce(
												function(a, b) {
													return intVal(a)
															+ intVal(b);
												}, 0);
										console.log(total);

										// Update footer
										$(api.column(5).footer()).html(
												'Rs' + ' ' + total);
										console.log(total);

									}

								});
						var tableTools = new $.fn.dataTable.TableTools(
								table,
								{
									'sSwfPath' : '//cdn.datatables.net/tabletools/2.2.4/swf/copy_csv_xls_pdf.swf',
									'aButtons' : [ 'copy', 'print', 'csv', {
										'sExtends' : 'xls',
										'sFileName' : 'Data.xls',
										'sButtonText' : 'Save to Excel'
									} ]
								});
						$(tableTools.fnContainer()).insertBefore(
								'#list_wrapper');
					});
</script>
</head>

<div class="container col-md-offset-1" style="float: left">
	<div class="row">
		<div align="center" style="margin-top: 75px">
			<h2 class="form-name style_heading">Upcoming Expiry Date
				Product's</h2>
		</div>
		<div class="row " id="report" style="text-align: center">
			<label id="demo" class="col-md-offset-7"></label>
			<script>
				var date = new Date();
				document.getElementById("demo").innerHTML = date.toDateString();
			</script>
		</div>
		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>

	<div class="row"></div>
	<%
		GoodsReceiveDao productForNotification = new GoodsReceiveDao();
		List<GoodsReceiveDetail> notificationProducts = productForNotification.upcomingExpirySeedProducts();
	%>
	<div id="demo_jui" style="text-align: center">
		<table id="list" class="display col-md-2" border="1">
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Manufacturer Name</th>
					<th>Weight</th>
					<th>Batch Number</th>
					<th>Expiry Date</th>
					<th>Stock</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < notificationProducts.size(); i++) {
						GoodsReceiveDetail goodsForNotification = (GoodsReceiveDetail) notificationProducts
								.get(i);
				%>
				<tr>
					<td><%=goodsForNotification.getProductName()%></td>
					<td><%=goodsForNotification.getCompany()%></td>
					<td><%=goodsForNotification.getWeight()%></td>
					<td><%=goodsForNotification.getBatchNumber()%></td>
					<td><%=goodsForNotification.getExpiryDate()%></td>
					<td><%=goodsForNotification.getStock()%></td>
					<%
						}
					%>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>
