<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>LOLForum_注册</title>
<%@ include file="part/head_global.jsp"%>
<link rel="stylesheet" href="<c:url value="/css/regist.css" />">
</head>
<body>
	<div class="container ">
		<img src="./image/logo.png">
		<h2 class="col-sm-offset-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎加入LOLForum</h2>
		<form action="" class="form-horizontal" id="form" method="post">
			<div class="form-group has-feedback">
				<label for="lastname" class="col-sm-4 control-label font">
					邮箱 </label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="email" id="email"
						value="" placeholder="请输入邮箱" aria-describedby="inputStatus"> 
					<span id="email-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
				</div>
				<span class="glyphicon form-control-feedback"></span> 
				<span class="col-sm-4 error text" id="email-msg"></span>
			</div>
			<div class="form-group  has-feedback">
				<label for="firstname" class="col-sm-4 control-label font">
					昵称 </label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="name" id="name" value="" placeholder="昵称长度(1~15)" aria-describedby="inputStatus"	>
					<span id="name-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
				</div>
				<span class="glyphicon form-control-feedback"></span> <span class="col-sm-4 error text" id="name-msg"></span>
			</div>
			<div class="form-group has-feedback">
				<label for="lastname" class="col-sm-4 control-label font">
					密码 </label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="pwd" id="pwd"
						value="" placeholder="输入密码(5~10)" aria-describedby="inputStatus">
						<span id="pwd-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
				</div>
				<span class="glyphicon form-control-feedback"></span> <span
					class="col-sm-4 error text" id="pwd-msg"></span>
			</div>
			<div class="form-group has-feedback">
				<label for="lastname" class="col-sm-4 control-label font">
					确认密码 </label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="rpwd" id="rpwd"
						value="" placeholder="确认密码" aria-describedby="inputStatus"><span id="rpwd-info" class="glyphicon  form-control-feedback"
						aria-hidden="true"></span>
				</div>
				<span class="glyphicon form-control-feedback"></span> <span
					class="col-sm-4 error text" id="rpwd-msg"></span>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-10">
					<button id="registBtn" type="" class="btn col-sm-2"
						style="background-color:#f0a860" onclick="validate()">完成注册</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="index" type="button" class="btn btn-default">返回首页</a>
				</div>
			</div>
			<div id="warning"></div>
	</div>
	</form>
	<%@ include file="part/footer.jsp"%>
	<script>
	$("#name").blur(function(){
		var url = "user/registVal/name?name="+$("#name").val();
		$.post(url, null, function(data){
			if(data == ""){
				$("#name-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
				$("#name-msg").text("");
			}else{
				
				$("#name-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
				$("#name-msg").text(data);
			}
	    });
	});

	$("#email").blur(function(){
		var url = "user/registVal/email?email="+$("#email").val();
		$.post(url, null, function(data){
			if(data == ""){
				$("#email-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
				$("#email-msg").text("");
			}else{
				$("#email-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
				$("#email-msg").text(data);
			}
	    });
	});

	$("#pwd").blur(function(){
		var url = "user/registVal/pwd?pwd="+$("#pwd").val();
		$.post(url, null, function(data){
			if(data == ""){
				$("#pwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
				$("#pwd-msg").text("");
			}else{
				$("#pwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
				$("#pwd-msg").text(data);
			}
	    });
	});

	$("#rpwd").blur(function(){
		var url = "user/registVal/rpwd?pwd="+$("#pwd").val()+"&rpwd="+$("#rpwd").val();
		$.post(url, null, function(data){
			if(data == "" && ($("#pwd").val().length < 11 && $("#pwd").val().length > 4)){
				$("#rpwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
				$("#rpwd-msg").text("");
			}else {
				$("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
				$("#rpwd-msg").text(data);
			}
	    });
	});


	function validate(){
		
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; //验证邮箱的正则
		if (($("#name").val().length < 15 && $("#name").val().length > 0)
				&& reg.test($("#email").val())
				&& !($("#email-msg").text()=="邮箱已存在请重新填写")
				&& ($("#pwd").val().length < 11 && $("#pwd").val().length > 4)
				&& ($("#pwd").val() == ($("#rpwd").val()))) {
				$("#registBtn").attr("type","submit");
				
				$("#form").attr("action","user/regist");
		}else{
				$("#registBtn").attr("type","button");
				$("#warning").html("<div class='alert alert-danger'><a href='#' class='close' data-dismiss='alert'>&times;</a><strong>警告！</strong>亲，请填写好您的基本资料，方便我们注册~！</div>");			
		}
	}
	</script>
</body>
</html>
