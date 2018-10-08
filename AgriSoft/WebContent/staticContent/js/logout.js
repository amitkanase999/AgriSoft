function Logout(){

	var params = {};

	params["methodName"] = "logout";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{   
		alert (" You Are Logged Out Successfully!!!");
		window.location.replace("/AgriSoft/jsp/login.jsp");

			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}


function login(){
	var uname = $("#uname").val();
	var pass = $("#pass").val();

	var params = {};

	params["uname"] = uname;
	params["pass"] = pass;
	params["methodName"] = "login";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{   
		window.location.replace("/AgriSoft/jsp/index1.jsp");
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function language(){

	var language = $("#language").val();

	var params = {};

	params["language"] = language;

	params["methodName"] = "language";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{   
		location.reload();
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}
