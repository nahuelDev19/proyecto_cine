package com.personal.api.cine.ptoyecto_cine.models.request;

import lombok.*;

/**
 * Representa una solicitud para crear o actualizar una reserva en el sistema.
 * Incluye detalles sobre el usuario, la función y la cantidad de entradas.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequest {

    /**
     * Información del usuario que realiza la reserva.
     * No debe ser nulo.
     */
    private UsuarioRequest usuario;

    /**
     * Información de la función para la cual se realiza la reserva.
     * No debe ser nula.
     */
    private FuncionRequest funcion;

    /**
     * Cantidad de entradas que se están reservando.
     * Debe ser un valor positivo.
     */
    private Integer cantidadDeEntradas;
}
