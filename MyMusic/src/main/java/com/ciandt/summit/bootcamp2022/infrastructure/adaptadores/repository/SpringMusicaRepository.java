package com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.entities.MusicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;
import java.util.Optional;


public interface SpringMusicaRepository extends JpaRepository<MusicaEntity, String> {

    Optional<MusicaEntity> findMusicById(String id);

    List<MusicaEntity> findAll();

    List<MusicaEntity> findByNomeStartingWith(String nome);

    List<MusicaEntity> findByArtistaStartingWith(String nome);
}
