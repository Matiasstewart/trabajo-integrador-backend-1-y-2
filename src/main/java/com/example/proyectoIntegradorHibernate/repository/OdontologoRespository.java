package com.example.proyectoIntegradorHibernate.repository;

import com.example.proyectoIntegradorHibernate.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRespository extends JpaRepository<Odontologo,Long> {
}
