package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.PlayList;

import java.util.List;

public interface PlaylistRepositoryPort {

    List<PlayList> findById(String id);
    List<PlayList> findAll();


}
