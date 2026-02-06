package com.miapp.vuelos.repository;

import com.miapp.vuelos.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repositorio para la entidad Vuelo
@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}