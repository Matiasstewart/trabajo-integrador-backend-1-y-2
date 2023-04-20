package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//DESHABILITAR LA CLASE CARGADORADEDATOS PARA PODER CORRER LOS TEST

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    OdontologoService odontologoService;

    @Autowired
    public OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // TESTEAMOS EL GUARDAR UN ODONTOLOGO
    @Test
    @Order(1)
    public void guardarOdontologoTest(){
        Odontologo odontologoAGuardar = new Odontologo();

        odontologoAGuardar.setNombre("Miguel");
        odontologoAGuardar.setApellido("Gomez");
        odontologoAGuardar.setMatricula("AB1245");

        Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoAGuardar);

        Long idEsperado = 1L;
        String nombreEsperado= "Miguel";
        String apellidoEsperado= "Gomez";
        String matriculaEsperada= "AB1245";

        assertEquals(idEsperado,odontologoGuardado.getId());
        assertEquals(nombreEsperado,odontologoGuardado.getNombre());
        assertEquals(apellidoEsperado,odontologoGuardado.getApellido());
        assertEquals(matriculaEsperada,odontologoGuardado.getMatricula());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);

        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void guardarUnSegundoOdontologoTest(){
        Odontologo odontologoAGuardar = new Odontologo();

        odontologoAGuardar.setNombre("Luciano");
        odontologoAGuardar.setApellido("Aguero");
        odontologoAGuardar.setMatricula("AB2356");

        Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoAGuardar);

        Long idEsperado = 2L;
        String nombreEsperado= "Luciano";

        assertEquals(idEsperado,odontologoGuardado.getId());
        assertEquals(nombreEsperado,odontologoGuardado.getNombre());
    }

    @Test
    @Order(4)
    public void buscarTodosLosOdontologosTest(){
        int cantidadEsperadaDeOdontologos = 2;
        int cantidadActualDeOdontologos= odontologoService.buscarTodosLosOdontologos().size();

        assertEquals(cantidadEsperadaDeOdontologos,cantidadActualDeOdontologos);
    }

    @Test
    @Order(5)
    public void actualizarOdontologoTest(){
        Odontologo odontologoAActualizar = new Odontologo();

        odontologoAActualizar.setId(1L);
        odontologoAActualizar.setNombre("Juan");
        odontologoAActualizar.setApellido("Gomez");
        odontologoAActualizar.setMatricula("AB1289");

        Odontologo odontologoActualizado = odontologoService.actualizarOdontolgo(odontologoAActualizar);

        String nombreEsperado = "Juan";
        String apellidoEsperado = "Gomez";

        assertEquals(nombreEsperado,odontologoActualizado.getNombre());
        assertEquals(apellidoEsperado,odontologoActualizado.getApellido());
    }

    @Test
    @Order(6)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.borrarOdontologo(1L);
        odontologoService.borrarOdontologo(2L);

        int cantEsperadaDeOdontologos = 0;
        int cantActualDeOdontologos = odontologoService.buscarTodosLosOdontologos().size();

        assertEquals(cantEsperadaDeOdontologos,cantActualDeOdontologos);
    }
}