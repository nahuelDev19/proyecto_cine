package com.personal.api.cine.ptoyecto_cine.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;
import com.personal.api.cine.ptoyecto_cine.excepciones.IdNotFoudException;
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
        UsuarioEntity buscado= usuarioRepository.findById(id).orElseThrow(() -> new IdNotFoudException("usuario"));
        return this.userResponse(buscado);
    }

    @Override
    public UsuarioResponse update(UsuarioRequest rq, Long id) {
        Optional<UsuarioEntity> buscado = usuarioRepository.findById(id);
        if (buscado.isPresent()) {
            UsuarioEntity user = buscado.get();
            user.setNombre(rq.getNombre());
            user.setApellido(rq.getApellido());
            user.setEmail(rq.getEmail());
            return userResponse(usuarioRepository.save(user));
        } else {
            // Manejar el caso donde no se encuentra el usuario con el id dado
            throw new IdNotFoudException("usuario");
        }
    }
    

   


    @Override
    public void delete(Long id) {
        UsuarioEntity usuario= usuarioRepository.findById(id).orElseThrow(() -> new IdNotFoudException("usuario"));
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
