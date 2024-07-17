package com.personal.api.cine.ptoyecto_cine.models.responses;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @Builder @NoArgsConstructor
public class ReservasResponse {

    private Long id;
    private UsuarioResponse usuario;
    private FuncionesResponse funcion;
    private Integer cantidadDeEntradas;
    private Integer total;  
}
