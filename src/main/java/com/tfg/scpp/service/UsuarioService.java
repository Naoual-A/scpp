package com.tfg.scpp.service;

import java.util.List;

import com.tfg.scpp.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findUsuarios();
	
	public Usuario getUserById(String id);
	
	public List<Usuario> getAlumnos();
	
	public List<Usuario> getProfesores();
	
	public List<Usuario> getPadres();
	
	public List<Usuario> getTutoresDisponibles();
	
	public Usuario guardarUsuario(Usuario user);
	
	public void setProfesorGrupoTutorizado(Long grupo, String id);
	
	public void alta(Usuario u);
	
	public void setGrupoTutorizadoNull(String id);
	
	public void setInSession(Usuario u);

	int totalUsuarios();
	
}
