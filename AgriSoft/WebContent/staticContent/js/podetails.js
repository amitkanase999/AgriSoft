function purchaseOrderHelper(){

	this.getAllProduct = getAllProduct;

	function getAllProduct(){
		var input1 = document.getElementById('fk_cat_id'),
		list = document.getElementById('cat_drop'),
		i,fk_cat_id;
		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input1.value) {
				fk_cat_id = list.options[i].getAttribute('data-value');
			}
		}

		var fk_cat_id = fk_cat_id;
		$("#proName").empty();
		$("#proName").append($("<option></option>").attr("value","").text("Select product"));
		var params= {};

		params["methodName"] = "getAllProductByCategoriesForAdvance";
		params["fk_cat_id"]= fk_cat_id;
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
}
var pro = new purchaseOrderHelper();

function getdetails()
{
	var productList = "";
	this.getProductdetails = getProductdetails;

	function getProductdetails()
	{
		if (document.getElementById('token').checked) {
			var params= {};
			var itemparams={};
			productId = $('#proName').val();

			$("#proName option:selected").each(function() {
				selectedVal = $(this).text();
			});

			var splitText = selectedVal.split(",");

			var proName = splitText[0];
			var company = splitText[1];
			var weight = splitText[2];

			itemparams["proName"]= proName;
			itemparams["company"]= company;
			itemparams["weight"]= weight;

			var count=0;
			var newrow;
			var rowId;
			var tota = 0;
			itemparams["methodName"] = "getProductDetailsForAdvanceBook";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
					function(data)
					{ 
				var jsonData = $.parseJSON(data);
				function sumFmatter (cellvalue, options, rowObject)
				{


					var jam=0;
					var jam1=0;
					var tot= (options.rowData.quantity * options.rowData.token);

					var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
					var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
					var AllRows=JSON.stringify(allRowsInGrid1);
					for (var i = 0; i < count; i++) {

						var quantity = allRowsInGrid1[i].quantity;
						params["quantity"+i] = quantity;

						var token = allRowsInGrid1[i].token;
						params["token"+i] = token;

						var totals1=((token)*(quantity));

						jam = jam + totals1;

					}
					jam1= jam;

					document.getElementById("grossTotal").value = jam1;//for gross total

					return tot;
				}

				$.each(jsonData,function(i,v)
						{
					$("#jqGrid").jqGrid({

						datatype:"local",

						colNames: ["Product ID","Product Name","Manufacturer","Buy Price","M.R.P","Sale Price","Packing","Unit","Token Rate Per Bag","Quantity","Total" ],

						colModel: [
						           { 	
						        	   name: "productID",
						        	   hidden:true

						           },
						           { 	
						        	   name: "productName",
						        	   width:100,

						           },
						           { 	
						        	   name: "manufacturer",
						        	   width:100,

						           },
						           {
						        	   name: "buyPrice",
						        	   width: 120,
						        	   editable: true
						           },	
						           {
						        	   name: "mrp",
						        	   width: 140,
						        	   editable: true
						           },
						           {
						        	   name:  "salePrice",
						        	   width: 140,
						        	   editable: true
						           },

						           {
						        	   name: "weight",
						        	   width: 140,
						        	   editable: true,

						           },
						           {
						        	   name: "unitName",
						        	   width: 100,
						        	   editable: true,

						           },
						           {
						        	   name: "token",
						        	   width: 160,
						        	   editable: true,

						           },
						           {
						        	   name: "quantity",
						        	   width: 100,
						        	   editable: true


						           },

						           {
						        	   name : 'Total',
						        	   formatter: sumFmatter,
						        	   width: 150,

						           }
						           ],


						           sortorder : 'desc',

						           loadonce: true,
						           viewrecords: true,
						           width: 1300,
						           shrinkToFit :true,
						           rowNum: 10,

						           pager: "#jqGridPager",
						           'cellEdit':true,
						           afterSaveCell: function  grossTotal() {
						        	   /* 	Calculation of total after editing quantity*/

						        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
						        	   var quantity = rowData['quantity'];
						        	   var buyPrice = rowData['buyPrice'];

						        	   tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
						        	   
						           }
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
							
							{

								afterSubmit: function() {
									$('#jqGrid').trigger( 'reloadGrid' );
								},
								closeAfterAdd: true,
								recreateForm: true,
								errorTextFormat: function (data) {
									return 'Error: ' + data.responseText
								}
							
							},
							
							// options for the Delete Dialogue
							{
								afterComplete: function  grossTotal() {
									$('#jqGrid').trigger( 'reloadGrid' );

						        	   /* 	Calculation of total after editing quantity*/

						        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
						        	   var quantity = rowData['quantity'];
						        	   var buyPrice = rowData['buyPrice'];

						        	   tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
						          
								},
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

					}); 
		}

		/*+++++++++++ grid for advance amount ++++++++++++*/
		else if(document.getElementById('advanceAmount').checked){

			var params= {};
			itemparams={};
			productId = $('#proName').val();

			$("#proName option:selected").each(function() {
				selectedVal = $(this).text();
			});

			var splitText = selectedVal.split(",");
			var proName = splitText[0];
			var company = splitText[1];
			var weight = splitText[2];

			itemparams["proName"]= proName;
			itemparams["company"]= company;
			itemparams["weight"]= weight;
			itemparams["productId"]= productId;

			var count=0;
			var newrow;
			var rowId;
			var total = 0.0;
			var tota = 0.0;
			itemparams["methodName"] = "getProductDetailsForAdvanceBook";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
					function(data)
					{ 
				var jsonData = $.parseJSON(data);
				$.each(jsonData,function(i,v)
						{
					$("#jqGrid").jqGrid({
						datatype:"local",

						colNames: ["Product ID","Product Name","Manufacturer","Buy Price","M.R.P","Sale Price","Packing","Unit","Quantity" ],

						colModel: [
						           { 	
						        	   name: "productID",
						        	   hidden:true

						           },
						           { 	
						        	   name: "productName",
						        	   width:100,

						           },
						           {
						        	   name: "manufacturer",
						        	   width: 140,
						        	   editable: true
						           },
						           {
						        	   name: "buyPrice",
						        	   width: 120,
						        	   editable: true
						           },	
						           {
						        	   name: "mrp",
						        	   width: 140,
						        	   editable: true
						           },


						           {
						        	   name:  "salePrice",
						        	   width: 140,
						        	   editable: true
						           },

						           {
						        	   name: "weight",
						        	   width: 100,

						           },
						           {
						        	   name: "unitName",
						        	   width: 100,
						        	   editable: true,

						           },

						           {
						        	   name: "quantity",
						        	   width: 120,
						        	   editable: true


						           }
						           ],


						           sortorder : 'desc',

						           multiselect: false,	
						           loadonce: false,
						           rownumbers:true,
						           'cellEdit':true,
						           afterSaveCell: function  grossTotal1() {
						        	   //	Calculation of total after editing quantity

						        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
						        	   var quantity = rowData['quantity'];
						        	   var buyPrice = rowData['buyPrice'];
						        	   total = document.getElementById("grossTotal").value;
						        	   tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
						        	   
						        	   var Total1 = tota;
										if(Total1 != undefined){
											total = +total + +Total1;
										}
										
						        	   document.getElementById("grossTotal").value=total;
						           },
						           viewrecords: true,
						           width: 1300,
						           shrinkToFit :true,
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
							
							{
								afterSubmit: function() {
									$('#jqGrid').trigger( 'reloadGrid' );
								},
								closeAfterAdd: true,
								recreateForm: true,
								errorTextFormat: function (data) {
									return 'Error: ' + data.responseText
								}
							},
							
							// options for the Delete Dialogue
							{
								afterComplete: function  grossTotal1() {
						        	   //	Calculation of total after editing quantity

						        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
						        	   var quantity = rowData['quantity'];
						        	   var buyPrice = rowData['buyPrice'];
						        	   total = document.getElementById("grossTotal").value;
						        	   tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
						        	   
						        	   var Total1 = tota;
										if(Total1 != undefined){
											total = +total + +Total1;
										}
										
						        	   document.getElementById("grossTotal").value=total;
						           },
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
					}); 
		}

	}
}

var prodetails = new getdetails();

//Adding advance booking
function addAdvanceBook(){

	if ( document.poferti.supplier.value != "" )
	{
		
		if ( document.poferti.fk_cat_id.value != "" )
		{
			if ( document.poferti.proName.value != "" )
			{
				if ( document.poferti.grossTotal.value != "" )
				{
					var letterNumber = /^\s*-?[0-9]\d*(\.\d{1,2})?\s*$/;
					var num = /^\d+$/;
					if(document.poferti.grossTotal.value.match(letterNumber) || document.poferti.grossTotal.value.match(num) )
					{

						purchaseOrder();
					}

					else
					{
						
						$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
								{
					
							var msg="Enter only Numbers upto 2 decimal in Gross Total field..!!";
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
				
						var msg="Please Enter Gross Total";
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
					
						});
			}
		}
		else
		{
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Please Select Product Category";
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
	
			var msg="Please Select Supplier";
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
function purchaseOrder(){

	document.poferti.btn.disabled = true;
//	to fetch grid data

	var params= {};
	var totals=0;

	var approveVal;

	var namePresent;
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');

	var action = new Array();

	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var productID = allRowsInGrid[i].productID;
		params["productID"+i] = productID;

		var productName = allRowsInGrid[i].productName;
		params["productName"+i] = productName;// productName is value of name attribute from grid

		var buyPrice = allRowsInGrid[i].buyPrice;
		params["buyPrice"+i] = buyPrice;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		if ( quantity == 0 )
		{
			alert("Please enter quantity ");
			return false;
		}

		var manufacturer = allRowsInGrid[i].manufacturer;
		params["manufacturer"+i] = manufacturer;

		var token = allRowsInGrid[i].token;
		params["token"+i] = token;

		var unitName = allRowsInGrid[i].unitName;
		params["unitName"+i] = unitName;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;
	}

	var dcNum = $('#dcNum').val();
	var grossTotal = $('#grossTotal').val();
	var advanceTokenAmount = $('#advanceTokenAmount').val();
	var input1 = document.getElementById('fk_cat_id'),
	list = document.getElementById('cat_drop'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}

	var input11 = document.getElementById('supplier'),
	list1 = document.getElementById('sup_drop'),
	i,fk_supplier_id;
	for (i = 0; i < list1.options.length; ++i) {
		if (list1.options[i].value === input11.value) {
			fk_supplier_id = list1.options[i].getAttribute('data-value');
		}
	}

	params["grossTotal"] =grossTotal;
	params["advanceTokenAmount"] =advanceTokenAmount;
	params["dcNum"] =dcNum;
	params["fk_supplier_id"] = fk_supplier_id;
	params["fk_cat_id"] =fk_cat_id;
	params["count"] = count;
	params["methodName"] = "addPurchaseOrderDetails";
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
				document.poferti.save.disabled = false;
			}, 1500);
			
			return false;
			
				});
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});}

function reset()
{
	document.poferti.reset();	

}
