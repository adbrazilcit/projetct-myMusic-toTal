package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistMusicasServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistMusicasRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImp implements PlaylistServicePort {

    @Autowired
    private PlaylistRepositoryPort playlistRepositoryPort;

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playlists = this.playlistRepositoryPort.findAll();

        if(playlists.isEmpty())
            throw  new NotFoundException("As informações não foram encontradas");

        return playlists;
    }

    @Override
    public Playlist findPlaylistById(String param) {
        return this.playlistRepositoryPort.findById(param);
    }

}
