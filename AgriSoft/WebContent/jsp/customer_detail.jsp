<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%boolean isHome = false;%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">

<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/RadioButtonStyle.css">

<script type="text/javascript"
	src="/AgriSoft/staticContent/js/customerDetails.js"></script>
<script type="text/javascript">
          		 function Customerlist()
          		 {
          		 window.location = "creditCustomerList.jsp";
          		 }
          		 function editCustomer() {
          			 window.location = "editCreditCustomerDetails.jsp";
				}
          		</script>
          		
 
 <script type="text/javascript">

function pageLoad(){
	$("#Regularcustomer").show();
	$("#wholesalecustomer").hide(); 
}
function ShowRegularCustomerDetails() {
	$("#Regularcustomer").show();
	$("#wholesalecustomer").hide();
	location.reload();
}
function ShowWholeSaleCustomerDetails() {
	$("#wholesalecustomer").show();
	$("#Regularcustomer").hide();
}
	
</script>
          		
</head>


<body onload="pageLoad()">
<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">
			<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("customerdetails") %>
			<%}%>
			<%if(abc.equals("english")){%>Customer Details
			<%}%>
		</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1; margin-top: 0px;">
	</div>
</div>


<div class="row form-group">
			<!-- <div class="col-md-2 col-md-offset-2 control-label" style="margin-left: 241px;">
				<label class="control-label" for="customertype">Type<sup>*</sup></label>
			</div> -->
		<!-- 	<div class="col-md-7">
				<div class="col-md-4 col-xs-6 ">
					<label class="radio-inline"> <input type="radio"
						name="customertype" id="customertype" checked="checked"
						onchange="ShowRegularCustomerDetails()">Regular Customer
					</label>
				</div>

				<div class="col-md-6 col-xs-6 col-md-ffset-1 ">
					<label class="radio-inline"> <input type="radio"
						name="customertype" id="customertype"
						onchange="ShowWholeSaleCustomerDetails()">Wholesale Customer
					</label>
				</div>
			</div> -->
			
				<div class="textalign">
   			 <label>
      			  <input type="radio" checked name="customertype" id="customertype" checked="checked"
						onchange="ShowRegularCustomerDetails()"> 
        		 <div  class="btn1 btn-sık"><span>Retail</span></div> </label>
    		 <label >
       				 <input  type="radio" name="customertype" id="customertype"
						onchange="ShowWholeSaleCustomerDetails()"> 
       				<div class="btn1 btn-sık"><span>WholeSale</span></div></label>
       			</div>
			
		</div>


<!-- -------------------Regular Customer--------------------- -->
<div id="Regularcustomer">
<div class="container col-sm-offset-1">
	<form class="form-horizontal" method="post" action="" name="cstd">
		<!-- Value of 'name' attribute is used in customerDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>


			<div class="row form-group">
				<label class="col-md-2 control-label" for="firstName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("firstName") %>
					<%}%> <%if(abc.equals("english")){%>First Name<sup>*</sup>
					<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span>
						<%
							CustomerDetailsDao dao1 = new CustomerDetailsDao();
           						List unitList =dao1.getAllCustomer();
							%>
						<input list="firstName_drop" id="firstName" class="form-control">
						<datalist id="firstName_drop">
							<%
					           for(int i=0;i<unitList.size();i++){
					        	   CustomerDetailsBean bean1 =(CustomerDetailsBean)unitList.get(i);
							%>
							<option data-value="<%=bean1.getCustId()%>"
								value="<%=bean1.getFirstName()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>

				<label class="col-md-2 control-label" for="middleName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("middleName") %>
					<%}%> <%if(abc.equals("english")){%>Middle Name <%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i></span>
						<%
							CustomerDetailsDao dao2 = new CustomerDetailsDao();
           						List middleList =dao2.getAllCustomer();
							%>
						<input list="middleName_drop" id="middleName" class="form-control">
						<datalist id="middleName_drop">
							<%
					           for(int i=0;i<middleList.size();i++){
					        	   CustomerDetailsBean bean2 =(CustomerDetailsBean)middleList.get(i);
							%>
							<option data-value="<%=bean2.getCustId()%>"
								value="<%=bean2.getMiddleName()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>

				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="lastName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("lastName") %>
					<%}%> <%if(abc.equals("english")){%>Last Name <sup>*</sup><%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i></span>
						<%
							CustomerDetailsDao dao3 = new CustomerDetailsDao();
           						List lastList =dao3.getAllCustomer();
							%>
						<input list="lastName_drop" id="lastName" class="form-control">
						<datalist id="lastName_drop">
							<%
					           for(int i=0;i<lastList.size();i++){
					        	   CustomerDetailsBean bean3 =(CustomerDetailsBean)lastList.get(i);
							%>
							<option data-value="<%=bean3.getCustId()%>"
								value="<%=bean3.getLastName()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>

				<label class="col-md-2 control-label" for="address">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("address") %>
					<%}%> <%if(abc.equals("english")){%>Address<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-map-marker"></i>
						</span> <input id="address" name="address " placeholder="Address"
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="contactNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("contactNumber") %>
					<%}%> <%if(abc.equals("english")){%>Contact No.
					<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="contactNo" name="contactNo" maxlength="10"
							placeholder="10 Digit Contact No." class="form-control input-md"
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="aadharNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("aadhar") %>
					<%}%> <%if(abc.equals("english")){%>Aadhar No.<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="aadharNo"
							name="aadharNo" maxlength="12" placeholder="12 Digit Aadhar No."
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="emailId">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("email") %>
					<%}%> <%if(abc.equals("english")){%>Email ID<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-envelope"></i>
						</span> <input id="emailId" name="emailId " placeholder="Email ID"
							class="form-control input-md" type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="zipCode">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("pinCode") %>
					<%}%> <%if(abc.equals("english")){%>Zip Code<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-envelope"></i>
						</span> <input id="zipCode" maxlength="6" name="zipCode"
							placeholder="6 Digit Zip code" class="form-control input-md"
							type="text">
					</div>
				</div>
				
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="gstno">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("gstno") %>
					<%}%> <%if(abc.equals("english")){%>GST NO<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-envelope"></i>
						</span> <input id="gstno" name="gstno " placeholder="GST No"
							class="form-control input-md" type="text">
					</div>
				</div>
				</div>
			<div class="form-group row">
				<div class="col-md-11 text-center">
					<input type="button" id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-success button-height-width"
						onclick="customerDetails()" value="Submit">
						 <input
						type="button" id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-danger  button-height-width" type="reset"
						onclick="reset()" value="Cancel">
						 <input
						style="height: 65px; font-size: 25; width: 196px" type="button"
						value="Customer List" id="listBtn" class="btn btn-primary"
						onclick="Customerlist()" /> <input
						style="height: 65px; width: 180; font-size: 25" type="button"
						value="Edit" id="listBtn" class="btn btn-primary"
						onclick="editCustomer()" />
				</div>
			</div>
		</fieldset>
	</form>
</div>
</div>
<!-- ------------------- End Regular Customer--------------------- -->


<!-- -------------------- Wholesale Customer --------------------- -->
<div id="wholesalecustomer">
	
	<form class="form-horizontal" method="post" action="" name="wcstd">
			<div class="row form-group">
				<label class="col-md-2 control-label" for="shopname">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("shopname") %>
					<%}%> <%if(abc.equals("english")){%>Shop Name<sup>*</sup>
					<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="shopname" name="shopname"
							placeholder="Enter Shop Name" class="form-control input-md"
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="Address">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("address") %>
					<%}%> <%if(abc.equals("english")){%>Address <%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="custaddress"
							name="address" placeholder="Enter Address"
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>
								
	
				<div class="row form-group">
				<label class="col-md-2 control-label" for="mobileno">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("mobileno") %>
					<%}%> <%if(abc.equals("english")){%>Contact No.<sup>*</sup>
					<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="mobilenum" name="mobileno" maxlength="10"
							placeholder="10 Digit Contact No." class="form-control input-md"
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="emailid">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("emailid") %>
					<%}%> <%if(abc.equals("english")){%>Email ID<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="emailid"
							name="emailid"  placeholder="Enter Email ID."
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>
	
			<div class="row form-group">
				<label class="col-md-2 control-label" for="gstno">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("gstno") %>
					<%}%> <%if(abc.equals("english")){%>GST No.<sup>*</sup>
					<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="gstnum" name="gstno"
							placeholder="GST No." class="form-control input-md"
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="aadharNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("aadharNo") %>
					<%}%> <%if(abc.equals("english")){%>Aadhar No.<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="aadharnum"
							name="aadharNo" maxlength="12" placeholder="12 Digit Aadhar No."
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="zipcode">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("zipcode") %>
					<%}%> <%if(abc.equals("english")){%>Zip Code
					<%}%>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="zipcode" name="zipcode"
						   maxlength=6 	placeholder="Zip code" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-md-11 text-center">
					<input type="button" id="save1" name="btn" style="font-size: 25"
						class="btn btn-large btn-success button-height-width"
						onclick="whosaleCustValdidation()" value="Submit"> <input
						type="button" id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-danger  button-height-width" type="reset"
						onclick="reset()" value="Cancel"> <!-- <input
						style="height: 65px; font-size: 25; width: 250" type="button"
						value="Credit Customer List" id="listBtn" class="btn btn-primary"
						onclick="Customerlist()" /> <input
						style="height: 65px; width: 180; font-size: 25" type="button"
						value="Edit" id="listBtn" class="btn btn-primary"
						onclick="editCustomer()" /> -->
				</div>
			</div>
		</form>	
</div>
</body>
<!-- -------------------- End Wholesale Customer --------------------- -->
<%@include file="commons/newFooter.jsp"%>
