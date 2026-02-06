package com.miapp.vuelos.controller;

import com.miapp.vuelos.model.Agencia;

import com.miapp.vuelos.service.impl.AgenciaServiceImplement;
import com.miapp.vuelos.service.impl.UsuarioServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller  // Indica que esta clase es un controlador web
public class AgenciaController {

    private final AgenciaServiceImplement agenciaService;
    private final UsuarioServiceImplement usuarioService;

    // Constructor para inyectar el servicio de agencia
    public AgenciaController(AgenciaServiceImplement agenciaServiceImplement, UsuarioServiceImplement usuarioService) {
        this.agenciaService = agenciaServiceImplement;
        this.usuarioService = usuarioService;
    }

    // Maneja la solicitud GET a /agencias
    @GetMapping("/agencias")
    public String listarAgencias(Model model) {
        // Agrega la lista de agencias como atributo del modelo para pasarla a la vista
        model.addAttribute("agencias", agenciaService.listarTodos());

        // Retorna el nombre de la vista (agencias.html) que se encuentra en src/main/resources/templates
        return "agencias";
    }

    // Maneja la solicitud POST al formulario de /agencias/guardar
    @PostMapping("/agencias/guardar")
    public String guardar(@Valid Agencia agencia, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("agencias", agenciaService.listarTodos());
            model.addAttribute("agencia", agencia); // para repintar inputs
            return "agencias";
        }

        agenciaService.guardar(agencia);
        return "redirect:/agencias";
    }

    @GetMapping("/agencias/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {

        Agencia agencia = agenciaService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agencia no encontrada"));

        // Pasamos la agencia a la vista (para rellenar el form)
        model.addAttribute("agencia", agencia);

        // Pasamos sus usuarios asociados para poder listarlos debajo
        // (si es null, la vista lo manejará)
        model.addAttribute("usuarios", agencia.getUsuarios());

        return "editar_agencia";
    }

    @PostMapping("/agencias/actualizar")
    public String actualizarAgencia(@RequestParam Long id, @RequestParam String nombre, @RequestParam String ubicacion) {

        // No tocamos usuarios aquí: se gestionan por separado (eliminación desde la tabla)
        agenciaService.actualizar(id, new Agencia(nombre, ubicacion));

        return "redirect:/agencias";
    }


    // Maneja la solicitud GET para eliminar un agencia por ID
    @GetMapping("/agencias/eliminar")
    public String eliminarAgencia(@RequestParam Long id) {
        // Llama al servicio para eliminar el agencia por su ID
        agenciaService.eliminar(id);

        // Redirige a la página de lista de agencias después de eliminar
        return "redirect:/agencias";
    }

    @GetMapping("/agencias/usuarios/eliminar")
    public String eliminarUsuarioDesdeAgencia(@RequestParam Long usuarioId, @RequestParam Long agenciaId) {

        // Borra el usuario completo (no puede existir sin agencia)
        usuarioService.eliminar(usuarioId);

        // Volvemos a la pantalla de editar de esa agencia
        return "redirect:/agencias/editar?id=" + agenciaId;
    }
}
