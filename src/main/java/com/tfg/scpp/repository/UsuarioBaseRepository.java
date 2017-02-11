//package com.tfg.scpp.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//import org.springframework.data.repository.query.Param;
//
//import com.tfg.scpp.entity.Rol;
//import com.tfg.scpp.entity.Usuario;
//
//@NoRepositoryBean
//public interface UsuarioBaseRepository<T extends Usuario> 
//										 extends CrudRepository<T, String> {
//	
//	@Query("select u from Usuario u where u.rol = :rol")
//	List<Usuario> getAlumnos(@Param("rol")Rol rol);
//
//	@Query("select u from Usuario u where u.rol = :rol")
//	List<Usuario> getPadres(@Param("rol")Rol rol);
//	
//	@Query("select u from Usuario u where u.rol = :rol")
//	List<Usuario> getProfesores(@Param("rol")Rol rol);
//	
//	@Query("select u from Usuario u where u.rol = 2 and not exists (select g.tutor from Grupo g where u.id = g.tutor.id)")
//	List<Usuario> getTutoresDisponibles();
//
//}
