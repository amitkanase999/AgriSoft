//sale return for Fetilizer
function fertilizerSale()
{
	if(document.salereturn.bill_no.value != "")
	{
		saleReturn();
	}
	else
	{
		alert("Select Bill Number")
	}
}

function saleReturn(){
	document.getElementById("save").disabled = true;
	var params={};
	var bill_no=$('#bill_no').val;
	var input1 = document.getElementById('bill_no'),
	list = document.getElementById('bill_no_drop'),
	i,bill_no;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			bill_no = list.options[i].getAttribute('data-value');
		}
	}	

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pkfertilizerBillId = allRowsInGrid[i].pkfertilizerBillId;
		params["pkfertilizerBillId"+i] = pkfertilizerBillId;

		var customerName = allRowsInGrid[i].customerName;
		params["customerName"+i] = customerName;

		var availbleQuantity = allRowsInGrid[i].availbleQuantity;
		params["availbleQuantity"+i] = availbleQuantity;

		var productName = allRowsInGrid[i].productName;
		params["productName"+i] = productName;

		var company = allRowsInGrid[i].company;
		params["company"+i] = company;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var quantity1 = allRowsInGrid[i].quantity1;
		params["quantity1"+i] = quantity1;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var quantity1 = allRowsInGrid[i].quantity1;
		params["quantity1"+i] = quantity1;

		var taxPercentage = allRowsInGrid[i].taxPercentage;
		params["taxPercentage"+i] = taxPercentage;

		var aadhar = allRowsInGrid[i].aadhar;
		params["aadhar"+i] = aadhar;

		var customerName = allRowsInGrid[i].customerName;
		params["customerName"+i] = customerName;

		var insertDate = allRowsInGrid[i].insertDate;
		params["insertDate"+i] = insertDate;
	}
	params["bill_no"] = bill_no;
	params["count"] = count;
	params["methodName"] = "returnSale";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		location.reload();
		window.open("FertilizerSaleRetutnPDF.jsp");

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

//sale return for seed

function seedSale()
{
	if(document.seedform.seedBillNo.value != "")
	{
		seedReturn();
	}
	else
	{
		alert("Select Bill Number")
	}
}

function seedReturn(){
	document.getElementById("save1").disabled = true;
	var params={};
	var input1 = document.getElementById('seedBillNo'),
	list = document.getElementById('seedBillNo_drop'),
	i,seedBillNo;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			seedBillNo = list.options[i].getAttribute('data-value');
		}
	}	
	var count = jQuery("#jqGridSeed").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGridSeed').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pkSeedBillingId = allRowsInGrid[i].pkSeedBillingId;
		params["pkSeedBillingId"+i] = pkSeedBillingId;

		var availbleQuantity = allRowsInGrid[i].availbleQuantity;
		params["availbleQuantity"+i] = availbleQuantity;

		var productName = allRowsInGrid[i].productName;
		params["productName"+i] = productName;

		var company = allRowsInGrid[i].company;
		params["company"+i] = company;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var quantity1 = allRowsInGrid[i].quantity1;
		params["quantity1"+i] = quantity1;

		var batchNo = allRowsInGrid[i].batchNo;
		params["batchNo"+i] = batchNo;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var taxPercentage = allRowsInGrid[i].taxPercentage;
		params["taxPercentage"+i] = taxPercentage;

		var aadhar = allRowsInGrid[i].aadhar;
		params["aadhar"+i] = aadhar;

		var customerName = allRowsInGrid[i].customerName;
		params["customerName"+i] = customerName;

		var insertDate = allRowsInGrid[i].insertDate;
		params["insertDate"+i] = insertDate;
	}
	params["bill_no"] = seedBillNo;
	params["count"] = count;
	params["methodName"] = "seedSaleReturn";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		location.reload();	
		document.getElementById("save1").disabled = false;

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

//Pesticide return for seed

function PesticideSale()
{
	if(document.Pesticideform.pestiBillNo.value != "")
	{
		pesticideReturn();
	}
	else
	{
		alert("Select Bill Number")
	}
}

function pesticideReturn(){
	document.getElementById("save2").disabled = true;
	var params={};
	var input1 = document.getElementById('pestiBillNo'),
	list = document.getElementById('pestiBillNo_drop'),
	i,pestiBillNo;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			pestiBillNo = list.options[i].getAttribute('data-value');
		}
	}	
	var count = jQuery("#jqGridPesti").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGridPesti').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pkPesticideBillingId = allRowsInGrid[i].pkPesticideBillingId;
		params["pkPesticideBillingId"+i] = pkPesticideBillingId;

		var availbleQuantity = allRowsInGrid[i].availbleQuantity;
		params["availbleQuantity"+i] = availbleQuantity;

		var productName = allRowsInGrid[i].productName;
		params["productName"+i] = productName;

		var company = allRowsInGrid[i].company;
		params["company"+i] = company;

		var weight = allRowsInGrid[i].weight;
		params["weight"+i] = weight;

		var quantity1 = allRowsInGrid[i].quantity1;
		params["quantity1"+i] = quantity1;

		var batchNo = allRowsInGrid[i].batchNo;
		params["batchNo"+i] = batchNo;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice"+i] = salePrice;

		var mrp = allRowsInGrid[i].mrp;
		params["mrp"+i] = mrp;

		var taxPercentage = allRowsInGrid[i].taxPercentage;
		params["taxPercentage"+i] = taxPercentage;

		var aadhar = allRowsInGrid[i].aadhar;
		params["aadhar"+i] = aadhar;

		var customerName = allRowsInGrid[i].customerName;
		params["customerName"+i] = customerName;

		var insertDate = allRowsInGrid[i].insertDate;
		params["insertDate"+i] = insertDate;

	}
	params["bill_no"] = pestiBillNo;
	params["count"] = count;
	params["methodName"] = "pesticideSaleReturn";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		location.reload();
		document.getElementById("save2").disabled = false;

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
//Fetch data for sale return fron bill table
function fetchDataForSale(){

	var input = document.getElementById('bill_no'),
	list = document.getElementById('bill_no_drop'),
	i,bill_no;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			bill_no = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params["methodName"] = "getAllFertiIetmByBillNo";

	params["bill_no"]= bill_no;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ 


		var jsonData = $.parseJSON(data);

		$("#jqGrid").jqGrid("clearGridData", true).trigger("reloadGrid");

		$.each(jsonData,function(i,v)
				{
			$("#jqGrid").jqGrid({

				datatype:"local",

				colNames: ["pk fertilizerBill ID","fkGoodsReceive","catId","customerName","Adhar No","Product Name","Company","Packing","Sale Price","M.R.P","Available Quantity","Return Quantity","Sale Date","Tax %" ],

				colModel: [
				           { 	
				        	   name: "pkfertilizerBillId",
				        	   hidden:true
				           },
				           { 	
				        	   name: "fkGoodsReceive",
				        	   hidden:true
				           },
				           { 	
				        	   name: "catId",
				        	   hidden:true
				           },
				           { 	
				        	   name: "customerName",
				        	   hidden:false
				           },
				           {
				        	   name: "aadhar",
				        	   width: 90
				           },
				           { 	
				        	   name: "productName",
				        	   width:100,
				           },

				           {
				        	   name: "company",
				        	   width: 80
				           },
				           {
				        	   name: "weight",
				        	   width: 60
				           },

				           {
				        	   name: "salePrice",
				        	   width: 60,
				           },

				           {
				        	   name : 'mrp',
				        	   width: 60
				           },

				           {
				        	   name: "availbleQuantity",
				        	   width: 40,

				           },
				           {
				        	   name: "quantity1",
				        	   width: 40,
				        	   editable:true,
				        	 
				           },

				           {
				        	   name: "insertDate",
				        	   width:60
				           },
				           {
				        	   name: "taxPercentage",
				        	   width:40
				           }


				           ],


				           sortorder : 'desc',

				           multiselect: false,	
				           loadonce: false,
				           rownumbers:true,
				           forcePlaceholderSize: true ,
				           'cellEdit':true,
				           viewrecords: true,
				           shrinkToFit:true,
				           rowNum: 10,
				           pager: "#jqGridPager",
				           sortorder: "desc",

				           afterSaveCell: function  grossTotal() {
				        	   /* 	Calculation of total after editing quantity*/

				        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
				        	   var rowData = jQuery("#jqGrid").getRowData(rowId);
				        	   var stock = rowData['availbleQuantity'];
				        	   var returning = rowData['quantity1'];
				        	   var quantity=0;


				        	   if ( Number(returning) > Number(stock)){

				        		   alert("Available stock = "+stock);
				        		   $("#jqGrid").jqGrid("setCell", rowId, "quantity1", quantity);
				        	   }
				           }
			});

			$("#jqGrid").addRowData(i,jsonData[i]);

			$('#jqGrid').navGrid('#jqGridPager',
					// the buttons to appear on the toolbar of the grid
					{edit: true, add: false,del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
					// options for the Edit Dialog

					{
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
		function validatePositive(value, column) {
			var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
			var rowData = jQuery("#jqGrid").getRowData(rowId);
			var quan = rowData['availbleQuantity']; 	

			if (value > quan)
				return [false, "Return Quantity Must Be Less Than "+quan+ " !!!"];
			else
				return [true, ""];
		}   

			}); 

}


//Seed billing details for sale return
function fetchSeedDataForSale(){

	var input = document.getElementById('seedBillNo'),
	list = document.getElementById('seedBillNo_drop'),
	i,bill_no;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			bill_no = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params["methodName"] = "getAllSeedBillinfDetailsByBillNo";

	params["bill_no"]= bill_no;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ 


		var jsonData = $.parseJSON(data);

		$("#jqGridSeed").jqGrid("clearGridData", true).trigger("reloadGrid");



		$.each(jsonData,function(i,v)
				{
			$("#jqGridSeed").jqGrid({

				datatype:"local",

				colNames: ["pk SeedBill ID","catId","customerName","Adhar No","Product Name","Company","Packing","Batch Number","Sale Price","M.R.P","Available Quantity","Return Quantity","Sale Date","Tax %" ],

				colModel: [
				           { 	
				        	   name: "pkSeedBillingId",
				        	   hidden:true
				           },

				           { 	
				        	   name: "catId",
				        	   hidden:true
				           },
				           { 	
				        	   name: "customerName",
				        	   hidden:true
				           },
				           {
				        	   name: "aadhar",
				        	   width: 90
				           },
				           { 	
				        	   name: "productName",
				        	   width:100,
				           },

				           {
				        	   name: "company",
				        	   width: 80
				           },
				           {
				        	   name: "weight",
				        	   width: 60
				           },
				           {
				        	   name: "batchNo",
				        	   width: 60
				           },

				           {
				        	   name: "salePrice",
				        	   width: 60,
				           },

				           {
				        	   name : 'mrp',
				        	   width: 60
				           },

				           {
				        	   name: "availbleQuantity",
				        	   width: 40,

				           },
				           {
				        	   name: "quantity1",
				        	   width: 40,
				        	   editable:true,
				           },

				           {
				        	   name: "insertDate",
				        	   width:60
				           },
				           {
				        	   name: "taxPercentage",
				        	   width:40
				           }

				           ],


				           sortorder : 'desc',

				           multiselect: false,	
				           loadonce: false,
				           rownumbers:true,
				           forcePlaceholderSize: true ,
				           'cellEdit':true,
				           viewrecords: true,
				           width: 1200,
				           height: 250,
				           rowNum: 10,
				           pager: "#jqGridPagerSeed",
				           sortorder: "desc",
			});




			$("#jqGridSeed").addRowData(i,jsonData[i]);


			$('#jqGridSeed').navGrid('#jqGridPagerSeed',
					// the buttons to appear on the toolbar of the grid
					{edit: true, add: false,del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
					// options for the Edit Dialog

					{
						editCaption: "The Edit Dialog",
						recreateForm: true,
						checkOnUpdate : true,
						checkOnSubmit : true,
						closeAfteredit: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						},


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
								jQuery("#jqGridSeed").saveRow(lastSel, true, 'clientArray');
								jQuery("#jqGridSeed").editRow(id, true);

								lastSel = id;
								console.log(id);
							}
						}

					});

			// grid refresh code	

				});
		function validatePositive(value, column) {
			var rowId =$("#jqGridSeed").jqGrid('getGridParam','selrow');  
			var rowData = jQuery("#jqGridSeed").getRowData(rowId);
			var quan = rowData['availbleQuantity']; 	

			if (value <= quan)
				return [true, ""];	
			else
				return [false, "Return Quantity Must Be Less Than "+quan+ " !!!"];
		}   

			}); 

}




//Pesticide billing details for sale return
function fetchPesticideDataForSale(){

	var input = document.getElementById('pestiBillNo'),
	list = document.getElementById('pestiBillNo_drop'),
	i,bill_no;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			bill_no = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params["methodName"] = "getAllPesticideBillingDetailsByBillNo";
	params["bill_no"]= bill_no;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ 


		var jsonData = $.parseJSON(data);

		$("#jqGridPesti").jqGrid("clearGridData", true).trigger("reloadGrid");
		$.each(jsonData,function(i,v)
				{
			$("#jqGridPesti").jqGrid({

				datatype:"local",

				colNames: ["pk PestiBill ID","catId","customerName","Adhar No","Product Name","Company","Packing","Batch Number","Sale Price","M.R.P","Available Quantity","Return Quantity","Sale Date","Tax %" ],

				colModel: [
				           { 	
				        	   name: "pkPesticideBillingId",
				        	   hidden:true
				           },

				           { 	
				        	   name: "catId",
				        	   hidden:true
				           },
				           { 	
				        	   name: "customerName",
				        	   hidden:true
				           },
				           {
				        	   name: "aadhar",
				        	   width: 90
				           },
				           { 	
				        	   name: "productName",
				        	   width:100,
				           },

				           {
				        	   name: "company",
				        	   width: 80
				           },
				           {
				        	   name: "weight",
				        	   width: 60
				           },
				           {
				        	   name: "batchNo",
				        	   width: 60
				           },

				           {
				        	   name: "salePrice",
				        	   width: 60,
				           },

				           {
				        	   name : 'mrp',
				        	   width: 60
				           },

				           {
				        	   name: "availbleQuantity",
				        	   width: 40,

				           },
				           {
				        	   name: "quantity1",
				        	   width: 40,
				        	   editable:true,
				           },

				           {
				        	   name: "insertDate",
				        	   width:60
				           },
				           {
				        	   name: "taxPercentage",
				        	   width:40
				           }

				           ],


				           sortorder : 'desc',

				           multiselect: false,	
				           loadonce: false,
				           rownumbers:true,
				           forcePlaceholderSize: true ,
				           'cellEdit':true,
				           viewrecords: true,
				           width: 1200,
				           height: 250,
				           rowNum: 10,
				           pager: "#jqGridPagerPesti",
				           sortorder: "desc",
			});

			$("#jqGridPesti").addRowData(i,jsonData[i]);

			$('#jqGridPesti').navGrid('#jqGridPagerPesti',
					// the buttons to appear on the toolbar of the grid
					{edit: true, add: false,del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
					// options for the Edit Dialog

					{
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
						closeAfterdel:true,
						recreateForm: true,
						errorTextFormat: function (data) {
							return 'Error: ' + data.responseText
						},

						onSelectRow: function(id) {
							if (id && id !== lastSel) {
								jQuery("#jqGridPesti").saveRow(lastSel, true, 'clientArray');
								jQuery("#jqGridPesti").editRow(id, true);

								lastSel = id;
								console.log(id);
							}
						}
					});

			// grid refresh code	

				});
		function validatePositive(value, column) {
			var rowId =$("#jqGridPesti").jqGrid('getGridParam','selrow');  
			var rowData = jQuery("#jqGridPesti").getRowData(rowId);
			var quan = rowData['availbleQuantity']; 	

			if (value > quan)
				return [false, "Return Quantity Must Be Less Than "+quan+ " !!!"];
			else
				return [true, ""];
		}   
			}); 
}
