package com.personal.api.cine.ptoyecto_cine.models.responses;

import java.time.LocalDate;
import java.time.LocalTime;

import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta con los detalles de una función de cine.
 * Incluye información sobre la fecha, hora, sala, precio y película asociada.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionesResponse {

    /**
     * Fecha en la que se realiza la función.
     */
    private LocalDate fecha;

    /**
     * Hora en la que se realiza la función.
     */
    private LocalTime hora;

    /**
     * Sala de cine en la que se lleva a cabo la función.
     */
    private SalaDeCine sala;

    /**
     * Precio de las entradas para la función.
     */
    private Integer precio;

    /**
     * Detalles de la película asociada a la función.
     */
    private PeliculaResponse pelicula;
}
