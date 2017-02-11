package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.scpp.entity.Rol;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.RolRepository;
import com.tfg.scpp.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	private static final int PROFESOR = 2;
	private static final int PADRE = 3;
	private static final int ALUMNO = 4;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private RolRepository rolRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public List<Usuario> findUsuarios() {
		Iterator<Usuario> iter = usuarioRepo.findAll().iterator();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		while(iter.hasNext()){
			usuarios.add(iter.next());
		}
		return usuarios;
	}

	@Override
	public Usuario getUserById(String id) {
		Usuario user = usuarioRepo.findOne(id);
		return user;
	}

	@Override
	public List<Usuario> getAlumnos() {
		Rol rolAlumno = rolRepo.findOne(ALUMNO);
		return usuarioRepo.getAlumnos(rolAlumno);
	}

	@Override
	public List<Usuario> getProfesores() {
		Rol rolProfesor = rolRepo.findOne(PROFESOR);
		return usuarioRepo.getProfesores(rolProfesor);
	}

	@Override
	public List<Usuario> getPadres() {
		Rol rolPadre = rolRepo.findOne(PADRE);
		return usuarioRepo.getPadres(rolPadre);
	}
	
	@Override
	public List<Usuario> getTutoresDisponibles() {
		return usuarioRepo.getTutoresDisponibles();
	}

	@Override
	public Usuario guardarUsuario(Usuario user) {
		logger.debug("guardando usuario");
		return usuarioRepo.save(user);
	}

	@Override
	public void setProfesorGrupoTutorizado(Long grupo, String id) {
		logger.debug("vamos a cambiar el grupo a null: " + grupo + " : " + id);
		usuarioRepo.updateProfesor(grupo, id);
	}
	
	@Override
	public void setGrupoTutorizadoNull(String id) {
		logger.debug("vamos a cambiar el grupo a null: " + id);
		usuarioRepo.setGrupoTutorizadoNull(id);
	}
	
	@Override
	public void setInSession(Usuario u) {
		httpSession.setAttribute("user", u);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public void alta(Usuario u){
		String encodedPass = passwordEncoder.encode(u.getPassword());
		u.setPassword(encodedPass);
		usuarioRepo.save(u);
	}
	
	@Override
	public int totalUsuarios(){
		return (int) usuarioRepo.count();
	}

}
