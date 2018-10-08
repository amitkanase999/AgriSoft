function creditnotegeneration()
{
	if(document.creditnote.partyname.value != "")
	{
		if(document.creditnote.particular.value!="")
		{
			if(document.creditnote.amount.value!="")			
			{
				creditnote();
			}
			else
			{
				
				$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
			
					var msg="Enter Amount";
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
		
				var msg="Enter Particular";
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
	
			var msg="Enter Party Name";
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

function creditnote()
{
	document.getElementById("save").disabled = true;
	var partyname = $('#partyname').val();
	var particular = $('#particular').val();
	var amount = $('#amount').val();

	var params = {};

	if(partyname==undefined || partyname== null || partyname == "" )
	{
		middleName="N/A";
	}
	if(particular==undefined || particular== null || particular == "" )
	{
		lastName="N/A";
	}
	if(amount==undefined || amount== null || amount == "" )
	{
		address=0;
	}

	params["partyname"] =partyname;
	params["particular"] =particular;
	params["amount"] =amount;
	params["methodName"] = "creditnotegeneration";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data) 			 	    	{
		//alert(data);
		if(document.creditnote)
		{
			window.location.reload();
			document.getElementById("save").disabled = false;
			window.open("CreditNoteGenerationPDF.jsp");
		}	
	}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}