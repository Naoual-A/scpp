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
				<h3 class="page-header">Listado de grupos</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!-- <div class="panel panel-default"> -->

					<div class="panel-body">
						<sec:authorize access="hasRole('ADMIN')">
						<a href="<c:url value='/grupos/nuevo' />" class="btn btn-success">Nuevo grupo</a>
						<br><br>
						</sec:authorize>
	
		<c:if test="${empty grupos }">
			<div class="alert alert-info">
				<p>No tiene grupos tutorizados.</p>
			</div>
		</c:if>
		<c:if test="${param.error != null }">
			<div class="alert alert-danger">
				<p>${param.error }.</p>
			</div>
		</c:if>
							<table class="table table-striped table-bordered">
			<tr>
				<th>ID</th>
				<th>ALIAS</th>
				<th>TUTOR</th>
				<th>CURSO</th>
				<th></th>
				<th></th>
				<sec:authorize access="hasRole('ADMIN')">
				<th></th>
				<th></th>
				</sec:authorize>
			</tr>
			<c:forEach items="${grupos}" var="grupo">
				<tr>
					<td>${grupo.id}</td>
					<td>${grupo.alias}</td>
					<td>${grupo.tutor.fullName}</td>
					<td>${grupo.curso.alias}</td>
					<td><a href="<c:url value='/grupos/ver/${grupo.id}' />" class="btn btn-info">Ver</a></td>
					<td><a href="<c:url value='/grupos/alumnos/${grupo.id}' />" class="btn btn-success">Alumnos</a></td>
					<sec:authorize access="hasRole('ADMIN')">
					<td><a href="<c:url value='/grupos/editar/${grupo.id}' />" class="btn btn-warning">Editar</a></td>
					<td><a  href="javascript:alerta(${grupo.id})"  class="btn btn-danger">Eliminar</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>	
		<script>
			function alerta(id){
				var x;
				if(confirm("¿Está seguro de que desea eliminar el grupo " + id + "?") == true){
					window.location = "http://localhost:8080/scpp/grupos/borrar/"+id;
				} else {
					window.location = "http://localhost:8080/scpp/grupos/gruposadmin";
				}
			}
			</script>
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