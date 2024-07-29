package com.personal.api.cine.ptoyecto_cine.models.request;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor

public class PeliculaRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @Min(value = 60)
    @NotNull
    private Integer duracion;
    @NotNull
    private GeneroPelicula genero;

}
