package com.tfg.scpp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Mensaje;
import com.tfg.scpp.entity.Usuario;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {

	@Query("select m from Mensaje m where m.destino = :to")
	List<Mensaje> findByOwner(@Param("to") Usuario to); 
	
	@Query("select m from Mensaje m where m.fuente = :from")
	List<Mensaje> findBySender(@Param("from") Usuario from);
	
	@Query("select m from Mensaje m where m.destino = :user and leido = false")
	List<Mensaje> getMensajeNoLeidos(@Param("user") Usuario user);
	
	@Query("select m from Mensaje m where m.destino = :user order by m.fecha desc")
	List<Mensaje> getUltimosMensajes(@Param("user") Usuario user, Pageable p);
}
