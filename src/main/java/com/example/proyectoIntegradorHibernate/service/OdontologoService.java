package com.example.proyectoIntegradorHibernate.service;

import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import com.example.proyectoIntegradorHibernate.exceptions.ResourceNotFoundException;
import com.example.proyectoIntegradorHibernate.repository.OdontologoRespository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger LOGGER= Logger.getLogger(OdontologoService.class);

    //Forma de hacerlo sin inyeccion de dependencias.
    //private OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();

    @Autowired
    private OdontologoRespository odontologoRespository;

    // LISTAR TODOS LOS ODONTOLOGOS
    public List<Odontologo> buscarTodosLosOdontologos(){
        LOGGER.info("Se buscaron todos los odontologos");
        return odontologoRespository.findAll();
    }

    // LISTAR ODONTOLOGO POR ID
    public Optional<Odontologo> buscarOdontologo(Long id){
        LOGGER.info("Se busco un odontologo por su ID");
        return odontologoRespository.findById(id);
    }

    // CREAR UN ODONTOLOGO
    public Odontologo crearOdontologo(Odontologo odontologo){
        LOGGER.info("Se guardo un Odontologo");
        return odontologoRespository.save(odontologo);
    }

    // BORRAR UN ODONTOLOGO POR ID
    public void borrarOdontologo(Long id) throws ResourceNotFoundException{
        LOGGER.info("Se inicio el proceso de borrado de un Odontologo");

        Optional<Odontologo> odontologoBuscado = odontologoRespository.findById(id);

        if (odontologoBuscado.isPresent()){
            odontologoRespository.deleteById(id);
        } else {
            LOGGER.error("Error al tratar de eliminar un odontologo");
            throw new ResourceNotFoundException("ERROR: No existe el Odontologo con id = " + id);
        }
    }

    // ACTUALIZAR UN ODONTOLOGO
    public Odontologo actualizarOdontolgo(Odontologo odontologo){
        LOGGER.info("Se inicio la actualizacion de un Odontologo");

        return odontologoRespository.save(odontologo);
    }
}
