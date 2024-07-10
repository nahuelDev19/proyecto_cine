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

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "peliculas")  
public class PeliculaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer duracion;
    private GeneroPelicula genero;

    @OneToMany(mappedBy = "pelicula") // Relación inversa: una película puede tener muchas funciones
    private Set<FuncionEntity> funciones;
}
