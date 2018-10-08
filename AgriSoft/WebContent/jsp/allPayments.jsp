
<%boolean isHome = false;%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.EmployeeDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.EmployeeDetailsBean"%>
<%@include file="commons/header.jsp"%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/AgriSoft/staticContent/js/bootstrap-select.min.js"></script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/bootstrap.allPayment.min.css">
<script src="/AgriSoft/staticContent/js/jquery.allPayment.min.js"></script>
<script src="/AgriSoft/staticContent/js/bootstrap.allPayment.min.js"></script>
<script src="/AgriSoft/staticContent/js/cashbankbook.js"></script>

<!--   files for selectpicker -->
<script src="/AgriSoft/staticContent/js/bootstrap-select.js"></script>
<script src="/Fertilizer/staticContent/js/bootstrapForSelect.min.js"></script>
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/bootstrap-select.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/bootstrap-select.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/bootstrapForSelect.min.css">
</head>

<div class="container col-sm-offset-2" style="float: left">
	<h3 align="center">
		<b>CashBook</b>
	</h3>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home"><h4
					style="color: blue">Supplier Payment</h4></a></li>
		<li><a data-toggle="tab" href="#menu1"><h4
					style="color: blue">Customer Payment</h4></a></li>
		<li><a data-toggle="tab" href="#menu2"><h4
					style="color: blue">Employee Payment</h4></a></li>
		<li><a data-toggle="tab" href="#menu3"><h4
					style="color: blue">Add Other Account</h4></a></li>
	</ul>

	<div class="tab-content" style="float: left">
		<!-------- supplier Payment ---------->

		<div id="home" class="tab-pane fade in active">

			<form class="form-horizontal" method="post" action="" name="spmt">
				<div class="row">
					<div align="center">
						<h2 class="form-name style_heading">Supplier Payment</h2>
					</div>

					<div class="row">
						<div class="col-sm-offset-1 col-md-10">
							<hr style="border-top-color: #c1b1b1;">
						</div>
					</div>
				</div>
				
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
								onchange="bill.getAllBills();" class="form-control">
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


					<label class="col-md-2 control-label" for="bill_no"> Bill
						No </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> No </span> <select
								class="form-control input-md" id='billNo' name="billNo"
								onchange="bill.getTotalAmtByBills()">
							</select>
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="totalAmt"> Total
						Amount</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								readonly="readonly" id="totalAmount" name="totalAmount"
								class="form-control" placeholder="Total Amount">
						</div>
					</div>

					<label class="col-md-2 control-label" for="balanceAmt">
						Balance Amount</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								readonly="readonly" id="balanceAmount" name="balanceAmount"
								class="form-control" placeholder="Balance Amount">
						</div>
					</div>

				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="credit">Credit</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input id="credit"
								name="credit" placeholder="Credit Amount"
								class="form-control input-md" type="text">
						</div>
					</div>

					<label class="col-md-2 control-label" for="paymentMode">
						Payment Mode</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-usd"></i>
							</span> <select class="form-control" id="paymentMode">
								<option value="selected">-Select Type--</option>
								<option value="cash">Cash</option>
								<option value="cheque">Cheque</option>
								<option value="card">Card</option>
								<option value="neft">NEFT</option>
							</select>
						</div>
					</div>


		<script>
		$(document).ready(function(){
	  		 $("#paymentMode").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#cheque_no").show(); 
	           	
	           	$("#neft_acc_no").hide(); 
	           	$("#card_no").hide();
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#card_no").show(); 	
	          		
	          		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neft_acc_no").show(); 	
	           		
	           		$("#card_no").hide(); 
	        		$("#cheque_no").hide();
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#neft_acc_no").hide(); 
	            		$("#cheque_no").hide();
	            		$("#card_no").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	        		$("#card_no").hide(); 
	         }
	          
	       });
	   }).change();
		});	
		</script>
	</div>
				
				<div class="row form-group">
					<div id="cheque_no">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="chequeNum"
								id="chequeNum" placeholder="Cheque No." />
						</div>

						
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="nameOnCheck"
								id="nameOnCheck" placeholder="Name On check" />
						</div>
					</div>

					<div id="card_no" class="form-group">
						<div class="col-md-3 col-md-offset-7 first">
							<input class="form-control" type="text" name="cardNum"
								id="cardNum" placeholder="Card No." />
						</div>
					</div>

					<div id="neft_acc_no" class="form-group">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="accNum" id="accNum"
								placeholder="Account No." />
						</div>
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="bankName"
								id="bankName" placeholder="Name Of Bank" />
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="personname">
						Person Name </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input id="personname" name="personname"
								placeholder="Person Name" class="form-control input-md"
								type="text">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-md-10 text-center">

						<button id="save" name="btn1"
							class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
							onclick="supplierPayment()">
							<h4>Submit</h4>
						</button>
						<button id="" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-print  button-height-width"
							onclick="">
							<h4>Print</h4>
						</button>
						<button
							class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
							type="reset" onclick="reset()">
							<h4>Cancel</h4>
						</button>
					</div>
				</div>
			</form>
		</div>

		<!--------  Credit customer Payment ---------->

		<div id="menu1" class="tab-pane fade">
			<form class="form-horizontal" method="post" action="" name="cust">
				<div class="row">
					<div align="center">
						<h2 class="form-name style_heading">Credit Customer Payment</h2>
					</div>

					<div class="row">
						<div class="col-sm-offset-1 col-md-10">
							<hr style="border-top-color: #c1b1b1;">
						</div>
					</div>
				</div>
			
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
								onchange="custBill.getBillByCustomer()" class="form-control">
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

					<label class="col-md-2 control-label" for="bill_no"> Bill
						No </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> No </span> <select
								class="form-control input-md" id='billNo1' name="billNo"
								onchange="custBill.getTotalAmountByBill()">
							</select>
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="totalAmt"> Total
						Amount</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								readonly="readonly" id="totalAmount1" name="totalAmount"
								class="form-control" placeholder="Total Amount">
						</div>
					</div>

					<label class="col-md-2 control-label" for="balanceAmt">
						Balance Amount</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input
								readonly="readonly" id="balanceAmount1" name="balanceAmount"
								class="form-control" placeholder="Balance Amount">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="credit">Credit</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input id="credit1"
								name="credit1" placeholder="Credit Amount"
								class="form-control input-md" type="text">
						</div>
					</div>

					<label class="col-md-2 control-label" for="paymentMode">
						Payment Mode</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-usd"></i>
							</span> <select class="form-control" id="paymentMode1">
								<option value="selected1">-Select Type--</option>
								<option value="cash1">Cash</option>
								<option value="cheque1">Cheque</option>
								<option value="card1">Card</option>
								<option value="neft1">NEFT</option>
							</select>
						</div>
					</div>
	<script>
		$(document).ready(function(){
	  		 $("#paymentMode1").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque1"){
	           	
	           	$("#cheque_no1").show(); 
	           	
	           	$("#neft_acc_no1").hide(); 
	           	$("#card_no1").hide();
	           	}
	          	 else if($(this).attr("value")=="card1"){
	           	
	          		$("#card_no1").show(); 	
	          		
	          		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	           }
	          	 else if($(this).attr("value")=="neft1"){
	                	
	           		$("#neft_acc_no1").show(); 	
	           		
	           		$("#card_no1").hide(); 
	        		$("#cheque_no1").hide();
	            }
	          	 else if($(this).attr("value")=="cash1"){
	             	
	            		$("#neft_acc_no1").hide(); 
	            		$("#cheque_no1").hide();
	            		$("#card_no1").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected1"){
	             	
	        		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	        		$("#card_no1").hide(); 
	         }
	          
	       });
	   }).change();
		});	
</script>
</div>

				<div class="row form-group">
					<div id="cheque_no1">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="chequeNum"
								id="chequeNum1" placeholder="Cheque No." />
						</div>
					
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="nameOnCheck"
								id="nameOnCheck1" placeholder="Name On check" />
						</div>
					</div>

					<div id="card_no1" class="form-group">
						<div class="col-md-3 col-md-offset-7 first">
							<input class="form-control" type="text" name="cardNum"
								id="cardNum1" placeholder="Card No." />
						</div>
					</div>

					<div id="neft_acc_no1" class="form-group">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="accNum"
								id="accNum1" placeholder="Account No." />
						</div>
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="bankName"
								id="bankName1" placeholder="Name Of Bank" />
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="personname">
						Person Name </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input id="personname1" name="personname"
								placeholder="Person Name" class="form-control input-md"
								type="text">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-md-10 text-center">
						<button id="save" name="btn2"
							class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
							onclick="customerPayment()">
							<h4>Submit</h4>
						</button>
						<button id="" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-print  button-height-width"
							onclick="">
							<h4>Print</h4>
						</button>
						<button
							class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
							type="reset" onclick="reset()">
							<h4>Cancel</h4>
						</button>
					</div>
				</div>
			</form>
		</div>

		<div id="menu2" class="tab-pane fade">

			<form class="form-horizontal" method="post" action="" name="emp">
				<div class="row">
					<div align="center">
						<h2 class="form-name style_heading">Employee Payment</h2>
					</div>

					<div class="row">
						<div class="col-sm-offset-1 col-md-10">
							<hr style="border-top-color: #c1b1b1;">
						</div>
					</div>
				</div>
				
				<div class="row form-group">
					<label class="col-md-2 control-label" for="employeename">Employee
						Name</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span>

							<%
								EmployeeDetailsDao eedd = new EmployeeDetailsDao();
           						List mList =eedd.getAllMainEmployee();
							
							%>
							<input list="emp_drop" id="employee" class="form-control">
							<datalist id="emp_drop">

								<%
					           for(int i=0;i<mList.size();i++){
					        	   EmployeeDetailsBean detailsBean =(EmployeeDetailsBean)mList.get(i);
							%>

								<option data-value="<%=detailsBean.getEmpId()%>"><%=detailsBean.getFirstName()%>
									<%=detailsBean.getLastName()%></option>
								<%
				      			}
				    		%>
							</datalist>
						</div>
					</div>

					<label class="col-md-2 control-label" for="credit">Credit</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> Rs </span> <input id="credit2"
								name="credit" placeholder="Credit Amount"
								class="form-control input-md" type="text">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="personName">
						Person Name </label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input id="personName2" name="personName"
								placeholder="Person Name" class="form-control input-md"
								type="text">
						</div>
					</div>

					<label class="col-md-2 control-label" for="reason"> Reason</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-question-sign"></i>
							</span> <input id="reason2" name="reason" placeholder="Reason"
								class="form-control input-md" type="text">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="paymentMode">
						Payment Mode</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-usd"></i>
							</span> <select class="form-control" id="paymentMode2">
								<option value="selected2">-Select Type--</option>
								<option value="cash2">Cash</option>
								<option value="cheque2">Cheque</option>
								<option value="card2">Card</option>
								<option value="neft2">NEFT</option>
							</select>
						</div>
					</div>
	<script>
		$(document).ready(function(){
	  		 $("#paymentMode2").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque2"){
	           	
	           	$("#cheque_no2").show(); 
	           	
	           	$("#neft_acc_no2").hide(); 
	           	$("#card_no2").hide();
	           	}
	          	 else if($(this).attr("value")=="card2"){
	           	
	          		$("#card_no2").show(); 	
	          		
	          		$("#neft_acc_no2").hide(); 
	        		$("#cheque_no2").hide();
	           }
	          	 else if($(this).attr("value")=="neft2"){
	                	
	           		$("#neft_acc_no2").show(); 	
	           		
	           		$("#card_no2").hide(); 
	        		$("#cheque_no2").hide();
	            }
	          	 else if($(this).attr("value")=="cash2"){
	             	
	            		$("#neft_acc_no2").hide(); 
	            		$("#cheque_no2").hide();
	            		$("#card_no2").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected2"){
	             	
	        		$("#neft_acc_no2").hide(); 
	        		$("#cheque_no2").hide();
	        		$("#card_no2").hide(); 
	         }
	          
	       });
	   }).change();
		});	
 </script>
</div>

				<div class="row form-group">
					<div id="cheque_no2">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="chequeNum"
								id="chequeNum2" placeholder="Cheque No." />
						</div>

						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="nameOnCheck"
								id="nameOnCheck2" placeholder="Name On check" />
						</div>
					</div>

					<div id="card_no2" class="form-group">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="cardNum"
								id="cardNum2" placeholder="Card No." />
						</div>
					</div>

					<div id="neft_acc_no2" class="form-group">
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="accNum"
								id="accNum2" placeholder="Account No." />
						</div>
						<div class="col-md-3 col-md-offset-2 first">
							<input class="form-control" type="text" name="bankName"
								id="bankName2" placeholder="Name Of Bank" />
						</div>
					</div>
				</div>

		<div class="form-group row">
					<div class="col-md-10 text-center">
						<button id="save" name="btn3"
							class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
							onclick="emplyeePayment()">
							<h4>Submit</h4>
						</button>
						<button id="" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-print  button-height-width"
							onclick="">
							<h4>Print</h4>
						</button>
						<button
							class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
							type="reset" onclick="reset()">
							<h4>Cancel</h4>
						</button>
					</div>
				</div>
			</form>
		</div>

		<div id="menu3" class="tab-pane fade">
			<form method="post" action="">
				<div class="row">
					<div align="center">
						<h2 class="form-name style_heading">Expenditure Payment</h2>
					</div>

					<div class="row">
						<div class="col-sm-offset-1 col-md-10">
							<hr style="border-top-color: #c1b1b1;">
						</div>
					</div>
				</div>
			
				<div class="row form-group">
					<label class="col-md-2 control-label" for="accountName">Account
						Name</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input id="accountName" name="accountName"
								placeholder="Account Name" class="form-control input-md"
								type="text">
						</div>
					</div>
				
					<label class="col-md-2 control-label" for="accountNumber">Account
						Number</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> No. </span> <input
								id="contactNumber" name="contactNumber"
								placeholder="Account Number" class="form-control input-md"
								type="number">
						</div>
					</div>
				</div>

				<div class="row form-group">
					<label class="col-md-2 control-label" for="accountName">Account
						Type</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <select class="form-control" name="accType" id="accType">
								<option value="selected">--Select Type--</option>
								<option value="dP">D Primary</option>
								<option value="bA">Bank Accounts</option>
								<option value="bO">Bank OCC A/c</option>
								<option value="bOA">Bank OD A/c</option>
								<option value="bD">Branch/Divisions</option>
								<option value="cA">Capital Account</option>
								<option value="cI">Cash-in-hand</option>
								<option value="cAS">Current Assets</option>
								<option value="cL">Current Liabilities</option>
								<option value="dA">Deposits(Assets)</option>
								<option value="dE">Direct Expenses</option>
								<option value="dI">Direct Incomes</option>
								<option value="dT">Duties & Taxes</option>
								<option value="eD">Expenses(Direct)</option>
								<option value="eI">Expenses (Indirect)</option>
								<option value="fA">Fixed Assets</option>
								<option value="iD">Income (Direct)</option>
								<option value="iID">Income (Indirect)</option>
								<option value="iE">Indirect Expenses</option>
								<option value="iI">Indirect Incomes</option>
								<option value="i">Interest</option>
								<option value="iOF">Interest On FD</option>
								<option value="i">Investments</option>
								<option value="lA">Loans & Advances (Assets)</option>
							</select>
						</div>
					</div>
					
					<label class="col-md-2 control-label" for="contactNumber">Contact
						Number</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-earphone"></i>
							</span> <input id="contactNumber" name="contactNumber"
								placeholder="Contact Number" class="form-control input-md"
								type="number">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-md-10 text-center">
						<button id="save" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
							onclick="">
							<h4>Submit</h4>
						</button>
						<button id="" name="btn"
							class="btn btn-large btn-success glyphicon glyphicon-print  button-height-width"
							onclick="">
							<h4>Print</h4>
						</button>
						<button
							class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
							type="reset" onclick="reset()">
							<h4>Cancel</h4>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="commons/newFooter.jsp"%>
