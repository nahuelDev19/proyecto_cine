package com.personal.api.cine.ptoyecto_cine.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.PeliculaEntity;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;


public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long>{

    Page<PeliculaEntity> findByGenero(GeneroPelicula genero,Pageable pageable);
    Page<PeliculaEntity> findAll(Pageable pageable);
}
