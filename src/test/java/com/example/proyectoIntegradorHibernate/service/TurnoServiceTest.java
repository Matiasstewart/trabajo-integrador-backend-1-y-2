package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Domicilio;
import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.domain.Turno;
import com.example.proyectoIntegradorHibernate.dto.TurnoDTO;
import com.example.proyectoIntegradorHibernate.exceptions.BadRequestException;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//DESHABILITAR LA CLASE CARGADORADEDATOS PARA PODER CORRER LOS TEST

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {
    TurnoService turnoService;
    PacienteService pacienteService;
    OdontologoService odontologoService;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Test
    @Order(1)
    public void crearTurnoTest() throws BadRequestException {
        Odontologo odontologoParaElTurno = new Odontologo();

        odontologoParaElTurno.setNombre("Franco");
        odontologoParaElTurno.setApellido("Aguirre");
        odontologoParaElTurno.setMatricula("AB1245");

        Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoParaElTurno);

        Domicilio domicilioParaElPaciente = new Domicilio();

        domicilioParaElPaciente.setCalle("Cordoba");
        domicilioParaElPaciente.setNumero("45");
        domicilioParaElPaciente.setLocalidad("Pacheco");
        domicilioParaElPaciente.setProvincia("Buenos Aires");

        Paciente pacienteParaElTurno = new Paciente();

        pacienteParaElTurno.setNombre("Danilo");
        pacienteParaElTurno.setApellido("Avila");
        pacienteParaElTurno.setDni("38564789");
        pacienteParaElTurno.setFechaDeIngreso(LocalDate.of(2022,04,24));
        pacienteParaElTurno.setDomicilio(domicilioParaElPaciente);
        pacienteParaElTurno.setEmail("daniavila@gmail.com");

        Paciente pacienteGuardado = pacienteService.crearPaciente(pacienteParaElTurno);

        TurnoDTO turnoDTOAGuardar = new TurnoDTO();

        turnoDTOAGuardar.setNombre_odontologo(odontologoGuardado.getNombre());
        turnoDTOAGuardar.setNombre_paciente(pacienteGuardado.getNombre());
        turnoDTOAGuardar.setId_paciente(pacienteGuardado.getId());
        turnoDTOAGuardar.setId_odontologo(odontologoGuardado.getId());
        turnoDTOAGuardar.setFecha(LocalDate.of(2023,07,24));

        TurnoDTO turnoDTOGuardado = turnoService.guardarTurno(turnoDTOAGuardar);

        String nombreOdontologoEsperado = "Franco";
        Long idOdontologoEsperado =  1L;
        String nombrePacienteEsperado = "Danilo";
        Long idPacienteEsperado = 1L;
        LocalDate fechaEsperada = LocalDate.of(2023,07,24);

        String nombreOdontologoObtenido = turnoDTOGuardado.getNombre_odontologo();
        Long idOdontologoObtenido = turnoDTOGuardado.getId_odontologo();
        String nombrePacienteObtenido = turnoDTOGuardado.getNombre_paciente();
        Long idPacienteObtenido = turnoDTOGuardado.getId_paciente();
        LocalDate fechaObtenida = turnoDTOGuardado.getFecha();

        assertEquals(nombreOdontologoEsperado,nombreOdontologoObtenido);
        assertEquals(idOdontologoEsperado,idOdontologoObtenido);
        assertEquals(nombrePacienteEsperado,nombrePacienteObtenido);
        assertEquals(idPacienteEsperado,idPacienteObtenido);
        assertEquals(fechaEsperada,fechaObtenida);
    };

    @Test
    @Order(2)
    public void buscarTurnoPorIdTest(){
        Long idABuscar = 1L;
        Optional<TurnoDTO> turnoObtenido = turnoService.buscarTurno(idABuscar);

        assertNotNull(turnoObtenido.get());
    };

    @Test
    @Order(3)
    public void crearUnSegundoTurnoTest() throws BadRequestException{
        Odontologo odontologoParaElTurno = new Odontologo();

        odontologoParaElTurno.setNombre("Michael");
        odontologoParaElTurno.setApellido("Roberts");
        odontologoParaElTurno.setMatricula("AB456852");

        Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoParaElTurno);

        Domicilio domicilioParaElPaciente = new Domicilio();

        domicilioParaElPaciente.setCalle("Antartida Argentina");
        domicilioParaElPaciente.setNumero("78");
        domicilioParaElPaciente.setLocalidad("Rio Cuarto");
        domicilioParaElPaciente.setProvincia("Cordoba");

        Paciente pacienteParaElTurno = new Paciente();

        pacienteParaElTurno.setNombre("Julian");
        pacienteParaElTurno.setApellido("Alvarez");
        pacienteParaElTurno.setDni("42586798");
        pacienteParaElTurno.setFechaDeIngreso(LocalDate.of(2021,05,12));
        pacienteParaElTurno.setDomicilio(domicilioParaElPaciente);
        pacienteParaElTurno.setEmail("laarania@gmail.com");

        Paciente pacienteGuardado = pacienteService.crearPaciente(pacienteParaElTurno);

        TurnoDTO turnoDTOAGuardar = new TurnoDTO();

        turnoDTOAGuardar.setNombre_odontologo(odontologoGuardado.getNombre());
        turnoDTOAGuardar.setNombre_paciente(pacienteGuardado.getNombre());
        turnoDTOAGuardar.setId_paciente(pacienteGuardado.getId());
        turnoDTOAGuardar.setId_odontologo(odontologoGuardado.getId());
        turnoDTOAGuardar.setFecha(LocalDate.of(2023,8,14));

        TurnoDTO turnoDTOGuardado = turnoService.guardarTurno(turnoDTOAGuardar);

        String nombreOdontologoEsperado = "Michael";
        Long idOdontologoEsperado =  2L;
        String nombrePacienteEsperado = "Julian";
        Long idPacienteEsperado = 2L;
        LocalDate fechaEsperada = LocalDate.of(2023,8,14);

        String nombreOdontologoObtenido = turnoDTOGuardado.getNombre_odontologo();
        Long idOdontologoObtenido = turnoDTOGuardado.getId_odontologo();
        String nombrePacienteObtenido = turnoDTOGuardado.getNombre_paciente();
        Long idPacienteObtenido = turnoDTOGuardado.getId_paciente();
        LocalDate fechaObtenida = turnoDTOGuardado.getFecha();

        assertEquals(nombreOdontologoEsperado,nombreOdontologoObtenido);
        assertEquals(idOdontologoEsperado,idOdontologoObtenido);
        assertEquals(nombrePacienteEsperado,nombrePacienteObtenido);
        assertEquals(idPacienteEsperado,idPacienteObtenido);
        assertEquals(fechaEsperada,fechaObtenida);
    }

    @Test
    @Order(4)
    public void buscarTodosLosTurnosTest(){
        int valorEsperado = 2;
        int valorActual = turnoService.buscarTodosLosTurnos().size();

        assertEquals(valorEsperado,valorActual);
    }

    @Test
    @Order(5)
    public void actualizarUnTurnoTest(){
        TurnoDTO turnoDTOAActualizar = new TurnoDTO();

        turnoDTOAActualizar.setId(2L);
        turnoDTOAActualizar.setId_odontologo(1L);
        turnoDTOAActualizar.setNombre_odontologo("Franco");
        turnoDTOAActualizar.setId_paciente(2L);
        turnoDTOAActualizar.setNombre_paciente("Julian");
        turnoDTOAActualizar.setFecha(LocalDate.of(2023,8,14));

        TurnoDTO turnoActualizado = turnoService.actualizarTurno(turnoDTOAActualizar);

        String nombreOdontologoEsperado = "Franco";
        String nombreOdontologoObtenido = turnoActualizado.getNombre_odontologo();

        String nombrePacienteEsperado = "Julian";
        String nombrePacienteObtenido = turnoActualizado.getNombre_paciente();

        assertEquals(nombreOdontologoEsperado,nombreOdontologoObtenido);
        assertEquals(nombrePacienteEsperado,nombrePacienteObtenido);

    }

    @Test
    @Order(6)
    public void eliminarUnTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        turnoService.eliminarTurno(2L);

        int cantDeTurnosEsperados = 0;
        int cantDeTurnosObtenidos = turnoService.buscarTodosLosTurnos().size();

        assertEquals(cantDeTurnosEsperados,cantDeTurnosObtenidos);
    }
}