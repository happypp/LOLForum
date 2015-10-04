// JavaScript Document
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
			var url = getProjectRootPath() +"/discuss/subject/savePost";
			alert(url);
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
    
        $(".subjectAvatar").attr("src", getRootPath() + imageUrl);
    }
    //加载回复帖子人的头像
	$(".postAvatar").each(function(index, element){
        var imageUrl = $(this).data("avatar");
        if (imageUrl != null && imageUrl.length > 0) {
            $(this).attr("src", getRootPath() + imageUrl);
        }
    });
});

