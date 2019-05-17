<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!doctype html>
<html>
<head>
<base href="<%=basepath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人博客 </title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
<script src="static/js/jquery-2.1.1.min.js"></script>
</head>
<body>
<%--静态包含 --%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>

<article style="margin-top:100px;">
  <h1 class="t_nav"><span>您现在的位置是：收藏列表 
  	<c:if test="${not empty requestScope.markBokeParam.markDescript }"> > 关键字：${requestScope.markBokeParam.markDescript }</c:if> 
  </span><a class="n2" href="addBokeMark">添加收藏</a>
  </h1>
  <div class="picbox" id="picbox">
  
    
    <!-- <ul>
      <li><a href="/">
        <div class="picinfo">
          <h3>CSND博客</h3>
          <span>jsp如何使用el表达式判断是否为null...</span> 
        </div>
        </a></li>
    </ul>
     -->
    
    <div class="blank"></div>
    <div class="pagelist"><div id="test1"></div></div>
  </div>
  <div class="sidebar">
   <div class="search">
      <form action="mark" method="post" name="searchform" id="searchform">
      	<c:if test="${empty requestScope.markBokeParam.markDescript }">
      		<input name="markDescript" id="keyword" class="input_text" value="请输入关键字" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      	</c:if>
      	<c:if test="${not empty requestScope.markBokeParam.markDescript }">
      		<input name="markDescript" id="keyword" class="input_text" value="${requestScope.markBokeParam.markDescript }" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      	</c:if>
        <!-- <input name="show" value="title" type="hidden">
        <input name="tempid" value="1" type="hidden">
        <input name="tbname" value="news" type="hidden"> -->
        <input name="Submit" class="input_submit" value="搜索" type="button" onclick="formFilter()">
      </form>
    </div>
    <!-- <div class="lmnav">
      <h2 class="hometitle">栏目导航</h2>
      <ul class="navbor">
        <li><a href="#">关于我</a></li>
        <li><a href="share.html">模板分享</a>
          <ul>
            <li><a href="list.html">个人博客模板</a></li>
            <li><a href="#">HTML5模板</a></li>
          </ul>
        </li>
        <li><a href="list.html">学无止境</a>
          <ul>
            <li><a href="list.html">学习笔记</a></li>
            <li><a href="#">HTML5+CSS3</a></li>
            <li><a href="#">网站建设</a></li>
          </ul>
        </li>
        <li><a href="#">慢生活</a></li>
      </ul>
    </div>
   
    <div class="paihang">
      <h2 class="hometitle">点击排行</h2>
      <ul>
        <li><b><a href="/download/div/2015-04-10/746.html" target="_blank">【活动作品】柠檬绿兔小白个人博客模板30...</a></b>
          <p><i><img src="images/t02.jpg"></i>展示的是首页html，博客页面布局格式简单，没有复杂的背景，色彩局部点缀，动态的幻灯片展示，切换卡，标...</p>
        </li>
        <li><b><a href="/download/div/2014-02-19/649.html" target="_blank"> 个人博客模板（2014草根寻梦）30...</a></b>
          <p><i><img src="images/b03.jpg"></i>2014第一版《草根寻梦》个人博客模板简单、优雅、稳重、大气、低调。专为年轻有志向却又低调的草根站长设...</p>
        </li>
        <li><b><a href="/download/div/2013-08-08/571.html" target="_blank">黑色质感时间轴html5个人博客模板30...</a></b>
          <p><i><img src="images/b04.jpg"></i>黑色时间轴html5个人博客模板颜色以黑色为主色，添加了彩色作为网页的一个亮点，导航高亮显示、banner图片...</p>
        </li>
        <li><b><a href="/download/div/2014-09-18/730.html" target="_blank">情侣博客模板系列之《回忆》Html30...</a></b>
          <p><i><img src="images/b05.jpg"></i>Html5+css3情侣博客模板，主题《回忆》，使用css3技术实现网站动画效果，主题《回忆》,分为四个主要部分，...</p>
        </li>
        <li><b><a href="/download/div/2014-04-17/661.html" target="_blank">黑色Html5个人博客模板主题《如影随形》30...</a></b>
          <p><i><img src="images/b06.jpg"></i>014第二版黑色Html5个人博客模板主题《如影随形》，如精灵般的影子会给人一种神秘的感觉。一张剪影图黑白...</p>
        </li>
        <li><b><a href="/jstt/bj/2015-01-09/740.html" target="_blank">【匆匆那些年】总结个人博客经历的这四年…30...</a></b>
          <p><i><img src="images/mb02.jpg"></i>博客从最初的域名购买，到上线已经有四年的时间了，这四年的时间，有笑过，有怨过，有悔过，有执着过，也...</p>
        </li>
      </ul>
    </div> -->
    <div class="cloud">
      <h2 class="hometitle">博客分类 </h2>
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
						target="_self">${randomBoke.bokeTitle }</a></b>
					<p  style="padding-bottom:10px;">
						${randomBoke.bokeText }...
					</p></li>
					
		</c:forEach>
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
<footer>
  <p>Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxxx号-1</a></p>
</footer>
<script src="static/js/nav.js"></script>

<script src="static/js/jquery-2.1.1.min.js"></script>
<script src="static/layui/layui.js"></script>

<script>
/**
 * 查询参数缓存
 */
var markDescript = "${requestScope.markBokeParam.markDescript}";//el表达式取值只是文本值，不是字符串

//分页插件
layui.use('laypage', function(){
  var laypage = layui.laypage;
  
  //执行一个laypage实例
  laypage.render({
    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
    ,theme: '#555'
    ,count: ${requestScope.counts} //数据总数，从服务端得到，第一次跳转到此页面时查出来
    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
    ,limit:12
    ,limits:[12,24,48]
  	,jump: function(obj, first){//点击页数时调用
	    //obj包含了当前分页的所有参数，比如：
	    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
	    console.log(obj.limit); //得到每页显示的条数
	    
	    //使用ajax查询数据并手动渲染
	    var datas = getMarkBokePage(obj.curr,obj.limit);
	    //渲染数据
	    renderMarkBokeData(datas);
	    //首次不执行
	    /* if(!first){
	      //do something
	    } */
	  }
  });
});

/**
 * 使用ajax查询分页
 */
function getMarkBokePage(curr,limit){
	var datas;
	$.ajax({
		type:"post",
		url:"<%=basepath%>findMarkBokes",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({'cpage':curr,'pageSize':limit,'params':{'markDescript':markDescript}}),
		dataType:"json",
		async:false,//是否异步，否
		success:function(data){
			datas = data;
		}
	});
	return datas;
}

/**
 * 渲染数据到html页面
 */
function renderMarkBokeData(datas){
	var contents = $("#picbox");
	if(datas.length==0){
		contents.prepend("没有记录");
	}
	var html = '';
	for(var i=0; i<datas.length; i++){
		html += "<ul>";
		html +='<li><a target="_blank" href="'+datas[i].netAddress+'">';
		html +='<div class="picinfo">';
		html +='<h3 style="height:15px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap" title="'+datas[i].markTitle+'（'+datas[i].sourceFrom+'）'+'">'+datas[i].markTitle+'（'+datas[i].sourceFrom+'）</h3>';
		html +='<span style="height:250px; overflow: auto;">'+datas[i].markDescript+'</span> ';
		html +='</div>';
		html +='</a></li>';
		html +='</ul>';
	}
	$("#picbox ul").remove();
	contents.prepend(html);
}


/**
 * 表单提交时过滤
 */
function formFilter(){
	var keyword = jQuery("#keyword").val();
	var form = jQuery("#searchform");
	if(keyword=='请输入关键字'){
		jQuery("#keyword").val('');
	}
	form.submit();
}
</script>
</body>
</html>
