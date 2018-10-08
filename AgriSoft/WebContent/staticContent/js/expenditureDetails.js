//Adding expense detail
function addExpenseDetails(){
	if(document.expenseDetails.expenseName.value != "")
	{
		var letterNumber = /^[a-zA-Z0-9, ]+$/;
		if(document.expenseDetails.expenseName.value.match(letterNumber))
		{
			addExpense();
		}
		else
		{
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Enter Alphabates And Numbers Only in Expense name field..!!";
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
	
			var msg="Please Enter Expense Name";
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

function addExpense() {

	document.expenseDetails.btn.disabled = true;
	var expenseName = $('#expenseName').val();

	var params = {};

	params["expenseName"] = expenseName;
	params["methodName"] = "addExpenseDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp', params, function(data) 
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
				document.expenseDetails.btn.disabled = false;
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

function reset() {
	document.expenseDetails.reset();
}

function addExpenseForBillingAndGoodsReceive() {

	document.expenseDetails1.btn1.disabled = true;
	var expenseName = $('#expenseNameForBilling').val();

	var params = {};

	params["expenseName"] = expenseName;
	params["methodName"] = "addExpenseDetailsForBilling";
	$.post('/AgriSoft/jsp/utility/controller.jsp', params, function(data) {
		alert(data);
		if (document.expenseDetails1) {
			document.expenseDetails1.reset();
		}
		document.expenseDetails1.btn1.disabled = false;
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}