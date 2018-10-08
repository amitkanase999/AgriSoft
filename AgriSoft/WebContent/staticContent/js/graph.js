	  
function stockHelper()
{
	var offerList="";
	this.graph = graph;
	this.month = month;
	this.day = day;
	
	
	function day()
	{
		var params= {};
		
		params["methodName"] = "getAllDay";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('container', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Daily Fertilizer Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			          
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	
	
	
	function graph()
	{
		var params= {};
		
		params["methodName"] = "getAllWeek";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('container', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Weekly Fertilizer Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	function month()
	{
		var params= {};
		
		params["methodName"] = "getAllMonth";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('container', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Monthly Fertilizer Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}


	
}

																																																																																																																																																																																																																																																																																																																																																																																																																																																
var stock = new stockHelper();

function graphForSeed(){

	var offerList="";
	this.graph = graph;
	this.month = month;
	this.day = day;
	
	
	function day()
	{
		var params= {};
		
		params["methodName"] = "getAllDayForSeed";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForSeed', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Daily Seed Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			          
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	
	
	
	function graph()
	{
		var params= {};
		
		params["methodName"] = "getAllWeekForSeed";

		$.post('/Fertilizer/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForSeed', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Weekly Seed Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	function month()
	{
		var params= {};
		
		params["methodName"] = "getAllMonthForSeed";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForSeed', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Monthly Seed Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}

}
var seedGraph = new graphForSeed();


function graphForPesticide(){


	var offerList="";
	this.graphForPesti = graphForPesti;
	this.monthForPesti = monthForPesti;
	this.dayForPesti = dayForPesti;
	
	
	function dayForPesti()
	{
		var params= {};
		
		params["methodName"] = "getAllDayForPesti";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForPesti', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Daily Pesticide Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			          
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	
	
	
	function graphForPesti()
	{
		var params= {};
		
		params["methodName"] = "getAllWeekForPesti";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForPesti', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Weekly Pesticide Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}
	
	
	function monthForPesti()
	{
		var params= {};
		
		params["methodName"] = "getAllMonthForPesti";

		$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
				{
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			var arr = $.map(catmap, function(el) { return el.week });
			var sss = $.map(catmap, function(el) { return el.price });
			var ddd = $.map(catmap, function(el) { return el.weekDays });
			
			
			$(function () {
			    Highcharts.chart('containerForPesti', {
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'Monthly Pesticide Sale'
			        },
			        subtitle: {
			            text: 'Source: embel.co.in'
			        },
			        xAxis: {
			        	categories: ddd
			            /*categories: [
			                'MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN','MON-SUN'
			            ],
			            crosshair: true*/
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'Price (Rs)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} Rs</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            
			            name: 'Price',
			            data: sss

			       

			        }]
			    });
			});
			
				});
	}

}
var pestiGraph = new graphForPesticide();