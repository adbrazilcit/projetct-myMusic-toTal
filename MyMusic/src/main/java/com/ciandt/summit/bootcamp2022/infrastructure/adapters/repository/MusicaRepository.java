package com.ciandt.summit.bootcamp2022.infrastructure.adapters.repository;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.exceptions.BadRequestException;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.ArtistaEntity;
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
    private SpringMusicaRepository springMusicaRepository;

    @Autowired
    private SpringArtistaRepository springArtistaRepository;

    @Override
    public List<Musica> findByFilter(String filtro) {
        List<MusicaEntity> musica = this.springMusicaRepository.findByNomeContainingIgnoreCase(filtro);
        List<ArtistaEntity> artista = this.springArtistaRepository.findByNomeContainingIgnoreCase(filtro);

        if (musica.isEmpty() && artista.isEmpty()) {
            LOGGER.info("As informações não foram encontradas");
            throw new NotFoundException("As informações não foram encontradas");
        }

        for (ArtistaEntity artitas : artista) {
            musica.addAll(springMusicaRepository.findByArtistas(artitas));
        }

        LOGGER.info("Filtrando por: " + filtro + " - " + musica.size() + " resultados");
        return musica.stream().map(
                MusicaEntity::toMusica).collect(Collectors.toList());
    }

    @Override
    public List<Musica> findAll() {
        List<MusicaEntity> musicaEntity = this.springMusicaRepository.findAll();

        LOGGER.info("Músicas encontradas com sucesso");
        return musicaEntity.stream().map(MusicaEntity::toMusica).collect(Collectors.toList());
    }

    public Musica findMusicById(String musicId) {

        if (musicId == null) {
            LOGGER.info("ID da Música incorreto");
            throw new BadRequestException("Id não informado");
        }

        if (this.springMusicaRepository.findById(musicId).isEmpty()) {
            LOGGER.info("Música não existe");
            throw new BadRequestException("Música não existe no banco de dados");
        }

        return this.springMusicaRepository.findById(musicId).get().toMusica();
    }
}
