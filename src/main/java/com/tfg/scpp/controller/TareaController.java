package com.tfg.scpp.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.scpp.entity.Alumno;
import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Padre;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.Tarea;
import com.tfg.scpp.entity.TareaExt;
import com.tfg.scpp.entity.TipoRol;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.TareaRepository;
import com.tfg.scpp.service.AsignaturaService;
import com.tfg.scpp.service.TareaService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class TareaController {

	private static final Logger logger = LoggerFactory.getLogger(TareaController.class);
	
	@Autowired
	private TareaService tareaService;
	@Autowired
	private AsignaturaService asigService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TareaRepository tareaRepo;
	
	@RequestMapping("tareas/mistareas")
	public String misTareas(Model model, HttpSession session, Principal p){
		logger.info("recogiendo mis tareas");

//		Usuario user = (Usuario) session.getAttribute("user");
		Usuario user = usuarioService.getUserById(p.getName());
//		///////////////////////////////
//		String userName = "";
//		Object principal = SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//			userName = ((UserDetails) principal).getUsername();
//			logger.debug("en el if: " + userName);
//		} else {
//			userName = principal.toString();
//			logger.debug("en el else: " + userName);
//		}
//		
//		///////////////////////////////
		List<Tarea> tareas = getTareasRol(user);
		logger.debug("Los tareas recuperadas son: " + tareas);
//		int total = (int) tareaRepo.count();
//		total = total / 10;
		model.addAttribute("tareas",tareas);
//		model.addAttribute("total", total);
		return "tareas/tareas";
	}
	
	@RequestMapping(value = "tareas/nuevo", method = RequestMethod.GET)
	public String nuevaTarea(Model model, HttpSession session){
		Tarea t = new Tarea();
		String i = "";
		String f = "";
		TareaExt tarea = new TareaExt(t, i, f);
		model.addAttribute("tarea", tarea);
		Usuario creador = (Usuario) session.getAttribute("user");
		if(creador.getRol().getRol().equals(TipoRol.PROFESOR)){
			Profesor p = (Profesor) creador;
			List<Asignatura> asignaturas = p.getAsignaturasImpartidas();
			model.addAttribute("asignaturas", asignaturas);
		} else {
			model.addAttribute("asignaturas", asigService.getAsignaturas());
		}
		logger.info("Redirigiendo a la pantalla de nueva tarea");
		return "tareas/nuevaTarea";
	}
	
	@RequestMapping(value = "tareas/nuevo", method = RequestMethod.POST)
	public ModelAndView nuevaTarea(
			@ModelAttribute("tarea") @Validated({Asignatura.AsigUno.class}) TareaExt tarea,
			BindingResult result, HttpSession session) throws ParseException{
		logger.info("Tarea recogida: " + tarea.toString());
		ModelAndView model = new ModelAndView();
		Usuario creador = (Usuario) session.getAttribute("user");

		if(result.hasErrors()){			
			logger.debug("tenemos errores: " + result.getAllErrors());
			model.setViewName("tareas/nuevaTarea");
			if(creador.getRol().getRol().equals(TipoRol.PROFESOR)){
				Profesor p = (Profesor) creador;
				List<Asignatura> asignaturas = p.getAsignaturasImpartidas();
				model.addObject("asignaturas", asignaturas);
			} else {
				model.addObject("asignaturas", asigService.getAsignaturas());
			}
			return model;
		}
		
		Tarea t = tarea.getTarea();
		t.setCreador(creador);
		t.setActiva(false);
		
		String inicio = tarea.getInicio();
		String fin = tarea.getFin();
		inicio = inicio.replace('T', ' ');
		inicio = inicio + ":00";
		logger.debug("inicio: " + inicio);
		logger.debug("time: " + Timestamp.valueOf(inicio));
		t.setFechaInicio(Timestamp.valueOf(inicio));
		fin = fin.replace('T', ' ');
		fin = fin + ":00";
		t.setFechaFin(Timestamp.valueOf(fin));
		
		logger.debug("tarea: " + t);
		logger.debug("now: " + LocalDateTime.now());
		//para poner si es activa miramos la fecha de hoy
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		logger.debug("time: " + time);
		if (t.getFechaInicio().before(time) && t.getFechaFin().after(time)) {
			t.setActiva(true);
		}
		tareaService.nuevaTarea(t);
		model.setViewName("redirect:/tareas/mistareas");
		return model;
	}
	
	@RequestMapping(value = "tareas/editar/{id}", method = RequestMethod.GET)
	public String modificarTarea(@PathVariable long id, Model model){
		
		Tarea tarea= tareaService.findById(id);
		logger.debug(tarea.toString());
		String inicio = tarea.getFechaInicio().toString();
		String fin = tarea.getFechaFin().toString();
		TareaExt t = new TareaExt(tarea, inicio.replace(' ', 'T'), fin.replace(' ', 'T'));
		logger.debug(t.toString());
		Usuario creador = tarea.getCreador();
		if(creador.getRol().getRol().equals(TipoRol.PROFESOR)){
			Profesor p = (Profesor) creador;
			List<Asignatura> asignaturas = p.getAsignaturasImpartidas();
			model.addAttribute("asignaturas", asignaturas);
		} else {
			model.addAttribute("asignaturas", asigService.getAsignaturas());
		}
		model.addAttribute("tarea" ,t);
		
		return "tareas/nuevaTarea";
	}
	
	@RequestMapping(value = "tareas/editar/{id}", method = RequestMethod.POST)
	public String guardarCambiosTarea(@PathVariable long id, @ModelAttribute("tarea") TareaExt t, BindingResult result){
		logger.debug("cambiar el contenido de la tarea");
		logger.debug(t.toString());
		Tarea tarea = t.getTarea();
		String inicio = t.getInicio().replace('T', ' ');
		String fin = t.getFin().replace('T', ' ');
		tarea.setFechaInicio(Timestamp.valueOf(inicio));
		tarea.setFechaFin(Timestamp.valueOf(fin));
		logger.debug(tarea.getCreador().getId());
		tareaService.nuevaTarea(tarea);
		return "redirect:/tareas/mistareas";
	}
	
	@RequestMapping("tareas/borrar/{id}")
	public String borrarTarea(@PathVariable long id, Model model){
		Tarea tarea= tareaService.findById(id);
		logger.debug("borrando la tarea: " + tarea.toString());
		tareaService.borrarTarea(tarea);
		return "redirect:/tareas/mistareas";
	}
	
	@RequestMapping(value = "tareas/ver/{id}")
	public String leerMensaje(@PathVariable long id, Model model){
		
		Tarea tarea= tareaService.findById(id);
		logger.debug(tarea.toString());
		String inicio = tarea.getFechaInicio().toString();
		String fin = tarea.getFechaFin().toString();
		TareaExt t = new TareaExt(tarea, inicio.replace(' ', 'T'), fin.replace(' ', 'T'));
		logger.debug(t.toString());
		model.addAttribute("tarea" ,t);

		return "tareas/verTarea";
	}
	
	private List<Tarea> getTareasRol(Usuario user){
		
		List<Tarea> tareas = new ArrayList<Tarea>();
		
		if (user.getRol().getRol().equals(TipoRol.ADMIN)) {
			tareas.addAll(tareaService.getTareas());
		} else if (user.getRol().getRol().equals(TipoRol.PROFESOR)){
			tareas.addAll(tareaService.getTareasByCreador(user));
		} else if (user.getRol().getRol().equals(TipoRol.PADRE)) {
			Padre padre = (Padre) user;
			Alumno alumno = (Alumno) usuarioService.getUserById(padre.getIdAlumno());
			Grupo grupo = alumno.getGrupo();
			for(Asignatura a: grupo.getAsignaturas()){
				tareas.addAll(a.getTareas());
			}
		} else if (user.getRol().getRol().equals(TipoRol.ALUMNO)) {
			Alumno alumno = (Alumno) user;
			Grupo grupo = alumno.getGrupo();
			for(Asignatura a: grupo.getAsignaturas()){
				tareas.addAll(a.getTareas());
			}
		}
		return tareas;
	}
	
//	@RequestMapping(value="tareas/{pageid}")  
//    public String edit(@PathVariable int pageid, HttpSession session, Model model){  
//		List<Tarea> tareas = new ArrayList<Tarea>();
//		Usuario user = (Usuario) session.getAttribute("user");
//		logger.debug("getting page: " + pageid );
//        int total = (int) tareaRepo.count();
//        total = total / 10;
// 
//        logger.debug("page: " + pageid);
//        Pageable p = new PageRequest(pageid, 10);
//		tareas.addAll(tareaService.getTareasByCreador(user,p));
//        logger.debug("tareas: " + tareas);
//		model.addAttribute("tareas", tareas);
//		model.addAttribute("total", total);
//		return "tareas/tareas";
//    }  
}
