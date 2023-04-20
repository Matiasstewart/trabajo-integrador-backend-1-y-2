package com.example.proyectoIntegradorHibernate.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private Long id_odontologo;
    private Long id_paciente;
    private LocalDate fecha;
    private String nombre_odontologo;
    private String nombre_paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_odontologo() {
        return id_odontologo;
    }

    public void setId_odontologo(Long id_odontologo) {
        this.id_odontologo = id_odontologo;
    }

    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre_odontologo() {
        return nombre_odontologo;
    }

    public void setNombre_odontologo(String nombre_odontologo) {
        this.nombre_odontologo = nombre_odontologo;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }
}
