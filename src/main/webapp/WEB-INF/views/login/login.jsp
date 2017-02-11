<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SCPP - LOGIN</title>

<link href="<c:url value='/static/css/bootstrap.min.css' />"
	rel="stylesheet"></link>

<!-- MetisMenu CSS -->
<link href="<c:url value='/static/css/metisMenu.min.css' />"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/static/css/sb-admin-2.css' />"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="<c:url value='/static/css/font-awesome.min.css' />"
	rel="stylesheet">
</head>

<body style="background-color:#FFB338">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title">Acceso</h3>
					</div>
					<div class="panel-body" id="login">
						<c:url var="loginUrl" value="/login" />
						<form role="form" action="${loginUrl}" method="post">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>Se ha desconectado correctamente.</p>
								</div>
							</c:if>
							<c:if test="${param.sesion != null}">
								<div class="alert alert-warning">
									<p>Su sesión ha caducado. Por favor, vuelva a loguearse.</p>
								</div>
							</c:if>
							<c:if test="${param.login != null}">
								<div class="alert alert-success">
									<p>Se ha dado de alta correctamente.</p>
								</div>
							</c:if>
							<fieldset>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="username" style="background-color:#fcf8e3"><i class="fa fa-user"></i></label> 
									<input class="form-control" placeholder="ID" name="id" type="text" autofocus required>
								</div>
								<div class="input-group input-sm">
									<label class="input-group-addon" for="password" style="background-color:#fcf8e3"><i class="fa fa-lock"></i></label> 
									<input class="form-control" placeholder="Password" name="password" type="password"
										value="" required>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								<!--                                 <div class="input-group input-sm">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div> -->
								<!-- Change this to a button or input when using this as a form -->
								<div class="input-sm">
									<input type="submit" class="btn btn-success btn-block" value="Acceder">
								</div>
								<br>
								<div class="input-sm">
									<a href="<c:url value='/NuevoUsuario'/>"
												class="btn btn-danger btn-block">Dar de alta</a>
								</div>
<%-- 								<div class="input-sm">
									<a href="<c:url value='/home'/>"
										class="btn btn-success btn-block">Acceder</a>
								</div> --%>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>

</body>

</html>