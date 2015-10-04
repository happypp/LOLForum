
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
	if (($("#name").val().length < 15 && $("#name").val().length > 4)
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

