package com.tfg.scpp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfg.scpp.entity.Administrador;
import com.tfg.scpp.entity.Alumno;
import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Padre;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.TipoRol;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.GrupoService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class UsuarioConroller {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioConroller.class);
	
	@Autowired 
	private UsuarioService userService;
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping(value="/usuarios/{id}")
	public String perfil(@PathVariable String id, Model model, HttpSession session){
//		Usuario user = (Usuario) session.getAttribute("user");
		Usuario user = userService.getUserById(id);
		Administrador admin = null;
		Profesor profesor = null;
		Padre padre = null;
		Alumno alumno = null;
		String path = "";
		logger.debug("rol: " + user.getRol().getRol());
		if (user.getRol().getRol().equals(TipoRol.ADMIN)){
			admin = (Administrador) user;
			model.addAttribute("user", admin);
			path = "usuarios/perfil_administrador";
		} else if (user.getRol().getRol().equals(TipoRol.PROFESOR)) {
			profesor = (Profesor) user;
			model.addAttribute("user", profesor);
			if(profesor.getGrupoTutorizado()!=null){
				Grupo grupo = grupoService.getGrupoById(profesor.getGrupoTutorizado());
				model.addAttribute("grupo",grupo);
			} else {
				model.addAttribute("grupo", new Grupo());
			}
			path = "usuarios/perfil_profesor";
		} else if (user.getRol().getRol().equals(TipoRol.PADRE)) {
			padre = (Padre) user;
			model.addAttribute("user", padre);
			path = "usuarios/perfil_padre";
		} else if (user.getRol().getRol().equals(TipoRol.ALUMNO)) {
			alumno = (Alumno) user;
			model.addAttribute("user", alumno);
			path = "usuarios/perfil_alumno";
		}		
		
		return path;
	}
	
	@RequestMapping(value="/usuarios/perfilvinculado/{id}")
	public String perfilVinculado(@PathVariable String id,  @ModelAttribute("Padre") Padre p, BindingResult result, Model model){
		Alumno alumno = (Alumno) userService.getUserById(id);
		model.addAttribute("user", alumno);
		return "usuarios/perfil_alumno";
	}
	
	@RequestMapping(value="/usuarios")
	public String usuarios(Model model){
		model.addAttribute("users", userService.findUsuarios());
		return "usuarios/usuarios";
	}
}
