package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.AsignaturaRepository;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

	@Autowired
	private AsignaturaRepository asigRepo;

	@Override
	public List<Asignatura> getAsignaturas() {
		List<Asignatura> asigs = new ArrayList<Asignatura>();
		Iterator<Asignatura> iter = asigRepo.findAll().iterator();
		while(iter.hasNext()){
			asigs.add(iter.next());
		}
		return asigs;
	}

	@Override
	public void crearAsignatura(Asignatura asignatura) {
		asigRepo.save(asignatura);
	}

	@Override
	public Asignatura getAsignaturaById(long id) {
		return asigRepo.findOne(id);
	}

	@Override
	public void eliminarAsignatura(Asignatura asignatura) {
		asigRepo.delete(asignatura);
	}

	@Override
	public List<Asignatura> getAsignaturasByProfesor(Usuario profesor) {
		return asigRepo.getAsignaruasByProfesor(profesor);
	}
	
	@Override
	public int totalAsignaturas(){
		return (int) asigRepo.count();
	}
}
