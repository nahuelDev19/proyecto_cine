package com.personal.api.cine.ptoyecto_cine.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.api.cine.ptoyecto_cine.entitys.FuncionEntity;
import java.time.LocalDate;


public interface FuncionRepository extends JpaRepository<FuncionEntity,Long>{



}
