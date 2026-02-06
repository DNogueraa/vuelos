package com.miapp.vuelos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

// Esta clase representa la tabla "vuelo" en la base de datos
@Entity
public class Vuelo {

    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avion;
    private String matricula;
    private String destino;

    // Relación 1:N -> Un Vuelo tiene muchos Tickets
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    // Constructor vacío necesario para JPA
    public Vuelo() {}

    // Constructor con parámetros
    public Vuelo(String avion, String matricula, String destino) {
        this.avion = avion;
        this.matricula = matricula;
        this.destino = destino;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAvion() { return avion; }
    public void setAvion(String avion) { this.avion = avion; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}
