
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="java.util.List"%>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">Supplier Payment</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<body onload="defaultHideElement()">
	<form class="form-horizontal" method="post" action="">
		<div class="row form-group">
			<label class="col-md-2 control-label" for="supplierName">Supplier
				Name</label>
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-user"></i>
					</span> <select class="form-control input-md" id="supplierName">
						<option value="">Select Supplier</option>

						<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
						<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->

						<%
								SupplierDetailsDao sdd = new SupplierDetailsDao();
           						List sList =sdd.getAllSupplier();
							
							%>

						<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetailsBean sup =(SupplierDetailsBean)sList.get(i);
							%>

						<option value="<%=sup.getSupId()%>"><%=sup.getDealerName() %>
						</option>
						<%
				      			}
				    	%>
					</select>
				</div>
			</div>
			
			<label class="col-md-2 control-label" for="bill_no"> Bill No
			</label>
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon"> No </span> <input id="bill_no"
						name="bill_no" placeholder="" class="form-control input-md"
						type="text">
				</div>
			</div>
		</div>

		<div class="row form-group">
			<label class="col-md-2 control-label" for="totalAmt"> Total
				Amount</label>
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon"> Rs </span> <input id="totalAmt"
						name="totalAmt" placeholder="" class="form-control input-md"
						type="text">
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

			<label class="col-md-2 control-label" for="reason">Reason</label>
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-question-sign"></i>
					</span> <input id="reason" name="reason" placeholder=""
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
					</span> <select class="form-control" id="payment_type_id">
						<option value="selected">-Select Type--</option>
						<option value="cash">Cash</option>
						<option value="cheque">Cheque</option>
						<option value="card">Card</option>
						<option value="neft">NEFT</option>
					</select>
				</div>
			</div>

<script>
		function defaultHideElement()
		{
			
			document.getElementById("neft_acc_no").style.display = "none";
			document.getElementById("cheque_no").style.display = "none";
			document.getElementById("card_no").style.display = "none";
		}
		
		$(document).ready(function(){
	  		 $("#payment_type_id").change(function(){
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
	          
	       });
	   }).change();
		});	
</script>

			<div class="input-group">
				<div id="cheque_no">
					<div class="col-md-2 ">
						<label>Cheque No.:</label>
					</div>
					
					<div class="col-md-4 first">
						<input class="form-control" type="text" name="chequeNum"
							id="chequeNum" placeholder="Cheque No." />
					</div>
					
					<div class="col-md-2">
						<label>Date:</label>
					</div>
					
					<div class="col-md-4 first">
						<input class="form-control" type="date" name="bdate" id="bdate"
							placeholder="Cheque date" />
					</div>
				</div>

				<div id="card_no">
					<div class="col-md-2">
						<label>Card No.:</label>
					</div>
					
					<div class="col-md-4 first">
						<input class="form-control" type="text" name="cardNum"
							id="cardNum" placeholder="Card No." />
					</div>
				</div>

				<div id="neft_acc_no">
					<div class="col-md-3 ">
						<label>Acc No.:</label>
					</div>
					<div class="col-md-4 first">
						<input class="form-control" type="text" name="NeftAccNum"
							id="NeftAccNum" placeholder="Account No." />
					</div>
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
					</span> <input id="personname" name="personname" placeholder=""
						class="form-control input-md" type="text">
				</div>
			</div>

			<label class="col-md-2 control-label" for="registrationname">
				Registration No</label>
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon"> No </span> <input
						id="registrationname" name="registrationname" placeholder=""
						class="form-control input-md" type="text">
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
</body>
