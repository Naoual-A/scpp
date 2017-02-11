package com.tfg.scpp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Usuario;

public interface GrupoRepository extends CrudRepository<Grupo, Long> {
	
//	@Query("select g from grupo g where g.tutor = :t")
//	public Grupo getGruposByTutor(@Param("t") Usuario t);

}
