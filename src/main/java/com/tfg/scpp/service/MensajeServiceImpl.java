package com.tfg.scpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.scpp.entity.Mensaje;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.MensajeRepository;

@Service
public class MensajeServiceImpl implements MensajeService {
	
	@Autowired
	private MensajeRepository mensajeRepo;
	
	@Override
	public void enviarMensaje(Mensaje mensaje) {
		this.mensajeRepo.save(mensaje);
	}

	@Override
	public List<Mensaje> consultarMensajesRecibidos(Usuario owner) {
		return mensajeRepo.findByOwner(owner);
	}

	@Override
	public Mensaje findById(long id) {
		return mensajeRepo.findOne(id);
	}

	@Override
	public List<Mensaje> consutlarMensajesEnviados(Usuario from) {
		return mensajeRepo.findBySender(from);
	}

	@Override
	public List<Mensaje> getMensajesNoLeidos(Usuario user) {
		return mensajeRepo.getMensajeNoLeidos(user);
	}

	@Override
	public List<Mensaje> getUltimosMensajes(Usuario user, Pageable p) {
		return mensajeRepo.getUltimosMensajes(user,p);
	}
	
}
