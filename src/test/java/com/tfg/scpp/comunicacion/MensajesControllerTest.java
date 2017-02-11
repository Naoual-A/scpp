//package com.tfg.scpp.comunicacion;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
// 
//
//
//
//
//
//
//
//
//import com.tfg.scpp.config.AppConfig;
//import com.tfg.scpp.config.TestContext;
//import com.tfg.scpp.entity.Mensaje;
//import com.tfg.scpp.entity.Usuario;
//import com.tfg.scpp.service.MensajeService;
//import com.tfg.scpp.service.UsuarioService;
//
//import java.util.Arrays;
// 
//
//
//
//
//
//
//
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, AppConfig.class})
//@WebAppConfiguration
//public class MensajesControllerTest {
//	
//	private MockMvc mockMvc;
//	
//	@Autowired
//	@Qualifier("mensajeService")
//	private MensajeService mensajeServiceMock;
//	
//	@Autowired
//	@Qualifier("usuarioService")
//	private UsuarioService usuarioServiceMock;
//	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() {
//        //We have to reset our mock between tests because the mock objects
//        //are managed by the Spring container. If we would not reset them,
//        //stubbing and verified behavior would "leak" from one test to another.
//        Mockito.reset(usuarioServiceMock);
//        Mockito.reset(mensajeServiceMock);
//
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//	@Test
//	public void testNuevoMensaje() throws Exception {
////		Usuario user1 = new Usuario();
////		user1.setId("00000000");
////		user1.setNombre("jj");
////		user1.setApellidos("jj jj");
////		
////		Usuario user2 = new Usuario();
////		user2.setId("11111111");
////		user2.setNombre("ss");
////		user2.setApellidos("ss ss");
////		
////		when(usuarioServiceMock.findDestinatarios()).thenReturn(Arrays.asList(user1, user2));
////		
////		mockMvc.perform(get("/mensajes/nuevo"))
////			.andExpect(status().isOk())
////			.andExpect(view().name("comunicacion/NuevoMensaje"))
////			.andExpect(forwardedUrl("/WEB-INF/views/comunicacion/NuevoMensaje.jsp"))
////			.andExpect(model().attribute("dest", hasSize(2)))
////			.andExpect(model().attribute("dest", hasItem(
////                    allOf(
////                            hasProperty("id", is("00000000")),
////                            hasProperty("nombre", is("jj")),
////                            hasProperty("apellidos", is("jj jj"))
//////                            hasProperty("rol", is(1))
////                    )
////            )))
////            .andExpect(model().attribute("dest", hasItem(
////                    allOf(
////                            hasProperty("id", is("11111111")),
////                            hasProperty("nombre", is("ss")),
////                            hasProperty("apellidos", is("ss ss"))
//////                            hasProperty("rol", is(1))
////                    )
////            )));
////		
////		verify(usuarioServiceMock, times(1)).findDestinatarios();
////        verifyNoMoreInteractions(usuarioServiceMock);
////        verifyZeroInteractions(mensajeServiceMock);
//	}
//
////	@Test
////	public void testNuevoMensajeMensajeModel() {
////		fail("Not yet implemented");
////	}
////
////	@Test
////	public void testEnviarMensaje() {
////		fail("Not yet implemented");
////	}
////
////	@Test
////	public void testConsultarMensajes() {
////		fail("Not yet implemented");
////	}
////
////	@Test
////	public void testLeerMensaje() {
////		fail("Not yet implemented");
////	}
//
//}
