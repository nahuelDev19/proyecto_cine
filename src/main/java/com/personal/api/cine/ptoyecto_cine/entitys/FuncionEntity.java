package com.personal.api.cine.ptoyecto_cine.entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "funciones") 
public class FuncionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private SalaDeCine sala;
    private Integer precio;

    @OneToMany
    private Set<ReservaEntity> reserva;
    @ManyToOne // Indica que muchas funciones pertenecen a una película
    private PeliculaEntity pelicula;
}
