package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.scpp.entity.Curso;
import com.tfg.scpp.repository.CursoRepository;

@Service
public class CursoSeviceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepo;
	
	@Override
	public List<Curso> getCursos() {
		List<Curso> cursos = new ArrayList<Curso>();
		Iterator<Curso> iter = cursoRepo.findAll().iterator();
		while(iter.hasNext()){
			cursos.add(iter.next());
		}
		return cursos;
	}

	@Override
	public void crearCurso(Curso curso) {
		cursoRepo.save(curso);
	}

	@Override
	public void modificarCurso(Curso curso) {
		
	}

	@Override
	public void eliminarCurso(Curso curso) {
		cursoRepo.delete(curso);
	}

	@Override
	public Curso getCursoById(long id) {
		return cursoRepo.findOne(id);
	}
	
	@Override
	public int totaCursos(){
		return (int) cursoRepo.count();
	}

}
