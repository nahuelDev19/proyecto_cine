package com.personal.api.cine.ptoyecto_cine.models.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;
import com.personal.api.cine.ptoyecto_cine.uitils.validaciones.SalaDeCineDeserializer;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * Representa una solicitud para crear o actualizar una función en el sistema.
 * Incluye detalles como fecha, hora, sala y precio de la función.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionRequest {

    /**
     * Identificador único de la función (opcional para solicitudes de creación).
     */
    private Long id;

    /**
     * Fecha en la que se realizará la función.
     * Debe ser la fecha actual o una fecha futura.
     */
    @FutureOrPresent
    private LocalDate fecha;

    /**
     * Hora a la que se realizará la función.
     * No puede ser nula.
     */
    @NotNull
    private LocalTime hora;

    /**
     * Sala en la que se llevará a cabo la función.
     * Utiliza un deserializador personalizado para convertir de JSON a la enumeración `SalaDeCine`.
     */
    @JsonDeserialize(using = SalaDeCineDeserializer.class)
    private SalaDeCine sala;

    /**
     * Precio de la función.
     * Debe ser un valor positivo y no puede ser nulo.
     */
    @NotNull
    @Positive
    private Integer precio;

    /**
     * Película asociada a la función.
     * Incluye detalles básicos sobre la película.
     */
    private PeliculaRequest pelicula;
}
