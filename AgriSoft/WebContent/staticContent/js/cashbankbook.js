var loantotal=0;
var producttotal=0;
//Adding supplier Payment
function supplierPayment(){
	var balanceAmount = $("#balanceAmount").val();
	var supPay = $("#supPay").val();
	var paymentType = $('#paymentType').val();
	var paymentMode = $('#paymentMode').val();
	var receiptno=$('receiptno').val();

	if((Number(supPay) > Number(balanceAmount ))){
		alert("Supplier Payment Amount Should Be Less Than Balance Amount");
		return false;
	}

	if(document.spmt.supplier.value != "")
	{
		if(document.spmt.personname.value != "")
		{
			if(paymentMode != "selected" && paymentMode != null && paymentType != " " )
			{

				if(paymentType != "selected")
				{

					if(document.spmt.supPay.value != "")
					{
						var letterNumber = /^[0-9]+([.][0-9]+)?$/;
						if(document.spmt.supPay.value.match(letterNumber))
						{
							if(document.spmt.receiptno.value != "")
							{
								if(receiptno!="" || receiptno!="undefined" || receiptno!="Null")
								{
									sup();
								}
								else
								{
									receiptno=0;
								}
							}
							else
							{
								
								$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
										{
							
									var msg="Please Enter Reciept No";
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
						
								var msg="Please Enter valid amount";
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
					
							var msg="Please Enter Supplier Payment  amount";
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
				
						var msg="Please Select Payment Type";
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
			
					var msg="Please select Payment Mode";
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
		
				var msg="Please Enter Account Name";
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
	
			var msg="Please select Supplier Name";
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

function	sup(){

	document.getElementById("btn1").disabled = true;
	var totalAmount = $('#totalAmount').val();
	var balanceAmount = $('#balanceAmount').val();
	var paymentType = $('#paymentType').val();
	var paymentMode = $('#paymentMode').val();
	var chequeNum = $('#chequeNum').val();
	var nameOnCheck = $('#nameOnCheck').val();
	var bankName = $('#bankName').val();
	var cardNum = $('#cardNum').val();
	var accNum = $('#accNum').val();
	var personname = $('#personname').val();
	var supPay = $('#supPay').val();
	var receiptno=$('#receiptno').val();
	var creditnotenumber = $('#creditnotenumber').val();
	var cramount = $('#cramount').val();

	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,fkRootsupId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootsupId = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params ["supplier"] = fkRootsupId;
	params ["totalAmount"] = totalAmount;
	params ["balanceAmount"] = balanceAmount;
	params ["paymentType"] = paymentType;
	params ["paymentMode"] = paymentMode;
	params ["chequeNum"] = chequeNum;
	params ["nameOnCheck"] = nameOnCheck;
	params ["bankName"] = bankName;
	params ["cardNum"] = cardNum;
	params ["accNum"] = accNum;
	params ["creditnotenumber"] = creditnotenumber;
	params ["cramount"] = cramount;
	params ["personname"] = personname;
	params ["supPay"] = supPay;
	params["receiptno"]=receiptno;
	params["methodName"] = "regSupCashBook";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		/*alert(data);
		location.reload();
		document.getElementById("btn1").disabled = false;*/
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
				document.creditloan.btn1.disabled = false;
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

function customerPaymentValidation() {
	var creditCustomer = $("#creditCustomer").val();
	var creditCustBillNo = $("#billNo1").val();
	var creditCustAccName = $("#personname1").val();
	var creditCustPaymentMode = $("#paymentMode1").val();
	var creditCustPaymentType = $("#paymentType1").val();
	var creditCustBalanceAmt = $("#balanceAmount1").val();
	var creditCustCreditAmt = $("#custPay").val();

	if (creditCustomer != null && creditCustomer != "") {
		if (creditCustBillNo != null && creditCustBillNo != "") {// &&
			var onlyAlfabet = /^[a-zA-Z ]*$/;
			if (creditCustAccName.match(onlyAlfabet) && creditCustAccName != ""
				&& creditCustAccName != " " && creditCustAccName != null) {

				var creditAmtRegExp = /^[0-9]+([.][0-9]+)?$/;
				if (creditCustCreditAmt != null && creditCustCreditAmt != ""
					&& creditCustCreditAmt != " ") {

					if (creditCustCreditAmt.match(creditAmtRegExp)) {

						if (Number(creditCustCreditAmt) <= Number(creditCustBalanceAmt)) {

							if (Number(creditCustCreditAmt) > 0) {
								if (creditCustPaymentMode != "selected" && creditCustPaymentMode != "" && creditCustPaymentMode != " ") {
									if (creditCustPaymentType != "selected" && creditCustPaymentType != "" && creditCustPaymentType != " ") {
										customerPayment();
									} else {
										alert("Please select Payment Type");
									}
								} else {
									alert("Please select Payment Mode");
								}
							} else {
								alert("Credit Amount should be grater than 1");
							}

						} else {
							alert("Credit Amount should not grater than Balance Amount");
						}

					} else {
						alert("Please Enter valid Credit Amount");
					}

				} else {
					alert("Please Enter Credit Amount");
				}

			} else {
				alert("Enter Account Hoder Name without Any Number and Special symbols");
			}
		} else {
			alert("please Select Bill Number");
		}
	} else {
		alert("please Select Customer Name");
	}
}


//Adding Credit Customer Payment

function customerPayment(){
	
	//validation of balance amount with credit amount
	
	var balanceAmount = $("#balanceAmount1").val();
	var credit = $("#credit1").val();
	if(credit >balanceAmount ){
		alert("Credit Amount Should Be Less Than Balance Amount")
	}

	document.getElementById("btn2").disabled = true;

	var billNo = $('#billNo1').val();
	var totalAmount = $('#totalAmount1').val();
	var custPay = $('#custPay').val();
	var paymentMode = $('#paymentMode1').val();
	var chequeNum = $('#chequeNum1').val();
	var nameOnCheck = $('#nameOnCheck1').val();
	var bankName = $('#bankName1').val();
	var cardNum = $('#cardNum1').val();
	var accNum = $('#accNum1').val();
	var personname = $('#personname1').val();
	var paymentType = $('#paymentType1').val();
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,fkRootCustId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootCustId = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,cat;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			cat = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params ["customer"] = fkRootCustId;
	params ["catId"] = cat;
	params ["billNo"] = billNo;
	params ["totalAmount"] = totalAmount;
	params ["custPay"] = custPay;
	params ["paymentMode"] = paymentMode;
	params ["chequeNum"] = chequeNum;
	params ["nameOnCheck"] = nameOnCheck;
	params ["bankName"] = bankName;
	params ["cardNum"] = cardNum;
	params ["accNum"] = accNum;
	params ["personname"] = personname;
	params ["paymentType"] = paymentType;
	params["methodName"] = "regCreditCustCashBook";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		window.open("customerPaymentPDF.jsp");
		location.reload();
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function EmployeeValidation() {

	var empName = $("#employee").val();
	var empCreditAmt = $("#empPay").val();
	var empAccName = $("#personName2").val();
	var empPaymentReason = $("#reason2").val();
	var empPaymentMode = $("#paymentMode2").val();
	var paymentType2 = $("#paymentType2").val();

	if (empName != null && empName != "") {

		var creditAmtRegExp = /^[0-9]+([.][0-9]+)?$/;
		if (empCreditAmt != null && empCreditAmt != "" && empCreditAmt != " ") {
			if (empCreditAmt.match(creditAmtRegExp)) {
				if (Number(empCreditAmt) > 0) {
					var onlyAlfabet = /^[a-zA-Z ]*$/;
					if (empAccName.match(onlyAlfabet) && empAccName != "" && empAccName != " " && empAccName != null) {
						if (empPaymentReason != "" && empPaymentReason != " " && empPaymentReason != null) {
							if (empPaymentMode != "selected2" && empPaymentMode != "" && empPaymentMode != " ") {
								if (paymentType2 != "selected2" && paymentType2 != "" && paymentType2 != " " && paymentType2 != "selected") {
									emplyeePayment();
								} else {
									alert("Select Payment Type Credti or debit");
								}
							} else {
								alert("Select Payment Mode");
							}

						} else {
							alert("Enter Payment Reason.");
						}
					} else {
						alert("Enter Account Hoder Name without Any Number");
					}
				} else {
					alert("Credit Amount should be grater than 1");
				}
			} else {
				alert("Please Enter valid Credit Amount");
			}
		} else {
			alert("Please Enter Credit Amount");
		}
	} else {
		alert("Select Employee Name");
	}
}

//Adding Employee Payment
function emplyeePayment(){

	document.getElementById("btn3").disabled = true;

	var empPay= $('#empPay').val();
	var personName= $('#personName2').val();
	var reason= $('#reason2').val();
	var paymentMode = $('#paymentMode2').val();
	var chequeNum = $('#chequeNum2').val();
	var nameOnCheck = $('#nameOnCheck2').val();
	var bankName = $('#bankName2').val();
	var cardNum = $('#cardNum2').val();
	var accNum = $('#accNum2').val();
	var paymentType = $('#paymentType2').val();
	var input = document.getElementById('employee'),
	list = document.getElementById('emp_drop'),
	i,fkRootempId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootempId = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params ["employee"] = fkRootempId;
	params ["empPay"] = empPay;
	params ["personName"] = personName;
	params ["reason"] = reason;
	params ["paymentMode"] = paymentMode;
	params ["chequeNum"] = chequeNum;
	params ["nameOnCheck"] = nameOnCheck;
	params ["bankName"] = bankName;
	params ["cardNum"] = cardNum;
	params ["accNum"] = accNum;
	params ["paymentType"] = paymentType;
	params["methodName"] = "regEmpCashBook";
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

function expensePaymentValidation() {

	
	var expenseName = $("#expenseName").val();
	var serviceProvider = $("#serviceProvider").val();
	var contactNumber = $("#contactNumber").val();
	var expCredit = $("#expCredit").val();
	var expDebit = $("#expDebit").val();
	var accountantName = $("#accountantName").val();

	if (expenseName != null && expenseName != "" && expenseName != " ") {
		if (serviceProvider != null && serviceProvider != "" && serviceProvider != " ") 
		{
			if (contactNumber != null && contactNumber != ""
				&& contactNumber != " ") {
				var phoneno = /^\d{10}$/;
				if (contactNumber.match(phoneno)) {
					if (accountantName != null && accountantName != ""
						&& accountantName != " ") {
						var onlyAlfabet = /^[a-zA-Z ]*$/;
						if (accountantName.match(onlyAlfabet)) {
							if (accountantName.match(onlyAlfabet)) 
							{
								if (expCredit != null &&  expCredit != "" && expCredit != "") 
								{

									if (expDebit != null && expDebit != "" && expDebit != "") 
									{
										addExpense();
									}
									else
									{
										
										$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
												{
									
											var msg="Enter Debit Amount";
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
								
										var msg="Enter Creadit Amount";
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
							} else {
							
								$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
										{
							
									var msg="Enter Accountant Name without Any Number and Special symbols";
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
						} else {
							
							$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
									{
						
								var msg="Enter Accountant Name without Any Number and Special symbols";
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
					} else {
						
						$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
								{
					
							var msg="Enter Accountant Name.";
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

				} else {
			
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
				
						var msg="Contact No Must be 10 digit.";
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
			} else {
				
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Enter Contact Number";
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
		} else {
		
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Enter Service Provider";
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
	} else {
		
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg="please Select Expenditure Name";
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

/********************Adding Expenditure Payment****************/

function addExpense(){

	//document.exp.btn4.disabled = true;
	document.getElementById("save").disabled = true;

	var serviceProvider = $('#serviceProvider').val();
	var expCredit = $('#expCredit').val();
	var expDebit = $('#expDebit').val();
	var contactNumber = $('#contactNumber').val();
	var accountantName = $('#accountantName').val();
	var input = document.getElementById('expenseName'),
	list = document.getElementById('exp_drop'),
	i,fkRootexpId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootexpId = list.options[i].getAttribute('data-value');
		}
	}

	var params= {};

	params ["expenseName"] = fkRootexpId;
	params ["serviceProvider"] = serviceProvider;
	params ["expCredit"] = expCredit;
	params ["expDebit"] = expDebit;
	params ["contactNumber"] = contactNumber;
	params ["accountantName"] = accountantName;
	params["methodName"] = "regExpenseCashBook";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		/*alert(data);
		if(document.exp)
		{
			document.exp.reset();
		}	
		//document.exp.btn4.disabled =false;
		document.getElementById("save").disabled = false;*/
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
				document.exp.save.disabled = false;
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


/*Following BillHelper() function is to get Bill no , total amount and balamce amount after selecting supplier in Supplier Payment*/
var bill = new BillHelper();
function BillHelper()
{
	this.getAllBills =getAllBills;
	this.getTotalAmtByBills=getTotalAmtByBills;
	this.getRemainingBalaneByBills=getRemainingBalaneByBills;

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
		$("#billNo").empty();
		$("#billNo").append($("<option></option>").attr("value","").text("Select bill"));
		var params= {};

		params["methodName"] = "getAllBillBySuppliers";
		params["supplier"]= supplier;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#billNo").append($("<option></option>").attr("value",i).text(v.billNo)); 

					});
				})
	}

	function getTotalAmtByBills()
	{
		/*var input = document.getElementById('supplier'),
		list = document.getElementById('sup_drop'),
		i,supplier;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				supplier = list.options[i].getAttribute('data-value');
			}
		}*/
		
		supplierId = $('#supplier').val();
		var supplier = supplierId.split(",")[0];

		$("#totalAmount").empty();
		$("#totalAmount").append($("<input/>").attr("value","").text());
		var params= {};
		params["methodName"] = "getTotalAmtByBillNo";
		params["supplier"]= supplier;
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				document.getElementById("totalAmount").value = v.totalAmt;

					});
				})
				getRemainingBalaneByBills()
	}

	function getRemainingBalaneByBills()
	{
		var input = document.getElementById('supplier'),
		list = document.getElementById('sup_drop'),
		i,supplier;

		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				supplier = list.options[i].getAttribute('data-value');
			}
		}

		var billNo = $("#billNo").val();
		var totalAmount = $("totalAmount").val();

		$("#balanceAmount").empty();
		$("#balanceAmount").append($("<input/>").attr("value","").text());

		var params= {};

		params["methodName"] = "getBalanceAmtByBillNo";
		params["billNo"]= billNo;
		params["supplier"]= supplier;
		params["totalAmount"]= totalAmount;

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
		
			$.each(jsonData,function(i,v)
					{

				document.getElementById("balanceAmount").value = v.balance;
					});
				})
	}
}

function getBillByCustomer(){
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,cat;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			cat = list.options[i].getAttribute('data-value');
		}
	}

	var creditCustomer = creditCustomer;
	$("#billNo1").empty();
	$("#billNo1").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};

	params["methodName"] = "getAllBillByCustomer";
	params["creditCustomer"]= creditCustomer;
	params["cat"]= cat;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#billNo1").append($("<option></option>").attr("value",i).text(v.billNo)); 

				});
			})
}

function getBillByCustomerForReport(){
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,cat;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			cat = list.options[i].getAttribute('data-value');
		}
	}

	var creditCustomer = creditCustomer;
	$("#billNo1").empty();
	$("#billNo1").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};

	params["methodName"] = "getAllBillByCustomer";
	params["creditCustomer"]= creditCustomer;
	params["cat"]= cat;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			$("#billNo1").append($("<option></option>").attr("value",i).text(v.billNo)); 

				});
			})
}

function getCreditCustomerBillDetails(){
	this.getBillByCustomer = getBillByCustomer;
	this.getTotalAmountByBill = getTotalAmountByBill;
}

function getTotalAmountByBill(){
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,cat;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			cat = list.options[i].getAttribute('data-value');
		}
	}

	var billNo1 = $("#billNo1").val();
	$("#totalAmount1").empty();
	$("#totalAmount1").append($("<input/>").attr("value","").text());
	var params= {};

	params["methodName"] = "getTotalAmtByBillNoForCreditCustomer";
	params["billNo1"]= billNo1;
	params["cat"]= cat;
	params["creditCustomer"]= creditCustomer;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("totalAmount1").value = v.totalAmount;

				});
			})

			balaceAmountbyBillNoForCreditPayment();
}

function balaceAmountbyBillNoForCreditPayment(){
	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,catId;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			catId = list.options[i].getAttribute('data-value');
		}
	}

	var cat = $("#fk_cat_id0").val();


	var billNo1 = $("#billNo1").val();
	$("#balanceAmount1").empty();
	$("#balanceAmount1").append($("<input/>").attr("value","").text());

	var params= {};

	params["methodName"] = "getBalanceAmtByBillNoForCreditCustomer";
	params["billNo1"]= billNo1;
	params["cat"]= cat;
	params["catId"]= catId;
	params["creditCustomer"]= creditCustomer;
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{

			document.getElementById("balanceAmount1").value = v.balance;
				});
			})
}

var custBill = new getCreditCustomerBillDetails();

/*************** For Reports *********************/

/**************** Supplier Payment *************/
/************ for single date **************/
function supplierReportForSingleDate(){


	var params= {};
	var fDate = $("#fDate11").val();


	params["fDate"]= fDate;

	params["methodName"] = "getSupplierPaymentDetailsForSingleDate";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#supplierSingleDatetable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#supplierSingleDatetable').DataTable( {

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
					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
				          {"data": "supplierName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo", "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Date Wise Supplier Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#supplierSingleDatetable').dataTable().fnAddData(mydata);

			}
	);
}

/********* Between two dates for supplier ***************/
function getSupplierDetailsBetweenTwoDates(){

	var params= {};
	var startDate = $("#fisDate1").val();
	var endDate = $("#endDate1").val();


	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getSupplierPaymentReportBetweenTwoDates";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#supplierBetweenTwoDatestable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#supplierBetweenTwoDatestable').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "supplierName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo", "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Range Wise Supplier Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#supplierBetweenTwoDatestable').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				}
			});
}

/*Bill number Wise supplier payment report*/
function getBillWiseReport(){

	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,supplier;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			supplier = list.options[i].getAttribute('data-value');
		}
	}
	var fkSupplierId = supplier;
	var params= {};
	var billNo = $("#billNo").val();

	params["fkSupplierId"]= fkSupplierId;
	params["billNo"]= billNo;
	params["methodName"] = "getSupplierPaymentDetailsAsPerBillNumber";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		//To clear table
		$('#supplierBillWiseData').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#supplierBillWiseData').DataTable( {
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

					// Total over all pages
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       
					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
				          {"data": "supplierName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo", "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],
				          dom: 'Bfrtip',
				          buttons: [
				                    'copy', 'csv', 'excel', 'pdf', 'print'
				                    ]

			} );
		} );

		var mydata = catmap;
		$('#supplierBillWiseData').dataTable().fnAddData(mydata);
			}
	);
}

/*Supplier Name wise*/
function getSupNameWiseReport(){

	var input = document.getElementById('supplier7'),
	list = document.getElementById('sup_drop7'),
	i,supplier;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			supplier = list.options[i].getAttribute('data-value');
		}
	}
	var fkSupplierId = supplier;

	var params= {};

	params["fkSupplierId"]= fkSupplierId;
	params["methodName"] = "getSupplierPaymentDetailsAsPerName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		//To clear table
		$('#supplierNameWiseTable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#supplierNameWiseTable').DataTable( {

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
					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );
					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );
					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},
				destroy: true,
				searching: false,

				columns: [
				          {"data": "supplierName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo", "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Supplier Name Wise Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#supplierNameWiseTable').dataTable().fnAddData(mydata);

			}
	);
}

/************ loan payment reports**********************/
/*Supplier Name wise*/
function getCustNameWiseReport(){

	var custname = $("#custname").val();
	
	var params= {};

	params["custname"]= custname;
	params["methodName"] = "getCustLoanPaymentDetailsAsPerName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{

		//To clear table
		$('#custNameWiseTable1').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#custNameWiseTable1').DataTable( {

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
					// Total over this page
					/*pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal)*/;

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );
					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );
					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},
				destroy: true,
				searching: false,

				columns: [
				          {"data": "Loanno", "width": "5%", "defaultContent": ""},
				          {"data": "cust_name", "width": "5%", "defaultContent": ""},
				          {"data": "type" , "width": "5%", "defaultContent": ""},
				          {"data": "payment_mode" , "width": "5%", "defaultContent": ""},
				          {"data": "loanDate" , "width": "5%", "defaultContent": ""},
				          {"data": "interest_rate" , "width": "5%", "defaultContent": ""},
				          {"data": "loan_amt" , "width": "5%", "defaultContent": ""},
				          {"data": "balance_status" , "width": "5%", "defaultContent": ""},
				          {"data": "paid_amt" , "width": "5%", "defaultContent": ""},
				          {"data": "paidDate" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Customer Name Wise Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#custNameWiseTable1').dataTable().fnAddData(mydata);

			}
	);
}


/********** Credit Customer Reports *********************/

/*++++++ Customer Name wise +++++*/
function getCreditCustomerReportNameWise(){

	var input = document.getElementById('creditCustomer5'),
	list = document.getElementById('cust_drop5'),
	i,customer;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			customer = list.options[i].getAttribute('data-value');
		}
	}
	var fkCustomerId = customer;

	var params= {};

	params["fkCustomerId"]= fkCustomerId;
	params["methodName"] = "getCustomerReportByName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#customerNameWiseData2').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#customerNameWiseData2').DataTable( {

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

					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},
				destroy: true,
				searching: false,

				columns: [
				          {"data": "creditCustomerFirstName", "width": "5%", "defaultContent": ""},
				          {"data": "creditCustomerLastName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Customer Name Wise Credit Customer Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#customerNameWiseData2').dataTable().fnAddData(mydata);

			}
	);
}


/* Bill Wise */
function getBillWiseCreditReport(){

	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,creditCustomer;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			creditCustomer = list.options[i].getAttribute('data-value');
		}
	}

	var input = document.getElementById('fk_cat_id0'),
	list = document.getElementById('cat_drop0'),
	i,cat;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			cat = list.options[i].getAttribute('data-value');
		}
	}

	var fkCustomerId = creditCustomer;
	var params= {};
	var billNo = $("#billNo1").val();


	params["billNo"]= billNo;
	params["fkCustomerId"]= fkCustomerId;
	params["cat"]= cat;
	params["methodName"] = "getCreditCustPaymentDetailsForBillNo";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#customerNameWiseData').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;



		$(document).ready(function() {
			var total =   $('#customerNameWiseData').DataTable( {

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

					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
				          {"data": "creditCustomerFirstName", "width": "5%", "defaultContent": ""},
				          {"data": "creditCustomerLastName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],


				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Bill Number Wise Credit Customer Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#customerNameWiseData').dataTable().fnAddData(mydata);

			}
	);
}


/********** for single date *********/
function creditCustReportForSingleDate(){

	var params= {};
	var fDate = $("#fDate1").val();


	params["fDate"]= fDate;

	params["methodName"] = "getCreditCustPaymentDetailsForSingleDate";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#customerSingleDatetable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;



		$(document).ready(function() {
			var total =   $('#customerSingleDatetable').DataTable( {

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

					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},


				destroy: true,
				searching: false,


				columns: [
				          {"data": "creditCustomerFirstName", "width": "5%", "defaultContent": ""},
				          {"data": "creditCustomerLastName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Date Wise Credit Customer Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#customerSingleDatetable').dataTable().fnAddData(mydata);

			}
	);

}

/***************** Between two dates *************/

function getCreditCustomerDetailsBetweenTwoDates()
{
	var params= {};
	var startDate = $("#fisDate").val();
	var endDate = $("#endDate").val();


	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getCustomerReportBetweenTwoDates";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#customerBetweenTwoDates').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#customerBetweenTwoDates').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 5 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 7 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       

					pageTotal = api
					.column( 8 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 8 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);
				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "creditCustomerFirstName", "width": "5%", "defaultContent": ""},
				          {"data": "creditCustomerLastName", "width": "5%", "defaultContent": ""},
				          {"data": "billNo" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentDate" , "width": "5%", "defaultContent": ""},
				          {"data": "creditAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "debitAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          {"data": "balanceAmount" , "width": "5%", "defaultContent": ""},

				          ],


				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Range Wise Credit Customer Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#customerBetweenTwoDates').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});
}

/************ Employee Payment Report **************/
/*********** For single Date *********/

function employeePaymentReportForSingleDate(){


	var params= {};
	var fDate = $("#fDate2").val();


	params["fDate"]= fDate;

	params["methodName"] = "getEmpPaymentDetailsForSingleDate";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#employeeSingleDatetable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;



		$(document).ready(function() {
			var total =   $('#employeeSingleDatetable').DataTable( {

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

					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );


					// Update footer
					$( api.column( 6 ).footer() ).html(
							//  'Rs'+' '+pageTotal.toFixed(2)
							str = pageTotal.toFixed(0)
					);
					console.log( pageTotal);

					// Total over this page       
				},

				destroy: true,
				searching: false,

				columns: [
				          {"data": "firstName", "width": "5%", "defaultContent": ""},
				          {"data": "lastName", "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%", "defaultContent": ""},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "reason" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          ],


				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Date Wise Employee Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		var check=mydata.length;
		if(check != 0)
		{
			$('#employeeSingleDatetable').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Data Not Available... !");
		}
			}
	);

}

/****** Between Two dates as per name**********/
function empNameWiseBetweenTwoDate(){

	var params= {};
	var input = document.getElementById('employeeId'),
	list = document.getElementById('emp_drop'),
	i,fkRootEmpId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootEmpId = list.options[i].getAttribute('data-value');
		}
	}
	var startDate = $("#firstDateForEmpName").val();
	var endDate = $("#endDateForEmpName").val();

	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["fkEmpId"]= fkRootEmpId;
	params["methodName"] = "getEmployeeReportBetweenTwoDatesAsPerName";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#empBetweenTwoDatesPerName').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			$('#empBetweenTwoDatesPerName').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "firstName", "width": "5%", "defaultContent": ""},
				          {"data": "lastName", "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%"},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "reason" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Empoyee Name Wise Empoyee Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#empBetweenTwoDatesPerName').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});
}

/****** Between Two dates **********/

function getEmpPaymentDetailsBetTwoDays(){


	var params= {};
	var startDate = $("#fisDate2").val();
	var endDate = $("#endDate2").val();

	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getEmployeeReportBetweenTwoDates";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#empBetweenTwoDates').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#empBetweenTwoDates').DataTable( {
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
					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "firstName", "width": "5%", "defaultContent": ""},
				          {"data": "lastName", "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%"},
				          {"data": "paymentMode" , "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "reason" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Range Wise Employee Payment Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#empBetweenTwoDates').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});
}

/*********** Expense Payment Reports *************/
/******** For single date *********/

function expensePaymentReportForSingleDate(){

	var params= {};
	var fDate = $("#fDate4").val();


	params["fDate"]= fDate;

	params["methodName"] = "getExpensePaymentDetailsForSingleDate";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#expenseSingleDatetable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			var total =   $('#expenseSingleDatetable').DataTable( {

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

					// Total over this page
					pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},


				destroy: true,
				searching: false,
				columns: [
				          {"data": "expenseName", "width": "5%", "defaultContent": ""},
				          {"data": "serviceProviderName", "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          {"data": "debit" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},

				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "DateWise Expenditure Payment Reports";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#expenseSingleDatetable').dataTable().fnAddData(mydata);

			}
	);
}


/************ Between Two Dates *************/
function getExpensePaymentDetailsBetTwoDays(){
	var params= {};
	var startDate = $("#fisDate4").val();
	var endDate = $("#endDate4").val();


	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getExpenditurePaymentReportBetweenTwoDates";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#expenseBetweenTwoDates').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			$('#expenseBetweenTwoDates').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "expenseName", "width": "5%", "defaultContent": ""},
				          {"data": "serviceProviderName", "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          {"data": "debit" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          ],


				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Range Wise Expenditure Payment Reports";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#expenseBetweenTwoDates').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});
}

function expenseReportAsPerExpenditureName(){

	var params= {};
	var startDate = $("#firstDateForExpenseName").val();
	var endDate = $("#endDateForExpenseName").val();

	var input = document.getElementById('expenseName'),
	list = document.getElementById('exp_drop'),
	i,expenseName;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			expenseName = list.options[i].getAttribute('data-value');
		}
	}

	params["expenseName"]= expenseName;
	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getExpenditurePaymentReportBetweenTwoDatesAsPerExpName";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#expenseBetweenTwoDatesAsPerSelectedName').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#expenseBetweenTwoDatesAsPerSelectedName').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 4 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 4 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					// Total over this page
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,
				columns: [

				          {"data": "expenseName", "width": "5%", "defaultContent": ""},
				          {"data": "serviceProviderName", "width": "5%", "defaultContent": ""},
				          {"data": "accountantName" , "width": "5%", "defaultContent": ""},
				          {"data": "insertDate" , "width": "5%", "defaultContent": ""},
				          {"data": "credit" , "width": "5%", "defaultContent": ""},
				          {"data": "debit" , "width": "5%", "defaultContent": ""},
				          {"data": "totalAmount" , "width": "5%", "defaultContent": ""},
				          ],

				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Expenditure Name Wise Expenditure Payment Reports";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		$('#expenseBetweenTwoDatesAsPerSelectedName').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});


}
/*get taday credit and debit report*/
function getTodayCreditDebitReport(){

	var params= {};

	params["methodName"] = "getTodayCreditDebitReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example1').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;

		$(document).ready(function() {
			$('#example1').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					var abc = pageTotal;

					document.getElementById('creAmt').value = abc;
				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "name", "width": "5%", "defaultContent": ""},
				          {"data": "credit", "width": "5%", "defaultContent": ""},


				          ]
			} );
		} );

		var mydata = catmap;
		$('#example1').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});
}

/*get taday credit and debit report*/
function getTodayCreditDebitReport1(){

	var params= {};

	params["methodName"] = "getTodayCreditDebitReport1";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example2').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#example2').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					var xyz = pageTotal;

					document.getElementById('debAmt').value = xyz;
				},

				destroy: true,
				searching: false,

				columns: [
				          {"data": "name", "width": "5%", "defaultContent": ""}, 
				          {"data": "debit", "width": "5%", "defaultContent": ""},

				          ]
			} );
		} );

		var mydata = catmap;
		$('#example2').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});

}

/*get taday credit and debit report*/
function creditdebitForsingleDate(){

	var params= {};
	var fDate = $("#fDate").val();

	params["fDate"]= fDate;
	params["methodName"] = "creditdebitForsingleDate";


	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example3').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#example3').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "name", "width": "5%"},
				          {"data": "credit", "width": "5%"},
				          ]
			} );
		} );

		var mydata = catmap;
		$('#example3').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});

}


/*get taday credit and debit report*/
function creditdebitForsingleDate1(){

	var params= {};
	var fDate = $("#fDate").val();

	params["fDate"]= fDate;
	params["methodName"] = "creditdebitForsingleDate1";


	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example4').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#example4').DataTable( {
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

					
					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "name", "width": "5%"},
				          {"data": "debit", "width": "5%"},


				          ]
			} );
		} );

		var mydata = catmap;
		$('#example4').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});

}



function creditdebitForBetTowDate(){

	var params= {};
	var fisDate = $("#fisDate").val();
	var endDate = $("#endDate").val();

	params["fisDate"]= fisDate;
	params["endDate"]= endDate;
	params["methodName"] = "creditdebitForBetTowDate";


	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example5').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#example5').DataTable( {
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

					// Total over all pages
					/* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);




				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "name", "width": "5%", "defaultContent": ""},
				          {"data": "credit", "width": "5%", "defaultContent": ""},


				          ]
			} );
		} );

		var mydata = catmap;
		$('#example5').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});

}


/*get taday credit and debit report*/
function creditdebitForBetTowDate1(){

	var params= {};
	var fisDate = $("#fisDate").val();
	var endDate = $("#endDate").val();

	params["fisDate"]= fisDate;
	params["endDate"]= endDate;
	params["methodName"] = "creditdebitForBetTowDate1";


	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example6').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;


		$(document).ready(function() {
			$('#example6').DataTable( {
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

					// Total over this page
					pageTotal = api
					.column( 1 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 1 ).footer() ).html(

							'Rs'+' '+parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);




				},

				destroy: true,
				searching: false,

				columns: [

				          {"data": "name", "width": "5%"},
				          {"data": "debit", "width": "5%"},


				          ]
			} );
		} );

		var mydata = catmap;
		$('#example6').dataTable().fnAddData(mydata);

			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();

				}
			});

}


function getYesterdarTotalAmount(){

	var params= {};

	params["methodName"] = "getYesterdarTotalAmount";


	$("#yesAmt").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("yesAmt").value =parseFloat(v.totalAmount).toFixed(2);

				});
			})


}

function getTodaySaleTotalAmount(){

	var params= {};

	params["methodName"] = "getTodaySaleTotalAmount";


	$("#dupsaletotal").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("dupsaletotal").value = v.totalAmount;
			getTodaySaleTotalAmount1();

				});
			})


}

function getTodaySaleTotalAmount1(){

	var params= {};

	params["methodName"] = "getTodaySaleTotalAmount1";


	$("#dupsaletotal1").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("dupsaletotal1").value = v.totalAmount;
			var asd = document.getElementById("dupsaletotal").value;
			var grossSaleTotal = +asd + +v.totalAmount;
			document.getElementById("totsaleAmt").value = grossSaleTotal;

			shreeDemo();

				});
			})
}

function shreeDemo(){

	var yesterdayAmt = document.getElementById("yesAmt").value;
	var todayCreditAmt = document.getElementById("creAmt").value;
	var todayDebitAmt = document.getElementById("debAmt").value;
	var todaySaleAmt = document.getElementById("totsaleAmt").value;

	var grossSaleTotal = (+yesterdayAmt + +todayDebitAmt + +todaySaleAmt) - (+todayCreditAmt);
	document.getElementById("remAmt").value = grossSaleTotal;
}

function LoanReport(){

	var params= {};
	var custname1 = $('#custname1').val();


	params["custname1"]= custname1;
	params["methodName"] = "customerWiseLoanReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#LoanReportDetails').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#LoanReportDetails').DataTable( {

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
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);

					pageTotal = api
					.column( 5 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 5 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					loantotal=pageTotal;
					document.getElementById("loantotal").value=loantotal;
					
					
					pageTotal = api
					.column( 7 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 7 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
				},

				destroy: true,
				searching: false,


				columns: [
				          {"data": "Loanno" , "width": "5%"},
				          {"data": "cust_name", "width": "5%"},
				          {"data": "loan_date", "width": "5%"},
				          {"data": "loan_amt", "width": "5%"},
				          {"data": "interest_rate" , "width": "5%"},
				          {"data": "balance_status", "width": "5%"},
				          {"data": "paid_date", "width": "5%"},
				          {"data": "paid_amt", "width": "5%"}
				          ],
				         
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Loan Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		var check=mydata.length;
		
		if(check !=0)
		{
			$('#LoanReportDetails').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Data is Not Available For Loan Details");
		}
			}
	);
}

function getCreditReport(){

	var params= {};
	var custname1 = $('#custname1').val();


	params["custname1"]= custname1;
	params["methodName"] = "getCustomerWiseCreditReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		$('#CreditProductReport').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			var total =   $('#CreditProductReport').DataTable( {

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

									

				},

				destroy: true,
				searching: false,


				columns: [
				          {"data": "custname" , "width": "5%"},
				          {"data": "fertilizerBalance" , "width": "5%"},
				          {"data": "seedBalance", "width": "5%"},
				          {"data": "pesticideBalancel", "width": "5%"}
				          ],
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Credit Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]
			} );
		} );

		var mydata = catmap;
		var check=mydata.length;
		
		if(check !=0)
		{
			$('#CreditProductReport').dataTable().fnAddData(mydata);
		}
		else
		{
			alert("Data is Not available for Credit Product");
		}
			}
	);
	
	loanGrandTotal();
}
function loanGrandTotal()
{
	
	var grandTotal= +loantotal + +producttotal;
	document.getElementById("grandtotal").value=grandTotal;
}


function getCustNameWiseLoanPaymentReport(){
	//this.getTotalAndInterest = getTotalAndInterest;

	var custname = $("#customername").val();
	
	var params= {};

	params["custname"]= custname;
	params["methodName"] = "getCustNameWiseLoanPaymentReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
	
		//To clear table
		$('#CustNameWiseLoanPaymentReportTable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			$('#CustNameWiseLoanPaymentReportTable').DataTable( {

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
					.column( 2 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 2 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					var loanamount=pageTotal;
					
					
				},
									
									
									
									
				destroy: true,
				searching: false,

				columns: [
				          {"data": "paydate", "width": "5%", "defaultContent": ""},
				          {"data": "paymentmode" , "width": "5%", "defaultContent": ""},
				          {"data": "payamout" , "width": "5%", "defaultContent": ""},
				          ],
				      	
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	
				                    	 
				                    	 
				                    	 title : function() {
				                    		/* paid = $("#paidamount").val();
				                    			loan = $("#loanamount").val();
				                    			balance = $("#remainingamt").val();*/
				                    			
				                    		 
				                    		 return "Customer Name Wise Payment Report \n Customer Name : ";
				                    	
				                    	 },
				                    	 orientation : 'portrait-secondary',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ]

			} );
		} );

		var mydata = catmap;
		$('#CustNameWiseLoanPaymentReportTable').dataTable().fnAddData(mydata);

			}
	);
}

/*var paid,loan,balance;
function getTotalAndInterest(){
	paid = $("#paidamount").val();
	loan = $("#loanamount").val();
	balance = $("#remainingamt").val();
	
}
alert(paid)

var amit = new getCustNameWiseLoanPaymentReport();*/
function getCustNameWiseLoanReportValidation()
{
	if(document.goodsReceiveForm.supplier.value != "")
	{
		
	}
	else
	{
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function()  
				{
		
				var msg="Please Select Customer Name";
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

function getCustNameWiseLoanReportValidation()
{
	if(document.loanpaymentform.CustName1.value != "")
	{
		if(document.loanpaymentform.cloaserdate.value != "")
		{
			if(document.loanpaymentform.interestrate.value != "")
			{
				getCustNameWiseLoanReport();
			}
			else
			{
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function()  
						{
				
						var msg="Please Enter Interest Rate";
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
			
					var msg="Please Select Closer Date";
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
		
				var msg="Please Select Customer Name";
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

function getCustNameWiseLoanReport(){
	//this.getTotalAndInterest = getTotalAndInterest;

	var custname = $('#CustName1').val();
	var cloaserdate=$('#cloaserdate').val();
	var interestrate=$('#interestrate').val();
	var params= {};

	params["custname"]= custname;
	params["cloaserdate"]= cloaserdate;
	params["interestrate"]= interestrate;
	params["methodName"] = "getCustNameWiseLoanReport";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
	
		//To clear table
		$('#CustNameWiseLoanReportTable').dataTable().fnClearTable();

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$(document).ready(function() {
			$('#CustNameWiseLoanReportTable').DataTable( {

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
					.column( 3 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 3 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					var loanamt=pageTotal;
				
					pageTotal = api
					.column( 6 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 6 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					var interestamt=pageTotal;
					
					
					pageTotal = api
					.column( 9 )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );

					// Update footer
					$( api.column( 9 ).footer() ).html(

							parseFloat(pageTotal).toFixed(2)

					);
					console.log( pageTotal);
					var paidamt=pageTotal;
					
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
					paidamtwithinterest=pageTotal;
					
					
					var credit=+loanamt+ +interestamt;
					//var debit=+paidamt+ +paidamtwithinterest;
					var remamt=+credit-paidamtwithinterest;
					
					document.getElementById("creditamount").value =credit; 
					document.getElementById("debitamount").value = paidamtwithinterest;
					document.getElementById("remamt").value = remamt;
					
									},
									
									
									
									
				destroy: true,
				searching: false,

				columns: [
				          {"data": "custname", "width": "5%", "defaultContent": ""},
				          {"data": "loanno" , "width": "5%", "defaultContent": ""},
				          {"data": "loandate" , "width": "5%", "defaultContent": ""},
				          {"data": "loanamount" , "width": "5%", "defaultContent": ""},
				          {"data": "interest_rate" , "width": "10%", "defaultContent": ""},
				          {"data": "Days" , "width": "5%", "defaultContent": ""},		
				          {"data": "interest_amount" , "width": "5%", "defaultContent": ""},
				          {"data": "interest_per_day" , "width": "5%", "defaultContent": ""},
				          {"data": "paiddate" , "width": "5%", "defaultContent": ""},
				          {"data": "paidamount" , "width": "5%", "defaultContent": ""},
				          {"data": "paidamountinterest" , "width": "5%", "defaultContent": ""},
				          ],
				      	
				          dom : 'Bfrtip',
				          buttons : [ 

				                     { extend: 'copyHtml5', footer: true },
				                     { extend: 'excelHtml5', footer: true },
				                     { extend: 'csvHtml5', footer: true },
				                     { extend : 'pdfHtml5', footer: true,
				                    	 title : function() {
				                    		 return "Loan & Credit Report";
				                    	 },
				                    	 orientation : 'landscape',
				                    	 pageSize : 'LEGAL',
				                    	 titleAttr : 'PDF' 
				                     } ],
				                     
				                     "scrollY": "300px",
				                	  "scrollCollapse": true,
				                	  "paging": false,

			} );
		} );

		var mydata = catmap;
		$('#CustNameWiseLoanReportTable').dataTable().fnAddData(mydata);

			}
	);
}