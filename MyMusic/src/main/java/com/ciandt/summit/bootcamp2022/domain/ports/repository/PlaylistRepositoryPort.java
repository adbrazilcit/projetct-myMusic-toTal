package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Playlist;

import java.util.List;

public interface PlaylistRepositoryPort {

    Playlist findById(String id);

    List<Playlist> findAll();

    void save(String playlistId, String musicaId);
}
