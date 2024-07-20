package com.personal.api.cine.ptoyecto_cine.models.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @Builder @NoArgsConstructor
public class UsuarioRequest {

    
    @NotBlank(message = "nombre no puede ser nulo o vacio")
    private String nombre;
    @NotBlank(message = "nombre no puede ser nulo o vacio")
    private String apellido;
    @NotBlank(message = "edad no puede ser nulo o vacio")
    private Integer edad;
    @NotBlank(message = "email no puede ser nulo o vacio")
    private String email;
    @NotBlank(message = "password no puede ser nulo o vacio")
    private String password;
    

}
