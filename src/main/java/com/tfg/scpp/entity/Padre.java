package com.tfg.scpp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@DiscriminatorValue("3")
@NamedQuery(name="Padre.findAll", query="SELECT p FROM Padre p")
public class Padre extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idAlumno;
//	private Grupo grupo;
//	private Usuario usuario;

	public Padre() {
	}

	@Column(name="ID_ALUMNO")
	@NotEmpty(message="Campo Obligatorio")
	@Valid
	public String getIdAlumno(){
		return this.idAlumno;
	}
	
	public void setIdAlumno(String id){
		this.idAlumno = id;
	}

	@Override
	public String toString() {
		return "Padre [idAlumno=" + idAlumno + "]";
	}
}