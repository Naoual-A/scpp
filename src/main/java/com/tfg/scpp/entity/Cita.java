package com.tfg.scpp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@NamedQuery(name="Cita.findAll", query="SELECT c FROM Cita c")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Date fecha;
	private Time hora;
	private boolean solicitada;
	private Usuario solicitante;
	private Usuario profesor;

	public Cita() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}


	public boolean getSolicitada() {
		return this.solicitada;
	}

	public void setSolicitada(boolean solicitada) {
		this.solicitada = solicitada;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="SOLICITANTE")
	public Usuario getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Usuario usuario1) {
		this.solicitante = usuario1;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="PROFESOR")
	public Usuario getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Usuario usuario2) {
		this.profesor = usuario2;
	}
	
	public boolean equals(Object o){
		boolean res = false;
		
		if(o instanceof Cita){
			Cita c = (Cita) o;
			res = id==c.id && fecha.equals(c.fecha) && hora.equals(c.hora) &&
					profesor.equals(c.profesor);
		}
		return res;
	}
	
	public int hashCode(){
		return fecha.hashCode() + (int)id;
	}

	@Override
	public String toString() {
		return "Cita [fecha=" + fecha + ", hora=" + hora + ", solicitada="
				+ solicitada  + "]";
	}

}