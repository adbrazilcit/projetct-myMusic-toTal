package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MusicaRepository implements MusicaRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(MusicaRepository.class.getName());

    @Autowired
    private  SpringMusicaRepository springMusicaRepository;

    @Override
    public List<Musica> findByFilter(String nome, String nomeArtista) {
        List<MusicaEntity>  filter = this.springMusicaRepository.findByNomeContainingIgnoreCaseOrArtistasNomeContainingIgnoreCase(nome, nomeArtista);


        if (filter.isEmpty()){
            LOGGER.info("Nenhuma musica encontrada com o filtro: " + filter);
            throw new NotFoundException("Nenhuma musica encontrada");
        }

        LOGGER.info("Musicas encontradas com o filtro: " + filter);

        return filter.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

    @Override
    public List<Musica> findAll() {
        List<MusicaEntity> musicaEntity = this.springMusicaRepository.findAll();

        LOGGER.info("Músicas encontradas com sucesso");
        return musicaEntity.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

}
