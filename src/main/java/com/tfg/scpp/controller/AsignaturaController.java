package com.tfg.scpp.controller;

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
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.AsignaturaService;
import com.tfg.scpp.service.CursoService;
import com.tfg.scpp.service.GrupoService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class AsignaturaController {

	private static final Logger logger = LoggerFactory.getLogger(AsignaturaController.class);
	
	@Autowired
	private AsignaturaService asigService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping(value="/asignaturas/admin/asignaturas")
	public String asignaturasAdmin(Model model){
		logger.debug("redirigiendo a asignaturas");
		model.addAttribute("asignaturas", asigService.getAsignaturas());
		return "asignaturas/asignaturas";
	}
	
	//las asignaturas de un profesor conectado
	@RequestMapping(value="/asignaturas/prof/asignaturas")
	public String asignaturasProf(Model model, HttpSession session){
		logger.debug("redirigiendo a asignaturas profesor");
		Usuario user = (Usuario) session.getAttribute("user");
		Profesor profesor = (Profesor) user;
		List<Asignatura> asigs = profesor.getAsignaturasImpartidas();
		model.addAttribute("asignaturas", asigs);
		return "asignaturas/asignaturas";
	}
	
	//las asignaturas de un alumno conectado
	@RequestMapping(value="asignaturas/alu/asignaturas")
	public String asignaturasAlumno(Model model, HttpSession session){
		logger.debug("redirigiendo a asignaturas alumno");
		Usuario user = (Usuario) session.getAttribute("user");
		Alumno alumno = (Alumno) user;
		List<Asignatura> asigs = alumno.getGrupo().getAsignaturas();
		logger.debug("asignaturas: " + asigs);
		model.addAttribute("asignaturas", asigs);
		return "asignaturas/asignaturas";
	}
	
	//las asignaturas de un padre conectado
	@RequestMapping(value="asignaturas/padre/asignaturas")
	public String asignaturasPadre(Model model, HttpSession session){
		logger.debug("redirigiendo a asignaturas padre");
		Usuario user = (Usuario) session.getAttribute("user");
		Padre padre = (Padre) user;
		Alumno alumno = (Alumno) usuarioService.getUserById(padre.getIdAlumno());
		List<Asignatura> asigs = alumno.getGrupo().getAsignaturas();
		model.addAttribute("asignaturas", asigs);
		return "asignaturas/asignaturas";
	}
	
	@RequestMapping(value = "/asignaturas/nuevo", method = RequestMethod.GET)
	public String nuevaAsignatura(Model model){
		logger.debug("creando nueva asignatura");
		model.addAttribute("asignatura", new Asignatura());
		model.addAttribute("grupos", grupoService.getGrupos());
		model.addAttribute("profesores", usuarioService.getProfesores());
		return "asignaturas/nuevaAsignatura";
	}
	
	@RequestMapping(value = "/asignaturas/nuevo", method = RequestMethod.POST)
	public ModelAndView nuevaAsignatura(@ModelAttribute("asignatura") @Validated(Grupo.GrupoUno.class) Asignatura asignatura,
			BindingResult result){
		logger.debug("asignatura: " + asignatura);
		ModelAndView model = new ModelAndView();
		if(result.hasErrors()){
			logger.debug("tenemos errores: " + result.getAllErrors());
			model.setViewName("asignaturas/nuevaAsignatura");
			model.addObject("grupos", grupoService.getGrupos());
			model.addObject("profesores", usuarioService.getProfesores());
			return model;
		}
		if(asignatura.getProfesor().getId().equalsIgnoreCase("null")){
			logger.debug("profesor nulo");
			asignatura.setProfesor(null);
		}
		asigService.crearAsignatura(asignatura);
		model.setViewName("redirect:/asignaturas/admin/asignaturas");
		return model;
	}
	
	@RequestMapping(value = "asignaturas/editar/{id}", method = RequestMethod.GET)
	public String editarAsignatura(@PathVariable long id, Model model){
		Asignatura asig = asigService.getAsignaturaById(id);
		logger.debug(asig.toString());
		List<Usuario> profesores = usuarioService.getProfesores();
		if(asig.getProfesor() == null){
			logger.debug("profesor nulo");
			Usuario user = new Usuario();
			user.setId("null");
			asig.setProfesor(user);
		} else {
			profesores.add(asig.getProfesor());
		}
		
		model.addAttribute("asignatura" ,asig);
		model.addAttribute("profesores", profesores);
		model.addAttribute("grupos", grupoService.getGrupos());
		return "asignaturas/nuevaAsignatura";
	}
	
	@RequestMapping(value = "asignaturas/editar/{id}", method = RequestMethod.POST)
	public String guardarCambiosAsignatura(@PathVariable long id, @ModelAttribute("asignatura") Asignatura asig,
			BindingResult result){
		logger.debug("cambiar detalle de una asignatura");
		logger.debug(asig.toString());
		asigService.crearAsignatura(asig);
		return "redirect:/asignaturas/admin/asignaturas";
	}
	
	@RequestMapping("asignaturas/borrar/{id}")
	public String eliminarAsignatura(@PathVariable long id, Model model){
		Asignatura asig = asigService.getAsignaturaById(id);
		logger.debug("borrando la asignatura: " + asig.toString());
		asigService.eliminarAsignatura(asig);
		return "redirect:/asignaturas/admin/asignaturas";
	}
	
	@RequestMapping(value = "asignaturas/ver/{id}")
	public String detalleAsignatura(@PathVariable long id, Model model){
		Asignatura asig = asigService.getAsignaturaById(id);
		logger.debug(asig.toString());
//		List<Alumno> alumnos = asigService;
//		logger.debug(asig.g);
		model.addAttribute("asignatura" ,asig);
		model.addAttribute("alumnos", asig.getGrupo().getAlumnos());
		return "asignaturas/verAsignatura";
	}
	
	@RequestMapping(value = "asignaturas/alumnos/{id}")
	public String verAlumnosGrupo(@PathVariable long id, Model model){
		Asignatura a = asigService.getAsignaturaById(id);
		model.addAttribute("asig", a);
		model.addAttribute("users" ,a.getGrupo().getAlumnos());
		return "usuarios/listado_alumnos";
	}
}
