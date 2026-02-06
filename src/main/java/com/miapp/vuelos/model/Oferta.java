package com.miapp.vuelos.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double precio;

    @ManyToOne
    @JoinColumn(name = "ID_AGENCIA", nullable = false)
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "ID_TICKET", nullable = false)
    private Ticket ticket;

    public Oferta() {}

    public Oferta(double precio, com.miapp.vuelos.model.Agencia agencia, com.miapp.vuelos.model.Ticket ticket) {
        this.precio = precio;
        this.agencia = agencia;
        this.ticket = ticket;
    }

    public Long getId() { return id; }
    public com.miapp.vuelos.model.Agencia getAgencia() { return agencia; }
    public com.miapp.vuelos.model.Ticket getTicket() { return ticket; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
