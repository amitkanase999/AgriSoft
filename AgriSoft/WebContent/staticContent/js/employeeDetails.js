
function employeedetails(){

	if(document.empd.firstName.value == "")
	{
		alert("Enter Employee First Name.");
		return false;
	}	
	var letterNumber = /^[a-zA-Z, ]+$/;
	if(document.empd.firstName.value.match(letterNumber))
	{
		if(document.empd.middleName.value == "")
		{
			alert("Enter Employee Middle Name.");
			return false;
		}	
		var letterNumber = /^[a-zA-Z, ]+$/;
		if(document.empd.middleName.value.match(letterNumber))
		{
			if(document.empd.lastName.value == "")
			{
				alert("Enter Employee Last Name.");
				return false;
			}	
			var letterNumber = /^[a-zA-Z, ]+$/;
			if(document.empd.lastName.value.match(letterNumber))
			{
				if ( document.empd.joiningDate.value == "" )
				{

					alert("Please Select Date of Joining");
					return false;
				}

				var letterNumber = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if(document.empd.emailId.value.match(letterNumber) || document.empd.emailId.value == null ||  document.empd.emailId.value == "")
				{
					if ( document.empd.salary.value == "" )
					{
						alert("Please Enter Salary.");
						return false;
					}
					var letterNumber = /^[0-9]+$/;
					if(document.empd.salary.value.match(letterNumber))
					{
						if ( document.empd.contactNo.value == "" )
						{
							alert("Please Enter Contact Number");
							return false;
						}
						var letterNumber = /^[0-9]{10}$/;
						if(document.empd.contactNo.value.match(letterNumber))
						{
							if(document.empd.address.value == "")
							{
								alert("Please Enter Employee Address.");
								return false;
							}	
							var letterNumber = /^[a-zA-Z0-9, ]+$/;
							if(document.empd.address.value.match(letterNumber))
							{
								if ( document.empd.zipCode.value == "" )
								{

									alert("Please Enter Zip Code");
									return false;
								}
								var letterNumber = /^[0-9]{6}$/;
								if(document.empd.zipCode.value.match(letterNumber))
								{
									empDetails();
								}
								else
								{
									alert("Enter 6 Digit Numbers Only in Zip Code..!!");
									return false;
								}
							}

							else
							{
								alert("Enter Alphabates Only in Address..!!");
								return false;
							}	
						}

						else
						{
							alert("Enter 10 Digit Numbers Only in Contact Number..!!");
							return false;
						}	
					}

					else
					{
						alert("Enter Numbers Only in Salary..!!");
						return false;
					}
				}

				else
				{
					alert("Enter a Valid Email Adress..!!");
					return false;
				}
			}

			else
			{
				alert("Enter Alphabets Only in Last Name..!!");
				return false;
			}
		}

		else
		{
			alert("Enter Alphabets Only in Middle Name..!!");
			return false;
		}
	}
	else
	{
		alert("Enter Alphabets Only in First Name..!!");
		return false;
	}
}	

function empDetails(){

	//document.empd.btn.disabled = true;
	 document.getElementById("save").disabled = true;
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();
	var joiningDate = $('#joiningDate').val();
	var salary = $('#salary').val();
	var address = $('#address').val();
	var contactNo  = $('#contactNo').val();
	var emailId = $('#emailId').val();
	var zipCode = $('#zipCode').val();

	var params = {};

	params["firstName"] = firstName;
	params["middleName"] =middleName;
	params["lastName"] =lastName;
	params["joiningDate"] =joiningDate;
	params["salary"] =salary;
	params["address"] = address;
	params["contactNo"] =contactNo;
	params["emailId"] = emailId;
	params["zipCode"] = zipCode;
	params["methodName"] = "regDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		alert(data);
		if(document.empd)
		{
			document.empd.reset();
		}	
		//document.empd.btn.disabled =false;
		 document.getElementById("save").disabled = false;
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
	document.empd.reset();	

}


/********* Edit Employee Details ************/
function getEmployeeDetails(){
	var params= {};

	var input = document.getElementById('employee'),
	list = document.getElementById('emp_drop'),
	i,fkRootEmpId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootEmpId = list.options[i].getAttribute('data-value');
		}
	}

	$("#firstName").append($("<input/>").attr("value","").text());
	$("#middleName").append($("<input/>").attr("value","").text());
	$("#lastName").append($("<input/>").attr("value","").text());
	$("#joiningDate").append($("<input/>").attr("value","").text());
	$("#emailId").append($("<input/>").attr("value","").text());
	$("#salary").append($("<input/>").attr("value","").text());
	$("#contactNo").append($("<input/>").attr("value","").text());
	$("#address").append($("<input/>").attr("value","").text());
	$("#zipCode").append($("<input/>").attr("value","").text());

	params["EmpId"]= fkRootEmpId;

	params["methodName"] = "getEmployeeDetailsToEdit";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){

		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("firstName").value = v.firstName;
			document.getElementById("middleName").value = v.middleName;
			document.getElementById("lastName").value = v.lastName;
			document.getElementById("joiningDate").value = v.joiningDate;
			document.getElementById("emailId").value = v.email;
			document.getElementById("salary").value = v.salary;
			document.getElementById("contactNo").value = v.contactNo;
			document.getElementById("address").value = v.addresst;
			document.getElementById("zipCode").value = v.zipCode;

				});
	}).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {

		}
	});

}

/*============= Update employee detail ========*/
function editEmployee(){

	if(document.empd1.firstName.value == "")
	{
		alert("Enter Employee First Name.");
		return false;
	}	
	var letterNumber = /^[a-zA-Z, ]+$/;
	if(document.empd1.firstName.value.match(letterNumber))
	{
		if(document.empd1.middleName.value == "")
		{
			alert("Enter Employee Middle Name.");
			return false;
		}	
		var letterNumber = /^[a-zA-Z, ]+$/;
		if(document.empd1.middleName.value.match(letterNumber))
		{
			if(document.empd1.lastName.value == "")
			{
				alert("Enter Employee Last Name.");
				return false;
			}	
			var letterNumber = /^[a-zA-Z, ]+$/;
			if(document.empd1.lastName.value.match(letterNumber))
			{

				var letterNumber = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if(document.empd1.emailId.value.match(letterNumber) || document.empd1.emailId.value == null ||  document.empd1.emailId.value == "")
				{

					if ( document.empd1.salary.value == "" )
					{

						alert("Please Enter Salary.");
						return false;
					}
					var letterNumber = /^[0-9]+$/;
					if(document.empd1.salary.value.match(letterNumber))
					{
						if ( document.empd1.contactNo.value == "" )
						{
							alert("Please Enter Contact Number");
							return false;
						}
						var letterNumber = /^[0-9]{10}$/;
						if(document.empd1.contactNo.value.match(letterNumber))
						{
							if(document.empd1.address.value == "")
							{
								alert("Please Enter Employee Address.");
								return false;
							}	
							var letterNumber = /^[a-zA-Z0-9, ]+$/;
							if(document.empd1.address.value.match(letterNumber))
							{
								if ( document.empd1.zipCode.value == "" )
								{

									alert("Please Enter Zip Code");
									return false;
								}
								var letterNumber = /^[0-9]{6}$/;
								if(document.empd1.zipCode.value.match(letterNumber))
								{
									updateEmployeeDetails();
								}
								else
								{
									alert("Enter 6 digit Numbers Only in zip code..!!");
									return false;
								}
							}

							else
							{
								alert("Enter Alphabates Only in address..!!");
								return false;
							}	
						}

						else
						{
							alert("Enter 10 digit Numbers Only in contact number..!!");
							return false;
						}	
					}

					else
					{
						alert("Enter Numbers Only in salary..!!");
						return false;
					}
				}

				else
				{
					alert("Enter a Valid email adress..!!");
					return false;
				}
			}

			else
			{
				alert("Enter Alphabets Only in last name..!!");
				return false;
			}
		}

		else
		{
			alert("Enter Alphabets Only in middle name..!!");
			return false;
		}
	}

	else
	{
		alert("Enter Alphabets Only in first name..!!");
		return false;
	}

}
function updateEmployeeDetails(){

	document.empd1.btn.disabled = true;

	var input = document.getElementById('employee'),
	list = document.getElementById('emp_drop'),
	i,fkRootEmpId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			fkRootEmpId = list.options[i].getAttribute('data-value');
		}
	}
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();				
	var joiningDate = $('#newJoiningDate').val();
	var emailId = $('#emailId').val();
	var salary = $('#salary').val();
	var contactNo = $('#contactNo').val();
	var address = $('#address').val();
	var zipCode = $('#zipCode').val();
	var oldJoiningDate = $('#joiningDate').val();

	var params = {};

	params["EmployeeId"] = fkRootEmpId;
	params["firstName"] = firstName;	
	params["middleName"] = middleName;
	params["lastName"] = lastName;
	params["joiningDate"] = joiningDate;
	params["emailId"] =emailId;
	params["salary"] = salary;
	params["contactNo"] = contactNo;
	params["address"] = address;
	params["zipCode"] = zipCode;
	params["oldJoiningDate"] = oldJoiningDate;
	params["methodName"] = "updateEmployeeDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data){
		alert(data);
		if(document.empd1)
		{
			document.empd1.reset();
		}	
		document.empd1.btn.disabled =false;
	}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}	