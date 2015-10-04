// JavaScript Document

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
    
    // 加载头像
    $(".table img").each(function(index, element){
        var imageUrl = $(this).data("image");
        if (imageUrl != null && imageUrl.length > 0) {
            $(this).attr("src", getRootPath() + imageUrl);
        }
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
