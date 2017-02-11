package com.tfg.scpp.service;

import java.util.List;

import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Usuario;

public interface GrupoService {
	
	public List<Grupo> getGrupos();
	
	public void crearGrupo(Grupo grupo);
	
	public Grupo getGrupoById(Long id);
	
	public void eliminarGrupo(Grupo grupo);
	
	public List<Grupo> getGruposByTutor(Usuario user);
	
	public Grupo getGrupoTutor(Usuario user);

	int totalGrupos();
}
