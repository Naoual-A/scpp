package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Tarea;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {
	@Autowired
	private TareaRepository tareaRepo;

	@Override
	public List<Tarea> getTareas() {
		List<Tarea> tareas = new ArrayList<Tarea>();
		Iterator<Tarea> iter = tareaRepo.findAll().iterator();
		while(iter.hasNext()){
			tareas.add(iter.next());
		}
		return tareas;
	}

	@Override
	public void nuevaTarea(Tarea tarea) {
		tareaRepo.save(tarea);
	}

	@Override
	public void editarTarea(Tarea tarea) {
		tareaRepo.save(tarea);
	}

	@Override
	public void borrarTarea(Tarea tarea) {
		tareaRepo.delete(tarea);
	}

	@Override
	public Tarea findById(long id) {
		return tareaRepo.findOne(id);
	}

	@Override
	public List<Tarea> getTareasByCreadorPaginada(Usuario usuario, Pageable p) {
		return tareaRepo.getTareasByCreadorPaginada(usuario, p);
	}

	@Override
	public List<Tarea> getTareasByCreador(Usuario usuario) {
		return tareaRepo.getTareasByCreador(usuario);
	}
	
	@Override
	public List<Tarea> getTareasByCreadorActivas(Usuario usuario) {
		return tareaRepo.getTareasByCreadorActivas(usuario);
	}

	@Override
	public List<Tarea> getTareasActivasAsignatura(Asignatura a) {
		return tareaRepo.getTareasActivasAsignatura(a);
	}
}
