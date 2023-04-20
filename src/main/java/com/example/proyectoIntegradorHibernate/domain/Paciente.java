package com.example.proyectoIntegradorHibernate.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String dni;
    @Column
    private LocalDate fechaDeIngreso;
    @OneToOne(cascade = CascadeType.ALL) //Relacion uno a uno unidireccional
    @JoinColumn(name = "id_domicilio", referencedColumnName = "id") //En referencedColumName ponemos el nombre del atributo de la otra tabla que queremos relacionar.
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    private Set<Turno> turnos= new HashSet<>();


    @Column
    private String email;

    public Paciente() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "\n🧑🏻Paciente{" +
                "id: '" + id +
                ", apellido: '" + apellido + '\'' +
                ", nombre: '" + nombre + '\'' +
                ", dni: '" + dni + '\'' +
                ", fechaNacimiento: '" + fechaDeIngreso +
                ", domicilio: '" + domicilio +
                "\n";
    }

}
