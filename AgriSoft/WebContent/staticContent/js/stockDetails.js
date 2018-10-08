function StockDetailsReportAsPerCompanyName(){
	var params= {};
	var input1 = document.getElementById('company_name'),
	list = document.getElementById('company_drop'),
	i,companyName;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			companyName = list.options[i].getAttribute('data-value');
		}
	}

	params["companyName"]= companyName;
	params["methodName"] = "StockDetailsReportAsPerCompanyName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#companyWiseTable').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#companyWiseTable').DataTable( {

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
*/
				},
				destroy: true,
				searching: false,
				columns: [
				          {"data": "product_name", "width": "5%"},
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "currentDate" , "width": "5%"},
				          {"data": "openingQty" , "width": "5%"},
				          {"data": "closingQty" , "width": "5%"},
				          {"data": "sale" , "width": "5%"},
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Company Name Wise Stock Report";
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
			$('#companyWiseTable').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Records Not Found");
		}
			}
	);
}

function StockDetailsReportAsPerCat(){
	var params= {};

/*	var input1 = document.getElementById('fk_cat_id'),
	list = document.getElementById('cat_drop'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}*/
	var fk_cat_id=$('#fk_cat_id').val();
	
	params["fk_cat_id"]= fk_cat_id;
	params["methodName"] = "getStockReportAsPerCategory";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#stockByCat').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		console.log(data);
		$(document).ready(function() {
			var total =   $('#stockByCat').DataTable( {

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
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "batchno", "width": "5%"},
				          {"data": "packing" , "width": "5%"},
				          {"data": "openingQty" , "width": "5%"},
				          {"data": "closingQty" , "width": "5%"},
				          {"data": "sale" , "width": "5%"},
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
			$('#stockByCat').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Data is not Available");
		}
			}
	);
}


function StockDetailsReportAsPerGodown(){
	var params= {};
	var input1 = document.getElementById('fk_godown_id'),
	list = document.getElementById('godown_drop'),
	i,fk_godown_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_godown_id = list.options[i].getAttribute('data-value');
		}
	}

	params["fkGodownId"]= fk_godown_id;
	params["methodName"] = "getStockReportAsPerGodown";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#stockByGodown').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#stockByGodown').DataTable( {

				fnRowCallback : function(nRow, aData, iDisplayIndex){
					$("th:first", nRow).html(iDisplayIndex +1);
					return nRow;
				},
				destroy: true,
				searching: false,
				columns: [
				          {"data": "categoryName" , "width": "5%"},
				          {"data": "productName", "width": "5%"},
				          {"data": "stock", "width": "5%"},
				          {"data": "batchNo", "width": "5%"},
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Supplier Wise Purchase Return Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ],
				
				                     "scrollY": "200px",
				                     "scrollCollapse": true,
				                     "paging": false,
			} );
		} );s
		var mydata = catmap;
		$('#stockByGodown').dataTable().fnAddData(mydata);

			}
	);
}


/*** +++ Fetching product Name+++ *****/
function getProductName(){

	$("#proName").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};
	params["methodName"] = "getAllProductByCategoriesForStockReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#proName").append($("<option></option>").attr("value",count).text(v.productName)); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}


function StockDetailsReportAsPerProductName(){
	var params= {};
	$("#proName option:selected").each(function() {
		selectedVal = $(this).text();
	});
	var splitText = selectedVal.split(",");
	var proName = splitText[0];
	var company = splitText[1];
	var weight = splitText[2];

	params["proName"]= proName;
	params["company"]= company;
	params["weight"]= weight;
	params["methodName"] = "StockDetailsReportAsPerProductName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#productTable').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#productTable').DataTable( {

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
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);*/
				},

				destroy: true,
				searching: false,

				columns: [
				          {"data": "product_name", "width": "5%"},
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "batchno", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "openingQty", "width": "5%"},
				          {"data": "closingQty" , "width": "5%"},
				          {"data": "sale" , "width": "5%"},
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Product Name Wise Stock Report";
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
		var check=mydata.length;
		if(check!=0)
		{
			$('#productTable').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Stock Report is Not Available for Selected Product");
		}

			}
	);
}


function StockReportBasedOnOnlyAvailableStockProductNameWise(){
	
	var params= {};
	var proName=$('#productname').val();
	params["proName"]= proName;
	
	params["methodName"] = "StockDetailsReportOnAvailableStockAsPerProductName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#productNameWiseAvailableQty').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#productNameWiseAvailableQty').DataTable( {

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
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);*/
				},

				destroy: true,
				searching: false,

				columns: [
				          {"data": "product_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "batchno", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Product Name Wise Available Stock Report";
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
		var check=mydata.length;
		if(check!=0)
		{
			$('#productNameWiseAvailableQty').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Stock Report is Not Available for Selected Product");
		}

			}
	);
}

function StockReportBasedOnOnlyAvailableStockCategoryWise(){
	
	var params= {};
	var category=$('#category').val();
	params["category"]= category;
	
	params["methodName"] = "StockDetailsReportOnAvailableStockAsPerCategory";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#categoryWiseAvailableQty').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#categoryWiseAvailableQty').DataTable( {

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
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);*/
				},

				destroy: true,
				searching: false,

				columns: [
				          {"data": "product_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "batchno", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Category Wise Available Stock Report";
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
		var check=mydata.length;
		if(check!=0)
		{
			$('#categoryWiseAvailableQty').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Stock Report is Not Available for Selected Category");
		}

			}
	);
}


/*** +++ Fetching product Name+++ *****/
function getProductNameForStockReport(){

	$("#proName").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};
	params["methodName"] = "getAllProductByCategoriesForStockReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#proName").append($("<option></option>").attr("value",count).text(v.product + ","+v.manufacturer+","+v.weight+","+v.unitName)); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function dateWiseStock(){
	var params= {};
	var firstDate = $("#firstDate").val();

	params["firstDate"]= firstDate;
	params["methodName"] = "StockDetailsReportAsPerDate";					
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#dateWiseStockTable').dataTable().fnClearTable();
		var  jsonData= $.parseJSON(data);
		var catmap = jsonData.list; 
		$(document).ready(function() {
			var total =   $('#dateWiseStockTable').DataTable( {

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
				          {"data": "supplier_name", "width": "5%"},
				          {"data": "company_name", "width": "5%"},
				          {"data": "packing", "width": "5%"},
				          {"data": "openingQty" , "width": "5%"},
				          {"data": "closingQty" , "width": "5%"},
				          {"data": "sale" , "width": "5%"},
				          
				          ],
				          
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Date Wise Stock Report";
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
		var check=mydata.length;
		if(check!=0)
		{
			$('#dateWiseStockTable').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Records Not found for Given Date");
		}
			}
	);

}