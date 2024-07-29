package com.personal.api.cine.ptoyecto_cine.entitys;

import java.util.Set;

import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una película en el sistema.
 * Incluye detalles como título, descripción, duración y género.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peliculas")
public class PeliculaEntity {

    /**
     * Identificador único de la película.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la película.
     */
    private String titulo;

    /**
     * Descripción de la película.
     */
    private String descripcion;

    /**
     * Duración de la película en minutos.
     */
    private Integer duracion;

    /**
     * Género de la película.
     */
    private GeneroPelicula genero;

    /**
     * Conjunto de funciones asociadas a esta película.
     * Una película puede tener muchas funciones.
     */
    @OneToMany(mappedBy = "pelicula")
    private Set<FuncionEntity> funciones;
}
