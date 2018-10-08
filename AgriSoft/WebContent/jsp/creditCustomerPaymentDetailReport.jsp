<%@page import="com.Fertilizer.bean.CreditCustPaymentDetail"%>
<%@page import="com.Fertilizer.dao.CustomerPaymentDao"%>

<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.bean.ProductDetailsForReports"%>
<%@page import="com.Fertilizer.dao.ProductDetailsDao"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>

<head>
<link href="/AgriSoft/WebContent/staticContent/css/dataTa.css" rel="stylesheet" type="text/css" media="all" />
	<link href="/AgriSoft/staticContent/css/dataTables.jqueryui.min.css"  rel="stylesheet" type="text/css" media="all">
	<link rel="stylesheet" href="/AgriSoft/staticContent/css/tabDemo.css">
 	<link rel="stylesheet" href="/AgriSoft/staticContent/css/jquery-ui.min.css">
    <link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
    <script src="/AgriSoft/staticContent/js/jquery.min.js"></script>
    <script src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
	<script src="/AgriSoft/staticContent/js/jquery.dataTables.js" type="text/javascript"></script>
	<script type="text/javascript" src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
</head>

 <div class="container col-md-offset-1" style="float: left"> 
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Credit Customer Payment details</h2>
			</div>
				 	
			 <div class="row">
				<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color:#c1b1b1;">
				</div>	
			  </div>
		</div>
   
    <!-------- All Credit Customers ---------->
   
   	<div id="report" style="text-align: center">
		<label id="demo" align=></label>
		<script>
		   var date = new Date();
		   document.getElementById("demo").innerHTML = date.toDateString();
		</script>
	</div>

	<script type="text/javascript"> 
		$(document).ready(function () {
			var table=$("#list").dataTable({
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            total = api
			                .column( 5 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); 
			 			
			 		
			            // Update footer
			            $( api.column( 5 ).footer() ).html(
			                'Rs'+' '+total
			            );
			            console.log( total);
			    }
			});
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

	<%
	CustomerPaymentDao dao=new CustomerPaymentDao();
	List Lis1=dao.getCreditCustomerPaymentDetailForReport();
	%>

	<div id="demo_jui" style="text-align: center">
		<table id="list" class="display" border="1">
			<thead>
				<tr>
					<th>Customer First Name</th>
					<th>Customer Last Name</th>
	                <th>Category Name</th>
					<th>Bill Number</th>
					<th>Total Amount</th>
					<th>Credit Amount</th>
					<th>Debit Amount</th>
					<th>Balance Amount</th>
					<th>Payment Mode</th>
					<th>Accountant Name</th>
				</tr>
			</thead>
			<tfoot>
            <tr>
                <th></th>
            </tr>
        </tfoot>
			
			<tbody>
   				<%
					for(int i=0;i<Lis1.size();i++){
						CreditCustPaymentDetail pro = (CreditCustPaymentDetail)Lis1.get(i);
				%>
				<tr>
					<td class="align"><%=pro.getCreditCustomerFirstName()%></td>
					<td class="align"><%=pro.getCreditCustomerLastName()%></td>
					<td class="align"><%=pro.getCatName()%></td>
					<td class="align"><%=pro.getBillNo()%></td>
					<td class="align"><%=pro.getTotalAmount()%></td>
					<td class="align"><%=pro.getCreditAmount()%></td>
					<td class="align"><%=pro.getDebitAmount()%></td>
					<td class="align"><%=pro.getBalanceAmount()%></td>
					<td class="align"><%=pro.getPaymentMode()%></td>
					<td class="align"><%=pro.getAccountantName()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
 </div>
<%@include file="commons/newFooter.jsp" %>tml>