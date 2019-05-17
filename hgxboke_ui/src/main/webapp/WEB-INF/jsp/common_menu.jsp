<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>

<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<script>
	window.onload = function() {
		var oH2 = document.getElementsByTagName("h2")[0];
		var oUl = document.getElementsByTagName("ul")[0];
		oH2.onclick = function() {
			var style = oUl.style;
			style.display = style.display == "block" ? "none" : "block";
			oH2.className = style.display == "block" ? "open" : ""
		}
	}
</script>
<body>
	<header style="position:fixed;top:0px;z-index:99;">
	<div class="tophead">
		<div class="logo">
			<a href="index">个人博客</a>
		</div>
		<div id="mnav">
			<h2>
				<span class="navicon"></span>
			</h2>
			<ul>
				<li><a href="index">网站首页</a></li>
				<li><a href="list">博客列表</a></li>
				<li><a href="write">写博客</a></li>
				<li><a href="mark">收藏记录</a></li>
				<li><a href="about_me">关于我</a></li>
			</ul>
		</div>
		<nav class="topnav" id="topnav">
		<ul>
			<li><a href="index">网站首页</a></li>
			<li><a href="list">博客列表</a></li>
			<li><a href="write">写博客</a></li>
			<li><a href="mark">收藏记录</a></li>
			<li><a href="about_me">关于我</a></li>
		</ul>
		</nav>
	</div>
	</header>
<body>
</body>
</html>
