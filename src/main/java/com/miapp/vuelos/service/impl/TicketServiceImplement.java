package com.miapp.vuelos.service.impl;

import com.miapp.vuelos.model.Ticket;
import com.miapp.vuelos.repository.TicketRepository;
import com.miapp.vuelos.service.ServiceTables;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplement implements ServiceTables<Ticket> {

    private final TicketRepository ticketRepository;

    public TicketServiceImplement(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> obtenerPorId(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket guardar(Ticket entidad) {
        return ticketRepository.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Ticket entidad) {
        Ticket ticketViejo = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket no encontrado"));
        try {
        if (entidad != null){
            ticketViejo.setFecha(entidad.getFecha());
            ticketViejo.setHora(entidad.getHora());
            ticketViejo.setPrecio(entidad.getPrecio());
            ticketViejo.setUsuario(entidad.getUsuario());
            ticketViejo.setVuelo(entidad.getVuelo());

            ticketRepository.save(ticketViejo);
            }
        }catch (RuntimeException e){
            throw new RuntimeException("Error al actualizar registro");
        }
    }


}
