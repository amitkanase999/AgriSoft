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
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
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
					
						});
			}
		}
		else
		{
			
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
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
				
					});
		}
	}	
	else
	{
		$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
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
			
				});
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
				document.getElementById("save").disabled = false;
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

function reset()
{
	document.spld.reset();	

}

/********* Edit Supplier Details ************/

function getSupplierDetails(){
	var params= {};

	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,fkRootSupId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootSupId = list.options[i].getAttribute('data-value');
		}
	}

	$("#dealerName").append($("<input/>").attr("value","").text());
	$("#personName").append($("<input/>").attr("value","").text());
	$("#contactNo").append($("<input/>").attr("value","").text());
	$("#landline").append($("<input/>").attr("value","").text());
	$("#emailId").append($("<input/>").attr("value","").text());
	$("#city").append($("<input/>").attr("value","").text());
	$("#tinNo").append($("<input/>").attr("value","").text());
	$("#address").append($("<input/>").attr("value","").text());

	params["SupplierId"]= fkRootSupId;
	params["methodName"] = "getSupplierDetailsToEdit";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("dealerName").value = v.dealerName;
			document.getElementById("personName").value = v.personName;
			document.getElementById("contactNo").value = v.contactNo;
			document.getElementById("landline").value = v.landline;
			document.getElementById("emailId").value = v.email;
			document.getElementById("city").value = v.city;
			document.getElementById("tinNo").value = v.tin;
			document.getElementById("address").value = v.address;
				});
	}).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
		}
	});
}

function updatesupplierDetail()
{
	if(document.spld1.dealerName.value != "")
	{
		/*var name = /^[a-zA-Z]{2,30}$/;
		if(dealerName.value.match(name))
		{*/
			if(document.spld1.contactNo.value != "")
			{
				var phoneno = /^\d{10}$/;
				if(contactNo.value.match(phoneno))
				{
					updateSupplierDetails();
				}
				else
				{
					alert("Enter 10 Digit valid Mobile Number");
					return false;
				}
			}
			else
			{
				alert("Enter Mobile No");
				return false;
			}
		/*}
		else
		{
			alert("Enter Character only in Suppiler Name");
			return false;
		}*/
	}
	else
	{
		alert("Enter Supplier Name");
		return false;
	}
}	

function updateSupplierDetails(){

	document.getElementById("save").disabled = true;
	var input = document.getElementById('supplier'),
	list = document.getElementById('sup_drop'),
	i,fkRootSupId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootSupId = list.options[i].getAttribute('data-value');
		}
	}

	var dealerName = $('#dealerName').val();
	var personName = $('#personName').val();
	var contactNo = $('#contactNo').val();				
	var landline = $('#landline').val();
	var emailId = $('#emailId').val();
	var city = $('#city').val();
	var tinNo = $('#tinNo').val();
	var address = $('#address').val();

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

	var params = {};

	params["supplierId"] = fkRootSupId;
	params["dealerName"] = dealerName;	
	params["personName"] = personName;
	params["contactNo"] = contactNo;
	params["landline"] = landline;
	params["emailId"] =emailId;
	params["city"] = city;
	params["tinNo"] = tinNo;
	params["address"] = address;
	params["methodName"] = "updateSupplierDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){
		alert(data);
		if(document.spld1)
		{
			document.spld1.reset();
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