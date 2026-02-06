package com.miapp.vuelos.repository;

import com.miapp.vuelos.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repositorio para la entidad Ticket
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}