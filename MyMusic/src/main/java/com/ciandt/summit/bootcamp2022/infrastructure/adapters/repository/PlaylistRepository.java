package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.PlayList;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistMusicasRepository;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PlaylistRepository implements PlaylistMusicasRepository {

    private static final Logger LOGGER = Logger.getLogger(PlaylistRepository.class.getName());

    @Autowired
    private SpringPlaylistMusicasEntityRepository springPlaylistMusicasEntityRepository;

    @Autowired
    private SpringPlaylistMusicasIdRepository springPlaylistMusicasIdRepository;

    @Autowired
    private SpringPlaylistEntityRepository springPlaylistEntityRepository;

    @Autowired
    private PlaylistMusicasRepository playlistMusicasRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistRepositoryPort playlistRepositoryPort;

    @Override
    public List<PlayList> findById(PlayList playlistId) {
        Optional<PlaylistEntity> playlist = this.springPlaylistEntityRepository.findById(playlistId.getId());

        if (playlist.isEmpty() && playlistId.getId().isEmpty()) {
            LOGGER.info("As informações não foram encontradas");
            throw new NotFoundException("As informações não foram encontradas");


        }
        LOGGER.info("Filtrando por: " + playlistId + " - " + playlist.isEmpty() + "resultados");
        return playlist.stream().map(PlaylistEntity::toPlayList).collect(Collectors.toList());


    }

    @Override
    public List<PlayList> findAll() {
        List<PlaylistEntity> playlistEntity = this.springPlaylistEntityRepository.findAll();
        LOGGER.info("Músicas encontradas com sucesso");
        return playlistEntity.stream().map(PlaylistEntity::toPlayList).collect(Collectors.toList());
        
    }
}
