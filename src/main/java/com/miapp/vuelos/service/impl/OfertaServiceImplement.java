package com.miapp.vuelos.service.impl;

import com.miapp.vuelos.model.Oferta;
import com.miapp.vuelos.repository.OfertaRepository;
import com.miapp.vuelos.service.ServiceTables;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaServiceImplement implements ServiceTables<Oferta> {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImplement(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public List<Oferta> listarTodos() {
        return ofertaRepository.findAll();
    }

    @Override
    public Optional<Oferta> obtenerPorId(Long id) {
        return ofertaRepository.findById(id);
    }

    @Override
    public Oferta guardar(Oferta entidad) {
        return ofertaRepository.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        ofertaRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Oferta entidad) {

    }
}
