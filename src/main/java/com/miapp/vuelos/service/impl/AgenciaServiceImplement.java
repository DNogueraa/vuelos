package com.miapp.vuelos.service.impl;

import com.miapp.vuelos.model.Agencia;
import com.miapp.vuelos.repository.AgenciaRepository;
import com.miapp.vuelos.service.ServiceTables;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaServiceImplement implements ServiceTables<Agencia> {

    private final AgenciaRepository agenciaRepository;

    public AgenciaServiceImplement(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    @Override
    public List listarTodos() {
        return agenciaRepository.findAll();
    }

    @Override
    public Optional<Agencia> obtenerPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    @Override
    public Agencia guardar(Agencia entidad) {
        return agenciaRepository.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        agenciaRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Agencia entidad) {
        Agencia agencia = obtenerPorId(id).orElseThrow(() -> new RuntimeException("Agencia a modificar no encontrada"));

        if (entidad != null){
            agencia.setNombre(entidad.getNombre());
            agencia.setUbicacion(entidad.getUbicacion());

            agenciaRepository.save(agencia);
        }

    }
}
