/*Sale for Fertilizer*/
function getCashSaleAmount(){

	var params= {};

	params["methodName"] = "getCashSaleAmountForDayCloseReport";
	$("#cashSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("cashSale").value =parseFloat(v.saleByCash).toFixed(2);

				});
			})
			getCreditSaleAmount();
}

function getCreditSaleAmount(){

	var params= {};

	params["methodName"] = "getCreditSaleAmountForDayCloseReport";
	$("#creditSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("creditSale").value =parseFloat(v.saleByCredit).toFixed(2);

				});
			})
			getCashSaleAmountForSeed();
}

/*Sale for Seed*/
function getCashSaleAmountForSeed(){

	var params= {};

	params["methodName"] = "getSeedCashSaleAmountForDayCloseReport";

	$("#seedCashSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("seedCashSale").value =parseFloat(v.seedSaleByCash).toFixed(2);

				});
			})
			getCreditSaleAmountForSeed()
}

function getCreditSaleAmountForSeed(){

	var params= {};

	params["methodName"] = "getSeedCreditSaleAmountForDayCloseReport";

	$("#seedCreditSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("seedCreditSale").value =parseFloat(v.seedSaleByCredit).toFixed(2);
				});
			})
			getCashSaleAmountForPesti()
}

/*Sale for Pesticide*/
function getCashSaleAmountForPesti(){

	var params= {};
	params["methodName"] = "getPesticideCashSaleAmountForDayCloseReport";
	$("#pestiCashSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("pestiCashSale").value =parseFloat(v.pesticideSaleByCash).toFixed(2);

				});
			})
			getCreditSaleAmountForPesti();
}

function getCreditSaleAmountForPesti(){

	var params= {};

	params["methodName"] = "getPesticideCreditSaleAmountForDayCloseReport";
	$("#pestiCreditSale").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("pestiCreditSale").value =parseFloat(v.pesticideSaleByCredit).toFixed(2);

				});
			})
}

/*PURCHASE*/
function getTodayPurchaseAmountForFertilizer(){

	var params= {};

	params["methodName"] = "getFertilizerTodayPurchase";
	$("#fertiPurchase").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("fertiPurchase").value =parseFloat(v.fertilizerPurchase).toFixed(2);

				});
			})
}

function getTodayPurchaseAmountForSeed(){

	var params= {};

	params["methodName"] = "getSeedTodayPurchase";
	$("#seedPurchase").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("seedPurchase").value =parseFloat(v.seedPurchase).toFixed(2);

				});
			})
}

function getTodayPurchaseAmountForPesti(){

	var params= {};

	params["methodName"] = "getPestiTodayPurchase";
	$("#pestiPurchase").append($("<input/>").attr("value","").text());
	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		$.each(jsonData,function(i,v)
				{
			document.getElementById("pestiPurchase").value =parseFloat(v.pesticidePurchase).toFixed(2);

				});
			})
}