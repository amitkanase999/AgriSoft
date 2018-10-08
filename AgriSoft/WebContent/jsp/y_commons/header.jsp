<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script src="/AgriSoft/staticContent/js/logout.js"></script>
<% String  contextPath=request.getContextPath(); %>
<% String path=""; if(isHome)path="jsp\\"; %>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width , initial-scale=1">
 		<title>Embel Technologies Pvt Ltd.</title>
		<script type="text/javascript" src="/Fertilizer/staticContent/js/jquery-1.12.3.min.js"></script>
		<script type="text/javascript"  src="/Fertilizer/staticContent/js/bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="/Fertilizer/staticContent/css/bootstrap.min.css">
 		<link rel="stylesheet" type="text/css" href="/Fertilizer/staticContent/y_css/style.css" />
  		 
  		 <!----------------------- Offline glyphicons used for footer.jsp-------------------------------------------------------- -->
  		 
<link href="/Fertilizer/staticContent/css/bootstrap.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap.min.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap-theme.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- Font awesome (social icon) -->
<link href="/Fertilizer/staticContent/css/font-awesome.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.js"> </script>
<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.min.js"> </script>
<script type="text/javascript" src="/Fertilizer/staticContent/js/tooltip.js"> </script>
<script type="text/javascript" src="/Fertilizer/staticContent/js/jquery.min.js"> </script>

<link href="/Fertilizer/staticContent/fonts/glyphicons-halflings-regular.eot" >
<link href="/Fertilizer/staticContent/fonts/glyphicons-halflings-regular.svg" >
<link href="/Fertilizer/staticContent/fonts/glyphicons-halflings-regular.ttf" >
<link href="/Fertilizer/staticContent/fonts/glyphicons-halflings-regular.woff" >
<link href="/Fertilizer/staticContent/fonts/glyphicons-halflings-regular.woff2" >

<!-- Font awesome (social icon) -->
<link href="/Fertilizer/staticContent/fonts/fontawesome-webfont.eot" >
<link href="/Fertilizer/staticContent/fonts/fontawesome-webfont.svg" >
<link href="/Fertilizer/staticContent/fonts/fontawesome-webfont.ttf" >
<link href="/Fertilizer/staticContent/fonts/fontawesome-webfont.woff" >
<link href="/Fertilizer/staticContent/fonts/fontawesome-webfont.woff2" >
<link href="/Fertilizer/staticContent/fonts/FontAwesome.otf" >

		<!------------------------------------------ Shortcut.jsp styles -------------------------------------------------->

<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.js"> </script>
<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.min.js"> </script>
<link href="/Fertilizer/staticContent/css/bootstrap.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap.min.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap-theme.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap-theme.min.css" rel="stylesheet">
 

 <!-------------------------------- header style -------------------------------------------------------------------------------------------------------->	
 
<!-- <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> -->
<script src="/Fertilizer/staticContent/y_js/prefixfree.min.js"></script>
<script src="/Fertilizer/staticContent/y_js/modernizr.min.js"></script>
<style>
@import url(http://fonts.googleapis.com/css?family=roboto:400,400italic,600,700,800);

*,
html,
body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
pre,
form,
label,
fieldset,
input,
p,
blockquote,
th,
td {
  margin: 0;
  padding: 0;
}

article,
aside,
figure,
footer,
header,
hgroup,
nav,
section { display: block; }

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

html { -webkit-font-smoothing: antialiased; }

a {
  color: #BA0707;
  text-decoration: none;
}

a:hover { color: #BA0707; }

body {
  
  color: #E95546;
  font: 14px "roboto", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  line-height: 1;
  width: 100%;
}

nav {
  display: block;
  background:#8c7674
}

.menu { display: block; }

.menu li {
  display: inline-block;
  position: relative;
  z-index: 100;
}

.menu li:first-child { margin-left: 0; }

.menu li a {
  font-weight: 600;
  text-decoration: none;
  padding: 20px 15px;
  display: block;
  color: #fff;
  transition: all 0.2s ease-in-out 0s;
}

.menu li a:hover,
.menu li:hover>a {
  color: #fff;
  background: cadetblue;
}

.menu ul {
  visibility: hidden;
  opacity: 0;
  margin: 0;
  padding: 0;
  width: 170px;
  position: absolute;
  left: 0px;
  background: #fff;
  z-index: 99;
  transform: translate(0, 20px);
  transition: all 0.2s ease-out;
}

.menu ul:after {
  bottom: 100%;
  left: 20%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  border-color: rgba(255, 255, 255, 0);
  border-bottom-color: #fff;
  border-width: 6px;
  margin-left: -6px;
}

.menu ul li {
  display: block;
  float: none;
  background: none;
  margin: 0;
  padding: 0;
}

.menu ul li a {
  font-size: 15px;
  font-weight: bold;
  display: block;
  color: #797979;
  background: #fff;
}

.menu ul li a:hover,
.menu ul li:hover>a {
  background: #FC6D58;
  color: #fff;
}

.menu li:hover>ul {
  visibility: visible;
  opacity: 1;
  transform: translate(0, 0);
}

.menu ul ul {
  left: 169px;
  top: 0px;
  visibility: hidden;
  opacity: 0;
  transform: translate(20px, 20px);
  transition: all 0.2s ease-out;
}

.menu ul ul:after {
  left: -6px;
  top: 10%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  border-color: rgba(255, 255, 255, 0);
  border-right-color: #fff;
  border-width: 6px;
  margin-top: -6px;
}

.menu li>ul ul:hover {
  visibility: visible;
  opacity: 1;
  transform: translate(0, 0);
}

.responsive-menu {
  display: none;
  width: 100%;
  padding: 20px 15px;
  background: #E95546;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
}

.responsive-menu:hover {
  background: #E95546;
  color: #fff;
  text-decoration: none;
}

a.homer { background: #ab8da0; }
 @media (min-width: 768px) and (max-width: 979px) {

.mainWrap { width: 768px; }

.menu ul { top: 37px; }

.menu li a { font-size: 12px; }

a.homer { background: #E95546; }
}
 @media (max-width: 767px) {

.mainWrap {
  width: auto;
  padding: 50px 20px;
}

.menu { display: none; }

.responsive-menu { display: block; }

nav {
  margin: 0;
  background: none;
}

.menu li {
  display: block;
  margin: 0;
}

.menu li a {
  background: #fff;
  color: #797979;
}

.menu li a:hover,
.menu li:hover>a {
  background: #8c7674;
  color: #fff;
}

.menu ul {
  visibility: hidden;
  opacity: 0;
  top: 0;
  left: 0;
  width: 100%;
  transform: initial;
}

.menu li:hover>ul {
  visibility: visible;
  opacity: 1;
  position: relative;
  transform: initial;
}

.menu ul ul {
  left: 0;
  transform: initial;
}

.menu li>ul ul:hover { transform: initial; }
}
</style>
</head>

<body>
<nav class="navbar navbar-fixed-top"> 
<a id="resp-menu" class="responsive-menu" href="#"><i class="fa fa-reorder"></i> Menu</a>
  <ul class="menu">
    <li><a class="homer" href="http://localhost:8080/Fertilizer/jsp/allBilling.jsp"><i class="fa fa-home"></i> HOME</a>
     
    </li>
    <li><a href="<%=path%>allBilling.jsp" accesskey="b" ><i class="fa fa-sitemap"></i> Billing</a>
      <ul class="sub-menu">
        <li><a href="<%=path%>seedAndPestiBill.jsp">SeedAndPesticide Bill</a> </li>
        <li><a href="<%=path%>salereturn.jsp"  >Sale Return</a></li>
        <li><a href="<%=path%>customeradvancebooking.jsp">Customer Advance Booking</a></li>
        <li><a href="<%=path%>quatation.jsp">Quotation</a></li>
        
      </ul>
    </li>
   
 
    <li><a  href="<%=path%>allPayments.jsp" accesskey="c"><i class="fa fa-edit"></i> CashBook</a>
      
    </li>
    
   		<li><a  href="#"><i class="fa fa-shopping-cart"></i> Account</a>
      		<ul class="sub-menu">
           
						<li><a href="<%=path%>supplierAccountDetails.jsp"  accesskey="n">Supplier Account Details</a></li>
			 </ul>
        </li>
        
     <li><a  href="#"><i class="fa fa-user-secret"></i> Purchase</a>
     			 <ul class="sub-menu">
        						<li><a href="<%=path%>purchaseOrderDetails.jsp"  accesskey="n">Purchase Order </a></li>
								<li><a href="<%=path%>updateDCNumber.jsp"  >Update DC Number </a></li>
								<li><a href="<%=path%>goodsReceive.jsp"  >Goods Receive </a></li>	
								<li><a href="<%=path%>purchase_return.jsp" >Purchase return</a></li>	
							</ul>
		</li>
        
      
        
     
        
    
    
    <li><a  href="#"><i class="	fa fa-address-book-o"></i> HR</a>
      						<ul class="sub-menu">
        						<li><a href="<%=path%>employee_detail.jsp" accesskey="w" >Employee Details</a></li>
								<li><a href="<%=path%>customer_detail.jsp" accesskey="t">Customer Details</a></li>
								<li><a href="<%=path%>categoryDetails.jsp" accesskey="y" >Category Details</a></li>	
								<li><a href="<%=path%>product_detail.jsp" accesskey="q">Product Details</a></li>
								<li><a href="<%=path%>supplierdetails.jsp" accesskey="r">Supplier Details</a></li>
								<li><a href="<%=path%>tax.jsp">Tax Creation</a></li>
								<li><a href="<%=path%>measuringUnits.jsp">Measuring Units</a></li>
								<li><a href="<%=path%>userDetails.jsp">User Details</a></li>
							</ul>
        
    </li>
    
 
   <li><a  href="#"><i class="fa fa-bar-chart"></i> Reports</a>
      <ul class="sub-menu">
        						<li><a href="#">Purchase</a>
				  						<ul>
							
														<li><a href="#">Daywise Purchase Report</a></li>
														<li><a href="#">Purchase Report Between Two Days</a></li>
														<li><a href="#">Purchase Report Between Two Years</a></li>
														<li><a href="#">Billwise Purchase Report</a></li>
														
								
				 						 </ul>
								</li>
								
								<li><a href="#">Sale</a>
				  						<ul>
							
														<li><a href="#">Daywise Sale Report</a></li>
														<li><a href="#">Purchase Report Between Two Days</a></li>
														<li><a href="#">Purchase Report Between Two Years</a></li>
														<li><a href="#">Billwise Purchase Report</a></li>
														
								
				 						 </ul>
								</li>
									
							
      </ul>
    </li>
    
      	 <li><a  href="#"><i class="	fa fa-address-book-o"></i> Graphs</a>
      						<ul class="sub-menu">
        												<li><a href="#">Purchase</a></li>
														<li><a href="#">Sale</a></li>
														<li><a href="#">Stock</a></li>
														<li><a href="#">Other</a></li>
							</ul>
        
    </li>
      	   
  </ul>
  
</nav>


<script src="/Fertilizer/staticContent/y_js/jquery-2.1.3.min.js"></script> 
<script>
$(document).ready(function(){ 
	var touch 	= $('#resp-menu');
	var menu 	= $('.menu');
 
	$(touch).on('click', function(e) {
		e.preventDefault();
		menu.slideToggle();
	});
	
	$(window).resize(function(){
		var w = $(window).width();
		if(w > 767 && menu.is(':hidden')) {
			menu.removeAttr('style');
		}
	});
	
});
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<!-- header end -->
 