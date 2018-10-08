 <%@page import="java.util.List" %>
 <%@page import="com.Fertilizer.dao.CustomerDetailsDao" %>
 <%@page import="com.Fertilizer.hibernate.CustomerDetailsBean" %>
 
 <body onload="defaultHideElementOfCust()">	 
  <form class="form-horizontal" method="post" action="">
         	<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Customer Payment</h2>
			  	</div>
    </div>
 
    <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
 		   </div>
 		   
         	       <div class="row form-group">
           				 <label class="col-md-2 control-label" for="customerName">Customer Name</label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
								<select class="form-control input-md" id="customerName">
										<option value="">Select Customer</option>
							<%
								CustomerDetailsDao cdd = new CustomerDetailsDao();
           						List cList =cdd.getAllCustomer();
							
							%>
							
							<%
					           for(int i=0;i<cList.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList.get(i);
							%>
						<option value="<%=cust.getCustId()%>"><%=cust.getFirstName() %> <%=cust.getLastName() %> </option>
							<%
				      			}
				    		%>
						</select>  
								</div>
           					 </div>
           				  
           				  <label class="col-md-2 control-label" for="bill_no"> Bill No </label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										No
									</span>
           		 						  <input id="bill_no" name="bill_no" placeholder="" class="form-control input-md" type="text" >
           						 </div>
							</div>
						  </div>
						  
					 <div class="row form-group">
           				<label class="col-md-2 control-label" for="totalAmt"> Total Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									   <input id="totalAmt" name="totalAmt" placeholder="" class="form-control input-md" type="text" >
           							 </div>
								</div>
            
           				 	 <label class="col-md-2 control-label" for="creditDebit">Credit/Debit</label>  
           						 <div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-hand-right"></i>
										</span>
										<select class="form-control input-md" id="creditDebit">
											<option>Select Type</option>
											<option>Credit</option>
											<option>Debit</option>
										</select>
									</div>
          						  </div>
							  </div>
						  
						   <div class="row form-group">
           					 <label class="col-md-2 control-label" for="payAmount">Pay Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									   <input id="payAmount" name="payAmount" placeholder="" class="form-control input-md" type="text" >
           							 </div>
								</div>
            
           				 	 <label class="col-md-2 control-label" for="reason">Reason</label>  
           						 <div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-question-sign"></i>
										</span>
				  						    <input id="reason" name="reason" placeholder="" class="form-control input-md" type="text" >
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
					</span> <select class="form-control" id="payment_type_id1">
						<option value="selected">-Select Type--</option>
						<option value="cash1">Cash</option>
						<option value="cheque1">Cheque</option>
						<option value="card1">Card</option>
						<option value="neft1">NEFT</option>
					</select>
				</div>
			</div>

			<script>
	function defaultHideElementOfCust()
	{
		
		document.getElementById("neft_acc_no1").style.display = "none";
		document.getElementById("cheque_no1").style.display = "none";
		document.getElementById("card_no1").style.display = "none";
	}
	
	$(document).ready(function(){
  		 $("#payment_type_id1").change(function(){
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
          	else if($(this).attr("value")=="selected"){
             	
        		$("#neft_acc_no1").hide(); 
        		$("#cheque_no1").hide();
        		$("#card_no1").hide(); 
         }
          
       });
   }).change();
  });	
</script>

			<div class="input-group">
				<div id="cheque_no1" class="row">

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

				<div id="card_no1" class="row">
					<div class="col-md-2">
						<label>Card No.:</label>
					</div>
					<div class="col-md-4 first">
						<input class="form-control" type="text" name="cardNum"
							id="cardNum" placeholder="Card No." />
					</div>

				</div>

				<div id="neft_acc_no1" class="row">
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
						 <label class="col-md-2 control-label" for="personname"> Person Name </label>  
			           		 <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
			           		   <input id="personname" name="personname" placeholder="" class="form-control input-md" type="text" >
			           		 </div>
						</div>
						
						<label class="col-md-2 control-label" for="registrationname"> Registration  No</label>  
           						 <div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											No
										</span>
				  						    <input id="registrationname" name="registrationname" placeholder="" class="form-control input-md" type="text">
									</div>
          						  </div>
			          </div>  
			          
			         	<div class="form-group row">
			            <div class="col-md-10 text-center">
			              <button id="save" name="save" class="btn btn-large btn-success"> Submit</button>
			              <button class="btn btn-large btn-danger" type="reset"> Cancel </button>
			            </div>
			          </div>       	    
			 </form>