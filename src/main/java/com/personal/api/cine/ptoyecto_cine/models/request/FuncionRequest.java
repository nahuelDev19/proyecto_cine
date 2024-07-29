package com.personal.api.cine.ptoyecto_cine.models.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;
import com.personal.api.cine.ptoyecto_cine.uitils.validaciones.SalaDeCineDeserializer;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor

public class FuncionRequest {


    private Long id;
    @FutureOrPresent
    private LocalDate fecha;
    @NotNull
    private LocalTime hora;
    @JsonDeserialize(using = SalaDeCineDeserializer.class)
    private SalaDeCine sala;
    @NotNull @Positive
    private Integer precio;
    private PeliculaRequest pelicula;
}
