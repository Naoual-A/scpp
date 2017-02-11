package com.tfg.scpp.service;

import java.util.List;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Usuario;

public interface AsignaturaService {
	
	public List<Asignatura> getAsignaturas();
	
	public void crearAsignatura(Asignatura asignatura);
	
	public Asignatura getAsignaturaById(long id);
	
	public void eliminarAsignatura(Asignatura asignatura);
	
	public List<Asignatura> getAsignaturasByProfesor(Usuario profesor);

	int totalAsignaturas();
}
