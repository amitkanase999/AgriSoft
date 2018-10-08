function StockDetailsReportAsPerCat(){
	var params= {};


	var godownName=$('#godownName').val();
	
	params["godownName"]= godownName;
	params["methodName"] = "getGodownWiseStock";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#godownstock').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		console.log(data);
		$(document).ready(function() {
			var total =   $('#godownstock').DataTable( {

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
					/*pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );
					// Update footer
					$( api.column( 4 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);*/

				},
				destroy: true,
				searching: false,
				columns: [
				          {"data": "product_name", "width": "5%"},
				          {"data": "product_category", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "batchno", "width": "5%"},
				          {"data": "expiryDate" , "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				         
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Category wise Stock  Report";
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
		check=mydata.length;
		if(check!=0)
		{
			$('#godownstock').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Data is not Available");
		}
			}
	);
}
