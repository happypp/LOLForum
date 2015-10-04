<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<script>
		 // 为顶部导航栏确定当前所在版块
		 var id = $(".middle-box").attr("data-pageid");
		 var li = ".top-box .nav li:nth-child(" + id + ")";
		 $(li).addClass("active");
</script>
<div class="bottom-box">
	<div class="info-box" style="width:170px;">
		<p class="t">导航</p>
		<ul>
			<li><a href="#">网站首页</a></li>
			<li><a href="#">资讯专栏</a></li>
			<li><a href="#">讨论版区</a></li>
			<li><a href="#">问答系统</a></li>
		</ul>
	</div>
	<div class="info-box" style="width:210px;">
		<p class="t">友情链接</p>
		<ul>
			<li><a href="http://discuss.cocos2d-x.org/" target="_blank">Coscos2d-x社区</a></li>
			<li><a href="http://www.bootcss.com/" target="_blank">Bootstrap中文网</a></li>
			<li><a href="http://lol.qq.com/" target="_blank">英雄联盟官方网站</a></li>
			<li><a href="https://www.processon.com/network" target="_blank">ProcessOn在线绘图</a></li>
		</ul>
	</div>
	<div class="info-box tec" style="width:530px;">
		<p class="t">我们用到的技术</p>
		<ul>
			<li><button class="btn btn-default">Bootstrap</button></li>
			<li><button class="btn btn-default">Lucene</button></li>
			<li><button class="btn btn-default">jQuery</button></li>
			<li><button class="btn btn-default">Font-Awesome</button></li>
			<li><button class="btn btn-default">Summernote</button></li>
			<li><button class="btn btn-default">SpringMVC&Hibernate&Spring</button></li>
		</ul>
	</div>
	<div class="info-box" style="width:90px;">
		<p class="t">关于</p>
		<ul>
			<li><a href="#">关于我们</a></li>
			<li><a href="#">内容报错</a></li>
			<li><a href="#">联系方式</a></li>
		</ul>
	</div>
</div>
<a href="#pin" title="回到顶部" id="icon-double"></a>

<div id="overlay"></div>


<script src="<c:url value="/js/jquery-ui-1.9.2.min.js" />"></script>
<script>


//登录
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

//显示登录对话框
function showLoginDialog(){
 function mouserMove(){
     return false;
 }
 $("#login").modal("show");
}

//隐藏登录对话框
function hideLoginDialog(){
 $("#login").modal("hide");
}

//取消页面滚动.$(window).
//注册scroll(scrollHandler).解绑$(window).off("scroll", scrollHandler)
var scrollHandler = function(){
 $(window).scrollTop();
}

$(document).ready(function(){
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
             "background-color": "rgba(255, 255, 255, .7)",
             "box-shadow": "0px 3px 3px rgba(152,152,152,.5)"
         });
         $(".top-box .nav").css("margin-top", "0px");
         $(".top-box .nav li a").css("padding-top", "8px");
         $(".user").css({
             "margin-top": "0px",
             "padding-top": "0px"
         });
         $(".middle-box").css("margin-top","85px");
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
         $(".middle-box").css("margin-top","0");
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
         $("#icon-double").html("<i class='fa fa-angle-double-up fa-3x'></li>");
     }
     else {
         $("#icon-double").html("");
     }
 });
 
});

</script>
