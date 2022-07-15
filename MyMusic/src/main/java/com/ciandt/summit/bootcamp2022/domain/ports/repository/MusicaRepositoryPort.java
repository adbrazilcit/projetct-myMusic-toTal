package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;

import java.util.List;


public interface MusicaRepositoryPort {

    List<Musica> findByFilter(String nome, String nomeArtista);

    List<Musica> findAll();


}
