//validateing password with re-password

function validatePassword(){
	var password=$('#password').val();
	var rePassword=$('#password2').val();

	if(password != rePassword){
		alert("Password mismatch")
	}
}

//Ading user detail

function regUserDetails(){
	if(document.usd.firstName.value == "")
	{
		alert("Enter Employee First Name.");
		return false;
	}	
	var letterNumber = /^[a-zA-Z, ]+$/;
	if(document.usd.firstName.value.match(letterNumber))
	{
		if(document.usd.lastName.value == "")
		 {
			 alert("Enter Employee Last Name.");
			 return false;
		 }	
		 var letterNumber = /^[a-zA-Z, ]+$/;
		 if(document.usd.lastName.value.match(letterNumber))
		 {

			 if ( document.usd.contactNo.value == "" )
			 {
				 alert("Please Enter Contact Number");
				 return false;
			 }
			 var letterNumber = /^[0-9]{10}$/;
			 if(document.usd.contactNo.value.match(letterNumber))
			 {
				 if(document.usd.address1.value == "")
				 {
					 alert("Please Enter Employee Address Line 1.");
					 return false;
				 }	
				 var letterNumber = /^[a-zA-Z0-9, ]+$/;
				 if(document.usd.address1.value.match(letterNumber))
				 {

					 if ( document.usd.userName.value == "" )
					 {

						 alert("Please Enter User Name...!!!");
						 return false;
					 }
					 var letterNumber = /^[a-zA-Z0-9, ]+$/;
					 if(document.usd.userName.value.match(letterNumber))
					 {
						 if ( document.usd.password.value == "" )
						 {

							 alert("Please Enter Password...!!!");
							 return false;
						 }
						 if ( document.usd.password2.value == "" )
						 {

							 alert("Please Enter Re-Password...!!!");
							 return false;
						 }
						 if ( document.usd.pan.value == "" )
						 {

							 alert("Please Enter Pan Number...!!!");
							 return false;
						 }
						 var letterNumber = /^[a-zA-Z0-9]+$/;
						 if(document.usd.pan.value.match(letterNumber))
						 {
							 if ( document.usd.city.value != "" )
							 {
								 var cityval = /^[a-zA-Z]+$/;
								 if(document.usd.city.value.match( cityval))
								 {
									 if ( document.usd.typeId.value != "selected" )
									 {
										 usrDetails();

									 }
									 else
									 {
										 alert("Please Select Type");
										 return false;
									 }
								 }
								 else
								 {
									 alert("Enter Only Character in City");
									 return false;
								 }
							 }
							 else
							 {
								 alert("Enter City Name");
								 return false;
							 }

						 }

						 else
						 {
							 alert("Enter Numbers And Alphabates Only in User Name Field..!!");
							 return false;
						 }
					 }

					 else
					 {
						 alert("Enter Numbers And Alphabates Only in Pan Number Field..!!");
						 return false;
					 }
				 }

				 else
				 {
					 alert("Enter Numbers And Alphabates Only in Address Line 1 Field..!!");
					 return false;
				 }	
			 }

			 else
			 {
				 alert("Enter 10 Digit Numbers Only in Contact Number Field..!!");
				 return false;
			 }	
		 }

		 else
		 {
			 alert("Enter Alphabets Only in Last Name Field..!!");
			 return false;
		 }
	}
	else
	{
		alert("Enter Alphabets Only in First Name Field..!!");
		return false;
	}
}	

function usrDetails(){

	//document.usd.btn.disabled = true;
	document.getElementById("btn").disabled = true;
	
	var params = {};
	var firstName = $('#firstName').val();
	var city = $('#city').val();
	var lastName  = $('#lastName').val();
	var contactNo = $('#contactNo').val();
	var address1 = $('#address1').val();
	var pan = $('#pan').val();
	var userName = $('#userName').val();
	var password = $('#password').val();
	var password2 = $('#password2').val();
	var typeId= $('#typeId').val();

	params["firstName"] = firstName;
	params["city"] = city;
	params["lastName"] = lastName;
	params["contactNo"] = contactNo;
	params["address1"] = address1;
	params["pan"] =pan;
	params["userName"] = userName;
	params["password"] = password;
	params["password2"] =password2;
	params ["typeId"] = typeId;
	params["methodName"] = "regUserDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		if(document.usd)
		{
			document.usd.reset();
		}	
		//document.usd.btn.disabled =false;
		 document.getElementById("btn").disabled = false;
		
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
	document.usd.reset();	

}
