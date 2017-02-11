package com.tfg.scpp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Tarea;
import com.tfg.scpp.entity.Usuario;

public interface TareaService {

	public List<Tarea> getTareas();
	
	public void nuevaTarea(Tarea tarea);
	
	public void editarTarea(Tarea tarea);
	
	public void borrarTarea(Tarea tarea);
	
	public Tarea findById(long id);
	
	public List<Tarea> getTareasByCreador(Usuario usuario);

	public List<Tarea> getTareasByCreadorPaginada(Usuario usuario, Pageable p);
	
	public List<Tarea> getTareasByCreadorActivas(Usuario usuario);
	
	public List<Tarea> getTareasActivasAsignatura(Asignatura a);
}
