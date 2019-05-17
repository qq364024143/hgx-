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
<meta name="description" content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- layui.css放到最前面，防止与本地样式冲突 -->
<link rel="stylesheet" href="static/layui/css/layui.css">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">

<style type="text/css">
.comment_list {
	padding-top: 40px;
	width: 100%;
	margin: 0 auto;
	background-color: #eee;
}
.comment_time{
	font-size: small;
    color: darkgray;
    margin-left: 20px;
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
.del_comment:hover{
	color:black;
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
.root_comment_name {
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
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty requestScope.rootComment }">
			<div class="root_comment">
				<div class="imgdiv">
					<img class="imgcss"
						src="${requestScope.rootComment.commentUserUserPicture }" />
				</div>
				<div class="root_comment_details">
					<input type="hidden" id="rootCommentId" value="${requestScope.rootComment.commentId }"/>
					<input type="hidden" id="bokeId" value="${requestScope.rootComment.bokeId }"/>
					<span class="root_comment_name">${requestScope.rootComment.commentUserNickname }</span> 
					<span class="comment_time">${requestScope.rootComment.commentTime }</span>
					<div class="comment_content">${requestScope.rootComment.commentContent }</div>
					<div class="del">
						<!-- <i class="icon layui-icon">赞(164)</i>  <a class="del_comment" href="javascript:;"
					data-id="1"> <i class="icon layui-icon">删除</i></a>-->
					</div>
				</div>
				<hr height="1px">
			</div>
		</c:when>
		<c:otherwise>
			<h3>未查到数据...</h3>
		</c:otherwise>
	</c:choose>


	<div class="comment_list">
		<!-- <div class="comment">
			<div class="imgdiv">
				<img class="imgcss" src="./images/user.jpg" />
			</div>
			<div class="conmment_details">
				<span class="comment_name">大白 </span> <span>22分钟前</span>
				<div class="comment_content">感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒</div>
				<div class="del">
					<i class="icon layui-icon">赞(164)</i> <a class="del_comment"
						data-id="1"> <i class="icon layui-icon">删除</i></a>
				</div>
			</div>
			<hr>
		</div> -->
		
		<!-- <div class="comment">
			<div class="imgdiv">
				<img class="imgcss" src="./images/user.jpg" />
			</div>
			属性data-user-id用来保存用户id,html标签的属性 可以自定义，可以用来保存数据，方便拿数据
			<div class="comment_details" data-user-id="用户id" >
				<span class="comment_name">大白 </span> <span>22分钟前</span>
				<div class="comment_content">感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒感觉林丹越来越帅了，好棒</div>
				<div class="del">
					<i class="icon layui-icon">赞(164)</i> <a class="del_comment"
						data-id="1"> <i class="layui-icon-reply-fill icon layui-icon" onclick="createReplyTextArea('userNickname','commentId')">回复</i></a>
				</div>
				用于创建文本域所在位置
				<div id="commentId"></div>
			</div>
			<hr>
		</div> -->
		
	</div>
	<div class="comment_add_or_last"></div>

</body>

<script src="static/js/jquery-2.1.1.min.js"></script>
<script src="static/layui/layui.js"></script>

<script>
	
	var timeoutId;
	var cpage = 1;
	//滚动加载下一页
	layui.use("layer",function(){
	$(function() {//页面加载事件
		//初始化页面数据
		var isScroll = inintPage();
	
		if(isScroll){//初始化后出现滚动条且总页数大于当前页
		$(window).scroll(function() {//滚动条事件
			//$(window).scrollTop()这个方法是当前滚动条滚动的距离
			//$(window).height()获取当前窗体的高度
			//$(document).height()获取当前文档的高度
			//文档高度=滚动距离+窗口高度
			var bot = 20; //bot是底部距离的高度
			if ((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
			//当底部基本距离+滚动的高度〉=文档的高度-窗体的高度时；
			
			var index = layer.load(1, {shade: [0.1,'#fff']}); //0.1透明度的白色背景
			
			//我们需要去ajax加载数据了
				var responseDatas = getCommentPage(cpage,10);
				if(responseDatas!=undefined){
					//渲染数据
					renderDatasToPage(responseDatas.data);
					layer.close(index);//渲染完成后移除通知
					if(responseDatas.pageNums<=cpage){
						$(window).unbind("scroll");//移除事件
						$(".comment_add_or_last").html("没有更多了");
					}else{
						cpage++;
					}
				}else{
					$(".comment_add_or_last").html("加载失败");
				}
			}
			
		});
	}
	});
	});
	//初始化页面，当数据能使页面能出现滚动条为止，或查完都没出现滚动条 
	function inintPage(){
		var windowHeight = $(window).height();//获取当前窗体的高度
		var docHeight = $(document).height();//获取当前文档的高度
		var scrollBottomHeight = 20;
		var isScroll = true;
		if(docHeight<=windowHeight+scrollBottomHeight){
			//获取数据
			var responseDatas = getCommentPage(cpage,10);
			if(responseDatas!=undefined){
				//渲染数据
				renderDatasToPage(responseDatas.data);
				if(responseDatas.pageNums<=cpage){//总页数小于 等于当前页则
					//window.clearTimeout(timeoutId);
					$(".comment_add_or_last").html("没有更多了");
					isScroll=false;//不绑定滚动条事件
				}else{//总页数大于当前页则继续
					cpage++;
					timeoutId = window.setTimeout(inintPage(),800);//800ms后执行一次，且只执行一次 
				}
				
			}else{
				//清除调用
				clearTimeout(timeoutId);
				$(".comment_add_or_last").html("加载失败");
			}
			
		}else{
			//清除调用
			clearTimeout(timeoutId);
			isScroll = true;//总页数大于当前页且已经出现滚动条，则绑定滚动条事件
		}
		return isScroll;
	}

	//ajax获取数据
	function getCommentPage(cpage,limit) {
		var responseData = {};
		var rootCommentId = $("#rootCommentId").val();
		if(rootCommentId==undefined){
			rootCommentId = '';
		}
		$.ajax({
			type:'get',
			url:'<%=basepath%>comment/findPageForCommentReplys',
			data:{'rootCommentId':rootCommentId,'cpage':cpage,'limit':limit},
			dataType:'json',
			async:false,//不使用异步，会导致后台查到数据但是前端拿不到数据，相当于多线程
			success:function(data){
				responseData = data;
			}
		});
		return responseData;
	}
	
	//渲染数据
	function renderDatasToPage(data){
		
		var commentList = $(".comment_list");
		
		if(data==undefined||data.length==0){
			$(".comment_add_or_last").html("没有更多了 ");
			return;
		}
		var html = '';
		for(var i=0;i<data.length; i++){
			html +='<div class="comment">';
			html +='<div class="comment_details" data-user-id="'+data[i].commentUserId+'" >';
			html +='<span class="comment_name">'+data[i].commentUserNickname+'&nbsp;<font color="black">回复</font>&nbsp;'+data[i].parentCommentUserNickname+' </span> <span class="comment_time">'+data[i].commentTime+'</span>';
			html +='<div class="comment_content">'+data[i].commentContent+'</div>';
			html +='<div class="del">';
			html +='<a class="del_comment" data-id="1"> ';
			html +='<i class="layui-icon-reply-fill icon layui-icon" onclick="createReplyTextArea(\''+data[i].commentUserNickname+'\',\''+data[i].commentId+'\')">回复</i></a>';
			html +='</div>';
			html +='<div id="'+data[i].commentId+'"></div>';
			html +='</div>';
			html +='<hr>';
			html +='</div>';
		}
		
		commentList.append(html);
	}
	
	//创建回复文本域commentId用于将文本域拼接在哪个位置以及提交数据传参和获取textarea
	function createReplyTextArea(commentUserNickname,commentId){
		//判断是否已经存在id为commentId的文本域，是则代表同一个回复点击多次
		if($("#"+commentId+"_textarea").length!=0){
			return;
		}
		//移除前面的textarea
		$(".replyTextArea").remove();
		var html = '<div class="replyTextArea">' 	
		html += '<textarea name="desc" id="'+commentId+'_textarea" placeholder="回复'+commentUserNickname+'" class="layui-textarea" onblur="textareaOnBlur(this)" ></textarea>';
		html += '<button class="layui-btn" lay-submit lay-filter="formDemo" style="margin:10px 0 10px 0;" onclick="submitReply(\''+commentId+'\')">回复</button>';
		html += '</div>';
		$("#"+commentId).append(html);
	}
	
	function textareaOnBlur(textarea){
		var textareaVla = $(textarea).val();
		if(textareaVla==''){
			$(".replyTextArea").remove();
		}
	}
	
	//提交回复
	function submitReply(commentId){
		var textareaVal =$("#"+commentId+"_textarea").val();
		if(textareaVal==''){
			layui.use("layer",function(){
				layer.msg('回复内容不能为空', {
				    time: 1500, //1500ms后自动关闭
				  });
			});
			return;
		}
		var rootCommentId = $("#rootCommentId").val();
		var bokeId = $("#bokeId").val();
		var commentUserId = $("#"+commentId).parent().attr("data-user-id");
		if(commentUserId==undefined){
			commentUserId = "";
		}
		console.log({commentId:commentId,rootCommentId:rootCommentId,commentContent:textareaVal,bokeId:bokeId,parentCommentUserId:commentUserId});
		//ajax提交
		jQuery.ajax({
		type:'post',
		url:"<%=basepath%>comment/saveComment",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({bokeId:bokeId,rootCommentId:rootCommentId,parentCommentId:commentId,parentCommentUserId:commentUserId,commentType:'1',commentContent:textareaVal}),
		dataType:'json',
		async:false,//设为同步执行
		success:function(data){
			if(data.code=='200'){
				
				layui.use("layer",function(){
					layer.msg('评论成功，重新进入后显示评论', {
					    time: 1500, //1500ms后自动关闭
					  });
					//成功则移除文本域
					$(".replyTextArea").remove();
				});
			}else{
				layui.use("layer",function(){
					layer.msg('评论失败', {
					    time: 1500, //1500ms后自动关闭
					  });
				})
			}
		}
	});
	}
</script>
</html>
