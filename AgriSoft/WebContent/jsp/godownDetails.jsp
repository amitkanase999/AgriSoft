<%@page import="com.Fertilizer.dao.GodownEntryDao"%>
<%@page import="com.Fertilizer.hibernate.GodownEntry"%>

<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/godownDetails.js"></script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">
			<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("godownDetails") %>
			<%}%>
			<%if(abc.equals("english")){%>Godown Details<%}%>
		</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action="" name="godown">
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-offset-2  col-md-2  control-label" for="godown">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("godownName") %>
					<%}%> <%if(abc.equals("english")){%>Godown Name<%}%><sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<% 
							GodownEntryDao dao1 = new GodownEntryDao();
           						List unitList =dao1.getAllGodown();
							
							%>
						<input list="unitName_drop1" id="godownName" class="form-control">
						<datalist id="unitName_drop1">
							<%
					           for(int i=0;i<unitList.size();i++){
					        	   GodownEntry bean1 =(GodownEntry)unitList.get(i);
							%>

							<option data-value="<%=bean1.getPkGodownId()%>"
								value="<%=bean1.getGodownName()%>">
								<%
				      			}
				    		%>
							
						</datalist>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<input type="button" id="btn" name="btn" style="font-size: 25"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="GoDetails()" value="Submit"> <input id="save"
						name="btn" style="font-size: 25"
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()" value="Cancel">
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
