package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;

import org.springframework.stereotype.Repository;


import java.util.List;


import java.util.List;
import java.util.Optional;



public interface MusicaRepositoryPort {
//    List<Musica> findMusicByFilter(String filtro);


   // Optional<Musica> findMusicById(String id);

    List<Musica> findAll();

    //List<Musica> findByNomeStartingWith(String nome);

    //List<Musica> findByArtistaStartingWith(String nome);

}
