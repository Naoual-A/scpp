<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
				<h3 class="page-header">Nuevo Curso</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">

					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">

								<form:form method="POST" modelAttribute="curso"
									class="form-horizontal">
									<!-- Hidden fields -->
			
			<div class="form-group">
				<label for="titulo" class="col-sm-2 control-label">ID</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="id" placeholder="Id"/>
					<form:errors path="id" cssClass="text-danger"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="contenido" class="col-sm-2 control-label">ALIAS</label>
				<div class="col-sm-10">
					<form:input path="alias" class="form-control" placeholder="Alias"/>
					<form:errors path="alias" cssClass="text-danger"/>
				</div>
			</div>	

			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
    				<c:if test="${empty curso.alias}">
      				<button type="submit" class="btn btn-success">Crear</button>
      				</c:if>
      				<c:if test="${!empty curso.alias}">
      				<button type="submit" class="btn btn-success">Guardar cambios</button>
      				</c:if>
      				<a href="<c:url value='/cursos/miscursos'/>" class="btn btn-danger">Cancelar</a>
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