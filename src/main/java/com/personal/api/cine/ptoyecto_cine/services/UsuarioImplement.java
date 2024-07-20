package com.personal.api.cine.ptoyecto_cine.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;
import com.personal.api.cine.ptoyecto_cine.models.request.UsuarioRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.UsuarioResponse;
import com.personal.api.cine.ptoyecto_cine.repositorys.UsuarioRepository;

@Service  @Transactional
public class UsuarioImplement implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse create(UsuarioRequest rq) {
        UsuarioEntity user = new UsuarioEntity();
        user.setNombre(rq.getNombre());
        user.setApellido(rq.getApellido());
        user.setEmail(rq.getEmail());
        user.setEdad(rq.getEdad());
        user.setPassword(rq.getPassword());
        UsuarioEntity userCreate= usuarioRepository.save(user);
        return this.userResponse(userCreate);
    }

    @Override
    public UsuarioResponse read(Long id) {
        UsuarioEntity buscado= usuarioRepository.findById(id).orElseThrow();
        return this.userResponse(buscado);
    }

    /*@Override
    public UsuarioResponse update(UsuarioRequest rq, Long id) {
        Optional<UsuarioEntity> buscado= usuarioRepository.findById(id);
        if (buscado.isPresent()){
            UsuarioEntity user= buscado.get();
            user.setNombre(rq.getNombre());
            user.setApellido(rq.getApellido());
            user.setEmail(rq.getEmail());
            return userResponse(usuarioRepository.save(user));
        }else {
        // Manejar el caso donde no se encuentra el usuario con el id dado
        throw new NoSuchElementException("Usuario no encontrado con id: " + id);
    }
    }*/

    @Override
public UsuarioResponse update(UsuarioRequest rq, Long id) {
    Optional<UsuarioEntity> buscado = usuarioRepository.findById(id);
    if (buscado.isPresent()) {
        UsuarioEntity user = buscado.get();

        if (rq.getNombre() != null ) {
            user.setNombre(rq.getNombre());
        }
        if (rq.getApellido() != null) {
            user.setApellido(rq.getApellido());
        }
        if (rq.getEdad() != null) {
            user.setEdad(rq.getEdad());
        }
        if (rq.getEmail() != null) {
            user.setEmail(rq.getEmail());
        }
        if (rq.getPassword() != null) {
            user.setPassword(rq.getPassword());
        }

        return userResponse(usuarioRepository.save(user));
    } else {
        throw new NoSuchElementException("Usuario no encontrado con id: " + id);
    }
}


    @Override
    public void delete(Long id) {
        UsuarioEntity usuario= usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }


    private UsuarioResponse userResponse(UsuarioEntity entity){
        if (entity==null) {
            return null;
        }
        UsuarioResponse response = new UsuarioResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


}
