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
						<h3 class="page-header">Nuevo Mensaje</h3>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">

										<form:form method="POST" modelAttribute="mensaje"
											class="form-horizontal">

											<div class="form-group">
												<label for="destino" class="col-sm-2 control-label">PARA</label>
												<c:if test="${empty mensaje.destino.id}">
													<div class="col-sm-10">
														<form:select path="destino.id" class="form-control"
															placeholder="PARA">
															<form:option value="${null}"
																label="Seleccione un destinatario" />
															<form:options items="${dest}" itemValue="id"
																itemLabel="FullName" />
														</form:select>
														<form:errors path="destino.id" cssClass="text-danger" />
													</div>
												</c:if>
												<c:if test="${not empty mensaje.destino.id}">
													<div class="col-sm-10">
														<form:input type="hidden" path="destino.id" />
														<form:input class="form-control" path="destino.FullName" />
													</div>
												</c:if>

											</div>

											<div class="form-group">
												<label for="fuente" class="col-sm-2 control-label">DE</label>
												<div class="col-sm-10">
													<c:if test="${error eq 'si'}">
														<input class="form-control" value="${fuente.fullName}" />
													</c:if>
													<c:if test="${empty error }">
														<form:input type="hidden" path="fuente.id" />
														<form:input class="form-control" path="fuente.FullName" />
													</c:if>
												</div>
											</div>

											<div class="form-group">
												<label for="asunto" class="col-sm-2 control-label">ASUNTO</label>
												<div class="col-sm-10">
													<form:input type="text" class="form-control" path="asunto"
														placeholder="ASUNTO" />
													<form:errors path="asunto" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<label for="cuerpo" class="col-sm-2 control-label">MENSAJE</label>
												<div class="col-sm-10">
													<form:textarea rows="10" maxlenth="5000" path="cuerpo"
														class="form-control" placeholder="Introduzca su mensaje" />
													<form:errors path="cuerpo" cssClass="text-danger" />
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<button type="submit" class="btn btn-success">Enviar</button>
													<a href="<c:url value='/mensajes/recibidos'/>"
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