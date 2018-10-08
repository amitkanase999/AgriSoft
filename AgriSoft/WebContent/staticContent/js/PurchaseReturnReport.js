function supplierWisePurchaseReport(){

	var params= {};
	var supplier=$('#fkSupplierId').val();
	params["supplier"]= supplier;

	params["methodName"] = "getPurchaseReturnDetailsAsPerSupplier";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#purchase3').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#purchase3').DataTable( {


				fnRowCallback : function(nRow, aData, iDisplayIndex){
					$("th:first", nRow).html(iDisplayIndex +1);
					return nRow;
				},
				destroy: true,
				searching: false,
				columns: [
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "bill_no", "width": "5%"},
				          {"data": "product_name", "width": "5%"},
				          {"data": "category", "width": "5%"},
				          {"data": "batch_no" , "width": "5%"},
				          {"data": "packing" , "width": "5%"},
				          {"data": "buy_price" , "width": "5%"},
				          {"data": "sale_price" , "width": "5%"},
				          {"data": "MRP", "width": "5%"},
				          {"data": "tax_per" , "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          {"data": "retun_quantity" , "width": "5%"},
				          {"data": "dc_no", "width": "5%"},
				          {"data": "barcode_no" , "width": "5%"},
				          {"data": "purchase_date" , "width": "5%"},
				          {"data": "return_date" , "width": "5%"}
				          ],

				          dom: 'Bfrtip',
				          buttons: [
				                    'copy', 'csv', 'excel', 'pdf', 'print'
				                    ]
			} );
		} );

		var mydata = catmap;
		$('#purchase3').dataTable().fnAddData(mydata);

			}
	);
}

function purchaseReturnReportBetweenTwoDate(){

	var params= {};
	var startDate = $("#fisDate2").val();
	var endDate = $("#endDate2").val();

	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getPurchaseReturnReportBetweenTwoDates";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#purchase2').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#purchase2').DataTable( {


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

					pageTotal = api
					.column( 12 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 12 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,


				columns: [
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "bill_no", "width": "5%"},
				          {"data": "product_name", "width": "5%"},
				          {"data": "category", "width": "5%"},
				          {"data": "batch_no" , "width": "5%"},
				          {"data": "packing" , "width": "5%"},
				          {"data": "buy_price" , "width": "5%"},
				          {"data": "sale_price" , "width": "5%"},
				          {"data": "MRP", "width": "5%"},
				          {"data": "tax_per" , "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          {"data": "retun_quantity" , "width": "5%"},
				          {"data": "dc_no", "width": "5%"},
				          {"data": "barcode_no" , "width": "5%"},
				          {"data": "purchase_date" , "width": "5%"},
				          {"data": "return_date" , "width": "5%"}
				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Range wise Purchase Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );
		var mydata = catmap;
		$('#purchase2').dataTable().fnAddData(mydata);

			}
	);


}	
