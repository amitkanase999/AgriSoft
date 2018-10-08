
function getPOdetails()
{
	this.getPurchaseOrderdetails = getPurchaseOrderdetails;

	function getPurchaseOrderdetails()
	{
		var params= {};
		itemparams={};
		poNumber = $('#poNum').val();
		itemparams["poNumber"]= poNumber;
		itemparams["methodName"] = "getPurchaseOrderDetails";
		$.post('/AgriSoft/jsp/utility/controller.jsp',itemparams,
				function(data)
				{ 
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{

				var pkPOId = v.pkPOId;
				var dcNum = v.dcNum;
				var productName =  v.productName;
				var  buyPrice =  v.buyPrice;  
				var salePrice =v.salePrice;
				var quantity = v.quantity;
				var weight = v.weight ;
				var totalAmount = v.totalAmount;

				$("#jqGrid").jqGrid({
					datatype:"local",

					colNames: ["Product ID","DC Number","Product Name","Buy Price","Sale Price","Weight Per Bag","Quantity","Total Amount" ],

					colModel: [
					           { 	
					        	   name: "pkPOId",
					        	   hidden:true

					           },
					           { 	
					        	   name: "dcNum",
					        	   width:100,

					           },
					           { 	
					        	   name: "productName",
					        	   width:100,

					           },
					           {
					        	   name: "buyPrice",
					        	   width: 80,
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
					        	   name: "quantity",
					        	   width: 140,
					        	   editable: true
					           },

					           {
					        	   name: "totalAmount",
					        	   width: 80,

					           }
					           ],

					           sortorder : 'desc',
					           loadonce: true,
					           viewrecords: true,
					           width: 1000,
					           height: 250,
					           rowNum: 10,
					           pager: "#jqGridPager",
					           'cellEdit':true
				});

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

				$("#jqGrid").addRowData(i,jsonData[i]);
				var grid = $("#jqGrid"),
				intervalId = setInterval(
						function() {
							grid.trigger("reloadGrid",[{current:true}]);
						},
						100); // 300 sec === 5 min
					});

				}); 
	}
}

var podetails = new getPOdetails();

function updateDcNumber(){

	document.dc.btn.disabled = true;
	var params = {};
	var poNum = $('#poNum').val();
	var dcNum = $('#dcNum').val();
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');//to get all rows of grid
	var AllRows=JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {
		var pkPOId = allRowsInGrid[i].pkPOId;
		params["pkPOId"+i] = pkPOId;
	}

	params["methodName"] = "updateDCNumber";
	params["poNum"] = poNum;
	params["dcNum"] = dcNum;
	params["count"] = count;
	params["pkPOId"] = pkPOId;
	params["methodName"] = "updateDCNumber";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		if(document.dc)
		{
			document.dc.reset();
		}	
		document.dc.btn.disabled =false;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
