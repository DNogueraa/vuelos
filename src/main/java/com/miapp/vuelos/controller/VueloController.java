package com.miapp.vuelos.controller;

import com.miapp.vuelos.model.Vuelo;

import com.miapp.vuelos.service.impl.VueloServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // Indica que esta clase es un controlador web
public class VueloController {

    private final VueloServiceImplement vueloService;

    // Constructor para inyectar el servicio de vuelo
    public VueloController(VueloServiceImplement vueloServiceImplement) {
        this.vueloService = vueloServiceImplement;
    }

    // Maneja la solicitud GET a /vuelos
    @GetMapping("/vuelos")
    public String listarVuelos(Model model) {
        // Agrega la lista de vuelos como atributo del modelo para pasarla a la vista
        model.addAttribute("vuelos", vueloService.listarTodos());

        // Retorna el avion de la vista (vuelos.html) que se encuentra en src/main/resources/templates
        return "vuelos";
    }

    // Maneja la solicitud POST al formulario de /vuelos/guardar
    @PostMapping("/vuelos/guardar")
    public String guardarVuelo(@RequestParam String avion, @RequestParam String matricula, @RequestParam String destino) {
        // Crea un nuevo objeto Vuelo con los datos del formulario y lo guarda en la base de datos
        Vuelo vuelo = new Vuelo(avion, matricula, destino);
        vueloService.guardar(vuelo);

        // Redirige a la página de lista de vuelos después de guardar
        return "redirect:/vuelos";
    }

    @GetMapping("/vuelos/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Vuelo vuelo = vueloService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));

        model.addAttribute("vuelo", vuelo);
        return "editar_vuelo";
    }

    @PostMapping("/vuelos/actualizar")
    public String actualizarVuelo(@RequestParam Long id,
                                  @RequestParam String avion,
                                  @RequestParam String matricula,
                                  @RequestParam String destino) {

        vueloService.actualizar(id, new Vuelo(avion, matricula, destino));
        return "redirect:/vuelos";
    }

    // Maneja la solicitud GET para eliminar un vuelo por ID
    @GetMapping("/vuelos/eliminar")
    public String eliminarVuelo(@RequestParam Long id) {
        // Llama al servicio para eliminar el vuelo por su ID
        vueloService.eliminar(id);

        // Redirige a la página de lista de vuelos después de eliminar
        return "redirect:/vuelos";
    }
}
