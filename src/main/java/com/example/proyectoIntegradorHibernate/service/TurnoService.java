package com.example.proyectoIntegradorHibernate.service;


import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.domain.Turno;
import com.example.proyectoIntegradorHibernate.dto.TurnoDTO;
import com.example.proyectoIntegradorHibernate.exceptions.BadRequestException;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER= Logger.getLogger(String.valueOf(TurnoService.class));

    //Forma de hacerlo sin inyeccion de dependencias.
    //private TurnoDAOList turnoIDao= new TurnoDAOList();

    //@Autowired
    //private TurnoDAOList turnoIDao;

    private TurnoRepository turnoRepository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    // GUARDAR UN TURNO
    public TurnoDTO guardarTurno(TurnoDTO turnoDTO) throws BadRequestException{
        LOGGER.info("Se inicio el guardado de un turno");
        //return convertirTurnoATurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turnoDTO)));

        Optional<Paciente> pacienteBuscado = pacienteService.buscarXId(turnoDTO.getId_paciente());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turnoDTO.getId_odontologo());

        Boolean seEncontroPaciente = pacienteBuscado.isPresent();
        Boolean seEncotroOdontologo = odontologoBuscado.isPresent();

        String nombreEncontradoPaciente = "";
        String nombreEncontradoOdontologo = "";

        if (seEncontroPaciente && seEncotroOdontologo){
            nombreEncontradoPaciente = pacienteBuscado.get().getNombre();
            nombreEncontradoOdontologo = odontologoBuscado.get().getNombre();
        }

        String nombreTurnoDTOPaciente = turnoDTO.getNombre_paciente();
        String nombreTurnoDTOOdontologo = turnoDTO.getNombre_odontologo();

        Boolean nombreOdontologoSonIguales = nombreEncontradoOdontologo.equals(nombreTurnoDTOOdontologo);
        Boolean nombrePacienteSonIguales = nombreEncontradoPaciente.equals(nombreTurnoDTOPaciente);


        if (seEncontroPaciente && seEncotroOdontologo && nombreOdontologoSonIguales && nombrePacienteSonIguales){
            return convertirTurnoATurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turnoDTO)));
        } else {
            throw new BadRequestException("ERROR: el odontologo y/o paciente no esta registrado");
        }
    }

    // ELIMINAR TURNO POR ID
    public void eliminarTurno(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio la eliminacion de un turno");

        Optional<Turno> turnoBuscado = turnoRepository.findById(id);

        if(turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);
        } else {
            LOGGER.error("Falla en la eliminacion de un turno");
            throw new ResourceNotFoundException("ERROR: no existe el Turno con el id = " +id );
        }
    }

    //public void eliminarTurnoOptional(int id){}

    // ACTUALIZAR UN TURNO
    public TurnoDTO actualizarTurno(TurnoDTO turno){
        LOGGER.info("Se inicio la actualizacion de un turno");
        return convertirTurnoATurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turno)));
    }

    // BUSCAR UN TURNO POR ID
    public Optional<TurnoDTO> buscarTurno(Long id){
        LOGGER.info("Se inicio la busqueda de un turno por id");
        Optional<Turno> turnoBuscado= turnoRepository.findById(id);

        if (turnoBuscado.isPresent()){
            return Optional.of(convertirTurnoATurnoDTO(turnoBuscado.get()));
        } else {
            return Optional.empty();
        }
    }

    // BUSCAR TODOS LOS TURNOS
    public List<TurnoDTO> buscarTodosLosTurnos(){
        LOGGER.info("Se inicio la busqueda de todos los turnos");
        return convertirTurnosATurnosDTO(turnoRepository.findAll());
    }

    private Turno convertirTurnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        //Seteamos los objetos, se podria hacer con constructor
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getId_paciente());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getId_odontologo());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());

        //Vincular los objetos
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        return turno;
    }

    private TurnoDTO convertirTurnoATurnoDTO(Turno turno){ //turno es una entidad completa
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(turno.getId());
        turnoDTO.setId_odontologo(turno.getOdontologo().getId());
        turnoDTO.setId_paciente(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());

        return turnoDTO;
    }

    private List<TurnoDTO> convertirTurnosATurnosDTO(List<Turno> turnos){
        List<TurnoDTO> turnosDTOS = new ArrayList<>();

        for ( Turno t : turnos){
            TurnoDTO turnoConvertido = convertirTurnoATurnoDTO(t);
            turnosDTOS.add(turnoConvertido);
        }

        return turnosDTOS;
    }
}
