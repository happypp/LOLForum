<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit">
        <title>${subject.title }_LOLForum_讨论</title>
        <%@ include file="part/head_global.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/summernote.css" />">

        <link rel="stylesheet" href="<c:url value="/css/post.css" />">

    </head>
    <body>
        <%@ include file="part/header.jsp" %>
        <p id="pin">
        </p>
        <div id="post" class="container middle-box" data-pageid="3" data-sid="${subject.id}">
            <!-- 发帖人 -->
            <div id="post-title" class="title-font">
                <h3><a href="#">${subject.title }</a></h3>
                <div class="subject">
                    <a href="#"><span>综合</span></a>
                </div>
            </div>
            <div class="post-contents">
                <div class="row">
                    <div class="post-avatar">
                        <div class="content">
                            <a href="#"><img class="img-circle subjectAvatar" data-avatar="${author.avatar }" /></a>
                        </div>
                    </div><!--内容显示-->
                    <div class="post-content">
                        <div class="authorName">
                            ${author.name }
                        </div>
                    </div>
                    <span>
                        <small>
                            ${createTime } 
                        </small>
                    </span>
                    <div class="article">
                        <div class="article-content">
                            ${subject.content }
                        </div>
                        <div class="msg">
                            <ul class="clearfix nav navbar-nav">
                                <li>
                                    <a href="#"><h4>创建者</h4><img class="img-circle subjectAvatar" data-avatar="${author.avatar }" /><span> ${createTime }</span></a>
                                </li>
                                <li>
                                    <a href="#"><h4>最后回复</h4><img class="img-circle postAvatar" data-avatar="${lastPost.userAvatar }" /><span> ${lastPost.postTime}</span></a>
                                </li>
                                <li>
                                    <a><h4>帖子数量</h4>
                                        <p>
                                            ${count }
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <a><h4>浏览次数</h4>
                                        <p>
                                            ${subject.visits }
                                        </p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--回复帖子-->
                <c:forEach items="${pvos}" var="pvo">
                    <div class="row">
                        <div class="post-avatar">
                            <div class="content">
                                <a href="#"><img class="img-circle postAvatar" data-avatar="${pvo.userAvatar }"></a>
                            </div>
                        </div><!--内容显示-->
                        <div class="post-content">
                            <div class="authorName">
                                ${pvo.userName }
                            </div>
                        </div>
                        <span>
                            <small>
                                ${pvo.postTime } 
                            </small>
                        </span>
                        <div class="article">
                            <div class="article-content">
                                ${pvo.content }
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%@ include file="part/pagination.jsp" %>
            <div id="editor">
                <button class="btn btn-default" id="new-reply" data-container="body" data-toggle="popover" data-placement="top" data-content="请登录方可回复">
                    回复本主题
                </button>
                <input id="summernote" name="content">
                </input>
            </div>
        </div><!-- 热门主题
        <div id="hotSubject">
        <div class="container">
        <table class="table table-striped">
        <h3>Suggested Topics</h3>
        <thead>
        <tr>
        <th>
        <small>
        Topic
        </small>
        </th>
        <th>
        <small>
        Category
        </small>
        </th>
        <th>
        <small>
        Posts
        </small>
        </th>
        <th>
        <small>
        Views
        </small>
        </th>
        <th>
        <small>
        Activity
        </small>
        </th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        </tr>
        </tbody>
        </table>
        </div>
        </div> -->
        <%@ include file="part/footer.jsp" %>
        <script src="<c:url value="/js/summernote.min.js" />"></script>
        <script>
            function popoverOut($v){
                $v.popover("hide");
            }

            $(document).ready(function(){


                /*回复按钮 监听事件*/
                $("#new-reply").click(function(){
                    if (!isLogin()) {
                        $("#new-reply").popover("show");
                        setTimeout("popoverOut($(\"#new-reply\"))", 2000);
                        return;
                    }

                    var sid = $("#post").data("sid");
                    var uid = $("#avatar").data("uid");


                    var content = $("#summernote").code();

                    if (content.length > 11) {
                    	$("#new-reply").attr("disabled","disabled");
                        var url = getProjectRootPath() +
                        "/discuss/subject/savePost";
                        $.postJSON(url, {
                            uid: uid,
                            sid: sid,
                            content: content.replace("\"", "|")
                        }, function(data, status){
                            if (data == 1) {
                                location.reload();
                            }
                        });
                    }
                });

                /*文本编辑器*/
                $('#summernote').summernote({
                    lang: "zh-CN",
                    toolbar: [['style', ['style', 'bold', 'italic', 'underline', 'clear']], ['font', ['fontname', 'fontsize']], ['font', ['strikethrough', 'superscript', 'subscript']], ['color', ['color']], ['para', ['paragraph']], ['height', ['height']], ['insert', ['hr', 'link', 'picture']], ['misc', ['undo', 'redo', 'codeview', 'fullscreen']]],
                    height: 250,
                    minHeight: 250,
                    maxHeight: 250,
                    focus: false
                });

                // 加载发帖人头像
                var imageUrl = $(".subjectAvatar").attr("data-avatar");
                if (imageUrl != null && imageUrl.length > 0) {

                    $(".subjectAvatar").attr("src", getAvatarImage() + imageUrl);
                }
                //加载回复帖子人的头像
                $(".postAvatar").each(function(index, element){
                    var imageUrl = $(this).data("avatar");
                    if (imageUrl != null &&
                    imageUrl.length > 0) {
                        $(this).attr("src", getAvatarImage() + imageUrl);
                    }
                });
            });
        </script>
    </body>
</html>
