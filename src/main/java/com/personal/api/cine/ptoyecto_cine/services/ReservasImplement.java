package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.ReservaEntity;
import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;
import com.personal.api.cine.ptoyecto_cine.excepciones.ClassNotExcist;
import com.personal.api.cine.ptoyecto_cine.excepciones.IdNotFoudException;
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
        ReservaEntity reser = new ReservaEntity();
        
        // Validación y búsqueda de usuario
        if (rq.getUsuario() == null || rq.getUsuario().getNombre() == null) {
            throw new ClassNotExcist("El nombre del usuario es necesario para crear una reserva");
        }
        UsuarioEntity user = usuarioRepository.findByNombre(rq.getUsuario().getNombre())
                .orElseThrow(() -> new ClassNotExcist("Usuario no encontrado"));
        reser.setUsuario(user);
        
        // Validación y búsqueda de función
        if (rq.getFuncion() == null || rq.getFuncion().getId() == null) {
            throw new ClassNotExcist("La función es requerida para realizar una reserva");
        }
        FuncionEntity fun = funcionRepository.findById(rq.getFuncion().getId())
                .orElseThrow(() -> new ClassNotExcist("Función no encontrada"));
        reser.setFuncion(fun);
        
        // Configuración de cantidad de entradas y total
        reser.setCantidadDeEntradas(rq.getCantidadDeEntradas());
        reser.setTotal(rq.getCantidadDeEntradas() * fun.getPrecio());
        
        // Guardar la reserva y devolver la respuesta
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
    ReservaEntity reser = buscadorid(id);
    
    // Actualizar cantidad de entradas
    reser.setCantidadDeEntradas(rq.getCantidadDeEntradas());
    
    // Validar y actualizar usuario
    if (rq.getUsuario() != null) {
        UsuarioEntity user = usuarioRepository.findByNombreAndApellido(rq.getUsuario().getNombre(), rq.getUsuario().getApellido())
                .orElseThrow(() -> new ClassNotExcist("No se encontró coincidencias con nombre y apellido"));
        reser.setUsuario(user);
    }
    
    // Validar y actualizar función
    if (rq.getFuncion() != null) {
        if (rq.getFuncion().getId()!=null) {
         
        FuncionEntity fun = funcionRepository.findById(rq.getFuncion().getId())
        .orElseThrow(() -> new ClassNotExcist("Función no encontrada"));
        reser.setFuncion(fun);
        reser.setTotal(rq.getCantidadDeEntradas() * fun.getPrecio());   
        }else{
            throw new ClassNotExcist("el id es obligatorio");
        }
    } else {
        reser.setTotal(rq.getCantidadDeEntradas() * reser.getFuncion().getPrecio());
    }
    
    // Guardar la reserva actualizada y devolver la respuesta
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
        return reservasRepository.findById(id).orElseThrow(() -> new IdNotFoudException("reserva"));
    }

}
