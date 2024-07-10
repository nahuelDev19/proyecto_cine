package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.data.domain.Page;

import com.personal.api.cine.ptoyecto_cine.models.request.PeliculaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

public interface IPeliculaService extends ICrudService<PeliculaRequest,PeliculaResponse,Long>{

        Page<PeliculaResponse> paginado(Integer page, Integer size,GeneroPelicula genero);
        

}
