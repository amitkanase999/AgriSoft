
<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>
<head>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.11.1.min.js"></script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">
			<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("billing") %>
			<%}%>
			<%if(abc.equals("english")){%>Billing<%}%>
		</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2 ">
	<fieldset>
		<div class="row form-group ">
			<input type="button"
				style="height: 250px; width: 258px; font-size: 36px"
				value="<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("fertilizer") %> <%}%> <%if(abc.equals("english")){%>Fertilizer Billing<%}%>"
				onclick="goToFertilizer()"> <input class="col-md-offset-1"
				type="button" style="height: 250px; width: 258px; font-size: 36px"
				value="<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("seedAndPesticide") %> <%}%> <%if(abc.equals("english")){%>Seed Billing<%}%>"
				onclick="goToSeed()"> <input class="col-md-offset-1"
				type="button" style="height: 250px; width: 265px; font-size: 36px"
				value="<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("seedAndPesticide") %> <%}%> <%if(abc.equals("english")){%>Pesticide Billing<%}%>"
				onclick="goToPesticide()">
		</div>

		<script type="text/javascript">
					function goToFertilizer(){
					    window.location = '/AgriSoft/jsp/allBilling.jsp';
					}
					function goToSeed(){
					    window.location = '/AgriSoft/jsp/seedAndPestiBill.jsp';
					} 
					function goToPesticide(){
					    window.location = '/AgriSoft/jsp/pesticideBill.jsp';
					} 
		</script>

		<div class="row form-group"></div>
	</fieldset>
</div>
<%@include file="commons/newFooter.jsp"%>
