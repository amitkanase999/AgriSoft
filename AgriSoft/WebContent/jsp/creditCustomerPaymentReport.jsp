<%@page import="com.Fertilizer.bean.CreditCustPaymentDetail"%>
<%@page import="com.Fertilizer.dao.CustomerPaymentDao"%>
<%@page import="java.util.List"%>

<% boolean isHome = false; %>

<%@include file="commons/header.jsp"%>

<html>
<head>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/selectjj.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/buttom.js"></script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
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
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Credit Customer Payment
			Report</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div id="report">
	<label id="demo"></label>
	<script>
		var date = new Date();
		document.getElementById("demo").innerHTML = date.toDateString();
	</script>
</div>

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
</script>

<body id="dt_example">

	<%
		CustomerPaymentDao dao = new CustomerPaymentDao();
		List Lis1 = dao.getCreditCustPaymentDetailsForReport();
	%>

	<div id="demo_jui">
		<table id="list" class="display" border="1">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Bill Number</th>
					<th>Total Amount</th>
					<th>Balance Amount</th>
					<th>Credit Amount</th>
					<th>Payment Mode</th>

				</tr>
			</thead>
			<tfoot>
				<tr>
					<th colspan="5" style="text-align: right">Total:</th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<%
					for (int i = 0; i < Lis1.size(); i++) {
						CreditCustPaymentDetail cust = (CreditCustPaymentDetail) Lis1
								.get(i);
				%>
				<tr>
					<td class="align"><%=cust.getCreditCustomerFirstName()%></td>
					<td class="align"><%=cust.getCreditCustomerLastName()%></td>
					<td class="align"><%=cust.getBillNo()%></td>
					<td class="align"><%=cust.getTotalAmount()%></td>
					<td class="align"><%=cust.getBalanceAmount()%></td>
					<td class="align"><%=cust.getCredit()%></td>
					<td class="align"><%=cust.getPaymentMode()%></td>
				</tr>

				<%
					}
				%>

			</tbody>
		</table>
	</div>
	<%@include file="commons/newFooter.jsp"%>
</body>
</html>
