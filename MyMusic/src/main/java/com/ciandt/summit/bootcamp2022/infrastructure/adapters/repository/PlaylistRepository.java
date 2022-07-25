package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.exceptions.BadRequestException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class PlaylistRepository implements PlaylistRepositoryPort {

    @Autowired
    private JpaPlaylistRepository springPlaylistRepository;

    @Autowired
    private JpaMusicaRepository springMusicaRepository;

    @Override
    public Playlist findById(String playlistId) {
        Optional<PlaylistEntity> playlist = this.springPlaylistRepository.findById(playlistId);

        if (playlist.isEmpty()) {
            LOGGER.info("As informações não foram encontradas");
            throw new BadRequestException("Playlist não encontrada");
        }

        LOGGER.info("Filtrando por: " + playlistId);
        return playlist.get().toPlayList();
    }

    @Override
    public List<Playlist> findAll() {
        List<PlaylistEntity> playlistEntity = this.springPlaylistRepository.findAll();
        LOGGER.info("Músicas encontradas com sucesso");
        return playlistEntity.stream().map(PlaylistEntity::toPlayList).collect(Collectors.toList());
    }

    @Override
    public void save(String playlistId, String musicaId) {
        Optional<PlaylistEntity> playlist = this.springPlaylistRepository.findById(playlistId);

        LOGGER.info("Playlist encontrada " + playlistId);

        Optional<MusicaEntity> musica = this.springMusicaRepository.findById(musicaId);

        LOGGER.info("Musica encontrada " + musicaId);
        playlist.get().adicionaMusicasNaPlaylist(musica.get());

        this.springPlaylistRepository.save(playlist.get());

        LOGGER.info("Música adicionada com sucesso");
    }
}
