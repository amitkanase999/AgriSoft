<head>
<meta name="viewport" content="width=device-width , initial-scale=1">
<script type="text/javascript"
	src="/AgriSoft/staticContent/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/AgriSoft/staticContent/js/calculator.js"></script>
<link rel="stylesheet" href="/AgriSoft/staticContent/css/calculator.css">
<title>Embel Technologies Pvt ltd.</title>
</head>

<div class="container text-center " id="calc" align="center">
	<div class="calcBG col-md-3 col-md-offset-4 text-center">
		<div class="row">
			<small>Calculator</small>
		</div>
		
		<div class="row" id="result">
			<form name="calc">
				<input type="text" class="screen text-right" name="result" readonly>
			</form>
		</div>
		
		<div class="row">
			<button id="allClear" type="button" class="btn btn-danger"
				onclick="clearScreen()">AC</button>
			<button id="clear" type="button" class="btn btn-warning"
				onclick="clearScreen()">CE</button>
			<button id="%" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">%</button>
			<button id="/" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">÷</button>
		</div>
		
		<div class="row">
			<button id="7" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">7</button>
			<button id="8" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">8</button>
			<button id="9" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">9</button>
			<button id="*" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">x</button>
		</div>
		
		<div class="row">
			<button id="4" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">4</button>
			<button id="5" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">5</button>
			<button id="6" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">6</button>
			<button id="-" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">-</button>
		</div>
		
		<div class="row">
			<button id="1" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">1</button>
			<button id="2" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">2</button>
			<button id="3" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">3</button>
			<button id="+" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">+</button>
		</div>
		
		<div class="row">
			<button id="0" type="button" class="btn btn-info"
				onclick="myFunction(this.id)">0</button>
			<button id="." type="button" class="btn btn-info"
				onclick="myFunction(this.id)">.</button>
			<button id="equals" type="button" class="btn btn-success"
				onclick="calculate()">=</button>
			<button id="blank" type="button" class="btn btn-info">&nbsp;</button>
		</div>
	</div>
</div>
