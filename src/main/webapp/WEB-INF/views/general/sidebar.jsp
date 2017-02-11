<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
</head>
<body>
<div id="wrapper">
		<!-- <div class="navbar-default sidebar" role="navigation"> -->
		<div id="sidebar-wrapper" >
			<!-- <div class="sidebar-nav navbar-collapse"> -->
			<!-- MENU ADMIN -->
 			<sec:authorize access="hasRole('ADMIN')">
			<ul class="nav" id="side-menu">
					<li><a href="/scpp/principal"><i class="fa fa-home"></i>
							&nbsp; Inicio</a></li>
				 	<li><a href="/scpp/usuarios/${sessionScope.user.id}"><i class="fa fa-user"></i>
							&nbsp; Perfil de usuario</a></li>
				 	<li><a href="#"><i class="fa fa-exchange"></i>
							&nbsp; Mensajes<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/mensajes/nuevo">Nuevo</a></li>
							<li><a href="/scpp/mensajes/recibidos">Mensajes recibidos</a></li>
							<li><a href="/scpp/mensajes/enviados">Mensajes enviados</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-calendar-check-o"></i>
							&nbsp; Tutorías<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tutorias">Habilitar/Deshabilitar</a></li>
							<li><a href="/scpp/tutorias/misTutorias">Mis tutorías</a></li>
							<li><a href="/scpp/tutorias/solicitar">Solicitar</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-book"></i>
							&nbsp; Cursos<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/cursos/miscursos">Cursos</a></li>
							<li><a href="/scpp/cursos/nuevo">Nuevo Curso</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-users"></i>
							&nbsp; Grupos<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/grupos/gruposadmin">Grupos</a></li>
							<li><a href="/scpp/grupos/nuevo">Nuevo Grupo</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-book"></i>
							&nbsp; Asignaturas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/asignaturas/admin/asignaturas">Asignaturas</a></li>
							<li><a href="/scpp/asignaturas/nuevo">Nueva Asignatura</a></li>
						</ul> <!--/.nav-second-level --></li>
				 	<li><a href="#"><i class="fa fa-pencil-square-o"></i>
							&nbsp; Tareas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tareas/mistareas">Tareas</a></li>
							<li><a href="/scpp/tareas/nuevo">Nueva Tarea</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="/scpp/usuarios/"><i class="fa fa-list"></i>
							&nbsp; Usuarios</a></li>
					<li><a href="/scpp/manual" target="_blank"><i class="fa fa-life-ring"></i>
							&nbsp; Ayuda</a></li>
				</ul>
 				</sec:authorize>
			<!-- FIN MENU ADMIN -->
			<!-- MENU PROFESOR -->
			<sec:authorize access="hasRole('PROFESOR')">
			<ul class="nav" id="side-menu">
					<li><a href="/scpp/principal"><i class="fa fa-home"></i>
							&nbsp; Inicio</a></li>
				 	<li><a href="/scpp/usuarios/${sessionScope.user.id}"><i class="fa fa-user"></i>
							&nbsp; Perfil de usuario</a></li>
				 	<li><a href="#"><i class="fa fa-exchange"></i>
							&nbsp; Mensajes<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/mensajes/nuevo">Nuevo</a></li>
							<li><a href="/scpp/mensajes/recibidos">Mensajes recibidos</a></li>
							<li><a href="/scpp/mensajes/enviados">Mensajes enviados</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-calendar-check-o"></i>
							&nbsp; Tutorías<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tutorias">Habilitar/Deshabilitar</a></li>
							<li><a href="/scpp/tutorias/misTutorias">Mis tutorías</a></li>
						</ul> <!-- /.nav-second-level --></li>
				 	<li><a href="#"><i class="fa fa-users"></i>
							&nbsp; Grupos<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<!-- <li><a href="/scpp/grupos/misgrupos">Mis grupos</a></li> -->
							<li><a href="/scpp/grupos/misgrupostutorizados">Mis grupos tutorizados</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-book"></i>
							&nbsp; Asignaturas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/asignaturas/prof/asignaturas">Mis Asignaturas</a></li>
						</ul> <!--/.nav-second-level --></li>				 	
				 	<li><a href="#"><i class="fa fa-pencil-square-o"></i>
							&nbsp; Tareas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tareas/mistareas">Mis tareas</a></li>
							<li><a href="/scpp/tareas/nuevo">Nueva tarea</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="/scpp/manual" target="_blank"><i class="fa fa-life-ring"></i>
							&nbsp; Ayuda</a></li>
				</ul>
 			</sec:authorize>
			<!-- FIN MENU PROFESOR -->
			<!-- MENU PADRE -->
			<sec:authorize access="hasRole('PADRE')">
			<ul class="nav" id="side-menu">
					<li><a href="/scpp/principal"><i class="fa fa-home"></i>
							&nbsp; Inicio</a></li>
					<li><a href="#"><i class="fa fa-user"></i>
							&nbsp; Información de usuario<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/usuarios/${sessionScope.user.id}">Perfil de usuario</a></li>
<!-- 							<li><a href="/scpp/usuarios/perfilvinculado">Perfil vinculado</a></li> -->
						</ul> <!-- /.nav-second-level --></li>
				 	<li><a href="#"><i class="fa fa-exchange"></i>
							&nbsp; Mensajes<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/mensajes/nuevo">Nuevo</a></li>
							<li><a href="/scpp/mensajes/recibidos">Mensajes recibidos</a></li>
							<li><a href="/scpp/mensajes/enviados">Mensajes enviados</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-calendar-check-o"></i>
							&nbsp; Tutorías<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tutorias/solicitar">Solicitar</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-book"></i>
							&nbsp; Asignaturas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/asignaturas/padre/asignaturas">Mis Asignaturas</a></li>
						</ul> <!--/.nav-second-level --></li>						
				 	<li><a href="#"><i class="fa fa-pencil-square-o"></i>
							&nbsp; Tareas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tareas/mistareas">Mis tareas</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="/scpp/manual" target="_blank"><i class="fa fa-life-ring"></i>
							&nbsp; Ayuda</a></li>
				</ul>
 			</sec:authorize>
			<!-- FIN MENU PADRE -->
			<!-- MENU ALUMNO -->
			<sec:authorize access="hasRole('ALUMNO')">
			<ul class="nav" id="side-menu">
					<li><a href="/scpp/principal"><i class="fa fa-home"></i>
							&nbsp; Inicio</a></li>
					<li><a href="/scpp/usuarios/${sessionScope.user.id}"><i class="fa fa-user"></i>
							&nbsp; Perfil de usuario</a></li>
					<li><a href="#"><i class="fa fa-book"></i>
							&nbsp; Asignaturas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/asignaturas/alu/asignaturas">Mis Asignaturas</a></li>
						</ul> <!--/.nav-second-level --></li>				 	
				 	<li><a href="#"><i class="fa fa-pencil-square-o"></i>
							&nbsp; Tareas<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/scpp/tareas/mistareas">Mis tareas</a></li>
						</ul> <!--/.nav-second-level --></li>
					<li><a href="/scpp/manual" target="_blank"><i class="fa fa-life-ring"></i>
							&nbsp; Ayuda</a></li>
			</ul>
 			</sec:authorize>
			<!-- FIN MENU ALUMNO -->
			<!-- </div> -->
			<!-- /.sidebar-collapse -->
		        
		</div>
		<!-- /.navbar-static-side -->
		<div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                      
                        
                    </div>
                </div>
            </div>
        </div>
		</div>
		</body>
</html>