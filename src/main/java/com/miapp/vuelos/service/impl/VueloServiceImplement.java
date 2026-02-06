package com.miapp.vuelos.service.impl;

import com.miapp.vuelos.model.Vuelo;
import com.miapp.vuelos.repository.VueloRepository;
import com.miapp.vuelos.service.ServiceTables;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImplement implements ServiceTables<Vuelo> {

    private final VueloRepository vueloRepository;

    public VueloServiceImplement(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<Vuelo> listarTodos() {
        return vueloRepository.findAll();
    }

    @Override
    public Optional<Vuelo> obtenerPorId(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public Vuelo guardar(Vuelo entidad) {
        return vueloRepository.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        vueloRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Vuelo entidad) {
        Vuelo vueloViejo = vueloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no existe"));

        if (entidad != null) {
            vueloViejo.setAvion(entidad.getAvion());
            vueloViejo.setMatricula(entidad.getMatricula());
            vueloViejo.setDestino(entidad.getDestino());

            vueloRepository.save(vueloViejo); // ← UPDATE real
        }
    }

}
