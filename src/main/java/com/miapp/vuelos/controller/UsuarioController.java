package com.miapp.vuelos.controller;


import com.miapp.vuelos.model.Agencia;
import com.miapp.vuelos.model.Usuario;
import com.miapp.vuelos.service.impl.AgenciaServiceImplement;
import com.miapp.vuelos.service.impl.UsuarioServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final UsuarioServiceImplement usuarioService;
    private final AgenciaServiceImplement agenciaService;

    public UsuarioController(UsuarioServiceImplement usuarioService, AgenciaServiceImplement agenciaService) {
        this.usuarioService = usuarioService;
        this.agenciaService = agenciaService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("agencias", agenciaService.listarTodos());  // Pasamos la lista de agencias a la vista
        return "usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long agenciaId) {
        Optional<Agencia> agenciaOptional = agenciaService.obtenerPorId(agenciaId);  // Buscamos el agencia por ID

        if (agenciaOptional.isPresent()) {
            Usuario usuario = new Usuario(nombre, apellido, agenciaOptional.get());  // Pasamos el agencia como tercer argumento
            usuarioService.guardar(usuario);
        }
        return "redirect:/usuarios";  // Redirige a la lista de usuarios
    }

    @GetMapping("/usuarios/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Usuario usuario = usuarioService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);
        model.addAttribute("agencias", agenciaService.listarTodos()   );

        return "editar_usuario";
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long agenciaId) {
        Agencia agencia = agenciaService.obtenerPorId(agenciaId).orElseThrow(() -> new IllegalArgumentException("Agencia no existe"));

        usuarioService.actualizar(id, new Usuario(nombre, apellido, agencia));
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}
