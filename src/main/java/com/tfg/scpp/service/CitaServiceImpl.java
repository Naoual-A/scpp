package com.tfg.scpp.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.scpp.controller.TutoriasController;
import com.tfg.scpp.entity.Cita;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.repository.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {
	
	private static final Logger logger = LoggerFactory.getLogger(CitaServiceImpl.class);
	
	@Autowired
	private CitaRepository citaRepo;
	
	@Override
	public Cita habilitarHoraTutoria(Cita cita) {
		return citaRepo.save(cita);
	}

	@Override
	public List<Cita> getCitasDisponibles(Date d1, Date d2, Usuario p) {
		logger.debug("fecha1: " + d1 + " fecha2: " + d2 + " profesor: " + p);
		List<Cita> citas = citaRepo.getCitasDisponibles(d1, d2, p); 
		logger.debug("citas: " + citas);
		return citas;
	}
	
	@Override
	public List<Cita> getCitasEntre(Date d1, Date d2, Usuario u) {
		return citaRepo.getCitasBetween(d1, d2, u);
	}
	
	@Override
	public Cita guardarCitaSolicitada(Cita cita) {
		return citaRepo.save(cita);
	}

	@Override
	public Cita findCitaById(long id) {
		return citaRepo.findOne(id);
	}

	@Override
	public List<Cita> getMisCitas(Usuario user) {
		return citaRepo.getCitasByUser(user);
	}

	@Override
	public void deshabilitarHoraTutoria(Cita cita) {
		citaRepo.delete(cita);
	}

	@Override
	public Cita findByHoraFechaProf(Date d, Time t, Usuario u) {
		return citaRepo.findByFechaHoraProf(d, t, u);
	}

	@Override
	public List<Cita> getCitasDelasemana(Date d1, Date d2, Usuario u) {
		return citaRepo.getCitasSolicitadasDelasemana(d1, d2, u);
	}
}
