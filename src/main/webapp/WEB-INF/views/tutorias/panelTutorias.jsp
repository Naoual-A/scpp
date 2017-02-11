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
<body onload="cambia_color()">
	
<div id="wrapper">
	<div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
				<h3 class="page-header">Panel de tutorías</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!-- <div class="panel panel-default"> -->

					<div class="panel-body">
	-Seleccionar las casillas deseadas para habilitar una tutoría
	<br>
	-Deseleccionar las casillas deseadas para deshabilitar una tutoría
	<br><br>
	<form:form method="POST" modelAttribute="citas"  class="form-horizontal">
	<table class="table table-bordered" id ="tabla">
		<tr>
			<th></th>
			<th>9:00</th>
			<th>9:30</th>
			<th>10:00</th>
			<th>10:30</th>
			<th>11:00</th>
			<th>11:30</th>
			<th>12:00</th>
			<th>12:30</th>
			<th>13:00</th>
			<th>13:30</th>
		</tr>
		<tr>
			<td><b>Lunes ${map.Lunes}</b></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-09:00"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-09:30"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-10:00"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-10:30"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-11:00"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-11:30"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-12:00"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-12:30"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-13:00"/></td>
			<td><form:checkbox path="citas" value="${map.Lunes}-13:30"/></td>
		</tr>
		<tr>
			<td><b>Martes ${map.Martes}</b></td>
			<td><form:checkbox path="citas" value="${map.Martes}-09:00"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-09:30"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-10:00"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-10:30"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-11:00"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-11:30"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-12:00"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-12:30"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-13:00"/></td>
			<td><form:checkbox path="citas" value="${map.Martes}-13:30"/></td>
		</tr>
		<tr>
			<td><b>Miércoles ${map.Miércoles}</b></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-09:00"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-09:30"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-10:00"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-10:30"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-11:00"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-11:30"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-12:00"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-12:30"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-13:00"/></td>
			<td><form:checkbox path="citas" value="${map.Miércoles}-13:30"/></td>
		</tr>
		<tr>
			<td><b>Jueves ${map.Jueves}</b></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-09:00"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-09:30"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-10:00"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-10:30"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-11:00"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-11:30"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-12:00"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-12:30"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-13:00"/></td>
			<td><form:checkbox path="citas" value="${map.Jueves}-13:30"/></td>
		</tr>
		<tr>
			<td><b>Viernes ${map.Viernes}</b></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-09:00"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-09:30"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-10:00"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-10:30"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-11:00"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-11:30"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-12:00"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-12:30"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-13:00"/></td>
			<td><form:checkbox path="citas" value="${map.Viernes}-13:30"/></td>
		</tr>
	</table>
	<br>
		<div class="form-group">
    		<div class="col-sm-12">
      			<button type="submit" class="btn btn-success">Guardar</button>
      			<a href="<c:url value='/'/>" class="btn btn-danger">Cancelar</a>
    		</div>
  		</div>
	</form:form>
	
	<script type="text/javascript">
	/*#A9F5BC*/
		function cambia_color(){
			var tabla = document.getElementById("tabla");
			var td = tabla.getElementsByTagName("td");
			var c;
			
			var solicitadas = [];
			
			var sol;
			<c:forEach var="i" items="${solicitadas}">
			    sol = '${i}';
			    solicitadas.push(sol);                                  
			</c:forEach>
			
			for(var i=0; i<td.length; i++){
				c = td.item(i).childNodes;
				if(c.item(0).type == "checkbox"){
					if(c.item(0).checked){
						if(solicitadas.includes(c.item(0).value)){
							/* td[i].style.backgroundColor = "#F6CECE"; */
							td[i].style.backgroundColor = "#f28d8a";
						} else {
/* 							td[i].style.backgroundColor = "#A9F5BC"; */
							td[i].style.backgroundColor = "#b7ed84";
						}
					}
				}
			}
		}
	</script>
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