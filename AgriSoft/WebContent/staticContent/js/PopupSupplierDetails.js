function supplierDetail(){
	if(document.spld.dealerName.value != "")
	{
		if(document.spld.contactNo.value != "")
		{
			var letterNumber = /^[0-9]{10}$/;
			if(document.spld.contactNo.value.match(letterNumber))
			{
				supDetails();
			} 
			else
			{
				alert("Enter  10 Digit Valid Mobile Number");
				/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Enter  10 Digit Valid Mobile Number";
					var dialog = bootbox.dialog({
						//title: "Embel Technologies Says :",
					    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
					    closeButton: false
					});
					
					setTimeout(function() {
						dialog.modal('hide');
					}, 1500);
					
					return false;
					
						});*/
			}
		}
		else
		{
			alert("Enter Dealer Mobile Number");
			/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
		
				var msg="Enter Dealer Mobile Number";
				var dialog = bootbox.dialog({
					//title: "Embel Technologies Says :",
				    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
				    closeButton: false
				});
				
				setTimeout(function() {
					dialog.modal('hide');
				}, 1500);
				
				return false;
				
					});*/
		}
	}	
	else
	{
		alert("Enter Dealer  Name.");
		/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg="Enter Dealer  Name.";
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
			}, 1500);
			
			return false;
			
				});*/
	}
}     				 

function supDetails(){

	document.getElementById("save").disabled = true;
	
	var dealerName = $('#dealerName').val();
	var personName = $('#personName').val();
	var contactNo = $('#contactNo').val();
	var landline = $('#landline').val();
	var emailId = $('#emailId').val();
	var tinNo = $('#tinNo').val();
	var city = $('#city').val();
	var address = $('#address').val();

	var params = {};

	if(personName==undefined || personName== null || personName == "" ){
		personName="N/A";
	}

	if(landline==undefined || landline== null || landline == "" ){
		landline="0";
	}
	if(emailId==undefined || emailId== null || emailId == "" ){
		emailId="N/A";
	}
	if(tinNo==undefined || tinNo== null || tinNo == "" ){
		tinNo="0";
	}

	if(city==undefined || city== null || city == "" ){
		city="N/A";
	}
	if(address==undefined || address== null || address == "" ){
		address="N/A";
	}

	params["dealerName"] = dealerName;
	params["personName"] =personName;
	params["contactNo"] = contactNo;
	params["landline"] =landline;
	params["emailId"] = emailId;
	params["tinNo"] = tinNo;
	params["city"] = city;
	params["address"] = address;
	params["methodName"] = "supplierDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		
			alert(data);
			location.reload();
			document.getElementById("save").disabled = false;
		/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
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
				document.getElementById("save").disabled = false;
			}, 1500);
			
			return false;
			
				});*/
		

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
