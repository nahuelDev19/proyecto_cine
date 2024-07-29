package com.personal.api.cine.ptoyecto_cine.models.request;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Representa una solicitud para crear o actualizar una película en el sistema.
 * Incluye detalles como título, descripción, duración y género de la película.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaRequest {

    /**
     * Título de la película.
     * No puede estar en blanco.
     */
    @NotBlank
    private String titulo;

    /**
     * Descripción de la película.
     * No puede estar en blanco.
     */
    @NotBlank
    private String descripcion;

    /**
     * Duración de la película en minutos.
     * Debe ser un valor entero mayor o igual a 60 minutos.
     * No puede ser nulo.
     */
    @Min(value = 60)
    @NotNull
    private Integer duracion;

    /**
     * Género de la película.
     * No puede ser nulo.
     */
    @NotNull
    private GeneroPelicula genero;
}
