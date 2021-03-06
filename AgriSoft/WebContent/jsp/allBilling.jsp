<%@page import="com.Fertilizer.hibernate.ExpenseDetailForBillingAndGoodsReceiveBean"%>
<%@page import="com.Fertilizer.dao.ExpenseDetailForBillingAndGoodsReceiveDao"%>



<% boolean isHome=false;%>

<%@include file="commons/header.jsp"%>
<%@page import="com.Fertilizer.dao.CustomerDetailsDao"%>
<%@page import="com.Fertilizer.dao.FertilizerBillDao"%>
<%@page import="com.Fertilizer.hibernate.CustomerDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="com.Fertilizer.dao.ProductDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.ProductDetailsBean"%>
<%@page import="com.Fertilizer.dao.TaxCreationDao"%>
<%@page import="com.Fertilizer.hibernate.TaxCreationBean"%>
<%@page import="com.Fertilizer.bean.CustomerBillBean"%>
<%@page import="com.Fertilizer.hibernate.WholesaleCustomerDetailsBean"%>
<%@page import="com.Fertilizer.dao.ProductStockDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.ProductStockDetailsBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.Fertilizer.dao.GodownEntryDao"%>
<%@page import="com.Fertilizer.hibernate.GodownEntry"%>



<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/RadioButtonStyle.css">

<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
<link rel="stylesheet" href="/AgriSoft/staticContent/css/shree.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
<link rel="stylesheet"
	href="/AgriSoft/staticContent/y_css/jquery-ui.css">
<link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.css">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-ui-min.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jqueryUi.js"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>

<script type="text/javascript"
	src="/AgriSoft/staticContent/js/fertilizerBill.js"></script>
<script type="text/javascript">
	  	function openStockReport() {
	  		 window.location = '/AgriSoft/jsp/stockReport.jsp';
		}
	  		
	  	
	  	</script>
	  	
<script>
	
		/*  Calculations for cash  */
		 function transExpenseAddingToGrossTotal(){
			
			var transExpence = document.getElementById("transExpence").value;
			var hamaliExpence = document.getElementById("hamaliExpence").value;
	
		if(hamaliExpence == ""){
				var total = document.getElementById("totalWithExpense").value;
				var totalWithExpense = Number(total) + Number(transExpence);
				document.getElementById("grossTotal").value = totalWithExpense;
			}	
		
			if(hamaliExpence != ""){
				var total = document.getElementById("totalWithExpense").value;
				var hamaliTotal = Number(total) + Number(hamaliExpence);
				var totalWithExpense = Number(transExpence) + Number(hamaliTotal);
				document.getElementById("grossTotal").value = totalWithExpense;
			}	
		}

		function hamaliExpenseAddingToGross(){
			var hamaliExpence = document.getElementById("hamaliExpence").value;
			var transExpence = document.getElementById("transExpence").value;
		
			if(transExpence == ""){
				var total = document.getElementById("totalWithExpense").value;
				var totalWithExpense = Number(total) + Number(hamaliExpence);
				document.getElementById("grossTotal").value = totalWithExpense;
			}
			if(transExpence != ""){
				var total = document.getElementById("totalWithExpense").value;
				var totalWithExpense = Number(total) + Number(transExpence);
				var totalWithExpense1 = Number(totalWithExpense) + Number(hamaliExpence);
				document.getElementById("grossTotal").value = totalWithExpense1;
			}
		}
	
		
	/* 	Calculations for Credit  */
			function transExpenseAddingToGrossTotalForCredit(){
			
			var transExpence = document.getElementById("transExpence1").value;
			var hamaliExpence = document.getElementById("hamaliExpence1").value;
	
		if(hamaliExpence == ""){
				var total = document.getElementById("totalWithExpense1").value;
				var totalWithExpense = Number(total) + Number(transExpence);
				document.getElementById("grossTotal1").value = totalWithExpense;
			}	
		
			if(hamaliExpence != ""){
				var total = document.getElementById("totalWithExpense1").value;
				var hamaliTotal = Number(total) + Number(hamaliExpence);
				var totalWithExpense = Number(transExpence) + Number(hamaliTotal);
				document.getElementById("grossTotal1").value = totalWithExpense;
			}	
		}

		function hamaliExpenseAddingToGrossForCredit(){
			var hamaliExpence = document.getElementById("hamaliExpence1").value;
			var transExpence = document.getElementById("transExpence1").value;
		
			if(transExpence == ""){
				var total = document.getElementById("totalWithExpense1").value;
				var totalWithExpense = Number(total) + Number(hamaliExpence);
				document.getElementById("grossTotal1").value = totalWithExpense;
			}
			if(transExpence != ""){
				var total = document.getElementById("totalWithExpense1").value;
				var totalWithExpense = Number(total) + Number(transExpence);
				var totalWithExpense1 = Number(totalWithExpense) + Number(hamaliExpence);
				document.getElementById("grossTotal1").value = totalWithExpense1;
			}
		} 

</script>
<script type="text/javascript">
function pageLoad(){
	$("#CashCustDetail").show();
	$("#CreditCustDetail").hide(); 
}
function openCashCustomerBilling() {
	$("#CashCustDetail").show();
	$("#CreditCustDetail").hide();
	location.reload();
}
function openCreditCustomerBilling() {
	$("#CreditCustDetail").show();
	$("#CashCustDetail").hide();
}
</script>
<style>
@font-face{
 font-family:'digital-clock-font';
 src: url('/AgriSoft/staticContent/fonts/digital-7 (mono).ttf');
}
</style>
</head>
<body>
<%
        Long customerBill = 1l;
	 %>
<%
        FertilizerBillDao dao1 = new FertilizerBillDao();
	
	    List bill = dao1.getCustomerBill();
	
	    for(int i=0;i<bill.size();i++){
	    CustomerBillBean sa=(CustomerBillBean)bill.get(i);
	    customerBill= sa.getBillNo();
	    System.out.println(customerBill);
	
	     customerBill++;
	     System.out.println(customerBill);        
	        }
         %>


<body onload="pageLoad();fetchFertilizerProductAgainstDefaultGodown();">

	<!-- getProductName(); -->
	<div class="row header_margin_top" style="height: 1px;">
		<div align="center">
			<h2 class="form-name style_heading">Fertilizer Billing</h2>
		</div>

		<div align="right" style="margin-right: 150px;">
			<h3 style="color: red;font-family: digital-clock-font;">
				Bill No ::
				<%out.println(customerBill); %>
			</h3>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-offset-1 col-md-10" style="margin-top: 60px;">
			 <hr style="border-top-color: #c1b1b1;"> 
		</div>
	</div>

	<div id="RegularCustomerDropDown" style="background: #f0f0f0;    padding-bottom: 90px;">
	<div class="container col-sm-offset-1 ">
		<div class="row form-group">
			
			<div class="textalign">
   			 <label>
       		 <input type="radio" checked name="customertype" id="customertype" checked="checked"
						onclick="openCashCustomerBilling()" > 
         <div  class="btn1 btn-sık"><span>Cash</span></div> </label>
     <label >
        <input  type="radio" name="customertype" id="customertype"
						onclick="openCreditCustomerBilling()"> 
       <div class="btn1 btn-sık"><span>Credit</span></div></label>
       </div>
			
			
			
		</div>
	</div>
	
<form class="form-horizontal" method="post" action="" name="fertilizerform">
	<div class="row form-group">
	
		<div style="margin-left: 382px;padding-bottom: 10px;">
			<div class="col-md-4" style="margin-left: -37px;width: 345px;">
    			   <select id="customertype1" class="form-control" style="color:black;background-color:#ebecf2;font-size:20px;border:5px">
    		    	<option value="Regular">Regular Customer</option>
    		    	<option value="Wholesale">Wholesale Customer</option>
    		   	</select>
 	 		</div>
 	 	</div>
 	 	
 	 		     
 	 	
 	 	
 	 	
 	 	
 	 	<script>
			
				       	$(document).ready(function(){
					  		 $("#customertype1").change(function(){
					       	$(this).find("option:selected").each(function(){
					       		if($(this).attr("value")=="Regular"){
						           	
						           	$("#RegularCustDropDown").show(); 
						           	$("#WholesalecustDropDown").hide(); 
						           	}
						          	 else if($(this).attr("value")=="Wholesale"){
						           	
						          		 	
						          		$("#RegularCustDropDown").hide(); 
						          		$("#WholesalecustDropDown").show();
						           }
					          	 //class="dropdown"
					          
					       });
					   }).change();
						});	
				       	
				       	
					</script>
	</div>	
</form>
		<!------------------			Code for Cash customers ------------------>

		<div id="CashCustDetail">
			<form class="form-horizontal" method="post" action="" name="fertiBill">
				<fieldset>
				
				
				<div id="RegularcustDropDown">
					<div class="row form-group">
						<label class="col-md-3 control-label" for="customerName">Customer Name<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input id="customerName" name="customerName"
									placeholder="Customer Name" class="form-control input-md"
									type="text">
							</div>
						</div>
					</div>
					
				</div>
					<div class="row form-group">
						<label class="col-md-3 control-label" for="village">Village/City</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input id="village" name="village" placeholder="Village"
									class="form-control input-md ac_district" type="text">
							</div>
						</div>
						
						<label class="col-md-2 control-label" for="contactNo">Contact
							No.</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-earphone"></i>
								</span> <input id="contactNo" name="contactNo"
									placeholder="Contact No." class="form-control input-md"
									type="text">
							</div>
						
					</div>
				</div>
					<div class="row form-group">
							<label class="col-md-3 control-label" for="godown">
								Godown
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> 
									<%
									
									GodownEntryDao ged=new GodownEntryDao();
									List gList =ged.getAllGodown();
								
							
							%>

								<input list="godown_drop" id="godown" class="form-control"  onchange="fetchProductGodownWise()"><!-- <span class="input-group-addon"> 
								 <button  type="button" onclick="supplierDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								<button type="button" onclick="autoRefresh_div()" ><img src="/AgriSoft/staticContent/images/refresh.png" width="16" heigth="13"></button> 
								</span> -->
								<datalist id="godown_drop">
									
									<%
					           for(int i=0;i<gList.size();i++){
					        	   GodownEntry bean=(GodownEntry)gList.get(i);
					        	
							%>		
									
									 <option data-value="<%=bean.getPkGodownId()%>"
										value="<%=bean.getGodownName() %>" >
									</option>
										<%
				      			}
				    		%>
								</datalist>
									
									
									
								</div>
							</div>
							
								<label class="col-md-2 control-label" for="aadharNo">Aadhar
							No.</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-earphone"></i>
								</span> <input id="aadharNo" name="aadharNo" placeholder="Aadhar No."
									class="form-control input-md" type="text">
							</div>
						</div>
						</div>

						
					
					
					<!-- <div class="row form-group">
						<label class="col-md-3 control-label" for="productName">Product
							Name<sup>*</sup>
						</label>
						<div class="col-md-8">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span> 
								<input onchange="getProductDetailsByProductName()"  list="fert_pro_drop" class="form-control" id='proName' name="proName">
								 <datalist id="fert_pro_drop">
								 <option></option>
								 </datalist>
							</div>
						</div>
					</div> -->
					
					
					<div class="row form-group">
						<label class="col-md-3 control-label" for="productName">Product
							Name<sup>*</sup>
						</label>
						<div class="col-md-8">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span> 
								
								<%-- <%
								
								ProductDetailsDao FertiProDao=new ProductDetailsDao();
								List ProductList=FertiProDao.getAllFertilizerProductList(); 
								/* 	CustomerDetailsDao dao = new CustomerDetailsDao();
           							List cust =dao.getAllCustomer(); */
							
							%> --%>

								<input list="fert_pro_drop" class="form-control" id='proName' name="proName" onchange="getProductDetailsByProductName()" onfocus="this.value=''">
								<datalist id="fert_pro_drop">

							<%-- 		<%
					           for(int i=0;i<ProductList.size();i++){
					        	   ProductStockDetailsBean bean =(ProductStockDetailsBean)ProductList.get(i);
							%>
									<option data-value="<%=bean.getPk_stock_id()%>"><%=bean.getProduct_name() %>,
										<%=bean.getCompanyname() %>,<%=bean.getPacking()%>,Stock=,<%=bean.getQuantity()%>
									</option>
									<%
				      			}
				    		%> --%>
								</datalist>
								
							</div>
						</div>
					</div>
					
					
					
					<div class="row form-group">
						<label class="col-md-3 control-label" for="barcode">Barcode</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No. </span> <input id="key"
									name="key" placeholder="Barcode" class="form-control input-md"
									type="text" onchange="getitemData()">
							</div>
						</div>
						<label class="col-md-2 control-label" for="barcode">Bill Date<sup>*</sup></label>
						<%
					  	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					  	String todayDate = simpleDateFormat.format(new Date());
						%>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No. </span> <input id="billdate"
									name="billdate"  value="<%= todayDate%>" class="form-control input-md"
									type="Date">
							</div>
						</div>
						
						
					</div>
					
					<!-- 	<div style='position:absolute; top:440px; left:108px; '> -->
					
					<div class="table-responsive	row" style="margin-left: 75px">
						<table id="list4"></table>
						<div id="jqGridPager"></div>
						<!-- </div> -->
					</div>

					<div class="row form-group"></div>
					<div class="row form-group">
						<div class="col-md-3 control-label">
							<label for="paymentMode"> Payment Mode</label>
						</div>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-usd"></i>
								</span> <select class="form-control" id="paymentMode">
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
	          
	       });
	   }).change();
		});	
		</script>
		</div>
					<div class="row form-group">
						<div id="cheque_no">
							<div class="col-md-3 col-md-offset-3 first">
								<input class="form-control" type="text" name="chequeNum"
									id="chequeNum" placeholder="Cheque No." />
							</div>

							<div class="col-md-3 col-md-offset-2 first">
								<input class="form-control" type="text" name="nameOnCheck"
									id="nameOnCheck" placeholder="Name On check" />
							</div>
						</div>

						<div id="card_no" class="form-group">
							<!-- <div class="col-md-2">
													<label class="control-label">Card No:</label>
												</div> -->
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
							<!-- <div class="col-md-1 ">
													<label class="control-label">Bank Name</label>
												</div> -->
							<div class="col-md-3 col-md-offset-2 first">
								<input class="form-control" type="text" name="bankName"
									id="bankName" placeholder="Name Of Bank" />
							</div>
						</div>
					</div>

					<div class="row form-group" style="margin-top: 10px;">
						<div class="col-md-5" id="calculator" style="margin-left: 146px;">
							<!-- Screen and clear key -->
							<div class="top">
								<span class="clear">C</span>
								<div class="screen"></div>
							</div>

							<div class="keys">
								<!-- operators and other keys -->
								<span>7</span> <span>8</span> <span>9</span> <span
									class="operator">+</span> <span>4</span> <span>5</span> <span>6</span>
								<span class="operator">-</span> <span>1</span> <span>2</span> <span>3</span>
								<span class="operator">÷</span> <span>0</span> <span>.</span> <span
									class="eval">=</span> <span class="operator">x</span>
							</div>
						</div>
						<script type="text/javascript"
							src="/AgriSoft/staticContent/js/PrefixFree 1.0.7.js"></script>
						<script type="text/javascript"
							src="/AgriSoft/staticContent/js/calculator.js"></script>

						<div class="col-md-6">
							<div class="row" style="margin-top: 15px;">
								<label class="col-md-4 control-label" for="total"><b>Total</b></label>
								<div class="col-md-7">
									<div class="input-group">
										<span class="input-group-addon"> Rs </span> <input
											id="totalWithExpense" readonly="readonly"
											class="form-control input-md" type="text">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row" style="margin-top: 15px;">
								<label class="col-md-4 control-label" for="transExpence"><b>Transportation
										Expenses</b></label>

								<div class="col-md-7">
									<div class="input-group">
										<span class="input-group-addon"> Rs </span> <input
											id="transExpence" name="transExpence"
											placeholder="Transportation Expenses"
											class="form-control input-md" type="text" onkeypress="return isNumber(event)"
											onchange="transExpenseAddingToGrossTotal()">
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row" style="margin-top: 15px;">
								<label class="col-md-4 control-label" for="hamaliExpence"><b>Hamali
										Expenses</b></label>
								
								<div class="col-md-7">
									<div class="input-group">
										<span class="input-group-addon"> Rs </span> <input
											id="hamaliExpence" name="hamaliExpence"
											placeholder="Hamali Expenses" class="form-control input-md"
											type="text" onkeypress="return isNumber(event)" onchange="hamaliExpenseAddingToGross()">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row" style="margin-top: 15px;">
								<label class="col-md-4 control-label" for="grossTotal"><h4>
										<b>Gross Total</b>
									</h4></label>
								<div class="col-md-7">
									<div class="input-group">
										<span class="input-group-addon"> Rs </span> <input
											readonly="readonly" id="grossTotal" name="grossTotal"
											placeholder="Gross Total" class="form-control input-md"
											type="text" style="font-size: 25px; height: 55px;">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row form-group buttons_margin_top ">
						<div align="center">

							<input type="button" accesskey="q" id="btn" style="font-size: 25"
								class="btn btn-large btn-success button-height-width"
								name="save" onclick="fertilizerBill()"
								value="Print Bill (Alt + Q)"> <input type="reset"
								style="font-size: 25"
								class="btn btn-large btn-danger  button-height-width" id="save"
								name="btn" value="Cancel" onclick="window.location.reload()"> <input
								type="button" id="btn" style="font-size: 25"
								class="btn btn-large btn-success button-height-width" name="btn"
								onclick="openStockReport()" value="Stock Report">
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<!--         For credit Customer   -->

		<div id="CreditCustDetail">
			<form class="form-horizontal" method="post" action=""
				name="creditFertiBill1">
				<fieldset>
				<div  class="row form-group">
				<div id="RegularCustDropDown" class="form-group">
						<label class="col-md-3 control-label" for="creditCustomerName">
							Customer Name<sup>*</sup>
						</label>
						<div class="col-md-3" style="margin-left: 8px;width: 343px;">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span>


								<!-- Following code is to get customers from "customer_details" table of "fertilizer" DB -->
								<!-- getAllCustomer() is implemented in  CustomerDetailsDao with return type List-->

								<%
							CustomerDetailsDao dao = new CustomerDetailsDao();
           						List cust =dao.getAllCustomer();
							
							%>

								<input type="text" id="creditCustomer" list="cust_drop1"
									class="form-control"
									onchange="custDetail.getVillageName();custDetail.getContactNo();custDetail.getName();custDetail.getAadhar()">
								<datalist id="cust_drop1">

									<%
					           for(int i=0;i<cust.size();i++){
					        	   CustomerDetailsBean bean =(CustomerDetailsBean)cust.get(i);
							%>
									<option data-value="<%=bean.getCustId()%>"><%=bean.getFirstName() %>
										<%=bean.getLastName() %>
									</option>
									<%
				      			}
				    		%>
								</datalist>
							</div>
					</div>
					
				</div>	
			<!-- Wholesale Customer DropDown -->
					
				<div id="WholesalecustDropDown" class="form-group">
						<label class="col-md-3 control-label" for="creditWholsaleCustomerName">Wholsale Customer Name <sup>*</sup>
						</label>
						<div class="col-md-3" style="margin-left: 8px;width: 343px;">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span>

							<%
								CustomerDetailsDao daow = new CustomerDetailsDao();
           						List wholesalecust =daow.getAllWholesaleCustomer();
							
							%>
								<!-- onchange="custDetail.getVillageName();custDetail.getContactNo();custDetail.getName();custDetail.getAadhar()" -->
								<input type="text" id="creditwholsaleCustomer" list="whosalecust_drop"
									class="form-control">
								<datalist id="whosalecust_drop">

									<%
					           for(int i=0;i<wholesalecust.size();i++){
					        	   WholesaleCustomerDetailsBean wbean =(WholesaleCustomerDetailsBean)wholesalecust.get(i);
							%>
									<option data-value="<%=wbean.getPk_shop_id()%>"><%=wbean.getShopname() %>
										
									</option>
									<%
				      			}
				    		%>
								</datalist>
							</div>
						</div>
 					</div>
				
		</div>
				<!-- End wholesale dropdown -->
					
					
					<div class="row form-group">
						<label class="col-md-3 control-label" for="village1">Village/City</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-map-marker"></i>
								</span> <input id="village1" name="village1"
									class="form-control input-md ac_district" type="text">
							</div>
						</div>
						
						<label class="col-md-2 control-label" for="contactNo1">Contact
							No.</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-earphone"></i>
								</span> <input id="contactNo1" name="contactNo1"
									class="form-control input-md" type="text">
							</div>
						
					</div>
				</div>
				
					<div class="row form-group">
						
								<label class="col-md-3 control-label" for="godown">
								Godown
							</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> 
									<%
									
									GodownEntryDao ged1=new GodownEntryDao();
									List gList1 =ged.getAllGodown();
								
							
							%>

								<input list="godown_drop" id="godown1" class="form-control"  onchange="fetchProductGodownWiseforCredit()"><!-- <span class="input-group-addon"> 
								 <button  type="button" onclick="supplierDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								<button type="button" onclick="autoRefresh_div()" ><img src="/AgriSoft/staticContent/images/refresh.png" width="16" heigth="13"></button> 
								</span> -->
								<datalist id="godown_drop">
									
									<%
					           for(int i=0;i<gList.size();i++){
					        	   GodownEntry bean=(GodownEntry)gList1.get(i);
					        	
							%>		
									
									 <option data-value="<%=bean.getPkGodownId()%>"
										value="<%=bean.getGodownName() %>" >
									</option>
										<%
				      			}
				    		%>
								</datalist>
									
									
									
								</div>
							</div>
						
						
						
						<label class="col-md-2 control-label" for="aadharNo1">Aadhar
							No.</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No. </span> <input
									id="aadharNo1" name="aadharNo1" placeholder="Aadhar No."
									class="form-control input-md" type="text">
							</div>
						</div>
					</div>
						
				

					<!-- <div class="row form-group">
						<label class="col-md-3 control-label" for="productName">Product
							Name<sup>*</sup>
						</label>
						<div class="col-md-8">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span> <select class="form-control" id='proName1' name="proName1"
									onchange="getProductDetailsByProductNameForCredit()">
								</select>
							</div>
						</div>
					</div> -->


					<div class="row form-group">
						<label class="col-md-3 control-label" for="productName">Product
							Name<sup>*</sup>
						</label>
						<div class="col-md-8">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="	glyphicon glyphicon-hand-right"></i>
								</span>
								
								<%-- <%
								
								ProductDetailsDao FertiProDao1=new ProductDetailsDao();
								List ProductList1=FertiProDao1.getAllFertilizerProductList(); 
								
							
							%> --%>

								<input list="fert_pro_drop" class="form-control" id='proName1' name="proName1" onchange="getProductDetailsByProductNameForCredit()" onfocus="this.value=''">
								<datalist id="fert_pro_drop">

							<%-- 		<%
					           for(int i=0;i<ProductList1.size();i++){
					        	   ProductStockDetailsBean bean =(ProductStockDetailsBean)ProductList1.get(i);
							%>
									<option data-value="<%=bean.getPk_stock_id()%>"><%=bean.getProduct_name() %>,
										<%=bean.getCompanyname() %>,<%=bean.getPacking()%>,Stock=,<%=bean.getQuantity()%>
									</option>
									<%
				      			}
				    		%> --%>
								</datalist>
					
								
								
								<!--  <select class="form-control" id='proName1' name="proName1"
									onchange="getProductDetailsByProductNameForCredit()">
								</select> -->
							</div>
						</div>
					</div>
					
						

					<div class="row form-group">
						<label class="col-md-3 control-label" for="barcode1">Barcode</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No. </span> <input
									id="barcode1" name="barcode1" placeholder="Barcode"
									class="form-control input-md" type="text"
									onchange="getProDetailsAsPerBarcode()">
							</div>
						</div>
						
						<label class="col-md-2 control-label" for="barcode">Bill Date<sup>*</sup></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> No. </span> <input id="billdate1"
									name="billdate" value="<%= todayDate%>" class="form-control input-md"
									type="Date">
							</div>
						</div>
						
					</div>
					
					<div class="table-responsive row" style="margin-left: 75px">
						<table id="credit"></table>
						<div id="jqGridPagerForCreditCustomer"></div>
					</div>

					<div class="row form-group"></div>
					<div class="row form-group">

						<!--  	Customer first name is hidden	 -->

						<label class="col-md-3 control-label" style="display: none"
							for="customerNameHidden">Customer Name</label>
						<div class="col-md-3" style="display: none">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input id="customerNameHidden" name="customerNameHidden"
									class="form-control input-md" type="text">
							</div>
						</div>

						<label class="col-md-2 col-md-offset-6  control-label" for="total"><b>Total</b></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> Rs </span> <input
									id="totalWithExpense1" readonly="readonly"
									class="form-control input-md" type="text">
							</div>
						</div>
					</div>

					<div class="row form-group">
						<label class="col-md-2 col-md-offset-6 control-label"
							for="transExpence"><b>Transportation Expenses</b></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> Rs </span> <input
									id="transExpence1" name="transExpence"
									placeholder="Transportation Expenses"
									class="form-control input-md" type="text" onkeypress="return isNumber(event)"
									onchange="transExpenseAddingToGrossTotalForCredit()">
							</div>
						</div>
					</div>
				
					<div class="row form-group">
						<label class="col-md-2 col-md-offset-6 control-label"
							for="hamaliExpence"><b>Hamali Expenses</b></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> Rs </span> <input
									id="hamaliExpence1" name="hamaliExpence"
									placeholder="Hamali Expenses" class="form-control input-md"
									type="text" onkeypress="return isNumber(event)" onchange="hamaliExpenseAddingToGrossForCredit()">
							</div>
						</div>
					</div>

					<div class="row form-group">
						<label class="col-md-offset-6 col-md-2 control-label"
							for="grossTotal1"><h4>
								<b>Gross Total</b>
							</h4></label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> Rs </span> <input
									readonly="readonly" id="grossTotal1" name="grossTotal1"
									placeholder="Gross Total" class="form-control input-md"
									type="text" style="font-size: 25px; height: 55px;">
							</div>
						</div>
					</div>


					<div class="row form-group buttons_margin_top ">
						<div align="center">
							<input type="button" accesskey="w" id="btn1" style="font-size: 25"
								class="btn btn-large btn-success button-height-width"
								name="btn2" onclick="fertilizerBillForCreditCustomer()"
								value="Print Bill (Alt + W)"> <input type="reset"
								style="font-size: 25"
								class="btn btn-large btn-danger  button-height-width" id="save"
								name="btn" value="Cancel" onclick="window.location.reload()"> <input
								type="button" id="btn" style="font-size: 25"
								class="btn btn-large btn-success button-height-width" name="btn"
								onclick="openStockReport()" value="Stock Report">
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>

<%@include file="commons/newFooter.jsp"%>

