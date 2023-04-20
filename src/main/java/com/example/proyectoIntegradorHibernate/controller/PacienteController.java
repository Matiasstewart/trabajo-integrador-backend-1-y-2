package com.example.proyectoIntegradorHibernate.controller;

import com.example.proyectoIntegradorHibernate.domain.Paciente;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Opcion B de inyeccion de dependencias
    // @Autowired
    // private PacienteService pacienteService;

    // LISTAR PACIENTE POR EMAIL
    @GetMapping("/email")
    public Optional<Paciente> buscarPacienteXEmail(@RequestParam("email") String email){
        Optional<Paciente> paciente= pacienteService.buscarXEmail(email);
        return paciente;
    }

    // LISTAR TODOS LOS PACIENTES
    //Utilizamos el siguiente metodo si queremos traer todos los registros de pacientes.
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());
    }

    // LISTAR PACIENTE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacienteXId(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXId(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GUARDAR UN PACIENTE
    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente){
        return pacienteService.crearPaciente(paciente);
    }

    // ACTUALIZAR UN PACIENTE
    @PutMapping("/actualizar")
    public void actualizarPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizarPaciente(paciente);
    }

    // BORRAR UN PACIENTE POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacientePorID(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Eliminacion del paciente con id " + id + " con exito");
    }

}
