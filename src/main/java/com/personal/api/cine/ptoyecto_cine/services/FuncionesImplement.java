package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.FuncionesResponse;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.FuncionRepository;
import com.personal.api.cine.ptoyecto_cine.repositorys.PeliculaRepository;

@Service @Transactional
public class FuncionesImplement implements IFuncionService{

    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;


    @Override
    public FuncionesResponse create(FuncionRequest rq) {
        FuncionEntity fun= new FuncionEntity();
        fun.setFecha(rq.getFecha());
        fun.setHora(rq.getHora());
        if(rq.getPelicula().getTitulo()!=null){
            PeliculaEntity nuevaPeli= peliculaRepository.findByTitulo(rq.getPelicula().getTitulo()).orElseThrow();
            fun.setPelicula(nuevaPeli);        
        }
        fun.setPrecio(rq.getPrecio());
        fun.setSala(rq.getSala());
        return this.funcionResponse(funcionRepository.save(fun));
    }

    @Override
    public FuncionesResponse read(Long id) {
        FuncionEntity fun=buscadorId(id);
        return funcionResponse(fun);
    }

    @Override
    public FuncionesResponse update(FuncionRequest rq, Long id) {
        FuncionEntity fun=  buscadorId(id);
        FuncionEntity funcionActulizada= fun;
        funcionActulizada.setFecha(rq.getFecha());
        funcionActulizada.setHora(rq.getHora());
        if(rq.getPelicula().getTitulo()!=null){
            PeliculaEntity nuevaPeli= peliculaRepository.findByTitulo(rq.getPelicula().getTitulo()).orElseThrow();
            funcionActulizada.setPelicula(nuevaPeli);        
        }
        funcionActulizada.setSala(rq.getSala());
        return funcionResponse(funcionRepository.save(funcionActulizada));
    }

    @Override
    public void delete(Long id) {
        FuncionEntity fun= funcionRepository.findById(id).orElseThrow();
        funcionRepository.delete(fun);
    }

    private FuncionesResponse funcionResponse(FuncionEntity entity){
        FuncionesResponse response = new FuncionesResponse();
        BeanUtils.copyProperties(entity, response);
        PeliculaResponse peliResponse= new PeliculaResponse();
        if (entity.getPelicula() != null) {
            BeanUtils.copyProperties(entity.getPelicula(), peliResponse);
        }
        response.setPelicula(peliResponse);
        return response;
    }

    private FuncionEntity buscadorId(Long id){
        return funcionRepository.findById(id).orElseThrow();
    }

}
