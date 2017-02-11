package com.tfg.scpp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tfg.scpp.entity.Grupo;
import com.tfg.scpp.entity.Rol;
import com.tfg.scpp.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	@Query("select u from Usuario u where u.rol = :rol")
	List<Usuario> getAlumnos(@Param("rol")Rol rol);

	@Query("select u from Usuario u where u.rol = :rol")
	List<Usuario> getPadres(@Param("rol")Rol rol);
	
	@Query("select u from Usuario u where u.rol = :rol")
	List<Usuario> getProfesores(@Param("rol")Rol rol);
	
	@Query("select u from Usuario u where u.rol = 2 and not exists (select g.tutor from Grupo g where u.id = g.tutor.id)")
	List<Usuario> getTutoresDisponibles();
	
	@Modifying
	@Query("update Profesor u set u.grupoTutorizado = :grupo where u.id = :id")
	@Transactional
	void updateProfesor(@Param("grupo") Long grupo, @Param("id")String id);
	
	@Modifying
	@Query("update Profesor u set u.grupoTutorizado = NULL where u.id = :id")
	@Transactional
	void setGrupoTutorizadoNull(@Param("id")String id);
}
