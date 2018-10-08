<!-- Guru ShortCuts upto line 162 -->
 <html>
<head>
	<link href="/AgriSoft/staticContent/css/bootstrap.css" rel="stylesheet">
	<link href="/AgriSoft/staticContent/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="/AgriSoft/staticContent/js/bootstrap.js"> </script>
	<script type="text/javascript" src="/AgriSoft/staticContent/js/bootstrap.min.js"> </script>
	
	<style>
		.gradient {
   			 position: relative;
		}
		.gradient:after {
		    color:#000;
		    position: absolute;
		    top: 0;
		    left: 0;
		    z-index: -1;
		}
		.gradient h2 {
		    color: #117FB2;
		    -webkit-mask-box-image: -webkit-gradient(linear,
                left top, left bottom,
                color-stop(20%,rgba(0,0,0,1)),
                color-stop(100%,rgba(0,0,0,0)));
		}
	</style>
	
	<script type="text/javascript">
	
		function FertilizerBill(){
			window.location.href = "allBilling.jsp";
		}
		
		function seddAndPestiBill(){
			window.location.href = "seedAndPestiBill.jsp";
		}
		
		function pesticideBill(){
			window.location.href = "pesticideBill.jsp";
		}
		
		
		
		function customerEntry(){
			window.location.href = "customer_detail.jsp";
		}
		
		function categoryEntry(){
			window.location.href = "categoryDetails.jsp";	
		}
		
		function productEntry(){
			window.location.href = "product_detail.jsp";
		}
		
		function supplierEntry(){
			window.location.href = "supplierdetails.jsp";			
		}
		
		function godownEntry(){
			window.location.href = "godownDetails.jsp";
		}
		
		function expeditureEntry(){
			window.location.href = "expenditureDetails.jsp";
		}
		
		function advanceBooking(){
			window.location.href = "purchaseOrderDetails.jsp";
		}
		
		function goodReceive(){
			window.location.href = "goodsReceive.jsp";
		}
		
		function purchaseReturn(){
			window.location.href = "purchase_return.jsp";
		}
		
		function stockReport(){
			window.location.href = "stockReport.jsp";
		}
		
		function purchaseReport(){
			window.location.href = "purchaseReports.jsp";
		}
		
		function cashbookReport(){
			 window.location.href = "cashBookReports.jsp";
		}
		
		function creditDebitReport(){
			window.location.href = "AllCreditAmountReports.jsp";
		}
		
		function fertilizerBillCopy(){
			window.location.href = "SeedAndPesticideBillCOPY.jsp";
		}
		
		function seedAndPestiBillCopy(){
			window.location.href = "SeedAndPesticideBillCOPY.jsp";
		}
		
		function pestiBillCopy()
		{
			window.location.href = "pesticideBillPDFCopy.jsp";
		}

		
	</script>
	
</head>
<body>
	<div class="fluid-container">
	<div class="row footer navbar-fixed-bottom" style="background-color:#8c7674;">
		<div class="col-md-12" class="row footer navbar-fixed-bottom" style="background-color: #d6d4d4;">
			<div class="table-responsive">
			<center><font size="3" color="black"><b> Copyright <img src="/AgriSoft/staticContent/images/c1.png" height="18" width="18"/> 2018 All Rights Reserved. Design  & Developed By Embel Technologies Pvt Ltd </b></font></center>
			 <!--  <marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="4" style="height: 35px;">			
			   <table class="table">
				<tbody>
					<tr>
						<div class="btn-group">
						
						    billing module
							<td><input  type="button" class="btn btn-primary" onclick="FertilizerBill()" value="Fertilizer Bill ( Ctrl + F )"></td>
							<td><input type="button" class="btn btn-primary" onclick="seddAndPestiBill()" value="Seed Bill ( Ctrl + S )"></td>
							<td><input type="button" class="btn btn-primary" onclick="pesticideBill()" value="Pesticide Bill ( Ctrl + Alt + L )"></td>
							
							Master module
							<td><input  type="button" class="btn btn-primary" onclick="customerEntry()" value="Customer Entry ( Alt + C )"></td>
							<td><input  type="button" class="btn btn-primary" onclick="categoryEntry()" value="Category Entry = Ctrl + Alt + T"></td>
							<td><input type="button" class="btn btn-primary" onclick="productEntry()" value="Product Entry ( Alt + U )"></td>
							<td><input type="button" class="btn btn-primary" onclick="supplierEntry()" value="Supplier Entry ( Alt + S )"></td>
							<td><input type="button" class="btn btn-primary" onclick="godownEntry()" value="Godown Entry ( Alt + G )"></td>
							<td><input type="button" class="btn btn-primary" onclick="expeditureEntry()" value="Expenditure Entry = Ctrl + Alt + E"></td>
							 <td><input type="button" class="btn btn-primary" value="Expence Entry = Alt + E"></td>
							
							purchase module
							<td><input type="button" class="btn btn-primary" onclick="advanceBooking()" value="Advance Booking ( Ctrl + A )"></td>
							<td><input type="button" class="btn btn-primary" onclick="goodReceive()" value="Goods Receive ( Ctrl + G )"></td>
							<td><input type="button" class="btn btn-primary" onclick="purchaseReturn()" value="Purchase Return ( Ctrl + R )"></td>
							
							stock module
							<td><input type="button" class="btn btn-primary" onclick="stockReport()" value="Stock Report ( Ctrl + Alt + T )"></td>
							<td><input type="button" class="btn btn-primary" onclick="purchaseReport()" value="Purchase Report ( Ctrl + Alt + P )"></td>
							<td><input type="button" class="btn btn-primary" onclick="cashbookReport()" value="CashBook Report ( Ctrl + Alt + C )"></td>
							<td><input type="button" class="btn btn-primary" onclick="creditDebitReport()" value="Credit/Debit Report ( Ctrl + Alt + M )"></td>
							
							bill copy module
							<td><input type="button" class="btn btn-primary" onclick="FertilizerBillCOPY.jsp" value="Fertilizer Bill Copy ( Ctrl + Shift + F )"></td>
							<td><input type="button" class="btn btn-primary" onclick="seedAndPestiBillCopy()" value="Seed Bill Copy ( Ctrl + Shift + S )"></td>
							<td><input type="button" class="btn btn-primary" onclick="pestiBillCopy()" value="Pesticide Bill Copy ( Ctrl + B )"></td>
						</div>
					</tr>
				</tbody>
			  </table>
			</marquee>
 -->		</div>
	  </div>
	 </div>
</div>
</body>
</html> 
