<!-- <head>
<link rel="stylesheet" type="text/css" href="/Fertilizer/staticContent/css/clock.css" />
<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.js"> </script>
<script type="text/javascript" src="/Fertilizer/staticContent/js/bootstrap.min.js"> </script>
<link href="/Fertilizer/staticContent/css/bootstrap.css" rel="stylesheet">
<link href="/Fertilizer/staticContent/css/bootstrap.min.css" rel="stylesheet">
</head>
			
<div class="container">
		<div class="row ">
		
			<div class="col-sm-9">
					<marquee direction="right" behavior="alternate"><h3 style="font-family: Comic Sans MS;color:blue"><b><i> Duddulwar Agencies</i></b></h3></marquee>
			</div>
				
			<div class="col-sm-2">
					<b id="date" style="color:greenyellow;font-size: large"></b>
				<script>
					var d = new Date();
					document.getElementById("date").innerHTML = d.toDateString();
				</script>
				</div>
		</div>
			
			<div class="col-md-2 ">
				
				//code for analog Watch 
				<div id="liveclock" class="outer_face">
			<div class="marker oneseven"></div>
			<div class="marker twoeight"></div>
			<div class="marker fourten"></div>
			<div class="marker fiveeleven"></div>
			<div class="inner_face">
				<div class="hand hour"></div>
				<div class="hand minute"></div>
				<div class="hand second"></div>
			</div>
		</div>
		 <script type="text/javascript">
				var $hands = $('#liveclock div.hand')

				window.requestAnimationFrame = window.requestAnimationFrame
				                               || window.mozRequestAnimationFrame
				                               || window.webkitRequestAnimationFrame
				                               || window.msRequestAnimationFrame
				                               || function(f){setTimeout(f, 60)}

				
				function updateclock(){
					var curdate = new Date()
					var hour_as_degree = ( curdate.getHours() + curdate.getMinutes()/60 ) / 12 * 360
					var minute_as_degree = curdate.getMinutes() / 60 * 360
					var second_as_degree = ( curdate.getSeconds() + curdate.getMilliseconds()/1000 ) /60 * 360
					$hands.filter('.hour').css({transform: 'rotate(' + hour_as_degree + 'deg)' })
					$hands.filter('.minute').css({transform: 'rotate(' + minute_as_degree + 'deg)' })
					$hands.filter('.second').css({transform: 'rotate(' + second_as_degree + 'deg)' })
					requestAnimationFrame(updateclock)
				}
				
				requestAnimationFrame(updateclock)
		  </script>
				
				
			</div>
			
		</div>
 -->