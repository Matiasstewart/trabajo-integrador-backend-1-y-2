package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Usuario;
import com.example.proyectoIntegradorHibernate.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {
    private static final Logger LOGGER= Logger.getLogger(String.valueOf(UsuarioService.class));

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional< Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()){
            LOGGER.info("Se encontro un usuario");
            return usuarioBuscado.get();
        } else {
            LOGGER.error("No se pudo encontrar un usuario");
            throw new UsernameNotFoundException("ERROR: Usuario con email "+username+" no encontrado.");
        }
    }
}
