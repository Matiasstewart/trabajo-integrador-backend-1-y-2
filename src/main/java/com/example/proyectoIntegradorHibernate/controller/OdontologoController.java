package com.example.proyectoIntegradorHibernate.controller;

import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // LISTAR TODOS LOS ODONTOLOGOS
    @GetMapping
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoService.buscarTodosLosOdontologos();
    }

    // BUSCAR ODONTOLOGO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREAR UN ODONTOLOGO
    @PostMapping
    public Odontologo crearOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.crearOdontologo(odontologo);
    }

    // ACTUALIZAR UN ODONTOLOGO
    @PutMapping("/actualizar")
    public Odontologo actualizarOdontolgo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontolgo(odontologo);
    }

    /* @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable int id){
        odontologoService.borrarOdontologo(id);
    } */

    //De esta forma podemos trabajar devolviendo status codes de metodos http

    // ELIMINAR UN ODONTOLOGO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.borrarOdontologo(id);
        return ResponseEntity.ok("Se elimino correctamente el Odontologo con id = " + id);
    }
}
