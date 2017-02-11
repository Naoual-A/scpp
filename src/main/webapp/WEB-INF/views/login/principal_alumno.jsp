<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script src="<c:url value="/static/js/sockjs-0.3.4.js" />"></script>
<script src="<c:url value="/static/js/stomp.js" />"></script>
<script src="<c:url value="/static/js/app.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">Bienvenido a su página principal</h3>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">

					<!--                 </div>
                <div class="row"> -->
					<div class="col-lg-4 col-md-8">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-pencil-square-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${tareas }</div>
										<div>Tareas activas!</div>
									</div>
								</div>
							</div>
							<a href="/scpp/tareas/mistareas">
								<div class="panel-footer">
									<span class="pull-left">Ir a mis tareas</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-4 col-md-8">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${asigs }</div>
										<div>Asignaturas matriculadas!</div>
									</div>
								</div>
							</div>
							<a href="/scpp/asignaturas/alu/asignaturas">
								<div class="panel-footer">
									<span class="pull-left">Ir a mis asignaturas</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
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