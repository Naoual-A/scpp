package com.tfg.scpp.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the administrador database table.
 * 
 */
@Entity
@DiscriminatorValue("1")
@NamedQuery(name="Administrador.findAll", query="SELECT a FROM Administrador a")
public class Administrador extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String idAdmin;
//	private Usuario usuario;

	public Administrador() {
	}

//	@Column(name="ID_ADMIN")
//	public String getIdAdmin() {
//		return this.idAdmin;
//	}
//
//	public void setIdAdmin(String idAdmin) {
//		this.idAdmin = idAdmin;
//	}


	//bi-directional one-to-one association to Usuario
//	@OneToOne
//	@JoinColumn(name="ID_ADMIN")
//	public Usuario getUsuario() {
//		return this.usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}