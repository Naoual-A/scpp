package com.tfg.scpp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String alias;
	private Curso curso;
	private Usuario tutor;
	private List<Alumno> alumnos;
	private List<Asignatura> asignaturas;

	public Grupo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(value=1, message = "Campo Obligatorio", groups=Grupo.GrupoUno.class)
	@Valid
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message="Campo obligatorio", groups=Curso.CursoUno.class)
	@Valid
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="CURSO")
	@NotNull(message="Campo obligatorio", groups=Curso.CursoUno.class)
	@Valid
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso cursoBean) {
		this.curso = cursoBean;
	}


	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="TUTOR", nullable=true)
	public Usuario getTutor() {
		return this.tutor;
	}

	public void setTutor(Usuario usuario) {
		this.tutor = usuario;
	}

	@OneToMany(mappedBy="grupo")
	@LazyCollection(LazyCollectionOption.FALSE) 
	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asig) {
		this.asignaturas = asig;
	}

	public Asignatura addAsignatura(Asignatura asig) {
		getAsignaturas().add(asig);
		asig.setGrupo(this);

		return asig;
	}

	public Asignatura removeAsignatura(Asignatura asig) {
		getAlumnos().remove(asig);
		asig.setGrupo(null);

		return asig;
	}

	@OneToMany(mappedBy="grupo")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setGrupo(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setGrupo(null);

		return alumno;
	}
	
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", alias=" + alias + ", curso=" + curso
				+ ", tutor=" + tutor + "]";
	}

	public interface GrupoUno{
		
	}
}