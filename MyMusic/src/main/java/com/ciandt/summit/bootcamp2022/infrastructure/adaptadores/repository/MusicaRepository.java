package com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.entities.MusicaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MusicaRepository implements MusicaRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(MusicaRepository.class.getName());

    @Autowired
    private  SpringMusicaRepository springMusicaRepository;

    @Override
    public List<Musica> findByFilter(String filtro) {
        List<MusicaEntity>  filter = this.springMusicaRepository.findByNomeContainingIgnoreCase(filtro);

        if (filter.isEmpty()){
            LOGGER.info("Nenhuma musica encontrada com o filtro: " + filtro);
            throw new NotFoundException("Nenhuma musica encontrada");
        }

        LOGGER.info("Musicas encontradas com o filtro: " + filtro);

        return filter.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

    @Override
    public Optional<Musica> findMusicById(String id) {
        Optional<MusicaEntity> musicaEntity = this.springMusicaRepository.findById(id);

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


}
