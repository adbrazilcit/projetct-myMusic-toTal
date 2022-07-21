package com.ciandt.summit.bootcamp2022.domain.ports.repository;

import com.ciandt.summit.bootcamp2022.domain.PlayList;

import java.util.List;

public interface PlaylistMusicasRepositoryPort {
    List<PlayList> findById(PlayList playlistId);
    List<PlayList> findAll();
}
