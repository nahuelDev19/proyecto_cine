package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.ReservaEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;
import com.personal.api.cine.ptoyecto_cine.models.request.ReservaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.FuncionesResponse;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.models.responses.ReservasResponse;
import com.personal.api.cine.ptoyecto_cine.models.responses.UsuarioResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.FuncionRepository;
import com.personal.api.cine.ptoyecto_cine.repositorys.ReservationRepository;
import com.personal.api.cine.ptoyecto_cine.repositorys.UsuarioRepository;

@Service @Transactional
public class ReservasImplement implements IReservaService{

    @Autowired
    private ReservationRepository reservasRepository;
    @Autowired 
    private FuncionRepository funcionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public ReservasResponse create(ReservaRequest rq) {
        ReservaEntity reser= new ReservaEntity();
        if(rq.getUsuario() !=null && rq.getUsuario().getNombre() !=null){
            UsuarioEntity user= usuarioRepository.findByNombre(rq.getUsuario().getNombre()).orElseThrow(()-> new RuntimeException(
                "usuario no encontrado con el id: "+rq.getUsuario().getNombre()
            ));
        reser.setUsuario(user);}else{
            throw new RuntimeException("el usuario es necesario para crear una reserva");
        }
        if(rq.getFuncion()!=null && rq.getFuncion().getId()!=null){
            FuncionEntity fun= funcionRepository.findById(rq.getFuncion().getId()).orElseThrow(()-> new RuntimeException(
                "funcion no encontrada con id: "+ rq.getFuncion().getId()
            ));
            reser.setFuncion(fun);
            reser.setTotal(rq.getCantidadDeEntradas()*fun.getPrecio());
        }else{
            throw new RuntimeException("la funcion es requerida para realizar una reserva");
        }
        reser.setCantidadDeEntradas(rq.getCantidadDeEntradas());
        reservasRepository.save(reser);
        return this.reserva(reser);
    }

    @Override
    public ReservasResponse read(Long id) {
        ReservaEntity reser= buscadorid(id);
        return this.reserva(reser);
    }

    @Override
    public ReservasResponse update(ReservaRequest rq, Long id) {
        ReservaEntity reser= buscadorid(id);
        reser.setCantidadDeEntradas(rq.getCantidadDeEntradas());
        if(rq.getUsuario()!=null){
            UsuarioEntity user= usuarioRepository.findByNombreAndApellido(rq.getUsuario().getNombre(),rq.getUsuario().getApellido()).orElseThrow(
                
            );
            reser.setUsuario(user);
        }
        if(rq.getFuncion()!=null){
            FuncionEntity fun = funcionRepository.findById(rq.getFuncion().getId()).orElseThrow();
            reser.setFuncion(fun);
            reser.setTotal(rq.getCantidadDeEntradas()*fun.getPrecio());
        }else{
            reser.setTotal(rq.getCantidadDeEntradas()*reser.getFuncion().getPrecio());
        }        
        reservasRepository.save(reser);
        return reserva(reser);

    }

    @Override
    public void delete(Long id) {
        ReservaEntity reser= buscadorid(id);
        reservasRepository.delete(reser);
    }

    private ReservasResponse reserva(ReservaEntity entity){
        ReservasResponse response= new ReservasResponse();
        BeanUtils.copyProperties(entity, response);
        
        if (entity.getUsuario()!=null) {
            UsuarioResponse usuarioResponse= new UsuarioResponse();
            BeanUtils.copyProperties(entity.getUsuario(), usuarioResponse);
            response.setUsuario(usuarioResponse);
        }
        if (entity.getFuncion()!=null) {
            FuncionesResponse funcionesResponse= new FuncionesResponse();
            BeanUtils.copyProperties(entity.getFuncion(), funcionesResponse);
            
                if (entity.getFuncion().getPelicula() != null) {
                    PeliculaResponse peliculaResponse = new PeliculaResponse();
                    BeanUtils.copyProperties(entity.getFuncion().getPelicula(), peliculaResponse);
                    funcionesResponse.setPelicula(peliculaResponse);
        }
        
        response.setFuncion(funcionesResponse);
        }
                
        return response;
    }

    private ReservaEntity buscadorid(Long id){
        return reservasRepository.findById(id).orElseThrow(() -> new RuntimeException("el id de reserva no a sido encontrado"));
    }

}
