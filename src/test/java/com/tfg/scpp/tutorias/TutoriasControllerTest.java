package com.tfg.scpp.tutorias;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.Test;

import com.tfg.scpp.controller.TutoriasController;

public class TutoriasControllerTest {

	private TutoriasController controller = new TutoriasController();
	
	@Test
	public void construirMapConLunesTest() {
		LocalDate date = LocalDate.of(2016, 07, 04);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "04/07/2016");
		mapCorrecto.put("Martes", "05/07/2016");
		mapCorrecto.put("Miércoles", "06/07/2016");
		mapCorrecto.put("Jueves", "07/07/2016");
		mapCorrecto.put("Viernes", "08/07/2016");
		Map<String, String> map = controller.construirMap("MONDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConMartesTest() {
		LocalDate date = LocalDate.of(2016, 9, 27);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "26/09/2016");
		mapCorrecto.put("Martes", "27/09/2016");
		mapCorrecto.put("Miércoles", "28/09/2016");
		mapCorrecto.put("Jueves", "29/09/2016");
		mapCorrecto.put("Viernes", "30/09/2016");
		Map<String, String> map = controller.construirMap("TUESDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConMiercolesTest() {
		LocalDate date = LocalDate.of(2017, 01, 04);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "02/01/2017");
		mapCorrecto.put("Martes", "03/01/2017");
		mapCorrecto.put("Miércoles", "04/01/2017");
		mapCorrecto.put("Jueves", "05/01/2017");
		mapCorrecto.put("Viernes", "06/01/2017");
		Map<String, String> map = controller.construirMap("WEDNESDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConJuevesTest() {
		LocalDate date = LocalDate.of(2016, 07, 07);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "04/07/2016");
		mapCorrecto.put("Martes", "05/07/2016");
		mapCorrecto.put("Miércoles", "06/07/2016");
		mapCorrecto.put("Jueves", "07/07/2016");
		mapCorrecto.put("Viernes", "08/07/2016");
		Map<String, String> map = controller.construirMap("THURSDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConViernesTest() {
		LocalDate date = LocalDate.of(2016, 10, 14);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "10/10/2016");
		mapCorrecto.put("Martes", "11/10/2016");
		mapCorrecto.put("Miércoles", "12/10/2016");
		mapCorrecto.put("Jueves", "13/10/2016");
		mapCorrecto.put("Viernes", "14/10/2016");
		Map<String, String> map = controller.construirMap("FRIDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConSabadoTest() {
		LocalDate date = LocalDate.of(2016, 9, 03);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "05/09/2016");
		mapCorrecto.put("Martes", "06/09/2016");
		mapCorrecto.put("Miércoles", "07/09/2016");
		mapCorrecto.put("Jueves", "08/09/2016");
		mapCorrecto.put("Viernes", "09/09/2016");
		Map<String, String> map = controller.construirMap("SATURDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}
	
	@Test
	public void construirMapConDomingoTest() {
		LocalDate date = LocalDate.of(2016, 10, 06);
		Map<String,String> mapCorrecto = new HashMap<String,String>();
		mapCorrecto.put("Lunes", "07/10/2016");
		mapCorrecto.put("Martes", "08/10/2016");
		mapCorrecto.put("Miércoles", "09/10/2016");
		mapCorrecto.put("Jueves", "10/10/2016");
		mapCorrecto.put("Viernes", "11/10/2016");
		Map<String, String> map = controller.construirMap("SUNDAY", date);
		
		assertEquals(mapCorrecto.get("Lunes"), map.get("Lunes"));
		assertEquals(mapCorrecto.get("Martes"), map.get("Martes"));
		assertEquals(mapCorrecto.get("Miércoles"), map.get("Miércoles"));
		assertEquals(mapCorrecto.get("Jueves"), map.get("Jueves"));
		assertEquals(mapCorrecto.get("Viernes"), map.get("Viernes"));
	}

}
