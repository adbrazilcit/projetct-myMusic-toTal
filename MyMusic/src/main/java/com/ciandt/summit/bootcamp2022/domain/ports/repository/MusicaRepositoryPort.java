package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;

import java.util.List;
import java.util.Optional;

public interface MusicaRepositoryPort {


    Optional<Musica> findMusicById(String id);

    List<Musica> findAll();

    List<Musica> findByNomeStartingWith(String nome);

    List<Musica> findByArtistaStartingWith(String nome);

}
