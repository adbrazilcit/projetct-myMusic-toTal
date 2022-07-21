package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistMusicasRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistMusicasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PlaylistMusicasRepository implements PlaylistMusicasRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(PlaylistMusicasRepository.class.getName());

    @Autowired
    private SpringPlaylistMusicasRepository springPlaylistMusicasRepository;

    @Override
    public PlaylistMusicas findById(Playlist playlistId) {
        Optional<PlaylistMusicasEntity> playlistMusicas = this.springPlaylistMusicasRepository.findById(playlistId.getId());

        if (playlistMusicas.isEmpty()) {
            LOGGER.info("As informações não foram encontradas");
            throw new NotFoundException("As informações não foram encontradas");
        }

        PlaylistMusicas novaPlaylist = playlistMusicas.get().toPlaylistMusicas();

        LOGGER.info("Filtrando por: " + playlistId + " - " + playlistMusicas.isEmpty() + "resultados");
        return novaPlaylist;

    }

    @Override
    public List<PlaylistMusicas> findAll() {
        List<PlaylistMusicasEntity> playlistMusicas = this.springPlaylistMusicasRepository.findAll();
        LOGGER.info("Músicas encontradas com sucesso");
        return playlistMusicas.stream().map(PlaylistMusicasEntity::toPlaylistMusicas).collect(Collectors.toList());
        
    }

    public Musica save(PlaylistMusicas playlistMusicas){
        this.springPlaylistMusicasRepository.save(playlistMusicas.toEntity());

        return playlistMusicas.getMusicaId();
    }
}
