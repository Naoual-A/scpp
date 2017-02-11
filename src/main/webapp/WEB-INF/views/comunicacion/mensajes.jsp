<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
						<h3 class="page-header">Mis mensajes recibidos</h3>
					</div>
					<!-- /.col-lg-12 -->
				</div>

				<div class="row">
					<div class="col-lg-12">
						<!-- <div class="panel panel-default"> -->

						<div class="panel-body">
							<c:if test="${empty mensajes }">
								<div class="alert alert-info">
									<p>No tiene mensajes recibidos.</p>
								</div>
							</c:if>
							<table class="table table-striped table-bordered">
								<tr>
									<th>DE</th>
									<th>ASUNTO</th>
									<th>FECHA</th>
									<th></th>
								</tr>
								<c:forEach items="${mensajes}" var="mensaje">
									<c:if test="${!mensaje.leido}">
										<tr>
											<td><b>${mensaje.fuente.nombre}
													${mensaje.fuente.apellidos}</b></td>
											<td><b>${mensaje.asunto}</b></td>
											<td><b>${mensaje.fecha}</b></td>
											<td><a href="<c:url value='/mensajes/${mensaje.id}' />"
												class="btn btn-info">Leer</a></td>
										</tr>
									</c:if>
									<c:if test="${mensaje.leido}">
										<tr>
											<td>${mensaje.fuente.nombre}${mensaje.fuente.apellidos}</td>
											<td>${mensaje.asunto}</td>
											<td>${mensaje.fecha}</td>
											<td><a href="<c:url value='/mensajes/${mensaje.id}' />"
												class="btn btn-info">Leer</a></td>
										</tr>
									</c:if>
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