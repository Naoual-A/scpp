package com.tfg.scpp.controller;

import java.text.ParseException;
import java.util.List;

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

import com.tfg.scpp.entity.Curso;
import com.tfg.scpp.service.CursoService;

@Controller
public class CursoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

	@Autowired
	private CursoService cursoService;
	
	@RequestMapping("cursos/miscursos")
	public String misCursos(Model model){
		logger.info("recogiendo mis cursos");
		//Opción unicamente disponible para administrador
		List<Curso> cursos = cursoService.getCursos();
		logger.debug("cursos recuperados: " + cursos.size());
		logger.debug("cursos recuperados: " + cursos);
		model.addAttribute("cursos",cursos);
		return "cursos/cursos";
	}
	
	@RequestMapping(value = "cursos/nuevo", method = RequestMethod.GET)
	public String nuevaCurso(Model model){
		model.addAttribute("curso", new Curso());
		logger.info("Redirigiendo a la pantalla de nuevo curso");
		return "cursos/nuevoCurso";
	}
	
	@RequestMapping(value = "cursos/nuevo", method = RequestMethod.POST)
	public String nuevaCurso(@ModelAttribute("curso") @Validated({Curso.CursoUno.class, Curso.CursoDos.class}) Curso curso,
			BindingResult result) throws ParseException{
		logger.info("Curso: " + curso.toString());
		if(result.hasErrors()){
			return "cursos/nuevoCurso";
		}
		cursoService.crearCurso(curso);
		return "redirect:/cursos/miscursos";
	}
	
	@RequestMapping(value = "cursos/editar/{id}", method = RequestMethod.GET)
	public String modificarCurso(@PathVariable long id, Model model){
		
		Curso curso= cursoService.getCursoById(id);
		logger.debug(curso.toString());
		model.addAttribute("curso" ,curso);
		
		return "cursos/nuevoCurso";
	}
	
	@RequestMapping(value = "cursos/editar/{id}", method = RequestMethod.POST)
	public String guardarCambiosCurso(@PathVariable long id, @ModelAttribute("curso") Curso c, BindingResult result){
		logger.debug("cambiar detalle del curso");
		logger.debug(c.toString());
		cursoService.crearCurso(c);
		return "redirect:/cursos/miscursos";
	}
	
	@RequestMapping("cursos/borrar/{id}")
	public String eliminarCurso(@PathVariable long id, Model model){
		Curso curso = cursoService.getCursoById(id);
		logger.debug("borrando el curso: " + curso.toString());
		cursoService.eliminarCurso(curso);
		return "redirect:/cursos/miscursos";
	}
	
	@RequestMapping(value = "cursos/ver/{id}")
	public String detalleCurso(@PathVariable long id, Model model){
		Curso curso = cursoService.getCursoById(id);
		logger.debug(curso.toString());
		model.addAttribute("curso" ,curso);

		return "cursos/verCurso";
	}
}
