package com.personal.api.cine.ptoyecto_cine.models.responses;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta que se envía al cliente con los detalles de una película.
 * Incluye información sobre el título, la descripción, la duración y el género de la película.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaResponse {

    /**
     * Título de la película.
     */
    private String titulo;

    /**
     * Descripción de la película.
     * Proporciona un resumen o detalles sobre la trama de la película.
     */
    private String descripcion;

    /**
     * Duración de la película en minutos.
     */
    private Integer duracion;

    /**
     * Género de la película.
     * Utiliza la enumeración {@link GeneroPelicula} para especificar el género de la película.
     */
    private GeneroPelicula genero;
}
