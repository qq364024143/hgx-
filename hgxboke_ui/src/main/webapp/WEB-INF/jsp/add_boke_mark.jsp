<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<base href="<%=basepath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description"
	content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="static/layui/css/layui.css">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
</head>
<body>

<%--静态包含 --%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>

<article style="margin-top: 100px;">
	<h1 class="t_nav">
		<span>像“草根”一样，紧贴着地面，低调的存在，冬去春来，枯荣无恙。</span><a href="index" class="n1">网站首页</a>
		<!-- <a href="/" class="n2">留言</a> -->
	</h1>
	<div class="ab_box">
		<div class="leftbox">
			<div class="newsview">
				<div class="news_infos">
					<form class="layui-form" action="saveMarkBoke" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label">标题</label>
							<div class="layui-input-block">
								<input type="text" name="markTitle" required
									lay-verify="required" placeholder="请输入标题" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">描述</label>
							<div class="layui-input-block">
								<textarea name="markDescript" placeholder="描述。尽量描述清楚，以便查找" required
									class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">文章来源</label>
							<div class="layui-input-block">
								<input type="text" name="sourceFrom" required
									placeholder="请输入文章来源" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">博客地址</label>
							<div class="layui-input-block">
								<input type="text" name="netAddress" required
									placeholder="请输入博客以http://或https://的地址" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="rightbox">
			<div class="aboutme">
				<h2 class="hometitle">关于我</h2>
				<div class="avatar">
					<img src="static/images/avatar.jpg">
				</div>
				<div class="ab_con">
					<p>网名：小何</p>
					<p>职业：Java web开发</p>
					<p>个人微信：xxx</p>
					<p>邮箱：xxxxx@qq.com</p>
				</div>
			</div>
			<div class="weixin">
				<h2 class="hometitle">个人QQ</h2>
				<ul>
					<img src="static/images/qq.jpg">
				</ul>
			</div>
		</div>
	</div>
</article>
<footer>
	<p>
		Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxxxx号-1</a>
	</p>
</footer>
<script src="static/js/jquery-2.1.1.min.js"></script>
<script src="static/layui/layui.all.js"></script>
<script src="static/js/nav.js"></script>


<script>

	//表单提交过滤器
	layui.use('form', function() {
		var form = layui.form;
		//监听提交
		form.on('submit(formDemo)', function(data) {
			//...dosomething
			
			return true;
		});
	});
</script>
</body>
</html>
