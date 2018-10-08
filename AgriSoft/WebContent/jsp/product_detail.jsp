<%@page import="com.Fertilizer.bean.GetProductDetails"%>
<%@page import="com.Fertilizer.dao.TaxCreationDao"%>
<%@page import="com.Fertilizer.hibernate.TaxCreationBean"%>
<%@page import="com.Fertilizer.dao.CategoryDetailsDao"%>
<%@page import="com.Fertilizer.dao.ProductDetailsDao"%>
<%@page import="com.Fertilizer.dao.SupplierDetailsDao"%>
<%@page import="com.Fertilizer.hibernate.SupplierDetailsBean"%>
<%@page import="com.Fertilizer.hibernate.CategoryDetailsBean"%>
<%@page import="com.Fertilizer.dao.MeasuringUnitsDao"%>
<%@page import="com.Fertilizer.hibernate.MeasuringUnitsBean"%>
<%@page import="java.util.List"%>

<%boolean isHome = false;%>

<%@include file="commons/header.jsp"%>

<head>
<script src="/AgriSoft/staticContent/js/vatpercentage.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/productDetail.js"></script>

<script type="text/javascript">
          		function checkForDuplicateProductEntry(){
          			<%
          			ProductDetailsDao dao = new ProductDetailsDao();
          			List list = dao.getProductDetails();
          			%>
          			
          			<%
          			int z = 0;
          			for(z=0;z<list.size();z++){
          			GetProductDetails bean = (GetProductDetails)list.get(z);
          			%>
          			var pro = "<%=bean.getProduct()%>";
          			var company = "<%=bean.getManufacturer()%>";
          			var weight = "<%=bean.getWeight()%>";
          			if(document.getElementById("productName").value ==pro && document.getElementById("manufacturingCompany").value ==company && document.getElementById("weight").value ==weight ){
          				alert("Product Already Exist...Duplicate Not allowed");
          				location.reload();
          				return false;
          			}
          			<%
          			}
          			%>
          			}
</script>

<script type="text/javascript">
          		 function productlist()
          		 {
          		 window.location = "productList.jsp";
          		 }
          		 function editProduct() {
          			 window.location = "editProductDetails.jsp";
				}
          		</script>

</head>

<div class="row header_margin_top">
	<div align="center">
		<h2 class="form-name style_heading">
			<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("productDetails") %>
			<%}%>
			<%if(abc.equals("english")){%>Product Details<%}%>
		</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-offset-1 col-md-10">
		<hr style="border-top-color: #c1b1b1;">
	</div>
</div>

<div class="container col-sm-offset-1">
	<form class="form-horizontal" method="post" action="" name="prd">
		<!-- Value of 'name' attribute is used in  productDetail.js  -->
		<fieldset>
			<div class="row form-group">
				<div class="col-md-6">
					<%@include file="commons/clock.jsp"%>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-md-2 control-label" for="fk_cat_id">
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
							   CategoryDetailsDao cdd = new CategoryDetailsDao();
           						List cList =cdd.getAllMainCat();
							
							%>
						<input list="cat_drop" id="fk_cat_id" class="form-control">
						<datalist id="cat_drop">
							<%
					           for(int i=0;i<cList.size();i++){
					        	   CategoryDetailsBean cat=(CategoryDetailsBean)cList.get(i);
							%>

							<option data-value="<%=cat.getCatId()%>"
								value="<%=cat.getCategoryName()%>">
								<%
				      			}
				    		%>
							
						</datalist>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="productName">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("productName") %>
					<%}%> <%if(abc.equals("english")){%>Product Name<%}%> <sup>*</sup>
				</label>
			
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>
						<%
							ProductDetailsDao productdao = new ProductDetailsDao();
           					List productList =productdao.getAllProductDetails();
							
						%>
						<input list="productName_drop" id="productName"
							class="form-control" onblur="checkForDuplicateProductEntry()"
							style="text-transform: uppercase">
						<datalist id="productName_drop">
							<%
					           for(int i=0;i<productList.size();i++){
					        	   ProductDetailsBean productbean =(ProductDetailsBean)productList.get(i);
							%>

							<option data-value="<%=productbean.getProdctId()%>"
								value="<%=productbean.getProductName()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>

				<label class="col-md-2 control-label" for="manufacturingCompany">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("company") %>
					<%}%> <%if(abc.equals("english")){%>Manufacturing Company<sup>*</sup><%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>
						<%
							ProductDetailsDao productdao1 = new ProductDetailsDao();
           						List productList1 =productdao1.getAllProductDetails();
							
							%>
						<input list="manufacturingCompany_drop" id="manufacturingCompany"
							style="text-transform: uppercase" class="form-control"
							onblur="checkForDuplicateProductEntry()">
						<datalist id="manufacturingCompany_drop">
							<%
					           for(int i=0;i<productList1.size();i++){
					        	   ProductDetailsBean productbean1 =(ProductDetailsBean)productList1.get(i);
							%>
							<option data-value="<%=productbean1.getProdctId()%>"
								value="<%=productbean1.getManufacturingCompany()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="weight">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("weight") %>
					<%}%> <%if(abc.equals("english")){%>Packing<%}%><sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> No </span>
						<%
							ProductDetailsDao productdao2 = new ProductDetailsDao();
           						List productList2 =productdao2.getAllProductDetails();
							
							%>
						<input list="weight_drop" id="weight" class="form-control"
							onblur="checkForDuplicateProductEntry()">
						<datalist id="weight_drop">
							<%
					           for(int i=0;i<productList2.size();i++){
					        	   ProductDetailsBean productbean2 =(ProductDetailsBean)productList2.get(i);
							%>

							<option data-value="<%=productbean2.getProdctId()%>"
								value="<%=productbean2.getWeight()%>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>

				<label class="col-md-2 control-label" for="Unit">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("unit") %>
					<%}%> <%if(abc.equals("english")){%>Unit<sup>*</sup>
					<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<!-- Following code is to get all measuring units from "sold_units" table of "fertilizer" DB -->
						<!-- getAllUnits() is implemented in  MeasuringUnitsDao with return type List-->

						<%
							MeasuringUnitsDao mdao = new MeasuringUnitsDao();
           						List uList = mdao.getAllUnits();
							%>

						<input list="unit_drop" id="fk_unit_id" class="form-control">
						<span class="input-group-addon"> 
					<button  type="button" onclick="unitDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								</span>
						<datalist id="unit_drop">
							<%
					           for(int i=0;i<uList.size();i++){
					        	   MeasuringUnitsBean bean =(MeasuringUnitsBean)uList.get(i);
							%>
							<option data-value="<%=bean.getPkUnitId()%>"
								value="<%=bean.getUnitName() %>">
								<%
				      			}
				    		%>
						</datalist>
					</div>
				</div>
			</div>

			<!-- Following code is used to show tax percentage in % field with the help of  "myid" and "taxPercentage"-->
			<script type="text/javascript">
				function ChooseContact(data)
					{
						
						document.getElementById("taxPercentage").value=(data.options[data.selectedIndex].getAttribute("myid"));
					}
			</script>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="fk_tax_id">Tax
					Type<sup>*</sup>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="	glyphicon glyphicon-hand-right"></i>
						</span>

						<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
						<!-- getAllMainTax() is implemented in  TaxCreationDao with return type List-->


						<%
							TaxCreationDao tcd = new TaxCreationDao();
							List tList = tcd.getAllMainTax();
		            	%>
						<select class="form-control input-md" autofocus name="fk_tax_id"
							id="fk_tax_id" onChange="ChooseContact(this)"> 
							<option value="selected">--select value--</option>
							
							
							<%
					           for(int i=0;i<tList.size();i++){
					        	   TaxCreationBean tax=(TaxCreationBean)tList.get(i);
							%>

							<option value="<%=tax.getTxId()%>"
								myid="<%=tax.getTaxPercentage()%>"><%=tax.getTaxType() %>
							</option>
							<%
				      			}
				    		%>

						</select><span class="input-group-addon"> 
					<button  type="button" onclick="taxDetailsPopup()"><img src="/AgriSoft/staticContent/images/add.jpg" width="16" heigth="13"></button>
								</span>
					</div>
				</div>

				<div class="col-md-offset-2 col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> % </span> <input
							id="taxPercentage" name="taxPercentage"
							placeholder="Tax Percentage" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="buyPrice">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("buyPrice") %>
					<%}%> <%if(abc.equals("english")){%>Buy Price<%}%>
				</label>
				
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="buyPrice"
							name="buyPrice" placeholder="Buy Price"
							class="form-control input-md" type="text">
					</div>
				</div>

				<label class="col-md-2 control-label" for="mrp">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("") %>
					<%}%> <%if(abc.equals("english")){%>M.R.P<%}%>
				</label>
				
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="mrp"
							name="mrp" placeholder="MRP" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="salePrice">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("salePrice") %>
					<%}%> <%if(abc.equals("english")){%>Sale Price<%}%>
				</label>
				
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="salePrice"
							name="salePrice" placeholder="Sale Price"
							class="form-control input-md" type="text"
							onchange="salePriceCompaireWithMRP()">
					</div>
				</div>
				
				<label class="col-md-3 control-label" for="creditSalePrice">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("creditSalePrice") %>
					<%}%> <%if(abc.equals("english")){%>Sale Price For Credit Customer<%}%>
				</label>
				<div class="col-md-2">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input
							id="creditSalePrice" name="creditSalePrice"
							placeholder="credit Sale Price" class="form-control input-md"
							type="text" onchange="creditSalePriceCompaireWithMRP()">
					</div>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="hsn">
					<%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("salePrice") %>
					<%}%> <%if(abc.equals("english")){%>HSN<%}%>
				</label>
				<div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon"> Rs </span> <input id="hsn"
							name="hsn" placeholder="HSN Number" class="form-control input-md"
							type="text">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-11 text-center">
					<input style="height: 65px; width: 180; font-size: 25"
						type="button" id="save" name="btn" value="Submit"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="productDetails()" /> <input
						style="height: 65px; width: 180; font-size: 25"
						class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width"
						type="reset" id="save" name="btn" value="Cancel" onclick="window.location.reload()" />
					<input style="height: 65px; width: 180; font-size: 25;background: #ffb100;"
						type="button" value="List" id="listBtn"
						class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width" onclick="productlist()" /> <input
						style="height: 65px; width: 180; font-size: 25;background: #ffb100;" type="button"
						value="Edit" id="listBtn" class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"
						onclick="editProduct()" />
				</div>
			</div>
		</fieldset>
	</form>
</div>
<script>

function unitDetailsPopup() {
	 var myWindow = window.open("/AgriSoft/jsp/PopupUnitDetails.jsp", "_blank","toolbar=no,scrollbars=no,resizable=no,top=150,left=400,width=600,height=250");
}

function taxDetailsPopup() {
	 var myWindow = window.open("/AgriSoft/jsp/PopupTaxDetails.jsp", "_blank", "toolbar=no,scrollbars=no,resizable=no,top=150,left=400,width=600,height=250");
}
</script>

<%@include file="commons/newFooter.jsp"%>
