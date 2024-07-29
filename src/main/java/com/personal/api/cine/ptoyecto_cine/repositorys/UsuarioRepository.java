package com.personal.api.cine.ptoyecto_cine.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link UsuarioEntity}.
 * Proporciona métodos para realizar operaciones CRUD sobre los usuarios.
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    /**
     * Busca un usuario por su nombre y apellido.
     *
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @return Un {@link Optional} que contiene el usuario encontrado, o vacío si no se encuentra.
     */
    Optional<UsuarioEntity> findByNombreAndApellido(String nombre, String apellido);

    /**
     * Busca un usuario por su nombre.
     *
     * @param nombre El nombre del usuario.
     * @return Un {@link Optional} que contiene el usuario encontrado, o vacío si no se encuentra.
     */
    Optional<UsuarioEntity> findByNombre(String nombre);
}
