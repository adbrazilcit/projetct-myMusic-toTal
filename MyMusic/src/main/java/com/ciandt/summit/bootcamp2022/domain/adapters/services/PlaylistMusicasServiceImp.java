package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistMusicasServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistMusicasRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistMusicasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistMusicasServiceImp implements PlaylistMusicasServicePort {

    @Autowired
    private PlaylistMusicasRepositoryPort playlistMusicasRepositoryPort;

    @Autowired
    private MusicaServicePort musicaServicePort;

    @Autowired
    private PlaylistServicePort playlistServicePort;

    @Override
    public MusicaDTO addMusicInPlaylist(String playlistId, String musicaId) {
        Musica musica = musicaServicePort.findMusicById(musicaId).toMusic();
        Playlist playlist = playlistServicePort.findPlaylistById(playlistId);

        if(playlist == null){
            throw new NotFoundException("Playlist não encontrada!");
        }else if(musica == null){
            throw new NotFoundException("Música não encontrada!");
        }

        Musica novaMusica = playlistMusicasRepositoryPort.save(new PlaylistMusicas(playlist, musica)); 
        
        return novaMusica.toMusicaDTO();
    }
}
