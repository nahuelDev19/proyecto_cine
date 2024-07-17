package com.personal.api.cine.ptoyecto_cine.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.UsuarioEntity;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long>{

    Optional<UsuarioEntity> findByNombreAndApellido(String nombre, String apellido);

}
