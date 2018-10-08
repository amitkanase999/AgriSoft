<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>
<%@include file="commons/DashboardHeader.jsp"%>

<%@page import="org.hibernate.Session"%>
<%@page import="com.Fertilizer.utility.HibernateUtility"%>
<%@page import="com.Fertilizer.hibernate.UserDetailsBean"%>

<!DOCTYPE html>
<html lang="en">



                <div class="row">
                    <div class="col-lg-12	 col-sm-6 col-xs-12">
             
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
            </div>

            <footer class="footer text-center"> 2017 &copy; Embel Admin brought to you by wrappixel.com </footer>
        </div>
        <!-- ============================================================== -->
        <!-- End Page Content -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="/AgriSoft/staticContent/dashboard/html/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="/AgriSoft/staticContent/dashboard/html/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="/AgriSoft/staticContent/dashboard/html/js/waves.js"></script>
    <!--Counter js -->
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/counterup/jquery.counterup.min.js"></script>
    <!-- chartist chart -->
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/chartist-js/dist/chartist.min.js"></script>
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
    <!-- Sparkline chart JavaScript -->
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="/AgriSoft/staticContent/dashboard/html/js/custom.min.js"></script>
    <script src="/AgriSoft/staticContent/dashboard/html/js/dashboard1.js"></script>
    <script src="/AgriSoft/staticContent/dashboard/plugins/bower_components/toast-master/js/jquery.toast.js"></script>
</body>
       
<script>
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	exportEnabled: true,
	animationEnabled: true,
	title:{
		text: "Profit And Loss Report"
	},
	subtitles: [{
		text: "Click Legend to Hide or Unhide Data Series"
	}], 
	axisX: {
		title: "Products"
	},
	axisY: {
		title: "Profit And Loss",
		titleFontColor: "#4F81BC",
		lineColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		tickColor: "#4F81BC"
	},
	axisY2: {
		title: "",
		titleFontColor: "#C0504E",
		lineColor: "#C0504E",
		labelFontColor: "#C0504E",
		tickColor: "#C0504E"
	},
	toolTip: {
		shared: true
	},
	legend: {
		cursor: "pointer",
		itemclick: toggleDataSeries
	},
	data: [{
		type: "column",
		name: "Profit",
		showInLegend: true,      
		yValueFormatString: "#,##0.# Units",
		dataPoints: [
			/* { label: "New Jersey",  y: 19034.5 }, */
			{ label: "chetan", y: 20015 },
			{ label: "Oregon", y: 25342 },
			{ label: "Montana",  y: 20088 }
			/* { label: "Massachusetts",  y: 28234 } */
		]
	},
	{
		type: "column",
		name: "Loss",
		axisYType: "secondary",
		showInLegend: true,
		yValueFormatString: "#,##0.# Units",
		dataPoints: [
			/* { label: "New Jersey", y: 210.5 }, */
			{ label: "Fertilizer", y: 135 },
			{ label: "Seed", y: 45 },
			{ label: "Pesticide", y: 130 }
			/* { label: "Massachusetts", y: 528 } */
		]
	}]
});
chart.render();

function toggleDataSeries(e) {
	if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	} else {
		e.dataSeries.visible = true;
	}
	e.chart.render();
}

}
</script>

</html>
