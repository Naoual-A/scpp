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
				<div class="alta-panel panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title">Dar de alta</h3>
					</div>
					<div class="panel-body" id="alta">
						<form:form method="POST" modelAttribute="user"
									class="form-horizontal">
									<!-- Hidden fields -->
			
			<div class="form-group">
				<div class="col-sm-12">
					<form:input class="form-control" path="idAlumno" placeholder="NIE ALUMNO"/>
					<form:errors path="idAlumno" cssClass="text-danger"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12">
					<form:input path="id" class="form-control" placeholder="NIE PADRE"/>
					<form:errors path="id" cssClass="text-danger"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-12">
					<form:input type="email" path="email" class="form-control" placeholder="EMAIL"/>
					<form:errors path="email" cssClass="text-danger"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12">
					<form:input path="nombre" class="form-control" placeholder="NOMBRE"/>
					<form:errors path="nombre" cssClass="text-danger"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12">
					<form:input path="apellidos" class="form-control" placeholder="APELLIDOS"/>
					<form:errors path="apellidos" cssClass="text-danger"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-12">
					<form:input type="password" path="password" class="form-control" placeholder="CONTRASEÑA"/>
					<form:errors path="password" cssClass="text-danger"/>
				</div>
			</div>
			
<%-- 			<div class="form-group">
				<div class="col-sm-12">
					<form:input type="password" path="password" class="form-control" placeholder="CONFIRMAR CONTRASEÑA"/>
				</div>
			</div>			 --%>
			<div class="form-group">
    			<div class="col-sm-offset-0 col-sm-12">
      				<button type="submit" class="btn btn-success btn-block">Dar de Alta</button>
      				<a href="<c:url value='/login'/>" class="btn btn-danger btn-block">Cancelar</a>
    			</div>
  			</div>

								</form:form>
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