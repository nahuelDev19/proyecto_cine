package com.personal.api.cine.ptoyecto_cine.services;

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

/**
 * Implementación de los servicios para manejar usuarios.
 */
@Service
@Transactional
public class UsuarioImplement implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Crea un nuevo usuario con los detalles proporcionados en la solicitud.
     *
     * @param rq Los detalles del usuario a crear.
     * @return La respuesta del usuario creado.
     */
    @Override
    public UsuarioResponse create(UsuarioRequest rq) {
        UsuarioEntity user = new UsuarioEntity();
        user.setNombre(rq.getNombre());
        user.setApellido(rq.getApellido());
        user.setEmail(rq.getEmail());
        user.setEdad(rq.getEdad());
        user.setPassword(rq.getPassword());
        UsuarioEntity userCreate = usuarioRepository.save(user);
        return this.userResponse(userCreate);
    }

    /**
     * Recupera los detalles de un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return La respuesta del usuario.
     * @throws IdNotFoudException Si el usuario no es encontrado.
     */
    @Override
    public UsuarioResponse read(Long id) {
        UsuarioEntity buscado = usuarioRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Usuario no encontrado"));
        return this.userResponse(buscado);
    }

    /**
     * Actualiza los detalles de un usuario existente.
     *
     * @param rq Los nuevos detalles del usuario.
     * @param id El identificador del usuario a actualizar.
     * @return La respuesta del usuario actualizado.
     * @throws IdNotFoudException Si el usuario no es encontrado.
     */
    @Override
    public UsuarioResponse update(UsuarioRequest rq, Long id) {
        Optional<UsuarioEntity> buscado = usuarioRepository.findById(id);
        if (buscado.isPresent()) {
            UsuarioEntity user = buscado.get();
            user.setNombre(rq.getNombre());
            user.setApellido(rq.getApellido());
            user.setEmail(rq.getEmail());
            user.setEdad(rq.getEdad());
            user.setPassword(rq.getPassword());
            return userResponse(usuarioRepository.save(user));
        } else {
            throw new IdNotFoudException("Usuario no encontrado");
        }
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id El identificador del usuario a eliminar.
     * @throws IdNotFoudException Si el usuario no es encontrado.
     */
    @Override
    public void delete(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IdNotFoudException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    /**
     * Convierte una entidad de usuario en una respuesta de usuario.
     *
     * @param entity La entidad del usuario.
     * @return La respuesta del usuario.
     */
    private UsuarioResponse userResponse(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        UsuarioResponse response = new UsuarioResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
