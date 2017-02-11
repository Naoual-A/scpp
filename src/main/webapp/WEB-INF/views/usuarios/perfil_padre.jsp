<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../general/navbar.jsp" />
<jsp:include page="../general/sidebar.jsp" /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SCPP</title>
<link href="<c:url value='/static/css/bootstrap.min.css' />"
	rel="stylesheet"></link>

<!-- MetisMenu CSS -->
<link href="<c:url value='/static/css/metisMenu.min.css' />"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/static/css/sb-admin-2.css' />"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<c:url value='/static/css/morris.css' />" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<c:url value='/static/css/font-awesome.min.css' />"
	rel="stylesheet">
</head>
<body>
<div id="wrapper">
	<div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
				<h3 class="page-header">Detalle usuario</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			
		<div class=" col-md-9 col-lg-9 "> 
		<table class="table table-user-information">
			<tbody>
				<tr>
					<td>Id:</td>
					<td>${user.id}</td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td>${user.nombre}</td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td>${user.apellidos}</td>
				</tr>
				<tr>
					<td>Rol:</td>
					<td>${user.rol.rol}</td>
				</tr>
				
				<tr>
					<td>Estado</td>
					<td>${user.estado}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><a href="mailto:${user.email}">${user.email}</a></td>
				</tr>
				
				<tr>
					<td>Perfil vinculado: </td>
					<td><a href="<c:url value='/usuarios/perfilvinculado/${user.idAlumno }'/>">${user.idAlumno}</a></td>
				</tr>

			</tbody>
		</table>
	</div>
	<div class="col-md-3 col-lg-3 " align="center"> 
				<img alt="User Pic" src="/scpp/static/imagenes/User_Accounts.png" height="100" width="100"> 
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