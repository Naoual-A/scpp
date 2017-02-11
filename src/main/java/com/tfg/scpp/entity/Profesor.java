package com.tfg.scpp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * The persistent class for the profesor database table.
 * 
 */
@Entity
@DiscriminatorValue("2")
@NamedQuery(name="Profesor.findAll", query="SELECT p FROM Profesor p")
public class Profesor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long grupoTutorizado;
	private List<Asignatura> asignaturasImpartidas;
	private List<Tarea> tareas;
	private List<Cita> citasProfesor;

	public Profesor() {
		super();
	}
	
	public Profesor(Usuario usuario){
		super(usuario);
	}


//	@Id
//	@Column(name="ID_PROFESOR")
//	public String getIdProfesor() {
//		return this.idProfesor;
//	}
//
//	public void setIdProfesor(String idProfesor) {
//		this.idProfesor = idProfesor;
//	}

//	@OneToOne(mappedBy="tutor")
//	@JoinColumn(name="GRUPO_TUTORIZADO", nullable=true)
	@Column(name="GRUPO_TUTORIZADO", nullable=true)
	public Long getGrupoTutorizado(){
		return this.grupoTutorizado;
	}
	
	public void setGrupoTutorizado(Long g){
		this.grupoTutorizado = g;
	}

	//bi-directional many-to-one association to Asignatura
		@OneToMany(mappedBy="profesor")
		@LazyCollection(LazyCollectionOption.FALSE) 
		public List<Asignatura> getAsignaturasImpartidas() {
			return this.asignaturasImpartidas;
		}

		public void setAsignaturasImpartidas(List<Asignatura> asigs) {
			this.asignaturasImpartidas = asigs;
		}

		public Asignatura addAsignaturasImpartidas(Asignatura a) {
			getAsignaturasImpartidas().add(a);
			a.setProfesor(this);

			return a;
		}

		public Asignatura removeAsignaturasImpartidas(Asignatura a) {
			getAsignaturasImpartidas().remove(a);
			a.setProfesor(null);

			return a;
		}
		//bi-directional many-to-one association to Tarea
		@OneToMany(mappedBy="creador")
		public List<Tarea> getTareas() {
			return this.tareas;
		}

		public void setTareas(List<Tarea> tareas) {
			this.tareas = tareas;
		}

		public Tarea addTarea(Tarea tarea) {
			getTareas().add(tarea);
			tarea.setCreador(this);

			return tarea;
		}

		public Tarea removeTarea(Tarea tarea) {
			getTareas().remove(tarea);
			tarea.setCreador(null);

			return tarea;
		}
		
		//bi-directional many-to-one association to Cita
		@OneToMany(mappedBy="profesor")
		public List<Cita> getCitasProfesor() {
			return this.citasProfesor;
		}

		public void setCitasProfesor(List<Cita> citas2) {
			this.citasProfesor = citas2;
		}

		public Cita addCitas2(Cita citas2) {
			getCitasProfesor().add(citas2);
			citas2.setProfesor(this);

			return citas2;
		}

		public Cita removeCitas2(Cita citas2) {
			getCitasProfesor().remove(citas2);
			citas2.setProfesor(null);

			return citas2;
		}
	//bi-directional one-to-one association to Usuario
//	@OneToOne
//	@JoinColumn(name="")
//	public Usuario getUsuario() {
//		return this.usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}