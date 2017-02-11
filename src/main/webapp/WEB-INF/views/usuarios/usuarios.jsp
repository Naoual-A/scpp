<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
				<h3 class="page-header">Listado de usuarios</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!-- <div class="panel panel-default"> -->

					<div class="panel-body">
					
		<table class="table table-striped table-bordered">
			<tr>
				<th>ID</th>
				<th>NOMBRE</th>
				<th>APELLIDOS</th>
				<th>ROL</th>
				<th>EMAIL</th>
				<th>ESTADO</th>
				<th></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.nombre}</td>
					<td>${user.apellidos}</td>
					<td>${user.rol.rol} </td>					
					<td>${user.email}</td>
					<td>${user.estado}</td>
					<td><a href="<c:url value='/usuarios/${user.id}' />" class="btn btn-info">Ver</a></td>
				</tr>
			</c:forEach>
		</table>	
						</div>
				<!-- </div> -->
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