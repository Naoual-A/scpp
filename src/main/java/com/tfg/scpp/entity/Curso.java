package com.tfg.scpp.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String alias;
	private List<Grupo> grupos;

	public Curso() {
	}

	@Id
	@NotNull(message="Campo obligatorio", groups = CursoUno.class)
	@Valid
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message="Campo obligatorio", groups = CursoDos.class)
    @Valid
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	//bi-directional many-to-one association to Grupo
	@OneToMany(mappedBy="curso")
	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo addGrupo(Grupo grupo) {
		getGrupos().add(grupo);
		grupo.setCurso(this);

		return grupo;
	}

	public Grupo removeGrupo(Grupo grupo) {
		getGrupos().remove(grupo);
		grupo.setCurso(null);

		return grupo;
	}


	@Override
	public String toString() {
		return "Curso [id=" + id + ", alias=" + alias + "]";
	}
	
	public interface CursoUno{
		
	}
	public interface CursoDos{
		
	}
}