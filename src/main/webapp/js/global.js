// JavaScript Document

// 获取根路径，如： http://localhost:8080
function getRootPath(){
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath;
};

// 获取项目根路径，如： http://localhost:8080/ems
function getProjectRootPath(){
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
};

$.postJSON = function(url, data, callback){
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json',
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};

// 登录
function login(){
    var $warn = $("#login .alert strong");
    if ($("#emailInput").val() == "" || $("#pwdInput").val() == "") {
        $warn.text("用户名和密码不能为空！");
        $("#login .alert").show();
        return;
    }
    var url = getProjectRootPath() + "/user/login";
    $.post(url, {
        "email": $("#emailInput").val(),
        "pwd": $("#pwdInput").val()
    }, function(data, status){
        if (data == 1) {
            location.reload();
        }
        else {
            $warn.text("用户名或密码错误！");
            $("#login .alert").show();
        }
    });
}

// 显示登录对话框
function showLoginDialog(){
    function mouserMove(){
        return false;
    }
    $("#login").modal("show");
}

// 隐藏登录对话框
function hideLoginDialog(){
    $("#login").modal("hide");
}

// 取消页面滚动.$(window).
// 注册scroll(scrollHandler).解绑$(window).off("scroll", scrollHandler)
var scrollHandler = function(){
    $(window).scrollTop();
}

// 用户是否登录
function isLogin(){
    return !$("#avatar").attr("data-uid") == "";
}

$(document).ready(function(){
    // 为顶部导航栏确定当前所在版块
    var id = $(".middle-box").attr("data-pageid");
    var li = ".top-box .nav li:nth-child(" + id + ")";
    $(li).addClass("active");
    
    // 加载头像
    var imageUrl = $("#avatar").attr("data-avatar");
    if (imageUrl != null && imageUrl.length > 0) {
        $("#avatar").attr("src", getRootPath() + imageUrl);
        $("#avatar-box .btn").hide();
        $("#avatar").show();
    }
    
    // 固定顶部导航栏
    var $nav = $(".top-box"); //得到导航对象
    var win = $(window); //得到窗口对象
    var sc = $(document);//得到document文档对象。
    win.scroll(function(){
        if (sc.scrollTop() >= 15) {
            $nav.css({
                padding: "5px"
            });
            $("#logo img").attr("src", getProjectRootPath() + "/image/logo_simple.png");
            $(".top-box").css({
                "height": "50px",
                "background-color": "rgba(255, 255, 255, 6)",
                "box-shadow": "0px 3px 3px rgba(152,152,152,.5)"
            });
            $(".top-box .nav").css("margin-top", "0px");
            $(".top-box .nav li a").css("padding-top", "8px");
            $(".user").css({
                "margin-top": "0px",
                "padding-top": "0px"
            });
            $nav.addClass("fixed-nav");
        }
        else {
            $("#logo img").attr("src", getProjectRootPath() + "/image/logo.png");
            $(".top-box").css({
                "height": "",
				"background-color": "rgba(255, 255, 255, 0)",
                "box-shadow": ""
            });
            $(".top-box .nav").css("margin-top", "15px");
            $(".top-box .nav li a").css("padding-top", "10px");
            $(".user").css({
                "margin-top": "15px",
                "padding-top": "5px"
            });
            $nav.removeClass("fixed-nav");
        }
    });
    
    // 邮箱输入框自动完成
    var mailSuffix = ["@qq.com", "@sina.com", "@163.com", "@gmail.com", "@126.com", "@hotmail.com", "@vip.qq.com", "@foxmail.com", "@sohu.com", "@aliyun.com", "@yahoo.com"];
    // 自动完成.改变候选项
    var auto = function(mailSuffix){
        var hints = new Array(mailSuffix.length);
        var str = $("#emailInput").val();
        str = str.split("@")[0];
        for (var i = 0; i < hints.length; i++) {
            hints[i] = str + mailSuffix[i];
        }
        return hints;
    };
    $("#emailInput").autocomplete({
        source: function(request, response){
            response(auto(mailSuffix));
        }
    });
    
    // 登录模态对话框.监听事件
    $("#login").on("show.bs.modal", function(){
        $(window).scroll(scrollHandler);
        $("#overlay").height($(document).height());
        $("#overlay").width($(document).width());
        $("#overlay").fadeIn(200);
    });
    
    $("#login").on("hide.bs.modal", function(){
        $(window).off("scroll", scrollHandler);
        $("#overlay").fadeOut(200);
    });
    
    // 登录警告信息.关闭监听事件
    $("#login .c-close").click(function(){
        $("#login .alert").hide();
    });
    
    // 登出
    $("#avatar").click(function(){
        if (isLogin()) {
            var url = getProjectRootPath() + "/user/logout";
            $.post(url, null, function(data, status){
                if (data == 1) {
                    location.reload();
                }
            });
        }
    });
    
    // 回到顶部
    window.addEventListener("scroll", function(){
        var afterScroll = $("body").scrollTop();
        if (afterScroll > 100) {
            $("#icon-double").html("<i class='icon-double-angle-up icon-4x'></li>");
        }
        else {
            $("#icon-double").html("");
        }
    });
    
});
