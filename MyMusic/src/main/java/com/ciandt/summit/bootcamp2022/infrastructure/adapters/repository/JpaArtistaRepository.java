package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaArtistaRepository extends JpaRepository<ArtistaEntity, String> {

    List<ArtistaEntity> findByNomeContainingIgnoreCase(String nome);
}
