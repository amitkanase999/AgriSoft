<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%boolean isHome = false;%>
<%@include file="commons/header.jsp"%>

<head>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/customerDetails.js"></script>
<script type="text/javascript">
  			function Back()
  			{
  				window.location = "customer_detail.jsp" ;
  			}
  		</script>
</head>

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
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-1">
	<form class="form-horizontal" method="post" action="" name="cstd1">
		<!-- Value of 'name' attribute is used in customerDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<label class="col-md-2 control-label" for="customerName">Customer
					Name</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span>
						<%
								CustomerDetailsDao cdd = new CustomerDetailsDao();
           						List cList =cdd.getAllCustomer();
							
							%>
						<input list="cust_drop" id="creditCustomer"
							onchange="getCustomerDetails()" class="form-control">
						<datalist id="cust_drop">

							<%
					           for(int i=0;i<cList.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList.get(i);
							%>

							<option data-value="<%=cust.getCustId()%>"><%=cust.getFirstName() %>
								<%=cust.getLastName() %>
							</option>
							<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="firstName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("firstName") %>
					<%}%> <%if(abc.equals("english")){%>First Name<%}%><sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="firstName" name="firstName" placeholder="First Name"
							class="form-control input-md" type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="middleName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("middleName") %>
					<%}%> <%if(abc.equals("english")){%>Middle Name <%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="middleName" name="middleName"
							placeholder="Middle Name" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="lastName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("lastName") %>
					<%}%> <%if(abc.equals("english")){%>Last Name<sup>*</sup><%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="lastName" name="lastName" placeholder="Last Name"
							class="form-control input-md" type="text">
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
					<%}%> <%if(abc.equals("english")){%>Contact No.<%}%><sup>*</sup>
				</label>
				<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="contactNo" name="contactNo" placeholder="Contact No."
							class="form-control input-md" type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="aadharNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("aadhar") %>
					<%}%> <%if(abc.equals("english")){%>Aadhar No.<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No. </span> <input id="aadharNo"
							name="aadharNo" placeholder="Aadhar No."
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
					<%}%> <%if(abc.equals("english")){%>Zip code<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-envelope"></i>
						</span> <input id="zipCode" name="zipCode" placeholder="Zip code"
							class="form-control input-md" type="text">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 col-md-offset-2 text-center">
					<button id="update" name="btn"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="editCustomerDetails()">
						<h4>
							<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("submit") %>
							<%}%>
							<%if(abc.equals("english")){%>Update<%}%>
						</h4>
					</button>
					<button
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()">
						<h4>
							<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("cancel") %>
							<%}%>
							<%if(abc.equals("english")){%>Cancel<%}%>
						</h4>
					</button>
					<input style="height: 65px; width: 180; font-size: 25"
						type="button" value="Back" id="listBtn" class="btn btn-primary"
						onclick="Back()" />
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
