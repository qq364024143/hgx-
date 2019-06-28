<!DOCTYPE html>
<html lang="en">
<head>
<base href="${request.contextPath}/"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta author="zrong.me 曾荣">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="static/register-login.css">
<link rel="stylesheet" href="static/layer.css" id="layui_layer_skinlayercss" style=""></head>
<body>
<input type="hidden" id="loginId" value="${loginId}"/>
<input type="hidden" id="redirectUrl" value="${redirectUrl}"/>
<div id="box"><canvas class="particles-js-canvas-el" style="width: 100%; height: 100%;" width="2560" height="797"></canvas></div>
<div class="cent-box">
	<div class="cent-box-header">
		<!--<h1 class="main-title hide">千寻</h1>-->
		<h2 class="sub-title">学习使我快乐</h2>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="javascript:;" class="active" onclick="tab_active('login_tab')">登录</a>
				<a href="javascript:;" onclick="tab_active('regist_tab')" >注册</a>			
			</div>
		</div>

		<div class="active_tab" id="login_tab">
		<div class="login form">
			<div class="group">
				<div class="group-ipt email">
					<input type="text" name="username" id="username" class="ipt" placeholder="邮箱地址||手机号" required="">
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" class="ipt" placeholder="输入您的登录密码" required="">
				</div>
				<div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required="">
					<img src="sso/getVerifyPic?type=login" class="imgcode" onclick='refreshVerify(this,"login")'>
				</div>
			</div>
		</div>

		<div class="button">
			<button type="submit" class="button" id="login_button">登录</button>
		</div>
		
		<div class="remember clearfix">
			<label class="remember-me"><span class="icon"><span class="zt"></span></span><input type="checkbox" name="remember-me" id="remember-me" class="remember-mecheck" checked="checked">记住我</label>
			<label class="forgot-password">
				<a href="#">忘记密码？</a>
			</label>
		</div>
		</div>
		
		
		<div class="active_tab" id="regist_tab" style="display:none;">
		<div class="login form">
			<div class="group">
				<div class="group-ipt email">
					<input type="email" name="email" id="email" class="ipt" placeholder="邮箱地址" required="">
				</div>
				<div class="group-ipt user">
					<input type="text" name="user" id="user" class="ipt" placeholder="选择一个用户名" required="">
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" class="ipt" placeholder="设置登录密码" required="">
				</div>
				<div class="group-ipt password1">
					<input type="password" name="password1" id="password1" class="ipt" placeholder="重复密码" required="">
				</div>
				<div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required="">
					<img src="sso/getVerifyPic?type=regist" class="imgcode" onclick='refreshVerify(this,"regist")'>
				</div>
			</div>
		</div>

		<div class="button">
			<button type="submit" class="button" id="regist_button">注册</button>
		</div>
		</div>
	</div>
</div>

<!--<div class="footer">
	<p>千寻 - Thousands Find</p>
	<p>Designed By ZengRong &amp; <a href="http://www.17sucai.com/preview/462257/2016-09-27/demo/zrong.me">zrong.me</a> 2016</p>
</div>-->

<script src="static/particles.js" type="text/javascript"></script>
<script src="static/background.js" type="text/javascript"></script>
<script src="static/jquery.js" type="text/javascript"></script>
<script src="static/layer.js" type="text/javascript"></script>
<script src="static/index.js" type="text/javascript"></script>
<script>
	//刷新验证码
	$('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
        		time: 6000,
        		tips: [2, "#3c3c3c"]
    		})
	},function(){
		layer.closeAll('tips');
	}).click();
	
	function refreshVerify(dom,type){
		$(dom).attr('src','sso/getVerifyPic?type='+type+'&id=' + Math.random());
	}
	
	$("#remember-me").click(function(){
		var n = document.getElementById("remember-me").checked;
		if(n){
			$(".zt").show();
		}else{
			$(".zt").hide();
		}
	});
	//登录注册切换
	function tab_active(activeId){
		$(".index-slide-nav a").removeClass("active");
		
		//获取事件
        var e = window.event;
        //获取元素
        obj = e.target || e.srcElement;
		$(obj).toggleClass("active");
		
		$(".active_tab").hide();
		$("#"+activeId).show();
	}
	
	//登录
	$("#login_button").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();//需要加密
		var verify = $("#verify").val();
		var redirectUtl = $("#redirectUrl").val();
		var loginId = $("#loginId").val();
		if(redirectUtl==''||redirectUtl==undefined){
			redirectUtl = 'http://127.0.0.1:1003/boke';
		}
		$.ajax({
			type:"post",
			url:"${request.contextPath}/sso/login",
			contentType: "application/json" ,
			data:JSON.stringify({"username":username,"password":password,"verify":verify,"loginId":loginId}),//data必须为json对象
			dataType:"json",
			async:false,
			success:function(data){
				if(data.code=='500'){
					alert(data.message);
				}else{
					
					window.location.href=redirectUtl;
				}
			}
		});
	});
</script>

</body></html>