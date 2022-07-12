package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;

import java.util.List;

public interface MusicaRepositoryPort {
    List<Musica> findMusicByFilter(String filtro);

}
