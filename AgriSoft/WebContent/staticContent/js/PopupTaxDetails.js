function addTaxDetails(){
	if ( document.txc.taxType.value !="" )
	{
		if ( document.txc.taxPercentage.value !="" )
		{
			txCreation();
		}
		else
		{
			alert("Enter Tax percentage");
		}
	}
	else
	{
		alert("Enter Tax type");
	}
}




function txCreation(){
	
	document.getElementById("save").disabled = true;
	var taxType= $('#taxType').val();
	var taxPercentage = $('#taxPercentage').val();

	var params= {};

	params ["taxType"] = taxType;
	params ["taxPercentage"] = taxPercentage;
	params["methodName"] = "taxCreation";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
				alert(data);
				document.getElementById("save").disabled = false;
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
