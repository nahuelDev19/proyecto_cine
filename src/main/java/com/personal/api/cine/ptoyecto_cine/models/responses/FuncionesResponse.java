package com.personal.api.cine.ptoyecto_cine.models.responses;

import java.time.LocalDate;
import java.time.LocalTime;

import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FuncionesResponse {

    private LocalDate fecha;
    private LocalTime hora;
    private SalaDeCine sala;
    private Integer precio;
    private PeliculaResponse pelicula;
}
