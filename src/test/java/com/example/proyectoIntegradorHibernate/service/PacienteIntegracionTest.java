package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Domicilio;
import com.example.proyectoIntegradorHibernate.domain.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteIntegracionTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

    private void cargadorDePaciente(){
        Domicilio domicilio= new Domicilio();
        domicilio.setNumero("23");
        domicilio.setCalle("Aguirre");
        domicilio.setLocalidad("Los Troncos");
        domicilio.setProvincia("Buenos Aires");

        Paciente paciente= new Paciente();
        paciente.setNombre("Federico");
        paciente.setApellido("Perez");
        paciente.setDni("39468545");
        paciente.setEmail("fedepe@gmail.com");
        paciente.setDomicilio(domicilio);
        paciente.setFechaDeIngreso(LocalDate.of(2020,5,6));

        pacienteService.crearPaciente(paciente);
    }

    @Test
    public void listarPacientesTest() throws Exception{
        cargadorDePaciente();

        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/todos").accept(
                MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}
