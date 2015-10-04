<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>LOLForum_资讯</title>
<%@ include file="part/head_global.jsp"%>
<style>
	/* CSS Document */
	body{ font-size:14px; }
	
	/*面包屑导航条*/
	#news-list{
		width:1000px;
		overflow: hidden;
	}
	
	.breadcrumb { 
		padding: 8px 30px;
	}
	
	/*新闻内容*/
	#list-title {
		width: 640px;
		float: left;
		height: 63px;
		display: inline;
		margin-left: 25px;
		line-height: 63px;
		font-weight: 700;
		font-size: 20px;
		text-indent: 70px;
		color: #E08931;
		background: url(../../image/list_logo.png) no-repeat;
	}
	#list-title + #list {
		width: 770px;
		overflow: hidden;
		border-top: 0 none;
	}
	#list ul{
		margin-top:20px;
		font-size:16px;
	}
	#list ul a{
		text-decoration:none;
		color:#000;
	}
	#list ul a:hover{
		color:#FFC100;
	}
	#list .art {padding: 5px 30px;}
	#list .art span {
		float: right;
		margin-left: 5px;
		color: #41002A;
	}
</style>
</head>

<body>
	<%@ include file="part/header.jsp"%>
	<a id="pin"></a>
	
	<div id="news-list" class="container middle-box" data-pageid="2">
    	<!--面包屑导航-->
        <div >
        	<ol class="breadcrumb">
              <li><a href="<c:url value="/index" />"><i class="fa fa-home fa-lg"></i></a></li> 
              <li class="active"><a>资讯</a></li> 
            </ol>
        </div>
        
        <div id="list-title">LOL权威新闻</div>
        <div id="list">
        	<c:forEach items="${list }" var="a" varStatus="status">
        	<ul class="art">
            	<span>${a.time }</span>
                <a href="<c:url value="/article/show/${a.id }" />" title="${a.title }">${a.title }</a>
            </ul>
            </c:forEach>
        </div>
        <%@ include file="part/pagination.jsp"%>
    </div>
	
	<%@ include file="part/footer.jsp"%>
	<script src="js/unslider.min.js"></script>
	
</body>
</html>
