<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Sam.BC.Liu">

<title>LbC's Login</title>

<!-- Bootstrap Core CSS -->
<link href="${BootstrapCoreCSS}" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${MetisMenuCSS}" rel="stylesheet">

<!-- Custom CSS -->
<link href="${CustomCSS}" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${CustomFonts}" rel="stylesheet" type="text/css">


</head>

<body>

	<div class="container">
		<div class="col-md-6 col-md-offset-4">
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message != null}">
				<div class="alert alert-danger">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">成功退出.</div>
			</c:if>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" > 请   登   录</h3>
					</div>
					<div class="panel-body">
						<form:form role="form" id="loginForm" method="post"
							action="${rootURL}login" modelAttribute="user">
							<fieldset>
								<div class="form-group">
									<input type="text" id="username" name="username"
										class="form-control" placeholder="UserName" autofocus>

								</div>
								<div class="form-group">

									<input type="password" id="password" name="password"
										class="form-control" placeholder="Password" />

								</div>
								<div class="form-group">
									<input type="checkbox" id="remember-me" name="remember-me" />
									<label for="remember-me">Remember Me</label>

								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-4">
										<input type="submit" class="btn btn-lg btn-success btn-block"
											value="Login">
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="${jQuery}"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${BootstrapCoreJavaScript}"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="${MetisMenuPluginJavaScript}"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${CustomThemeJavaScript}"></script>

</body>

</html>