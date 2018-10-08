function supAccount(){

	document.splAcc.btn.disabled = true;

	var fk_supplier_id= $('#fk_supplier_id').val();
	var billNo= $('#billNo').val();
	var totalAmt= $('#totalAmt').val();

	var params = {};

	params["fk_supplier_id"] = fk_supplier_id;
	params["billNo"] =billNo;
	params["totalAmt"] =totalAmt;
	params["methodName"] = "suppAccountDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		if(document.splAcc)
		{
			document.splAcc.reset();
		}	
		document.splAcc.btn.disabled =false;
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
	document.splAcc.reset();	

}
