package com.personal.api.cine.ptoyecto_cine.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta que se envía al cliente con los detalles de una reserva.
 * Incluye información sobre el identificador de la reserva, el usuario que realizó la reserva,
 * la función para la cual se hizo la reserva, la cantidad de entradas y el total a pagar.
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ReservasResponse {

    /**
     * Identificador único de la reserva.
     */
    private Long id;

    /**
     * Información del usuario que realizó la reserva.
     * Utiliza la clase {@link UsuarioResponse} para representar los detalles del usuario.
     */
    private UsuarioResponse usuario;

    /**
     * Información sobre la función para la cual se realizó la reserva.
     * Utiliza la clase {@link FuncionesResponse} para representar los detalles de la función.
     */
    private FuncionesResponse funcion;

    /**
     * Cantidad de entradas reservadas.
     */
    private Integer cantidadDeEntradas;

    /**
     * Total a pagar por la reserva.
     * Calculado en función de la cantidad de entradas y el precio de la función.
     */
    private Integer total;  
}
