package com.personal.api.cine.ptoyecto_cine.models.responses;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @Builder @NoArgsConstructor
public class ReservasResponse {

    private FuncionEntity funcion;
    private Integer cantidadDeEntradas;
}
