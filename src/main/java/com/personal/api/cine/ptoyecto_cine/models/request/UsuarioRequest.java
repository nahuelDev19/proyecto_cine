package com.personal.api.cine.ptoyecto_cine.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una solicitud para crear o actualizar un usuario en el sistema.
 * Incluye detalles personales del usuario como nombre, apellido, edad, correo electrónico y contraseña.
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UsuarioRequest {

    /**
     * Nombre del usuario.
     * No debe estar vacío o ser nulo.
     */
    @NotBlank
    private String nombre;

    /**
     * Apellido del usuario.
     * No debe estar vacío o ser nulo.
     */
    @NotBlank
    private String apellido;

    /**
     * Edad del usuario.
     * Debe ser un valor no nulo y mayor o igual a 18.
     */
    @NotNull
    @Min(value = 18)
    private Integer edad;

    /**
     * Correo electrónico del usuario.
     * Debe ser una dirección de correo electrónico válida y no debe estar vacío.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * Contraseña del usuario.
     * No debe estar vacía o ser nula.
     */
    @NotBlank
    private String password;
}
