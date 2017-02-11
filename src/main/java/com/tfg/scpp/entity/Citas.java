package com.tfg.scpp.entity;

import java.util.ArrayList;
import java.util.List;

public class Citas extends Cita {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> citas;
	
	public Citas(){
		super();
		citas = new ArrayList<String>();
	}

	public List<String> getCitas() {
		return citas;
	}

	public void setCitas(List<String> citas) {
		this.citas = citas;
	}
}
