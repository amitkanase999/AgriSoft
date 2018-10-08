<%@page import="com.Fertilizer.hibernate.ExpenditureDetailsBean"%>
<%@page import="com.Fertilizer.dao.ExpenditureDetailsDao"%>

<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/expenditureDetails.js"></script>
<script type="text/javascript">
function checkForDuplicateExpenseEntry(){
          			<%
          			ExpenditureDetailsDao dao2 = new ExpenditureDetailsDao();
          			List list = dao2.getAllExpenseName();
          			%>
          			
          			<%
          			int z = 0;
          			for(z=0;z<list.size();z++){
          				ExpenditureDetailsBean bean = (ExpenditureDetailsBean)list.get(z);
          			%>
          			var expenseName = "<%=bean.getExpenseName()%>";
          			var eName=document.getElementById("expenseName").value;
          			var expenseName1=expenseName.toLowerCase();
          			var eName1=eName.toLowerCase();
          			if(expenseName1 == eName1 ){
          				alert("Expense already exist...Duplicate Not allowed");
          				location.reload();
          				return false;
          			}
          			<%
          			}
          			%>
          			
          			}
</script>
</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Expenditure details</h2>
	</div>

</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-2">
	<form class="form-horizontal" method="post" action=""
		name="expenseDetails">
		<!-- Value of 'name' attribute is used in customerDetails.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-3 control-label" for="expenseName">Expenditure
					Name<sup>*</sup>
				</label>

				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
						<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
						<%
							ExpenditureDetailsDao cdd = new ExpenditureDetailsDao();
           						List cList =cdd.getAllExpenseNames();
							
							%>
						<input list="cat_drop" id="expenseName" class="form-control"
							onblur="checkForDuplicateExpenseEntry()"
							style="text-transform: lowercase;">
						<datalist id="cat_drop">
							<%
					           for(int i=0;i<cList.size();i++){
					        	   ExpenditureDetailsBean cat=(ExpenditureDetailsBean)cList.get(i);
							%>

							<option data-value="<%=cat.getPkExpenseDetailsId()%>"
								value="<%=cat.getExpenseName()%>">
								<%
				      			}
				    		%>
							
						</datalist>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-10 text-center">
					<input type="button" id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="addExpenseDetails()" value="Submit"> <input
						id="save" name="btn" style="font-size: 25"
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" onclick="reset()" value="Cancel">
				</div>
			</div>
		</fieldset>
	</form>
</div>
<%@include file="commons/newFooter.jsp"%>
