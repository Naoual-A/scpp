package com.tfg.scpp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Tarea;
import com.tfg.scpp.entity.Usuario;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
	
	@Query("select t from Tarea t where t.creador = :creador")
	List<Tarea> getTareasByCreadorPaginada(@Param("creador") Usuario u, Pageable p);
	
	@Query("select t from Tarea t where t.creador = :creador")
	List<Tarea> getTareasByCreador(@Param("creador") Usuario u);
	
	@Query("select t from Tarea t where t.creador = :creador and t.activa = true")
	List<Tarea> getTareasByCreadorActivas(@Param("creador") Usuario u);
	
	@Query("select t from Tarea t where t.asignatura = :asig and t.activa = true")
	List<Tarea> getTareasActivasAsignatura(@Param("asig") Asignatura a);	
}
