package com.ciandt.summit.bootcamp2022.domain.adaptadores.services;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicaServiceImp implements MusicaServicePort {

    @Autowired
    private  MusicaRepositoryPort musicaRepositoryPort;



    @Override
    public List<MusicaDTO> findMusicByFilter(String filtro) {
        List<Musica> musicas = this.musicaRepositoryPort.findByFilter(filtro, filtro);

        if(musicas.isEmpty())
            throw  new NotFoundException("As informações não foram encontradas");

        return  musicas.stream().map(
                musica -> new MusicaDTO( musica.getId(), musica.getNome(), musica.getArtistaId())
        ).collect(Collectors.toList());
    }
}
