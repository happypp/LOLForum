<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>${article.title }_LOLForum_资讯</title>
<%@ include file="part/head_global.jsp"%>

<style>
/* CSS Document */
body {
  font-size: 14px;
}

#news-content {
  width: 1000px;
  overflow: hidden;
}

/*主题内容*/
#contentFull #article h1 {
  text-align: center;
  font: bold 22px/70px \5fae\8f6f\96c5\9ed1;
  color: #424242;
  height: 50px;
  overflow: hidden;
}

#contentFull #article address {
  text-align: center;
  color: #41002C;
  line-height: 30px;
  margin: 0 20px;
  padding-bottom: 10px;
  border-bottom: 1px #EAD6A2 solid;
}

#contentFull #article address .breadcrumb>li+li:before {
  color: #CCCCCC;
  content: "| ";
  padding: 0 5px;
}

#contentFull #article address .breadcrumb {
  background-color: rgba(0, 0, 0, 0);
}

#contentFull #article blockquote.paper {
  margin: 10px auto;
  width: 835px;
  padding: 2px 2px 2px 55px;
  background: #fff url(../image/paper.jpg) no-repeat 2px 2px;
}

#contentFull #article blockquote.paper p {
  font-size: 14px;
  line-height: 20px;
  overflow: hidden;
  text-indent: 2em;
}

#text p img {
  margin-bottom: 5px;
}

.a-content{padding:20px;text-indent:20px;}
</style>
</head>

<body>
	<%@ include file="part/header.jsp"%>

	<a id="pin"></a>
	<div id="news-content" class="container middle-box" data-pageid="2">
		<!--面包屑导航-->
		<div>
			<ol class="breadcrumb">
				<li><a href="<c:url value="/index" />"><i class="fa fa-home fa-lg"></i></a></li>
				<li class="active"><a href="<c:url value="/article/list/1" />">资讯</a></li> 
				<li class="active">${article.title }</li>
			</ol>
		</div>

		<!--主题内容-->
		<div id="contentFull">
			<div id="article">
				<h1>${article.title }</h1>
				<address>
					<ol class="breadcrumb">
						<li>时间：${article.time }</li>
						<li>${article.source }</li>
					</ol>
				</address>
				<div class="a-content">${article.content }</div>
			</div>
		</div>
	</div>

	<%@ include file="part/footer.jsp"%>
</body>
</html>
