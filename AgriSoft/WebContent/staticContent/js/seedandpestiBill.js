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
/*++++++++++++++ To fetch product details as per batch num and stock for CASH++++++++++++++*/
function fechProductDetailsAsPerBatch(){
	var params= {};
	productId = $('#batchNo').val();
	$("#batchNo option:selected").each(function() {
		selectedVal = $(this).text();
	});

	var splitText = selectedVal.split(",");
	var batchNum = splitText[1];
	var stock = splitText[3];

	params["batchNum"]= batchNum;
	params["stock"]= stock;
	params["methodName"] = "fetchProductDetailAsPerBatchNumberAndStockForSeedPestiBilling";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);

		// $("#list4").jqGrid("clearGridData", true).trigger("reloadGrid");

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
				var count = jQuery("#list5").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#list5').getGridParam('data');
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

			$("#list5").jqGrid({
				datatype: "local",
				colNames:['pk_goodre_id','supp_id','cat_id','BarcodeNO','ItemName','CompanyName','Expiry Date','Packing', 'Quantity', 'UnitPrice','MRP','TaxPercentage' ,'Total'],
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

				          {	name:'expiryDate',
				        	  width:100,
				        	  editable: true

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
				          width: 1300,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function () {
				        	  var rowId =$("#list5").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#list5").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];

				        	  var tota = quantity * salePrice;

				        	  $("#list5").jqGrid("setCell", rowId, "total", tota);
				          },

				          pager: "#jqGridPager",

			});

			$("#list5").addRowData(i+1,jsonData[i]);
			$('#list5').navGrid('#jqGridPager',

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

/*+++++++++++++++++++++ fetch details for credit ++++++++++++++++++++++++++++++*/
function gridForCredit(){

	var params= {};
	/*$("#proName1 option:selected").each(function() {
		selectedVal = $(this).text();
	});

	var splitText = selectedVal.split(",");
	var proName = splitText[0];
	var batchno = splitText[1];
	var quantity = splitText[2];*/
	
	productId = $('#proName1').val();
	
	var proName = productId.split(",")[0];
	var batchno = productId.split(",")[1];
	var quantity = productId.split(",")[3];

	params["proName"]= proName;
	params["batchno"]= batchno;
	params["quantity"]= quantity;

	var count=0;
	var newrow;
	var rowId;

	params["methodName"] = "getProductDetailsforSeeds";
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
					var newSalePrice = Number(options.rowData.salePrice) + Number(taxcalculation);
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;


				var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#credit1').getGridParam('data');
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

						var taxcal = (vatPercentage/100) * Number(salePrice);
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

			count = jQuery("#credit1").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit1").jqGrid('getGridParam','data');
			var ids = jQuery("#credit1").jqGrid('getDataIDs');
			var batchName;
			var proName;
			var company;
			var weight;
			for (var j = 0; j < count; j++) 
			{
				batchName = rowdata[j].batchNumber;
				proName = rowdata[j].itemName;
				company = rowdata[j].companyName;
				weight = rowdata[j].weight;

				var rowId = ids[j];
				var rowData = jQuery('#credit1').jqGrid ('getRowData', rowId);

				if (batchName == jsonData.offer.batchNumber  && proName == jsonData.offer.itemName && company == jsonData.offer.companyName && weight == jsonData.offer.weight) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#credit1");
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

				$("#credit1").addRowData(count,jsonData.offer);

			}

			$("#credit1").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','BatchNo','ExpDate','Packing','Unit','UnitPrice','MRP','Quantity', 'Total','AvailableQty'],
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
				          {	name:'batchNumber',
				        	  width:150,
				        	  editable: true

				          }, 
				          {	name:'expdate',
				        	  width:150,

				          }, 
				          {	name:'weight',
				        	  width:100,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },

				          {	name:'unitName',
				        	  width:70,
				        	  editable: true

				          },


				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
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
				                    	  if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
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
				          width: 1300,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function  grossTotal() {
				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#credit1").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit1").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var availableQty = rowData['availableQty'];
				        	  
				        	  
				        	  
				        	 // productId = $('#proName1').val();

				        		/*$("#proName1 option:selected").each(function() {
				        			selectedVal = $(this).text();
				        		});

				        		//var splitText = selectedVal.split("=");
				        		var splitText = selectedVal.split(",");
				        		var stock = splitText[2];*/
				        	  
				        	 // var stock = productId.split(",")[3];
				        		
				        		
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
				        		  $("#credit1").jqGrid("setCell", rowId, "quantity", quantity);
				        		  $("#credit1").jqGrid("setCell", rowId, "total", total);
				        		  
				        		  var tota = quantity * salePrice;
				        		  $("#credit1").jqGrid("setCell", rowId, "total", tota);
				        		  var Total =0;
				        		  var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				        		  var allRowsInGrid1 = $('#credit1').getGridParam('data');
				        		  var AllRows=JSON.stringify(allRowsInGrid1);
				        		  for (var k = 0; k < count; k++) {
				        			  var Total1 = allRowsInGrid1[k].total;
				        			  Total = +Total + +Total1;
				        		  }
				        		  
				        		  document.getElementById("totalWithExpense1").value = Total;
				        		  document.getElementById("grossTotal1").value = Total;
				        		  return false;
				        	  }
				        	  
				        	  
				        		  var tota = quantity * salePrice;
				        		  $("#credit1").jqGrid("setCell", rowId, "total", tota);
				        		  var Total =0;
				        		  var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				        		  var allRowsInGrid1 = $('#credit1').getGridParam('data');
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
				          pager: "#jqGridPagerCeditCustomer",
			});

			if(count==0 || count==null)
			{
				$("#credit1").addRowData(0,jsonData.offer);
			}
			$('#credit1').navGrid('#jqGridPagerCeditCustomer',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",
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

				        	  var rowId =$("#credit1").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit1").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];
				        	  var tota = quantity * salePrice;
				        	  $("#credit1").jqGrid("setCell", rowId, "total", tota);
				        	  var Total =0;
				        	  var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#credit1').getGridParam('data');
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




/*+++++++++++++++++++++++ Adding Cash Customer Bill ++++++++++++++++++++++++++++*/
function addSeedAndPestiBill(){
	
	if(document.pestiBill.customerName.value!="")
	{
		
		if(document.pestiBill.proName.value!="")
		{
			
			if( document.pestiBill.paymentMode.value!="selected")
			{
				
				if( document.pestiBill.paymentMode.value=="cash")
				{
					addSeedAndBill();
				}
				else if( document.pestiBill.paymentMode.value=="cheque")
				{
					if(document.pestiBill.chequeNum.value !="")
					{
						if(document.pestiBill.nameOnCheck.value !="")
						{
							addSeedAndBill();

						}
						else
						{
							alert("Enter Name on Cheque");

						}
					}
					else
					{
						alert("Enter Cheque Number");
						return false;
					}
				}
				else if( document.pestiBill.paymentMode.value=="card")
				{
					if(document.pestiBill.cardNum.value !="")
					{
						addSeedAndBill();

					}
					else
					{
						alert("Enter Card Number");

					}
				}
				else if( document.pestiBill.paymentMode.value=="neft")
				{
					if(document.pestiBill.accNum.value !="")
					{
						if(document.pestiBill.bankName.value !="") 
						{
							addSeedAndBill();
							return false;
						}
						else
						{
							alert("Enter Bank Name");
							return false;
						}
					}
					else
					{
						alert("Enter Account Number ");
						return false;
					}
				}

			}
			else
			{
				addSeedAndBill();
			}
		}
		else
		{
			alert("Please Select Product");
		}
	}
	else
	{
		alert("Please Enter Customer Name");
	}

}

function addSeedAndBill(){
	//document.pestiBill.btn1.disabled = true;
	document.getElementById("save1").disabled = true;
	var params = {};
	
	var catid=2;
	var paycusttype="Cash";
	var customertype=$('#customertype1').val();
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
	var count = jQuery("#credit").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#credit').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var PkGoodreceiveId = allRowsInGrid[i].PkGoodreceiveId;
		params["PkGoodreceiveId"+i] = PkGoodreceiveId;

		var supplier_id = allRowsInGrid[i].supplier_id;
		params["supplier_id"+i] = supplier_id;

		//var cat_id = allRowsInGrid[i].cat_id;
		var cat_id=2;
		params["cat_id"+i] = cat_id;

		var barcodeNo = allRowsInGrid[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;

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

		var expiryDate = allRowsInGrid[i].expdate;
		params["expiryDate"+i] = expiryDate;

		var batchNumber = allRowsInGrid[i].batchNumber;
		params["batchNumber"+i] = batchNumber;


		if(batchNumber==undefined || batchNumber== null || batchNumber == "" ){
			params["batchNumber"+i] = "'N/A'";
		}
		if(expiryDate==undefined || expiryDate== null || expiryDate == "" ){
			params["expiryDate"+i] = 0000-00-00;
		}

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
	if(transExpense==undefined || transExpense== null || transExpense == "" ){
		transExpense=0;
	}
	if(hamaliExpense==undefined || hamaliExpense== null || hamaliExpense == "" ){
		hamaliExpense=0;
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
	params["methodName"] = "addingCashSeedAndPesticideBill";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		
		window.open("SeedAndPesticideBillPDF.jsp");
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


/*++++++++++++++  Adding credit customer bill ++++++++++++++++++*/
function addBillForCreditCustomer(){
	
	if ( document.seedbillingform.customertype1.value == "Regular" )
	{
		
		if ( document.pestiBillforCredit.creditCustomer.value != "" )
		{
			addBillForCredit();
		}
		else
		{
			alert("Please Select Regular Customer Name");
			return false;
		}
		
	}
	else
	{
		if ( document.pestiBillforCredit.creditwholsaleCustomer.value != "" )
		{
			addBillForCredit();
		}
		else
		{
			alert("Please Select Wholesale Customer Name");
			return false;
		}
	}
	
}

function addBillForCredit(){

	//document.pestiBillforCredit.custbtn.disable= true ;
	document.getElementById("save").disabled = true;
	var params = {};

	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop1'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var paycusttype="Credit";
	var customertype=$('#customertype1').val();
	var custname=$('#creditCustomer').val();
	var fkCreditCustomerID = creditCustomer;
	var creditCustomerName = $('#creditCustomer').val();
	var creditwholsaleCustomer=$('#creditwholsaleCustomer').val();
	var creditCustomerHiddenName = $('#customerNameHidden').val();
	var village = $('#village1').val();
	var contactNo = $('#contactNo1').val();
	var aadhar = $('#aadharNo1').val();
	var billdate = $('#billdate1').val();
	var transExpense = $('#transExpence1').val();
	var hamaliExpense = $('#hamaliExpence1').val();
	var grossTotal = $('#grossTotal1').val();
	var total = $('#totalWithExpense1').val();
	var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#credit1').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var PkGoodreceiveId = allRowsInGrid[i].PkGoodreceiveId;
		params["PkGoodreceiveId"+i] = PkGoodreceiveId;

		var supplier_id = allRowsInGrid[i].supplier_id;
		params["supplier_id"+i] = supplier_id;

		//var cat_id = allRowsInGrid[i].cat_id;
		var cat_id=2;
		params["cat_id"+i] = cat_id;

		var barcodeNo = allRowsInGrid[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;

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

		var expiryDate = allRowsInGrid[i].expdate;
		params["expiryDate"+i] = expiryDate;

		var batchNumber = allRowsInGrid[i].batchNumber;
		params["batchNumber"+i] = batchNumber;

		if(batchNumber==undefined || batchNumber== null || batchNumber == "" ){
			params["batchNumber"+i] = "'N/A'";
		}
		if(expiryDate==undefined || expiryDate== null || expiryDate == "" ){
			params["expiryDate"+i] = 0000-00-00;
		}

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
	if(transExpense==undefined || transExpense== null || transExpense == "" ){
		transExpense=0;
	}
	if(hamaliExpense==undefined || hamaliExpense== null || hamaliExpense == "" ){
		hamaliExpense=0;
	}
	
	if(creditCustomerName==null ||creditCustomerName==""||creditCustomerName==undefined)
	{
		params["creditCustomerName"] = creditwholsaleCustomer;
	}
	if(creditwholsaleCustomer==null ||creditwholsaleCustomer==""||creditwholsaleCustomer==undefined)
	{
		//wholesale customer name
		params["creditCustomerName"] = creditCustomerName; 
	}
	
	
	
	params["paycusttype"] = paycusttype;
	params["customertype"] = customertype;
	params["custname"] = custname;
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

	params["methodName"] = "addingSeedAndPesticideBill";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//document.pestiBillforCredit.custbtn.disabled =false;
		window.open("SeedAndPesticideBillPDF.jsp");
		location.reload();
		document.getElementById("save").disabled = true;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

//to show or hide customer name,village,contact number based on radio button choice(cash  or credit)
function myOnLoadFunction()
{
	$("#CreditCustDetail").hide();

}

function showCreditCustDetail()
{
	$("#CreditCustDetail").show();
	$("#ShowCashCustDetail").hide();
}

function showCashDetails()
{
	$("#ShowCashCustDetail").show();
	$("#CreditCustDetail").hide();
	location.reload(true)
}

//************** Credit Customer details like village,Name and Contact number***************//*

function customerDetail(){

	this.getVillageName = getVillageName;
	this.getContactNo = getContactNo;
	this.getName = getName;
	this.getAadhar = getAadhar;

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


/* ++++++++++++++++     Fetching product Name for Cash customer seed and pesti bill ++++++++++++++++++++*/
function garProductNameForCash(){

	var fk_cat_id1 = 2;
	$("#proName").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["methodName"] = "getAllProductByCategoriesForSeedAndPestiBill";

	params["fk_cat_id1"]= fk_cat_id1;

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#proName").append($("<option></option>").attr("value",count).text(v.product + ","+v.batchNum+","+v.quantity+","+v.expDate+",Packing="+v.weight)); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

/*++++++++++++++++++++++  fetching batch number and stock as per product Cash customer  ++++++++++++++++++++++*/
function getBatchNumberAndStock(){
	$("#batchNo").empty();
	$("#batchNo").append($("<option></option>").attr("value","").text("Select product"));
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
	params["methodName"] = "fetchBatchNumberAndStockForSeedPestiBilling";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var count = 1;
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#batchNo").append($("<option></option>").attr("value",count).text("Batch No = ,"+v.batchNum+", stock = ,"+v.stock)); 
			count++;
				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}


/* ++++++++++++++++     Fetching product Name for CREDIT customer seed and pesti bill ++++++++++++++++++++*/
function garProductNameForCredit(){

	var fk_cat_id1 = 2;
	$("#proName1").empty();
	$("#proName1").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["methodName"] = "getAllProductByCategoriesForSeedAndPestiBill";
	params["fk_cat_id1"]= fk_cat_id1;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#proName1").append($("<option></option>").attr("value",count).text(v.product + ","+v.batchNum+","+v.quantity+","+v.expDate+",Packing="+v.weight)); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});

}

/*++++++++++++++++++++++  fetching batch number and stock as per product CREDIT customer  ++++++++++++++++++++++*/
function getBatchNumberAndStock1(){
	$("#batchNo1").empty();
	$("#batchNo1").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	productId = $('#proName1').val();
	$("#proName1 option:selected").each(function() {
		selectedVal = $(this).text();
	});

	var splitText = selectedVal.split(",");

	var proName = splitText[0];
	var company = splitText[1];
	var weight = splitText[2];

	params["proName"]= proName;
	params["company"]= company;
	params["weight"]= weight;
	params["methodName"] = "fetchBatchNumberAndStockForSeedPestiBilling";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var count = 1;

		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#batchNo1").append($("<option></option>").attr("value",count).text("Batch No = ,"+v.batchNum+", stock = ,"+v.stock)); 
			count++;
				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
				}
			});
}

//FETCH PRODUCT DETAIL 23-5-17
function getProductDetailForSeedBill(){

	var params= {};
	productId = $('#proName').val();

/*	$("#proName option:selected").each(function() {
		selectedVal = $(this).text();
	});

	var splitText = selectedVal.split(",");
	var proName = splitText[0];
	var batchno = splitText[1];
	var quantity = splitText[2];*/
	
	var proName = productId.split(",")[0];
	var batchno = productId.split(",")[1];
	var quantity = productId.split(",")[3];

	params["proName"]= proName;
	params["batchno"]= batchno;
	params["quantity"]= quantity;

	var count=0;
	var newrow;
	var rowId;
	params["methodName"] = "getProductDetailsforSeeds";
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

					var taxcalculation = (tax/100)*Number(options.rowData.salePrice);
					var newSalePrice = Number(options.rowData.salePrice) + Number(taxcalculation);
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

						var taxcal = (vatPercentage/100) * Number(salePrice);
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

			count = jQuery("#credit").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit").jqGrid('getGridParam','data');
			var ids = jQuery("#credit").jqGrid('getDataIDs');


			var batchName;
			var proName;
			var company;
			var weight;
			for (var j = 0; j < count; j++) 
			{
				batchName = rowdata[j].batchNumber;
				proName = rowdata[j].itemName;
				company = rowdata[j].companyName;
				weight = rowdata[j].weight;

				var rowId = ids[j];
				var rowData = jQuery('#credit').jqGrid ('getRowData', rowId);
				newrow = true;
				
			/*	if (batchName == jsonData.offer.batchNumber && proName == jsonData.offer.itemName && company == jsonData.offer.companyName && weight == jsonData.offer.weight ) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#list4");
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
				$("#credit").addRowData(count,jsonData.offer);

			}
			$("#credit").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Unit','BatchNo','ExpDate', 'UnitPrice','MRP','Quantity' ,'Total','AvailableQty'],
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
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                           if (e.which != 8 && e.which != 0  && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
				                              return false;
				                           }
				                      });
				                  }
				        	 }

				          },

				          {	name:'unitName',
				        	  width:100,
				        	  editable: true

				          },
				          
				          {	name:'batchNumber',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'expdate',
				        	  width:100,
				        	  editable: true,
				        	/*  editrules:{date:true, minValue:0}, datefmt:'Y-m-d',
				        	  editoptions:{dataInit: function (elem) {$(elem).datepicker({dateFormat:"yy-mm-dd"});} }*/

				          },
				          
				          {	name:'salePrice',
				        	  width:150,
				        	  editable: true,
				        	  edittype:"text", editoptions:{
				                  size: 25, maxlengh: 30,
				                  dataInit: function(element) {
				                      $(element).keypress(function(e){
				                    	  if (e.which != 8 && e.which != 0  && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
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
				                    	  if (e.which != 8 && e.which != 0  && e.which != 46 &&  (e.which < 48 || e.which > 57)) {
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
				          width: 1300,
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
				        	  

				        	  //document.getElementById("duptotal").value = Total;

				        	  
				      	  productId = $('#proName').val();

				        		/*$("#proName option:selected").each(function() {
				        			selectedVal = $(this).text();
				        		});

				        		//var splitText = selectedVal.split("=");
				        		var splitText = selectedVal.split(",");
				        		var stock = splitText[2];*/
				      	  
				      	  		//var stock = productId.split(",")[3];
				        		
				        		
				        	  if( Number(quantity) > Number(availableQty))
				        	  {
				        		  
				        		  $.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				      					{
				      		
				      				var msg="Qantity less than Equal to Stock "+availableQty;
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
				        		  $(" #credit").jqGrid("setCell", rowId, "quantity", quantity);
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
				        		  document.getElementById("totalWithExpense").value = Total;
				        		  document.getElementById("grossTotal").value = Total;
				        	
				        		  return false;
				        	  }
				        	  
				        	  
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
				        		  document.getElementById("transExpence").value = zero;
				        		  document.getElementById("hamaliExpence").value = zero;
				        		  document.getElementById("totalWithExpense").value = Total;
				        		  document.getElementById("grossTotal").value = Total;
				        		  
				        	 
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

//get Seed Product detail as Per Barcode from goods receive for Cash
function getSeedProDetailAsPerBarcode(){

	var value = document.getElementById("seedBarcode").value;

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

					var taxcalculation = (tax/100)*Number(options.rowData.salePrice);
					var newSalePrice = Number(options.rowData.salePrice) + Number(taxcalculation);
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
						var taxcal = (vatPercentage/100) * Number(salePrice);
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

			count = jQuery("#credit").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit").jqGrid('getGridParam','data');
			var ids = jQuery("#credit").jqGrid('getDataIDs');
			var batchName;
			for (var j = 0; j < count; j++) 
			{
				batchName = rowdata[j].batchNumber;

				var rowId = ids[j];
				var rowData = jQuery('#credit').jqGrid ('getRowData', rowId);

				if (batchName == jsonData.offer.batchNumber) {
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
				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Batch Number','Unit','UnitPrice','MRP','CGST','SGST','IGST','Quantity', 'Total'],
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
				        	  editable: true

				          },
				          {	name:'batchNumber',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'unitName',
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

				          {	name:'cGst',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'sGst',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'iGst',
				        	  width:100,
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
				          width: 1300,
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
				        	  document.getElementById("totalWithExpense").value = Total;
				        	  document.getElementById("grossTotal").value = Total;

				          },
				          pager: "#jqGridPagerCash",
			});

			if(count==0 || count==null)
			{
				$("#credit").addRowData(0,jsonData.offer);
			}
			$('#credit').navGrid('#jqGridPagerCash',

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

//get Seed Product detail as Per Barcode from goods receive for Credit
function getSeedProDetailAsPerBarcodeForCredit(){
	var value = document.getElementById("seedCreditBarcode").value;

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
					var newSalePrice = Number(options.rowData.salePrice) + Number(taxcalculation);
					var tot= (Number(options.rowData.quantity) * Number(newSalePrice));
					if(isNaN(tot)){
						tot = 0;
					}
				}
				var jam=0;
				var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#credit1').getGridParam('data');
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

						var taxcal = (vatPercentage/100) * Number(salePrice);
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

			count = jQuery("#credit1").jqGrid('getGridParam', 'records'); 
			var rowdata =$("#credit1").jqGrid('getGridParam','data');
			var ids = jQuery("#credit1").jqGrid('getDataIDs');
			var batchName;
			for (var j = 0; j < count; j++) 
			{
				batchName = rowdata[j].batchNumber;
				var rowId = ids[j];
				var rowData = jQuery('#credit1').jqGrid ('getRowData', rowId);

				if (batchName == jsonData.offer.batchNumber) {
					newrow=false;
					alert("Product Name Already Inserted !!!");
					var grid = jQuery("#credit1");
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
				$("#credit1").addRowData(count,jsonData.offer);
			}
			$("#credit1").jqGrid({
				datatype: "local",

				colNames:['cat_id','ItemName','CompanyName','HSN','Packing','Batch Number','Unit', 'UnitPrice','MRP','CGST','SGST','IGST','Quantity','Total'],
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
				        	  editable: true

				          },
				          {	name:'batchNumber',
				        	  width:70,
				        	  editable: true

				          },
				          {	name:'unitName',
				        	  width:70,
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
				          {	name:'cGst',
				        	  width:100,
				        	  editable: true

				          },{	name:'sGst',
				        	  width:100,
				        	  editable: true

				          },
				          {	name:'iGst',
				        	  width:100,
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
				          width: 1300,
				          shrinkToFit:true,
				          rowheight: 300,
				          hoverrows: true,
				          rownumbers: true,
				          rowNum: 10,
				          'cellEdit':true,
				          afterSaveCell: function  grossTotal() {
				        	  /* 	Calculation of total after editing quantity*/

				        	  var rowId =$("#credit1").jqGrid('getGridParam','selrow');  
				        	  var rowData = jQuery("#credit1").getRowData(rowId);
				        	  var quantity = rowData['quantity'];
				        	  var salePrice = rowData['salePrice'];


				        	  var tota = quantity * salePrice;
				        	  $("#credit1").jqGrid("setCell", rowId, "total", tota);
				        	  var Total =0;
				        	  var count = jQuery("#credit1").jqGrid('getGridParam', 'records');
				        	  var allRowsInGrid1 = $('#credit1').getGridParam('data');
				        	  var AllRows=JSON.stringify(allRowsInGrid1);
				        	  for (var k = 0; k < count; k++) {
				        		  var Total1 = allRowsInGrid1[k].total;
				        		  Total = +Total + +Total1;
				        	  }
				        	  document.getElementById("totalWithExpense1").value = Total;
				        	  document.getElementById("grossTotal1").value = Total;
				          },
				          pager: "#jqGridPagerCeditCustomer",

			});

			if(count==0 || count==null)
			{
				$("#credit1").addRowData(0,jsonData.offer);
			}
			$('#credit1').navGrid('#jqGridPagerCeditCustomer',

					{ edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },

					{
						editCaption: "The Edit Dialog",
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


//fetch Seed product godown name wise for Cash
function fetchProductGodownWise(){


	var godownName=$('#godown').val();
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getSeedAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.batchno+",Qty=,"+v.quantity+" Exp Date =,"+v.expiryDate+",Packing = "+v.packing+","+v.unitName))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

//fetch Seed product godown name wise for credit
function fetchProductGodownWiseforCredit(){


	var godownName=$('#godown1').val();
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getSeedAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.batchno+",Qty=,"+v.quantity+" Exp Date =,"+v.expiryDate+",Packing = "+v.packing+","+v.unitName))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

//fetch product Against Default Godown for Seed
function fetchSeedProductAgainstDefaultGodown(){


	var godownName="Default";
	$("#fert_pro_drop").empty();
	$("#proName").append($("<option></option>").attr("value","").text("Select product"));
	var params= {};

	params["godownName"]= godownName;
	params["methodName"] = "getSeedAllProductAgainstGodown";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#fert_pro_drop").append($("<option></option>").attr("value",(v.product_name + ","+v.batchno+",Qty=,"+v.quantity+" Exp Date =,"+v.expiryDate+",Packing = "+v.packing+","+v.unitName))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}