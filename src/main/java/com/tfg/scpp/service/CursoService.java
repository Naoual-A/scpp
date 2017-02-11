package com.tfg.scpp.service;

import java.util.List;

import com.tfg.scpp.entity.Curso;

public interface CursoService {

	public List<Curso> getCursos();
	
	public void crearCurso(Curso curso);
	
	public void modificarCurso(Curso curso);
	
	public void eliminarCurso(Curso curso);
	
	public Curso getCursoById(long id);

	int totaCursos();
}
