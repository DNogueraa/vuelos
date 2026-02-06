package com.miapp.vuelos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Esta clase representa la tabla "agencia" en la base de datos
@Entity
public class Agencia {

    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @Column(name = "NOMBRE", nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
    @Pattern(
            regexp = "^[\\p{L}0-9 .,'-]+$",
            message = "El nombre contiene caracteres no permitidos"
    )
    private String nombre;


    @Column(name = "UBICACION", nullable = false)
    @NotBlank(message = "La ubicación es obligatoria")
    @Size(min = 6, max = 30, message = "La ubicación debe tener entre 2 y 30 caracteres")
    @Pattern(
            regexp = "^[\\p{L}0-9 .,'-]+$",
            message = "La ubicación contiene caracteres no permitidos"
    )
    private String ubicacion;

    // Relación 1:N -> Un Agencia tiene muchos Usuarios
    @OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

//    @ManyToMany
//    @JoinTable(
//            name = "AGENCIA_TICKET",
//            joinColumns = @JoinColumn(name = "ID_AGENCIA"),
//            inverseJoinColumns = @JoinColumn(name = "ID_TICKET")
//    )
//    private Set<Ticket> tickets = new HashSet<>();

    // Constructor vacío necesario para JPA
    public Agencia() {}

    // Constructor con parámetros
    public Agencia(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

//    public Set<Ticket> getTickets() { return tickets; }
}
