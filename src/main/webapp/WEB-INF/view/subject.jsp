<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>LOLForum_讨论</title>
<%@ include file="part/head_global.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/summernote.css" />">
<link rel="stylesheet" href="<c:url value="/css/subject.css" />">
</head>
<body>
	<%@ include file="part/header.jsp"%>
	<a id="pin"></a>
	<div class="middle-box" data-pageid="3">
		<div class="category">
			<button class="btn btn-default active">综合</button>
			<button class="btn btn-default">经验交流</button>
			<button class="btn btn-default">闲聊</button>
			<button class="btn btn-default">资源分享</button>
			<button class="btn btn-default pull-right" id="new-subject"
				data-placement="bottom" data-content="请先登录！">
				<i class="fa fa-plus" style="margin-right:3px;"></i>新主题
			</button>
		</div>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th width="570px">主题</th>
					<th width="110px">创建</th>
					<th width="130px">参与</th>
					<th style="text-align:center">回复</th>
					<th style="text-align:center">浏览</th>
					<th style="text-align:center">动态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${svos }" var="svo" varStatus="status">
					<tr>
						<td class="t-title"><a
							href="<c:url value="/discuss/subject/posts/${svo.sid }/1" />">${svo.title }</a></td>

						<td class="t-author"><a href="#"><img class="img-circle"
								data-image="${svo.author.userAvatar }" data-placement="bottom"
								data-content="${svo.author.userName }"
							></a> <time>${svo.author.postTime}</time></td>
						<td><c:forEach items="${svo.users }" var="info"
								varStatus="status">
								<a href="#"><img class="img-circle"
									data-image="${info.userAvatar }" data-placement="bottom"
									data-content="${info.userName } 于${info.postTime }"></a>
							</c:forEach></td>
						<td class="t-reply">${svo.reply }</td>
						<td class="t-vivits">${svo.visits }</td>
						<td class="t-activity">${svo.lastReply }</td>
					</tr>
				</c:forEach>
				<script>
				// 加载头像
			    $(".table img").each(function(index, element){
			        var imageUrl = $(this).data("image");
			        if (imageUrl != null && imageUrl.length > 0) {
			            $(this).attr("src", getRootPath() + imageUrl);
			        }
			    });
				</script>
			</tbody>
		</table>
		<!-- 文本编辑器 -->
		<div id="write-win">
			<div class="title-input">
				<label for="email" class="control-label"> 主题 </label> <input
					type="text" class="form-control" id="titleInput" name="text"
					placeholder="不得多于50字">
				<button class="btn btn-default" id="">
					<i class="fa fa-share" style="margin-right:3px;"></i>发表
				</button>
				<div class="info text-danger"></div>
				<div class="spin-icon">
					<i class="fa fa-spinner fa-spin"></i>
				</div>
			</div>
			<button type="button" class="close c-close">
				<span aria-hidden="true">&times;</span>
			</button>
			<div id="summernote"></div>
		</div>
		<!-- /文本编辑器 -->
		<%@ include file="part/pagination.jsp"%>
	</div>
	<%@ include file="part/footer.jsp"%>
	<script src="<c:url value="/js/summernote.min.js" />"></script>
	<script>
	function infoFadeOut(){
	    $("#write-win .title-input .info").fadeOut();
	}

	function popoverOut($v){
	    $v.popover("hide");
	}

	$(document).ready(function(e){

	    // 发表新主题点击事件
	    $("#new-subject").click(function(){
	        if (!isLogin()) {
	            $("#new-subject").popover("show");
	            setTimeout("popoverOut($(\"#new-subject\"))", 2000);
	            return;
	        }
	        $(this).attr("disabled", "disabled");
	        $("#write-win").fadeIn();
	    });
	    
	    $("#write-win .close").click(function(){
	        $("#new-subject").removeAttr("disabled");
	        $("#write-win").fadeOut();
	    });
	    
	    // 为头像增加鼠标移过监听事件
		$(".table img").hover(function(){
	        $(this).popover("show");
		},function(){
	        $(this).popover("hide");
		});
	    
	    // 初始化Summernote
	    $('#summernote').summernote({
	        height: 250,
	        lang: "zh-CN",
	        toolbar: [['style', ['style', 'bold', 'italic', 'underline', 'clear']], ['font', ['fontname', 'fontsize']], ['font', ['strikethrough', 'superscript', 'subscript']], ['color', ['color']], ['para', ['paragraph']], ['height', ['height']], ['insert', ['hr', 'link', 'picture']], ['misc', ['undo', 'redo', 'codeview', 'fullscreen']]]
	    });
	    
	    // 发表按钮点击事件
	    $("#write-win .title-input button").click(function(){
	        // 服务器处理前自行检验
	        var $input = $("#write-win .title-input input");
	        var $info = $("#write-win .title-input .info");
	        var s1 = "*主题标题不得为空！";
	        var s2 = "*主题标题过长！";
	        var s3 = "*主题内容不得为空！";
	        if ($input.val().trim == null || $input.val().length == 0) {
	            if ($info.text() != "" && $info.text() != s1) {
	                clearTimeout("infoFadeOut()");
	                $info.text(s1);
	                setTimeout("infoFadeOut()", 5000);
	            }
	            else {
	                $info.text(s1);
	                $info.fadeIn();
	                setTimeout("infoFadeOut()", 5000);
	            }
	        }
	        else 
	            if ($input.val().length > 50) {
	                if ($info.text() != "" && $info.text() != s2) {
	                    clearTimeout("infoFadeOut()");
	                    $info.text(s2);
	                    setTimeout("infoFadeOut()", 5000);
	                }
	                else {
	                    $info.text(s2);
	                    $info.fadeIn();
	                    setTimeout("infoFadeOut()", 5000);
	                }
	            }
	            else 
	                if ($("#summernote").code() == "<p><br></p>") {
	                    if ($info.text() != "" && $info.text() != s3) {
	                        clearTimeout("infoFadeOut()");
	                        $info.text(s3);
	                        setTimeout("infoFadeOut()", 5000);
	                    }
	                    else {
	                        $info.text(s3);
	                        $info.fadeIn();
	                        setTimeout("infoFadeOut()", 5000);
	                    }
	                }
	                else {
	                    clearTimeout("infoFadeOut()");
	                    
	                    $info.removeClass("text-danger");
	                    $info.addClass("text-success");
	                    $info.text("主题发表中");
	                    $info.show();
	                    $(".title-input .fa-spin").show();
	                    
	                    $(".title-input button").attr("disabled", "disabled");
	                    $(".title-input input").attr("disabled", "disabled");
	                    $("#write-win .note-editable").attr("contenteditable", "false");
	                    
	                    // 发表主题请求
	                    var url = getProjectRootPath() + "/discuss/saveSubject";
						$.postJSON(url,{
	                        uid: $("#avatar").data("uid"),
	                        title: $(".title-input input").val(),
	                        content: $("#summernote").code()
	                    }, function(data, status){
	                        if (data == 1) {
	                            location.reload();
	                        }
	                    });
	                }
	    });
	});
	</script>
</body>
</html>
