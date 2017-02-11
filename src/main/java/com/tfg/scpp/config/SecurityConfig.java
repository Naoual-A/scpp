package com.tfg.scpp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      	.antMatchers("/home").permitAll()  
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/mensajes/**").access("hasAnyRole('ADMIN', 'PROFESOR', 'PADRE')")
        .antMatchers("/tareas/mistareas").access("hasAnyRole('ADMIN', 'PROFESOR', 'PADRE', 'ALUMNO')")
        .antMatchers("/tareas/ver/**").access("hasAnyRole('ADMIN', 'PROFESOR', 'PADRE', 'ALUMNO')")
        .antMatchers("/tareas/**").access("hasAnyRole('ADMIN', 'PROFESOR')")
        .antMatchers("/tutorias/solicitar/**").access("hasAnyRole('ADMIN', 'PADRE')")
        .antMatchers("/tutorias/confirmar").access("hasAnyRole('ADMIN', 'PADRE')")
        .antMatchers("/tutorias/**").access("hasAnyRole('ADMIN', 'PROFESOR')")
        .antMatchers("/cursos/**").access("hasRole('ADMIN')")
        .antMatchers("/grupos/misgrupos").access("hasRole('PROFESOR')")
        .antMatchers("/grupos/misgrupostutorizados").access("hasRole('PROFESOR')")
        .antMatchers("/grupos/ver/**").access("hasAnyRole('ADMIN', 'PROFESOR')")
        .antMatchers("/grupos/**").access("hasAnyRole('ADMIN', 'PROFESOR')")
        .antMatchers("/").access("hasAnyRole('ADMIN', 'PROFESOR', 'PADRE', 'ALUMNO')")
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/principal")
        .usernameParameter("id").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception{
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
