package com.personal.api.cine.ptoyecto_cine.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.excepciones.IdNotFoudException;
import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.FuncionesResponse;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.FuncionRepository;
import com.personal.api.cine.ptoyecto_cine.repositorys.PeliculaRepository;
import com.personal.api.cine.ptoyecto_cine.repositorys.ReservationRepository;

/**
 * Implementación de los servicios para manejar funciones de cine.
 */
@Service
@Transactional
public class FuncionesImplement implements IFuncionService {

    @Autowired
    private ReservationRepository reservaRepository;
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    /**
     * Crea una nueva función con los detalles proporcionados en la solicitud.
     *
     * @param rq Los detalles de la función a crear.
     * @return La respuesta de la función creada.
     */
    @Override
    public FuncionesResponse create(FuncionRequest rq) {
        FuncionEntity fun = new FuncionEntity();
        fun.setFecha(rq.getFecha());
        fun.setHora(rq.getHora());
        PeliculaEntity nuevaPeli = peliculaRepository.findByTitulo(rq.getPelicula().getTitulo())
                .orElseThrow(() -> new IdNotFoudException("Película no encontrada"));
        fun.setPelicula(nuevaPeli);
        fun.setPrecio(rq.getPrecio());
        fun.setSala(rq.getSala());
        return this.funcionResponse(funcionRepository.save(fun));
    }

    /**
     * Recupera los detalles de una función por su identificador.
     *
     * @param id El identificador de la función.
     * @return La respuesta de la función.
     */
    @Override
    public FuncionesResponse read(Long id) {
        FuncionEntity fun = buscadorId(id);
        return funcionResponse(fun);
    }

    /**
     * Actualiza los detalles de una función existente.
     *
     * @param rq Los nuevos detalles de la función.
     * @param id El identificador de la función a actualizar.
     * @return La respuesta de la función actualizada.
     */
    @Override
    public FuncionesResponse update(FuncionRequest rq, Long id) {
        FuncionEntity fun = buscadorId(id);
        FuncionEntity funcionActualizada = fun;
        funcionActualizada.setFecha(rq.getFecha());
        funcionActualizada.setHora(rq.getHora());
        if (rq.getPelicula().getTitulo() != null) {
            PeliculaEntity nuevaPeli = peliculaRepository.findByTitulo(rq.getPelicula().getTitulo())
                    .orElseThrow(() -> new IdNotFoudException("Película no encontrada"));
            funcionActualizada.setPelicula(nuevaPeli);
        }
        funcionActualizada.setSala(rq.getSala());
        return funcionResponse(funcionRepository.save(funcionActualizada));
    }

    /**
     * Elimina una función y sus reservaciones asociadas.
     *
     * @param id El identificador de la función a eliminar.
     */
    @Transactional
    @Override
    public void delete(Long id) {
        FuncionEntity fun = funcionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Función no encontrada"));

        // Eliminar todas las reservaciones relacionadas con la función antes de eliminar la función
        reservaRepository.deleteByFuncion(fun);
        // Eliminar la función
        funcionRepository.delete(fun);
    }

    /**
     * Convierte una entidad de función en una respuesta de función.
     *
     * @param entity La entidad de la función.
     * @return La respuesta de la función.
     */
    private FuncionesResponse funcionResponse(FuncionEntity entity) {
        FuncionesResponse response = new FuncionesResponse();
        BeanUtils.copyProperties(entity, response);
        PeliculaResponse peliResponse = new PeliculaResponse();
        if (entity.getPelicula() != null) {
            BeanUtils.copyProperties(entity.getPelicula(), peliResponse);
        }
        response.setPelicula(peliResponse);
        return response;
    }

    /**
     * Busca una función por su identificador.
     *
     * @param id El identificador de la función.
     * @return La entidad de la función.
     * @throws IdNotFoudException Si la función no es encontrada.
     */
    private FuncionEntity buscadorId(Long id) {
        return funcionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Función no encontrada"));
    }
}
