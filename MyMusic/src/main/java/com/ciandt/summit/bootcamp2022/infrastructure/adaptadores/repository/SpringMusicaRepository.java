package com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.repository;

import com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.entities.MusicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringMusicaRepository extends JpaRepository<MusicaEntity, String> {

   List<MusicaEntity> findByNomeContainingIgnoreCaseOrArtistasNomeContainingIgnoreCase(String nome, String nomeArtista);

}
