/*$(document).ready(function() {
	$('#billdate').datepicker();
    $('#billdate').datepicker('setDate', 'today');
});

$(document).ready(function() {
	$('#billdate1').datepicker();
    $('#billdate1').datepicker('setDate', 'today');
});*/


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && charCode != 46 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}


//get Product detail as Per barcode for cash customer
function getitemData(){ 
	var value = document.getElementById("key").value;

	var params= {};
	var count=0;
	var newrow;
	var rowId;

	params["methodName"] ="fetchCust";
	params["key"]=value;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			function sumFmatter (cellvalue, options, rowObject)
			{

				var tax = options.rowData.vatPercentage;

				if(tax == 0){
					var tot= (options.rowData.quantity * options.rowData.salePrice);
					if(isNaN(tot)){
						tot = 0;
					}
				}
				if(tax != 0){

					var taxcalculation = (tax/100)* Number(options.rowData.salePrice);
					var newSalePrice = Number(taxcalculation) + Number(options.rowData.salePrice)
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;

				count = jQuery("#list4").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#list4').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var salePrice = allRowsInGrid1[i].salePrice;
					params["salePrice"+i] = salePrice;

					var vatPercentage = allRowsInGrid1[i].vatPercentage;
					params["vatPercentage"+i] = vatPercentage;

					if(vatPercentage == 0){

						var totals1=(salePrice)*(quantity);
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}

					if(vatPercentage != 0){

						var taxcal = (vatPercentage/100) * salePrice;
						var newSalePrice = Number(salePrice) + Number(taxcal);
						var totals1=(Number(newSalePrice)*Number(quantity));
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}                	
				}

				document.getElementById("totalWithExpense").value = jam;
				return tot;
			}

			count = jQuery("#list4").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#list4").jqGrid('getGridParam','data');
			var ids = jQuery("#list4").jqGrid('getDataIDs');

			var prodName,com,packing,unit;
			for (var j = 0; j < count; j++) 
			{
				prodName = rowdata[j].itemName;
				com = rowdata[j].companyName;
				packing = rowdata[j].weight;
				unit = rowdata[j].unitName;

				var rowId = ids[j];
				var rowData = jQuery('#list4').jqGrid ('getRowData', rowId);

				if (prodName == jsonData.offer.itemName && com == jsonData.offer.companyName && packing == jsonData.offer.weight && unit == jsonData.offer.unitName) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#list4");
					grid.trigger("reloadGrid");
					break;
				}
				else
				{
					newrow = true;
				}
			}

			if(newrow == true)
			{
				$("#list4").addRowData(count,jsonData.offer);
			}

			
			
			$("#list4").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Unit', 'UnitPrice','MRP','CGST','SGST','IGST','Quantity','Total'],
				colModel:[ 
				          {
				        	  name:'cat_id',
				        	  hidden:true,
				          },


				          {	name:'itemName',
				        	  width:150,

				          },

				          {	name:'companyName',
				        	  width:150,

				          },
				          {	name:'hsn',
				        	  width:150,

				          },


				          {	name:'weight',
				        	  width:100,


				          },
				          {	name:'unitName',
				        	  width:100,
				          },
				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true

				          },
				          {	name:'mrp',
				        	  width:140,
				        	  editable: true

				          },

				          {	name:'cGst',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'sGst',
				        	  width:70,
				        	  editable: true

				          },

				          {	name:'iGst',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'quantity',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'total',
				        	  width:150,
				          },
				          ],
				          sortorder : 'desc',
				          loadonce: false,
				          viewrecords: true,
				          width: 1200,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function  grossTotal() {
				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#list4").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#list4").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];

				        	  
				        	  var tota = quantity * salePrice;
				        	  $("#list4").jqGrid("setCell", rowId, "total", tota);

				        	  var Total =0;
				        	  var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#list4').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  document.getElementById("totalWithExpense").value = Total;
				        	  document.getElementById("grossTotal").value = Total;
				          },
				          pager: "#jqGridPager",
			});

			if(count==0 || count==null)
			{
				$("#list4").addRowData(0,jsonData.offer);
			}

			
			
			$('#list4').navGrid('#jqGridPager',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",

						afterSubmit: function () {

							var grid = $("#list4"),
							intervalId = setInterval(
									function() {
										grid.trigger("reloadGrid",[{current:true}]);
									},
									500);
						},

						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfterEdit: true,

						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterAdd: true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterdel:true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,

						reloadAftersubmit:true,	
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					});
				});
			})
}

//get Product detail as per Barcode for credit customer
function getProDetailsAsPerBarcode(){

	var value = document.getElementById("barcode1").value;
	var params= {};
	var count=0;
	var newrow;
	var rowId;

	params["methodName"] ="fetchCust";
	params["key"]=value;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);

		$.each(jsonData,function(i,v)
				{
			function sumFmatter (cellvalue, options, rowObject)
			{
				var tax = options.rowData.vatPercentage;

				if(tax == 0){
					var tot= (options.rowData.quantity * options.rowData.salePrice);
					if(isNaN(tot)){
						tot = 0;
					}
				}
				if(tax != 0){

					var taxcalculation = (tax/100)* Number(options.rowData.salePrice);
					var newSalePrice = Number(taxcalculation) + Number(options.rowData.salePrice);
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;

				var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#credit').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var salePrice = allRowsInGrid1[i].salePrice;
					params["salePrice"+i] = salePrice;

					var vatPercentage = allRowsInGrid1[i].vatPercentage;
					params["vatPercentage"+i] = vatPercentage;

					if(vatPercentage == 0){

						var totals1=(salePrice)*(quantity);
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}

					if(vatPercentage != 0){

						var taxcal = (vatPercentage/100) *Number(salePrice);
						var newSalePrice = Number(salePrice) + Number(taxcal);
						var totals1=(Number(newSalePrice)*Number(quantity));
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}                	
				}

				document.getElementById("totalWithExpense1").value = jam;
				return tot;
			}

			count = jQuery("#credit").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit").jqGrid('getGridParam','data');
			var ids = jQuery("#credit").jqGrid('getDataIDs');

			var prodName,com,packing,unit;
			for (var j = 0; j < count; j++) 
			{
				prodName = rowdata[j].itemName;
				com = rowdata[j].companyName;
				packing = rowdata[j].weight;
				unit = rowdata[j].unitName;

				var rowId = ids[j];
				var rowData = jQuery('#credit').jqGrid ('getRowData', rowId);

				if (prodName == jsonData.offer.itemName && com == jsonData.offer.companyName && packing == jsonData.offer.weight && unit == jsonData.offer.unitName) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#list4");
					grid.trigger("reloadGrid");
					break;
				}
				else
				{
					newrow = true;
				}
			}

			if(newrow == true)
			{
				$("#credit").addRowData(count,jsonData.offer);
			}

			$("#credit").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Unit','UnitPrice','MRP','CGST','SGST','IGST','Quantity','Total'],
				colModel:[ 
				          {
				        	  name:'cat_id',
				        	  hidden:true,
				          },


				          {	name:'itemName',
				        	  width:150,

				          },

				          {	name:'companyName',
				        	  width:150,

				          },
				          {	name:'hsn',
				        	  width:150,

				          },      

				          {	name:'weight',
				        	  width:100,

				          },
				          {	name:'unitName',
				        	  width:100,
				          },
				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true

				          },
				          {	name:'mrp',
				        	  width:140,
				        	  editable: true

				          },
				          {	name:'cGst',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'sGst',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'iGst',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'quantity',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'total',
				        	  width:150,
				          },
				          ],
				          sortorder : 'desc',
				          loadonce: false,
				          viewrecords: true,
				          width: 1200,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function  grossTotal() {
				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#credit").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var tota = quantity * salePrice;
				        	  $("#credit").jqGrid("setCell", rowId, "total", tota);
				        	  var Total =0;
				        	  var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#credit').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  document.getElementById("totalWithExpense1").value = Total;
				        	  document.getElementById("grossTotal1").value = Total;
				          },

				          pager: "#jqGridPager",
			});

			if(count==0 || count==null)
			{
				$("#credit").addRowData(0,jsonData.offer);
			}

			$('#credit').navGrid('#jqGridPager',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",
						afterSubmit: function () {

							var grid = $("#credit"),
							intervalId = setInterval(
									function() {
										grid.trigger("reloadGrid",[{current:true}]);
									},
									500);
						},

						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfterEdit: true,

						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},
					{
						closeAfterAdd: true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},
					{
						closeAfterdel:true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,

						reloadAftersubmit:true,	
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}

					});

				});

			})
}

/*+++++++++++++  Fetcing Data from goods receive by product name for cash customer 20-5-17++++++++++++*/
function fetchDataByProductName(){
	var params= {};
	productId = $('#proName').val();

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
	params["methodName"] = "fetchDetailsAsPerProductNameInFertiBill";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		var grid = jQuery("#list4");
		grid.trigger("reloadGrid");
		$.each(jsonData,function(i,v)
				{
			function sumFmatter (cellvalue, options, rowObject)
			{
				var tax = options.rowData.vatPercentage;

				if(tax == 0){
					var tot= (options.rowData.quantity * options.rowData.salePrice);
				}
				if(tax != 0){

					var taxcalculation = (tax/100)* options.rowData.salePrice;
					var tot= (options.rowData.quantity * options.rowData.salePrice) + taxcalculation;
				}
				var jam=0;
				var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#list4').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var salePrice = allRowsInGrid1[i].salePrice;
					params["salePrice"+i] = salePrice;

					var vatPercentage = allRowsInGrid1[i].vatPercentage;
					params["vatPercentage"+i] = vatPercentage;

					if(vatPercentage == 0){

						var totals1=(salePrice)*(quantity);
						jam = jam + totals1;
					}

					if(vatPercentage != 0){

						var taxcal = (vatPercentage/100) * salePrice;
						var totals1=((salePrice)*(quantity)) + taxcal;
						jam = jam + totals1;
					}                	
				}
				document.getElementById("totalWithExpense").value = jam;
				return tot;
			}
			$("#list4").jqGrid({
				datatype: "local",

				colNames:['pk_goodre_id','supp_id','cat_id','BarcodeNO','ItemName','CompanyName','Packing', 'Quantity', 'UnitPrice','MRP','TaxPercentage' ,'Total'],
				colModel:[ 


				          {
				        	  name:'PkGoodreceiveId',
				        	  hidden:true,

				          },    
				          {
				        	  name:'supplier_id',
				        	  hidden:true,
				          },
				          {
				        	  name:'cat_id',
				        	  hidden:true,
				          },

				          {
				        	  name:'barcodeNo',
				        	  width:100,				    	

				          },
				          {	name:'itemName',
				        	  width:150,

				          },

				          {	name:'companyName',
				        	  width:150,

				          },


				          {	name:'weight',
				        	  width:100,
				        	  editable: true

				          },

				          {	name:'quantity',
				        	  width:100,
				        	  editable: true

				          },

				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true

				          },
				          {	name:'mrp',
				        	  width:140,
				        	  editable: true

				          },

				          {	name:'vatPercentage',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'total',
				        	  width:150,
				        	  formatter: sumFmatter
				          },
				          ],

				          sortorder : 'desc',
				          loadonce: false,
				          viewrecords: true,
				          width: 1200,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function () {
				        	  var rowId =$("#list4").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#list4").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var tota = quantity * salePrice;

				        	  $("#list4").jqGrid("setCell", rowId, "total", tota);
				          },

				          pager: "#jqGridPager",

			});

			$("#list4").addRowData(i+1,jsonData[i]);
			$('#list4').navGrid('#jqGridPager',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",

						afterSubmit: function () {

							var grid = $("#list4"),
							intervalId = setInterval(
									function() {
										grid.trigger("reloadGrid",[{current:true}]);
									},
									500);
						},

						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfterEdit: true,

						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterAdd: true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterdel:true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,
						reloadAftersubmit:true,	
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}

					});
				});
			})
}





/*++++++++++++++ Fetch product details by product name for CREDIT customer 22-5-17 ++++++++++++++++++++++++++*/
function getProductDetailsByProductNameForCredit(){

	var params= {};
	productId = $('#proName1').val();

	/*$("#proName1 option:selected").each(function() {
		selectedVal = $(this).text();
	});

	var splitText = selectedVal.split(",");
	var proName = splitText[0];
	var company = splitText[1];
	var weight = splitText[2];
	var stock = splitText[3];*/
	
	var proName = productId.split(",")[0];
	var company = productId.split(",")[1];
	var weight = productId.split(",")[2];
	var availableQty = productId.split(",")[4];

	params["proName"]= proName;
	params["company"]= company;
	params["weight"]= weight;
	params["availableQty"]= availableQty;

	
	var count=0;
	var newrow;
	var rowId;

	params["methodName"] = "getProductDetails1";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			function sumFmatter (cellvalue, options, rowObject)
			{
				var tax = options.rowData.vatPercentage;

				if(tax == 0){
					var tot= (options.rowData.quantity * options.rowData.salePrice);
					if(isNaN(tot)){
						tot = 0;
					}
				}
				if(tax != 0){
					var taxcalculation = (tax/100)* Number(options.rowData.salePrice);
					var newSalePrice = Number(taxcalculation) + Number(options.rowData.salePrice);
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;
				var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#credit').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var salePrice = allRowsInGrid1[i].salePrice;
					params["salePrice"+i] = salePrice;

					var vatPercentage = allRowsInGrid1[i].vatPercentage;
					params["vatPercentage"+i] = vatPercentage;

					if(vatPercentage == 0){

						var totals1=(salePrice)*(quantity);
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}

					if(vatPercentage != 0){

						var taxcal = (vatPercentage/100) *Number(salePrice);
						var newSalePrice = Number(salePrice) + Number(taxcal);
						var totals1=(Number(newSalePrice)*Number(quantity));
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}                	
				}
				document.getElementById("totalWithExpense1").value = jam;
				return tot;
			}

			count = jQuery("#credit").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit").jqGrid('getGridParam','data');
			var ids = jQuery("#credit").jqGrid('getDataIDs');
			var prodName,com,packing,unit;
			for (var j = 0; j < count; j++) 
			{
				prodName = rowdata[j].itemName;
				com = rowdata[j].companyName;
				packing = rowdata[j].weight;
				unit = rowdata[j].unitName;

				var rowId = ids[j];
				var rowData = jQuery('#credit').jqGrid ('getRowData', rowId);

				if (prodName == jsonData.offer.itemName && com == jsonData.offer.companyName && packing == jsonData.offer.weight && unit == jsonData.offer.unitName) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#list4");
					grid.trigger("reloadGrid");
					break;
				}
				else
				{
					newrow = true;
				}
			}

			if(newrow == true)
			{
				$("#credit").addRowData(count,jsonData.offer);
			}


			$("#credit").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Unit', 'UnitPrice','MRP','CGST','SGST','IGST','Quantity', 'Total','AvailableQty'],
				colModel:[ 
				          {
				        	  name:'cat_id',
				        	  hidden:true,
				          },


				          {	name:'itemName',
				        	  width:150,

				          },

				          {	name:'companyName',
				        	  width:150,

				          },
				          {	name:'hsn',
				        	  width:80,

				          },       

				          {	name:'weight',
				        	  width:100,

				          },
				          {	name:'unitName',
				        	  width:100,
				          },


				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0 && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'mrp',
				        	  width:140,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },

				          {	name:'cGst',
				        	  width:70,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'sGst',
				        	  width:70,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'iGst',
				        	  width:70,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'quantity',
				        	  width:100,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'total',
				        	  width:150,
				          },
				          
				          {	name:'availableQty',
				        	  width:150,
				        	  hidden:true
				          },



				          ],


				          sortorder : 'desc',
				          loadonce: false,
				          viewrecords: true,
				          width: 1200,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function  grossTotal() {
				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#credit").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var availableQty = rowData['availableQty'];
				        	  
				        	  	productId = $('#proName1').val();

				        		/*$("#proName1 option:selected").each(function() {
				        			selectedVal = $(this).text();
				        		});

				        		var splitText = selectedVal.split("=");
				        		var stock = splitText[1];
				        		*/
				        	  	
								//for select stock from dropdown
				        		/*productId = $('#proName1').val();
				        		var stock = productId.split(",")[4];*/
				        		
				        	  if( Number(quantity) > Number(availableQty))
				        	  {
				        		  $.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					      					{
					      		
					      				var msg="Qauntity Should be less than Equal to Stock"+availableQty;
					      				var dialog = bootbox.dialog({
					      					//title: "Embel Technologies Says :",
					      				    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
					      				    closeButton: false
					      				});
					      				
					      				setTimeout(function() {
					      					dialog.modal('hide');
					      				}, 1500);
					      				
					      				return false;
					      				
					      					});
				        		  
				        		  var total=0;
				        		  var quantity=0;
				        		  $("#credit").jqGrid("setCell", rowId, "quantity", quantity);
				        		  $("#credit").jqGrid("setCell", rowId, "total", total);
				        		  var tota = quantity * salePrice;
				        		  $("#credit").jqGrid("setCell", rowId, "total", tota);
				        	 

				        		  var Total =0;
				        		  var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				        		  var allRowsInGrid1 = $('#credit').getGridParam('data');
				        		  var AllRows=JSON.stringify(allRowsInGrid1);
				        		  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        		  }
				        		  document.getElementById("totalWithExpense1").value = Total;
				        		  document.getElementById("grossTotal1").value = Total;
				        		  return false;
				        		  
				        		  
				        	  }
				        	 
				        		  //document.getElementById("duptotal").value = Total;
				        		  var tota = quantity * salePrice;
				        		  $("#credit").jqGrid("setCell", rowId, "total", tota);
				        	 

				        	  var Total =0;
				        	  var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#credit').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  var zero=0;
				        	  document.getElementById("transExpence1").value = zero;
				        	  document.getElementById("hamaliExpence1").value = zero;
				        	  document.getElementById("totalWithExpense1").value = Total;
				        	  document.getElementById("grossTotal1").value = Total;

				          },
				          pager: "#jqGridPagerForCreditCustomer",
			});

			if(count==0 || count==null)
			{
				$("#credit").addRowData(0,jsonData.offer);
			}

			$('#credit').navGrid('#jqGridPagerForCreditCustomer',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",

						afterSubmit: function () {

							var grid = $("#credit"),
							intervalId = setInterval(
									function() {
										grid.trigger("reloadGrid",[{current:true}]);
									},
									500);
						},

						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfterEdit: true,

						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterAdd: true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterdel:true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,

						afterComplete: function() {
							$('#credit').trigger( 'reloadGrid' );
							

				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#credit").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];

				        	  //document.getElementById("duptotal").value = Total;

				        	  var tota = quantity * salePrice;
				        	  $("#credit").jqGrid("setCell", rowId, "total", tota);


				        	  var Total =0;
				        	  var count = jQuery("#credit").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#credit').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  document.getElementById("totalWithExpense1").value = Total;
				        	  document.getElementById("grossTotal1").value = Total;
				          
						},
						reloadAftersubmit:true,	
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					});
				});
			})
}

/*++++++++++++++ Fetch product details by product name for cash customer 22-5-17 ++++++++++++++++++++++++++*/
function getProductDetailsByProductName(){

	var params= {};
	productId = $('#proName').val();
	/*$("#proName option:selected").each(function() {
		selectedVal = $(this).text();
	});*/

	
	/*var splitText = selectedVal.split(",");
	var proName = splitText[0];
	var company = splitText[1];
	var weight = splitText[2];*/
	
	var proName = productId.split(",")[0];
	var company = productId.split(",")[1];
	var weight = productId.split(",")[2];
	var availableQty = productId.split(",")[4];

	params["proName"]= proName;
	params["company"]= company;
	params["weight"]= weight;
	params["availableQty"]= availableQty;

	var count=0;
	var newrow;
	var rowId;

	params["methodName"] = "getProductDetails1";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			function sumFmatter (cellvalue, options, rowObject)
			{
				var tax = options.rowData.vatPercentage;

				if(tax == 0){
					var tot= (options.rowData.quantity * options.rowData.salePrice);
					if(isNaN(tot)){
						tot = 0;
					}
				}
				if(tax != 0){

					var taxcalculation = (tax/100)* Number(options.rowData.salePrice);
					var newSalePrice = Number(taxcalculation) + Number(options.rowData.salePrice)
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;
				count = jQuery("#list4").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#list4').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var salePrice = allRowsInGrid1[i].salePrice;
					params["salePrice"+i] = salePrice;

					var vatPercentage = allRowsInGrid1[i].vatPercentage;
					params["vatPercentage"+i] = vatPercentage;

					if(vatPercentage == 0){

						var totals1=(salePrice)*(quantity);
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}

					if(vatPercentage != 0){

						var taxcal = (vatPercentage/100) * salePrice;
						var newSalePrice = Number(salePrice) + Number(taxcal);
						var totals1=(Number(newSalePrice)*Number(quantity));
						if(isNaN(totals1)){
							totals1 = 0;
						}
						jam = jam + totals1;
					}                	
				}
				document.getElementById("totalWithExpense").value = jam;
				return tot;
			}

			count = jQuery("#list4").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#list4").jqGrid('getGridParam','data');
			var ids = jQuery("#list4").jqGrid('getDataIDs');

			var prodName,com,packing,unit;
			for (var j = 0; j < count; j++) 
			{
				prodName = rowdata[j].itemName;
				com = rowdata[j].companyName;
				packing = rowdata[j].weight;
				unit = rowdata[j].unitName;

				var rowId = ids[j];
				var rowData = jQuery('#list4').jqGrid ('getRowData', rowId);

				if (prodName == jsonData.offer.itemName && com == jsonData.offer.companyName && packing == jsonData.offer.weight && unit == jsonData.offer.unitName) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#list4");
					grid.trigger("reloadGrid");
					break;
				}
				else
				{
					newrow = true;
				}
			}

			if(newrow == true)
			{

				$("#list4").addRowData(count,jsonData.offer);
			}


			$("#list4").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName',"HSN",'Packing','Unit', 'UnitPrice','MRP','CGST','SGST','IGST','Quantity','Total','AvalableQty'],
				colModel:[ 
				          {
				        	  name:'cat_id',
				        	  hidden:true,
				          },


				          {	name:'itemName',
				        	  width:150,

				          },

				          {	name:'companyName',
				        	  width:150,

				          },
				          {	name:'hsn',
				        	  width:80,


				          },      

				          {	name:'weight',
				        	  width:70,


				          },
				          {	name:'unitName',
				        	  width:70,


				          },


				          {	name:'salePrice',
				        	  width:120,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0 && e.which !=46 && (e.which < 48 || e.which > 57 )) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'mrp',
				        	  width:120,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which !=46 && (e.which < 48 || e.which > 57 )) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },

				          {	name:'cGst',
				        	  width:80,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which !=46 && (e.which < 48 || e.which > 57 )) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'sGst',
				        	  width:80,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which !=46 && (e.which < 48 || e.which > 57 )) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'iGst',
				        	  width:80,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which !=46 && (e.which < 48 || e.which > 57 )) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },
				          {	name:'quantity',
				        	  width:100,
				        	  editable: true,
				        	 //code to disable Character 
				        	 edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }
				        	 

				          },
				          {	name:'total',
				        	  width:150,
				        	  //formatter: sumFmatter
				          },
				          {	name:'availableQty',
				        	  width:150,
				        	  hidden:true
				        	  //formatter: sumFmatter
				          },
				          
				          ],


				          sortorder : 'desc',
				          loadonce: false,
				          viewrecords: true,
				          width: 1200,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,

				          afterSaveCell: function  grossTotal() {

				        	  var rowId =$("#list4").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#list4").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var availableQty = rowData['availableQty'];

				        	  
				        	  /*productId = $('#proName').val();

				        		$("#proName option:selected").each(function() {
				        			selectedVal = $(this).text();
				        		});
				        		
				        		var splitText = selectedVal.split("=");
				        		var stock = splitText[1];*/
				        		
				        	  
				        	  //for select stock from dropdown
				        		/*productId = $('#proName').val();
				        		var stock = productId.split(",")[4];*/

				        		
				        		
				        	  if( Number(quantity) > Number(availableQty))
				        	  {
				        		  $.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					      					{
					      		
					      				var msg="Qauntity Should be less than Equal to Stock"+availableQty;
					      				var dialog = bootbox.dialog({
					      					//title: "Embel Technologies Says :",
					      				    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
					      				    closeButton: false
					      				});
					      				
					      				setTimeout(function() {
					      					dialog.modal('hide');
					      				}, 1500);
					      				
					      				return false;
					      				
					      					});
				        		  
				        		  
				        		  var total=0;
				        		  var quantity=0;
				        		  $("#list4").jqGrid("setCell", rowId, "quantity", quantity);
				        		  $("#list4").jqGrid("setCell", rowId, "total", total);
				        	
				        		  var tota = quantity * salePrice;
				        		  $("#list4").jqGrid("setCell", rowId, "total", tota);

				        		  var Total =0;
				        		  var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				        		  var allRowsInGrid1 = $('#list4').getGridParam('data');
				        		  var AllRows=JSON.stringify(allRowsInGrid1);
				        		  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        		  }
				        		 
				        		  document.getElementById("totalWithExpense").value = Total;
				        		  document.getElementById("grossTotal").value = Total;
				        		
				        		  return false;
				        	  }
				        	 
				        	  
				        		  var tota = quantity * salePrice;
				        		  $("#list4").jqGrid("setCell", rowId, "total", tota);

				        		  var Total =0;
				        		  var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				        		  var allRowsInGrid1 = $('#list4').getGridParam('data');
				        		  var AllRows=JSON.stringify(allRowsInGrid1);
				        		  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        		  }
				        		  var zero=0;
				        		  document.getElementById("transExpence").value =zero;
				        		  document.getElementById("hamaliExpence").value =zero;
				        		  document.getElementById("totalWithExpense").value = Total;
				        		  document.getElementById("grossTotal").value = Total;
				        	  
				          },


				          pager: "#jqGridPager",



			});
			if(count==0 || count==null)
			{
				$("#list4").addRowData(0,jsonData.offer);
			}

			$('#list4').navGrid('#jqGridPager',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",

						afterSubmit: function () {

							var grid = $("#list4"),
							intervalId = setInterval(
									function() {
										grid.trigger("reloadGrid",[{current:true}]);
									},
									500);
						},

						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfterEdit: true,

						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterAdd: true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					},

					{
						closeAfterdel:true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,

						afterComplete: function() {
							$('#list4').trigger( 'reloadGrid' );



				        	  var rowId =$("#list4").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#list4").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];

				        	  var tota = quantity * salePrice;
				        	  $("#list4").jqGrid("setCell", rowId, "total", tota);

				        	  var Total =0;
				        	  var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#list4').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  document.getElementById("totalWithExpense").value = Total;
				        	  document.getElementById("grossTotal").value = Total;

						},
						reloadAftersubmit:true,	
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}
					});
				});
			})
}


/*************					Cash Customer Bill adding to fertilizerBilling table      ************************/
function fertilizerBill(){


	if ( document.fertiBill.customerName.value != "" )
	{
		if ( document.fertiBill.proName.value != "" )
		{
			if(document.fertiBill.contactNo.value!="")
			{
				var contact=/^[0-9]{10}$/;
				if(document.fertiBill.contactNo.value.match(contact))
				{
					fertilizerBilling();
				}
				else
				{
					alert("Enter 10 Digit Mobile Number");
				}
			}
			else
			{
				fertilizerBilling();
			}

		}
		else
		{
			alert("Please Select Product Name");
			return fa
		}
	}
	else
	{
		alert("Please Enter Customer Name");
		return false;
	}

}

function fertilizerBilling(){

	//document.fertiBill.btn.disabled = true;
	document.getElementById("btn").disabled = true;
	var params = {};
	var paycusttype="Cash";
	var customertype=$('#customertype1').val();
	var custtype=$('#custtype').val();
	var customerName = $('#customerName').val();
	var village = $('#village').val();
	var contactNo = $('#contactNo').val();
	var aadhar = $('#aadharNo').val();
	var billdate = $('#billdate').val();
	var transExpense = $('#transExpence').val();
	var hamaliExpense = $('#hamaliExpence').val();
	var grossTotal = $('#grossTotal').val();
	var total = $('#totalWithExpense').val();
	var paymentMode = $('#paymentMode').val();
	var chequeNum = $('#chequeNum').val();
	var nameOnCheck = $('#nameOnCheck').val();
	var cardNum = $('#cardNum').val();
	var accNum = $('#accNum').val();
	var bankName = $('#bankName').val();

	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#list4').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var cat_id = allRowsInGrid[i].cat_id;
		params["cat_id"+i] = cat_id;

		var itemName = allRowsInGrid[i].itemName;
		params["itemName"+i] = itemName;


		var companyName = allRowsInGrid[i].companyName;
		params["companyName"+i] = companyName;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;
		
		var unitName = allRowsInGrid[i].unitName;
		params["unitName"+i] = unitName;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var cGst = allRowsInGrid[i].cGst;
		if(cGst==undefined || cGst== null || cGst== 0 ){
			params["cGst"+i] = 0;
		}
		else if(cGst!=undefined || cGst!= null || cGst!= null ){
			params["cGst"+i] = cGst;
		}


		var sGst = allRowsInGrid[i].sGst;
		if(sGst==undefined || sGst== null || sGst == 0){
			params["sGst"+i] = 0;
		}
		else if(sGst!=undefined || sGst!= null || sGst != 0 ){
			params["sGst"+i] = sGst;
		}
		var iGst = allRowsInGrid[i].iGst;
		if(iGst==undefined || iGst== null || iGst == 0){
			params["iGst"+i] = 0;
		}
		else if(iGst!=undefined || iGst!= null || iGst != 0){
			params["iGst"+i] = iGst;
		}
		var hsn = allRowsInGrid[i].hsn;
		params["hsn"+i] = hsn;

		var total = allRowsInGrid[i].total;
		params["total"+i] = total;
	}
	if(transExpense==undefined || transExpense== null || transExpense == "" ){
		transExpense=0;
	}
	if(hamaliExpense==undefined || hamaliExpense== null || hamaliExpense == "" ){
		hamaliExpense=0;
	}

	if(customerName==undefined || customerName== null || customerName == "" ){
		customerName="'N/A'";
	}

	if(village==undefined || village== null || village == "" ){
		village="N/A";
	}
	if(aadhar==undefined || aadhar== null || aadhar == "" ){
		aadhar=0;
	}
	if(contactNo==undefined || contactNo== null || contactNo == "" ){
		contactNo=0;
	}

	
	params["paycusttype"] = paycusttype;
	params["customertype"] = customertype;
	params["customerName"] = customerName;
	params["count"] = count;
	params["village"] = village;
	params["contactNo"] = contactNo;
	params["aadhar"] = aadhar;			
	params["billdate"] = billdate;
	params["transExpense"] = transExpense;
	params["total"] = total;
	params["grossTotal"] = grossTotal;
	params["hamaliExpense"] = hamaliExpense;
	params["paymentMode"] = paymentMode;
	params["chequeNum"] = chequeNum;
	params["nameOnCheck"] = nameOnCheck;
	params["cardNum"] = cardNum;
	params["accNum"] = accNum;
	params["bankName"] = bankName;

	params["methodName"] = "addingCashFertilizerBill";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		window.open("FertilizerBillPDF.jsp");
		location.reload();
		document.getElementById("btn").disabled =false;

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});


}

/*************					Cash Customer Bill adding to fertilizerBilling table    28-5-17  ************************/
function fertilizerBill_28_5_17(){

	document.fertiBill.btn.disabled = true;
	var params = {};

	var customerName = $('#customerName').val();
	var village = $('#village').val();
	var contactNo = $('#contactNo').val();
	var aadhar = $('#aadharNo').val();
	var transExpense = $('#transExpence').val();
	var hamaliExpense = $('#hamaliExpence').val();
	var grossTotal = $('#grossTotal').val();
	var total = $('#totalWithExpense').val();
	var paymentMode = $('#paymentMode').val();
	var chequeNum = $('#chequeNum').val();
	var nameOnCheck = $('#nameOnCheck').val();
	var cardNum = $('#cardNum').val();
	var accNum = $('#accNum').val();
	var bankName = $('#bankName').val();

	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#list4').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var itemName = allRowsInGrid[i].itemName;
		params["itemName"+i] = itemName;

		var companyName = allRowsInGrid[i].companyName;
		params["companyName"+i] = companyName;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var vatPercentage = allRowsInGrid[i].vatPercentage;
		params["vatPercentage"+i] = vatPercentage;

		var total = allRowsInGrid[i].total;
		params["total"+i] = total;
	}

	params["customerName"] = customerName;
	params["count"] = count;
	params["village"] = village;
	params["contactNo"] = contactNo;
	params["aadhar"] = aadhar;
	params["transExpense"] = transExpense;
	params["total"] = total;
	params["grossTotal"] = grossTotal;
	params["hamaliExpense"] = hamaliExpense;
	params["paymentMode"] = paymentMode;
	params["chequeNum"] = chequeNum;
	params["nameOnCheck"] = nameOnCheck;
	params["cardNum"] = cardNum;
	params["accNum"] = accNum;
	params["bankName"] = bankName;

	params["methodName"] = "addingFertilizerBill_28_5_17";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		window.open("FertilizerBillPDF.jsp");
		location.reload();
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}



/*************					Credit Customer Bill adding to fertilizerBilling table      ************************/
function fertilizerBillForCreditCustomer(){
	
	
	if ( document.fertilizerform.customertype1.value == "Regular" )
	{
		if ( document.creditFertiBill1.creditCustomer.value != "" )
		{
			fertilizerBillForCredit();
		}
		else
		{
			alert("Please Select Customer Name");
			return false;
		}
		
	}
	else
	{
		if ( document.creditFertiBill1.creditwholsaleCustomer.value != "" )
		{
			fertilizerBillForCredit();
		}
		else
		{
			alert("Please Select Customer Name");
			return false;
		}
	}
	
	
	
	
}

function fertilizerBillForCredit(){
	//document.creditFertiBill1.btn2.disable= true ;
	document.getElementById("btn1").disabled = true;
	var params = {};

	var customertype=$('#customertype1').val();
	var custname=$('#creditCustomer').val();
	var creditwholsaleCustomer=$('#creditwholsaleCustomer').val();
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop1'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var paycusttype="Credit";
	var fkCreditCustomerID = creditCustomer;
	var creditCustomerName = $('#creditCustomer').val();
	var creditCustomerHiddenName = $('#customerNameHidden').val();
	var village = $('#village1').val();
	var contactNo = $('#contactNo1').val();
	var aadhar = $('#aadharNo1').val();
	var billdate = $('#billdate1').val();
	var transExpense = $('#transExpence1').val();
	var hamaliExpense = $('#hamaliExpence1').val();
	var grossTotal = $('#grossTotal1').val();
	var total = $('#totalWithExpense1').val();
	var count = jQuery("#credit").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#credit').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var cat_id = allRowsInGrid[i].cat_id;
		params["cat_id"+i] = cat_id;

		var itemName = allRowsInGrid[i].itemName;
		params["itemName"+i] = itemName;

		var companyName = allRowsInGrid[i].companyName;
		params["companyName"+i] = companyName;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;
		
		var unitName = allRowsInGrid[i].unitName;
		params["unitName"+i] = unitName;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var cGst = allRowsInGrid[i].cGst;
		if(cGst==undefined || cGst== null || cGst== 0 ){
			params["cGst"+i] = 0;
		}
		else if(cGst!=undefined || cGst!= null || cGst!= null ){
			params["cGst"+i] = cGst;
		}

		var sGst = allRowsInGrid[i].sGst;
		if(sGst==undefined || sGst== null || sGst == 0){
			params["sGst"+i] = 0;
		}
		else if(sGst!=undefined || sGst!= null || sGst != 0 ){
			params["sGst"+i] = sGst;
		}

		var iGst = allRowsInGrid[i].iGst;
		if(iGst==undefined || iGst== null || iGst == 0){
			params["iGst"+i] = 0;
		}
		else if(iGst!=undefined || iGst!= null || iGst != 0){
			params["iGst"+i] = iGst;
		}
		var hsn = allRowsInGrid[i].hsn;
		params["hsn"+i] = hsn;

		var total = allRowsInGrid[i].total;
		params["total"+i] = total;
	}
	if(transExpense==undefined || transExpense== null || transExpense == "" ){
		transExpense=0;
	}
	if(hamaliExpense==undefined || hamaliExpense== null || hamaliExpense == "" ){
		hamaliExpense=0;
	}
	if(village==undefined || village== null || village == "" ){
		village="N/A";
	}
	if(aadhar==undefined || aadhar== null || aadhar == "" ){
		aadhar=0;
	}
	if(contactNo==undefined || contactNo== null || contactNo == "" ){
		contactNo=0;
	}
	
	
	if(creditCustomerName==null || creditCustomerName=="" || creditCustomerName =="undefined")
	{
		params["creditCustomerName"] = creditwholsaleCustomer;
	}
	
	if(creditwholsaleCustomer==null || creditwholsaleCustomer=="" || creditwholsaleCustomer =="undefined")
	{
	params["creditCustomerName"] = custname;
	}
	
	
	params["paycusttype"] = paycusttype;
	params["customertype"] = customertype;
	params["creditCustomerHiddenName"] = creditCustomerHiddenName;
	params["fkCreditCustomerID"] = fkCreditCustomerID;
	params["count"] = count;
	params["village"] = village;
	params["contactNo"] = contactNo;
	params["aadhar"] = aadhar;		
	params["billdate"] = billdate;
	params["transExpense"] = transExpense;
	params["total"] = total;
	params["grossTotal"] = grossTotal;
	params["hamaliExpense"] = hamaliExpense;
	//params["creditCustomerName"] = creditCustomerName;

	params["methodName"] = "addingFertilizerBill";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		window.open("FertilizerBillPDF.jsp");
		location.reload();
		document.getElementById("btn1").disabled = false;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

/*** +++ Fetching product Name FOR CASH CUSTOMER+++ *****/
function getProductName(){

	var fk_cat_id = 1;
	$("#proName").empty();
	$("#fert_pro_drop").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["methodName"] = "getAllProductByCategoriesForFertiBill";

	params["fk_cat_id"]= fk_cat_id;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.companyname+","+v.packing+",Stock="+v.quantity))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

/*** +++ Fetching product Name FOR CREDIT CUSTOMER+++ *****/
function getProductNameForCredit(){

	var fk_cat_id = 1;
	$("#proName1").empty();
	$("#proName1").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["methodName"] = "getAllProductByCategoriesForFertiBill";

	params["fk_cat_id"]= fk_cat_id;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#proName1").append($("<option></option>").attr("value",count).text(v.product_name + ","+v.companyname+","+v.packing+",Stock="+v.quantity)); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}



/* ++++++++++++++ Get credit customer details ++++*/
function customerDetail(){

	this.getVillageName = getVillageName;
	this.getContactNo = getContactNo;
	this.getName = getName;
	this.getAadhar = getAadhar;

	function getVillageName()
	{

		var input = document.getElementById('creditCustomer'),
		list = document.getElementById('cust_drop1'),
		i,creditCustomer;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				creditCustomer = list.options[i].getAttribute('data-value');
			}
		}

		var creditCustomerId = creditCustomer;
		$("#village1").empty();
		$("#village1").append($("<input/>").attr("value","").text());
		var params= {};
		params["methodName"] = "getVillageNameAndContactNoAndFirstNameByCustomer";
		params["creditCustomerId"]= creditCustomerId;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				document.getElementById("village1").value = v.village;
					});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}

	function getAadhar(){


		var input = document.getElementById('creditCustomer'),
		list = document.getElementById('cust_drop1'),
		i,creditCustomer;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				creditCustomer = list.options[i].getAttribute('data-value');
			}
		}

		var creditCustomerId = creditCustomer;
		$("#aadharNo1").empty();
		$("#aadharNo1").append($("<input/>").attr("value","").text());
		var params= {};
		params["methodName"] = "getVillageNameAndContactNoAndFirstNameByCustomer";
		params["creditCustomerId"]= creditCustomerId;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				document.getElementById("aadharNo1").value = v.aadhar;

					});

				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}

	function getContactNo()
	{
		var input = document.getElementById('creditCustomer'),
		list = document.getElementById('cust_drop1'),
		i,creditCustomer;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				creditCustomer = list.options[i].getAttribute('data-value');
			}
		}

		var creditCustomerId = creditCustomer;
		$("#contactNo1").empty();
		$("#contactNo1").append($("<input/>").attr("value","").text());
		var params= {};
		params["methodName"] = "getVillageNameAndContactNoAndFirstNameByCustomer";
		params["creditCustomerId"]= creditCustomerId;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				document.getElementById("contactNo1").value = v.contactNo;
					});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}

	function getName()
	{

		var input = document.getElementById('creditCustomer'),
		list = document.getElementById('cust_drop1'),
		i,creditCustomer;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				creditCustomer = list.options[i].getAttribute('data-value');
			}
		}

		var creditCustomerId = creditCustomer;
		$("#customerNameHidden").empty();
		$("#customerNameHidden").append($("<input/>").attr("value","").text());
		var params= {};
		params["methodName"] = "getVillageNameAndContactNoAndFirstNameByCustomer";
		params["creditCustomerId"]= creditCustomerId;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				document.getElementById("customerNameHidden").value = v.firstName;
					});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}
}

var custDetail = new customerDetail();


//fetch product godown name wise for Cash
function fetchProductGodownWise(){


	var godownName=$('#godown').val();
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getFertilizerAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.companyname+","+v.packing+",Stock=,"+v.quantity))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}


//fetch product godown name wise for credit
function fetchProductGodownWiseforCredit(){


	var godownName=$('#godown1').val();
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getFertilizerAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.companyname+","+v.packing+",Stock=,"+v.quantity))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

//fetch product Against Default Godown
function fetchFertilizerProductAgainstDefaultGodown(){


	var godownName="Default";
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getFertilizerAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.companyname+","+v.packing+",Stock=,"+v.quantity))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}