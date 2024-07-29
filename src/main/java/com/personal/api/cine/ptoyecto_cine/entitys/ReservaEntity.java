package com.personal.api.cine.ptoyecto_cine.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una reserva realizada por un usuario para una función específica.
 * Incluye la cantidad de entradas reservadas y el total a pagar.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservaciones") 
public class ReservaEntity {

    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que realiza la reserva.
     * Se establece una relación de muchos a uno con la entidad UsuarioEntity.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    /**
     * Función para la cual se realiza la reserva.
     * Se establece una relación de muchos a uno con la entidad FuncionEntity.
     */
    @ManyToOne
    @JoinColumn(name = "funcion_id")
    private FuncionEntity funcion;

    /**
     * Cantidad de entradas reservadas en esta reserva.
     */
    private Integer cantidadDeEntradas;

    /**
     * Total a pagar por la reserva.
     */
    private Integer total;
}
