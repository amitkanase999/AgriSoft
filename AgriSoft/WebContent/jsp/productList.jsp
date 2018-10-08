<%@page import="com.Fertilizer.bean.GetProductDetails"%>
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
<title>Supplier List</title>
<script type="text/javascript">
  			function Back()
  			{
  				window.location = "product_detail.jsp" ;
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
	ProductDetailsDao dao=new ProductDetailsDao();
	List list12=dao.getProductList();
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
					<th>Product Name</th>
					<th>Category</th>
					<th>Manufacturing Company</th>
					<th>Buy price</th>
					<th>M.R.P</th>
					<th>Tax Name</th>
					<th>Tax Percentage</th>
					<th>Sale Price</th>
					<th>Credit Customer Sale Price</th>
				</tr>
			</thead>

			<tbody>
				<%
					for(int i=0;i<list12.size();i++){
						GetProductDetails sr=(GetProductDetails)list12.get(i);
				%>

				<tr>
					<td class="align"><%=sr.getProduct()%></td>
					<td class="align"><%=sr.getCat()%></td>
					<td class="align"><%=sr.getManufacturer()%></td>
					<td class="align"><%=sr.getBuyPrice()%></td>
					<td class="align"><%=sr.getMrp()%></td>
					<td class="align"><%=sr.getTaxType()%></td>
					<td class="align"><%=sr.getTaxPercentage()%></td>
					<td class="align"><%=sr.getSalePrice()%></td>
					<td class="align"><%=sr.getCreditSalePrice()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="wrapper" align="center" class="btn btn-large btn-success button-height-width" style="margin-top: 25px;">
		<input style="font-size: 23px; width: 100px;height: 50px;" type="button" value="Back" id="listBtn" class="btn btn-primary"
			onclick="Back()" />
	</div>
</body>
</html>
