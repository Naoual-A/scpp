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
			<c:if test="${empty grupo.alias}">
				<h3 class="page-header">Nuevo Grupo</h3>
			</c:if>
			<c:if test="${not empty grupo.alias}">
				<h3 class="page-header">Editar Grupo</h3>
			</c:if>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">

					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">

								<form:form method="POST" modelAttribute="grupo"
									class="form-horizontal">
									<!-- Hidden fields -->

									<div class="form-group">
										<label for="contenido" class="col-sm-2 control-label">ALIAS</label>
										<div class="col-sm-10">
											<form:input path="alias" class="form-control" placeholder="Alias" />
											<form:errors path="alias" cssClass="text-danger"/>
										</div>
									</div>

									<div class="form-group">
										<label for="destino" class="col-sm-2 control-label">CURSO</label>
										<c:if test="${empty grupo.curso.alias}">
											<div class="col-sm-10">
												<form:select path="curso.id" class="form-control"
													placeholder="curso">
													<form:option value="${null}" label="Seleccione un curso" />
													<form:options items="${cursos}" itemValue="id"
														itemLabel="alias" />
												</form:select>
												<form:errors path="curso.id" cssClass="text-danger"/>
											</div>
										</c:if>
										<c:if test="${not empty grupo.curso.alias}">
											<div class="col-sm-10">
												<%-- <form:input type="hidden" path="curso.id" /> --%>
												<form:select path="curso.id" class="form-control" >
<%-- 													<form:option value="null" label="Seleccione un curso" /> --%>
													<form:options items="${cursos}" itemValue="id"
														itemLabel="alias" />
												</form:select>		
												<form:errors path="curso.id" cssClass="text-danger"/>										
											</div>
										</c:if>
									</div>
									
									<div class="form-group">
										<label for="destino" class="col-sm-2 control-label">TUTOR</label>
										<c:if test="${empty grupo.tutor.id}">
											<div class="col-sm-10">
												<form:select path="tutor.id" class="form-control"
													placeholder="curso">
													<form:option value="null" label="Seleccione un tutor" />
													<form:options items="${tutores}" itemValue="id"
														itemLabel="fullName" />
												</form:select>
											</div>
										</c:if>
										<c:if test="${not empty grupo.tutor.id}">
											<div class="col-sm-10">
												<%-- <form:input type="hidden" path="tutor.id" /> --%>
												<form:select path="tutor.id" class="form-control" >
 													<form:option value="null" label="Seleccione un tutor" />
													<form:options items="${tutores}" itemValue="id"
														itemLabel="fullName" />
												</form:select>
											</div>
										</c:if>

									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<c:if test="${empty grupo.alias}">
												<button type="submit" class="btn btn-success">Crear</button>
											</c:if>
											<c:if test="${!empty grupo.alias}">
												<button type="submit" class="btn btn-success">Guardar
													cambios</button>
											</c:if>
											<sec:authorize access="hasRole('ADMIN')">
											<a href="<c:url value='/grupos/gruposadmin'/>" class="btn btn-danger">Cancelar</a>
											</sec:authorize>
											<sec:authorize access="hasRole('PROFESOR')">
											<a href="<c:url value='/grupos/misgrupo'/>" class="btn btn-danger">Cancelar</a>
											</sec:authorize>
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