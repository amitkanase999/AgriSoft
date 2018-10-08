/**
 * 
 */var app=anguler.module("myModule",[]).controller("myController",function($scope,$http){
	 params["methodName"] = "getSaleDetailsAsPerCategoryForSingleDate";
		$.post('/AgriSoft/jsp/utility/controller.jsp/getTodaySaleTotalAmount1')
	.then(function (response){
		
		$scope.Amounts=response.data;
		
		
	});
});