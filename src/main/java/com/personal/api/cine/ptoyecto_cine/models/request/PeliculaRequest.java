package com.personal.api.cine.ptoyecto_cine.models.request;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor

public class PeliculaRequest {

    private String titulo;
    private String descripcion;
    private Integer duracion;
    private GeneroPelicula genero;

}
