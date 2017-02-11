package com.tfg.scpp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Asignatura;
import com.tfg.scpp.entity.Usuario;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {

	@Query("select a from Asignatura a where a.profesor = :prof")
	public List<Asignatura> getAsignaruasByProfesor(@Param("prof") Usuario profesor);
}
