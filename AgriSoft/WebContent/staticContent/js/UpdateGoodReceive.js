//date calender editable
/*$(document).ready(function() {
	$('#purchaseDate').datepicker();
    $('#purchaseDate').datepicker('setDate', 'today');
});*/

//Code for Get product Details from Good receive inside the Grid
function getproductDetailInGrid()
{
	
	var params= {};
	itemparams={};

	
	var challanNo = $('#challanNo').val();


	itemparams["challanNo"]= challanNo;
	

	var count=0;
	var newrow;
	var rowId;
	var	taxPercentage;
	itemparams["methodName"] = "getProductDetailsForUpdateGoodsReceive";
	$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
			function(data)
			{ 
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{

			var productName =  v.productName;
			var  buyPrice =  v.buyPrice;  
			var salePrice =v.salePrice;
			var quantity = v.quantity;
			var weight = v.weight ;
			var productID = v.productID ;
			var manufacturer = v.manufacturer ;
			var lastsel = productID;
			
			

			$("#jqGrid").jqGrid({

				datatype:"local",

				colNames: [	"Cat ID","Product Name","Company","HSN","Buy Price","M.R.P","Sale Price","Packing","CGST %","SGST %","IGST %","Quantity","Total","BatchNo","ExpiryDate" ],

				colModel: [
				           { 	
				        	   name: "catId",
				        	   hidden:true


				           },
				           { 	
				        	   name: "productName",
				        	  // hidden:true


				           },
				           { 	
				        	   name: "companyName",
				        	   width:120,

				           },
				           { 	
				        	   name: "hsn",
				        	   width:120

				           },
				           { 	
				        	   name: "buyPrice",
				        	   width:120

				           },
				           {
				        	   name: "mrp",
				        	   width: 120,
				        	  // editable: true
				           },	

				           {
				        	   name: "salePrice",
				        	   width: 140,
				        	  // editable: true
				           },
				           {
				        	   name:  "packing",
				        	   width: 140,
				        	  // editable: true
				           },

				           {
				        	   name: "cGst",
				        	   width: 100,
				        	   editable: true

				           },
				           {
				        	   name: "sGst",
				        	   width: 80,
				        	   editable: true
				           },
				           {
				        	   name: "iGst",
				        	   width: 80,
				        	   editable: true
				           },
				           {
				        	   name: "quantity",
				        	   width: 80,
				        	   //editable: true
				           },
				           {
				        	   name: "total",
				        	   width: 120,
				        	   editable: true

				           },

				           {
				        	   name : 'batchNo',
				        	   width: 150,

				           },
				
				           {
				        	   label : 'expDate',
				        	   name: "expDate",
				        	   index:'Date',
				        	   width: 200,
				        	   editable:true,
				        	   edittype:"text",
				        	   editrules:{date:true, minValue:0}, datefmt:'Y-m-d',
				        	   editoptions:{dataInit: function (elem) {$(elem).datepicker({dateFormat:"yy-mm-dd"});} }
				           }
				           ],


				           sortorder : 'desc',

				           multiselect: false,	
				           loadonce: false,
				           rownumbers:true,
				           'cellEdit':true,
				           afterSaveCell: function  grossTotal() {
				        	   /* 	Calculation of total after editing quantity*/

				        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
				        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
				        	   var quantity = rowData['quantity'];
				        	   var buyPrice = rowData['buyPrice'];
				        	   var iGst = rowData['iGst'];
				        	   var cGst = rowData['cGst'];
				        	   var sGst = rowData['sGst'];

				        	   if (iGst != 0){
				        		   var taxPercentage = iGst;
				        		   var taxAmount = ((taxPercentage/100)*buyPrice);
				        		   var BuyPriceWithTaxAmount = Number(taxAmount) + Number(buyPrice);
				        		   var tota = quantity * BuyPriceWithTaxAmount;
				        		   $("#jqGrid").jqGrid("setCell", rowId, "total", tota);
				        	   }
				        	   else if(iGst == 0){
				        		   var  taxPercentage = Number(cGst) + Number(sGst);
				        		   var taxAmount = ((taxPercentage/100)*buyPrice);
				        		   var BuyPriceWithTaxAmount = Number(taxAmount) + Number(buyPrice);
				        		   var tota = quantity * BuyPriceWithTaxAmount;
				        		   $("#jqGrid").jqGrid("setCell", rowId, "total", tota);

				        	   }

				        	   var Total =0;
				        	   var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
				        	   var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
				        	   var AllRows=JSON.stringify(allRowsInGrid1);
				        	   for (var k = 0; k < count; k++) {
				        		   var Total1 = allRowsInGrid1[k].tota;
				        		   Total = +Total + +Total1;
				        	   }
				        	  /* document.getElementById("total").value = Total;
				        	   document.getElementById("grossTotal").value = Total;
				        	   document.getElementById("duptotal").value = Total;*/

				           },

				           viewrecords: true,
				           width: 1200,
				           shrinkToFit:true,
				           rowNum: 10,
				           pager: "#jqGridPager",
				           sortorder: "desc",
				           onSelectRow: function(productID){
				        	   if(productID && productID!==lastsel){
				        		   jQuery('#jqGrid').jqGrid('restoreRow',lastsel);
				        		   jQuery('#jqGrid').jqGrid('editRow',productID,true);
				        		   lastsel=productID;
				        	   }
				           },
			});

			count = jQuery("#jqGrid").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#jqGrid").jqGrid('getGridParam','data');
			var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
			var prodName,com,packing;
			for (var j = 0; j < count; j++) 
			{
				prodName = rowdata[j].productName;
				com = rowdata[j].manufacturer;
				packing = rowdata[j].weight;

				var rowId = ids[j];
				var rowData = jQuery('#jqGrid').jqGrid ('getRowData', rowId);

				if (prodName == jsonData[i].productName && com == jsonData[i].manufacturer && packing == jsonData[i].weight) {

					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#jqGrid");
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
				$("#jqGrid").addRowData(count,jsonData[i]);

			}  
			if(count==0 || count==null)
			{
				$("#jqGrid").addRowData(0,jsonData[i]);
			}
			function pickdates(productID){
				jQuery("#"+productID+"_expiryDate","#jqGrid").datepicker({dateFormat:"yyyy-mm-dd"});
			}
			
			
			
			$('#jqGrid').navGrid('#jqGridPager',
					// the buttons to appear on the toolbar of the grid
					{edit: true, add: false,del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
					// options for the Edit Dialog
					{

						afterSaveCell: function () {
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

					// options for the Delete Dialogue
					{
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


function updateGoodreciveValidation()
{
	if(document.ugr.challanNo.value != "")
	{
		if(document.ugr.billNum.value != "")
		{
			if(document.ugr.purchaseDate.value != "")
			{
				updateGoodReceive();
			}
			else
			{
				alert("select purchase Date");
			}
		}
		else
		{
			/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
			
					var msg="Please Select Product Name";
					var dialog = bootbox.dialog({
						//title: "Embel Technologies Says :",
					    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
					    closeButton: false
					});
					
					setTimeout(function() {
						dialog.modal('hide');
					}, 1500);
					
					return false;
					
						});*/
			alert("select Enter Bill No");
		}
	}
	else
	{
		alert("select Challan no");
	}
}

function updateGoodReceive()
{
	document.getElementById("update").disabled = true;
	var params = {};
	
	var input1 = document.getElementById('suppliername'),
	list = document.getElementById('sup_drop'),
	i,fk_supplier_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_supplier_id = list.options[i].getAttribute('data-value');
		}
	}
	
	var challanNo=$('#challanNo').val();
	var billNum=$('#billNum').val();
	var purchaseDate=$('#purchaseDate').val();
	
	
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	
	for (var i = 0; i < count; i++) {

		var catId = allRowsInGrid[i].catId;
		params["catId"+i] = catId;

		var productName = allRowsInGrid[i].productName;
		params["productName"+i] = productName;

		var companyName = allRowsInGrid[i].companyName;
		params["companyName"+i] = companyName;

		var hsn = allRowsInGrid[i].hsn;
		params["hsn"+i] = hsn;

		var buyPrice = allRowsInGrid[i].buyPrice;
		params["buyPrice"+i] = buyPrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var packing = allRowsInGrid[i].packing;
		params["packing"+i] = packing;

		var cGst = allRowsInGrid[i].cGst;
		params["cGst"+i] = cGst;
		
		var sGst = allRowsInGrid[i].sGst;
		params["sGst"+i] = sGst;
		
		var iGst = allRowsInGrid[i].iGst;
		params["iGst"+i] = iGst;
		
		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;
		
		var total = allRowsInGrid[i].total;
		params["total"+i] = total;
		
		var batchNo = allRowsInGrid[i].batchNo;
		params["batchNo"+i] = batchNo;
		
		var expDate = allRowsInGrid[i].expDate;
		params["expDate"+i] = expDate;
		

	}


	
/*	if(GSTtransExpence==undefined || GSTtransExpence== null || GSTtransExpence == "" ){
		GSTtransExpence=0;
	}
	if(GSThamaliExpence==undefined || GSThamaliExpence== null || GSThamaliExpence == "" ){
		GSThamaliExpence=0;
	}

	if(transExpence==undefined || transExpence== null || transExpence == "" ){
		transExpence=0;
	}
*/

	params["fk_supplier_id"] = fk_supplier_id;
	params["challanNo"] = challanNo;
	params["billNum"] = billNum;
	params["purchaseDate"] = purchaseDate;
	params["count"] = count;
	
	
	params["methodName"] = "updateGoodsReceive";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
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
				document.getElementById("update").disabled = true;
			}, 1500);
			
			return false;
			
				});*/
		
		location.reload();
		document.getElementById("update").disabled = true;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function fetchChallanNo(){

	var input1 = document.getElementById('suppliername'),
	list = document.getElementById('sup_drop'),
	i,fk_supplier_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_supplier_id = list.options[i].getAttribute('data-value');
		}
	}

	var fk_supplier_id = fk_supplier_id;
	$("#challanNo").empty();
	$("#challanNo").append($("<option></option>").attr("value","").text("Select Challan No"));
	var params= {};

	params["fk_supplier_id"]= fk_supplier_id;
	params["methodName"] = "getAllChallanNo";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#challan_drop").append($("<option></option>").attr("value",(v.billNum))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}
