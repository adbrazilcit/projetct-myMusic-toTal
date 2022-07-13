package com.ciandt.summit.bootcamp2022.domain.adaptadores.services;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repository.MusicaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaServiceImp implements MusicaServicePort {

    @Autowired
    private  MusicaRepositoryPort musicaRepositoryPort;


//    public MusicaServiceImp(MusicaRepositoryPort musicaRepositoryPort) {
//        this.musicaRepositoryPort = musicaRepositoryPort;
//    }

    @Override
    public List<MusicaDTO> findByFilter(String filtro) {
        this.musicaRepositoryPort.findAll();
        return null;
    }
}
