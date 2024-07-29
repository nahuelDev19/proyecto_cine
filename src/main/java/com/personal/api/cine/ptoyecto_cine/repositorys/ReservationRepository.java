package com.personal.api.cine.ptoyecto_cine.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.ReservaEntity;

/**
 * Repositorio para la entidad {@link ReservaEntity}.
 * Proporciona métodos para realizar operaciones CRUD sobre las reservas.
 */
public interface ReservationRepository extends JpaRepository<ReservaEntity, Long> {

    /**
     * Elimina todas las reservas asociadas a una función específica.
     *
     * @param funcion La función cuyas reservas deben ser eliminadas.
     */
    void deleteByFuncion(FuncionEntity funcion);
}
