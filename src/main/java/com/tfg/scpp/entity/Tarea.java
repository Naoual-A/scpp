package com.tfg.scpp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private boolean activa;
	private String contenido;
	private Timestamp fechaFin;
	private Timestamp fechaInicio;
	private String titulo;
	private Usuario creador;
	private Asignatura asignatura;

	public Tarea() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public boolean getActiva() {
		return this.activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@NotEmpty(message="Campo Obligatorio", groups=Asignatura.AsigUno.class)
    @Valid
	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@NotNull(message="El campo fecha fin no puede ser vacío")
    @Valid
	@Column(name="FECHA_FIN")
	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	@NotNull(message="El campo fecha inicio no puede ser vacío")
    @Valid
	@Column(name="FECHA_INICIO")
	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@NotEmpty(message="Campo Obligatorio", groups=Asignatura.AsigUno.class)
    @Valid
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="CREADOR")
	public Usuario getCreador() {
		return this.creador;
	}

	public void setCreador(Usuario usuario) {
		this.creador = usuario;
	}
	
	//bi-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name="ASIGNATURA")
//	@Min(value=1, message = "Campo Obligatorio", groups=Asignatura.AsigUno.class)
	@NotNull(message="Campo Obligatorio")
	@Valid
	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}


	@Override
	public String toString() {
		return "Tarea [id=" + id + ", activa=" + activa + ", contenido="
				+ contenido  
				+ ", titulo=" + titulo + ", creador=" + creador
				+ "]";
	}

}