<%@page import="com.Fertilizer.bean.GetCreditCustomerDetails"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
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
<title>Credit Customer List</title>
<script type="text/javascript">
  			function Back()
  			{
  				window.location = "customer_detail.jsp" ;
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
	CustomerDetailsDao dao=new CustomerDetailsDao();
	List list12=dao.getCreditCustomerList();
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
					<th>First Name</th>
					<th>Middle Name</th>
					<th>Last Name</th>
					<th>Aadhar No</th>
					<th>Contact No</th>
					<th>Email Id</th>
					<th>Address</th>
					<th>Pin Code</th>
				</tr>
			</thead>

			<tbody>
				<%
					for(int i=0;i<list12.size();i++){
						GetCreditCustomerDetails sr=(GetCreditCustomerDetails)list12.get(i);
				%>
				<tr>
					<td class="align"><%=sr.getFirstName()%></td>
					<td class="align"><%=sr.getMiddleName()%></td>
					<td class="align"><%=sr.getLastName()%></td>
					<td class="align"><%=sr.getAadhar()%></td>
					<td class="align"><%=sr.getContactNo()%></td>
					<td class="align"><%=sr.getEmail()%></td>
					<td class="align"><%=sr.getAddress()%></td>
					<td class="align"><%=sr.getZipCode()%></td>
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
	<%@include file="commons/newFooter.jsp"%>
</body>
</html>
