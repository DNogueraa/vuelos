package com.miapp.vuelos.repository;

import com.miapp.vuelos.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repositorio para la entidad Oferta
@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
}