package com.ciandt.summit.bootcamp2022.domain.adaptadores.services;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;


public class MusicaServiceImp implements MusicaServicePort {

    private final MusicaRepositoryPort musicaRepositoryPort;

    public MusicaServiceImp(MusicaRepositoryPort musicaRepositoryPort) {
        this.musicaRepositoryPort = musicaRepositoryPort;
    }

    @Override
    public List<MusicaDTO> findMusicByFilter(String filtro) {

//        this.musicaRepositoryPort.(filtro);
        return null;
    }
}
