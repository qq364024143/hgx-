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
<title>个人博客列表</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
<script src="static/js/jquery-2.1.1.min.js"></script>
<style>
h3{
	font-size: 1.17em;
    font-weight: bold;
}
</style>
</head>
<body>
<%--静态包含 --%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>

<article style="margin-top:100px;">
  <h1 class="t_nav"><span>您现在的位置是：博客列表
  <c:if test="${not empty requestScope.bokeParam.bokeKeyword }"> >关键字：${requestScope.bokeParam.bokeKeyword }</c:if> 
  <c:if test="${not empty requestScope.bokeParam.bokeClassName }"> >分类：${requestScope.bokeParam.bokeClassName }</c:if>
  </span></h1>
  
  <div class="blogs" id="contents">
    
    
    <div class="pagelist"><div id="test1"></div></div>
  </div>
    
  <div class="sidebar">
    <div class="search">
      <form action="list" method="post" name="searchform" id="searchform">
      	<c:if test="${empty requestScope.bokeParam.bokeKeyword }">
      		<input name="bokeKeyword" id="keyword" class="input_text" value="请输入关键字" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      	</c:if>
      	<c:if test="${not empty requestScope.bokeParam.bokeKeyword }">
      		<input name="bokeKeyword" id="keyword" class="input_text" value="${requestScope.bokeParam.bokeKeyword }" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
      	</c:if>
        <!-- <input name="show" value="title" type="hidden">
        <input name="tempid" value="1" type="hidden">
        <input name="tbname" value="news" type="hidden"> -->
        <input name="Submit" class="input_submit" value="搜索" type="button" onclick="formFilter()">
      </form>
    </div>
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
var bokeClass = "${requestScope.bokeParam.bokeClass}";//el表达式取值只是文本值，不是字符串
var bokeKeyword = "${requestScope.bokeParam.bokeKeyword}";

//分页插件
layui.use('laypage', function(){
  var laypage = layui.laypage;
  
  //执行一个laypage实例
  laypage.render({
    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
    ,theme: '#555'
    ,count: ${requestScope.counts} //数据总数，从服务端得到，第一次跳转到此页面时查出来
    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
  	,jump: function(obj, first){//点击页数时调用
	    //obj包含了当前分页的所有参数，比如：
	    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
	    console.log(obj.limit); //得到每页显示的条数
	    
	    //使用ajax查询数据并手动渲染
	    var datas = getBokePage(obj.curr,obj.limit);
	    //渲染数据
	    renderBokeData(datas);
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
function getBokePage(curr,limit){
	var datas;
	$.ajax({
		type:"post",
		url:"<%=basepath%>findBokes",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({'cpage':curr,'pageSize':limit,'params':{'bokeClass':bokeClass,'bokeKeyword':bokeKeyword}}),
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
function renderBokeData(datas){
	var contents = $("#contents");
	if(datas.length==0){
		contents.prepend("没有记录");
	}
	var html = '<div class="mt20"></div>';
	for(var i=0; i<datas.length; i++){
		html += "<li>";
		html +='<h3 class="blogtitle"><a href="boke_info?bokeId='+datas[i].bokeId+'">'+datas[i].bokeTitle+'</a></h3>';
		html +='<div class="bloginfo">';
		html +='<p>'+datas[i].bokeText+'...</p>';
		html +='</div>';
		html +='<div class="autor"><span class="lm"><a href="list?bokeClass='+datas[i].bokeClass+'&bokeClassName='+datas[i].bokeClassName+'" title="'+datas[i].bokeClassName+'" target="_blank" class="classname">'+datas[i].bokeClassName+'</a></span><span class="dtime">'+datas[i].bokeUpdateTime+'</span><span class="au01"><a href="mailto:dancesmiling@qq.com">'+(datas[i].nickname==null?"":datas[i].nickname)+'</a></span><span class="readmore"><a href="boke_info?bokeId='+datas[i].bokeId+'">阅读原文</a></span></div>';
		html +='</li>';
	}
	$("#contents li").remove();
	contents.prepend(html);
	//回到顶部
	jQuery('html,body').animate({scrollTop:0},'slow');
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
