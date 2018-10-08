
function back()
{
	window.open("tax.jsp","_self");
}

function addTax(){
	if ( document.txc.taxType.value == "" )
	{
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg="Please Enter Tax Type";
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
	var letterNumber = /^[a-zA-Z0-9, ]+$/;
	if(document.txc.taxType.value.match(letterNumber) )
	{
		if ( document.txc.taxPercentage.value == "" )
		{
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Please Enter Tax Percentage";
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
		var letterNumber = /^\s*-?[0-9]\d*(\.\d{0,2})?\s*$/;
		if(document.txc.taxPercentage.value.match(letterNumber) )
		{
			txCreation();
		}

		else
		{

			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Enter Only Number upto 2 decimal in Tax percentage field..!!";
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
	
			var msg="Enter Alphabates And Number Only in Tax Type field..!!";
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

function txCreation(){
	var taxType= $('#taxType').val();
	var taxPercentage = $('#taxPercentage').val();

	var params= {};

	params ["taxType"] = taxType;
	params ["taxPercentage"] = taxPercentage;
	params["methodName"] = "taxCreation";
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
				//document.creditloan.save.disabled = false;
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


function editTax(){

	if ( document.txc.taxtypename.value == "" )
	{
		alert("Please Enter Tax Type");
		return false;
	}
	if ( document.txc.taxPercentage.value == "" )
	{
		alert("Please Enter Tax Percentage");
		return false;
	}
	var letterNumber = /^\d+(\.\d+)?$/;
	if(document.txc.taxPercentage.value.match(letterNumber) )
	{
		edittxCreation();
	}
	else
	{
		alert("Enter Number Only in percentage field..!!");
		return false;
	}
}

function edittxCreation(){
	var fk_tax_id = $('#fk_tax_id').val();
	var taxtypename=$('#taxtypename').val();
	var taxPercentage = $('#taxPercentage').val();

	var params= {};

	params ["fk_tax_id"] = fk_tax_id;
	params ["taxtypename"] = taxtypename;
	params ["taxPercentage"] = taxPercentage;
	params["methodName"] = "edittaxCreation";
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

function reset()
{
	document.txc.reset();	

}

/*++++++++ for Single date SALE +++++++++*/
function taxReportForSale(){
	var params= {};
	var input1 = document.getElementById('fk_cat_id_for_sale'),
	list = document.getElementById('cat_drop_for_sale'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}

	var fk_cat_id = fk_cat_id;
	var fDate= $('#fDate').val();
	params["cat"]= fk_cat_id;
	params["fDate"]= fDate;
	params["methodName"] = "getTaxDetailsAsPerCategoryFromSale";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#saleTax').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#saleTax').DataTable( {

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


					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 8 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,
				columns: [

				          {"data": "billNo", "width": "5%"},
				          {"data": "product", "width": "5%"},
				          {"data": "manufacturer", "width": "5%"},
				          {"data": "weight" , "width": "5%"},
				          {"data": "salePrice" , "width": "5%"},
				          {"data": "mrp" , "width": "5%"},
				          {"data": "taxPercentage" , "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          {"data": "taxAmount", "width": "5%"},

				          ],

			} );
		} );

		var mydata = catmap;
		$('#saleTax').dataTable().fnAddData(mydata);
			}
	);
}

/*++++++++ Between Two date SALE +++++++++*/
function taxFromSaleBetweenTwoDate(){

	var params= {};
	var input1 = document.getElementById('fk_cat_id_for_sale_Two'),
	list = document.getElementById('cat_drop_for_sale_Two'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}

	var fk_cat_id = fk_cat_id;
	var fDate= $('#fDateTwo').val();
	var sDate= $('#sDateTwo').val();
	
	params["cat"]= fk_cat_id;
	params["fDate"]= fDate;
	params["sDate"]= sDate;
	params["methodName"] = "getTaxDetailsAsPerCategoryFromSaleBetweenTwoDate";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#saleTaxTwo').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#saleTaxTwo').DataTable( {

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
					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 8 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},
				destroy: true,
				searching: false,
				columns: [

				          {"data": "billNo", "width": "5%"},
				          {"data": "product", "width": "5%"},
				          {"data": "manufacturer", "width": "5%"},
				          {"data": "weight" , "width": "5%"},
				          {"data": "salePrice" , "width": "5%"},
				          {"data": "mrp" , "width": "5%"},
				          {"data": "taxPercentage" , "width": "5%"},
				          {"data": "quantity" , "width": "5%"},
				          {"data": "taxAmount", "width": "5%"},
				          ],

			} );
		} );
		var mydata = catmap;
		$('#saleTaxTwo').dataTable().fnAddData(mydata);

			}
	);
}

/*+++++++++++++++++  Single date ++++++++++++++++*/
function taxReportForPurchaseSingleDate(){

	var params= {};
	var input1 = document.getElementById('fk_cat_id_for_purchase'),
	list = document.getElementById('cat_drop_for_purchase'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}

	var fk_cat_id = fk_cat_id;
	var fDate= $('#fDatePurchase').val();
	var sDate= $('#sDatePurchase').val();
	params["cat"]= fk_cat_id;
	params["fDate"]= fDate;
	params["sDate"]= sDate;
	params["methodName"] = "getTaxDetailsAsPerCategoryFromPurchase";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#purchaseTaxSingle').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#purchaseTaxSingle').DataTable( {

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


					pageTotal = api
					.column( 10 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 10 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,


				columns: [
				          {"data": "supplier", "width": "5%"},
				          {"data": "tinNo", "width": "5%"},
				          {"data": "billNo", "width": "5%"},
				          {"data": "productName", "width": "5%"},
				          {"data": "companyName", "width": "5%"},
				          {"data": "weight" , "width": "5%"},
				          {"data": "buyPrice" , "width": "5%"},
				          {"data": "mrp" , "width": "5%"},
				          {"data": "taxPercentage" , "width": "5%"},
				          {"data": "quantity2" , "width": "5%"},
				          {"data": "taxAmount", "width": "5%"},
				          ],

			} );
		} );
		var mydata = catmap;
		var check=mydata.length;
		if(check !=0)
		{
			$('#purchaseTaxSingle').dataTable().fnAddData(mydata);
		}
		else
		{
			/*/AgriSoft/staticContent/js/bootbox.js*/
			/*/AgriSoft/staticContent/js/bootbox.min.js*/
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
				
			/*	bootbox.alert({
					title: "Embel Technologies Says :",
				    message: "Data Not Available...!  ",
				   // className: 'bb-alternate-modal'
				    size: 'small'
				    	
				});*/
				
				var dialog = bootbox.dialog({
					//title: "Embel Technologies Says :",
				    message: '<p class="text-center">Data Not Available....!</p>',
				    closeButton: false
				});
				
				setTimeout(function() {
					dialog.modal('hide');
				}, 1500);
				
				
				
				
					});
			
			
		}
		}
	);

}