package com.tfg.scpp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.scpp.entity.Estado;
import com.tfg.scpp.entity.Usuario;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		logger.debug("id: " + id);
		Usuario user = usuarioService.getUserById(id);
		if (user == null) {
			logger.debug("user not found");
			throw new UsernameNotFoundException("User not found");
		}
		logger.debug("user: " + user);
		User u = new User(user.getId(), user.getPassword(), user.getEstado()
				.equals(Estado.ACTIVO), true, true, true,getGrantedAuthorities(user));
		return u;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().getRol()));
		logger.debug("authorities: " + authorities);
		return authorities;
	}
	
}
