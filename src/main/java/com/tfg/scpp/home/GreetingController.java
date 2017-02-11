package com.tfg.scpp.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
    @MessageMapping("/notificaciones")
    @SendTo("/topic/noti")
    public OutputMessage greeting(String n) throws Exception {
        Thread.sleep(1000); // simulated delay
        logger.debug("notificacion: " + n.toString());
        return new OutputMessage("from", n, " hello");
    }
    
	@RequestMapping(value = "/notificaciones")
	public String conectar(){
//		JavaScriptUtils
		return "indez";
	}
}
