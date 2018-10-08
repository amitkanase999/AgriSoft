function getRangeWiseGSTSaleReport(){

	var params= {};
	var startdate=$('#startDate').val();
	var endDate=$('#enddate').val();
	

	params["startdate"]= startdate;
	params["endDate"]= endDate;
	params["methodName"] = "getRangeWiseGstSaleReport";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		$('#RangewiseSaleGstReport').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#RangewiseSaleGstReport').DataTable( {

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
					// Total over this page
					//cash total
					
					pageTotal = api
					.column( 2 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 2 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					
					pageTotal = api
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					

					//Card Total
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
						  {"data": "customerType", "width": "5%"},
				          {"data": "salesTaxable", "width": "5%"},
				          {"data": "TaxableValue", "width": "5%"},
				          {"data": "cgst" , "width": "5%"},
				          {"data": "sgst", "width": "5%"},
				          {"data": "totalTaxAmount" , "width": "5%"}
				          ],
				          dom: 'Bfrtip',
				          buttons: [
				        	  {
				                  extend: 'excelHtml5',
				                  title: 'Gst Sale Report for Retailer And Wholesale Customer'
				              },
				              {
				                  extend: 'pdfHtml5',
				                  title: 'Gst Sale Report for Retailer And Wholesale Customer'
				              },
				              {
				                  extend: 'csvHtml5',
				                  title: 'Gst Sale Report for Retailer And Wholesale Customer'
				              },
				              {
				                  extend: 'print',
				                  title: 'Gst Sale Report for Retailer And Wholesale Customer'
				              }
				              
				          ]
			} );

		} );

		var mydata = catmap;
		$('#RangewiseSaleGstReport').dataTable().fnAddData(mydata);
			}
	);
}


//Percentage Wise GST Sale Report
function getGstSaleReportPercentageWise(){

	var params= {};
	var startdate=$('#startDate1').val();
	var endDate=$('#enddate1').val();
	

	params["startdate"]= startdate;
	params["endDate"]= endDate;
	params["methodName"] = "getRangeAndPercentageWiseGstSaleReport";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		$('#RangewiseAndPercentageWiseSaleGstReport1').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
		 $('#RangewiseAndPercentageWiseSaleGstReport1').DataTable( {

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
					// Total over this page
					//cash total
					
					pageTotal = api
					.column( 2 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 2 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					
					pageTotal = api
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					pageTotal = api
					.column( 4)
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					

					//Card Total
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
						  {"data": "custname", "width": "5%"},
						  {"data": "gstno", "width": "5%"},
				          {"data": "fivePercent", "width": "5%"},
				          {"data": "twelvePercent", "width": "5%"},
				          {"data": "eighteenPercent" , "width": "5%"},
				          {"data": "twentyEightPercent", "width": "5%"},
				          {"data": "totalamount" , "width": "5%"}
				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Single Date Purchase Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ],
				                     
				                     "scrollY": "200px",
				                	  "scrollCollapse": true,
				                	  "paging": false,
			} );

		} );

		var mydata = catmap;
		$('#RangewiseAndPercentageWiseSaleGstReport1').dataTable().fnAddData(mydata);
			}
	);
}


function getRangeWiseGSTPurchaseReport(){

	var params= {};
	var startdate=$('#pstartDate').val();
	var endDate=$('#penddate').val();
	

	params["startdate"]= startdate;
	params["endDate"]= endDate;
	params["methodName"] = "getRangeWiseGstPurchaseReport";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		$('#RangewisePurchaseGstReport').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#RangewisePurchaseGstReport').DataTable( {

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
					// Total over this page
					//cash total
					
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					
					pageTotal = api
					.column( 2 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 2 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					pageTotal = api
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					

					//Card Total
					pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
				          {"data": "purchaseTaxable", "width": "5%"},
				          {"data": "taxableValue", "width": "5%"},
				          {"data": "cgstTotalValue" , "width": "5%"},
				          {"data": "sgstTotalValue", "width": "5%"},
				          {"data": "igstTotalValue", "width": "5%"},
				          {"data": "totalTaxAmount" , "width": "5%"}
				          
				          ],
				          dom: 'Bfrtip',
				          buttons: [
				        	  {
				                  extend: 'excelHtml5',
				                  title: 'GST Purchase Report'
				              },
				              {
				                  extend: 'pdfHtml5',
				                  title: 'GST Purchase Report'
				              },
				              {
				                  extend: 'csvHtml5',
				                  title: 'GST Purchase Report'
				              },
				              {
				                  extend: 'print',
				                  title: 'GST Purchase Report'
				              }
				              
				          ]
			} );

		} );

		var mydata = catmap;
		$('#RangewisePurchaseGstReport').dataTable().fnAddData(mydata);
			}
	);
}
