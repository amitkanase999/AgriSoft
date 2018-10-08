<%@page import="com.Fertilizer.dao.TaxCreationDao"%>
<%@page import="com.Fertilizer.bean.GetTaxDetails"%>
<%@page import="java.util.List"%>

<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>

<link href="/AgriSoft/WebContent/staticContent/css/dataTa.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/AgriSoft/staticContent/css/dataTables.jqueryui.min.css"
	rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/tabDemo.css">
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

<html>
<head>
<title>Tax List</title>
<script type="text/javascript">
  			function Back()
  			{
  				window.location = "tax.jsp" ;
  			}
  		</script>
</head>

<script type="text/javascript"> 
		$(document).ready(function () {
	         var table=$("#list").dataTable();
			 var tableTools = new $.fn.dataTable.TableTools(table, {
				 'sSwfPath':'//cdn.datatables.net/tabletools/2.2.4/swf/copy_csv_xls_pdf.swf',
				 	'aButtons':['copy','print','csv',{
					 'sExtends':'xls',
					 'sFileName':'Data.xls',
					 'sButtonText': 'Save to Excel'
						}
					]
				});
					$(tableTools.fnContainer()).insertBefore('#list_wrapper');
			});
</script>

<body id="dt_example" style="min-height: 300px;">
	<div class="row">
		<div align="center">
			<h2 class="form-name style_heading">Credit Customer List</h2>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
	</div>
	<%
	TaxCreationDao dao=new TaxCreationDao();
	List list12=dao.getTaxDetails();
	%>
	<div id="date">
		<label id="demo"></label>
		<script>
			var date = new Date();
			document.getElementById("demo").innerHTML = date.toDateString();
		</script>
	</div>

	<div id="demo_jui">
		<table id="list" class="display" border="1">
			<thead>
				<tr>
					<th>Tax</th>
					<th>Tax Percentage</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(int i=0;i<list12.size();i++){
						GetTaxDetails sr=(GetTaxDetails)list12.get(i);
				%>
				<tr>
					<td class="align"><%=sr.getTaxType()%></td>
					<td class="align"><%=sr.getTaxPercentage()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="wrapper" align="center">
		<input type="button" value="Back" id="listBtn" class="btn btn-primary"
			onclick="Back()" />
	</div>
</body>
