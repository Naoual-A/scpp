package com.tfg.scpp.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfg.scpp.service.MensajeService;
import com.tfg.scpp.service.UsuarioService;

@Configuration
public class TestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MensajeService mensajeService() {
        return Mockito.mock(MensajeService.class);
    }
    
    @Bean
    public UsuarioService usuarioService(){
    	return Mockito.mock(UsuarioService.class);
    }
}