package com.tfg.scpp.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.tfg.scpp.entity.*;

public interface CitaService {

	public Cita habilitarHoraTutoria(Cita cita);
	
	public void deshabilitarHoraTutoria(Cita cita);
	
	public List<Cita> getCitasDisponibles(Date d1,Date d2, Usuario p);
	
	public List<Cita> getCitasEntre(Date d1,Date d2, Usuario u);
	
	public Cita guardarCitaSolicitada(Cita cita);
	
	public Cita findCitaById(long id);
	
	public List<Cita> getMisCitas(Usuario user);
	
	public Cita findByHoraFechaProf(Date d, Time t, Usuario u);
	
	public List<Cita> getCitasDelasemana(Date d1, Date d2, Usuario u);
}
