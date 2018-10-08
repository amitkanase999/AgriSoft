function onlyletters(input)
{
	var regex=/[^a-z]/gi;
	input.value=input.value.replace(regex,"");
}

function onlyNumbers(input)
{
	var regex=/[^0-9]/g;
	input.value=input.value.replace(regex,"");
}

function onlyNumbersWithDot(input)
{
	var regex=/[^0-9.]/g;
	input.value=input.value.replace(regex,"");
}

function checkFilled() {
    var inputVal = document.getElementById("payamt");
    if (inputVal.value == "") {
        inputVal.style.backgroundColor = "yellow";
    }
    else{
        inputVal.style.backgroundColor = "green";
        document.getElementById("payamt").style.color = "#ffffff";
        document.getElementById("payamt").style.fontSize = "x-large";
     
    }
}

function checkFilledRed() {
    var inputVal = document.getElementById("totalamt");
    if (inputVal.value == "") {
        //inputVal.style.backgroundColor = "yellow";
        inputVal.style.backgroundColor = "red";
        document.getElementById("totalamt").style.color = "#ffffff";
        document.getElementById("totalamt").style.fontSize = "x-large";
    }
    else{
        inputVal.style.backgroundColor = "red";
        document.getElementById("totalamt").style.color = "#ffffff";
        document.getElementById("totalamt").style.fontSize = "x-large";
     
    }
}
    
    function checkFilledPink() {
        var inputVal = document.getElementById("interestamt");
        if (inputVal.value == "") {
            //inputVal.style.backgroundColor = "yellow";
            inputVal.style.backgroundColor = "#FFC0CB";
            document.getElementById("interestamt").style.color = "#800000";
            document.getElementById("interestamt").style.fontSize = "x-large";
        }
    
}
    
function checkFilledFontColor()
{
	document.getElementById("loanamt").style.color = "#800000";
    document.getElementById("loanamt").style.fontSize = "x-large";
    
    document.getElementById("interestrate1").style.color = "#800000";
    document.getElementById("interestrate1").style.fontSize = "x-large";
    
    document.getElementById("day").style.color = "#800000";
    document.getElementById("day").style.fontSize = "x-large";
    
    document.getElementById("balance").style.color = "#800000";
    document.getElementById("balance").style.fontSize = "x-large";
}

function creditLoanDetailsValidation()
{
	if(document.creditloan.Custname.value != "")
	{
		
	
			if(document.creditloan.date.value!="")
			{
				/*if(document.creditloan.intrrate.value!="")
				{*/
					if(document.creditloan.loanpaymentMode.value!="selected")
					{
						if(document.creditloan.loanamount.value!="")
						{
							if(document.creditloan.loanamount.value > 0)
							{
								CreditloanDetails(); 
							}
							else
							{
								
								$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
										{
							
									var msg="Amount Should be gretarer than Zero";
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
						
								var msg="Please Enter Amount";
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
					
							var msg="Please Select Payment Mode";
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
			/*	}
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
				}*/
			}
			else
			{
				
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Please Select Date";
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
	
			var msg="Select Customer Name";
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

function CreditloanDetails(){

	document.creditloan.save.disabled = true;
	
	var loanno =$('#loanno1').val();
	var custname = $('#Custname').val();
	var date = $('#date').val();
	var type = $('#type').val();
	var loanpaymentMode = $('#loanpaymentMode').val();
	//var interestrate = $('#intrrate').val();
	var loanamount  = $('#loanamount').val();
	var chequeno2 = $('#chequeno2').val();
	var nameOnCheque2 = $('#nameOnCheque2').val();
	var cardNum3 = $('#cardNum3').val();
	var accNum3 = $('#accountNum').val();
	var bankName3 = $('#nameofbank').val();


	var params = {};

	if(loanno==undefined || loanno== null || loanno == "" ){
		loanno=0;
	}
	if(custname==undefined || custname== null || custname == "" ){
		custname="N/A";
	}
	if(date==undefined || date== null || date == "" ){
		date="0000-00-00";
	}
	if(loanpaymentMode==undefined || loanpaymentMode== null || loanpaymentMode == "" ){
		loanpaymentMode="N/A";
	}
	if(interestrate==undefined || interestrate== null || interestrate == "" ){
		interestrate=0;
	}
	if(loanamount==undefined || loanamount== null || loanamount == "" ){
		loanamount=0.0;
	}
	if(chequeno2==undefined || chequeno2== null || chequeno2 == "" ){
		chequeno2="N/A";
	}
	if(nameOnCheque2==undefined || nameOnCheque2== null || nameOnCheque2 == "" ){
		nameOnCheque2="N/A";
	}
	if(cardNum3==undefined || cardNum3== null || cardNum3 == "" ){
		cardNum3="N/A";
	}
	if(accNum3==undefined || accNum3== null || accNum3 == "" ){
		accNum3="N/A";
	}
	if(bankName3==undefined || bankName3== null || bankName3 == "" ){
		bankName3="N/A";
	}

	params["loanno"] =loanno;
	params["custname"] =custname;
	params["date"] =date;
	params["type"] =type;
	params["loanpaymentMode"] = loanpaymentMode;
	//params["interestrate"] =interestrate;
	params["loanamount"] = loanamount;
	params["chequeno2"] = chequeno2;
	params["nameOnCheque2"] = nameOnCheque2;
	params["cardNum3"] = cardNum3;
	params["accNum3"] = accNum3;
	params["bankName3"] = bankName3;
	params["methodName"] = "creditLoanDetails";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	  
	{
		//alert(data);
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
				document.creditloan.save.disabled = false;
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

function getLoanNo()
{
	
	 var input =$('#Customername').val();

	$("#loannum").empty();
	$("#loannum").append($("<option></option>").attr("value","").text("Select Loan No"));
	var params= {};
	params["loancustomer"]= input;
	params["methodName"] = "getAllLoanNo";
	
	
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)		
			{var count = 1;
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			//$("#loanno").append($("<option></option>").attr("value",v.loanno).text(v.loanno));
			$("#loannum").append($("<option></option>").attr("value",i).text(v.Loanno)); 
			//count++;
				});
			})

}

function creditLoanPaymentValidation() {
	var creditCustomer = $("#Customername").val();
	var creditCustBillNo = $("#loannum").val();
	var paymentDate = $("#paymentdate").val();
	var creditCustPaymentMode = $("#loanpaymentMode1").val();
	var creditCustCreditAmt = $("#payamt").val();
	var creditCustBalanceAmt = $("#balance").val();
	
	if (creditCustomer != null && creditCustomer != "") 
	{
		if (creditCustBillNo != null && creditCustBillNo != "") 
		{
			if(paymentDate != null && paymentDate != "" && paymentDate != " ")
			{
				var creditAmtRegExp = /^[0-9]+([.][0-9]+)?$/;
				if (creditCustPaymentMode != "selected" && creditCustPaymentMode != "" && creditCustPaymentMode != " ") 
				{
					if (creditCustCreditAmt.match(creditAmtRegExp)) 
					{
						if (Number(creditCustCreditAmt) <= Number(creditCustBalanceAmt)) 
						{
							if (Number(creditCustCreditAmt) > 0) 
							{
								if(creditCustCreditAmt != null && creditCustCreditAmt != "" && creditCustCreditAmt != " ") 
								{
									CreditloanPayment();
								} 
							}
							else{
								
								$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
										{
							
									var msg="Credit Amount should be greater than 1";
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
						else{

							$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
									{
						
								var msg="Credit Amount should not greater than Balance Amount";
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
					else{
						
						$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
								{
					
							var msg="Please Enter Credit Amount";
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
				else{
					
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
				
						var msg="Please Select Payment Mode";
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
			else{
				
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Please Select Date";
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
		else{
			
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Please Select Loan Number";
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
	else{
		
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

function CreditloanPayment(){

	document.loanpayments.save.disabled = true;

	var Customername =$('#Customername').val();
	var loannum = $('#loannum').val();
	var paymentdate = $('#paymentdate').val();
	var loanamt = $('#loanamt').val();
	
	var interestrate1=$('#interestrate1').val();
	var interestamt=$('#interestamt').val();
	var day=$('#day').val();
	var totalamt=$('#totalamt').val();
	
	var ptype = $('#ptype').val();
	var balance = $('#balance').val();
	var loanpaymentMode1  = $('#loanpaymentMode1').val();
	var payamt = $('#payamt').val();
	var chequeno3 = $('#chequeno3').val();
	var nameOnCheque3 = $('#nameOnCheque3').val();
	var cardNum4 = $('#cardNum4').val();
	var accNum4 = $('#accNum4').val();
	var bankName4 = $('#bankName4').val();
	
	var params = {};

	if(Customername==undefined || Customername== null || Customername == "" ){
		Customername="N/A";
	}
	if(paymentdate==undefined || paymentdate== null || paymentdate == "" ){
		paymentdate="0000-00-00";
	}
	if(loannum==undefined || loannum== null || loannum == "" ){
		loannum="N/A";
	}
	if(loanamt==undefined || loanamt== null || loanamt == "" ){
		loanamt=0.0;
	}
	
	
	if(interestrate1==undefined || interestrate1== null || interestrate1 == "" ){
		interestrate1=0.0;
	}
	if(day==undefined || day== null || day == "" ){
		day=0.0;
	}
	if(interestamt==undefined || interestamt== null || interestamt == "" ){
		interestamt=0.0;
	}
	if(totalamt==undefined || totalamt== null || totalamt == "" ){
		totalamt=0.0;
	}
	
	
	if(balance==undefined || balance== null || balance == "" ){
		balance=0.0;
	}
	if(loanpaymentMode1==undefined || loanpaymentMode1== null || loanpaymentMode1 == "" ){
		loanpaymentMode1="N/A";
	}
	if(payamt==undefined || payamt== null || payamt == "" ){
		payamt=0.0;
	}
	if(chequeno3==undefined || chequeno3== null || chequeno3 == "" ){
		chequeno3="N/A";
	}
	if(nameOnCheque3==undefined || nameOnCheque3== null || nameOnCheque3 == "" ){
		nameOnCheque3="N/A";
	}
	if(cardNum4==undefined || cardNum4== null || cardNum4 == "" ){
		cardNum4="N/A";
	}
	if(accNum4==undefined || accNum4== null || accNum4 == "" ){
		accNum4="N/A";
	}
	if(bankName4==undefined || bankName4== null || bankName4 == "" ){
		bankName4="N/A";
	}

	
	params["Customername"] =Customername;
	params["paymentdate"] =paymentdate;
	params["loannum"] =loannum;
	params["loanamt"] =loanamt;
	
	params["interestrate1"] =interestrate1;
	params["day"] =day;
	params["interestamt"] =interestamt;
	params["totalamt"] =totalamt;
	
	params["ptype"] = ptype;
	params["balance"] =balance;
	params["loanpaymentMode1"] = loanpaymentMode1;
	params["payamt"] = payamt;
	params["chequeno3"] = chequeno3;
	params["nameOnCheque3"] = nameOnCheque3;
	params["cardNum4"] = cardNum4;
	params["accNum4"] = accNum4;
	params["bankName4"] = bankName4;
	params["methodName"] = "creditLoanPayment";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	  
	{
		//alert(data);
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
				document.loanpayments.save.disabled = false;
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

function getLoanDetails()
{
	
	var loannum =$('#loannum').val();
	var paymentdate=$('#paymentdate').val();
	var params= {};
	
	$("#loanamt").append($("<input/>").attr("value","").text());
	$("#interestrate1").append($("<input/>").attr("value","").text());
	$("#day").append($("<input/>").attr("value","").text());
	$("#interestamt").append($("<input/>").attr("value","").text());
	$("#day").append($("<input/>").attr("value","").text());
	params["loannum"]= loannum;
	params["paymentdate"]= paymentdate;
	params["methodName"] = "getAllLoanDetails";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)		
			{var count = 1;
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
					document.getElementById("loanamt").value = v.loan_amt;
					document.getElementById("interestrate1").value = v.interest_rate;
					document.getElementById("day").value = v.days;
					document.getElementById("interestamt").value = v.interestamount;
					document.getElementById("totalamt").value = v.totalamount;
					document.getElementById("balance").value = v.balance_status;
				});
			})

}

function generateLonNo()
{
	
		var params= {};
		$("#loanno1").append($("<input/>").attr("value","").text());
		params["methodName"] = "genLoanNo";
		
		
		
		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)		
				{var count = 1;
			var jsonData = $.parseJSON(data);
			//var jsonData = jsonData.list;
			$.each(jsonData,function(i,v)
					{
				//$("#loanno").append($("<option></option>").attr("value",v.loanno).text(v.loanno));
				//$("#loanno1").append($("<option></option>").attr("value",i).text(v.MaxLoanNo)); 
				document.getElementById("loanno1").value = v.MaxLoanNo;
				document.getElementById("loanno1").style.color = "#800000";
			    document.getElementById("loanno1").style.fontSize = "small";
				//count++;
					});
				})
}

function loanPaymentValidation()
{
	if(document.loanpayment11.loanCustname.value != "")
	{
		if(document.loanpayment11.paydate.value != "")
		{
			if(document.loanpayment11.loanpaymentMode1.value != "selected")
			{
				if(document.loanpayment11.payamount.value != "")
				{
					loanPayment();
				}
				else
				{
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
				
						var msg="Please Enter pay amount ";
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
			
					var msg="Please Select Payment Mode";
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
		
				var msg="Please Select Date";
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

function loanPayment(){

	//document.loanpayment11.save.disabled = true;
	document.loanpayment11.save.disabled = true;
	var loanCustname =$('#loanCustname').val();
	var paydate = $('#paydate').val();
	var paytype = $('#paytype').val();
	var loanpaymentMode1 = $('#loanpaymentMode1').val();
	var payamount = $('#payamount').val();
	var chequeno3 = $('#chequeno3').val();
	var nameOnCheque3  = $('#nameOnCheque3').val();
	var cardNum4 = $('#cardNum4').val();
	var accountNum4 = $('#accountNum4').val();
	var nameofbank4 = $('#nameofbank5').val();
	


	var params = {};

	if(loanCustname==undefined || loanCustname== null || loanCustname == "" ){
		loanCustname=0;
	}

	if(paydate==undefined || paydate== null || paydate == "" ){
		paydate="0000-00-00";
	}
	if(payamount==undefined || payamount== null || payamount == "" ){
		payamount="0";
	}
	if(chequeno3==undefined || chequeno3== null || chequeno3 == "" ){
		chequeno3=0;
	}
	if(nameOnCheque3==undefined || nameOnCheque3== null || nameOnCheque3 == "" ){
		nameOnCheque3="N/A";
	}
	if(cardNum4==undefined || cardNum4== null || cardNum4 == "" ){
		cardNum4="N/A";
	}
	if(accountNum4==undefined || accountNum4== null || accountNum4 == "" ){
		accountNum4="N/A";
	}
	if(nameofbank4==undefined || nameofbank4== null || nameofbank4 == "" ){
		nameofbank4="N/A";
	}
	
	

	params["loanCustname"] =loanCustname;
	params["paydate"] =paydate;
	params["paytype"] =paytype;
	params["loanpaymentMode1"] =loanpaymentMode1;
	params["payamount"] = payamount;
	params["chequeno3"] =chequeno3;
	params["nameOnCheque3"] = nameOnCheque3;
	params["cardNum4"] = cardNum4;
	params["accountNum4"] = accountNum4;
	params["nameofbank4"] = nameofbank4;

	params["methodName"] = "LoanPaymentDetails";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	  
	{
		//alert(data);
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
				document.loanpayment11.save.disabled = false;
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