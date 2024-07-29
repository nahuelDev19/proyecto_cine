package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.data.domain.Page;
import com.personal.api.cine.ptoyecto_cine.models.request.PeliculaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

/**
 * Interfaz para servicios de gestión de películas.
 * Extiende de {@link ICrudService} para operaciones básicas CRUD y agrega funcionalidades específicas de películas.
 */
public interface IPeliculaService extends ICrudService<PeliculaRequest, PeliculaResponse, Long> {

    /**
     * Obtiene una página de respuestas de películas, con soporte para filtrado por género.
     *
     * @param page El número de página a recuperar (comienza en 0).
     * @param size El número de elementos por página.
     * @param genero El género de la película para filtrar (puede ser null para no filtrar por género).
     * @return Una página de respuestas de películas.
     */
    Page<PeliculaResponse> paginado(Integer page, Integer size, GeneroPelicula genero);

}
