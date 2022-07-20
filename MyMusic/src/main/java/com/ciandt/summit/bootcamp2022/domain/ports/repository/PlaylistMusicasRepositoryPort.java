package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;

import java.util.List;

public interface PlaylistMusicasRepositoryPort {
    PlaylistMusicas findById(Playlist playlistId);
    List<PlaylistMusicas> findAll();
    Musica save(PlaylistMusicas playlistMusicas);
}
