function customerDetails(){

	if(document.cstd.firstName.value != "")
	{
		if(document.cstd.lastName.value != "")
		{
/*			if(document.cstd.contactNo.value!="")
			{
				var phoneno = /^\d{10}$/;
				if(contactNo.value.match(phoneno))
				{
					if(document.cstd.gstno.value!="")
					{*/
							custDetails();
/*					}
					else
					{
						$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
								{
					
							var dialog = bootbox.dialog({
								//title: "Embel Technologies Says :",
							    message: '<p class="text-center">Enter Customer GST Number...!</p>',
							    closeButton: false
							});
							
							setTimeout(function() {
								dialog.modal('hide');
							}, 1500);
							
							return false;
							
								});
					}*/
	/*			}
				else
				{
					
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
				
						var dialog = bootbox.dialog({
							//title: "Embel Technologies Says :",
						    message: '<p class="text-center">Enter 10 Digit valid Mobile Number!</p>',
						    closeButton: false
						});
						
						setTimeout(function() {
							dialog.modal('hide');
						}, 1500);
						
						return false;
						
							});
				}*/
			/*}
			else
			{
				//alert("Enter Customer Mobile Number");
				//return false;
				
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Enter Customer Mobile Number";
					var dialog = bootbox.dialog({
						//title: "Embel Technologies Says :",
					    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'</p>',
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
		
				var msg="Enter Customer Last Name";
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
		//alert("Enter Customer First Name");
		//return false;
		
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg="Enter Customer First Name";
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'</p><center><img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></center>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
			}, 1500);
			
			return false;
			
				});
		
	}

}

function custDetails(){

	document.getElementById("save").disabled = true;
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();
	var address = $('#address').val();
	var contactNo  = $('#contactNo').val();
	var emailId = $('#emailId').val();
	var zipCode = $('#zipCode').val();
	var aadhar = $('#aadharNo').val();
	var gstno=$('#gstno').val();
	var params = {};

	if(middleName==undefined || middleName== null || middleName == "" ){
		middleName="N/A";
	}
	if(lastName==undefined || lastName== null || lastName == "" ){
		lastName="N/A";
	}
	if(address==undefined || address== null || address == "" ){
		address="N/A";
	}
	if(emailId==undefined || emailId== null || emailId == "" ){
		emailId="N/A";
	}
	if(zipCode==undefined || zipCode== null || zipCode == "" ){
		zipCode=0;
	}
	if(aadhar==undefined || aadhar== null || aadhar == "" ){
		aadhar=0;
	}
	
	params["firstName"] =firstName;
	params["middleName"] =middleName;
	params["lastName"] =lastName;
	params["address"] = address;
	params["contactNo"] =contactNo;
	params["emailId"] = emailId;
	params["zipCode"] = zipCode;
	params["aadhar"] = aadhar;
	params["gstno"] = gstno;
	params["methodName"] = "customerDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	    	{
		//alert(data);
		
		
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
			
		/*	bootbox.alert({
				title: "Embel Technologies Says :",
			    message: data,
			   // className: 'bb-alternate-modal'
			    size: 'small'
			    
			});*/
			
			
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+data+'</p>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
			}, 1500);
			
			
				});
		
		
		
		if(document.cstd)
		{
			document.cstd.reset();
			document.getElementById("save").disabled = false;
		}	
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
	document.cstd.reset();	

}

/*************** Edit Credit Customer Details **********/
function getCustomerDetails(){
	var params= {};

	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,fkRootCustId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootCustId = list.options[i].getAttribute('data-value');
		}
	}
	$("#firstName").append($("<input/>").attr("value","").text());
	$("#middleName").append($("<input/>").attr("value","").text());
	$("#lastName").append($("<input/>").attr("value","").text());
	$("#address").append($("<input/>").attr("value","").text());
	$("#contactNo").append($("<input/>").attr("value","").text());
	$("#aadharNo").append($("<input/>").attr("value","").text());
	$("#emailId").append($("<input/>").attr("value","").text());
	$("#zipCode").append($("<input/>").attr("value","").text());

	params["creditCustomer"]= fkRootCustId;
	params["methodName"] = "getCreditCustomerDetailsToEdit";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("firstName").value = v.firstName;
			document.getElementById("middleName").value = v.middleName;
			document.getElementById("lastName").value = v.lastName;
			document.getElementById("address").value = v.address;
			document.getElementById("contactNo").value = v.contactNo;
			document.getElementById("aadharNo").value = v.aadhar;
			document.getElementById("emailId").value = v.email;
			document.getElementById("zipCode").value = v.zipCode;
				});
	}).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {

		}
	});
}

function editCustomerDetails(){

	if(document.cstd1.firstName.value != "")
	{
		if(document.cstd1.contactNo.value!="")
		{
			var phoneno = /^\d{10}$/;
			if(contactNo.value.match(phoneno))
			{
				updateCustomerDetails();
			}
			else
			{
				alert("Enter 10 Digit valid Mobile Number");
				return false;
			}
		}
		else
		{
			alert("Enter Customer Mobile Number");
			return false;
		}
	}
	else
	{
		alert("Enter Customer First Name");
		return false;
	}
}

function updateCustomerDetails(){
	document.cstd1.btn.disabled = true;

	var input = document.getElementById('creditCustomer'),
	list = document.getElementById('cust_drop'),
	i,fkRootCustId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootCustId = list.options[i].getAttribute('data-value');
		}
	}

	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();				
	var address = $('#address').val();
	var contactNo = $('#contactNo').val();
	var aadharNo = $('#aadharNo').val();
	var emailId = $('#emailId').val();
	var zipCode = $('#zipCode').val();

	var params = {};

	params["customerId"] = fkRootCustId;
	params["firstName"] = firstName;	
	params["middleName"] = middleName;
	params["lastName"] = lastName;
	params["address"] = address;
	params["contactNo"] =contactNo;
	params["aadharNo"] = aadharNo;
	params["emailId"] = emailId;
	params["zipCode"] = zipCode;
	params["methodName"] = "updateCreditCustomerDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){
		alert(data);
		if(document.cstd1)
		{
			document.cstd1.reset();
		}	
		document.cstd1.btn.disabled =false;
	}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}


function wholesalecustomerDetails(){

	document.getElementById("save1").disabled = true;
	var shopname = $('#shopname').val();
	var address = $('#custaddress').val();
	var mobileno = $('#mobilenum').val();
	var emailid = $('#emailid').val();
	var gstno  = $('#gstnum').val();
	var aadharNo = $('#aadharnum').val();
	var zipCode = $('#zipcode').val();
	var params = {};

	if(shopname==undefined || shopname== null || shopname == "" ){
		shopname="N/A";
	}
	if(address==undefined || address== null || address == "" ){
		address="N/A";
	}
	if(mobileno==undefined || mobileno== null || mobileno == "" ){
		mobileno=0;
	}
	if(emailId==undefined || emailId== null || emailId == "" ){
		emailId="N/A";
	}
	if(gstno==undefined || gstno== null || gstno == "" ){
		gstno=0;
	}
	if(aadharNo==undefined || aadharNo== null || aadharNo == "" ){
		aadhar=0;
	}
	
	if(zipCode==undefined || zipCode== null || zipCode == "" ){
		zipCode=0;
	}
	
	
	params["shopname"] =shopname;
	params["address"] =address;
	params["mobileno"] =mobileno;
	params["emailid"] = emailid;
	params["gstno"] = gstno;
	params["aadharNo"] = aadharNo;
	params["zipCode"] = zipCode;

	params["methodName"] = "wholesalecustomerDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	    	{
	{	//alert(data);
		
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
				document.prd.save1.disabled = false;
			}, 1500);
			
			return false;
			
				});
		
	}
		/*document.getElementById("save1").disabled = false;
		location.reload();*/
		
	}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function whosaleCustValdidation()
{
	if(document.wcstd.shopname.value != "")
	{
		if(document.wcstd.mobilenum.value != "")
		{
			if(document.wcstd.gstnum.value != "")
			{
				wholesalecustomerDetails();
			}
			else
			{
					$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
					{
			
					var msg="Enter GST No";
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
		
				var msg="Enter Contact No";
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
	
			var msg="Enter Shop Name";
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