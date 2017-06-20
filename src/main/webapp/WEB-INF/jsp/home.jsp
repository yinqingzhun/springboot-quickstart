<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<style type="text/css">
.treebox {
	width: 200px;
	background-color: #1a6cb9;
	position: absolute;
	left: 0;
	right: 0;
	bottom: 0;
	height: 100%;
}

.current {
	background: #0f4679;
}

.menu {
	overflow: hidden;
	border-color: #ddd;
	border-style: solid;
	border-width: 0 1px 1px;
	padding: 0;
	margin: 0;
	border: none;
}

ul, li {
	list-style-type: none;
}

.page {
	height: 100%;
	width: auto;
	position: absolute;
	left: 200px;
	right: 0;
	bottom: 0;
	_width: 100%;
}

iframe {
	width: 100%;
	margin: 0;
	padding: 0;
}

.box {
	position: relative;
	margin: 0;
	padding: 0;
}

.menu li a {
	border-bottom: 1px solid #000;
	display: block;
	height: 45px;
	line-height: 45px;
	color: #fff;
	padding-left: 50px;
	border-bottom: 1px solid #000;
	font-size: 16px;
	position: relative;
	transition: all .5s ease 0s;
	height: 45px;
	line-height: 45px;
	color: #fff;
	padding-left: 50px;
	border-bottom: 1px solid #000;
	font-size: 16px;
	position: relative;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="treebox">
		<ul class="menu">
			<li><a href="javascript:;"
				data-url="http://agv.autohome.com.cn/youku">优酷（综合）</a></li>
			<li><a href="javascript:;"
				data-url="http://agv.autohome.com.cn/youku/su">优酷（自媒体）</a></li>
			<li><a href="javascript:;"
				data-url="http://agv.autohome.com.cn/so/q_宝马">爱奇艺</a></li>
			<li><a href="javascript:;"
				data-url="http://agv.autohome.com.cn/qq">腾讯</a></li>
		</ul>
	</div>

	<div class="page">
		<iframe id="frame" style="width: 100%; border: none; height: 100%;"
			src="http://www.baidu.com"></iframe>
	</div>

	<script type="text/javascript">
		$(function() {
			$('li').mouseover(function() {
				$(this).addClass('current');
			}).mouseout(function() {
				$(this).removeClass('current');
			});

			$('.menu a').click(function() {
				$('#frame').attr("src", $(this).data("url"));

			});
			
			$('.menu a').get(0).click();

		});
	</script>
</body>
</html>