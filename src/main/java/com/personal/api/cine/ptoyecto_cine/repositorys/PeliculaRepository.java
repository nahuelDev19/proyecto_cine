package com.personal.api.cine.ptoyecto_cine.repositorys;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

/**
 * Repositorio para la entidad {@link PeliculaEntity}.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre las películas.
 */
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    /**
     * Encuentra todas las películas por su género, paginadas.
     *
     * @param genero El género de las películas a buscar.
     * @param pageable Información de paginación y ordenación.
     * @return Una página de entidades {@link PeliculaEntity} que coinciden con el género especificado.
     */
    Page<PeliculaEntity> findByGenero(GeneroPelicula genero, Pageable pageable);

    /**
     * Encuentra todas las películas, paginadas.
     *
     * @param pageable Información de paginación y ordenación.
     * @return Una página de todas las entidades {@link PeliculaEntity}.
     */
    @Override
    Page<PeliculaEntity> findAll(Pageable pageable);

    /**
     * Encuentra una película por su título.
     *
     * @param titulo El título de la película a buscar.
     * @return Un {@link Optional} que contiene la entidad {@link PeliculaEntity} si se encuentra, o vacío si no se encuentra.
     */
    Optional<PeliculaEntity> findByTitulo(String titulo);

    /**
     * Verifica si existe una película con el título especificado.
     *
     * @param titulo El título de la película a verificar.
     * @return true si existe una película con el título especificado, false en caso contrario.
     */
    boolean existsByTitulo(String titulo);
}
