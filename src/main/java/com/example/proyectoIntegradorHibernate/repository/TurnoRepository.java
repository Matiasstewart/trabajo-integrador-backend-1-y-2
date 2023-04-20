package com.example.proyectoIntegradorHibernate.repository;

import com.example.proyectoIntegradorHibernate.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
