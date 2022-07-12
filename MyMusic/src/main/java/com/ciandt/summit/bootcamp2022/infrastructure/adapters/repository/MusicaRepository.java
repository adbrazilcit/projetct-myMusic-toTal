package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class MusicaRepository implements MusicaRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(MusicaRepository.class.getName());
    private final SpringMusicaRepository springMusicaRepository;

    public MusicaRepository(SpringMusicaRepository springMusicaRepository) {
        this.springMusicaRepository = springMusicaRepository;
    }

    @Override
    public Optional<Musica> findMusicById(String id) {
        Optional<MusicaEntity> musicaEntity = springMusicaRepository.findMusicById(id);

        if (musicaEntity.isPresent()) {
            LOGGER.info("Musica encontrada com sucesso");
            return Optional.of(musicaEntity.get().toMusica());
        }

        LOGGER.info("Musica não encontrada");
        throw new NotFoundException("Música não encontrada");
    }

    @Override
    public List<Musica> findAll() {
        List<MusicaEntity> musicaEntity = this.springMusicaRepository.findAll();

        LOGGER.info("Músicas encontradas com sucesso");
        return musicaEntity.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

    @Override
    public List<Musica> findByNomeStartingWith(String nome) {
        List<MusicaEntity> musicaEntity = this.springMusicaRepository.findByNomeStartingWith(nome);

        LOGGER.info("Músicas encontradas com sucesso");

        return musicaEntity.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

    @Override
    public List<Musica> findByArtistaStartingWith(String nome) {
        List<MusicaEntity> musicas = this.springMusicaRepository.findByArtistaStartingWith(nome);

        LOGGER.info("Músicas encontradas com sucesso");

        return musicas.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }
}
