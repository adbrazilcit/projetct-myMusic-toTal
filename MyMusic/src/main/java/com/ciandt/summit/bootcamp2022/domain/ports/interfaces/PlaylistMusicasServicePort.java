package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;

import java.util.List;


public interface PlaylistMusicasServicePort {

    MusicaDTO addMusicInPlaylist(String playlistId, String musicaId);

}
