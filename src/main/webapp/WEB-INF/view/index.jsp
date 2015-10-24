<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>LOLForum_首页</title>
<%@ include file="part/head_global.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/index.css" />">

</head>
<body>

	<%@ include file="part/header.jsp"%>
	<a id="pin"></a>
	<div class="middle-box c-box" data-pageid="1">
		<div id="carousel">
			<div id="myCarousel" class="carousel slide">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active">
					</li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="image/lol1.jpg" alt="Five slide">
						<p>标题1标题1标题1标题1标题1标题1标题1</p>
					</div>
					<div class="item">
						<img src="image/lol2.jpg" alt="Second slide">
						<p>标题2标题2标题2标题2标题2标题2标题2</p>
					</div>
					<div class="item">
						<img src="image/lol3.jpg" alt="Third slide">
						<p>标题3标题3标题3标题3标题3标题3</p>
					</div>
					<div class="item">
						<img src="image/lol4.jpg" alt="Four slide">
						<p>标题4标题4标题4标题4标题4标题4标题4</p>
					</div>
					<div class="item ">
						<img src="image/lol5.jpg" alt="First slide">
						<p>标题5标题5标题5标题5标题5标题5</p>
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myCarousel"
					data-slide="prev"
				>&lsaquo;</a> <a class="carousel-control right" href="#myCarousel"
					data-slide="next"
				>&rsaquo;</a>
			</div>
		</div>
		<div id="hot" class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">最新动态&nbsp;&nbsp;</h3>
			</div>
			<div class="panel-body">
				<ul>
					<c:forEach items="${lastArt }" var="art" varStatus="status">
						<li><a href="article/show/${art.id }" title="${art.title }">${art.title }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div id="lists">
			<div id="hotArt" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">最热资讯&nbsp;&nbsp;</h3>
				</div>
				<div class="panel-body">
					<ul>
						<c:forEach items="${hotArt }" var="art" varStatus="status">
							<li><a href="article/show/${art.id }" title="${art.title }">${art.title }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div id="hotSub" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">最热讨论&nbsp;&nbsp;</h3>
				</div>
				<div class="panel-body">
					<ul>
						<c:forEach items="${hotSub }" var="sub" varStatus="status">
							<li><a href="discuss/subject/posts/${sub.id }/1" title="${sub.title }">${sub.title }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div id="hotQ" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">最热问答&nbsp;&nbsp;</h3>
				</div>
				<div class="panel-body">
					<ul>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="part/footer.jsp"%>
	<script src="/js/unslider.min.js">
		
	</script>
	<script>
		function lunbo() {
			$("#myCarousel").carousel({
				interval : 2000
			});
		}

		$(document).ready(function() {
			lunbo();
		});
	</script>
</body>
</html>
