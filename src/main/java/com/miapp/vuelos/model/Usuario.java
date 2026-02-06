package com.miapp.vuelos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

// Esta clase representa la tabla "trabajo" en la base de datos
@Entity
public class Usuario {

    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    @Pattern(
            regexp = "^[\\p{L}0-9 .,'-]+$",
            message = "El nombre contiene caracteres no permitidos"
    )
    private String nombre;
    @Column(name = "APELLIDO")
    @NotBlank(message = "El apellido es obligatoria")
    @Size(min = 6, max = 30, message = "El apellido debe tener entre 6 y 30 caracteres")
    @Pattern(
            regexp = "^[\\p{L}0-9 .,'-]+$",
            message = "La ubicación contiene caracteres no permitidos"
    )
    private String apellido;

    // Relación N:1 -> Muchos Agencias contratan a una empresa
    @ManyToOne
    @JoinColumn(name = "ID_AGENCIA", nullable = false)
    private com.miapp.vuelos.model.Agencia agencia;

    // Relación 1:N -> Un Usuario tiene muchos Ticket
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    // Constructor vacío necesario para JPA
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombre, String apellido, com.miapp.vuelos.model.Agencia agencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.agencia = agencia;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public com.miapp.vuelos.model.Agencia getAgencia() { return agencia; }
    public void setAgencia(com.miapp.vuelos.model.Agencia agencia) { this.agencia = agencia; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}
