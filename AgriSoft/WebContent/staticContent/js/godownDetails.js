function GoDetails(){

	if(document.godown.godownName.value != "")
	{
		var letterNumber = /^[a-zA-Z0-9, ]+$/;
		if(document.godown.godownName.value.match(letterNumber))
		{
			GodownDetailsdd();
		}
		else
		{
			$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function()  
					{
			
					var msg="Enter Alphabates And Numbers Only in Godown name field..!!";
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
		
				var msg="Please Enter Godown Name";
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

function GodownDetailsdd(){

	document.getElementById("btn").disabled = true;
	var godown = $('#godownName').val();
	var params = {};

	params["godown"] =godown;
	params["methodName"] = "godownDetailsFir";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		if(data=="↵↵↵↵↵↵↵"){
			alert("Not Added");
		}
		else{
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
					document.getElementById("btn").disabled = false;
				}, 1500);
				
				return false;
				
					});
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
	document.catd.reset();	

}