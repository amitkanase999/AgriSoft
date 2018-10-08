function fetchproductname(){

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
				$("#Product_list_drop").append($("<option></option>").attr("value",(v.product + ","+v.manufacturer+","+v.weight))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

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

	params["methodName"] = "getAllProductByCategories";
	params["fk_cat_id"]= fk_cat_id;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#proName").append($("<option></option>").attr("value",i).text(v.product)); 

				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function getAllProductForBooked(){
	var input1 = document.getElementById('fk_cat_id1'),
	list = document.getElementById('cat_drop1'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}

	var input1 = document.getElementById('supplier1'),
	list = document.getElementById('sup_drop1'),
	i,fk_sup_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_sup_id = list.options[i].getAttribute('data-value');
		}
	}

	var fk_cat_id = fk_cat_id;
	var fk_sup_id = fk_sup_id;
	$("#proName1").empty();
	$("#proName1").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["methodName"] = "getAllProductByCategoriesAndBySupplierForBookedGoodsReceiveNew";
	params["fk_cat_id"]= fk_cat_id;
	params["fk_sup_id"]= fk_sup_id;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var count = 1;
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#proName1").append($("<option></option>").attr("value",count).text(v.product + ","+v.manufacturer+","+v.weight+","+v.unitName));
			count++;
				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function prodetail(){

	var itemparams={};
	var productId = $('#proName').val();

	itemparams["productId"]= productId;
	itemparams["methodName"] = "getpreviousDetsil";
	$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			alert( "Previous Purchase Detail Of "  +v.productName+  "\nProduct"+
					"\n\n\nProduct Name : " +v.productName+
					"\nBuy Price    : " +v.buyPrice+
					"\nSale Price   : " +v.salePrice+
					"\nQuantity     : " +v.quantity+
					"\nWeight       : " +v.weight+
					"\nMRP          : " +v.mrp);

				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function getdetails()
{
	var productList = "";
	this.getProductdetails = getProductdetails;
	this.getAllProductDetailsForBooked = getAllProductDetailsForBooked;

	function getProductdetails()
	{	
		//Grid for Retail if it is checked 
		if (document.getElementById('retail').checked) {
			jQuery("#jqGrid").trigger('reloadGrid');

			var params= {};
			var itemparams={};
			var productId = $('#proName').val();

			itemparams["productId"]= productId;
			itemparams["methodName"] = "getProductDetailsPO";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,function(data)
					{ 
				var jsonData = $.parseJSON(data);

				function sumFmatter (cellvalue, options, rowObject)
				{
					var jam=0;
					var jam1="";
					var tot= (options.rowData.quantity * options.rowData.buyPrice);
					var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
					var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
					var AllRows=JSON.stringify(allRowsInGrid1);
					for (var i = 0; i < count; i++) {

						var quantity = allRowsInGrid1[i].quantity;
						params["quantity"+i] = quantity;

						var buyPrice = allRowsInGrid1[i].buyPrice;
						params["buyPrice"+i] = buyPrice;

						var totals1=((buyPrice)*(quantity));

						jam = jam + totals1;

					}
					if(count == 0){
						document.getElementById("total").value = tot;
					}else{
						document.getElementById("total").value = jam;
					}
					// to check whether bill is with or without VAT  
					if (document.getElementById('retail').checked) {

						/*	Gross total calculation without VAT for cash customer 	*/
						var expence = document.getElementById("extraExpence").value;
						totalWithExpence = Number(jam) + Number(expence);
						var discount = $('#discount').val();
						var discountAmount = ((discount/100)*totalWithExpence);
						var totalminusDiscount = totalWithExpence - discountAmount;
						document.getElementById("discountAmount").value = discountAmount;

						document.getElementById("grossTotal").value = totalminusDiscount;

						return tot;
					}

					else{
						/*	Gross total calculation with VAT for cash customer 	*/
						/*Vat Amount calculation*/
						var expence = document.getElementById("extraExpence").value;
						totalWithExpence = Number(jam) + Number(expence);
						var discount = $('#discount').val();
						var discountAmount = ((discount/100)*totalWithExpence);
						var totalminusDiscount = totalWithExpence - discountAmount;
						document.getElementById("discountAmount").value = discountAmount;

						document.getElementById("grossTotal").value = totalminusDiscount;

						return tot; 	 
					}	
				}
				$.each(jsonData,function(i,v)
						{

					$("#jqGrid").jqGrid({

						datatype:"local",

						colNames: ["Product ID","Product Name","Buy Price","M.R.P","Sale Price","Packing","Quantity","Total","BatchNo","ExpiryDate" ],

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
						        	   name: "buyPrice",
						        	   width: 140,
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
						        	   name: "quantity",
						        	   width: 140,
						        	   editable: true


						           },

						           {
						        	   name : 'Total',
						        	   formatter: sumFmatter,
						        	   width: 150,

						           },
						           {
						        	   label : 'batchNo',
						        	   name: "batchNo",
						        	   width: 150,
						        	   editable:true,


						           },
						           {
						        	   label : 'expiryDate',
						        	   name: "expiryDate",
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

						        	   var tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
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

					$("#jqGrid").addRowData(i,jsonData[i]);
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

		//Grid for Tax if retail is not checked
		else if (document.getElementById('vat').checked)
		{
			jQuery("#jqGrid").trigger('reloadGrid');

			var params= {};
			itemparams={};
			productId = $('#proName').val();

			itemparams["productId"]= productId;
			itemparams["methodName"] = "getProductDetailsForGoodsReceiveForTax";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
					function(data)
					{ 
				var jsonData = $.parseJSON(data);

				function sumFmatter (cellvalue, options, rowObject)
				{
					var jam=0;
					var jam1="";
					var tot= ((options.rowData.quantity) * ((options.rowData.buyPrice)+((options.rowData.taxPercentage/100)*options.rowData.buyPrice)));

					var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
					var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
					var AllRows=JSON.stringify(allRowsInGrid1);
					for (var i = 0; i < count; i++) {

						var quantity = allRowsInGrid1[i].quantity;
						params["quantity"+i] = quantity;

						var buyPrice = allRowsInGrid1[i].buyPrice;
						params["buyPrice"+i] = buyPrice;

						var taxPercentage = allRowsInGrid1[i].taxPercentage;
						params["taxPercentage"+i] = taxPercentage;

						var taxAmount = ((taxPercentage/100)*buyPrice);

						var priceWithTaxamount = taxAmount+buyPrice;

						var totals1=((priceWithTaxamount)*(quantity));

						jam = jam + totals1;

						document.getElementById("total").value = jam;
					}
					if(count == 0){
						document.getElementById("total").value = tot;
					}
					else{
						document.getElementById("total").value = jam;
					}

					// to check whether bill is with or without VAT  
					if (document.getElementById('retail').checked) {

						/*	Gross total calculation without VAT for cash customer 	*/
						var expence = document.getElementById("extraExpence").value;
						totalWithExpence = Number(jam) + Number(expence);
						var discount = $('#discount').val();
						var discountAmount = ((discount/100)*totalWithExpence);
						var totalminusDiscount = totalWithExpence - discountAmount;
						document.getElementById("discountAmount").value = discountAmount;

						document.getElementById("grossTotal").value = totalminusDiscount;

						return tot;
					}

					else{
						/*	Gross total calculation with VAT for cash customer 	*/
						/*Vat Amount calculation*/
						var expence = document.getElementById("extraExpence").value;
						totalWithExpence = Number(jam) + Number(expence);
						var discount = $('#discount').val();
						var discountAmount = ((discount/100)*totalWithExpence);
						var totalminusDiscount = totalWithExpence - discountAmount;
						document.getElementById("discountAmount").value = discountAmount;

						document.getElementById("grossTotal").value = totalminusDiscount;

						return tot;	 
					}	
				}
				$.each(jsonData,function(i,v)
						{

					var productName =  v.productName;
					var  buyPrice =  v.buyPrice;  
					var salePrice =v.salePrice;
					var quantity = v.quantity;
					var weight = v.weight ;
					var productID = v.productID ;
					var lastsel = productID;

					$("#jqGrid").jqGrid({
						datatype:"local",

						colNames: ["Product ID","Product Name","Buy Price","Tax %","M.R.P","Sale Price","Packing","Quantity","Total","BatchNo","ExpiryDate" ],

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
						        	   name: "buyPrice",
						        	   width: 120,
						        	   editable: true
						           },	
						           {
						        	   name: "taxPercentage",
						        	   width: 140,
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
						        	   name: "quantity",
						        	   width: 120,
						        	   editable: true

						           },

						           {
						        	   name : 'Total',
						        	   formatter: sumFmatter,
						        	   width: 150,

						           },
						           {
						        	   label : 'batchNo',
						        	   name: "batchNo",
						        	   width: 150,
						        	   editable:true,

						           },
						           {
						        	   label : 'expiryDate',
						        	   name: "expiryDate",
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
						        	   var taxPercentage = rowData['taxPercentage'];
						        	   var taxAmount = ((taxPercentage/100)*buyPrice);
						        	   var BuyPriceWithTaxAmount = taxAmount + buyPrice;
						        	   var tota = quantity * BuyPriceWithTaxAmount;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
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

					$("#jqGrid").addRowData(i,jsonData[i]);
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
	}

	/****************	Grid for Booked Goods Receive new  ********/

	function getAllProductDetailsForBooked(){

		//Grid for Retail if it is checked 
		if (document.getElementById('retail1').checked) {
			jQuery("#jqGrid1").trigger('reloadGrid');

			var input1 = document.getElementById('fk_cat_id1'),
			list = document.getElementById('cat_drop1'),
			i,fk_cat_id;
			for (i = 0; i < list.options.length; ++i) {
				if (list.options[i].value === input1.value) {
					fk_cat_id = list.options[i].getAttribute('data-value');
				}
			}

			var input1 = document.getElementById('supplier1'),
			list = document.getElementById('sup_drop1'),
			i,fk_sup_id;
			for (i = 0; i < list.options.length; ++i) {
				if (list.options[i].value === input1.value) {
					fk_sup_id = list.options[i].getAttribute('data-value');
				}
			}

			var fk_cat_id = fk_cat_id;
			var fk_sup_id = fk_sup_id;

			var params= {};
			itemparams={};
			productId = $('#proName1').val();

			$("#proName1 option:selected").each(function() {
				selectedVal = $(this).text();
			});

			var splitText = selectedVal.split(",");
			var proName = splitText[0];
			var company = splitText[1];
			var weight = splitText[2];

			itemparams["proName"]= proName;
			itemparams["company"]= company;
			itemparams["weight"]= weight;
			itemparams["fk_cat_id"]= fk_cat_id;
			itemparams["fk_sup_id"]= fk_sup_id;

			var count=0;
			var newrow;
			var rowId;

			itemparams["methodName"] = "getbookedProductDetailsForGoodsReceiveNew";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,

					function(data)
					{ 
				var jsonData = $.parseJSON(data);
				function sumFmatter (cellvalue, options, rowObject)
				{

					var jam=0;
					var jam1="";
					var tot= (options.rowData.quantity * options.rowData.buyPrice);
					//var shree = document.poferti.grossTotal.value;// to get gross total

					var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
					var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
					var AllRows=JSON.stringify(allRowsInGrid1);
					for (var i = 0; i < count; i++) {

						var quantity = allRowsInGrid1[i].quantity;
						params["quantity"+i] = quantity;

						var buyPrice = allRowsInGrid1[i].buyPrice;
						params["buyPrice"+i] = buyPrice;

						var totals1=((buyPrice)*(quantity));

						jam = jam + totals1;

					}
					if(count == 0){
						document.getElementById("total1").value = tot;
						document.getElementById("grossTotal1").value = tot;
					}else{
						document.getElementById("total1").value = jam;
						document.getElementById("grossTotal1").value = tot;
					}

					return tot;
				}	

				$.each(jsonData,function(i,v)
						{
					$("#jqGrid1").jqGrid({

						datatype:"local",

						colNames: ["catId","Product ID","Product Name","Company","Buy Price","M.R.P","Sale Price","Packing","Quantity","Total","BatchNo","ExpiryDate" ],

						colModel: [
						           { 	
						        	   name: "catId",
						        	   hidden:true

						           },
						           { 	
						        	   name: "productID",
						        	   hidden:true
						           },
						           { 	
						        	   name: "productName",
						        	   width:120,

						           },
						           { 	
						        	   name: "manufacturer",
						        	   width:120

						           },
						           {
						        	   name: "buyPrice",
						        	   width: 140,
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
						        	   name: "quantity",
						        	   width: 140,
						        	   editable: true

						           },

						           {
						        	   name : 'Total',
						        	   formatter: sumFmatter,
						        	   width: 150,

						           },
						           {
						        	   label : 'batchNo',
						        	   name: "batchNo",
						        	   width: 150,
						        	   editable:true,

						           },
						           {
						        	   label : 'expiryDate',
						        	   name: "expiryDate",
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

						        	   var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid1").getRowData(rowId);
						        	   var quantity = rowData['quantity'];
						        	   var buyPrice = rowData['buyPrice'];
						        	   var tota = quantity * buyPrice;
						        	   $("#jqGrid1").jqGrid("setCell", rowId, "Total", tota);
						           },

						           viewrecords: true,
						           width: 1200,
						           shrinkToFit:true,
						           rowNum: 10,
						           pager: "#jqGridPager1",
						           sortorder: "desc",
						           onSelectRow: function(productID){
						        	   if(productID && productID!==lastsel){
						        		   jQuery('#jqGrid1').jqGrid('restoreRow',lastsel);
						        		   jQuery('#jqGrid1').jqGrid('editRow',productID,true);
						        		   lastsel=productID;
						        	   }
						           },
					});

					count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records'); 
					var rowdata =$("#jqGrid1").jqGrid('getGridParam','data');
					var ids = jQuery("#jqGrid1").jqGrid('getDataIDs');

					var prodName,com,packing;
					for (var j = 0; j < count; j++) 
					{
						prodName = rowdata[j].productName;
						com = rowdata[j].manufacturer;
						packing = rowdata[j].weight;

						var rowId = ids[j];
						var rowData = jQuery('#jqGrid1').jqGrid ('getRowData', rowId);

						if (prodName == jsonData[i].productName && com == jsonData[i].manufacturer && packing == jsonData[i].weight) {

							newrow=false;
							alert("Product Name Already Inserted !!!");
							var grid = jQuery("#jqGrid1");
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

						$("#jqGrid1").addRowData(count,jsonData[i]);

					}  

					if(count==0 || count==null)
					{
						$("#jqGrid1").addRowData(0,jsonData[i]);
					}
					function pickdates(productID){
						jQuery("#"+productID+"_expiryDate","#jqGrid1").datepicker({dateFormat:"yy-mm-dd"});
					}


					$('#jqGrid1').navGrid('#jqGridPager1',
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
										jQuery("#jqGrid1").saveRow(lastSel, true, 'clientArray');
										jQuery("#jqGrid1").editRow(id, true);

										lastSel = id;
										console.log(id);
									}
								}
							});
					// grid refresh code	

						});
					}); 
		}

		//Grid for Tax if retail is not checked
		else if (document.getElementById('vat1').checked)
		{
			jQuery("#jqGrid1").trigger('reloadGrid');

			var input1 = document.getElementById('fk_cat_id1'),
			list = document.getElementById('cat_drop1'),
			i,fk_cat_id;
			for (i = 0; i < list.options.length; ++i) {
				if (list.options[i].value === input1.value) {
					fk_cat_id = list.options[i].getAttribute('data-value');
				}
			}

			var input1 = document.getElementById('supplier1'),
			list = document.getElementById('sup_drop1'),
			i,fk_sup_id;
			for (i = 0; i < list.options.length; ++i) {
				if (list.options[i].value === input1.value) {
					fk_sup_id = list.options[i].getAttribute('data-value');
				}
			}

			var fk_cat_id = fk_cat_id;
			var fk_sup_id = fk_sup_id;

			var params= {};
			itemparams={};
			productId = $('#proName1').val();

			$("#proName1 option:selected").each(function() {
				selectedVal = $(this).text();
			});

			var splitText = selectedVal.split(",");

			var proName = splitText[0];
			var company = splitText[1];
			var weight = splitText[2];

			itemparams["proName"]= proName;
			itemparams["company"]= company;
			itemparams["weight"]= weight;
			itemparams["fk_cat_id"]= fk_cat_id;
			itemparams["fk_sup_id"]= fk_sup_id;

			var count=0;
			var newrow;
			var rowId;

			itemparams["methodName"] = "getProductDetailsForGoodsReceiveForBookedWithTaxNew";
			$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
					function(data)
					{ 
				var jsonData = $.parseJSON(data);
				function sumFmatter (cellvalue, options, rowObject)
				{
					var jam=0;
					var jam1="";
					var tot= ((options.rowData.quantity) * ((options.rowData.buyPrice)+((options.rowData.taxPercentage/100)*options.rowData.buyPrice)));
					var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
					var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
					var AllRows=JSON.stringify(allRowsInGrid1);
					for (var i = 0; i < count; i++) {

						var quantity = allRowsInGrid1[i].quantity;
						params["quantity"+i] = quantity;

						var buyPrice = allRowsInGrid1[i].buyPrice;
						params["buyPrice"+i] = buyPrice;

						var taxPercentage = allRowsInGrid1[i].taxPercentage;
						params["taxPercentage"+i] = taxPercentage;

						var taxAmount = ((taxPercentage/100)*buyPrice);

						var priceWithTaxamount = taxAmount+buyPrice;

						var totals1=((priceWithTaxamount)*(quantity));

						jam = jam + totals1;
					}
					if(count == 0){
						document.getElementById("total1").value = tot;
						document.getElementById("grossTotal1").value = tot;
					}
					else{
						document.getElementById("total1").value = jam;
						document.getElementById("grossTotal1").value = jam;
					}
					return tot;	 
				}	

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

					$("#jqGrid1").jqGrid({

						datatype:"local",

						colNames: ["catId","Product ID","Product Name","Company","HSN","Buy Price","M.R.P","Sale Price","Packing","CGST","SGST","IGST","Quantity","Total","BatchNo","ExpiryDate" ],

						colModel: [
						           { 	
						        	   name: "catId",
						        	   hidden:true


						           },
						           { 	
						        	   name: "productID",
						        	   hidden:true

						           },
						           { 	
						        	   name: "productName",
						        	   width:120,

						           },
						           { 	
						        	   name: "manufacturer",
						        	   width:120

						           },
						           { 	
						        	   name: "hsn",
						        	   width:120

						           },
						           {
						        	   name: "buyPrice",
						        	   width: 120,
						        	   editable: true
						           },
						           {
						        	   name: "mrp",
						        	   width: 120,
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
						        	   name: "cGst",
						        	   width: 140,
						        	   editable: true
						           },
						           {
						        	   name: "sGst",
						        	   width: 140,
						        	   editable: true
						           },
						           {
						        	   name: "iGst",
						        	   width: 140,
						        	   editable: true
						           },
						           {
						        	   name: "quantity",
						        	   width: 120,
						        	   editable: true
						           },

						           {
						        	   name : 'Total',
						        	   width: 150,

						           },
						           {
						        	   label : 'batchNo',
						        	   name: "batchNo",
						        	   width: 150,
						        	   editable:true,

						           },
						           {
						        	   label : 'expiryDate',
						        	   name: "expiryDate",
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

						        	   var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
						        	   var rowData = jQuery("#jqGrid1").getRowData(rowId);
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
						        		   $("#jqGrid1").jqGrid("setCell", rowId, "Total", tota);
						        	   }
						        	   else if(iGst == 0){
						        		   var  taxPercentage = Number(cGst) + Number(sGst);
						        		   var taxAmount = ((taxPercentage/100)*buyPrice);
						        		   var BuyPriceWithTaxAmount = Number(taxAmount) + Number(buyPrice);
						        		   var tota = quantity * BuyPriceWithTaxAmount;
						        		   $("#jqGrid1").jqGrid("setCell", rowId, "Total", tota);

						        	   }

						        	   var Total =0;
						        	   var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
						        	   var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
						        	   var AllRows=JSON.stringify(allRowsInGrid1);
						        	   for (var k = 0; k < count; k++) {
						        		   var Total1 = allRowsInGrid1[k].Total;
						        		   Total = +Total + +Total1;
						        	   }
						        	   document.getElementById("total1").value = Total;
						        	   document.getElementById("grossTotal1").value = Total;
						           },

						           viewrecords: true,
						           width: 1200,
						           shrinkToFit:true,
						           rowNum: 10,
						           pager: "#jqGridPager1",
						           sortorder: "desc",
						           onSelectRow: function(productID){
						        	   if(productID && productID!==lastsel){
						        		   jQuery('#jqGrid1').jqGrid('restoreRow',lastsel);
						        		   jQuery('#jqGrid1').jqGrid('editRow',productID,true);
						        		   lastsel=productID;
						        	   }
						           },
					});

					count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records'); 
					var rowdata =$("#jqGrid1").jqGrid('getGridParam','data');
					var ids = jQuery("#jqGrid1").jqGrid('getDataIDs');

					var prodName,com,packing;
					for (var j = 0; j < count; j++) 
					{
						prodName = rowdata[j].productName;
						com = rowdata[j].manufacturer;
						packing = rowdata[j].weight;

						var rowId = ids[j];
						var rowData = jQuery('#jqGrid1').jqGrid ('getRowData', rowId);

						if (prodName == jsonData[i].productName && com == jsonData[i].manufacturer && packing == jsonData[i].weight) {

							newrow=false;
							alert("Product Name Already Inserted !!!");
							var grid = jQuery("#jqGrid1");
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
						$("#jqGrid1").addRowData(count,jsonData[i]);

					}  

					if(count==0 || count==null)
					{
						$("#jqGrid1").addRowData(0,jsonData[i]);
					}
					function pickdates(productID){
						jQuery("#"+productID+"_expiryDate","#jqGrid1").datepicker({dateFormat:"yyyy-mm-dd"});
					}
					$('#jqGrid1').navGrid('#jqGridPager1',
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
										jQuery("#jqGrid1").saveRow(lastSel, true, 'clientArray');
										jQuery("#jqGrid1").editRow(id, true);

										lastSel = id;
										console.log(id);
									}
								}

							});

					// grid refresh code	

						});
					}); 
		}
	}
}


var prodetails = new getdetails();

//Calculations for Booked goods receive
function discountCalculationForBooked(){
	var total = document.getElementById("total1").value;
	var discount = $('#discount1').val();
	var discountAmount = ((discount/100)*Number(total));
	var totalminusDiscount = Number(total) - discountAmount;
	document.getElementById("discountAmount1").value = discountAmount;
	document.getElementById("grossTotal1").value = totalminusDiscount;
}

function hamaliExpenseAddingToGrossForBooked(){
	var hamaliExpence = document.getElementById("hamaliExpence1").value;
	//Gross total calculation
	var discount = $('#discount1').val();
	var transExpence = document.getElementById("transExpence1").value;

	if(discount == ""){

		if(transExpence == ""){
			var total = document.getElementById("total1").value;
			var totalWithExpense = Number(total) + Number(hamaliExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}
		if(transExpence != ""){
			var total = document.getElementById("total1").value;
			var totalWithExpense = Number(total) + Number(transExpence);
			var totalWithExpense1 = Number(totalWithExpense) + Number(hamaliExpence);
			document.getElementById("grossTotal1").value = totalWithExpense1;
		}
	}

	if(discount != ""){
		var transExpence = document.getElementById("transExpence1").value;
		if(transExpence == ""){
			var total = document.getElementById("total1").value;
			var discountAmount = ((discount/100)*Number(total));
			var totalminusDiscount = Number(total) - Number(discountAmount);
			var totalWithExpense = Number(totalminusDiscount) + Number(hamaliExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}
		if(transExpence != ""){
			var total = document.getElementById("total1").value;
			var discountAmount = ((discount/100)*Number(total));
			var totalminusDiscount = Number(total) - discountAmount;
			var totalwithTrans = Number(totalminusDiscount) + Number(transExpence);
			var totalWithExpense = Number(totalwithTrans) + Number(hamaliExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}
	}
}

function transExpenseAddingToGrossTotalForBooked(){
	var transExpence = document.getElementById("transExpence1").value;
	var hamaliExpence = document.getElementById("hamaliExpence1").value;
	var discount = $('#discount1').val();

	if(discount == ""){
		if(hamaliExpence == ""){
			var total = document.getElementById("total1").value;
			var totalWithExpense = Number(total) + Number(transExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}	

		if(hamaliExpence != ""){
			var total = document.getElementById("total1").value;
			var hamaliTotal = Number(total) + Number(hamaliExpence);
			var totalWithExpense = Number(transExpence) + Number(hamaliTotal);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}	
	}


	if(discount != ""){
		if(hamaliExpence == ""){
			var total = document.getElementById("total1").value;
			var discountAmount = ((discount/100)*Number(total));
			var totalminusDiscount = Number(total) - Number(discountAmount);
			var totalWithExpense = Number(totalminusDiscount) + Number(transExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}	
		if(hamaliExpence != ""){
			var total = document.getElementById("total1").value;
			var discountAmount = ((discount/100)*Number(total));
			var totalminusDiscount = Number(total) - Number(discountAmount);
			var totalwithTrans = Number(totalminusDiscount) + Number(hamaliExpence);
			var totalWithExpense = Number(totalwithTrans) + Number(transExpence);
			document.getElementById("grossTotal1").value = totalWithExpense;
		}
	}
}


/*++++++++ Grid code for Without Advance Booking by shrimant ++++++++++*/

function productDetailInGrid(){

	if (document.getElementById('retail').checked) {
		var params= {};
		var itemparams={};
		productId = $('#proName').val();

		/*$("#proName option:selected").each(function() {
			selectedVal = $(this).text();
		});

		var splitText = selectedVal.split(",");

		var proName = splitText[0];
		var company = splitText[1];
		var weight = splitText[2];*/
		
		
		var proName=productId.split(",")[0];
		var company=productId.split(",")[1];
		var weight=productId.split(",")[2];
		
		itemparams["proName"]= proName;
		itemparams["company"]= company;
		itemparams["weight"]= weight;

		var count=0;
		var newrow;
		var rowId;

		itemparams["methodName"] = "getProductDetailsForAdvanceBook";
		$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
				function(data)
				{

			var jsonData = $.parseJSON(data);

			function sumFmatter (cellvalue, options, rowObject)
			{

				var jam=0;
				var jam1="";
				var tot= (options.rowData.quantity * options.rowData.buyPrice);
				var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
				for (var i = 0; i < count; i++) {

					var quantity = allRowsInGrid1[i].quantity;
					params["quantity"+i] = quantity;

					var buyPrice = allRowsInGrid1[i].buyPrice;
					params["buyPrice"+i] = buyPrice;

					var totals1=((buyPrice)*(quantity));

					jam = jam + totals1;


				}
				if(count == 0){
					document.getElementById("total").value = tot;
					document.getElementById("grossTotal").value = tot;
					document.getElementById("duptotal").value = tot;
				}else{
					document.getElementById("total").value = jam;
					document.getElementById("grossTotal").value = jam;
					document.getElementById("duptotal").value = jam;
				}
				return tot;
			}

			$.each(jsonData,function(i,v)
					{
				
				$("#jqGrid").jqGrid({

					datatype:"local",

					colNames: ["Cat Id","Product ID","Product Name","Company","Buy Price","M.R.P","Sale Price","Packing","Quantity","Unit","Total","BatchNo","ExpiryDate" ],

					colModel: [
					           { 	
					        	   name: "catId",
					        	   hidden:true

					           },
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
					        	   width: 140,
					        	   editable: true,
					        	   edittype:"text", editoptions:{
						                  size: 25, maxlengh: 30,
						                  dataInit: function(element) {
						                      $(element).keypress(function(e){
						                           if (e.which != 8 && e.which != 0 && e.which != 46  && (e.which < 48 || e.which > 57)) {
						                              return false;
						                           }
						                      });
						                  }
						        	 }
					           },
					           {
					        	   name: "mrp",
					        	   width: 140,
					        	   editable: true,
					        	   edittype:"text", editoptions:{
						                  size: 25, maxlengh: 30,
						                  dataInit: function(element) {
						                      $(element).keypress(function(e){
						                    	  if (e.which != 8 && e.which != 0 && e.which != 46  && (e.which < 48 || e.which > 57)) {
						                              return false;
						                           }
						                      });
						                  }
						        	 }
					           },	


					           {
					        	   name:  "salePrice",
					        	   width: 140,
					        	   editable: true,
					        	   edittype:"text", editoptions:{
						                  size: 25, maxlengh: 30,
						                  dataInit: function(element) {
						                      $(element).keypress(function(e){
						                    	  if (e.which != 8 && e.which != 0 && e.which != 46  && (e.which < 48 || e.which > 57)) {
						                              return false;
						                           }
						                      });
						                  }
						        	 }
					           },

					           {
					        	   name: "weight",
					        	   width: 100,

					           },

					           {
					        	   name: "quantity",
					        	   width: 140,
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
					           
					           {
					        	   name: "unitName",
					        	   width: 140,
					        	   editable: false
					           },

					           {
					        	   name : 'Total',
					        	   formatter: sumFmatter,
					        	   width: 150,

					           },
					           {
					        	   label : 'batchNo',
					        	   name: "batchNo",
					        	   width: 150,
					        	   editable:true,


					           },
					           {
					        	   label : 'expiryDate',
					        	   name: "expiryDate",
					        	   index:'Date',
					        	   width: 200,
					        	   editable:true,
					        	   edittype:"text",
					        	   editrules:{date:true, minValue:0}, datefmt:'Y-m-d',
					        	   editoptions:{dataInit: function (elem) {$(elem).datepicker({dateFormat:"yy-mm-dd",minDate :new Date()});} }

					           }
					           ],


					           sortorder : 'desc',

					           multiselect: false,	
					           loadonce: false,
					           rownumbers:true,
					           forcePlaceholderSize: true ,
					           'cellEdit':true,
					           afterSaveCell: function  grossTotal() {
					        	   
					        	   /* 	Calculation of total after editing quantity*/
					        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
					        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
					        	   var quantity = rowData['quantity'];
					        	   var buyPrice = rowData['buyPrice'];
					        	   if(quantity <=0)
					        		{
					        		   alert("Please Enter Quantity greater than 0");
					        		}
					        	   else
					        		{
					        		   var tota = quantity * buyPrice;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);

					        		}
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
					newrow = true;
					
	/*				if (prodName == jsonData[i].productName && com == jsonData[i].manufacturer && packing == jsonData[i].weight) {

						newrow=false;
						alert("Product Name Already Inserted !!!");
						var grid = jQuery("#jqGrid");
						grid.trigger("reloadGrid");
						break;
					}
					else
					{
						newrow = true;
					}*/
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
						
						
						{
							closeAfterdel:true,
							checkOnUpdate : true,
							checkOnSubmit : true,
							recreateForm: true,
							
							afterComplete: function() {
								$('#list4').trigger( 'reloadGrid' );
							
							 
				        	   	 /*$('#jqGrid').trigger( 'reloadGrid' );

					        	  var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
					        	  var rowData = jQuery("#jqGrid").getRowData(rowId);
					        	  var quantity = rowData['quantity'];
					        	  var buyPrice = rowData['buyPrice'];

					        	  var tota = quantity * buyPrice;
					        	  $("#jqGrid").jqGrid("setCell", rowId, "total", tota);

					        	  var Total =0;
					        	  var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
					        	  var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
					        	  var AllRows=JSON.stringify(allRowsInGrid1);
					        	  for (var k = 0; k < count; k++) {
					        		  var Total1 = allRowsInGrid1[k].total;
					        		  Total = +Total + +Total1;
					        	  }
					        	 // document.getElementById("totalWithExpense").value = Total;
					        	  document.getElementById("grossTotal").value = Total;*/
							
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
				        		   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
				        	   }
				        	   else if(iGst == 0){
				        		   var  taxPercentage = Number(cGst) + Number(sGst);
				        		   var taxAmount = ((taxPercentage/100)*buyPrice);
				        		   var BuyPriceWithTaxAmount = Number(taxAmount) + Number(buyPrice);
				        		   var tota = quantity * BuyPriceWithTaxAmount;
				        		   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);

				        	   }

				        	   var Total =0;
				        	   var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
				        	   var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
				        	   var AllRows=JSON.stringify(allRowsInGrid1);
				        	   for (var k = 0; k < count; k++) {
				        		   var Total1 = allRowsInGrid1[k].Total;
				        		   Total = +Total + +Total1;
				        	   }
				        	   
				        	   document.getElementById("total").value = Total;
				        	   document.getElementById("grossTotal").value = Total;
				        	   document.getElementById("duptotal").value = Total;
							
							
						}
				        	   
						},
						
						
						

						// options for the Delete Dialogue
						{
							closeAfterdel:true,
							checkOnUpdate : true,
							checkOnSubmit : true,
							recreateForm: true,
							
							
							onSelectRow: function(id) {
								if (id && id !== lastSel) {
									jQuery("#jqGrid").saveRow(lastSel, true, 'clientArray');
									jQuery("#jqGrid").editRow(id, true);

									lastSel = id;
									console.log(id);
								}
							},
							
							
							afterSubmit: function() {
								$('#jqGrid').trigger( 'reloadGrid' );
							},
							reloadAftersubmit:true,	
							errorTextFormat: function (data) {
								return 'Error: ' + data.responseText
							},

						});
				
				

				// grid refresh code	

					});
				}); 
	}

	/*+++++++++++ grid for Tax ++++++++++++*/
	else if(document.getElementById('vat').checked){

		var params= {};
		itemparams={};
	/*	productId = $('#proName').val();
		$("#proName option:selected").each(function() {
			selectedVal = $(this).text();
		});

		var splitText = selectedVal.split(",");
		var proName = splitText[0];
		var company = splitText[1];
		var weight = splitText[2];*/
		
		productId = $('#proName').val();
		var proName=productId.split(",")[0];
		var company=productId.split(",")[1];
		var weight=productId.split(",")[2];

		itemparams["proName"]= proName;
		itemparams["company"]= company;
		itemparams["weight"]= weight;
		itemparams["productId"]= productId;

		var count=0;
		var newrow;
		var rowId;
		var	taxPercentage;
		itemparams["methodName"] = "getProductDetailsForGoodsReceiveForTax";
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

					colNames: [	"Cat ID","Product ID","Product Name","Company","HSN","Buy Price","M.R.P","Sale Price","Packing","Unit","CGST %","SGST %","IGST %","Quantity","Total","BatchNo","ExpiryDate" ],

					colModel: [
					           { 	
					        	   name: "catId",
					        	   hidden:true


					           },
					           { 	
					        	   name: "productID",
					        	   hidden:true


					           },
					           { 	
					        	   name: "productName",
					        	   width:120,

					           },
					           { 	
					        	   name: "manufacturer",
					        	   width:120

					           },
					           { 	
					        	   name: "hsn",
					        	   width:120

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
					        	   name:  "weight",
					        	   width: 140,
					        	   editable: true
					           },

					           {
					        	   name: "unitName",
					        	   width: 100,

					           },
					           {
					        	   name: "cGst",
					        	   width: 80,
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
					        	   width: 120,
					        	   editable: true

					           },

					           {
					        	   name : 'Total',
					        	   width: 150,

					           },
					           {
					        	   label : 'batchNo',
					        	   name: "batchNo",
					        	   width: 150,
					        	   editable:true,


					           },
					           {
					        	   label : 'expiryDate',
					        	   name: "expiryDate",
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
					        		   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);
					        	   }
					        	   else if(iGst == 0){
					        		   var  taxPercentage = Number(cGst) + Number(sGst);
					        		   var taxAmount = ((taxPercentage/100)*buyPrice);
					        		   var BuyPriceWithTaxAmount = Number(taxAmount) + Number(buyPrice);
					        		   var tota = quantity * BuyPriceWithTaxAmount;
					        		   $("#jqGrid").jqGrid("setCell", rowId, "Total", tota);

					        	   }

					        	   var Total =0;
					        	   var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
					        	   var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
					        	   var AllRows=JSON.stringify(allRowsInGrid1);
					        	   for (var k = 0; k < count; k++) {
					        		   var Total1 = allRowsInGrid1[k].Total;
					        		   Total = +Total + +Total1;
					        	   }
					        	   document.getElementById("total").value = Total;
					        	   document.getElementById("grossTotal").value = Total;
					        	   document.getElementById("duptotal").value = Total;

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
}


function batchNoValidate(){
	var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
	var rowData = jQuery("#jqGrid").getRowData(rowId);
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {
		var catId = allRowsInGrid[i].catId;
		if (catId != 1){
			var batchNo = allRowsInGrid[i].batchNo;
			var expiryDate = allRowsInGrid[i].expiryDate;
			if(batchNo == undefined || batchNo == ""){
				alert("Please Enter Batch number At Column No :: "+ ++i );
				return true;
			}
			else{
				if(expiryDate == undefined || expiryDate == ""){
					alert("Please Enter Expiry Date At Column No :: "+ ++i );
					return true;
				}
			}
		}
	}
	addingGoodsReceive()
}

function batchNoValidateForBooked(){
	var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
	var rowData = jQuery("#jqGrid1").getRowData(rowId);
	var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid1').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {
		var catId = allRowsInGrid[i].catId;
		if (catId != 1){
			var batchNo = allRowsInGrid[i].batchNo;
			var expiryDate = allRowsInGrid[i].expiryDate;
			if(batchNo == undefined || batchNo == ""){
				alert("Please Enter Batch number At Column No :: "+ ++i );
				return true;
			}
			else{
				if(expiryDate == undefined || expiryDate == ""){
					alert("Please Enter Expiry Date At Column No :: "+ ++i );
					return true;
				}
			}
		}
	}
	addBookedGoodsReceive()
}

