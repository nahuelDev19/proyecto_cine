package com.personal.api.cine.ptoyecto_cine.models.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @Builder @NoArgsConstructor
public class UsuarioRequest {

    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String password;

}
