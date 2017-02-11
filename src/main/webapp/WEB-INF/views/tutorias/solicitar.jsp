<!DOCTYPE html>
<%@page import="java.util.Date"%>
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
	<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
	
</head>
<body>
<div id="wrapper">
	<div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
				<h3 class="page-header">Tutorías disponibles para la semana</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!-- <div class="panel panel-default"> -->

					<div class="panel-body">
	<div class="row">
    <div class="col-md-15 col-sm-3">
	<div class="well" id="lunes"> ${col[0]} ${map.get(col[0])}
			<br><br>
			<c:forEach var="cita" items="${map2.get(col[0])	}">
				<a  href="javascript:alerta(${cita.id})"  class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br>
			</c:forEach> 
			</div></div>
	<div class="col-md-15 col-sm-3">		
	<div class="well" id="martes"> ${col[1]} ${map.get(col[1])}
			<br><br>
			<c:forEach var="cita" items="${map2.get(col[1])}">
				<a  href="javascript:alerta(${cita.id})"  class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br>
			</c:forEach>
			</div></div>		
	<div class="col-md-15 col-sm-3">
	<div class="well" id="miercoles"> ${col[2]} ${map.get(col[2])}
			<br><br>
			<c:forEach var="cita" items="${map2.get(col[2])}">
				<a  href="javascript:alerta(${cita.id})"  class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br>
			</c:forEach>
			</div></div>		
	<div class="col-md-15 col-sm-3">
	<div class="well" id="jueves"> ${col[3]} ${map.get(col[3])}
			<br><br>
			<c:forEach var="cita" items="${map2.get(col[3])}">
				<a  href="javascript:alerta(${cita.id})"  class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br>			</c:forEach>
			</div></div>		
	<div class="col-md-15 col-sm-3">
	<div class="well" id ="viernes"> ${col[4]} ${map.get(col[4])}
			<br><br>
			<c:forEach var="cita" items="${map2.get(col[4])}">
<%-- 			<a href="<c:url value='/tutorias/solicitar/${cita.id}' />" class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br> --%>			
				<a  href="javascript:alerta(${cita.id})"  class="btn btn-success btn-lg btn-block">${cita.hora}</a><br><br>

			</c:forEach>

			</div>		
    </div>
</div></div>

						</div>
			</div>
		</div>
</div>
								<script>
			function alerta(id, hora){
				var x;
				if(confirm("¿Está seguro de que desea solicitar la tutoría?") == true){
					window.location = "http://localhost:8080/scpp/tutorias/solicitar/"+id;
				} else {
					window.location = "http://localhost:8080/scpp/tutorias/solicitar";
				}
			}
			</script>
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