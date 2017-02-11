package com.tfg.scpp.home;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
//import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeControllerTest {

	@Test
	public void testHandleRequestView() throws JsonProcessingException {
		   ObjectMapper mapper=new ObjectMapper();
		    Map<String,String> dt=new Hashtable();
		    dt.put("1", "welcome");
		    dt.put("2", "bye");
		    String jsonString = mapper.writeValueAsString(dt);
		    System.out.println(jsonString);
		    }    
	
}
