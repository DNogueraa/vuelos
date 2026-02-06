package com.miapp.vuelos.controller;

import com.miapp.vuelos.model.Agencia;
import com.miapp.vuelos.model.Oferta;
import com.miapp.vuelos.model.Ticket;
import com.miapp.vuelos.service.impl.AgenciaServiceImplement;
import com.miapp.vuelos.service.impl.OfertaServiceImplement;
import com.miapp.vuelos.service.impl.TicketServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OfertaController {

    private final OfertaServiceImplement ofertaService;
    private final AgenciaServiceImplement agenciaService;
    private final TicketServiceImplement ticketService;

    public OfertaController(OfertaServiceImplement ofertaService, AgenciaServiceImplement agenciaService, TicketServiceImplement ticketService) {
        this.ofertaService = ofertaService;
        this.agenciaService = agenciaService;
        this.ticketService = ticketService;
    }

    // Maneja la solicitud GET a /ofertas
    @GetMapping("/ofertas")
    public String listarOfertas(Model model) {
        model.addAttribute("ofertas", ofertaService.listarTodos());

        // Pasamos agencias y tickets a la vista para poder seleccionarlos en el formulario
        model.addAttribute("agencias", agenciaService.listarTodos());
        model.addAttribute("tickets", ticketService.listarTodos());

        return "ofertas";
    }

    // Maneja la solicitud POST al formulario de /ofertas/guardar
    @PostMapping("/ofertas/guardar")
    public String guardarOferta(@RequestParam double precio,
                                @RequestParam Long agenciaId,
                                @RequestParam Long ticketId) {

        Optional<Agencia> agenciaOptional = agenciaService.obtenerPorId(agenciaId);
        Optional<Ticket> ticketOptional = ticketService.obtenerPorId(ticketId);

        if (agenciaOptional.isPresent() && ticketOptional.isPresent()) {
            Oferta oferta = new Oferta(precio, agenciaOptional.get(), ticketOptional.get());
            ofertaService.guardar(oferta);
        }

        return "redirect:/ofertas";
    }

    // Maneja la solicitud GET para eliminar una oferta por ID
    @GetMapping("/ofertas/eliminar")
    public String eliminarOferta(@RequestParam Long id) {
        ofertaService.eliminar(id);
        return "redirect:/ofertas";
    }
}
