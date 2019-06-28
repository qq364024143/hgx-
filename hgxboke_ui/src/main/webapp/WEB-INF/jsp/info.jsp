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
<title>${requestScope.boke.bokeTitle }详情 </title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 以免layui覆盖本地样式，所以放在前面 -->
<link rel="stylesheet" href="static/layui/css/layui.css">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
<link rel="stylesheet" href="static/prism/prism.css"/>
<script src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="static/layui/layui.all.js"></script>

<style type="text/css">

.reply{
		margin-left:10px;
		cursor: pointer;
		color:graytext;
		font-size:small;
	}
.reply:hover{
	color:black;
}
.comment_list {
	padding-top: 40px;
	width: 100%;
	margin: 0 auto;
	background-color: #eee;
}

.comment_details {
	/*float: left;
	padding: 0 25px 0 25px;*/
	width: 95%;
    margin: 0 auto;
}

.comment_content {
	margin-top: 10px;
	font-size: 16px;
	width:100%;
	word-break: break-word;/*强制换行*/
}

.comment_add_or_last {
	margin: 0 auto;
	clear: both;
	width: 100%;
	height: 40px;
	background: #F0F0F0;
	text-align: center;
	line-height: 40px;
	//
	行高（与div保持同高，垂直居中写法）
}

.imgdiv {
	float: left;
}

.imgcss {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

.comment_name {
	/*margin-left: 10px;*/
	color: #3D9EEA;
	font-size: 15px;
	font-weight: bolder;
}
.root_comment_name{
	margin-left: 10px;
	color: #3D9EEA;
	font-size: 15px;
	font-weight: bolder;
}
.layui-icon {
	font-size: 10px;
	color: grey;
	float:right;
	font-size: 14px;
}

.del {
	margin-left: 55px;
}

.del_comment{
	cursor:pointer;
}
.root_comment {
	width: 100%;
}

.root_comment_details {
	margin-left: 50px;
}
.comment_time{
	font-size:small; 
	color: darkgray;
	margin-left:20px;
}
</style>

</head>
<body>
<%--静态包含 菜单--%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>

<!-- 正文 -->
<article style="margin-top:100px;">
  <h1 class="t_nav"><span><!-- 您现在的位置是：首页 > 慢生活 > 程序人生 --></span><a href="index" class="n1">网站首页</a><a href="write" class="n2">写博客</a></h1>
  <div class="infos">
    <div class="newsview">
      <h3 class="news_title">${requestScope.boke.bokeTitle }</h3>
      <div class="news_author"><span class="au01"><a href="mailto:dancesmiling@qq.com">${requestScope.boke.nickname }</a></span><span class="au02">创建时间：${requestScope.boke.bokeCreateTime }</span><span class="au02">更新时间：${requestScope.boke.bokeUpdateTime }</span><!-- <span class="au03">共<b><script src="/e/public/ViewClick/?classid=5&amp;id=816&amp;addclick=1"></script>1833</b>人围观</span> --></div>
      <div class="news_about" style="margin-top:30px;"><strong>描述</strong>${requestScope.boke.bokeDescript }</div>
      <div class="news_infos">${requestScope.boke.bokeHtmlText }</div>
    </div>
    <div class="share"> </div>
    <div class="nextinfo" id="nextInfo">
      <!-- <p>上一篇：<a href="/news/life/2018-03-13/804.html">作为一个设计师,如果遭到质疑你是否能恪守自己的原则?</a></p>
      <p>下一篇：<a href="/news/life/">返回列表</a></p> -->
    </div>
   <!--  <div class="otherlink">
      <h2>相关文章</h2>
      <ul>
        <li><a href="/download/div/2018-04-22/815.html" title="html5个人博客模板《黑色格调》">html5个人博客模板《黑色格调》</a></li>
        <li><a href="/download/div/2018-04-18/814.html" title="html5个人博客模板主题《清雅》">html5个人博客模板主题《清雅》</a></li>
        <li><a href="/download/div/2018-03-18/807.html" title="html5个人博客模板主题《绅士》">html5个人博客模板主题《绅士》</a></li>
        <li><a href="/download/div/2018-02-22/798.html" title="html5时尚个人博客模板-技术门户型">html5时尚个人博客模板-技术门户型</a></li>
        <li><a href="/download/div/2017-09-08/789.html" title="html5个人博客模板主题《心蓝时间轴》">html5个人博客模板主题《心蓝时间轴》</a></li>
        <li><a href="/download/div/2017-07-16/785.html" title="古典个人博客模板《江南墨卷》">古典个人博客模板《江南墨卷》</a></li>
        <li><a href="/download/div/2017-07-13/783.html" title="古典风格-个人博客模板">古典风格-个人博客模板</a></li>
        <li><a href="/download/div/2015-06-28/748.html" title="个人博客《草根寻梦》―手机版模板">个人博客《草根寻梦》―手机版模板</a></li>
        <li><a href="/download/div/2015-04-10/746.html" title="【活动作品】柠檬绿兔小白个人博客模板">【活动作品】柠檬绿兔小白个人博客模板</a></li>
        <li><a href="/jstt/bj/2015-01-09/740.html" title="【匆匆那些年】总结个人博客经历的这四年…">【匆匆那些年】总结个人博客经历的这四年…</a></li>
      </ul>
    </div> -->
    <div class="news_pl">
      <h2 style="margin-bottom: 20px;">文章评论</h2>
      <!-- 评论输入框 -->
      <div style="width:90%; margin: 0 auto; margin-bottom: 50px;">
      		<textarea id="boke_comment_textArea_reply" name="desc" placeholder="大佬给点意见吧" class="layui-textarea" style="margin-bottom: 10px;"></textarea>
      		<button class="layui-btn" lay-submit lay-filter="formDemo" id="boke_comment_submit">评论</button>
      </div>
      
      <hr/>
      <!-- 评论和回复内容 -->
      <div id=comments style="margin:0 auto; width:90%;">
      
      
      <!-- 评论分页 -->
      	<div class="pagelist"><div id="test1"></div></div>
      </div>
      
    </div>
  </div>
  <div class="sidebar">
    <div class="search">
      <form action="list" method="post" name="searchform" id="searchform">
      	<c:if test="${empty requestScope.bokeParam.bokeKeyword }">
      		<input id="keyword" name="bokeKeyword" id="keyboard" class="input_text" value="请输入关键字" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
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
      <h2 class="hometitle">博客分类</h2>
      <ul>
      <c:forEach var="bokeClass" items="${requestScope.bokeClasses }">
      	<a href="list?bokeClass=${bokeClass.classId }">${bokeClass.className }</a>
      </c:forEach>
      </ul>
    </div>
   <!--  <div class="lmnav">
      <h2 class="hometitle">栏目导航</h2>
      <ul class="navbor">
        <li><a href="#">关于我</a></li>
        <li><a href="share.html">模板分享</a>
          <ul>
            <li><a href="list.html">个人博客模板</a></li>
            <li><a href="#">HTML5模板</a></li>
          </ul>
        </li>
      </ul>
    </div> -->
    <div class="paihang">
      <h2 class="hometitle">相关博客</h2>
      <ul>
      <c:forEach var="relateBoke" items="${requestScope.relateBokes }">
      	<li><b><a href="boke_info?bokeId=${relateBoke.bokeId }" target="_blank">${relateBoke.bokeTitle }</a></b>
          <p>${relateBoke.bokeText}...</p>
        </li>
      </c:forEach>
      </ul>
    </div>
    
    <!-- <div class="paihang">
      <h2 class="hometitle">站长推荐</h2>
      <ul>
        <li><b><a href="/download/div/2015-04-10/746.html" target="_blank">【活动作品】柠檬绿兔小白个人博客模板30...</a></b>
          <p><i><img src="images/t02.jpg"></i>展示的是首页html，博客页面布局格式简单，没有复杂的背景，色彩局部点缀，动态的幻灯片展示，切换卡，标...</p>
        </li>
      </ul>
    </div> -->
    <div class="weixin">
      <h2 class="hometitle">个人QQ</h2>
      <ul>
        <img src="static/images/qq.jpg">
      </ul>
    </div>
    <!-- <div class="ad" id="left_flow2"> <img src="static/images/ad.jpg"> </div> -->
  </div>
</article>
<footer>
  <p>Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxxx号-1</a></p>
</footer>
<script src="static/js/nav.js"></script> 


<script type="text/javascript">
jQuery.noConflict();
jQuery(function() { 
    /* var elm = jQuery('#left_flow2'); 
    var startPos = jQuery(elm).offset().top; 
    jQuery.event.add(window, "scroll", function() { 
        var p = jQuery(window).scrollTop(); 
        jQuery(elm).css('position',((p) > startPos) ? 'fixed' : ''); 

        jQuery(elm).css('top',((p) > startPos) ? '0' : '');
    }); */
    //获取上一篇和下一篇博客
    getLastAndNextBoke();
}); 

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

/**
 * 获取此博客的上一条和下一条博客，以时间排序
 */
function getLastAndNextBoke(){
	jQuery.ajax({
		type:'post',
		url:'<%=basepath%>getLastAndNextBoke',
		data:{bokeNums:"${requestScope.boke.bokeNums}"},
		dataType:'json',
		success:function(data){
			var html = '';
			if(data.last!=null&&data.last!=undefined){
				html += '<p>上一篇：<a href="boke_info?bokeId='+data.last.bokeId+'">'+data.last.bokeTitle+'</a></p>';
			}
			if(data.next!=null&&data.next!=undefined){
				html += '<p>下一篇：<a href="boke_info?bokeId='+data.next.bokeId+'">'+data.next.bokeTitle+'</a></p>';
			} 
			
			if(html==''){
				html = '<p>没有更多了</p>';
			}
			jQuery("#nextInfo").append(html);
		}
		
	});
}

//创建评论回复文本域,参数为博客ID，最上层评论ID，评论/回复人ID，评论/回复人昵称
function createReplyTextArea(bokeId,rootCommentId,commentId,commentUserId,commentNickname,commentType){
	
	if(jQuery("#"+commentId+"_reply").length>0){//判断同一个文本域已存在则不执行任何
		return;
	}
	//先移除所有TextArea
	jQuery(".commentReplyTextArea").remove();
	
	var textArea = '<div class="commentReplyTextArea" style="margin-top:30px;">';
	//textArea +='<div style=" width:90%; margin: 0 auto;">';
	textArea +='<textarea id="'+commentId+'_reply" name="desc" onblur="textAreaOnblur(\''+commentId+'\')" placeholder="回复'+commentNickname+'" class="layui-textarea" style="margin-bottom: 10px;"></textarea>';
	textArea +='<button class="layui-btn" lay-submit lay-filter="formDemo" onclick="commitReply(\''+bokeId+'\',\''+rootCommentId+'\',\''+commentId+'\',\''+commentUserId+'\',\''+commentType+'\')">回复</button>';
	//textArea +='</div>';	
	textArea +='</div>';
	
	jQuery("#"+commentId).append(textArea);
}

//回复框失去焦点如果值为空则移除
function textAreaOnblur(commentId){
	var textAreaVal = jQuery("#"+commentId+"_reply").val();
	if(textAreaVal==""||textAreaVal==null){
		jQuery(".commentReplyTextArea").remove();
	}
}

//博客评论


//提交回复和评论,rootCommentId用于提交数据（关联），commentId用于查找textArea
function commitReply(bokeId,rootCommentId,commentId,commentUserId,commentType){
	var textAreaVal = jQuery("#"+commentId+"_reply").val();
	console.log(textAreaVal);
	if(textAreaVal==''){
		layer.msg('评论内容不能为空', {
		    time: 1500, //1500ms后自动关闭
		  });
		return;
	}
	var flag = "";
	//ajax请求提交，提交成功则刷新评论，最多显示5条回复，多出来的直接js移除或者根据父类CommentId查询最新的5条再渲染，看用哪种方法。删除文本域
	jQuery.ajax({
		type:'post',
		url:"<%=basepath%>comment/saveComment",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({bokeId:bokeId,rootCommentId:rootCommentId,parentCommentId:commentId,parentCommentUserId:commentUserId,commentType:commentType,commentContent:textAreaVal}),
		dataType:'json',
		async:false,//设为同步执行
		success:function(data){
			if(data.code=='200'){
				//自动触发点击事件，刷新评论
				if(commentType=="1"){//回复评论不需要到首页，在当前页刷新
					jQuery(".layui-laypage-btn").trigger("click");
					layer.msg('评论成功', {
					    time: 1500, //1500ms后自动关闭
					  });
				}else{
					jQuery(".layui-laypage-skip .layui-input").val("1");//设置跳转页
					jQuery(".layui-laypage-btn").trigger("click");//触发点击跳转页事件刷新
					layer.msg('评论成功', {
					    time: 1500, //1500ms后自动关闭
					  });
					//重新设置分页插件
					resetLayuiPage();
				}
				
				flag = "success";
				//不能在这里return返回值，因为会返回到success这个函数，我们要返回的是最外层的函数
			}else{
				layer.msg('评论失败', {
				    time: 1500, //1500ms后自动关闭
				  });
				flag = "error";
			}
		}
	});
	return flag;
}



/**
 * 评论分页查询参数缓存
 */
 var laypage;
var bokeId = "${requestScope.boke.bokeId}";//el表达式取值只是文本值，不是字符串


//分页插件
layui.use('laypage', function(){
   laypage = layui.laypage;
  
  //执行一个laypage实例
  laypage.render({
    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
    ,theme: '#555'
    ,count:${requestScope.bokeCommentTotal} //数据总数，从服务端得到，第一次跳转到此页面时查出来
    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
    ,limit:5
    ,limits:[5,10,15,20]
  	,jump: function(obj, first){//点击页数时调用
	    //obj包含了当前分页的所有参数，比如：
	    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
	    console.log(obj.limit); //得到每页显示的条数
	    
	    //使用ajax查询数据并手动渲染
	    var datas = getPageCommentList(obj.curr,obj.limit);
	    //渲染数据
	    renderCommentData(datas);
	    //首次不执行
	    /* if(!first){
	      //do something
	    } */
	  }
  });
  
  //顺便将博客评论按钮绑定提交事件
  jQuery("#boke_comment_submit").click(function(){
	 var flag = commitReply(bokeId,null,"boke_comment_textArea",null,0);
	 if(flag=="success"){
		 jQuery("#boke_comment_textArea_reply").val("");//评论框置空
	 }
  });
});

/**
 * 使用ajax查询分页
 */
function getPageCommentList(curr,limit){
	var datas;
	jQuery.ajax({
		type:"post",
		url:"<%=basepath%>comment/findPageForCommentList",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({'cpage':curr,'pageSize':limit,'params':bokeId}),
		dataType:"json",
		async:false,//是否异步，否,一般取数据时不要使用异步，会导致拿到数据但是返回的datas为null（因为线程问题）
		success:function(data){
			datas = data;
		}
	});
	return datas;
}

/**
 * 渲染数据到html页面
 */
function renderCommentData(datas){
	
	//移除之前的评论
	jQuery("#comments ul").remove();
	jQuery("#comments hr").remove();
	jQuery("#comments p").remove();
	var contents = jQuery("#comments");
	
	if(datas==null||datas==undefined||datas.length==0){
		jQuery("#comments p").remove();
		contents.prepend("<p>还没得评论...</p>");
		return ;
	}
	
	
	 var  html ='';
	 for(var i=0; i<datas.length; i++){
		var comment = datas[i];
		var replys = datas[i].replys;
		
		html += '<ul>';
		html +='<div class="gbko">';
		
		/* html +='<div style="position: relative;"><img style="position: absolute;" width="50px" height="50px" src="'+comment.commentUserUserPicture+'" align="top"  /> <div style="padding: 5px 0px 5px 55px;"><font color="blue">'+comment.commentUserNickname+'</font>：'+comment.commentContent+'</div></div>';
		//html +='<div style="padding-left:50px;">'+comment.commentContent+'</div>';
		html +='<div style="float:right;">';
		html +='<font style="margin:0px 10px 0px 5px; font-size:small; color:graytext;">'+comment.commentTime+'</font>';
		html +='<font class="reply" onclick="createReplyTextArea(\''+bokeId+'\',\''+comment.commentId+'\',\''+comment.commentId+'\',\''+comment.commentUserId+'\',\''+comment.commentUserNickname+'\',\''+0+'\')">回复</font>';
		html +='</div>';
		html +='<div id="'+comment.commentId+'">';
		html +='</div>'; */
		
		html +='<div class="root_comment">';
		html +='<div class="imgdiv">';
		html +='<img class="imgcss" src="'+comment.commentUserUserPicture+'" />';
		html +='</div>';
		html +='<div class="root_comment_details">';
		html +='<span class="root_comment_name">'+comment.commentUserNickname+'</span>';
		html +='<span class="comment_time">'+comment.commentTime+'</span>';
		html +='<div class="comment_content">'+comment.commentContent+'</div>';
		html +='<div class="del">';
		html +='<a class="del_comment" data-id="1">';
		html +='<i class="layui-icon-reply-fill icon layui-icon reply"  onclick="createReplyTextArea(\''+bokeId+'\',\''+comment.commentId+'\',\''+comment.commentId+'\',\''+comment.commentUserId+'\',\''+comment.commentUserNickname+'\',\''+1+'\')">回复</i></a>';
		html +='</div>';
		html +='</div>';
		html +='<div id="'+comment.commentId+'"></div>';
		html +='</div>';
		
		if(replys.length>0){
			html +='<div style="background-color: rgb(247, 248, 250);  padding:10px 10px; border:1px solid #f2f2f2; margin-top:30px;">';
		}
		
		//遍历子集
		/* for(var j=0;j<replys.length; j++){
			var reply = replys[j];
			html +='<div style="padding-bottom:0px;">';
			html +='<div style="word-break:break-all;"><font color="skyblue">'+(reply.commentUserNickname==undefined?"某位靓仔":reply.commentUserNickname)+'</font><span style="margin:0px 8px; ">回复</span><font color="skyblue">'+reply.parentCommentUserNickname+'：</font>'+reply.commentContent+'</div>';
			html +='<div style="float:right;">';
			html +='<font style="margin:0px 5px 0px 50px; font-size:small; color:graytext;">'+reply.commentTime+'</font>';
			html +='<font class="reply" onclick="createReplyTextArea(\''+bokeId+'\',\''+comment.commentId+'\',\''+reply.commentId+'\',\''+reply.commentUserId+'\',\''+reply.commentUserNickname+'\',\''+1+'\')">回复</font>';
			html +='</div>';
			html +='<div id="'+reply.commentId+'">';
			html +='</div>';
			html +='</div>';
			html +='<HR style="border:1" width="100%" color=#987cb9 SIZE=1>';
			
		} */
		
		for(var j=0;j<replys.length; j++){
			var reply = replys[j];
					html +='<div class="comment">';
					html +='<div class="comment_details" >';
					html +='<span class="comment_name">'+reply.commentUserNickname+'&nbsp;<font color="black"  style="margin:0px 8px;">回复</font>&nbsp;'+reply.parentCommentUserNickname+' </span> <span class="comment_time">'+reply.commentTime+'</span>';
					html +='<div class="comment_content">'+reply.commentContent+'</div>';
					html +='<div class="del">';
					html +='<a class="del_comment" data-id="1"> ';
					html +='<i class="layui-icon-reply-fill icon layui-icon reply"  onclick="createReplyTextArea(\''+bokeId+'\',\''+comment.commentId+'\',\''+reply.commentId+'\',\''+reply.commentUserId+'\',\''+reply.commentUserNickname+'\',\''+1+'\')">回复</i></a>';
					html +='</div>';
					html +='<div id="'+reply.commentId+'"></div>';
					html +='</div>';
					html +='<hr>';
					html +='</div>';
		}
		if(comment.replyTotal>5){
			html +='<a href="javascript:;" target="_blank" color="blue" onclick="lookAllCommentReply(\''+comment.commentId+'\')">查看全部（'+comment.replyTotal+'）</a>';
		}
		if(replys.length>0){
			html +='</div>';
		}
		html +='</div>';
		html +='</ul>';
		html +='<hr style="margin-top:20px;"/>';
	 }
	
	//渲染
	contents.prepend(html);
	
}

/**
 * 查看评论的全部回复，弹出iframe
 */
function lookAllCommentReply(commentId){
	//判断电脑还是手机
	
	var width = '';
	var move = false;
	var offset = 'auto';
	if(isMobile()){
		//如果是手机
		width = '100%';
		offset = 'b';
	}else{
		width = '40%';
		move = true;
	}
	//iframe层
	layer.open({
	  type: 2,//类型，iframe
	  title: '全部回复',
	  shadeClose: true,//点击外部关闭
	  shade: 0.3,//遮挡层透明度
	  closeBtn: 1, //0不显示关闭按钮
	  area: [width, '85%'],
	  move: move,//拖拽
	  scrollbar:false,//父级滚动
	  offset:offset,//弹出位置，b:bottom
	  content: '<%=basepath%>comment/allReply?commentId='+commentId //iframe的url
	}); 
	//event可以不传直接使用，但是名字一定是event,或者使用window.event获取当前函数的触发事件
	//event.stopPropagation();
}

//判断用户使用的是PC还是移动端
function isMobile() {
	var userAgentInfo = navigator.userAgent;//获取用户设备信息
	var mobileAgents = [ "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad","iPod"];
	var mobile_flag = false; 
	//根据userAgent判断是否是手机
	for (var v = 0; v < mobileAgents.length; v++) {
		if (userAgentInfo.indexOf(mobileAgents[v]) > 0) {
			mobile_flag = true; break; 
		} 
	} 
	var screen_width = window.screen.width;
	var screen_height = window.screen.height; 
	//根据屏幕分辨率判断是否是手机 
	if(screen_width < 500 && screen_height < 800){
		mobile_flag = true; 
		} 
		return mobile_flag; 
	}

//重新设置layui分页插件
function resetLayuiPage(){
	var commentTotal;
	jQuery.ajax({
		type:'get',
		url:'<%=basepath%>comment/getBokeCommentCount',
		data:{bokeId:bokeId},
		dataType:'json',
		async:false,
		success:function(data){
			if(data.code=='200'){
				commentTotal = data.data;
			}else{//查询评论总数失败 则不重新设置
				return;
			}
			//重新渲染
			laypage.render({
			    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
			        ,theme: '#555'
			        ,count:commentTotal //数据总数，从服务端得到，第一次跳转到此页面时查出来
			        ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
			        ,limit:5
			        ,limits:[5,10,15,20]
			      	,jump: function(obj, first){//点击页数时调用
			    	    //obj包含了当前分页的所有参数，比如：
			    	    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
			    	    console.log(obj.limit); //得到每页显示的条数
			    	    
			    	    //使用ajax查询数据并手动渲染
			    	    var datas = getPageCommentList(obj.curr,obj.limit);
			    	    //渲染数据
			    	    renderCommentData(datas);
			    	    //首次不执行
			    	    /* if(!first){
			    	      //do something
			    	    } */
			      	}
			});
		}
	});
}
</script>



<script src='static/prism/prism.js'></script>
</body>
</html>