
function getAllBills()
{
	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,supplier;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			supplier = list.options[i].getAttribute('data-value');
		}
	}

	var supplier = supplier;
	$("#bill_no").empty();
	$("#bill_no").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};

	params["methodName"] = "getAllBillBySuppliers";

	params["supplier"]= supplier;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#bill_no").append($("<option></option>").attr("value",i).text(v.billNo)); 

				});
			})
}

function fetchDataForPurchase(){

	var bill_no = $('#bill_no').val();

	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,supplier;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			supplier = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params["methodName"] = "getAllIetmByBillNo";

	params["bill_no"]= bill_no;
	params["supplier"]= supplier;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ 
		var jsonData = $.parseJSON(data);

		$("#jqGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
		$.each(jsonData,function(i,v)
				{
			$("#jqGrid").jqGrid({

				datatype:"local",

				colNames: ["Product ID","Supplier","Product Name","Category","Company","Batch No","Packing","Buy Price","Sale Price","M.R.P","Tax per","Quantity","Return Quantity","Dc No","Barcode No","PurchaseDate","DuplicateQuant" ],

				colModel: [
				           { 	
				        	   name: "pk_goods_receive_id",
				        	   hidden:true
				           },
				           { 	
				        	   name: "supplier_name",
				        	   width:100,
				           },
				           { 	
				        	   name: "product_name",
				        	   width:100,
				           },
				           { 	
				        	   name: "catName",
				        	   width:100,
				           },
				           {
				        	   name: "company_Name",   
				        	   width: 100
				           },
				           {
				        	   name: "batch_no",
				        	   width: 70
				           },	
				           {
				        	   name:  "weight",
				        	   width: 70
				           },
				           {
				        	   name: "buy_price",
				        	   width: 100,
				           },
				           {
				        	   name: "sale_price",
				        	   width: 100
				           },
				           {
				        	   name : 'mrp',
				        	   width: 100
				           },
				           {
				        	   name: "tax_percentage",
				        	   width: 50
				           },
				           {
				        	   name: "quantity",
				        	   width: 70
				           },
				           {
				        	   name: "dupQuantity",
				        	   width: 70,
				        	   editable:true
				           },
				           {
				        	   name: "dc_number",
				        	   width: 70
				           },
				           {
				        	   name: "barcodeNo",
				        	   width: 80
				           },
				           {
				        	   name: "strDate",
				        	   width: 140
				           }
				           ,
				           {
				        	   name: "dupQuantity1",
				        	   hidden: true
				           }
				           ],


				           sortorder : 'desc',

				           multiselect: false,	
				           loadonce: false,
				           rownumbers:true,
				           forcePlaceholderSize: true ,
				           'cellEdit':true,
				           viewrecords: true,
				           width: 1400,
				           shrinkToFit : true,
				           rowNum: 10,
				           pager: "#jqGridPager",
				           sortorder: "desc",


				           afterSaveCell: function  grossTotal() {
				        	   /* 	Calculation of total after editing quantity*/

				        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
				        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
				        	   var stock = rowData['quantity'];
				        	   var returning = rowData['dupQuantity'];
				        	   var quantity=0;
				        	   if ( Number(returning) > Number(stock)){

				        		   alert("Available stock = "+stock);
				        		   $("#jqGrid").jqGrid("setCell", rowId, "dupQuantity", quantity);
				        	   }
				           }

			});

			$("#jqGrid").addRowData(i,jsonData[i]);

			$('#jqGrid').navGrid('#jqGridPager',
					// the buttons to appear on the toolbar of the grid
					{edit: true, add: false,del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
					// options for the Edit Dialog

					{
						afterSubmit: function () {
							$(this).trigger('reloadGrid');
						},
						editCaption: "The Edit Dialog",
						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfteredit: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						}

					},
					{},
					// options for the Delete Dialogue
					{    
						afterSubmit: function () {
							$(this).trigger('reloadGrid');
						},
						closeAfterdel:true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						},

						onSelectRow: function(id) {
							if (id && id !== lastSel) {
								jQuery("#jqGrid").saveRow(lastSel, true, 'clientArray');
								jQuery("#jqGrid").editRow(id, true);

								lastSel = id;
								console.log(id);
							}
						}
					});

			// grid refresh code	

				});

			}); 

}

function returntPurchaseValidation()
{
	if ( document.purchasereturn.supplier.value != "" )
	{
		if ( document.purchasereturn.bill_no.value != "" )
		{
		}
		else
		{
			alert("Please Select Bill Number");
			return false;
		}
	}
	else
	{
		alert("Please Select Supplier");
		return false;
	}
}

function returntPurchase(){
	var params={};

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pk_goods_receive_id = allRowsInGrid[i].pk_goods_receive_id;
		params["pk_goods_receive_id"+i] = pk_goods_receive_id;

		var dupQuantity = allRowsInGrid[i].dupQuantity;
		params["dupQuantity"+i] = dupQuantity;

		if(dupQuantity == undefined){
			alert("Please Enter Quantity")
			Break;
		}										

		var product_name = allRowsInGrid[i].product_name;
		params["product_name"+i] = product_name;

		var company_Name = allRowsInGrid[i].company_Name;
		params["company_Name"+i] = company_Name;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var tax_percentage = allRowsInGrid[i].tax_percentage;
		params["tax_percentage"+i] = tax_percentage;

		var buy_price = allRowsInGrid[i].buy_price;
		params["buy_price"+i] = buy_price;
	}

	params["count"] = count;
	params["methodName"] = "returntPurchase";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function returntMinusFromStockPurchase(){
	var params={};

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pk_goods_receive_id = allRowsInGrid[i].pk_goods_receive_id;
		params["pk_goods_receive_id"+i] = pk_goods_receive_id;

		var dupQuantity = allRowsInGrid[i].dupQuantity;
		params["dupQuantity"+i] = dupQuantity;

		var product_name = allRowsInGrid[i].product_name;
		params["product_name"+i] = product_name;

		var company_Name = allRowsInGrid[i].company_Name;
		params["company_Name"+i] = company_Name;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;
	}

	params["count"] = count;
	params["methodName"] = "returntMinusFromStockPurchase";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		location.reload();

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function registerPurchaseReturn(){

	var params={};

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pk_goods_receive_id = allRowsInGrid[i].pk_goods_receive_id;
		params["pk_goods_receive_id"+i] = pk_goods_receive_id;

		var dupQuantity = allRowsInGrid[i].dupQuantity;
		params["dupQuantity"+i] = dupQuantity;

		var product_name = allRowsInGrid[i].product_name;
		params["product_name"+i] = product_name;

		var company_Name = allRowsInGrid[i].company_Name;
		params["company_Name"+i] = company_Name;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

		var duplicateQuantity = allRowsInGrid[i].dupQuantity1;
		params["duplicateQuantity"+i] = duplicateQuantity;

	}

	params["count"] = count;
	params["methodName"] = "returntMinusFromStockPurchase";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		location.reload();

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function purchaseReturnVlidation()
{
	
	if(document.purchasereturn.supplier.value != "")
	{
		if(document.purchasereturn.bill_no.value != "")
		{
			purchaseReturnTable();
		}
		else
		{
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
			
					var msg="select Bill No";
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
		}
	}
	else
	{
		
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
		
				var msg="select supplier Name";
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
	}

}

function purchaseReturnTable(){
	document.getElementById("btn").disabled = true;
	var params={};

	var bill_no = $('#bill_no').val();
	params["bill_no"] = bill_no;

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pk_goods_receive_id = allRowsInGrid[i].pk_goods_receive_id;	
		params["pk_goods_receive_id"+i] = pk_goods_receive_id;

		var supplier_name = allRowsInGrid[i].supplier_name;		
		params["supplier_name"+i] = supplier_name;

		var product_name = allRowsInGrid[i].product_name;	
		params["product_name"+i] = product_name;


		var catName = allRowsInGrid[i].catName;
		params["category"+i] = catName;

		var company_Name = allRowsInGrid[i].company_Name;
		params["company_Name"+i] = company_Name;

		var batch_no = allRowsInGrid[i].batch_no;
		if(batch_no==""|batch_no==null |batch_no=="undefined")
		{
			batch_no="0"
		}
		params["batch_no"+i] = batch_no;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;
		
		var buy_price = allRowsInGrid[i].buy_price;
		params["buy_price"+i] = buy_price;

		var sale_price = allRowsInGrid[i].sale_price;
		params["sale_price"+i] = sale_price;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var tax_percentage = allRowsInGrid[i].tax_percentage;		
		params["tax_percentage"+i] = tax_percentage;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		var dupQuantity = allRowsInGrid[i].dupQuantity;
		params["dupQuantity"+i] = dupQuantity;

		var dc_number = allRowsInGrid[i].dc_number;
		params["dc_number"+i] = dc_number;

		var barcodeNo = allRowsInGrid[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;

		var strDate = allRowsInGrid[i].strDate;
		params["strDate"+i] = strDate;

		var dupQuantity1 = allRowsInGrid[i].dupQuantity1;	
		params["dupQuantity1"+i] = dupQuantity1;
	}

	params["count"] = count;
	params["methodName"] = "purchaseretun";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg=data;
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s2.gif" height="50" width="50"/></p>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
				location.reload();
				document.getElementById("btn").disabled = true;
			}, 1500);
			
			return false;
			
				});
		

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}