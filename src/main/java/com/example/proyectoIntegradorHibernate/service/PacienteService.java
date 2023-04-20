package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger LOGGER= Logger.getLogger(String.valueOf(PacienteService.class));

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // BUSCAR TODOS LOS PACIENTES
    public List<Paciente> buscarTodosLosPacientes(){
        LOGGER.info("Se inicio la busqueda de todos los pacientes");
        return pacienteRepository.findAll();
    }

    // BUSCAR UN PACIENTE POR ID
    public Optional<Paciente> buscarXId(Long id){
        LOGGER.info("Se inicio la busqueda de un paciente por id");
        return pacienteRepository.findById(id);
    }

    // BUSCAR UN PACIENTE POR EL MAIL
    public Optional<Paciente> buscarXEmail(String email){
        LOGGER.info("Se inicio la busqueda de un paciente por email");
        return pacienteRepository.findByEmail(email);
    }

    // CREAR UN PACIENTE
    public Paciente crearPaciente(Paciente paciente){
        LOGGER.info("Se creo un paciente");
        return pacienteRepository.save(paciente);
    }

    // ACTUALIZAR UN PACIENTE
    public Paciente actualizarPaciente(Paciente paciente){
        LOGGER.info("Se actualizo un paciente");
        // Forma 1
        // pacienteRepository.deleteById(paciente.getId());
        // pacienteRepository.save(paciente);
        // return paciente;

        //Forma 2
        return pacienteRepository.save(paciente);
    }

    // ELIMINAR UN PACIENTE POR SU ID
    /*
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    */
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio la eliminacion de un paciente");
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);

        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.error("No se pudo eliminar un paciente");
            throw new ResourceNotFoundException("ERROR: No existe el Paciente con id = " + id);
        }
    }
}
