package com.personal.api.cine.ptoyecto_cine.models.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @Builder @NoArgsConstructor
public class UsuarioRequest {

    
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    @Min(value = 18)
    private Integer edad;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    

}
