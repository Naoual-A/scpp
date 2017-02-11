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
						<h3 class="page-header">Mis Tareas</h3>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<div class="row">
					<div class="col-lg-12">
						<!-- <div class="panel panel-default"> -->

						<div class="panel-body">
							<sec:authorize access="hasAnyRole('ALUMNO', 'PADRE')">
							<c:if test="${empty tareas }">
								<div class="alert alert-info">
									<p>No tiene tareas asignadas.</p>
								</div>
							</c:if>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('ADMIN', 'PROFESOR')">
							<c:if test="${empty tareas }">
								<div class="alert alert-info">
									<p>No tiene tareas creadas.</p>
								</div>
							</c:if>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('ADMIN', 'PROFESOR')">
							<a href="<c:url value='/tareas/nuevo' />" class="btn btn-success">Nueva
								tarea</a> <br></sec:authorize>
							<br>
							<table class="table table-striped table-bordered" id="tabla">
								<tr>
									<th>ID</th>
									<th>CREADOR</th>
									<th>TITULO</th>
									<th>ASIGNATURA</th>
									<th>FECHA INICIO</th>
									<th>FECHA FIN</th>
									<th>ESTADO</th>
									<th></th>	
									<sec:authorize access="hasAnyRole('ADMIN', 'PROFESOR')">
										<th></th>
										<th></th>
									</sec:authorize>
								</tr>
								<c:forEach items="${tareas}" var="tarea">
									<tr data-toggle="modal" data-id="3" data-target="#orderModal">
										<td>${tarea.id}</td>
										<td>${tarea.creador.nombre} ${tarea.creador.apellidos}</td>
										<td>${tarea.titulo}</td>
										<td>${tarea.asignatura.fullAlias}</td>
										<td>${tarea.fechaInicio}</td>
										<td>${tarea.fechaFin}</td>
										<td id="estado">
										<c:if test="${tarea.activa eq true}"> ACTIVA</c:if>
										<c:if test="${tarea.activa eq false}"> CERRADA</c:if>
										</td>
										<td><a href="<c:url value='/tareas/ver/${tarea.id}' />"
											class="btn btn-info">Ver</a></td>
										<sec:authorize access="hasAnyRole('ADMIN', 'PROFESOR')">
											<td><a
												href="<c:url value='/tareas/editar/${tarea.id}' />"
												class="btn btn-warning">Editar</a></td>
											<td><a href="javascript:alerta(${tarea.id})"
												class="btn btn-danger">Eliminar</a></td>
										</sec:authorize>
									</tr>
								</c:forEach>
							</table>
<%-- 							<c:forEach var="i" begin="1" end="${total }">
								<a href="<c:url value='/tareas/${i}' />">${i}</a>
							</c:forEach> --%>
							
							<script>
								function alerta(id) {
									var x;
									if (confirm("¿Está seguro de que desea eliminar la tarea "
											+ id + "?") == true) {
										window.location = "http://localhost:8080/scpp/tareas/borrar/"
												+ id;
									} else {
										window.location = "http://localhost:8080/scpp/tareas/mistareas";
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