package com.personal.api.cine.ptoyecto_cine.models.responses;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PeliculaResponse {

    private String titulo;
    private String descripcion;
    private Integer duracion;
    private GeneroPelicula genero;


}
