package com.personal.api.cine.ptoyecto_cine.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta que se envía al cliente con los detalles de un usuario.
 * Incluye información básica del usuario como su identificador, nombre, apellido, edad y correo electrónico.
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UsuarioResponse {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellido;

    /**
     * Edad del usuario.
     * Debe ser un valor positivo.
     */
    private Integer edad;

    /**
     * Correo electrónico del usuario.
     * Debe ser una dirección de correo electrónico válida.
     */
    private String email;
}
