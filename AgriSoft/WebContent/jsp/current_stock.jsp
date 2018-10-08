			<%@page import="java.util.List"%>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<% boolean isHome=false;%>
			<%@include file="commons/header.jsp"%>
			
			<head>
			<meta name="viewport" content="width=device-width,initial-scale=1">
			<script src="/AgriSoft/staticContent/js/currentStock.js"></script>
			<script type="text/javascript"
				src="/AgriSoft/staticContent/js/jquery-1.12.3.min.js"></script>
			<link rel="stylesheet"
				href="/AgriSoft/staticContent/css/jquery-ui.min.css">
			<link rel="stylesheet"
				href="/AgriSoft/staticContent/css/ui.jqgrid.min.css">
			<link rel="stylesheet" href="/AgriSoft/staticContent/css/ui.jqgrid.css">
			<script type="text/javascript"
				src="/AgriSoft/staticContent/js/jquery.min.js"></script>
			<script type="text/javascript"
				src="/AgriSoft/staticContent/js/jquery.jqgrid.min.js"></script>
			</head>
			<div class="container">
				<h2 class="form-name">Current Stock</h2>
			</div>
			
			<input class="btn btn-success" type="button" value="Search"
				onclick="getAllCurrentStock();">
			
			<div style="position: absolute; top: 100px; left: 150px">
				<p id="pojo"></p>
				<table id="list4" class="scroll" cellpadding="0" cellspacing="0"></table>
			</div>
