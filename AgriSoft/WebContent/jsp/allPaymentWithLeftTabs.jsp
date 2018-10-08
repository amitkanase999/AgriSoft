<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.EmployeeDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.EmployeeDetailsBean"%>
<%@page import="com.Fertilizer.dao.ExpenditureDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.ExpenditureDetailsBean"%>	
<%@page import="com.Fertilizer.hibernate.CreditLoanBean"%>			
<%@page import="com.Fertilizer.dao.CreditLoanDao"%>
<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>

<link rel="stylesheet" href="/AgriSoft/staticContent/css/tabDemo.css">
<script src="/AgriSoft/staticContent/js/cashbankbook.js"></script>
<script src="/AgriSoft/staticContent/js/creditnotegenartion.js"></script>
<script src="/AgriSoft/staticContent/js/CreditLoan.js"></script>
<style>
.leftnav{
width: 195px;
}

}


</style>

<body onload="generateLonNo()">
<div class="row" style="min-height: 300px;">
	<div class="col-md-12">
		<h3>Left Tabs</h3>
		<hr />
		<div class="col-md-2">
			<!-- required for floating -->
			<!-- Nav tabs -->
			<ul class="nav nav-tabs tabs-left" style="text-align: center">
				<li class="active"><a href="#home" class="leftnav" data-toggle="tab">Supplier
						Payment</a></li>
				<!-- <li><a href="#profile" data-toggle="tab">Customer Payment</a></li> -->
				<li><a href="#messages" class="leftnav"  data-toggle="tab">Employee Payment</a></li>
				<li><a href="#settings" class="leftnav"  data-toggle="tab">Expenditure
						Payment</a></li>
				<li><a href="#creditnotegeneration" class="leftnav"  data-toggle="tab">Credit
						Note Generation</a></li>
				<li><a href="#loanpayment" class="leftnav"  data-toggle="tab">Customer Payment</a></li>
			</ul>
		</div>
		
		<div class="col-xs-9">
			<!-- Tab panes -->
			<div class="tab-content" style="margin-top: -41px;">
				<div class="tab-pane fade in active" id="home">
					<form method="post" action="" name="spmt">
						<div class="row">
							<div align="center">
								<h2 class="form-name style_heading">Supplier Payment</h2>
							</div>

							<div class="row">
								<div class="col-sm-offset-1 col-mxl-12">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
						</div>
						
						<div class="row form-group">
							<label class="col-md-3 control-label" for="supplier">Supplier
								Name<sup>*</sup>
							</label>
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
										onchange="bill.getTotalAmtByBills()" class="form-control">
									<datalist id="sup_drop">

							<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
							%>

										<option data-value="<%=sup.getSupId()%>"
											value="<%=sup.getSupId()%>,<%=sup.getDealerName()%>">
							<%
				      			}
				    		%>
										
									</datalist>
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-2 col-md-offset-6 control-label"
								for="totalAmt"> Total Amount</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input
										readonly="readonly" id="totalAmount" name="totalAmount"
										class="form-control" placeholder="Total Amount">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="personname">Accountant
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="personname" name="personname"
										placeholder="Accountant Name" class="form-control input-md"
										type="text">
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
							<label class="col-md-3 control-label" for="paymentMode">
								Payment Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentMode">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
										<option value="creditnote">Credit Note</option>
										<option value="debitnote">Debit Note</option>
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
	           	$("#creditnote").hide();
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#card_no").show(); 	
	          		
	          		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	        		$("#creditnote").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neft_acc_no").show(); 	
	           		
	           		$("#card_no").hide(); 
	        		$("#cheque_no").hide();
	        		$("#creditnote").hide();
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#neft_acc_no").hide(); 
	            		$("#cheque_no").hide();
	            		$("#card_no").hide(); 
	            		$("#creditnote").hide();
	             }
	          	 else if($(this).attr("value")=="debitnote"){
		             	
	            		$("#neft_acc_no").hide(); 
	            		$("#cheque_no").hide();
	            		$("#card_no").hide(); 
	            		$("#creditnote").hide();
	             }
	         	 else if($(this).attr("value")=="creditnote"){
	         			$("#creditnote").show();
	            		$("#neft_acc_no").hide();  
	            		$("#cheque_no").hide();
	            		$("#card_no").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	        		$("#card_no").hide(); 
	        		$("#creditnote").hide();
	         }
	          
	       });
	   }).change();
		});	
		</script>
						<label class="col-md-2 control-label" for="credit">Enter
								Amount :</label>
							<div class="col-md-2">
								<div class="input-group">
								</div>
							</div>

							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input id="supPay"
										name="supPay" class="form-control" placeholder="Enter Amount">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentMode">
								Payment Type<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"><i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentType">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="credit">Credit</option>
										<option value="debit">Debit</option>

									</select>
								</div>
							</div>
						</div>
						
						<div class="row form-group">
							<label class="col-md-3 control-label" for="receipt_no">
								Receipt No<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> No </span> <input
										id="receiptno" name="receiptno" class="form-control"
										placeholder="Enter Receipt no">
								</div>
							</div>
						</div>


						<div class="row form-group">
							<div id="cheque_no">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeNum"
										id="chequeNum" placeholder="Cheque No." />
								</div>
								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheck"
										id="nameOnCheck" placeholder="Name On check" />
								</div>
							</div>

							<div id="card_no" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum"
										id="cardNum" placeholder="Card No." />
								</div>
							</div>

							<div id="neft_acc_no" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum"
										id="accNum" placeholder="Account No." />
								</div>
								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName"
										id="bankName" placeholder="Name Of Bank" />
								</div>
							</div>


							<div id="creditnote" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="creditnotenumber"
										id="creditnotenumber" placeholder="Credit Note Number" />
								</div>
								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cramount"
										id="cramount" placeholder="Amount" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="btn1" name="btn1" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="supplierPayment(); return false;" value="Submit">
								<input type="reset" id="btn1" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn1" value="Cancel">
							</div>
						</div>
					</form>
				</div>

				<!---------Customer Payment --------->

				<div class="tab-pane" id="profile">
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
							<label class="col-md-3 control-label" for="customerName">Customer
								Name<sup>*</sup>
							</label>
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
										class="form-control">
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

							<div id="productcat">
								<label class="col-md-2 control-label" for="fk_cat_id0">
									<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("productCat") %>
									<%}%> <%if(abc.equals("english")){%>Product Category<%}%><sup>*</sup>
								</label>
								<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="	glyphicon glyphicon-hand-right"></i>
										</span>

										<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
										<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
										<%
							   CategoryDetailsDao cdd0 = new CategoryDetailsDao();
           						List cList0 =cdd0.getAllMainCat();
							
							%>
										<input list="cat_drop0" id="fk_cat_id0" class="form-control"
											onchange="getBillByCustomer()">
										<datalist id="cat_drop0">
							<%
					           for(int i=0;i<cList0.size();i++){
					        	   CategoryDetailsBean cat0=(CategoryDetailsBean)cList0.get(i);
							%>

											<option data-value="<%=cat0.getCatId()%>"
												value="<%=cat0.getCategoryName()%>">
							<%
				      			}
				    		%>
											
										</datalist>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row form-group" id="billnumber">
							<label class="col-md-3 control-label" for="bill_no"> Bill
								No<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> No </span> <select
										class="form-control input-md" id='billNo1' name="billNo"
										onchange="getTotalAmountByBill()">
									</select>
								</div>
							</div>

							<label class="col-md-2  control-label" for="totalAmt">
								Total Amount</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input
										readonly="readonly" id="totalAmount1" name="totalAmount"
										class="form-control" placeholder="Total Amount">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<div id="accntname">
								<label class="col-md-3 control-label" for="personname">Accountant
									Name <sup>*</sup>
								</label>
								<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="glyphicon glyphicon-user"></i>
										</span> <input id="personname1" name="personname"
											placeholder="Accountant Name" class="form-control input-md"
											type="text">
									</div>
								</div>
							</div>

							<div id="balnaceamt">
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
						</div>
						
						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentMode">
								Payment Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentMode1">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
										<option value="creditnote">Credit Note</option>
										<option value="debitnote">Debit Note</option>
										<option value="creditloan">Credit Loan</option>
									</select>
								</div>
							</div>

	<script>
		$(document).ready(function(){
	  		 $("#paymentMode1").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#cheque_no1").show(); 
	           	$("#billnumber").show(); 
	           	$("#productcat").show();
	           	$("#balnaceamt").show();	
	        	$("#accntname").show();
	           	
	           	$("#neft_acc_no1").hide(); 
	           	$("#card_no1").hide();
	           	$("#loanno").hide();
	           	$("#interestrate").hide();
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#card_no1").show(); 	
	          		$("#billnumber").show();
	          		$("#productcat").show();
	          		$("#balnaceamt").show();
	          		$("#accntname").show();
	          		
	          		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	        		$("#loanno").hide();
	        		$("#interestrate").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neft_acc_no1").show(); 	
	           		$("#billnumber").show();
	           		$("#productcat").show();
	           		$("#balnaceamt").show();
	           		$("#accntname").show();
	           		
	           		$("#card_no1").hide(); 
	        		$("#cheque_no1").hide();
	        		$("#loanno").hide();
	        		$("#interestrate").hide();
	            }
	          	 else if($(this).attr("value")=="creditnote"){
	          			$("#billnumber").show();
	          			$("#productcat").show();
	          			$("#balnaceamt").show();
	          			$("#accntname").show();
	          			
	            		$("#neft_acc_no1").hide(); 
	            		$("#cheque_no1").hide();
	            		$("#card_no1").hide();
	            		$("#loanno").hide();
	            		$("#interestrate").hide();
	             }
	          	 else if($(this).attr("value")=="cash"){
	          			
	          		 	$("#billnumber").show();
	          		 	$("#productcat").show();
	          			$("#balnaceamt").show();
	          			$("#accntname").show();
	          			
	            		$("#neft_acc_no1").hide(); 
	            		$("#cheque_no1").hide();
	            		$("#card_no1").hide();
	            		$("#loanno").hide();
	            		$("#interestrate").hide();
	             }
	          	 else if($(this).attr("value")=="debitnote"){
		             	
	          			$("#billnumber").show();
	          			$("#productcat").show();
	          			$("#balnaceamt").show();
	          			$("#accntname").show();
	          			
	            		$("#neft_acc_no1").hide(); 
	            		$("#cheque_no1").hide();
	            		$("#card_no1").hide(); 
	            		$("#loanno").hide();
	            		$("#interestrate").hide();
	          	 }
	           	
	          	else if($(this).attr("value")=="selected"){
	             	
	          		$("#billnumber").show();
	          		$("#productcat").show();
	          		$("#balnaceamt").show();
	          		$("#accntname").show();
	          		
	        		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	        		$("#card_no1").hide(); 
	        		$("#loanno").hide();
	        		$("#interestrate").hide();
	         	}
      			else if($(this).attr("value")=="creditloan"){
	             	
      				$("#loanno").show();
      				$("#interestrate").show();
      				$("#balnaceamt").show();
      				$("#billnumber").hide();
	        		$("#neft_acc_no1").hide(); 	
	        		$("#cheque_no1").hide();
	        		$("#card_no1").hide();
	        		$("#productcat").hide();
	        		$("#balnaceamt").hide();
	        		$("#accntname").hide();	         
      			}
	          
	       });
	   }).change();
		});	
		</script>
	</div>
						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentMode">
								Payment Type<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentType1">
										<option value="selected">-Select Type--</option>
										<option value="credit">Credit</option>
										</select>
								</div>
							</div>


							<div class="col-md-5">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input id="custPay"
										name="custPay" class="form-control" placeholder="Enter Amount">
								</div>
							</div>
						</div>
						
						<div class="row form-group">
							<div id="cheque_no1">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeNum"
										id="chequeNum1" placeholder="Cheque No." />
								</div>

								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheck"
										id="nameOnCheck1" placeholder="Name On check" />
								</div>
							</div>


							<div id="card_no1" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum"
										id="cardNum1" placeholder="Card No." />
								</div>

							</div>

							<div id="neft_acc_no1" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum"
										id="accNum1" placeholder="Account No." />
								</div>
								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName"
										id="bankName1" placeholder="Name Of Bank" />
								</div>
							</div>

							<div id="loanno" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="loanno"
										id="loanno" placeholder="Loan No." />
								</div>


								<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon"> Date </span> <input
											type="date" id="loandate" name="Loan date"
											class="form-control" placeholder="">
									</div>
								</div>
							</div>

							<div id="interestrate" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="interestrate"
										id="interestrate" placeholder="Interest Rate" />
								</div>
							</div>
						</div>


						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="btn2" name="btn2" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="customerPaymentValidation(); return false;CreditloanDetails();"
									value="Submit"> <input type="reset" id="btn2"
									style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn2" value="Cancel">
							</div>
						</div>
					</form>
				</div>

				<!------------ Employee Payment ---------->

				<div class="tab-pane" id="messages">
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
							<label class="col-md-3 control-label" for="employeename">Employee
								Name<sup>*</sup>
							</label>
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

							<label class="col-md-2 control-label" for="personName">Accountant
								Name <sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="personName2" name="personName"
										placeholder="Accountant Name" class="form-control input-md"
										type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="reason2">Reason<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="reason2" name="reason2" placeholder="Reason"
										class="form-control input-md" type="text">
								</div>
							</div>

							<label class="col-md-2 control-label" for="paymentMode">
								Payment Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentMode2">
										<option value="selected2">-Select Type--</option>
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
									</select>
								</div>
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

						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentMode">
								Payment Type<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
class="fa fa-inr"></i>
									</span> <select class="form-control" id="paymentType2">
										<option value="selected">-Select Type--</option>
										<option value="credit">Credit</option>
										<option value="debit">Debit</option>
									</select>
								</div>
							</div>


							<div class="col-md-5">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input id="empPay"
										name="empPay" class="form-control" placeholder="Enter Amount">
								</div>
							</div>
						</div>


						<div class="row form-group">

							<div id="cheque_no2">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeNum"
										id="chequeNum2" placeholder="Cheque No." />
								</div>

								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheck"
										id="nameOnCheck2" placeholder="Name On check" />
								</div>
							</div>

							<div id="card_no2" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum"
										id="cardNum2" placeholder="Card No." />
								</div>

							</div>

							<div id="neft_acc_no2" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum"
										id="accNum2" placeholder="Account No." />
								</div>
								
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName"
										id="bankName2" placeholder="Name Of Bank" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="btn3" name="btn3" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="EmployeeValidation(); return false;" value="Submit">
								<input type="reset" id="btn2" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn2" value="Cancel">
							</div>
						</div>
					</form>
				</div> 

				<!------------------   Expenditure Payment ------------>

				<div class="tab-pane" id="settings">
					<form method="post" action="" name="exp">
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
							<label class="col-md-3 control-label" for="expenditureName">Expenditure
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span>

									<!-- Following code is to get expense name from "expenditure_details" table of "fertilizer" DB -->
									<!-- getAllExpenseName() is implemented in  SupplierDetailsDao with return type List-->

									<%
							ExpenditureDetailsDao exdd = new ExpenditureDetailsDao();
           						List exList =exdd.getAllExpenseName();
							
							%>

									<input list="exp_drop" id="expenseName" class="form-control">
									<datalist id="exp_drop">

										<%
					           for(int i=0;i<exList.size();i++){
					        	   ExpenditureDetailsBean expbean =(ExpenditureDetailsBean)exList.get(i);
							%>

										<option data-value="<%=expbean.getPkExpenseDetailsId()%>"
											value="<%=expbean.getExpenseName() %>">
											<%
				      			}
				    		%>
										
									</datalist>
								</div>
							</div>
							<label class="col-md-3 control-label" for="serviceProvider">Service
								Provider<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="serviceProvider" name="serviceProvider"
										placeholder="Service Provider" class="form-control input-md"
										type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="contactNumber">Contact
								Number<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-earphone"></i>
									</span> <input id="contactNumber" name="contactNumber"
										placeholder="Contact Number" class="form-control input-md"
										type="text">
								</div>
							</div>

							<label class="col-md-3 control-label" for="expCredit">Credit
								Amount<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input
										id="expCredit" name="expCredit" placeholder="Credit Amount"
										class="form-control input-md" type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="personName">Accountant
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="accountantName" name="personName"
										placeholder="Accountant Name" class="form-control input-md"
										type="text">
								</div>
							</div>

							<label class="col-md-3 control-label" for="expDebit">Debit
								Amount<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> Rs </span> <input
										id="expDebit" name="expDebit" placeholder="Debit Amount"
										class="form-control input-md" type="text">
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="save" name="btn4" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="expensePaymentValidation(); return false;"
									value="Submit"> <input type="reset" id="btn2"
									style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn4" value="Cancel">
							</div>
						</div>
					</form>
				</div>

				<!------------------   Credit Note Generation Payment ------------>

				<div class="tab-pane" id="creditnotegeneration">
					<form method="post" action="" name="creditnote">
						<div class="row">
							<div align="center">
								<h2 class="form-name style_heading">Credit Note Generation</h2>
							</div>

							<div class="row">
								<div class="col-sm-offset-1 col-md-10">
									<hr style="border-top-color: #c1b1b1;">
								</div>
							</div>
						</div>
				
						<div class="row form-group">
							<label class="col-md-3 control-label" for="partyname">Party
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span> <input id="partyname" name="partyname"
										placeholder="Party Name" class="form-control input-md"
										type="text">
								</div>
							</div>

							<label class="col-md-3 control-label" for="particular">Particular<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input id="particular" name="particular"
										placeholder="Particular" class="form-control input-md"
										type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="amount">Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-earphone"></i>
									</span> <input id="amount" name="amount" placeholder="Amount"
										class="form-control input-md" type="text">
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="save" name="btn4" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="creditnotegeneration(); return false;" value="Print">
								<input type="reset" id="btn2" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn4" onclick="location.reload()" value="Cancel">
							</div>
						</div>
					</form>
				</div>

				<!------------------   Loan Payment ------------>

				<div class="tab-pane" id="loanpayment">
					<!--  <form method="post" action="" name="loanpayment"> -->
						<div class="row">
							<div align="center">
								<h2 class="form-name style_heading">Credit Loan Payment</h2>
							</div>
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#creditloan"><h4 style="color: black">Credit Loan</h4></a></li>
								<!-- <li><a data-toggle="tab" href="#loanpayments"><h4 style="color: black">Loan Payment</h4></a></li> -->
								<li><a data-toggle="tab" href="#customerloanpayment"><h4 style="color: black">Customer Loan Payment</h4></a></li>
							</ul>
							<br> <br>
						</div>
					<!-- </form> -->
				
			 <div class="tab-content">	
				<div  id="creditloan"  class="tab-pane active">
					<form method="post" action="" name="creditloan" >
						<div class="row form-group">
							<label class="col-md-3 control-label" for="partyname">Loan
								No<sup>*</sup>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span> <input id="loanno1" name="loanno" placeholder=""
										class="form-control input-md" type="text" readonly="true">
								</div>
							</div>
						</div>
					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="particular">Customer
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> 
												<%
									//CreditLoanDao cld1=new CreditLoanDao();
									
									CustomerDetailsDao cld1=new CustomerDetailsDao();
           							List cuList1 =cld1.getAllCustomer();
							
								%>

									<input list="customer_drop1" id="Custname" class="form-control">
									<datalist id="customer_drop1">

									<%
					           			  for(int i=0;i<cuList1.size();i++){
					           				CustomerDetailsBean bean1=(CustomerDetailsBean)cuList1.get(i);					           				  
									%>

									<option data-value="<%=bean1.getCustId()%>"value="<%=bean1.getFirstName()%> <%=bean1.getLastName()%>">
									
									<%
				      						}
				    				%>
								</datalist>
								</div>
							</div>

							<label class="col-md-2 control-label" for="customername">Date<sup>*</sup></label>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-calendar"></i>
									</span> <input id="date" name="date" placeholder=""
										Name" class="form-control input-md" type="date">
								</div>
							</div>
						</div>
					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="amount">Type<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> </span> <input id="type"
										name="type" value="Credit" class="form-control input-md"
										type="text" readonly>
								</div>
							</div>

						<!-- 	<label class="col-md-2 control-label" for="partyname">Interest
								Rate<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> % </span> <input
										id="intrrate" name="interestrate"
										placeholder="Interest Rate" class="form-control input-md"
										onkeyup="onlyNumbersWithDot(this)" type="text">
								</div>
							</div> -->
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentmode">Payment
								Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon fa fa-inr"> </span> <select
										class="form-control" id="loanpaymentMode">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>

									</select>
								</div>
							</div>

	<script>
		$(document).ready(function(){
	  		 $("#loanpaymentMode").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#chequeno").show(); 
	           	
	           	$("#neftaccno").hide(); 
	           	$("#cardno").hide();
	           
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#cardno").show(); 	
	          		
	          		$("#neftaccno").hide(); 
	        		$("#chequeno").hide();
	        		$("#creditnote").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neftaccno").show(); 	
	           		
	           		$("#cardno").hide(); 
	        		$("#chequeno").hide();
	        	
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#neftaccno").hide(); 
	            		$("#chequeno").hide();
	            		$("#cardno").hide(); 
	            	
	             }
	          	 else if($(this).attr("value")=="debitnote"){
		             	
	            		$("#neftaccno").hide(); 
	            		$("#chequeno").hide();
	            		$("#cardno").hide(); 

	             }
	         	 
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#neftaccno").hide(); 
	        		$("#chequeno").hide();
	        		$("#cardno").hide(); 
	        	
	         }
	          
	       });
	   }).change();
		});	
</script>

							<label class="col-md-2 control-label" for="partyname">Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon fa fa-inr"> 
										
									</span> <input id="loanamount" name="loanamount"
										placeholder="Enter Loan Amount"
										onkeyup="onlyNumbersWithDot(this)"
										class="form-control input-md" type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">

							<div id="chequeno">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeno2"
										id="chequeno2" placeholder="Cheque No." />
								</div>

								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheque2"
										id="nameOnCheque2" onkeyup="onlyletters(this)"
										placeholder="Name On check" />
								</div>
							</div>
						
							<div id="cardno" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum3"
										id="cardNum3" onkeyup="onlyNumbers(this)"
										placeholder="Card No." />
								</div>

							</div>

							<div id="neftaccno" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum3"
										id="accountNum" placeholder="Account No." />
								</div>
							
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName3"
										id="nameofbank" onkeyup="onlyletters(this)"
										placeholder="Name Of Bank" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="save" name="btn4" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="creditLoanDetailsValidation()" value="Submit"> <input
									type="reset" id="btn2" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn4" onclick="location.reload()" value="Cancel">
							</div>
						</div>
					</form>
				</div>
				
				
				<!-- customer loan payment -->
				
				<div  id="customerloanpayment"  class="tab-pane">
					<form method="post" action="" name="loanpayment11" >
					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="custname">Customer
								Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> 
												<%
									//CreditLoanDao cld1=new CreditLoanDao();
									
									CustomerDetailsDao cld2=new CustomerDetailsDao();
           							List cuList2 =cld2.getAllCustomer();
							
								%>

									<input list="customer_drop1" id="loanCustname" class="form-control">
									<datalist id="customer_drop1">

									<%
					           			  for(int i=0;i<cuList2.size();i++){
					           				CustomerDetailsBean bean1=(CustomerDetailsBean)cuList2.get(i);					           				  
									%>

									<option data-value="<%=bean1.getCustId()%>"value="<%=bean1.getFirstName()%> <%=bean1.getLastName()%>">
									
									<%
				      						}
				    				%>
								</datalist>
								</div>
							</div>

							<label class="col-md-2 control-label" for="paydate">Pay Date<sup>*</sup></label>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-calendar"></i>
									</span> <input id="paydate" name="date" placeholder=""
										Name" class="form-control input-md" type="date">
								</div>
							</div>
						</div>
					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="amount">Type<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> </span> <input id="paytype"
										name="type" value="debit" class="form-control input-md"
										type="text" readonly>
								</div>
							</div>

						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentmode">Payment
								Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon fa fa-inr"> </span> <select
										class="form-control" id="loanpaymentMode1">
										<!-- <option value="selected">-Select Type--</option> -->
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>

									</select>
								</div>
							</div>

	<script>
		$(document).ready(function(){
	  		 $("#loanpaymentMode1").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#chequenumber").show(); 
	           	
	           	$("#neftaccnumber").hide(); 
	           	$("#cardnumber").hide();
	           
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#cardnumber").show(); 	
	          		
	          		$("#neftaccnumber").hide(); 
	        		$("#chequenumber").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neftaccnumber").show(); 	
	           		
	           		$("#chequenumber").hide(); 
	        		$("#cardnumber").hide();
	        	
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#neftaccnumber").hide(); 
	            		$("#chequenumber").hide();
	            		$("#cardnumber").hide(); 
	            	
	             }
	          	 else if($(this).attr("value")=="debitnote"){
		             	
	            		$("#neftaccnumber").hide(); 
	            		$("#chequenumber").hide();
	            		$("#cardnumber").hide(); 

	             }
	         	 
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#neftaccnumber").hide(); 
	        		$("#chequenumber").hide();
	        		$("#cardnumber").hide(); 
	        	
	         }
	          
	       });
	   }).change();
		});	
</script>

							<label class="col-md-2 control-label" for="payamount">Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon fa fa-inr"> 
										
									</span> <input id="payamount" name="payamount"
										placeholder="Enter Amount to Pay"
										onkeyup="onlyNumbersWithDot(this)"
										class="form-control input-md" type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">

							<div id="chequenumber">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeno3"
										id="chequeno3" placeholder="Cheque No." />
								</div>

								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheque3"
										id="nameOnCheque3" onkeyup="onlyletters(this)"
										placeholder="Name On check" />
								</div>
							</div>
						
							<div id="cardnumber" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum4"
										id="cardNum4" onkeyup="onlyNumbers(this)"
										placeholder="Card No." />
								</div>

							</div>

							<div id="neftaccnumber" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum4"
										id="accountNum4" placeholder="Account No." />
								</div>
							
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName5"
										id="nameofbank5" onkeyup="onlyletters(this)"
										placeholder="Name Of Bank" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="save" name="btn4" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="loanPaymentValidation()" value="Submit"> <input
									type="reset" id="btn2" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn4" onclick="location.reload()" value="Cancel">
							</div>
						</div>
					</form>
				</div>
				
				
				<!-- Loan payment Back -->
		<!-- 		<div class="tab-pane" id="loanpayments">
					<form method="post" action="" name="loanpayments">
						<div class="row">
							<div align="center">
								<h2 class="form-name style_heading">Credit Loan Payment</h2>
							</div>
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#creditloan"><h4 style="color: black">Credit Loan</h4></a></li>
								<li><a data-toggle="tab" href="#loanpayments"><h4 style="color: black">Loan Payment</h4></a></li>
							</ul>
							<br> <br>
						</div> -->
						
						
						
<%-- 				<div class="tab-pane" id="loanpayments">
					<form method="post" action="" name="loanpayments">
					
												<div class="row form-group">
							<label class="col-md-3 control-label" for="particular">Customer Name<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> 
								<%
									CreditLoanDao cld=new CreditLoanDao();
           							List cuList =cld.getAllCustomer();
							
								%>

									<input list="customer_drop" id="Customername" class="form-control" onchange="getLoanNo()">
									<datalist id="customer_drop">

									<%
					           			  for(int i=0;i<cuList.size();i++){
					           				CreditLoanBean bean=(CreditLoanBean)cuList.get(i);					           				  
									%>

									<option data-value="<%=bean.getPk_loan_id()%>"value="<%=bean.getCust_name()%>">
									
									<%
				      						}
				    				%>
								</datalist>
									
								</div>
							</div>

							
						</div>
					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="loanno">Loan
								No<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="	glyphicon glyphicon-hand-right"></i>
									</span> 
										<select class="form-control" id='loannum'  name="loanum"> 
									</select>
								</div>
							</div>
							
							<label class="col-md-2 control-label" for="paymentdate">Payment Date<sup>*</sup></label>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-calendar"></i>
									</span> <input  id="paymentdate" name="paymentdate" placeholder="" class="form-control input-md" onchange="getLoanDetails();checkFilledRed();checkFilledPink();checkFilledFontColor()" type="date">
								</div>
							</div>
							
						</div>
						
						
						<div class="row form-group">
						<label class="col-md-3 control-label" for="loanamount">Loan Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-Doller"></i>
									</span> <input id="loanamt" name="loanamt" placeholder=""
										Name" class="form-control input-md" type="text" readonly="true" >
								</div>
							</div>
							
							<label class="col-md-2 control-label" for="interestrate">Interest Rate<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
										%
									</span> <input id="interestrate1" name="interestrate" placeholder=""
										 class="form-control input-md" type="text" readonly="true">
								</div>
							</div>
						</div>
						
						<div class="row form-group">
						<label class="col-md-3 control-label" for="interestamt">Interest Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-Doller"></i>
									</span> <input id="interestamt" name="interestamt" placeholder=""
										Name" class="form-control input-md" type="text" readonly="true">
								</div>
							</div>
						</div>
						
						<div class="row form-group">
						<label class="col-md-3 control-label" for="days">Days<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
									<i class="glyphicon glyphicon-calendar"></i>
									</span> <input id="day" name="day" placeholder=""
										Name" class="form-control input-md" type="text" readonly="true">
								</div>
							</div>
							
							<label class="col-md-2 control-label" for="totalamt">Total Amount + Interest<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
									
									</span> <input id="totalamt" name="totalamt" placeholder=""
										class="form-control input-md" type="text" readonly="true">
								</div>
							</div>
						</div>

					
						<div class="row form-group">
							<label class="col-md-3 control-label" for="type">Type<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> </span> <input id="ptype"
										name="ptype" value="Credit" class="form-control input-md"
										type="text" readonly>
								</div>
							</div>

							<label class="col-md-2 control-label" for="balance">Last Balance<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">  </span> <input
										id="balance" name="balance"
										placeholder="Balance" class="form-control input-md"
										onkeyup="onlyNumbers(this)" type="text" readonly="true">
								</div>
							</div>
						</div>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="paymentmode">Payment
								Mode<sup>*</sup>
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> </span> <select
										class="form-control" id="loanpaymentMode1">
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
	  		 $("#loanpaymentMode1").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#lchequeno").show(); 
	           	
	           	$("#lneftaccno").hide(); 
	           	$("#lcardno").hide();
	           
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#lcardno").show(); 	
	          		
	          		$("#lneftaccno").hide(); 
	        		$("#lchequeno").hide();
	        		$("#lcreditnote").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#lneftaccno").show(); 	
	           		
	           		$("#lcardno").hide(); 
	        		$("#lchequeno").hide();
	        	
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#lneftaccno").hide(); 
	            		$("#lchequeno").hide();
	            		$("#lcardno").hide(); 
	            	
	             }
	          	 else if($(this).attr("value")=="debitnote"){
		             	
	            		$("#lneftaccno").hide(); 
	            		$("#lchequeno").hide();
	            		$("#lcardno").hide(); 

	             }
	         	 
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#lneftaccno").hide(); 
	        		$("#lchequeno").hide();
	        		$("#lcardno").hide(); 
	        	
	         }
	          
	       });
	   }).change();
		});	
</script>

							<label class="col-md-2 control-label" for="payamt">Pay Amount<sup>*</sup></label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> 
										$
									</span> <input id="payamt" name="payamt"
										placeholder="Enter Amount to pay"
										onkeyup="onlyNumbersWithDot(this)"
										class="form-control input-md" onkeypress="checkFilled()" type="text">
								</div>
							</div>
						</div>

						<div class="row form-group">

							<div id="lchequeno">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="chequeno3"
										id="chequeno3" placeholder="Cheque No." />
								</div>

								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="nameOnCheque3"
										id="nameOnCheque3" onkeyup="onlyletters(this)"
										placeholder="Name On check" />
								</div>
							</div>
						
							<div id="lcardno" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="cardNum4"
										id="cardNum4" onkeyup="onlyNumbers(this)"
										placeholder="Card No." />
								</div>

							</div>

							<div id="lneftaccno" class="form-group">
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="accNum4"
										id="accNum4" placeholder="Account No." />
								</div>
							
								<div class="col-md-3 col-md-offset-3 first">
									<input class="form-control" type="text" name="bankName4"
										id="bankName4" onkeyup="onlyletters(this)"
										placeholder="Name Of Bank" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-10 text-center">
								<input type="button" id="save" name="btn4" style="font-size: 25"
									class="btn btn-large btn-success button-height-width"
									onclick="creditLoanPaymentValidation()" value="Submit"> <input
									type="reset" id="btn2" style="font-size: 25"
									class="btn btn-large btn-danger   button-height-width"
									name="btn4" onclick="window.location.reload()" value="Cancel">
							</div>
						</div>
					</form>
				</div> --%>
				
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</body>
<%@include file="commons/newFooter.jsp"%>
