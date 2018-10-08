<% boolean isHome=false;%>

<%@include file="y_commons/header.jsp"%>

<body class="master_form_img">
	<div class="row header_margin_top">
		<div align="center">
			<h2 class="form-name style_heading">Sale Return</h2>
		</div>
	</div>

	<div class="row">
		<div class="form-group" align="right">
			<div class="col-sm-offset-6 col-md-5 control-label">
				<div id="date">
					<label id="demo"></label>
					<script>
							   var date = new Date();
							   document.getElementById("demo").innerHTML = date.toDateString();
							</script>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-offset-1 col-md-10">
			<hr style="border-top-color: #c1b1b1;">
		</div>
	</div>

	<form class="form-horizontal" method="post" action="">
		<div class="row form-group">
			<div class="col-sm-2 col-sm-offset-3" align="center">
				<label class="control-label" for="billNo">Bill No</label>
			</div>
			<div class="col-sm-3">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-hand-right"></i>
					</span> <input id="billNo" name="billNo" placeholder=""
						class="form-control input-md" type="text" required />
				</div>
			</div>
		</div>

		<div class="row form-group buttons_margin_top ">
			<div align="center">
				<input type="button" value="Submit" id="save" name="save"
					class="btn btn-lg btn-success btn-md button_hw button_margin_right" />
				<input type="reset" value="Cancel"
					class="btn btn-lg btn-danger btn-md button_hw button_margin_right" />
			</div>
		</div>
	</form>

	<div class="row margin_shortcut">
		<div class="col-sm-12">
			<%@include file="y_commons/footer.jsp"%>
		</div>
	</div>
	<div class="row footer_margin_top" align="center">
		<%@include file="y_commons/shortcut.jsp"%>
	</div>
</body>
</html>
