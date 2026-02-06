package com.miapp.vuelos.service.impl;

import com.miapp.vuelos.model.Agencia;
import com.miapp.vuelos.model.Usuario;
import com.miapp.vuelos.repository.AgenciaRepository;
import com.miapp.vuelos.repository.UsuarioRepository;
import com.miapp.vuelos.service.ServiceTables;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements ServiceTables<Usuario> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplement(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario entidad) {
        return usuarioRepository.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Usuario usuario) {
        Usuario usuarioViejo = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));;

        if (usuario != null) {
            usuarioViejo.setNombre(usuario.getNombre());
            usuarioViejo.setApellido(usuario.getApellido());
            usuarioViejo.setAgencia(usuario.getAgencia());

            usuarioRepository.save(usuarioViejo); // ← UPDATE
        }
    }
}
