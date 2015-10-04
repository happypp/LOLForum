<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="top-box">
	<nav class="top-nav">
		<a id="logo" href=""> <img src="<c:url value="/image/logo.png" />" alt="LOLForum" />
		</a>
		<ul class="nav navbar-nav">
			<li><a href="<c:url value="/index" />">首&nbsp;页</a></li>
			<li><a href="<c:url value="/article/list/1" />">资&nbsp;讯</a></li>
			<li><a href="<c:url value="/discuss/subjects/1" />">讨&nbsp;论</a></li>
			<li><a href="#">问&nbsp;答</a></li>
		</ul>
		<div class="user">
			<div id="avatar-box">
				<a class="btn" onClick="showLoginDialog()"><i
					class="fa fa-user fa-lg"></i>登录</a>
				<a><img class="img-circle" id="avatar" data-uid="${sessionScope.user.id }" data-name="${sessionScope.user.name }"
				data-avatar="${sessionScope.user.avatar }"></a>
			</div>
				<script>
				// 加载头像
					var imageUrl = $("#avatar").attr("data-avatar");
					if (imageUrl != null && imageUrl.length > 0) {
						$("#avatar").attr("src", getRootPath() + imageUrl);
						$("#avatar-box .btn").hide();
						$("#avatar").show();
					}
				</script>
			<a class="btn" id="search" href="#"><i
				class="fa fa-search fa-lg"></i></a>
			<!-- 登录对话框 -->
			<div class="modal fade container" id="login" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="false">
				<div class="modal-dialog " style="width:450px;">
					<div class="modal-content">
						<div class="modal-header text-center">
							<h4>
								欢迎登录
								<button type="button" class="btn btn-lg" data-dismiss="modal"
									style="position:absolute; right:5px; top:2px; background-color:#f0a860">&times;
								</button>
							</h4>
						</div>
						<div class="modal-body center-block">
							<div class="form-horizontal row">
								<div class="form-group center-block">
									<label for="email" class="col-sm-4 control-label">邮箱</label>
									<div class="col-sm-6">
										<input type="email" class="form-control" id="emailInput"
											name="email" placeholder="xxxx@xx.com">
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-sm-4 control-label">密码</label>
									<div class="col-sm-6">
										<input type="password" class="form-control" id="pwdInput"
											name="password" placeholder="请输入密码">
									</div>
								</div>

								<div class="form-group div-checkbox">
									<div class="col-sm-offset-4 col-sm-8">
										<div class="checkbox">
											<label> <input type="checkbox"> 记住我
											</label>
										</div>
									</div>
								</div>

								<!-- 警告框 -->
								<div class="alert alert-danger alert-dismissible fade in" role="alert">
									<button type="button" class="close c-close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong></strong>
								</div>
								<!-- /警告框 -->

								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn col-sm-10"
										style="background-color:#f0a860;margin-bottom:15px;"
										onClick="login()">登录</button>
								</div>
								<div class="col-sm-offset-2 col-sm-10">
									<a href="<c:url value="/registUI" />" onClick="hideLoginDialog()">创建新账号</a> <a
										href="#" style="margin-left:150px;" onClick="">忘记密码？</a>
								</div>
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /登录对话框 -->
	</nav>
</div>