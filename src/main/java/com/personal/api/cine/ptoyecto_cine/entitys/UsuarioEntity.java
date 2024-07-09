package com.personal.api.cine.ptoyecto_cine.entitys;

import java.util.HashSet;
import java.util.Set;

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
@Table(name ="usuarios") 
public class UsuarioEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String password;

    @OneToMany 
    private Set<ReservaEntity> reservas = new HashSet<>();

    public void addReserva(ReservaEntity reserva) {
        reservas.add(reserva);
    }

    public void removeReserva(ReservaEntity reserva) {
        reservas.remove(reserva);
    }
}
