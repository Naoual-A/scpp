<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <%@ include file="../general/navbar.jsp" %> --%>
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
						<c:if test="${empty tarea.tarea.titulo}">
							<h3 class="page-header">Nueva tarea</h3>
						</c:if>
						<c:if test="${not empty tarea.tarea.titulo}">
							<h3 class="page-header">Editar tarea</h3>
						</c:if>
					</div>
					<!-- /.col-lg-12 -->
				</div>

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">

										<form:form method="POST" modelAttribute="tarea"
											class="form-horizontal">
											<!-- Hidden fields -->
											<form:input type="hidden" path="tarea.id" />
											<form:input type="hidden" path="tarea.creador.id" />
											<form:input type="hidden" path="tarea.activa" />

											<div class="form-group">
												<label for="destino" class="col-sm-2 control-label">Asigantura</label>
												<c:if test="${empty tarea.tarea.asignatura.alias}">
													<div class="col-sm-5">
														<form:select path="tarea.asignatura.id"
															class="form-control" placeholder="Asignatura">
															<form:option value="-1" label="Seleccione una asignatura" />
															<form:options items="${asignaturas}" itemValue="id"
																itemLabel="fullAlias" />
														</form:select>
														<form:errors path="tarea.asignatura.id"
															cssClass="text-danger" />
													</div>
												</c:if>
												<c:if test="${not empty tarea.tarea.asignatura.alias}">
													<div class="col-sm-5">
														<form:select path="tarea.asignatura.id"
															class="form-control">
															<form:options items="${asignaturas}" itemValue="id"
																itemLabel="fullAlias" />
														</form:select>
														<form:errors path="tarea.asignatura.id"
															cssClass="text-danger" />
													</div>
												</c:if>
											</div>

											<div class="form-group">
												<label for="titulo" class="col-sm-2 control-label">TITULO</label>
												<div class="col-sm-5">
													<form:input class="form-control" path="tarea.titulo"
														placeholder="Título" />
													<form:errors path="tarea.titulo" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<label for="contenido" class="col-sm-2 control-label">CONTENIDO</label>
												<div class="col-sm-5">
													<form:textarea rows="6" path="tarea.contenido"
														class="form-control" placeholder="Descripción" />
													<form:errors path="tarea.contenido" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<label for="fechaInicio" class="col-sm-2 control-label">FECHA INICIO</label>
												<div class="col-sm-5">
													<div class="input-group">
														<form:input type="datetime-local" class="form-control"
															path="inicio" />
														<span class="input-group-addon"
															style="background-color: #fcf8e3"><i
															class="fa fa-calendar" aria-hidden="true"
															style="color: #B75E05"></i></span>
													</div>
													<form:errors path="inicio" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<label for="fechaFin" class="col-sm-2 control-label">FECHA FIN</label>
												<div class="col-sm-5">
													<div class="input-group">
														<form:input type="datetime-local" class="form-control"
															path="fin" />
														<span class="input-group-addon"
															style="background-color: #fcf8e3"><i
															class="fa fa-calendar" aria-hidden="true"
															style="color: #B75E05"></i></span>
													</div>
													<form:errors path="fin" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<c:if test="${empty tarea.tarea.titulo}">
														<button type="submit" class="btn btn-success">Crear</button>
													</c:if>
													<c:if test="${!empty tarea.tarea.titulo}">
														<button type="submit" class="btn btn-success">Guardar
															cambios</button>
													</c:if>
													<a href="<c:url value='/tareas/mistareas'/>"
														class="btn btn-danger">Cancelar</a>
												</div>
											</div>

										</form:form>
									</div>
								</div>
							</div>
						</div>
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