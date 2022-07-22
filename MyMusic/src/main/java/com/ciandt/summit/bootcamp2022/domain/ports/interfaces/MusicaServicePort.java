package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;

import java.util.List;


public interface MusicaServicePort {

    List<MusicaDTO> findMusicByFilter(String filtro);

    List<MusicaDTO> findAll();

    MusicaDTO findMusicById(String param);
}
