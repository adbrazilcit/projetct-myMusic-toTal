package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringMusicaRepository extends JpaRepository<MusicaEntity, String> {

    Optional<MusicaEntity> findMusicById(String id);

    List<MusicaEntity> findAll();

    List<MusicaEntity> findByNomeStartingWith(String nome);

    List<MusicaEntity> findByArtistaStartingWith(String nome);
}
