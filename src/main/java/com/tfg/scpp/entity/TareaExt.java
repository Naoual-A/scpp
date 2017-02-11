package com.tfg.scpp.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.tfg.scpp.entity.Asignatura.AsigUno;

public class TareaExt {
	
	private Tarea tarea;
	private String inicio;
	private String fin;
	
	public TareaExt(){
		tarea = new Tarea();
		inicio = "";
		fin = "";
	}
	
	public TareaExt(Tarea t, String i, String f){
		this.tarea = t;
		this.inicio =i;
		this.fin = f;
	}

	@NotNull(message="Campo Obligatorio", groups=AsigUno.class)
	@Valid
	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@NotEmpty(message="Campo Obligatorio", groups=AsigUno.class)
    @Valid
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	@NotEmpty(message="Campo Obligatorio", groups=AsigUno.class)
    @Valid
	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "TareaExt [tarea=" + tarea + ", inicio=" + inicio + ", fin="
				+ fin + "]";
	}
}
