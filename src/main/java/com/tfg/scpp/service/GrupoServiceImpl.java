package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.GrupoRepository;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private GrupoRepository grupoRepo;
	
	@Override
	public List<Grupo> getGrupos() {
		List<Grupo> grupos = new ArrayList<Grupo>();
		Iterator<Grupo> iter = grupoRepo.findAll().iterator();
		while(iter.hasNext()){
			grupos.add(iter.next());
		}
		return grupos;
	}

	@Override
	public void crearGrupo(Grupo grupo) {
		grupoRepo.save(grupo);
	}

	@Override
	public Grupo getGrupoById(Long id) {
		return grupoRepo.findOne(id);
	}

	@Override
	public void eliminarGrupo(Grupo grupo) {
		grupoRepo.delete(grupo);
	}

	@Override
	public List<Grupo> getGruposByTutor(Usuario user) {
//		return grupoRepo.getGruposByTutor(user);
		return null;
	}

	@Override
	public Grupo getGrupoTutor(Usuario user) {
//		return grupoRepo.getGruposByTutor(user);
		return null;
	}

	@Override
	public int totalGrupos(){
		return (int) grupoRepo.count();
	}
}
