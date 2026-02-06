package com.miapp.vuelos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Esta clase representa la tabla "trabajo" en la base de datos
@Entity
public class Ticket {

    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "VALOR")
    private double precio;
    private Date fecha;
    private Time hora;

    // Relación N:1 -> Muchos Tickets pertenecen a un Usuario
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private com.miapp.vuelos.model.Usuario usuario;

    // Relación N:1 -> Muchos Tickets están relacionado a un Vuelos
    @ManyToOne
    @JoinColumn(name = "ID_VUELO", nullable = false)
    private com.miapp.vuelos.model.Vuelo vuelo;

//    @ManyToMany(mappedBy = "tickets")
//    private Set<Agencia> agencias = new HashSet<>();


    // Constructor vacío necesario para JPA
    public Ticket() {}

    // Constructor con parámetros
    public Ticket(double precio, Date fecha, Time hora, com.miapp.vuelos.model.Usuario usuario, com.miapp.vuelos.model.Vuelo vuelo) {
        this.precio = precio;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
        this.vuelo = vuelo;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public com.miapp.vuelos.model.Usuario getUsuario() { return usuario; }
    public void setUsuario(com.miapp.vuelos.model.Usuario usuario) { this.usuario = usuario; }

    public com.miapp.vuelos.model.Vuelo getVuelo() { return vuelo; }
    public void setVuelo(com.miapp.vuelos.model.Vuelo vuelo) { this.vuelo = vuelo; }

//    public Set<Agencia> getAgencias() { return agencias; }

}
