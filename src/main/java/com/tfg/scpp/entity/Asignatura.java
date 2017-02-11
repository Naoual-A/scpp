package com.tfg.scpp.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the asignatura database table.
 * 
 */
@Entity
@NamedQuery(name="Asignatura.findAll", query="SELECT a FROM Asignatura a")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String alias;
	private Usuario profesor;
	private List<Tarea> tareas;
	private Grupo grupo;
	private String fullAlias;

	public Asignatura() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(value=1, message = "Campo Obligatorio", groups=Asignatura.AsigUno.class)
	@Valid
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message="Campo Obligatorio", groups=Grupo.GrupoUno.class)
	@Valid
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="PROFESOR", nullable=true)
	public Usuario getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Usuario usuario) {
		this.profesor = usuario;
	}
	
	@ManyToOne
	@JoinColumn(name="GRUPO")
	@NotNull(message="Campo Obligatorio", groups=Grupo.GrupoUno.class)
	@Valid
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	//bi-directional many-to-one association to Asignatura
	@OneToMany(mappedBy="asignatura")
	@LazyCollection(LazyCollectionOption.FALSE) 
	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Transient
	public String getFullAlias() {
		fullAlias = alias + " (" + grupo.getAlias() + ")";
		return fullAlias;
	}


	public void setFullAlias(String fullAlias) {
		this.fullAlias = fullAlias;
	}


	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", alias=" + alias 
				+ ", profesor=" + profesor + ", grupo=" + grupo + "]";
	}
	
	public interface AsigUno{
		
	}

}