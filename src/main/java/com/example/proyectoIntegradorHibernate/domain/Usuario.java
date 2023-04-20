package com.example.proyectoIntegradorHibernate.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails { //Implementamos UserDetails, parte de Spring Security, destinado a la config de la clase que actua como usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column //No es necesario agregar esta notacion en las versiones actuales de Spring
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email; //Tambien podria ser un nombre de usuario

    @Column
    private String password;

    @Enumerated(EnumType.STRING) // Es buena practica agregar esta anotacion para indicarle que es un Enum y de que tipo
    private UsuarioRol rol;

    public Usuario(String nombre, String apellido, String email, String password, UsuarioRol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {
    }

    // La implementacion de UserDetails trae metodos abstractos que debemos implementar si o si.
    // Trae los metodos de una logica completa de administracion de usuarios, algunos no los tocaremos en detalle.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.name()); //SimpleGra... especifica que solo tenemos un rol y este espera un String, con el punto name() pasamos el enum a string
        return Collections.singletonList(grantedAuthority);
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
