package com.tfg.scpp.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@DiscriminatorValue("4")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String idAlumno;
	private Grupo grupo;
//	private Usuario usuario;

	public Alumno() {
	}


//	@Id
//	@Column(name="ID_ALUMNO")
//	public String getIdAlumno() {
//		return this.idAlumno;
//	}
//
//	public void setIdAlumno(String idAlumno) {
//		this.idAlumno = idAlumno;
//	}

//	@Transient
	@ManyToOne
	@JoinColumn(name="GRUPO")
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	//bi-directional one-to-one association to Usuario
//	@OneToOne
//	@JoinColumn(name="ID_ALUMNO")
//	public Usuario getUsuario() {
//		return this.usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}