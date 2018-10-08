<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/supplierDetails.js"></script>
<script type="text/javascript">
  			function Back()
  			{
  				window.location = "supplierdetails.jsp" ;
  			}
  		</script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">
			<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("supplierdetails") %>
			<%}%>
			<%if(abc.equals("english")){%>Supplier Details<%}%>
		</h2>
	</div>

</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-1">
	<form class="form-horizontal" method="post" action="" name="spld1">
		<!-- Value of 'name' attribute is used in  supplierDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<label class="col-md-2 control-label" for="supplier">Supplier
					Name</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
						<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

						<%
								SupplierDetailsDao sdd = new SupplierDetailsDao();
           						List sList =sdd.getAllSupplier();
							
							%>

						<input list="sup_drop" id="supplier"
							onchange="getSupplierDetails()" class="form-control">
						<datalist id="sup_drop">

							<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
							%>

							<option data-value="<%=sup.getSupId()%>"
								value="<%=sup.getDealerName() %>">
								<%
				      			}
				    		%>
							
						</datalist>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="dealerName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("dealerName") %>
					<%}%> <%if(abc.equals("english")){%>Supplier Name<%}%><sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="dealerName" name="dealerName"
							placeholder="Dealer Name" class="form-control input-md"
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="personName">
					Person Name</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input id="personName" name="personName"
							placeholder="Person Name" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="contactNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("contactNumber") %>
					<%}%> <%if(abc.equals("english")){%>Contact Number<%}%><sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-phone"></i>
						</span> <input id="contactNo" maxlength="10" name="contactNo"
							placeholder="Contact Number" class="form-control input-md "
							type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="landline">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("landlineNumber") %>
					<%}%> <%if(abc.equals("english")){%>LandLine Number<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-earphone"></i>
						</span> <input id="landline" name="landline" placeholder="LandLine No"
							class="form-control input-md " type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="emailId">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("email") %>
					<%}%> <%if(abc.equals("english")){%>E-mail Id<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-envelope"></i>
						</span> <input id="emailId" name="emailId" placeholder="Email ID"
							class="form-control input-md " type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="city">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("city") %>
					<%}%> <%if(abc.equals("english")){%>City<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-map-marker"></i>
						</span> <input id="city" name="city" placeholder="city"
							class="form-control input-md " type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="tinNo">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("tin") %>
					<%}%> <%if(abc.equals("english")){%>GST No.<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-font"></i>
						</span> <input id="tinNo" name="tinNo" placeholder="Tin No"
							class="form-control input-md " type="text">
					</div>
				</div>
				<label class="col-md-2 control-label" for="address">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("address") %>
					<%}%> <%if(abc.equals("english")){%>Address<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-map-marker"></i>
						</span> <input id="address" name="address" placeholder="Address"
							class="form-control input-md " type="text">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-9 col-md-offset-2 text-center">
					<button id="save" name="btn"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="updatesupplierDetail()">
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