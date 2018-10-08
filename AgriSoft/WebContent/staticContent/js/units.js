function AddMeasuringUnit(){
	if ( document.munits.unitName.value != "" )
	{
		var letterNumber = /^[a-zA-Z]+$/;
		if(document.munits.unitName.value.match(letterNumber) )
		{
			if ( document.munits.unitDescription.value != "" )
			{
				var letterNumber = /^[a-zA-Z]+$/;
				if(document.munits.unitDescription.value.match(letterNumber) )
				{
					addUnit()
				}
				else
				{
					alert("Enter Alphabates Only in SI unit Description Field.");
					return false;
				}
			}
			else
			{
				alert("Please Enter SI Unit Description");
				return false;
			}

		}
		else
		{
			alert("Enter Alphabates Only in Unit Name  Field..!!");
			return false;
		}	
	}
	else
	{
		alert("Please Enter Unit");
		return false;
	}
}

function addUnit(){

	document.getElementById("save").disabled=true;
	var unitName = $('#unitName').val();
	var unitDescription = $('#unitDescription').val();
	var params = {};

	params["unitName"] = unitName;
	params["unitDescription"] = unitDescription;
	params["methodName"] = "measuringUnits";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		if(document.munits)
		{
			document.munits.reset();
			document.getElementById("save").disabled=false;
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
	document.munits.reset();	

}
