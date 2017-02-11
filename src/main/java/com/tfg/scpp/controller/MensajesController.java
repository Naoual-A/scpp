package com.tfg.scpp.controller;

import java.sql.Timestamp;
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
import com.tfg.scpp.entity.Mensaje;
import com.tfg.scpp.entity.Padre;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.TipoRol;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.MensajeService;
import com.tfg.scpp.service.UsuarioService;

/**
 * 
 * @author Naoual
 *  Manejea el módulo de comunicación
 */
@Controller
public class MensajesController {

	private static final Logger logger = LoggerFactory
			.getLogger(MensajesController.class);

	@Autowired
	private MensajeService mensajeService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/mensajes/nuevo", method = RequestMethod.GET)
	public String nuevoMensaje(Model model, HttpSession session) {
		Usuario user = (Usuario) session.getAttribute("user");
		List<Usuario> destinatarios = obtenerDestinatariosRol(user);
		model.addAttribute("dest", destinatarios);
		Mensaje mensaje = new Mensaje();
		mensaje.setFuente(user);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("error", "");
		logger.info("Redirigiendo a la pantalla de nuevo mensaje");
		return "comunicacion/NuevoMensaje";
	}

	@RequestMapping(value = "mensajes/nuevo", method = RequestMethod.POST)
	public ModelAndView EnviarMensaje(
			@ModelAttribute("mensaje") @Validated(Usuario.MensajeUno.class) Mensaje m, BindingResult result,
			HttpSession session) {
		Usuario user = (Usuario) session.getAttribute("user");
		logger.debug("user: " + user);
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			logger.debug("tenemos errores: " + result.getAllErrors());
			List<Usuario> destinatarios = obtenerDestinatariosRol(user);
			logger.debug("dest: " + destinatarios);
			Mensaje mensaje = new Mensaje();
			mensaje.setFuente(user);
			if(m.getDestino()!= null){
				m.setDestino(usuarioService.getUserById(m.getDestino().getId()));
			}
			model.addObject("fuente", user);
			model.addObject("error", "si");
			model.addObject("dest", destinatarios);
			model.setViewName("comunicacion/NuevoMensaje");
			return model;
		}
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		m.setFecha(time);
		if (m.getFuente() != null) {
			logger.debug("fuente: " + m.getFuente().toString());
		}

		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		mensajes.add(m);

		logger.debug("destino: " + m.getDestino());
		// recoger el destino
		Usuario destino = usuarioService.getUserById(m.getDestino().getId());
		Usuario fuente = usuarioService.getUserById(user.getId());
		m.setDestino(destino);
		m.setFuente(fuente);

		logger.info(m.toString());
		this.mensajeService.enviarMensaje(m);
		logger.info("Mensaje enviado");

		model.setViewName("redirect:/mensajes/recibidos");
		return model;
	}

	@RequestMapping(value = "/mensajes/responder", method = RequestMethod.GET)
	public String nuevoMensaje(@ModelAttribute("mensaje") Mensaje m, Model model) {
		logger.debug("mensaje no nulo: " + m.toString());
		model.addAttribute(m);
		return "comunicacion/NuevoMensaje";
	}

	@RequestMapping(value = "mensajes/recibidos")
	public String consultarMensajes(Model model, HttpSession session) {
		logger.info("recogiendo mis mensajes");
		// el id hay que recogerlo por la sesion
		Usuario user = (Usuario) session.getAttribute("user");
		if (user != null) {
			logger.debug("user: " + user.toString());
		}

		List<Mensaje> mensajes = mensajeService.consultarMensajesRecibidos(user);
		logger.debug("Los mensajes recuperados son: " + mensajes);
		model.addAttribute("mensaje", new Mensaje());
		model.addAttribute("mensajes", mensajes);
		return "comunicacion/mensajes";
	}

	@RequestMapping(value = "mensajes/enviados")
	public String consultarMensajesEnviados(Model model, HttpSession session) {
		logger.info("recogiendo mis mensajes");
		// el id hay que recogerlo por la sesion
		Usuario user = (Usuario) session.getAttribute("user");
		if (user != null) {
			logger.debug("user: " + user.toString());
		}

		List<Mensaje> mensajes = mensajeService.consutlarMensajesEnviados(user);
		logger.debug("Los mensajes recuperados son: " + mensajes);
		model.addAttribute("mensaje", new Mensaje());
		model.addAttribute("mensajes", mensajes);
		return "comunicacion/mensajes_enviados";
	}

	@RequestMapping(value = "mensajes/{id}")
	public String leerMensaje(@PathVariable long id, Model model) {
		Mensaje mensaje = mensajeService.findById(id);
		if (mensaje != null && !mensaje.getLeido()) {
			mensaje.setLeido(true);
			// para el update
			mensajeService.enviarMensaje(mensaje);
		}
		logger.debug(mensaje.toString());
		model.addAttribute(mensaje);
		return "comunicacion/leerMensaje";
	}

	@RequestMapping(value = "mensajes/responder/{id}", method = RequestMethod.GET)
	public String modificarTarea(@PathVariable long id, Model model) {
		Mensaje mensaje = mensajeService.findById(id);
		Usuario fuente = mensaje.getFuente();
		Usuario destino = mensaje.getDestino();
		mensaje.setFuente(destino);
		mensaje.setDestino(fuente);
		mensaje.setAsunto("RE: " + mensaje.getAsunto());
		mensaje.setCuerpo("");
		model.addAttribute("mensaje", mensaje);
		return "comunicacion/NuevoMensaje";
	}

	@RequestMapping(value = "mensajes/responder/{id}", method = RequestMethod.POST)
	public String guardarCambiosTarea(@PathVariable long id,
			@ModelAttribute("mensaje") Mensaje m, BindingResult result)
			throws CloneNotSupportedException {
		logger.debug("Guardar la respuesta del mensaje");
		logger.debug(m.toString());
		Mensaje mensajeNuevo = new Mensaje();
		mensajeNuevo.setId(0);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		mensajeNuevo.setFecha(time);
		mensajeNuevo.setFuente(usuarioService
				.getUserById(m.getFuente().getId()));
		mensajeNuevo.setDestino(usuarioService.getUserById(m.getDestino()
				.getId()));
		mensajeNuevo.setAsunto(m.getAsunto());
		mensajeNuevo.setCuerpo(m.getCuerpo());
		mensajeService.enviarMensaje(mensajeNuevo);
		return "redirect:/mensajes/recibidos";
	}

	private List<Usuario> obtenerDestinatariosRol(Usuario user) {
		List<Usuario> destinatarios = new ArrayList<Usuario>();

		if (user.getRol().getRol().equals(TipoRol.PADRE)) {
			// si el usuario conectado es un padre
			Padre padre = (Padre) user;
			Alumno alumno = (Alumno) usuarioService.getUserById(padre
					.getIdAlumno());
			destinatarios.add(alumno.getGrupo().getTutor());
		} else if (user.getRol().getRol().equals(TipoRol.PROFESOR)) {
			// si el usuario conectado es un profesor
			Profesor profesor = (Profesor) user;
			// get los padres de mi grupo
			destinatarios.addAll(usuarioService.getPadres());
		} else {
			destinatarios.addAll(usuarioService.findUsuarios());
		}
		logger.debug("destinatarios: " + destinatarios);
		return destinatarios;
	}

}
