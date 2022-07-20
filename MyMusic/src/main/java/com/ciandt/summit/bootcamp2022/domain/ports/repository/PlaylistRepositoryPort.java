package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;

import java.util.List;

public interface PlaylistRepositoryPort {

    Playlist findById(String id);

    List<Playlist> findAll();


}
