package com.personal.api.cine.ptoyecto_cine.entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una función en el cine.
 * Una función incluye una fecha, hora, sala, precio, reservas asociadas y la película que se proyecta.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funciones") 
public class FuncionEntity {

    /**
     * Identificador único de la función.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha en la que se lleva a cabo la función.
     */
    private LocalDate fecha;

    /**
     * Hora en la que se lleva a cabo la función.
     */
    private LocalTime hora;

    /**
     * Sala de cine en la que se lleva a cabo la función.
     */
    private SalaDeCine sala;

    /**
     * Precio de la entrada para la función.
     */
    private Integer precio;

    /**
     * Conjunto de reservas asociadas a la función.
     */
    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservaEntity> reserva;

    /**
     * Película que se proyecta en la función.
     * Muchas funciones pertenecen a una película.
     */
    @ManyToOne
    private PeliculaEntity pelicula;
}
