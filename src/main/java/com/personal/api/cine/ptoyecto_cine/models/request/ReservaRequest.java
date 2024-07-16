package com.personal.api.cine.ptoyecto_cine.models.request;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReservaRequest {
    private Long usuarioId;
    private Long funcionId;
    private Integer cantidadDeEntradas;
}
