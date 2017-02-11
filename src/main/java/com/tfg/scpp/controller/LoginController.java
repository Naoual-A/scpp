package com.tfg.scpp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.scpp.entity.Alumno;
import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Cita;
import com.tfg.scpp.entity.Estado;
import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Mensaje;
import com.tfg.scpp.entity.Padre;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.TipoRol;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.AsignaturaService;
import com.tfg.scpp.service.CitaService;
import com.tfg.scpp.service.CursoService;
import com.tfg.scpp.service.GrupoService;
import com.tfg.scpp.service.MensajeService;
import com.tfg.scpp.service.TareaService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MensajeService mensajeServie;
	@Autowired
	private CitaService citaService;
	@Autowired
	private TareaService tareaService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private AsignaturaService asignaturaService;
	@Autowired
	private HttpSession session;

	@RequestMapping(value = { "/", "/home", "/principal" })
	public ModelAndView homePage(Principal principal) {
		logger.debug("greeting");

		ModelAndView model = new ModelAndView();
		if(principal!=null){
		    logger.debug("name: " + principal.getName());
		    Usuario user = usuarioService.getUserById(principal.getName());
		    logger.debug("user in BBDD: " + user.toString());
		    session.setAttribute("user", user);
		    if(user.getRol().getRol().equals(TipoRol.ADMIN)){
		    	model = inicializarPrinciaplAdmin(user);
		    } else if (user.getRol().getRol().equals(TipoRol.PROFESOR)){
		    	model = inicializarPrincipalProfesor(user);
		    } else if (user.getRol().getRol().equals(TipoRol.PADRE)){
		    	model = inicializarPrincipalPadre(user);
		    } else if (user.getRol().getRol().equals(TipoRol.ALUMNO)) {
		    	model = inicializarPrincipalAlumno(user);
		    }
		} else {
			model.addObject("sesion", "sesion ha caducado");
			model = new ModelAndView("redirect:/login");
		}
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		logger.debug("redirigiendo admin");
		model.addAttribute("user", getPrincipal());
		return "login/admin";
	}
	
	@RequestMapping(value = "/manual", method = RequestMethod.GET)
	public String manual(ModelMap model) {
		logger.debug("redirigiendo al manual");
		return "redirect:/pages/manual_scpp.htm";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "login/accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		logger.debug("redirigiendo al login");
		return "login/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		logger.debug("username): " + userName);
		return userName;
	}
	
	@RequestMapping(value="/NuevoUsuario", method=RequestMethod.GET)
	public String nuevoUsuario(Model model){
		model.addAttribute("alumnos", usuarioService.getAlumnos());
		model.addAttribute("user", new Padre());
		return "usuarios/nuevo_usuario_padre";
	}
	
	@RequestMapping(value="/NuevoUsuario", method=RequestMethod.POST)
	public ModelAndView nuevoUsuario(@ModelAttribute("padre")@Valid Padre padre, BindingResult result){
		logger.debug("padre recuperado: " + padre.toString());
		ModelAndView model = new ModelAndView();
		if(result.hasErrors()){
			logger.debug("tenemos errores: " + result.getAllErrors());
			model.setViewName("usuarios/nuevo_usuario_padre");
			model.addObject("user", new Padre());
			return model;
		}
		padre.setEstado(Estado.ACTIVO);
		usuarioService.alta(padre);
		model.setViewName("redirect:/login?login");
		return model;
	}
	
	private ModelAndView inicializarPrinciaplAdmin(Usuario user){
		ModelAndView model = new ModelAndView("login/principal_admin");
		model.addObject("cursos", cursoService.totaCursos());
		model.addObject("grupos", grupoService.totalGrupos());
		model.addObject("asigs", asignaturaService.totalAsignaturas());
		model.addObject("users", usuarioService.totalUsuarios());
		return model;
	}
	
	private ModelAndView inicializarPrincipalProfesor(Usuario user){
		ModelAndView model = new ModelAndView("login/principal_profesor");
		Pageable p = new PageRequest(0, 3);
    	List<Mensaje> mensajes = mensajeServie.getUltimosMensajes(user,p);
    	session.setAttribute("mensajes", mensajes);

    	//sacar numero de mensajes no leidos
    	List<Mensaje> mensajesNoLeidos = mensajeServie.getMensajesNoLeidos(user);
    	logger.debug("mensajes no leidos: " + mensajesNoLeidos.size());
    	model.addObject("mensajes", mensajesNoLeidos.size());
    	
    	//sacar numero de tutorias solicitadas
    	Date today = Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		Date viernes = TutoriasController.getViernes(dayOfWeek, LocalDate.now());
    	logger.debug("today is : " + today);
    	List<Cita> citas = citaService.getCitasDelasemana(today, viernes, user);
    	logger.debug("numero de citas : " + citas.size());
    	model.addObject("citas", citas.size());
    	
    	//sacar numero de tareas activas y lanzar el thread de tareas
		TareaThread tarea = new TareaThread(user, tareaService);
		tarea.start();
		int tareas = tareaService.getTareasByCreadorActivas(user).size();
		model.addObject("tareas", tareas);
		//sacar numero de asiganturas
		Profesor profesor = (Profesor) user;
		int asigs = profesor.getAsignaturasImpartidas().size();
		model.addObject("asignaturas", asigs);
		return model;
	}
	
	private ModelAndView inicializarPrincipalPadre(Usuario user){
		ModelAndView model = new ModelAndView("login/principal_padre");
		Pageable p = new PageRequest(0, 3);
    	List<Mensaje> mensajes = mensajeServie.getUltimosMensajes(user,p);
    	session.setAttribute("mensajes", mensajes);

    	//sacar numero de mensajes no leidos
    	List<Mensaje> mensajesNoLeidos = mensajeServie.getMensajesNoLeidos(user);
    	logger.debug("mensajes no leidos: " + mensajesNoLeidos.size());
    	model.addObject("mensajes", mensajesNoLeidos.size());
    	Date today = Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		Date viernes = TutoriasController.getViernes(dayOfWeek, LocalDate.now());
		Padre padre = (Padre) user;
		Alumno alumno = (Alumno) usuarioService.getUserById(padre.getIdAlumno());
		Profesor profesor = (Profesor) alumno.getGrupo().getTutor();
    	List<Cita> citas = citaService.getCitasDisponibles(today, viernes, profesor);
    	logger.debug("numero de citas : " + citas.size());
    	model.addObject("citas", citas.size());
    	
    	//sacar el numero de tareas activas
		Grupo grupo = alumno.getGrupo();
		int tareas = 0;
		for(Asignatura a: grupo.getAsignaturas()){
			tareas = tareas + tareaService.getTareasActivasAsignatura(a).size();
		}
		int asigs = grupo.getAsignaturas().size();
		model.addObject("tareas", tareas);
		model.addObject("asigs", asigs);
		return model;
	}
	
	private ModelAndView inicializarPrincipalAlumno(Usuario user){
		ModelAndView model = new ModelAndView("login/principal_alumno");
		Alumno alumno = (Alumno) user;
		Grupo grupo = alumno.getGrupo();
		int tareas = 0;
		for(Asignatura a: grupo.getAsignaturas()){
			tareas = tareas + tareaService.getTareasActivasAsignatura(a).size();
		}
		int asigs = grupo.getAsignaturas().size();
		model.addObject("tareas", tareas);
		model.addObject("asigs", asigs);
		return model;
	}
}
