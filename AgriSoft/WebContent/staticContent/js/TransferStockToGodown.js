function hideProgressbar()
{
	 $("#stockprogress").hide();
}
function showDiv() {
   document.getElementById('stockprogress').style.display = "block";
}

function checktogodown()
{
	var fromgodown=$('#godown').val();
	var togodown=$('#togodown').val();
	
	if(fromgodown==togodown)
	{
		alert("you cant Transfer Stock to same Godown Please select another godown");
		document.getElementById("togodown").value="";
	}
}

function checkfromgodown()
{
	var fromgodown=$('#godown').val();
	var togodown=$('#togodown').val();
	
	if(fromgodown==togodown)
	{
		alert("you cant Transfer Stock to same Godown Please select another godown");
		document.getElementById("togodown").value="";
	}
}

/*function categoryDisable() {
    
        $('#cat :input').attr('disabled', true);
   
       // $('#elementsToOperateOn :input').removeAttr('disabled');
     
}*/

function fetchProductName(){

	var godown=$('#godown').val();
	var category=$('#category').val();
	
	if(category=="fertilizer")
	{
		$("#proName").empty();
		$("#Product_list_drop").append($("<option></option>").attr("value","").text("Select Category"));
		var params= {};

		params["godown"]= godown;
		params["category"]= category;
		params["methodName"] = "getAllProductName";
		
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{ var count = 1;

				var jsonData = $.parseJSON(data);
				$.each(jsonData,function(i,v)
						{
					$("#Product_list_drop").append($("<option></option>").attr("value",(v.companyname+","+v.product_name+","+v.packing+","+v.unitName+"  Stock=,"+v.quantity))); 
					count++;
						});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}
	else
	{
		$("#Product_list_drop").empty();
		$("#Product_list_drop").append($("<option></option>").attr("value","").text("Select Category"));
		var params= {};

		params["godown"]= godown;
		params["category"]= category;
		params["methodName"] = "getAllProductNameForSeedPesticide";
		
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{ var count = 1;

				var jsonData = $.parseJSON(data);
				$.each(jsonData,function(i,v)
						{
					$("#Product_list_drop").append($("<option></option>").attr("value",(v.companyname+" ,"+v.product_name+" ,Batch No =,"+v.batchno+" ,Exp Date= ,"+v.expiryDate+" ,Packing= ,"+v.packing+","+v.unitName+",Stock=,"+v.quantity))); 
					count++;
						});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});
	}

	
}

//fetch fertilizer product in Grid
function getAllFertilizerProductForGrid(){

	var params= {};
	
	var godown= $('#godown').val();
	var category= $('#category').val();
	productId = $('#proName').val();


	if(category =="fertilizer")
	{
	var company = productId.split(",")[0];
	var proName = productId.split(",")[1];
	var weight = productId.split(",")[2];
	var availableQty = productId.split(",")[4];

	
	params["godown"]= godown;
	params["category"]= category;
	
	params["company"]= company;
	params["proName"]= proName;
	params["weight"]= weight;
	params["availableQty"]= availableQty;

	var count=0;
	var newrow;
	var rowId;

	params["methodName"] = "getFertilizerProductDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		
		 
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{

			var product_name =  v.product_name;
			//var  buyPrice =  v.buyPrice;  
			//var salePrice =v.salePrice;
			var quantity = v.quantity;
			var packing = v.packing ;
			//var productID = v.productID ;
			var companyname = v.companyname ;
			//var lastsel = productID;
			
			

			$("#jqGrid").jqGrid({

				datatype:"local",

				colNames: [	"supplier Name","company name","product category","product name","packing","unit","quantity","TRF Qty","Leftover","batchno","Exp date" ],

				colModel: [
				           { 	
				        	   name: "supplier_name",
				        	   hidden:true


				           },
				           { 	
				        	   name: "companyname",
				        	  // hidden:true


				           },
				           { 	
				        	   name: "product_category",
				        	   width:120,

				           },
				           { 	
				        	   name: "product_name",
				        	   width:120

				           },
				           { 	
				        	   name: "packing",
				        	   width:120

				           },
				           {
				        	   name: "unitName",
				        	   width: 120,
				        	  // editable: true
				           },	

				           {
				        	   name: "quantity",
				        	   width: 140,
				        	  // editable: true
				           },
				           {
				        	   name: "TRF_Qty",
				        	   width: 140,
				        	   editable: true
				           },
				           {
				        	   name: "leftover",
				        	   width: 140,
				        	 //  editable: true
				           },
				           
				           {
				        	   name:  "batchno",
				        	   width: 140,
				        	  // editable: true
				           },

				           {
				        	   name: "expDate",
				        	   width: 100,
				        	  // editable: true

				           },
				          
				
				      
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
				        	   var TRF_Qty = rowData['TRF_Qty'];
				        	   
				        	
				        	   if(Number(TRF_Qty)<=Number(quantity))
				        	   {
				        		   var lefrtover=quantity-TRF_Qty;
					        	   $("#jqGrid").jqGrid("setCell", rowId, "leftover", lefrtover);
					        	  
				        	   }
				        	   else
				        	   {
				        		   $.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					      					{
					      		
					      				var msg="TRF Qauntity Should be less than Equal to Stock"+quantity;
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
				        		   $("#jqGrid").jqGrid("setCell", rowId, "leftover", quantity);
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
				prodName = rowdata[j].product_name;
				com = rowdata[j].companyname;
				packing = rowdata[j].packing;

				var rowId = ids[j];
				var rowData = jQuery('#jqGrid').jqGrid ('getRowData', rowId);

				if (prodName == jsonData[i].product_name && com == jsonData[i].companyname && packing == jsonData[i].packing) {

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
			
		
			})
			
	}
	else
	{
		
		
		var company = productId.split(",")[0];
		var proName = productId.split(",")[1];
		var batchno = productId.split(",")[3];
		var weight = productId.split(",")[8];
		var unit = productId.split(",")[9];
		var availableQty = productId.split(",")[11];

		
		params["godown"]= godown;
		params["category"]= category;
		
		params["company"]= company;
		params["proName"]= proName;
		params["batchno"]= batchno;
		params["weight"]= weight;
		params["unit"]= unit;
		params["availableQty"]= availableQty;

		var count=0;
		var newrow;
		var rowId;

		params["methodName"] = "getSeedPesticideProductDetails";
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			 
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{

				var product_name =  v.product_name;
				//var  buyPrice =  v.buyPrice;  
				//var salePrice =v.salePrice;
				var quantity = v.quantity;
				var packing = v.packing ;
				//var productID = v.productID ;
				var companyname = v.companyname ;
				//var lastsel = productID;
				
				

				$("#jqGrid").jqGrid({

					datatype:"local",

					colNames: [	"supplier Name","company name","product category","product name","packing","unit","quantity","TRF Qty","Leftover","batchno","Exp date" ],

					colModel: [
					           { 	
					        	   name: "supplier_name",
					        	   hidden:true


					           },
					           { 	
					        	   name: "companyname",
					        	  // hidden:true


					           },
					           { 	
					        	   name: "product_category",
					        	   width:120,

					           },
					           { 	
					        	   name: "product_name",
					        	   width:120

					           },
					           { 	
					        	   name: "packing",
					        	   width:120

					           },
					           {
					        	   name: "unitName",		
					        	   width: 120,
					        	  // editable: true
					           },	

					           {
					        	   name: "quantity",
					        	   width: 140,
					        	  // editable: true
					           },
					           {
					        	   name: "TRF_Qty",
					        	   width: 140,
					        	   editable: true
					           },
					           {
					        	   name: "leftover",
					        	   width: 140,
					        	 //  editable: true
					           },
					           
					           {
					        	   name:  "batchno",
					        	   width: 140,
					        	  // editable: true
					           },

					           {
					        	   name: "expDate",
					        	   width: 100,
					        	  // editable: true

					           },
					          
					
					      
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
					        	   var TRF_Qty = rowData['TRF_Qty'];
					        	   
					        	
					        	   if(Number(TRF_Qty)<=Number(quantity))
					        	   {
					        		   var lefrtover=quantity-TRF_Qty;
						        	   $("#jqGrid").jqGrid("setCell", rowId, "leftover", lefrtover);
						        	  
					        	   }
					        	   else
					        	   {
					        		   $.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						      					{
						      		
						      				var msg="TRF Qauntity Should be less than Equal to Stock"+quantity;
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
					        		   $("#jqGrid").jqGrid("setCell", rowId, "leftover", quantity);
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
					prodName = rowdata[j].product_name;
					com = rowdata[j].companyname;
					packing = rowdata[j].packing;

					var rowId = ids[j];
					var rowData = jQuery('#jqGrid').jqGrid ('getRowData', rowId);

					if (prodName == jsonData[i].product_name && com == jsonData[i].companyname && packing == jsonData[i].packing) {

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
				
			
				})
		
	}
}

function validationStockTransfer()
{
	if(document.trfs.godown.value != "")
	  {
		if(document.trfs.togodown.value != "")
		  {
			if(document.trfs.category.value != "")
			  {
				if(document.trfs.proName.value != "")
				  {
					TransferStock();
				  }
				else
				{
					
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
					
							var msg="Please Select Product Name...";
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
				
						var msg="Please Select category...";
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
			
					var msg="Please Select To Godown...";
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
		
				var msg="select Tax Type";
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

//Update and Transfer Stock
function TransferStock(){

	document.getElementById("transfer").disabled = true;
	//document.fertiBill.btn.disabled = true;
	var params = {};

	var fromgodown = $('#godown').val();
	var togodown = $('#togodown').val();
	var category = $('#category').val();
	

	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var supplier_name = allRowsInGrid[i].supplier_name;
		params["supplier_name"+i] = supplier_name;

		var companyname = allRowsInGrid[i].companyname;
		params["companyname"+i] = companyname;

		var product_category = allRowsInGrid[i].product_category;
		params["product_category"+i] = product_category;

		var product_name = allRowsInGrid[i].product_name;
		params["product_name"+i] = product_name;

		var packing = allRowsInGrid[i].packing;			
		params["packing"+i] = packing;

		var unitName = allRowsInGrid[i].unitName;
		params["unitName"+i] = unitName;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity"+i] = quantity;

		var TRF_Qty = allRowsInGrid[i].TRF_Qty;
		params["TRF_Qty"+i] = TRF_Qty;
		
		var leftover = allRowsInGrid[i].leftover;
		params["leftover"+i] = leftover;
		
		var batchno = allRowsInGrid[i].batchno;
		params["batchno"+i] = batchno;
		
		var expDate = allRowsInGrid[i].expDate;
		params["expDate"+i] = expDate;
		
		
	}

	params["fromgodown"] = fromgodown;
	params["togodown"] = togodown;
	params["category"] = category;
	params["count"] = count;
	
	
	params["methodName"] = "transferStockToGodown";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
			showDiv();
		//alert(data);
		window.open("FertilizerBillPDF.jsp");
		location.reload();
		document.getElementById("transfer").disabled = false;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
