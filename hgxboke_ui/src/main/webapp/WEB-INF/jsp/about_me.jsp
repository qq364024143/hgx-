<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basepath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="个人博客" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="static/css/base.css" rel="stylesheet">
<link href="static/css/index.css" rel="stylesheet">
<link href="static/css/m.css" rel="stylesheet">
</head>
<body>
<%--静态包含 --%>
<%@ include file="/WEB-INF/jsp/common_menu.jsp"%>

<article style="margin-top:100px;">
  <h1 class="t_nav"><span>像“草根”一样，紧贴着地面，低调的存在，冬去春来，枯荣无恙。</span><a href="index" class="n1">网站首页</a><!-- <a href="/" class="n2">关于我</a> --></h1>
  <div class="ab_box">
    <div class="leftbox">
      <div class="newsview">
        <div class="news_infos">
          	<h3>本项目使用技术：</h3>
          	<ul>
          		<li>1、spring cloud：eureka、feign、config、zuul、hystrix、turbine</li>
          		<li>2、数据库：mysql、redis，使用到mysql全文检索 match(column1,column2) against('key1 key2' with boolean model)</li>
          		<li>3、前台：spring boot、jsp</li>
          		<li>4、前端：layui框架、jquery->js库</li>
          		<li>5、其他插件： hanlp分词、pageHelper分页插件</li>
          	</ul>
          	<br/>
          	<br/>
          	<h3>以实现功能：</h3>
          	<ul>
          		<li>1、首页：最新15篇博客</li>
          		<li>2、博客列表：分类搜索、关键字搜索（全文检索）、分页查询、处理XSS攻击、随机推荐博客10篇 order by random</li>
          		<li>3、写博客：写博客</li>
          		<li>4、收藏记录：收藏于各个网站的资源可通过添加收藏录入、可通过关键字查询</li>
          		<li>5、博客详情：查询出10篇相关博客（通过当前博客标题分词检索）、博客评论（可匿名，分页）、回复、查看全部回复（无限滚动加载分页）、上一篇/下一篇博客</li>
          	</ul>
          	<br/>
          	<br/>
          	<h3>待实现功能：</h3>
          	<ul>
          		<li>1、博客管理：删、改、查（个人）</li>
          		<li>2、收藏管理：删、改、查（个人）</li>
          		<li>3、权限控制：登录（SSO单点登录）、细颗粒权限（每个uri均可管控）、菜单管理</li>
          		<li>4、后台管理系统：权限管理、分类管理、博客管理、评论审核、收藏记录审核、“关于我”动态编辑（非静态html）等</li>
          		<li>5、以实现功能优化：（1）关键字搜索优化，搜索结果相关性更高；（2）加入API文档管理swagger；（3）日志补全和管理（flume+kafka基于配置的提取日志）；</li>
          		<li>6、SSO单点登录系统</li>
          	</ul>
          	<br/>
          	<br/>
          	<h3>总结：</h3>
          	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;做这个项目之前本来只打算搭建一下springcloud的，没想到做到现在。其实我一直都想做一个这样的项目，能写一些日记、记录一些学习到的技术和做项目时遇到的一些问题就行了，
          		这个想法从2016年就有了，而且在上班空闲的时候也做过，当时是用SSM做的（因为那时候也不会多少东西），搭建了一个叫note的项目，从网上下载了html模板，只做了写笔记（用的百度的富文本编辑器，虽然功能齐全，
          		支持插入代码关键字高亮）和查笔记的功能，但是有一天TM不小心把项目删了，然后就没做了。后面心里一直想着这个事情，虽然有时间但是就是不想动手......
          	</p>
          	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2019年找工作的时候突然发现：尼玛了！之前只要会个SSM框架，写过两个项目投个简历就有无数面试，但是现在投几百份都没几个面试电话，有面试都是要会springcloud。而且都不是问项目实际开发中用到的东西，我就*了。后面
          		就以一边面试一边网上查东西，JVM（内存分析、优化、垃圾回收）、SSM源码、HashMap源码、Java并发包concurrent、mysql索引原理（B+Tree，其实索引就是数据结构）、spring boot自动配置原理、自定义spring boot组件 xxx-starter等等。其实感觉自己会很多东西，但是只会用不精通，一问深一点就不行了
          		所以就想学一学spring cloud，刚开始觉得很难，就去学dubbo了，发现dubbo入门很简单，只需要写一个接口、一个实现类再发布一下就行，服务端dubbo:service客户端dubbo:reference。后来试着学spring cloud，在网上下了个电子书《spring cloud实战》瞿永超的，按常用组件分章节介绍，很容易懂，花了两小时看了个大概，发现spring cloud并不是一个框架而是微服务的统称，相当于eureka、feign等组件的统称。
          		微服务就是这些组件的大集合，而这些组件使用起来也很简单，每个组件都是独立的，在spring boot上加入注解（一般以Enable开头）再配置一下application.properties就行。后来就做了这个项目。。。。
          	</p>
        </div>
      </div>
    </div>
    <div class="rightbox">
      <div class="aboutme">
        <h2 class="hometitle">关于我</h2>
        <div class="avatar"> <img src="static/images/avatar.jpg"> </div>
        <div class="ab_con">
          <p>网名：小何</p>
          <p>职业：Java开花 </p>
          <p>个人微信：xxxx</p>
          <p>邮箱：xxxxx@qq.com</p>
        </div>
      </div>
      <div class="weixin">
        <h2 class="hometitle">QQ</h2>
        <ul>
          <img src="static/images/qq.jpg">
        </ul>
      </div>
    </div>
  </div>
</article>
<footer>
  <p>Design by <a href="/">个人博客</a> <a href="/">蜀ICP备xxxxxx号-1</a></p>
</footer>
<script src="static/js/nav.js"></script>
</body>
</html>
