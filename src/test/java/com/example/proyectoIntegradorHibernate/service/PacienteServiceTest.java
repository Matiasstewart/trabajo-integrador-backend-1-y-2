package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Domicilio;
import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//DESHABILITAR LA CLASE CARGADORADEDATOS PARA PODER CORRER LOS TEST

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    //TESTEAMOS EL GUARDADO DE UN PACIENTE
    @Test
    @Order(1)
    public void guardarPacienteTest(){
        // Creamos el domicilio del paciente y le seteamos sus valores
        Domicilio domicilioPaciente = new Domicilio();
        domicilioPaciente.setCalle("Alvarado");
        domicilioPaciente.setLocalidad("Tigre");
        domicilioPaciente.setNumero("24");
        domicilioPaciente.setProvincia("Bs As");

        // Creamos el paciente y le seteamos sus valores
        Paciente pacienteAGuardar = new Paciente();
        pacienteAGuardar.setNombre("Matias");
        pacienteAGuardar.setApellido("Stewart");
        pacienteAGuardar.setDni("45785649");
        pacienteAGuardar.setFechaDeIngreso(LocalDate.of(2021,02,15));
        pacienteAGuardar.setDomicilio(domicilioPaciente);
        pacienteAGuardar.setEmail("mas@gmail.com");

        // Invocamos al Service y le pedimos que guarde el paciente
        Paciente pacienteGuardado = pacienteService.crearPaciente(pacienteAGuardar);

        // Verificamos que coincida el numero de id del paciente guardado con el esperado
        assertEquals(1L,pacienteGuardado.getId());
    }

    //TESTEAMOS EL BUSCAR UN PACIENTE POR SU ID
    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar=1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarXId(idABuscar);

        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void guardarUnSegundoPacienteTest(){
        // Creamos el domicilio del paciente y le seteamos sus valores
        Domicilio domicilioPaciente = new Domicilio();
        domicilioPaciente.setCalle("Misiones");
        domicilioPaciente.setLocalidad("Tigre");
        domicilioPaciente.setNumero("65");
        domicilioPaciente.setProvincia("Bs As");

        // Creamos el paciente y le seteamos sus valores
        Paciente pacienteAGuardar = new Paciente();
        pacienteAGuardar.setNombre("Marcos");
        pacienteAGuardar.setApellido("Parlangeli");
        pacienteAGuardar.setDni("41124456");
        pacienteAGuardar.setFechaDeIngreso(LocalDate.of(2023,01,23));
        pacienteAGuardar.setDomicilio(domicilioPaciente);
        pacienteAGuardar.setEmail("map@gmail.com");

        // Invocamos al Service y le pedimos que guarde el paciente
        Paciente pacienteGuardado = pacienteService.crearPaciente(pacienteAGuardar);

        // Verificamos que coincida el numero de id del paciente guardado con el esperado
        assertEquals(2L,pacienteGuardado.getId());
    }

    @Test
    @Order(4)
    public void buscarTodosLosPacientesTest(){
        int cantidadEsperadaDePacientes = 2;
        int cantActualDePacientes = pacienteService.buscarTodosLosPacientes().size();

        assertEquals(cantidadEsperadaDePacientes,cantActualDePacientes);
    }

    @Test
    @Order(5)
    public void actualizarPacientesTest(){
        Domicilio domicilioPaciente = new Domicilio();
        domicilioPaciente.setId(2L);
        domicilioPaciente.setCalle("Chaco");
        domicilioPaciente.setLocalidad("San Fernando");
        domicilioPaciente.setNumero("65");
        domicilioPaciente.setProvincia("Bs As");

        // Creamos el paciente y le seteamos sus valores
        Paciente pacienteAActualizar = new Paciente();
        pacienteAActualizar.setId(2L);
        pacienteAActualizar.setNombre("Miguel");
        pacienteAActualizar.setApellido("Parlangeli");
        pacienteAActualizar.setDni("41124456");
        pacienteAActualizar.setFechaDeIngreso(LocalDate.of(2023,01,23));
        pacienteAActualizar.setDomicilio(domicilioPaciente);
        pacienteAActualizar.setEmail("map@gmail.com");

        Paciente pacienteActualizado = pacienteService.actualizarPaciente(pacienteAActualizar);

        String calleEsperada = "Chaco";
        String calleActual = pacienteActualizado.getDomicilio().getCalle();

        assertEquals(calleEsperada,calleActual);

        String nombreEsperado = "Miguel";
        String nombreActual = pacienteActualizado.getNombre();

        assertEquals(nombreEsperado,nombreActual);
    }

    @Test
    @Order(6)
    public void eliminarPacientesTest() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        pacienteService.eliminarPaciente(2L);

        int cantEsperadaDePacientes = 0;
        int cantActualDePacientes = pacienteService.buscarTodosLosPacientes().size();

        assertEquals(cantEsperadaDePacientes,cantActualDePacientes);
    }
}