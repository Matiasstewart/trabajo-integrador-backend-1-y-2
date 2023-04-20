package com.example.proyectoIntegradorHibernate.controller;


import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.domain.Turno;
import com.example.proyectoIntegradorHibernate.dto.TurnoDTO;
import com.example.proyectoIntegradorHibernate.exceptions.BadRequestException;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.service.OdontologoService;
import com.example.proyectoIntegradorHibernate.service.PacienteService;
import com.example.proyectoIntegradorHibernate.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        ResponseEntity<List<TurnoDTO>> response;

        if(turnoService.buscarTodosLosTurnos()==null){
            response= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            response= ResponseEntity.ok(turnoService.buscarTodosLosTurnos());
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        TurnoDTO turnoGuardado = turnoService.guardarTurno(turno);
        return ResponseEntity.ok(turnoGuardado);
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        ResponseEntity response;

        if(turnoService.buscarTurno(id)==null){
            response= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            turnoService.eliminarTurno(id);
            response= new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException{
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se elimino correctamente el Turno con el id = " + id);
    }

    @PutMapping("/actualizar")
    public ResponseEntity actualizarTurno(@RequestBody TurnoDTO turno){
        ResponseEntity<TurnoDTO> response;

        if (turnoService.buscarTurno(turno.getId())==null){
            response= new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            turnoService.actualizarTurno(turno);
            response= new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }

    //2da opcion del buscar (con optional)
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoOptional(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            //encontrado
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            //no lo encotro
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
