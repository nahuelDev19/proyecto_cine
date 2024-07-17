package com.personal.api.cine.ptoyecto_cine.models.request;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReservaRequest {
    
    
    private UsuarioRequest usuario;
    private FuncionRequest funcion;
    private Integer cantidadDeEntradas;
}
