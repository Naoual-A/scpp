<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.tfg.scpp.entity.Usuario" %>
<div id="wrapper"> 

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/scpp/home.html"> SCPP
<!-- 				<img border="0" alt="SCPP" src="/scpp/static/imagenes/logo.gif" height="60" width="42"/> -->
			</a>
		</div>
		<!-- /.navbar-header -->
		
		<ul class="nav navbar-top-links navbar-right">
		<sec:authorize access="hasAnyRole('PROFESOR', 'PADRE')">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<c:if test="${not empty sessionScope.mensajes }">
				
				<ul class="dropdown-menu dropdown-messages">
				<c:forEach items="${sessionScope.mensajes}" var="mensaje">
					<li><a href="/scpp/mensajes/recibidos">
							<div>
								<strong>${mensaje.fuente.getFullName()}</strong> <span class="pull-right text-muted">
									<em>${fn:substring(mensaje.fecha, 0, 10)}</em>
								</span>
							</div>
							<c:if test="${fn:length(mensaje.cuerpo)> 40 }">
								<div> ${fn:substring(mensaje.cuerpo, 0, 40)} ...</div>
							</c:if>
							<c:if test="${fn:length(mensaje.cuerpo)< 40 }">
								<div> ${mensaje.cuerpo} ...</div>
							</c:if>							
					</a></li>
					
					</c:forEach>
					<li class="divider"></li>
					<li><a class="text-center" href="/scpp/mensajes/recibidos"> <strong>Todos los mensajes</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-messages --></li>
				
				</c:if>
				<c:if test="${empty sessionScope.mensajes }">
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="/scpp/mensajes/recibidos">
							<div><i>No tiene mensajes recibidos</i></div>
					</a></li>
					</ul>
				</c:if>
				</sec:authorize>
				
			<!-- /.dropdown -->
			<!-- <li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> /.dropdown-alerts</li> -->
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="/scpp/usuarios/${sessionScope.user.id}"><i class="fa fa-user"></i> 
					&nbsp; ${sessionScope.user.id } </a></li>
					<li class="divider"></li>
					<li><a href="/scpp/logout"><i class="fa fa-power-off"></i>
							&nbsp; Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->


	</nav>
	
</div>