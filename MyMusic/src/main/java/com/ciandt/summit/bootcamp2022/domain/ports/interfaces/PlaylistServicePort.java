package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;

import java.util.List;


public interface PlaylistServicePort {
       List<Playlist> findAll();

       Playlist findPlaylistById(String id);


}
