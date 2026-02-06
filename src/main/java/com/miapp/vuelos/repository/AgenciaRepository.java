package com.miapp.vuelos.repository;

import com.miapp.vuelos.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repositorio para la entidad Agencia
@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
}