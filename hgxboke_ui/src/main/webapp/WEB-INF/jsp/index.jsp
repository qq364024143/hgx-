<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basepath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人博客首页</title>
<meta name="keywords" content="个人博客" />
<meta name="description"
	content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
<script src="static/js/jquery-2.1.1.min.js"></script>
</head>
<body>
<%--静态包含 --%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>


<!-- <div class="picshow" style="margin-top:100px;">
	<ul>
		<li><a href="/"><i><img src="static/images/b01.jpg"></i>
				<div class="font">
					<h3>个人博客模板《早安》</h3>
				</div> </a></li>
		<li><a href="/"><i><img src="static/images/b02.jpg"></i>
				<div class="font">
					<h3>个人博客模板《早安》</h3>
				</div> </a></li>
		<li><a href="/"><i><img src="static/images/b03.jpg"></i>
				<div class="font">
					<h3>个人博客模板《早安》</h3>
				</div> </a></li>
		<li><a href="/"><i><img src="static/images/b04.jpg"></i>
				<div class="font">
					<h3>个人博客模板《早安》</h3>
				</div> </a></li>
		<li><a href="/"><i><img src="static/images/b05.jpg"></i>
				<div class="font">
					<h3>个人博客模板《早安》</h3>
				</div> </a></li>
	</ul>
</div> -->
<article style="margin-top:100px;">
	<div class="blogs">

		<c:forEach var="item" items="${requestScope.newBokes}">
			<li>
				<!-- <span class="blogpic"><a href="/"><img src="images/text02.jpg"></a></span> -->
				<h3 class="blogtitle">
					<a href="boke_info?bokeId=${item.bokeId }" >${item.bokeTitle}</a>
				</h3>
				<div class="bloginfo">
					<p>${item.bokeText }，...</p>
				</div>
				<div class="autor">
					<span class="lm">
						<a href="list?bokeClass=${item.bokeClass }&bokeClassName=${item.bokeClassName}" title="${item.bokeClassName }" target="_blank" class="classname">${item.bokeClassName }</a>
					</span>
					<span class="dtime">${item.bokeCreateTime}</span>
					<span class="au01"><a href="mailto:dancesmiling@qq.com">${item.nickname }</a></span>
					<!-- <span class="viewnum">浏览（<a href="javascript:;">0</a>）</span> -->
					<span class="readmore"><a href="boke_info?bokeId=${item.bokeId }" target="_blank">阅读原文</a></span>
				</div>
			</li>
		</c:forEach>


	</div>
	<div class="sidebar">
		<div class="about">
			<div class="avatar">
				<img src="static/images/avatar.jpg" alt="">
			</div>
			<p class="abname">change|小何</p>
			<p class="abposition">Java web 前台&后台</p>
			<div class="abtext">
				作为一个Java开发，心里面总想着多学东西，虽然学的东西多，但有些知识却并不深入，还得加把劲。最重要的是把心态调整好！！</div>
		</div>
		<div class="search">
			<form action="list" method="post" name="searchform"
				id="searchform">
				<c:if test="${empty requestScope.bokeParam.bokeKeyword }">
      				<input name="bokeKeyword" id="keyword" class="input_text" value="请输入关键字" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      			</c:if>
      			<c:if test="${not empty requestScope.bokeParam.bokeKeyword }">
      				<input name="bokeKeyword" id="keyword" class="input_text" value="${requestScope.bokeParam.bokeKeyword }" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      			</c:if>
				
					<input class="input_submit" value="搜索" type="button" onclick="formFilter()">
			</form>
		</div>
		<div class="cloud">
			<h2 class="hometitle">博客分类</h2>
			<ul>
			
			<c:forEach var="bokeClass" items="${requestScope.bokeClasses }">
				<a href="list?bokeClass=${bokeClass.classId }&bokeClassName=${bokeClass.className}">${bokeClass.className }</a>
			</c:forEach>
			
			</ul>
		</div>
		<div class="paihang">
			<h2 class="hometitle">站长推荐</h2>
			<ul>
				<c:forEach var="randomBoke" items="${requestScope.randomBokes }">
				
					<li><b><a href="boke_info?bokeId=${randomBoke.bokeId }"
						target="_blank">${randomBoke.bokeTitle }</a></b>
					<p  style="padding-bottom:10px;">
						${randomBoke.bokeText }...
					</p></li>
					
				</c:forEach>
					
			</ul>
		</div>
		<!-- <div class="paihang">
			<h2 class="hometitle">站长推荐</h2>
			<ul>
				<li><b><a href="/download/div/2015-04-10/746.html"
						target="_blank">【活动作品】柠檬绿兔小白个人博客模板30...</a></b>
					<p>
						<i><img src="images/t02.jpg"></i>展示的是首页html，博客页面布局格式简单，没有复杂的背景，色彩局部点缀，动态的幻灯片展示，切换卡，标...
					</p></li>
				<li><b><a href="/download/div/2014-02-19/649.html"
						target="_blank"> 个人博客模板（2014草根寻梦）30...</a></b>
					<p>
						<i><img src="images/b03.jpg"></i>2014第一版《草根寻梦》个人博客模板简单、优雅、稳重、大气、低调。专为年轻有志向却又低调的草根站长设...
					</p></li>
				<li><b><a href="/download/div/2013-08-08/571.html"
						target="_blank">黑色质感时间轴html5个人博客模板30...</a></b>
					<p>
						<i><img src="images/b04.jpg"></i>黑色时间轴html5个人博客模板颜色以黑色为主色，添加了彩色作为网页的一个亮点，导航高亮显示、banner图片...
					</p></li>
				<li><b><a href="/download/div/2014-09-18/730.html"
						target="_blank">情侣博客模板系列之《回忆》Html30...</a></b>
					<p>
						<i><img src="images/b05.jpg"></i>Html5+css3情侣博客模板，主题《回忆》，使用css3技术实现网站动画效果，主题《回忆》,分为四个主要部分，...
					</p></li>
				<li><b><a href="/download/div/2014-04-17/661.html"
						target="_blank">黑色Html5个人博客模板主题《如影随形》30...</a></b>
					<p>
						<i><img src="images/b06.jpg"></i>014第二版黑色Html5个人博客模板主题《如影随形》，如精灵般的影子会给人一种神秘的感觉。一张剪影图黑白...
					</p></li>
				<li><b><a href="/jstt/bj/2015-01-09/740.html"
						target="_blank">【匆匆那些年】总结个人博客经历的这四年…30...</a></b>
					<p>
						<i><img src="images/mb02.jpg"></i>博客从最初的域名购买，到上线已经有四年的时间了，这四年的时间，有笑过，有怨过，有悔过，有执着过，也...
					</p></li>
			</ul>
		</div> -->
		<div class="links">
			<h2 class="hometitle">学习平台</h2>
			<ul>
				<li><a target="_blank" href="https://home.cnblogs.com" title="博客园">博客园</a></li>
				<li><a target="_blank" href="https://www.csdn.net/" title="CSDN">CSDN博客</a></li>
				<li><a target="_blank" href="http://yun.itheima.com/" title="传智播客视频">传智播客视频</a></li>
			</ul>
		</div>
		<div class="weixin">
			<h2 class="hometitle">个人QQ</h2>
			<ul>
				<img src="static/images/qq.jpg">
			</ul>
		</div>
	</div>
</article>
<div class="blank"></div>
<footer>
	<p>
		Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxxxx号-1</a>
	</p>
</footer>
<script src="static/js/nav.js"></script>

<script type="text/javascript">
/**
 * 表单提交时过滤
 */
function formFilter(){
	var keyword = $("#keyword").val();
	var form = $("#searchform");
	if(keyword=='请输入关键字'){
		$("#keyword").val('');
	}
	form.submit();
}
</script>
</body>
</html>

