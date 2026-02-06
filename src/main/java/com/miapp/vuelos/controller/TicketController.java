package com.miapp.vuelos.controller;

import com.miapp.vuelos.model.Ticket;
import com.miapp.vuelos.model.Usuario;
import com.miapp.vuelos.model.Vuelo;
import com.miapp.vuelos.service.impl.TicketServiceImplement;
import com.miapp.vuelos.service.impl.UsuarioServiceImplement;
import com.miapp.vuelos.service.impl.VueloServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

@Controller
public class TicketController {

    private final TicketServiceImplement ticketService;
    private final UsuarioServiceImplement usuarioService;
    private final VueloServiceImplement vueloService;

    public TicketController(TicketServiceImplement ticketService,
                            UsuarioServiceImplement usuarioService,
                            VueloServiceImplement vueloService) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
        this.vueloService = vueloService;
    }

    // Maneja la solicitud GET a /tickets
    @GetMapping("/tickets")
    public String listarTickets(Model model) {
        model.addAttribute("tickets", ticketService.listarTodos());

        // Pasamos usuarios y vuelos a la vista para poder seleccionarlos en el formulario
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("vuelos", vueloService.listarTodos());

        return "tickets";
    }

    // Maneja la solicitud POST al formulario de /tickets/guardar
    @PostMapping("/tickets/guardar")
    public String guardarTicket(@RequestParam double precio,
                                @RequestParam String fecha,      // formato esperado: yyyy-MM-dd
                                @RequestParam String hora,       // formato esperado: HH:mm (HTML)
                                @RequestParam Long usuarioId,
                                @RequestParam Long vueloId) {

        Optional<Usuario> usuarioOptional = usuarioService.obtenerPorId(usuarioId);
        Optional<Vuelo> vueloOptional = vueloService.obtenerPorId(vueloId);

        if (usuarioOptional.isPresent() && vueloOptional.isPresent()) {

            // Date (java.util.Date) desde un String yyyy-MM-dd
            Date fechaDate = java.sql.Date.valueOf(fecha);

            // Time (java.sql.Time) desde un String HH:mm (lo convertimos a HH:mm:ss)
            String horaCompleta = hora.length() == 5 ? (hora + ":00") : hora;
            Time horaTime = Time.valueOf(horaCompleta);

            Ticket ticket = new Ticket(precio, fechaDate, horaTime, usuarioOptional.get(), vueloOptional.get());
            ticketService.guardar(ticket);
        }

        return "redirect:/tickets";
    }

    @GetMapping("/tickets/editar")
    public String editarTickets(@RequestParam long id, Model model){
        Ticket ticket = ticketService.obtenerPorId(id).orElseThrow(() -> new IllegalArgumentException("Ticket no encontrado"));

        model.addAttribute("ticket", ticket);
        model.addAttribute("vuelos", vueloService.listarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());

        return "editar_ticket";
    }

    @PostMapping("/tickets/actualizar")
    public String actualizarTickets(@RequestParam long id,
                                    @RequestParam double precio,
                                    @RequestParam String fecha,
                                    @RequestParam String hora,
                                    @RequestParam Long idVuelo,
                                    @RequestParam Long idUsuario){

        Vuelo vuelo = vueloService.obtenerPorId(idVuelo).orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));
        Usuario usuario = usuarioService.obtenerPorId(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        try {
            ticketService.actualizar(id, new Ticket(precio, java.sql.Date.valueOf(fecha),Time.valueOf(hora), usuario, vuelo ));
        }catch (RuntimeException e){
            System.err.println("Error al registrar cambio");
        }

        return "redirect:/tickets";
    }



    // Maneja la solicitud GET para eliminar un ticket por ID
    @GetMapping("/tickets/eliminar")
    public String eliminarTicket(@RequestParam Long id) {
        ticketService.eliminar(id);
        return "redirect:/tickets";
    }
}
