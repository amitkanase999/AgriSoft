function normalCustFertilzerBillCOPY() {
	// document.genIn.btn.disabled = true;
	/*var input = document.getElementById('BillNo'), list = document
	.getElementById('seedBillNo'), i, billNo;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			billNo = list.options[i].getAttribute('data-value');
		}
	}*/
	
	var billnumber=$('#BillNo').val();
	var billNo=billnumber.split(",")[0];
	var custname=billnumber.split(",")[1];
	
	var custtype=$('#custtype').val();
	var paycusttype=$('#paycusttype').val();
	
	
	var params = {};
	params["billNo"] = billNo;
	params["custname"]=custname;
	params["custtype"] = custtype;
	params["paycusttype"] = paycusttype;
	
	params["methodName"] = "NormalCustFertilizerBillCOPY";

	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {

		location.reload(true);
		window.open("COPY_Fertilizer_normalCustomerBillPdf.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function creditCustFertilzerBillCOPY() {
	var input = document.getElementById('CreditBillNo'), list = document
	.getElementById('creditCustBillNo'), i, billNo;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			billNo = list.options[i].getAttribute('data-value');
		}
	}
	var params = {};
	params["creditCustbillNo"] = billNo;

	params["methodName"] = "creditCustFertilzerBillCOPY";

	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {
		location.reload(true);
		window.open("COPY_Fertilizer_Credit_CustomerBillPdf.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function billGeneration() {
/*	var input = document.getElementById('BillNo'), list = document
	.getElementById('seedBillNo'), i, billNo;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			billNo = list.options[i].getAttribute('data-value');
		}
	}*/
	
	var Billnumber=$('#BillNo').val();
	var billNo=Billnumber.split(",")[0];
	var custname=Billnumber.split(",")[1];
	
	var custtype=$('#custtype').val();
	var paycusttype=$('#paycusttype').val();
	var params = {};
	params["billNo"] = billNo;
	params["custtype"] = custtype;
	params["paycusttype"] = paycusttype;
	params["methodName"] = "billGeneration";
	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {
		location.reload(true);
		window.open("COPY_SeedPesticide_normalCustomerBillPdf.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}


function billGenerationForNormalPesti() {
/*	var input = document.getElementById('BillNo'), list = document
	.getElementById('seedBillNo'), i, billNo;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			billNo = list.options[i].getAttribute('data-value');
		}
	}*/
	
	var Billnumber=$('#BillNum').val();
	var billNo=Billnumber.split(",")[0];
	var custname=Billnumber.split(",")[1];
	
	var custtype=$('#custtype').val();
	var paycusttype=$('#paycusttype').val();
	
	var params = {};
	params["billNo"] = billNo;
	params["custtype"] = custtype;
	params["paycusttype"] = paycusttype;
	
	params["methodName"] = "billGeneration";

	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {
		location.reload(true);
		window.open("COPY_Pesticide_normal_customer_PDF.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}


function CreditCustBillGeneration() {

	var CreditBillNo=$('#CreditBillNo').val();

	var params = {};
	params["CreditBillNo"] = CreditBillNo;

	params["methodName"] = "CreditCustmerBillCOPY";

	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {
		location.reload(true);
		window.open("COPY_SeddPesticide_Credit_CustomerBillPdf.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function CreditCustBillGenerationForPesticide() {
	
	var input = document.getElementById('CreditBillNo'), list = document
	.getElementById('creditCustBillNo'), i, billNo;

	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			billNo = list.options[i].getAttribute('data-value');
		}
	}
	var params = {};
	params["billNo"] = billNo;
	params["methodName"] = "CreditCustmerBillCOPY";
	$.post('/AgriSoft/jsp/utility/controller.jsp', params,
			function(data) {
		location.reload(true);
		window.open("COPY_Pesticide_Credit_cust_Bill_PDF.jsp");
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});
}

function getbillno()
{
	/*var input1 = document.getElementById('fk_cat_id'),
	list = document.getElementById('cat_drop'),
	i,fk_cat_id;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input1.value) {
			fk_cat_id = list.options[i].getAttribute('data-value');
		}
	}*/
	var ptype=$('#paycusttype').val();
	var custtype=$('#custtype').val();

	//var fk_cat_id = fk_cat_id;
	$("#BillNo").empty();
	$("#BillNo").append($("<option></option>").attr("value","").text("Select billno"));
	var params= {};

	params["ptype"]= ptype;
	params["custtype"]= custtype;
	params["methodName"] = "getAllBillNoforFertilizer";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#ferti_bill_drop").append($("<option></option>").attr("value",(v.billno + ","+v.custname))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function getbillnoForSeed()
{

	var ptype=$('#paycusttype').val();
	var custtype=$('#custtype').val();

	//var fk_cat_id = fk_cat_id;
	$("#BillNo").empty();
	$("#BillNo").append($("<option></option>").attr("value","").text("Select billno"));
	var params= {};

	params["ptype"]= ptype;
	params["custtype"]= custtype;
	params["methodName"] = "getAllBillNoforSeed";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#seedBillNo_drop").append($("<option></option>").attr("value",(v.billno + ","+v.custname))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}

function getbillnoForPesticide()
{

	var ptype=$('#paycusttype').val();
	var custtype=$('#custtype').val();

	//var fk_cat_id = fk_cat_id;
	$("#BillNum").empty();
	$("#BillNum").append($("<option></option>").attr("value","").text("Select billno"));
	var params= {};

	params["ptype"]= ptype;
	params["custtype"]= custtype;
	params["methodName"] = "getAllBillNoforPesticide";
	
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{ var count = 1;

			var jsonData = $.parseJSON(data);
			$.each(jsonData,function(i,v)
					{
				$("#PestiBillNo_drop").append($("<option></option>").attr("value",(v.billno + ","+v.custname))); 
				count++;
					});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
}