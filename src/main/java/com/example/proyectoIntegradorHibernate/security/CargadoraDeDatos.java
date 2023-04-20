/*

package com.example.proyectoIntegradorHibernate.security;

import com.example.proyectoIntegradorHibernate.domain.*;
import com.example.proyectoIntegradorHibernate.dto.TurnoDTO;
import com.example.proyectoIntegradorHibernate.repository.UsuarioRepository;
import com.example.proyectoIntegradorHibernate.service.OdontologoService;
import com.example.proyectoIntegradorHibernate.service.PacienteService;
import com.example.proyectoIntegradorHibernate.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component //Notacion para decirle a Spring que la tenga en cuenta, pero que no es un Service, Entity, Controller, ect
public class CargadoraDeDatos implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private TurnoService turnoService;

    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository, PacienteService pacienteService, OdontologoService odontologoService, TurnoService turnoService) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.turnoService = turnoService;
    }

    //Para que inserte un usuario al arrancar, vendria a funcionar como un script en los DAO
    @Override
    public void run(ApplicationArguments args) throws Exception{
        //CREAMOS EL CIFRADOR
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();

        // CREAMOS UN USARIO ADMIN
        String passwordAdminACifrar = "admin";
        String passwordAdminCifrada = cifrador.encode(passwordAdminACifrar);

        Usuario usuarioAdminAInsertar= new Usuario("Matias","Stewart","admin@gmail.com",passwordAdminCifrada, UsuarioRol.ROLE_ADMIN);

        usuarioRepository.save(usuarioAdminAInsertar);

        //CREAMOS UN USUARIO USER
        String passwordUserACifrar = "useruser";
        String passwordUserCifrada = cifrador.encode(passwordUserACifrar);

        Usuario usuarioUserAInsertar= new Usuario("Juan","Gomez","user@gmail.com",passwordUserCifrada, UsuarioRol.ROLE_USER);

        usuarioRepository.save(usuarioUserAInsertar);

        //CREAMOS DOS PACIENTES

        Domicilio domicilioPacienteUno = new Domicilio();
        domicilioPacienteUno.setCalle("Olavarria");
        domicilioPacienteUno.setNumero("48");
        domicilioPacienteUno.setLocalidad("Moron");
        domicilioPacienteUno.setProvincia("Buenos Aires");

        Paciente pacienteUno = new Paciente();
        pacienteUno.setNombre("Kevin");
        pacienteUno.setApellido("Lo Celso");
        pacienteUno.setDni("41568495");
        pacienteUno.setEmail("kevo@gmail.com");
        pacienteUno.setDomicilio(domicilioPacienteUno);
        pacienteUno.setFechaDeIngreso(LocalDate.of(2022,05,21));

        pacienteService.crearPaciente(pacienteUno);

        Domicilio domicilioPacienteDos = new Domicilio();
        domicilioPacienteDos.setCalle("Juarez");
        domicilioPacienteDos.setNumero("1265");
        domicilioPacienteDos.setLocalidad("Merlo");
        domicilioPacienteDos.setProvincia("Tucuman");

        Paciente pacienteDos = new Paciente();
        pacienteDos.setNombre("Miguel");
        pacienteDos.setApellido("Ribas");
        pacienteDos.setDni("18789456");
        pacienteDos.setEmail("miguelito@gmail.com");
        pacienteDos.setDomicilio(domicilioPacienteDos);
        pacienteDos.setFechaDeIngreso(LocalDate.of(2019,03,24));

        pacienteService.crearPaciente(pacienteDos);

        //CREAMOS DOS ODONTOLOGOS

        Odontologo odontoUno = new Odontologo();

        odontoUno.setNombre("Cristian");
        odontoUno.setApellido("Oroz");
        odontoUno.setMatricula("AB456ZD");

        odontologoService.crearOdontologo(odontoUno);

        Odontologo odontoDos = new Odontologo();

        odontoDos.setNombre("Tadeo");
        odontoDos.setApellido("Rios");
        odontoDos.setMatricula("AD415AF");

        odontologoService.crearOdontologo(odontoDos);

        //CREAMOS DOS TURNOS

        TurnoDTO turnoUno = new TurnoDTO();

        turnoUno.setNombre_paciente("Kevin");
        turnoUno.setNombre_odontologo("Cristian");
        turnoUno.setId_paciente(1L);
        turnoUno.setId_odontologo(1L);
        turnoUno.setFecha(LocalDate.of(2023,9,21));

        turnoService.guardarTurno(turnoUno);


        TurnoDTO turnoDos = new TurnoDTO();

        turnoDos.setNombre_paciente("Miguel");
        turnoDos.setNombre_odontologo("Tadeo");
        turnoDos.setId_paciente(2L);
        turnoDos.setId_odontologo(2L);
        turnoDos.setFecha(LocalDate.of(2023,9,26));

        turnoService.guardarTurno(turnoDos);

    }
}

 */