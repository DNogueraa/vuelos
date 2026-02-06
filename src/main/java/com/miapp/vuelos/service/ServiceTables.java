package com.miapp.vuelos.service;

import java.util.List;
import java.util.Optional;

public interface ServiceTables<T> {

    List<T> listarTodos();
    Optional<T> obtenerPorId(Long id);
    T guardar(T entidad);
    void eliminar(Long id);
    void actualizar(Long id, T entidad);
}


