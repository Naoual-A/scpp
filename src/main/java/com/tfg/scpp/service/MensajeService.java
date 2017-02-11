package com.tfg.scpp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tfg.scpp.entity.Mensaje;
import com.tfg.scpp.entity.Usuario;

public interface MensajeService {

	public void enviarMensaje(Mensaje mensaje);
	
	public List<Mensaje> consultarMensajesRecibidos(Usuario to);
	
	public Mensaje findById(long id);
	
	public List<Mensaje> consutlarMensajesEnviados(Usuario from);
	
	public List<Mensaje> getMensajesNoLeidos(Usuario user);

	public List<Mensaje> getUltimosMensajes(Usuario user, Pageable p);
	
}
