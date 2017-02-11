package com.tfg.scpp.controller;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfg.scpp.entity.Alumno;
import com.tfg.scpp.entity.Cita;
import com.tfg.scpp.entity.Citas;
import com.tfg.scpp.entity.Padre;
import com.tfg.scpp.entity.Profesor;
import com.tfg.scpp.entity.Usuario;
import com.tfg.scpp.service.CitaService;
import com.tfg.scpp.service.UsuarioService;

@Controller
public class TutoriasController {
	
	private static final Logger logger = LoggerFactory.getLogger(TutoriasController.class);
	
	@Autowired
	private CitaService citaService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/tutorias", method = RequestMethod.GET)
	public String tutorias(Model model, HttpSession session){
		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		Map<String,String> map = construirMap(dayOfWeek, LocalDate.now());
		Usuario user = (Usuario) session.getAttribute("user");
		List<Cita> citas = getCitasSemanaByTutor(user);
		List<String> citasSemana = new ArrayList<String>();
		Citas solicitadas = new Citas();
		List<String> cSolicitadas = new ArrayList<String>();
		String year = "";
		String month = "";
		String day = "";
		String time = "";
		String fecha = "";
		String valor = "";
		for(Cita c: citas){
			fecha = c.getFecha().toString() + " " + c.getHora().toString();
//			logger.debug("f: " + fecha);
			StringTokenizer stk = new StringTokenizer(fecha, " -");
			year = stk.nextToken();
			month = stk.nextToken();
			day = stk.nextToken();
			time = stk.nextToken();
			time = time.substring(0, time.length()-3);
			valor = day + "/" + month + "/" + year + "-" + time;
//			logger.debug("valor construido: " + valor);
			citasSemana.add(valor);
			if(c.getSolicitada()){
				cSolicitadas.add(valor);
			}
		}
		logger.debug("citas semana: " + citasSemana);
		Citas c = new Citas();
		c.setCitas(citasSemana);
		solicitadas.setCitas(cSolicitadas);

		model.addAttribute("map", map);
		model.addAttribute("citas", c);
		model.addAttribute("solicitadas",solicitadas.getCitas());
		
		return "tutorias/panelTutorias";
	}
	
	@RequestMapping(value = "/tutorias", method = RequestMethod.POST)
	public String habilitarTutorias(@ModelAttribute("citas") Citas citas, BindingResult result, HttpSession session) throws ParseException{

		//con la sesion recogemos el usuario que esta creando las citas
		Usuario user = (Usuario) session.getAttribute("user");
		
		//citas que habia antes del cambio, desde BBDD
		List<Cita> citaOriginales = getCitasSemanaByTutor(user);
		logger.debug("org: " + citaOriginales.size());
		//cita despues de habilitar/deshabilitar
		List<Cita> citasVista = new ArrayList<Cita>();
		
		if(citas.getCitas()!=null){
			logger.debug("no es null");
			for(String str: citas.getCitas()){
				logger.debug("cita de la vista: " + str);
				Cita cita = new Cita();
				cita.setProfesor(user);
				StringTokenizer stk = new StringTokenizer(str, "-");
				String fecha = stk.nextToken();
				String hora = stk.nextToken();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = sdf.parse(fecha + " " + hora + ":00");  
				
				cita.setFecha(date);
				Time time = new Time(date.getTime());
				cita.setHora(time);
				logger.debug(cita.toString());
				citasVista.add(cita);
			}			
		}
		
		logger.debug("citas traidas desde la vista: " + citasVista);
		List<Cita> citasResultantes = new ArrayList<Cita>();
		Cita cita = new Cita();
		for(Cita c:citasVista){
			cita = citaService.findByHoraFechaProf(c.getFecha(), c.getHora(), c.getProfesor());
			if(cita != null){
				logger.debug("existe en BBDD: " + cita.toString());
				citasResultantes.add(cita);
			}else{
				logger.debug("no existe en BBDD: " + c.toString());
				citasResultantes.add(c);
			}
		}
		
		
		logger.debug("citas originales: " + citaOriginales.size() + " " + citaOriginales);
		logger.debug("citas resultantes: " + citasResultantes.size() + " " + citasResultantes);
		if(citaOriginales.size() > citasResultantes.size()){
			//han sido deshabilitadas, se borran las que han sido deshabilitadas
			for(Cita c : citaOriginales){
				if(!citasResultantes.contains(c)){
					//se borra
					if(!c.getSolicitada()){
						logger.debug("se borra: " + c.toString());
						citaService.deshabilitarHoraTutoria(c);						
					}
				}
			}
		} else if (citaOriginales.size() < citasResultantes.size()){
			//han sido habilitadas, se agregan las nuevas
			for(Cita c : citasResultantes){
				if(!citaOriginales.contains(c)){
					//se agrega
					logger.debug("se agrega: " + c.toString());
					citaService.habilitarHoraTutoria(c);
				}
			}
		} else {
			for(Cita c: citasResultantes){
				logger.debug("en el caso igualado");
				logger.debug("cita: " + c.getHora());
				if(!citaOriginales.contains(c)){
					//se agrega
					logger.debug("se agrega: " + c.toString());
					citaService.habilitarHoraTutoria(c);
					logger.debug("lo eliminamos de originales");
				}
				citaOriginales.remove(c);
			}
			logger.debug("cuanto queda en originales: " + citaOriginales.size());
			for(Cita c:citaOriginales){
				citaService.deshabilitarHoraTutoria(c);
			}
		}
		
		return "redirect:/tutorias";
	}
	
	@RequestMapping(value = "/tutorias/solicitar", method = RequestMethod.GET)
	public String solicitar(Model model, HttpSession session){

		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		
		//getcitasbetween
//		Date lunes = getLunes(dayOfWeek, LocalDate.now());
//		Date viernes = getViernes(dayOfWeek, LocalDate.now());
		Date today = Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date otherDay = getOtherDay(dayOfWeek, LocalDate.now());
		logger.debug("other day: " + otherDay);
		
		Usuario user = (Usuario) session.getAttribute("user");
		Padre padre = (Padre) user;
		Alumno alumno = (Alumno) usuarioService.getUserById(padre.getIdAlumno());
		Profesor profesor = (Profesor) alumno.getGrupo().getTutor();

		List<Cita> citas = citaService.getCitasDisponibles(today, otherDay, profesor);
		logger.debug("citas recogidas: " + citas.toString());
		Map<String,String> map = construirMap4(dayOfWeek, LocalDate.now());
		Object[] col = map.keySet().toArray();
		Map<String,List<Cita>> map2 = construirMap2(citas);
		model.addAttribute("map", map);
		model.addAttribute("map2", map2);
		model.addAttribute("col", col);
		logger.debug("col: " + Arrays.toString(col));
//		model.addAttribute("citas", new Citas());
			return "tutorias/solicitar";
	}
	
	@RequestMapping(value = "tutorias/solicitar/{id}")
	public String guardarCitaSolicitada(@PathVariable long id, @ModelAttribute("cita") Cita c, 
			BindingResult result, HttpSession session){
		logger.debug("guardar cita solicitada");
		c = citaService.findCitaById(id);
		logger.debug(c.toString());
		Usuario user = (Usuario) session.getAttribute("user");
		c.setSolicitante(user);
		c.setSolicitada(true);
		citaService.guardarCitaSolicitada(c);
		return "redirect:/";
	}
	
	@RequestMapping(value = "tutorias/misTutorias", method = RequestMethod.GET)
	public String visualizarMisCitas(Model model, HttpSession session){
		
		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		
		Map<String,String> map = construirMap(dayOfWeek, LocalDate.now());
		
		model.addAttribute("map", map);
		
		//traer las citas con la sesion
		Usuario user = (Usuario) session.getAttribute("user");
		
		List<Cita> citas = citaService.getMisCitas(user);
		logger.debug("citas extraidas: " + citas.toString());
		Map<String,Cita> mapId = new HashMap<String,Cita>();
		String str = "";
		String str2 = "";
		for(Cita c:citas){
			str = c.getFecha().toString() + " " + c.getHora().toString();
			StringTokenizer stk = new StringTokenizer(str, "- :");
			while(stk.hasMoreTokens()){
				str2 = str2 + stk.nextToken();
			}
//			logger.debug("contenido str2: " + str2);
			mapId.put(str2, c);
			str2 = "";
		}
		logger.debug("lista construida: " + mapId.toString());
		
		model.addAttribute("mapId", mapId);
		Map<String,String> map3 = construirMap3(map);
		model.addAttribute("map3",map3);
		return "tutorias/visualizarTutorias";
	}
	
	/**
	 * Este método construye un map donde las claves son los días de la semana, lunes, martes,...
	 * hasta viernes, y el valor de cada clave es la fecha correspondiente a ese dia en la semana
	 * actual
	 * Ejemplo: [Lunes=29/08/2016, Martes=30/08/2016,....]
	 * @param str es el nombre del día actual
	 * @param date es la fecha del día actual
	 * @return
	 */
	public Map<String,String> construirMap(String str, LocalDate date){
		Map<String,String> map = new HashMap<String,String>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		switch (str) {
		case "MONDAY":
			map.put("Lunes", date.format(formatter));
			map.put("Martes", date.plusDays(1).format(formatter));
			map.put("Miércoles", date.plusDays(2).format(formatter));
			map.put("Jueves", date.plusDays(3).format(formatter));
			map.put("Viernes", date.plusDays(4).format(formatter));
			break;
			
		case "TUESDAY":
			map.put("Lunes", date.minusDays(1).format(formatter));
			map.put("Martes", date.format(formatter));
			map.put("Miércoles", date.plusDays(1).format(formatter));
			map.put("Jueves", date.plusDays(2).format(formatter));
			map.put("Viernes", date.plusDays(3).format(formatter));
			break;
			
		case "WEDNESDAY":
			map.put("Lunes", date.minusDays(2).format(formatter));
			map.put("Martes", date.minusDays(1).format(formatter));
			map.put("Miércoles", date.format(formatter));
			map.put("Jueves", date.plusDays(1).format(formatter));
			map.put("Viernes", date.plusDays(2).format(formatter));
			break;
			
		case "THURSDAY":
			map.put("Lunes", date.minusDays(3).format(formatter));
			map.put("Martes", date.minusDays(2).format(formatter));
			map.put("Miércoles", date.minusDays(1).format(formatter));
			map.put("Jueves", date.format(formatter));
			map.put("Viernes", date.plusDays(1).format(formatter));
			break;
			
		case "FRIDAY":
			map.put("Lunes", date.minusDays(4).format(formatter));
			map.put("Martes", date.minusDays(3).format(formatter));
			map.put("Miércoles", date.minusDays(2).format(formatter));
			map.put("Jueves", date.minusDays(1).format(formatter));
			map.put("Viernes", date.format(formatter));
			break;
		
		case "SATURDAY":
			map.put("Lunes", date.plusDays(2).format(formatter));
			map.put("Martes", date.plusDays(3).format(formatter));
			map.put("Miércoles", date.plusDays(4).format(formatter));
			map.put("Jueves", date.plusDays(5).format(formatter));
			map.put("Viernes", date.plusDays(6).format(formatter));
			break;
		
		case "SUNDAY":
			map.put("Lunes", date.plusDays(1).format(formatter));
			map.put("Martes", date.plusDays(2).format(formatter));
			map.put("Miércoles", date.plusDays(3).format(formatter));
			map.put("Jueves", date.plusDays(4).format(formatter));
			map.put("Viernes", date.plusDays(5).format(formatter));
			break;
		}
		logger.debug("map construido: " + map.toString());
		return map;
	}
	
	public Map<String,String> construirMap4(String str, LocalDate date){

		Map<String, String> map = new LinkedHashMap<String,String>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		switch (str) {
		case "MONDAY":
			map.put("Lunes", date.format(formatter));
			map.put("Martes", date.plusDays(1).format(formatter));
			map.put("Miércoles", date.plusDays(2).format(formatter));
			map.put("Jueves", date.plusDays(3).format(formatter));
			map.put("Viernes", date.plusDays(4).format(formatter));
			break;
			
		case "TUESDAY":
			map.put("Martes", date.format(formatter));
			map.put("Miércoles", date.plusDays(1).format(formatter));
			map.put("Jueves", date.plusDays(2).format(formatter));
			map.put("Viernes", date.plusDays(3).format(formatter));
			map.put("Lunes", date.plusDays(6).format(formatter));
			break;
			
		case "WEDNESDAY":
			map.put("Miércoles", date.format(formatter));
			map.put("Jueves", date.plusDays(1).format(formatter));
			map.put("Viernes", date.plusDays(2).format(formatter));
			map.put("Lunes", date.plusDays(5).format(formatter));
			map.put("Martes", date.plusDays(6).format(formatter));
			break;
			
		case "THURSDAY":
			map.put("Jueves", date.format(formatter));
			map.put("Viernes", date.plusDays(1).format(formatter));
			map.put("Lunes", date.plusDays(4).format(formatter));
			map.put("Martes", date.plusDays(5).format(formatter));
			map.put("Miércoles", date.plusDays(6).format(formatter));
			break;
			
		case "FRIDAY":
			map.put("Viernes", date.format(formatter));
			map.put("Lunes", date.plusDays(3).format(formatter));
			map.put("Martes", date.plusDays(4).format(formatter));
			map.put("Miércoles", date.plusDays(5).format(formatter));
			map.put("Jueves", date.plusDays(6).format(formatter));
			break;
		
		case "SATURDAY":
			map.put("Lunes", date.plusDays(2).format(formatter));
			map.put("Martes", date.plusDays(3).format(formatter));
			map.put("Miércoles", date.plusDays(4).format(formatter));
			map.put("Jueves", date.plusDays(5).format(formatter));
			map.put("Viernes", date.plusDays(6).format(formatter));
			break;
		
		case "SUNDAY":
			map.put("Lunes", date.plusDays(1).format(formatter));
			map.put("Martes", date.plusDays(2).format(formatter));
			map.put("Miércoles", date.plusDays(3).format(formatter));
			map.put("Jueves", date.plusDays(4).format(formatter));
			map.put("Viernes", date.plusDays(5).format(formatter));
			break;
		}
		logger.debug("map4 construido: " + map.toString());
		return map;
	}
	/**
	 * Este método obtiene la fecha del lunes de la semana actual
	 * @param str nombre del día actual
	 * @param date fecha del día actual
	 * @return
	 */
	public Date getLunes(String str, LocalDate date) {
		Date d = new Date();
		switch (str) {
		case "MONDAY":
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "TUESDAY":
			date = date.minusDays(1);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "WEDNESDAY":
			date = date.minusDays(2);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "THURSDAY":
			date = date.minusDays(3);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "FRIDAY":
			date = date.minusDays(4);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SATURDAY":
			date = date.plusDays(2);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SUNDAY":
			date = date.plusDays(1);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		}
		return d;
	}
	
	/**
	 * Este método obtiene la fecha del viernes de la semana actual
	 * @param str es el nombre del día actual
	 * @param date es la fecha del día actual
	 * @return
	 */
	public static Date getViernes(String str, LocalDate date) {
		Date d = new Date();
		switch (str) {
		case "MONDAY":
			date = date.plusDays(4);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "TUESDAY":
			date = date.plusDays(3);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "WEDNESDAY":
			date = date.plusDays(2);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "THURSDAY":
			date = date.plusDays(1);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "FRIDAY":
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SATURDAY":
			date = date.plusDays(6);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SUNDAY":
			date = date.plusDays(7);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		}
		return d;
	}
	
	public Date getOtherDay(String str, LocalDate date) {
		Date d = new Date();
		switch (str) {
		case "MONDAY":
			date = date.plusDays(4);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "TUESDAY":
			date = date.plusDays(6);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "WEDNESDAY":
			date = date.plusDays(7);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "THURSDAY":
			date = date.plusDays(8);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "FRIDAY":
			date = date.plusDays(9);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SATURDAY":
			date = date.plusDays(6);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		case "SUNDAY":
			date = date.plusDays(7);
			d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		}
		return d;
	}
	
	/**
	 * Este método construye un map donde las claves son los días de la semana y el valor de cada
	 * una de ellas es una lista con todas las citas que haya ese día
	 * @param citas conjunto de citas extraido desde BBDD
	 * @return
	 */
	public Map<String, List<Cita>> construirMap2(List<Cita> citas){
		Map<String,List<Cita>> map = new HashMap<String,List<Cita>>();
		map.put("MONDAY", new ArrayList<Cita>());
		map.put("TUESDAY", new ArrayList<Cita>());
		map.put("WEDNESDAY", new ArrayList<Cita>());
		map.put("THURSDAY", new ArrayList<Cita>());
		map.put("FRIDAY", new ArrayList<Cita>());
		
		Date fecha;
		String day;
		String month;
		String year;
		for(Cita c:citas){
			fecha = c.getFecha();
			logger.debug("fecha: " + fecha.toString());
			StringTokenizer stk = new StringTokenizer(fecha.toString(), "-");
			year = stk.nextToken();
			month = stk.nextToken();
			day = stk.nextToken();
			logger.debug("day: " + day + " month: " + month + " year: " + year);
			day = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day)).
					getDayOfWeek().name();
			List<Cita> list = map.get(day);
			list.add(c);
			map.put(day, list);
		}
		
		Map<String, List<Cita>> m = new HashMap<String,List<Cita>>();
		m.put("Lunes", map.get("MONDAY"));
		m.put("Martes", map.get("TUESDAY"));
		m.put("Miércoles", map.get("WEDNESDAY"));
		m.put("Jueves", map.get("THURSDAY"));
		m.put("Viernes", map.get("FRIDAY"));
		
		logger.debug("map2 constuirdo: " + map.toString());
		logger.debug("map a devolver: " + m.toString());
		return m;
	}
	
	/**
	 * Este metodo construye un map donde las claves son los días de la semana y el valor de 
	 * cada una de ellas es la fecha de ese día en la actual semana, el forma de fecha no 
	 * contiene separadores. Ejemplo: [Lunes=20160829, Martes=20160830,...]
	 * @param map
	 * @return
	 */
	public Map<String,String> construirMap3(Map<String,String> map){
		Map<String,String> map3 = new HashMap<String,String>();
		String valor = "";
		String fecha = "";
		String dia = "";
		String mes = "";
		String anyo = "";
		
		for(String key:map.keySet()){
			fecha = map.get(key);
			StringTokenizer stk = new StringTokenizer(fecha, "/");
			dia = stk.nextToken();
			mes = stk.nextToken();
			anyo = stk.nextToken();
			valor = anyo + mes + dia;
			map3.put(key, valor);
		}
				
		logger.debug("map3 construido: " + map3.toString());
		return map3;
	}
	
	public List<Cita> getCitasSemanaByTutor(Usuario user){
		
		String dayOfWeek = LocalDateTime.now().getDayOfWeek().name();
		Date lunes = getLunes(dayOfWeek, LocalDate.now());
		Date viernes = getViernes(dayOfWeek, LocalDate.now());
		List<Cita> citas = citaService.getCitasEntre(lunes, viernes, user);
		return citas;
	}
}
