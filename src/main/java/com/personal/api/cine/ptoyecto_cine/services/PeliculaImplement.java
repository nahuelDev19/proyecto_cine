package com.personal.api.cine.ptoyecto_cine.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.excepciones.IdNotFoudException;
import com.personal.api.cine.ptoyecto_cine.models.request.PeliculaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.PeliculaRepository;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;

@Service 
public class PeliculaImplement implements IPeliculaService{

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public PeliculaResponse create(PeliculaRequest rq) {
       PeliculaEntity peli= new PeliculaEntity();
       peli.setTitulo(rq.getTitulo());
       peli.setDuracion(rq.getDuracion());
       peli.setGenero(rq.getGenero());
       peli.setDescripcion(rq.getDescripcion());
       return pelRes(peliculaRepository.save(peli));
    }

    @Override
    public PeliculaResponse read(Long id) {
        PeliculaEntity peli= peliculaRepository.findById(id).orElseThrow(() -> new IdNotFoudException("pelicula") );
        return this.pelRes(peli);
    }

    @Override
    public PeliculaResponse update(PeliculaRequest rq, Long id) {
        PeliculaEntity peli= peliculaRepository.findById(id).orElseThrow(() -> new IdNotFoudException("pelicula"));
    
            PeliculaEntity peliActualiza= peli;
            peliActualiza.setTitulo(rq.getTitulo());
            peliActualiza.setDescripcion(rq.getDescripcion());
            peliActualiza.setDuracion(rq.getDuracion());
            peliActualiza.setGenero(rq.getGenero());
            return pelRes(peliculaRepository.save(peliActualiza));
        
    }

    @Override
    public void delete(Long id) {
        PeliculaEntity peli= peliculaRepository.findById(id).orElseThrow(() -> new IdNotFoudException("pelicula"));
        peliculaRepository.delete(peli);
    }

    @Override   
    public Page<PeliculaResponse> paginado(Integer page, Integer size,GeneroPelicula genero) {
        // Crear un objeto PageRequest a partir de la información de paginación
        PageRequest pagerRequest= PageRequest.of(page,size);
        Page<PeliculaEntity> peliPage;
        // Obtener la página de entidades desde el repositorio filtrando por género
        if(genero!=null){
            peliPage= peliculaRepository.findByGenero(genero, pagerRequest);
        }else{
            peliPage= peliculaRepository.findAll(pagerRequest);
        }
        
        // Convertir la página de entidades en una página de respuestas
        return peliPage.map(this::pelRes);
    }

    private PeliculaResponse pelRes(PeliculaEntity entity){
        PeliculaResponse response= new PeliculaResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
