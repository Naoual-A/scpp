package com.tfg.scpp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the usuario database table.
 * 
 */ 
@Entity
@DiscriminatorColumn(name="ROL")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String apellidos;
	private String email;
	private Estado estado;
	private String nombre;
	private String password;
	private String fullName;
	private List<Cita> citasSolicitante;
	private List<Mensaje> mensajeEnviados;
	private List<Mensaje> mensajesRecibidos;
	private Rol rol;

	public Usuario() {
		
	}
	
	public Usuario(Usuario usuario){
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.password = usuario.getPassword();
		this.fullName = usuario.getFullName();
		this.email = usuario.getEmail();
		this.estado = usuario.getEstado();
		this.rol = usuario.rol;
	}


	@Id
	@NotEmpty(message="Campo obligatorio", groups=MensajeUno.class)
    @Valid
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotEmpty(message="Campo obligatorio")
    @Valid
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@NotEmpty(message="Campo obligatorio")
    @Valid
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Enumerated(EnumType.STRING)
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@NotEmpty(message="Campo obligatorio")
    @Valid
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@NotEmpty(message="Campo obligatorio")
    @Valid
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="solicitante")
	public List<Cita> getCitasSolicitante() {
		return this.citasSolicitante;
	}

	public void setCitasSolicitante(List<Cita> citasSolicitante) {
		this.citasSolicitante = citasSolicitante;
	}


	public Cita addCitasSolicitante(Cita citas1) {
		getCitasSolicitante().add(citas1);
		citas1.setSolicitante(this);

		return citas1;
	}

	public Cita removeCitasSolicitante(Cita citas1) {
		getCitasSolicitante().remove(citas1);
		citas1.setSolicitante(null);

		return citas1;
	}

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="fuente")
	public List<Mensaje> getMensajesEnviados() {
		return this.mensajeEnviados;
	}

	public void setMensajesEnviados(List<Mensaje> mensajes1) {
		this.mensajeEnviados = mensajes1;
	}

	public Mensaje addMensajesEnviados(Mensaje mensajes1) {
		getMensajesEnviados().add(mensajes1);
		mensajes1.setFuente(this);

		return mensajes1;
	}

	public Mensaje removeMensajesEnviados(Mensaje mensajes1) {
		getMensajesEnviados().remove(mensajes1);
		mensajes1.setFuente(null);

		return mensajes1;
	}

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="destino")
	@LazyCollection(LazyCollectionOption.FALSE) 
	public List<Mensaje> getMensajesRecibidos() {
		return this.mensajesRecibidos;
	}

	public void setMensajesRecibidos(List<Mensaje> mensajes2) {
		this.mensajesRecibidos = mensajes2;
	}

	public Mensaje addMensajesRecibidos(Mensaje mensajes2) {
		getMensajesRecibidos().add(mensajes2);
		mensajes2.setDestino(this);

		return mensajes2;
	}

	public Mensaje removeMensajesRecibidos(Mensaje mensajes2) {
		getMensajesRecibidos().remove(mensajes2);
		mensajes2.setDestino(null);

		return mensajes2;
	}
	
	@Transient
	public String getFullName(){
		this.fullName = this.nombre + " " + this.apellidos;
		return this.fullName;
	}
	
	public void setFullName(String str){
		fullName = str;
	}
	
	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "ROL", insertable = false, updatable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol r) {
		this.rol = r;
	}
	
	public boolean equals(Object o){
		boolean res = false;
		
		if(o instanceof Usuario){
			Usuario u = (Usuario) o;
			res =  id.equalsIgnoreCase(u.id);
		}
		return res;
	}
	
	public int hashCode(){
		return id.hashCode();
	}

	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", apellidos=" + apellidos + ", estado="
				+ estado + ", nombre=" + nombre
				+ "]";
	}
	
	public interface MensajeUno{
		
	}
}