package com.example.proyectoIntegradorHibernate.security;

import com.example.proyectoIntegradorHibernate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired //Inyectamos el usuario service
    private UsuarioService usuarioService;
    @Autowired //Inyectamos uno de los encriptadores que posee Spring
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Esto esta porque las contraseñas estan encriptadas
    // Y para poder ir a buscar un usuario a la BD
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN","USER")
                .antMatchers("/pacientes/**").hasRole("ADMIN")
                .antMatchers("/odontologos").hasRole("ADMIN")
                .antMatchers("/turnos/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .and()
                .logout();
    }

    @Bean // Lo necesitamos para poder usar el configure AuthenticationManagerBuilder
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
}
