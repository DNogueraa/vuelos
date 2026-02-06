package com.miapp.vuelos.repository;

import com.miapp.vuelos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repositorio para la entidad Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}