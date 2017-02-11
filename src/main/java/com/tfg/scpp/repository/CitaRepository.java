package com.tfg.scpp.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Cita;
import com.tfg.scpp.entity.Usuario;

public interface CitaRepository extends CrudRepository<Cita, Long> {

	@Query("select c from Cita c where c.fecha >= :d1 and c.fecha <= :d2 and c.profesor = :u")
	List<Cita> getCitasBetween(@Param("d1")Date d1, @Param("d2")Date d2, @Param("u")Usuario u);
	
	@Query("select c from Cita c where c.fecha >= :d1 and c.fecha <= :d2 and c.solicitada = false"
			+ " and c.profesor = :u")
	List<Cita> getCitasDisponibles(@Param("d1")Date d1, @Param("d2")Date d2, @Param("u") Usuario u );
	
	@Query("select c from Cita c where c.profesor = :user and c.solicitada = true")
	List<Cita> getCitasByUser(@Param("user")Usuario user);
	
	@Query("select c from Cita c where c.fecha = :f and c.hora = :h and c.profesor = :p")
	Cita findByFechaHoraProf(@Param("f")Date f, @Param("h")Time h, @Param("p")Usuario p);
	
	@Query("select c from Cita c where c.fecha >= :f1 and c.fecha <= :f2 and c.profesor = :user"
			+ " and c.solicitada = true")
	List<Cita> getCitasSolicitadasDelasemana(@Param("f1")Date f1, @Param("f2")Date f2, @Param("user")Usuario user);
}
