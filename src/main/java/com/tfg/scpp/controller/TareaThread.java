package com.tfg.scpp.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tfg.scpp.entity.Tarea;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.TareaService;

public class TareaThread extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(TareaThread.class);
	
	private Usuario user;
	private TareaService tareaService;
	
	public TareaThread(Usuario u, TareaService tarea){
		this.user = u;
		this.tareaService = tarea;
	}
	
	public void run(){
		List<Tarea> tareas = tareaService.getTareasByCreadorActivas(user);
		Date today = Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		for(Tarea t: tareas){
			logger.debug("today: " + today);
			logger.debug("fecha fin: " + t.getFechaFin());
			if(today.compareTo(t.getFechaFin())> 0){
				logger.debug("entramos en el if");
				t.setActiva(false);
				tareaService.editarTarea(t);
			}
		}
		logger.debug("hebra teminada");
	}
}
