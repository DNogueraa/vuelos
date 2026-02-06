package com.miapp.vuelos;

import com.miapp.vuelos.model.Agencia;
import com.miapp.vuelos.model.Oferta;
import com.miapp.vuelos.model.Ticket;
import com.miapp.vuelos.model.Usuario;
import com.miapp.vuelos.model.Vuelo;
import com.miapp.vuelos.repository.AgenciaRepository;
import com.miapp.vuelos.repository.OfertaRepository;
import com.miapp.vuelos.repository.TicketRepository;
import com.miapp.vuelos.repository.UsuarioRepository;
import com.miapp.vuelos.repository.VueloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuelosApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner datosPrueba(
//			AgenciaRepository agenciaRepo,
//			UsuarioRepository usuarioRepo,
//			VueloRepository vueloRepo,
//			TicketRepository ticketRepo,
//			OfertaRepository ofertaRepo
//	) {
//		return args -> {
//
//			// 1) Crear Agencia
//			Agencia a1 = agenciaRepo.save(new Agencia("SkyTravel", "Madrid"));
//
//			// 2) Crear Usuario (asociado a Agencia)
//			Usuario u1 = usuarioRepo.save(new Usuario("Ana", "Gomez", a1));
//
//			// 3) Crear Vuelo
//			Vuelo v1 = vueloRepo.save(new Vuelo("Airbus A320", "EC-1234", "Roma"));
//
//			// 4) Crear Ticket (asociado a Usuario y Vuelo)
//			java.util.Date fecha = new java.util.Date();
//			java.sql.Time hora = new java.sql.Time(System.currentTimeMillis());
//
//			Ticket t1 = ticketRepo.save(new Ticket(120.21, fecha, hora, u1, v1));
//
//			// 5) Crear Oferta (tabla intermedia con precio propio)
//			Oferta o1 = new Oferta(99.99, a1, t1);
//			ofertaRepo.save(o1);
//
//			System.out.println("OK: Oferta creada y vinculada a Agencia + Ticket.");
//		};
//	}



}



