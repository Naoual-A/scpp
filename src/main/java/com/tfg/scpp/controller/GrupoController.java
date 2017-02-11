package com.tfg.scpp.controller;

import java.util.ArrayList;
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

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Curso;
import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.CursoService;
import com.tfg.scpp.service.GrupoService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class GrupoController {

	private static final Logger logger = LoggerFactory.getLogger(GrupoController.class);
			
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/grupos/gruposadmin")
	public String gruposAdmin(Model model){
		logger.debug("redirigiendo a grupos");
		model.addAttribute("grupos", grupoService.getGrupos());
		return "grupos/grupos";
	}
	
	
	@RequestMapping("/grupos/misgrupos")
	public String misGrupos(Model model, HttpSession session){
		Usuario user = (Usuario) session.getAttribute("user");
		//sabemos que esta consulta la realizara un profesor
		Profesor profesor = (Profesor) user;
		List<Asignatura> asignaturas = profesor.getAsignaturasImpartidas();
		List<Grupo> grupos = new ArrayList<Grupo>();
		if(!asignaturas.isEmpty()){
			for(Asignatura a: asignaturas){
				grupos.add(a.getGrupo());
			}
		}
		model.addAttribute("grupos", grupos);
		return "grupos/grupos";
	}
	
	//los grupos de un tutor conectado
	@RequestMapping("/grupos/misgrupostutorizados")
	public String misGruposTuto(Model model, HttpSession session){
		logger.debug("redirigiendo a mis grupos");
		Usuario user = (Usuario) session.getAttribute("user");
		Profesor profesor = (Profesor) user;
//		List<Grupo> misGrupos = grupoService.getGruposByTutor(user);
		List<Grupo> misGrupos = new ArrayList<Grupo>();
		if(profesor.getGrupoTutorizado()!=null){
			Grupo g = grupoService.getGrupoById(profesor.getGrupoTutorizado());
			misGrupos.add(g);
		}
		model.addAttribute("grupos", misGrupos);
		return "grupos/grupos";
	}
	
	@RequestMapping(value = "/grupos/nuevo", method = RequestMethod.GET)
	public String nuevoGrupo(Model model){
		logger.debug("creando nuevo grupo");
		model.addAttribute("grupo", new Grupo());
		model.addAttribute("cursos", cursoService.getCursos());
		model.addAttribute("tutores", usuarioService.getTutoresDisponibles());
		return "grupos/nuevoGrupo";
	}
	
	@RequestMapping(value = "/grupos/nuevo", method = RequestMethod.POST)
	public ModelAndView nuevoGrupo(@ModelAttribute("grupo") @Validated(Curso.CursoUno.class) Grupo grupo	, BindingResult result){
		logger.debug("grupo: " + grupo);
		ModelAndView model = new ModelAndView();
		if(result.hasErrors()){
			logger.debug("tenemos errores: " + result.getAllErrors().toString());
//			model.addAttribute("cursos", cursoService.getCursos());
			model.addObject("cursos", cursoService.getCursos());
			model.addObject("tutores", usuarioService.getTutoresDisponibles());
			model.setViewName("grupos/nuevoGrupo");
			return model;
		}
		if(grupo.getTutor().getId().equalsIgnoreCase("null")){
			logger.debug("tutor nulo");
			grupo.setTutor(null);
			grupoService.crearGrupo(grupo);
		} else {
			Usuario user = usuarioService.getUserById(grupo.getTutor().getId());
			logger.debug(user.toString());
			grupoService.crearGrupo(grupo);
			usuarioService.setProfesorGrupoTutorizado(grupo.getId(), user.getId());
		}
		model.setViewName("redirect:/grupos/gruposadmin");
		return model;
	}
	
	@RequestMapping(value = "grupos/editar/{id}", method = RequestMethod.GET)
	public String editarGrupo(@PathVariable long id, Model model){
		
		Grupo grupo = grupoService.getGrupoById(id);
		logger.debug(grupo.toString());
		List<Usuario> tutoresDisponibles = usuarioService.getTutoresDisponibles();
		if(grupo.getTutor() == null){
			logger.debug("tutor nulo");
			Profesor prof = new Profesor();
			prof.setId("null");
			grupo.setTutor(prof);
		} else {
			tutoresDisponibles.add(grupo.getTutor());
		}
		
		model.addAttribute("grupo" ,grupo);
		model.addAttribute("tutores", tutoresDisponibles);
		model.addAttribute("cursos", cursoService.getCursos());
		return "grupos/nuevoGrupo";
	}
	
	@RequestMapping(value = "grupos/editar/{id}", method = RequestMethod.POST)
	public String guardarCambiosGrupo(@PathVariable long id, @ModelAttribute("grupo") Grupo g,
			BindingResult result){
		logger.debug("cambiar detalle del grupo");
		logger.debug(g.toString());
		Grupo gEnBD = grupoService.getGrupoById(id);
		if(g.getTutor().getId().equalsIgnoreCase("null")){
			if(gEnBD.getTutor()!= null){
				logger.debug("quitar tutor");
				g.setTutor(null);
				Profesor p = (Profesor) gEnBD.getTutor();
				p.setGrupoTutorizado(null);
				usuarioService.guardarUsuario(p);
				grupoService.crearGrupo(g);
			} else {
				logger.debug("sigue sin tutor");
				grupoService.crearGrupo(g);
			}
		}else{
			if(gEnBD.getTutor()!= null){
				logger.debug("cambio de grupo al tutor");
				Profesor pEnBD = (Profesor) usuarioService.getUserById(gEnBD.getTutor().getId());
				pEnBD.setGrupoTutorizado(null);
				usuarioService.setGrupoTutorizadoNull(pEnBD.getId());
				Profesor p = (Profesor) usuarioService.getUserById(g.getTutor().getId());
				p.setGrupoTutorizado(g.getId());
				usuarioService.setProfesorGrupoTutorizado(g.getId(), p.getId());
				grupoService.crearGrupo(g);
			} else {
				logger.debug("antes tutor nulo");
				Usuario user = usuarioService.getUserById(g.getTutor().getId());
				logger.debug(user.toString());
				grupoService.crearGrupo(g);
				usuarioService.setProfesorGrupoTutorizado(g.getId(), user.getId());	
			}
		}
		return "redirect:/grupos/gruposadmin";
	}
	
	@RequestMapping("grupos/borrar/{id}")
	public String eliminarGrupo(@PathVariable long id, Model model){
		Grupo grupo = grupoService.getGrupoById(id);
		logger.debug("borrando el grupo: " + grupo.toString());
		if(grupo.getTutor() != null){
			logger.debug("if tutor no es nulo");
			Profesor p = (Profesor) grupo.getTutor();
			p.setGrupoTutorizado(null);
			usuarioService.guardarUsuario(p);
			grupo.setTutor(null);
			grupoService.crearGrupo(grupo);
		}
		try{
			grupoService.eliminarGrupo(grupo);			
		} catch (Exception e){
			model.addAttribute("error", "no se puede borrar el grupo");
			logger.debug("no se puede borrar");
			return "redirect:/grupos/gruposadmin";
		}

		return "redirect:/grupos/gruposadmin";
	}
	
	@RequestMapping(value = "grupos/ver/{id}")
	public String detalleGrupo(@PathVariable long id, Model model){
		Grupo grupo = grupoService.getGrupoById(id);
		logger.debug(grupo.toString());
		model.addAttribute("grupo" ,grupo);
		return "grupos/verGrupo";
	}
	
	@RequestMapping(value = "grupos/alumnos/{id}")
	public String verAlumnosGrupo(@PathVariable long id, Model model){
		Grupo grupo = grupoService.getGrupoById(id);
		logger.debug(grupo.toString());
		model.addAttribute("grupo", grupo);
		model.addAttribute("users" ,grupo.getAlumnos());
		return "usuarios/listado_alumnos";
	}
}
