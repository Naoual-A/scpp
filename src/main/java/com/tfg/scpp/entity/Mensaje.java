package com.tfg.scpp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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

import com.tfg.scpp.entity.Usuario.MensajeUno;


/**
 * The persistent class for the mensaje database table.
 * 
 */
@Entity
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String asunto;
	private String cuerpo;
	private Timestamp fecha;
	private boolean leido;
	private Usuario fuente;
	private Usuario destino;

	public Mensaje() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message="Campo obligatorio", groups=MensajeUno.class)
    @Valid
	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	@NotEmpty(message="Campo obligatorio", groups=MensajeUno.class)
    @Valid
	public String getCuerpo() {
		return this.cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}


	public boolean getLeido() {
		return this.leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="FROM_USER")
	public Usuario getFuente() {
		return this.fuente;
	}

	public void setFuente(Usuario usuario1) {
		this.fuente = usuario1;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="TO_USER")
	@NotNull(message="Campo obligatorio", groups=MensajeUno.class)
    @Valid
	public Usuario getDestino() {
		return this.destino;
	}

	public void setDestino(Usuario usuario2) {
		this.destino = usuario2;
	}


	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", asunto=" + asunto + ", cuerpo="
				+ cuerpo + ", fecha=" + fecha + ", leido=" + leido
				+ ", fuente=" + fuente.getFullName() + ", destino=" + destino.getFullName() + "]";
	}

}