package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.excepciones.IdNotFoudException;
import com.personal.api.cine.ptoyecto_cine.excepciones.TituloException;
import com.personal.api.cine.ptoyecto_cine.models.request.PeliculaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.PeliculaRepository;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

/**
 * Implementación de los servicios para manejar películas.
 */
@Service
@Transactional
public class PeliculaImplement implements IPeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    /**
     * Crea una nueva película con los detalles proporcionados en la solicitud.
     *
     * @param rq Los detalles de la película a crear.
     * @return La respuesta de la película creada.
     * @throws TituloException Si ya existe una película con el mismo título.
     */
    @Override
    public PeliculaResponse create(PeliculaRequest rq) {
        if (peliculaRepository.existsByTitulo(rq.getTitulo())) {
            throw new TituloException(rq.getTitulo());
        }
        PeliculaEntity peli = new PeliculaEntity();
        peli.setTitulo(rq.getTitulo());
        peli.setDuracion(rq.getDuracion());
        peli.setGenero(rq.getGenero());
        peli.setDescripcion(rq.getDescripcion());
        return pelRes(peliculaRepository.save(peli));
    }

    /**
     * Recupera los detalles de una película por su identificador.
     *
     * @param id El identificador de la película.
     * @return La respuesta de la película.
     * @throws IdNotFoudException Si la película no es encontrada.
     */
    @Override
    public PeliculaResponse read(Long id) {
        PeliculaEntity peli = peliculaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Película no encontrada"));
        return this.pelRes(peli);
    }

    /**
     * Actualiza los detalles de una película existente.
     *
     * @param rq Los nuevos detalles de la película.
     * @param id El identificador de la película a actualizar.
     * @return La respuesta de la película actualizada.
     * @throws IdNotFoudException Si la película no es encontrada.
     */
    @Transactional
    @Override
    public PeliculaResponse update(PeliculaRequest rq, Long id) {
        PeliculaEntity peli = peliculaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Película no encontrada"));

        peli.setTitulo(rq.getTitulo());
        peli.setDescripcion(rq.getDescripcion());
        peli.setDuracion(rq.getDuracion());
        peli.setGenero(rq.getGenero());
        return pelRes(peliculaRepository.save(peli));
    }

    /**
     * Elimina una película por su identificador.
     *
     * @param id El identificador de la película a eliminar.
     * @throws IdNotFoudException Si la película no es encontrada.
     */
    @Override
    public void delete(Long id) {
        PeliculaEntity peli = peliculaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Película no encontrada"));
        peliculaRepository.delete(peli);
    }

    /**
     * Obtiene una página de películas, filtradas opcionalmente por género.
     *
     * @param page   El número de la página a recuperar.
     * @param size   El tamaño de la página.
     * @param genero El género por el que filtrar (puede ser null).
     * @return Una página de respuestas de películas.
     */
    @Override
    public Page<PeliculaResponse> paginado(Integer page, Integer size, GeneroPelicula genero) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PeliculaEntity> peliPage;
        if (genero != null) {
            peliPage = peliculaRepository.findByGenero(genero, pageRequest);
        } else {
            peliPage = peliculaRepository.findAll(pageRequest);
        }
        return peliPage.map(this::pelRes);
    }

    /**
     * Convierte una entidad de película en una respuesta de película.
     *
     * @param entity La entidad de la película.
     * @return La respuesta de la película.
     */
    private PeliculaResponse pelRes(PeliculaEntity entity) {
        PeliculaResponse response = new PeliculaResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
