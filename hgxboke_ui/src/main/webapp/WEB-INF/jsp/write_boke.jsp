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
					<form class="layui-form" action="saveBoke" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label">标题</label>
							<div class="layui-input-block">
								<input type="text" name="bokeTitle" required
									lay-verify="required" placeholder="请输入标题" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">描述</label>
							<div class="layui-input-block">
								<textarea name="bokeDescript" placeholder="请输入内容"
									class="layui-textarea"></textarea>
							</div>
						</div>
						<!-- <div class="layui-form-item">
							<label class="layui-form-label">关键字</label>
							<div class="layui-input-block">
								<input type="text" name="bokeKeyword"
									placeholder="请输入关键字，多个关键字以，隔开" autocomplete="off"
									class="layui-input">
							</div>
						</div> -->
						<div class="layui-form-item">
							<label class="layui-form-label">选择分类</label>
							<div class="layui-input-block">
								<select name="bokeClass" lay-verify="required">
									<option value=""></option>
									<c:forEach var="bokeClass"
										items="${ requestScope.bokeClasses }">
										<option value="${bokeClass.classId }">${bokeClass.className }</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">正文</label>
							<div class="layui-input-block">
								<textarea id="demo"  placeholder="请输入内容" class="layui-textarea"  lay-verify="bokeText_textarea"></textarea>
							</div>
						</div>

						<!-- 隐藏输入框插入正文内容，提交表单 -->
						<input id="bokeText" type="hidden" name="bokeText" /> <input
							type="hidden" id="bokeHtmlText" name="bokeHtmlText" />

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
					<p>姓名：叫我小何就好了</p>
					<p>专业：Java</p>
					<p>个人微信：wxid_6ta8s9skoloa22</p>
					<p>邮箱：364024143@qq.com</p>
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
		Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxxx号-1</a>
	</p>
</footer>
<script src="static/js/jquery-2.1.1.min.js"></script>
<script src="static/layui/layui.all.js"></script>
<script src="static/js/nav.js"></script>


<script>
	var layedit;
	var layeditID;
	//富文本编辑器
	layui.use('layedit', function() {
		layedit = layui.layedit;
		var option = {
			tool : [ 'html'//源码模式 
			, 'strong' //加粗
			, 'italic' //斜体
			, 'underline' //下划线
			, 'del' //删除线
			, '|' //分割线
			, 'left' //左对齐
			, 'center' //居中对齐
			, 'right' //右对齐
			, 'link' //超链接
			, 'unlink' //清除链接
			, 'face' //表情
			, 'image' //插入图片
			, 'code'//插入代码
			, 'table'//插入表格
			, 'customlink'//插入自定义链接
			, 'fullScreen'//全屏
			, 'preview'//预览
			]
		};
		//插入图片
		layedit.set({
			uploadImage : {
				url : '' //接口url
				,
				type : '' //默认post
			}
		});

		layeditID = layedit.build('demo', option); //建立编辑器
	});

	//表单提交过滤器
	layui.use('form', function() {
		var form = layui.form;
		//表单验证
		form.verify({
			bokeText_textarea:function(value){
				//因为layui验证无法获取iframe的值所以自己获取
				var bokeText = layedit.getText(layeditID);
				if(bokeText==''){
					return '正文不能为空';
				}
			}
		});
		//监听提交
		form.on('submit(formDemo)', function(data) {

			//获取代码内容
			var pre = $("#LAY_layedit_1").contents().find("pre");
			//替换成prism代码标签
			$(pre).each(
					function() {
						var text = $(this).html();
						var lang = $(this).attr("lay-lang");
						var relang = lang.toLowerCase();
						var prismpre = "<code class='language-"+relang+"'>"
								+ text + "</code>";
						$(this).removeAttr("lay-lang");
						$(this)
								.attr("class",
										"line-numbers language-" + relang);
						$(this).html(prismpre);

					});
			//将内容放至隐藏文本提交
			layer.msg(JSON.stringify(data.text));
			$("#bokeText").val(layedit.getText(layeditID));
			$("#bokeHtmlText").val(layedit.getContent(layeditID));
			return true;
		});
	});
</script>
</body>
</html>
