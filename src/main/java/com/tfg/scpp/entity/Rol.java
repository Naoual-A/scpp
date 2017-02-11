package com.tfg.scpp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private TipoRol rol;
	//representa todos los usuario con el mismo rol
	private Set<Usuario> usuarios;

	public Rol() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public TipoRol getRol() {
		return this.rol;
	}

	public void setRol(TipoRol rol) {
		this.rol = rol;
	}


	//bi-directional many-to-many association to Usuario
	@OneToMany(mappedBy="rol")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}

}