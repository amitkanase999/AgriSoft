<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.dao.EmployeeDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.EmployeeDetailsBean"%>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Employee Payment</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<form method="post" action="">
	<div class="row form-group">
		<label class="col-md-2 control-label" for="employeename">Employee
			Name</label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-user"></i>
				</span> <select class="form-control input-md" id="customerName">
					<option value="">Select Employee</option>


					<%
								EmployeeDetailsDao edd = new EmployeeDetailsDao();
           						List eList =edd.getAllMainEmployee();
							
					%>

					<%
					           for(int i=0;i<eList.size();i++){
					        	   EmployeeDetailsBean emp =(EmployeeDetailsBean)eList.get(i);
					%>

					<option value="<%=emp.getEmpId()%>"><%=emp.getFirstName() %>
						<%=emp.getLastName() %>
					</option>
					<%
				      			}
				    %>
				</select>
			</div>
		</div>

		<label class="col-md-2 control-label" for="creditDebit">Credit/Debit</label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-hand-right"></i>
				</span> <select class="form-control input-md" id="creditDebit">
					<option>Select Type</option>
					<option>Credit</option>
					<option>Debit</option>
				</select>
			</div>
		</div>
	</div>

	<div class="row form-group">
		<label class="col-md-2 control-label" for="payAmount">Pay
			Amount</label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> Rs </span> <input id="payAmount"
					name="payAmount" placeholder="" class="form-control input-md"
					type="text">
			</div>
		</div>

		<label class="col-md-2 control-label" for="personName"> Person
			Name </label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-user"></i>
				</span> <input id="personName" name="personName" placeholder=""
					class="form-control input-md" type="text" required="">
			</div>
		</div>
	</div>

	<div class="row form-group">
		<label class="col-md-2 control-label" for="reason"> Reason</label>
		<div class="col-md-3">
			<div class="input-group">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-question-sign"></i>
				</span> <input id="reason" name="reason" placeholder=""
					class="form-control input-md" type="text" required="">
			</div>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-10 text-center">
			<button id="save" name="save" class="btn btn-large btn-success">
				Submit</button>
			<button class="btn btn-large btn-danger" type="reset">
				Cancel</button>
		</div>
	</div>
</form>
<%@include file="commons/newFooter.jsp"%>