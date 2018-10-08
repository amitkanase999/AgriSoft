function productDetails()
{

	if(document.prd.fk_cat_id.value != "")
	{
		if(document.prd.productName.value != "")
		{

			var letterNumber = /^[a-zA-Z0-9\\.;,:()%' ]{0,100}$/;
			if(document.prd.productName.value.match(letterNumber))
			{
				if(document.prd.manufacturingCompany.value != "")
				{
					if(document.prd.weight.value!="")
					{
						if(document.prd.fk_unit_id.value!="")
						{
							if(document.prd.fk_tax_id.value!="selected")
							{

								prdctDetails();
							}
							else
							{
								alert("select Tax Type");
								/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
										{
								
										var msg="select Tax Type";
										var dialog = bootbox.dialog({
											//title: "Embel Technologies Says :",
										    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
										    closeButton: false
										});
										
										setTimeout(function() {
											dialog.modal('hide');
										}, 1500);
										
										return false;
										
											});*/
							}

						}
						else
						{
							alert("Please Select Unit.");
							/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
									{
							
									var msg="Please Select Unit.";
									var dialog = bootbox.dialog({
										//title: "Embel Technologies Says :",
									    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
									    closeButton: false
									});
									
									setTimeout(function() {
										dialog.modal('hide');
									}, 1500);
									
									return false;
									
										});*/
						}
					}
					else
					{
						alert("Enter value for Packing");
						/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
								{
						
								var msg="Enter value for Packing";
								var dialog = bootbox.dialog({
									//title: "Embel Technologies Says :",
								    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
								    closeButton: false
								});
								
								setTimeout(function() {
									dialog.modal('hide');
								}, 1500);
								
								return false;
								
									});*/
					}
				}
				else
				{
					alert("Please Enter Manufacturing  Company");
					/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
							{
					
							var msg="Please Enter Manufacturing  Company";
							var dialog = bootbox.dialog({
								//title: "Embel Technologies Says :",
							    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
							    closeButton: false
							});
							
							setTimeout(function() {
								dialog.modal('hide');
							}, 1500);
							
							return false;
							
								});*/
				}
			}
			else
			{
				alert("Enter Alphabets And Numbers Only in Product Name field..!!");
				/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
						{
				
						var msg="Enter Alphabets And Numbers Only in Product Name field..!!";
						var dialog = bootbox.dialog({
							//title: "Embel Technologies Says :",
						    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
						    closeButton: false
						});
						
						setTimeout(function() {
							dialog.modal('hide');
						}, 1500);
						
						return false;
						
							});*/
			}
		}
		else
		{
			
				alert("Enter Product Name");
				/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
		
				var msg="Enter Product Name";
				var dialog = bootbox.dialog({
					//title: "Embel Technologies Says :",
				    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
				    closeButton: false
				});
				
				setTimeout(function() {
					dialog.modal('hide');
				}, 1500);
				
				return false;
				
					});*/
			
		}
	}	
	else
	{
		alert("Please select Product Category");
		/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg="Please select Product Category";
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s1.jpg" height="50" width="50"/></p>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
			}, 1500);
			
			return false;
			
				});*/
	}

} 

function prdctDetails(){

	document.prd.btn.disabled = true;

	var productName= $('#productName').val();
	var manufacturingCompany= $('#manufacturingCompany').val();
	var weight=$('#weight').val();
	var buyPrice=$('#buyPrice').val();
	var salePrice=$('#salePrice').val();
	var creditSalePrice=$('#creditSalePrice').val();
	var taxpercentage = $('#taxPercentage').val();
	var mrp = $('#mrp').val();
	var fk_tax_id = $('#fk_tax_id').val();
	var hsn = $('#hsn').val();
	var params = {};

	var input1 = document.getElementById('fk_cat_id'),
	list = document.getElementById('cat_drop'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}
	var input3 = document.getElementById('fk_unit_id'),
	list3 = document.getElementById('unit_drop'),
	i,fk_unit_id;
	for (i = 0; i < list3.options.length; ++i) {
		if (list3.options[i].value === input3.value) {
			fk_unit_id = list3.options[i].getAttribute('data-value');
		}
	}

	if(weight==undefined || weight== null || weight == "" ){
		weight="0";
	}
	if(buyPrice==undefined || buyPrice== null || buyPrice == "" ){
		buyPrice="0";
	}
	if(salePrice==undefined || salePrice== null || salePrice == "" ){
		salePrice="0";
	}

	if(creditSalePrice==undefined || creditSalePrice== null || creditSalePrice == "" ){
		creditSalePrice="0";
	}

	if(taxpercentage==undefined || taxpercentage== null || taxpercentage == "" ){
		taxpercentage="0.0";
	}

	if(mrp==undefined || mrp== null || mrp == "" ){
		mrp="0";
	}
	if(hsn==undefined || hsn== null || hsn == "" ){
		hsn="0";
	}
	if(manufacturingCompany==undefined || manufacturingCompany== null || manufacturingCompany == "" ){
		manufacturingCompany="0";
	}

	params["fkTaxId"] = fk_tax_id;
	params["taxPercentage"] = taxpercentage;
	params["mrp"] = mrp;
	params["fk_cat_id"] = fk_cat_id;
	params["productName"] =productName.toUpperCase();
	params["manufacturingCompany"] = manufacturingCompany.toUpperCase();
	params["weight"] =weight;
	params["fk_unit_id"] = fk_unit_id;
	params["buyPrice"] = buyPrice;
	params["salePrice"] = salePrice;
	params["creditSalePrice"] = creditSalePrice;
	params["hsn"] = hsn;
	params["methodName"] ="proDetails";
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
			
			alert(data);
			location.reload();
			document.prd.save.disabled = false;
		
		/*$.getScript('/AgriSoft/staticContent/js/bootbox.min.js', function() 
				{
	
			var msg=data;
			var dialog = bootbox.dialog({
				//title: "Embel Technologies Says :",
			    message: '<p class="text-center">'+msg.fontcolor("red").fontsize(5)+'<img src="/AgriSoft/staticContent/images/s2.gif" height="50" width="50"/></p>',
			    closeButton: false
			});
			
			setTimeout(function() {
				dialog.modal('hide');
				location.reload();
				document.prd.save.disabled = false;
			}, 1500);
			
			return false;
			
				});*/
			}
	).error(function(jqXHR, textStatus, errorThrown){
		if(textStatus==="timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});

}
