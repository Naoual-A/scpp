<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
				<h3 class="page-header">Tutorías concertadas</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!-- <div class="panel panel-default"> -->

					<div class="panel-body">
	
	<table class="table table-bordered" id="tabla">
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
			<td align="center"><b>Lunes ${map.Lunes}</b></td>
			<td id="lnueve">${mapId.get(map3.Lunes.concat(0).concat(90000)).solicitante.getFullName()}</td> 
			<td>${mapId.get(map3.Lunes.concat(0).concat(93000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(100000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(103000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(110000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(113000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(120000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(123000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(130000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Lunes.concat(133000)).solicitante.getFullName()}</td>
		</tr>
		<tr>
			<td align="center"><b>Martes ${map.Martes}</b></td>
			<td>${mapId.get(map3.Martes.concat(0).concat(90000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(0).concat(93000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(100000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(103000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(110000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(113000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(120000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(123000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(130000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Martes.concat(133000)).solicitante.getFullName()}</td>
		</tr>
		<tr>
			<td align="center"><b>Miércoles ${map.Miércoles}</b></td>
			<td>${mapId.get(map3.Miércoles.concat(0).concat(90000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(0).concat(93000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(100000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(103000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(110000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(113000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(120000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(123000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(130000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Miércoles.concat(133000)).solicitante.getFullName()}</td>
		</tr>
		<tr>
			<td align="center"><b>Jueves ${map.Jueves}</b></td>
			<td>${mapId.get(map3.Jueves.concat(0).concat(90000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(0).concat(93000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(100000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(103000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(110000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(113000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(120000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(123000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(130000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Jueves.concat(133000)).solicitante.getFullName()}</td>
		</tr>
		<tr>
			<td align="center"><b>Viernes ${map.Viernes}</b></td>
			<td>${mapId.get(map3.Viernes.concat(0).concat(90000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(0).concat(93000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(100000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(103000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(110000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(113000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(120000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(123000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(130000)).solicitante.getFullName()}</td>
			<td>${mapId.get(map3.Viernes.concat(133000)).solicitante.getFullName()}</td>
		</tr>
	</table>
	<br>


		<script type="text/javascript">
		function cambia_color(){
			var tabla = document.getElementById("tabla");
			var t = tabla.getElementsByTagName("td");
			for(var i=0; i<t.length; i++){
				
				if(i == 0 || i == 11 || i == 22 || i == 33 || i == 44){
					continue; 	
				} else if (t[i].innerHTML.length != 0){
					t[i].style.backgroundColor = "#F7BE81";
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