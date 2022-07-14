package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;

import java.util.List;
import java.util.Optional;


public interface MusicaRepositoryPort {

    List<Musica> findByFilter(String nome, String nomeArtista);

    Optional<Musica> findMusicById(String id);

    List<Musica> findAll();
    
}
