package com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.entities.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SpringArtistaRepository extends JpaRepository<ArtistaEntity,String> {
}
