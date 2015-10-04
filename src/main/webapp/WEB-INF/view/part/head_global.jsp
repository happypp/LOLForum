<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.9.2.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/style.css" />">
<script src="<c:url value="/js/jquery_bootstrap.min.js" />"></script>
<script>
//获取根路径，如： http://localhost:8080
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

//获取项目根路径，如： http://localhost:8080/ems
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
//用户是否登录
function isLogin(){
 return !$("#avatar").attr("data-uid") == "";
}
</script>