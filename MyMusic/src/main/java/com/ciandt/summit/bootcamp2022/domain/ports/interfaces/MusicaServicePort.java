package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MusicaServicePort {
       List<MusicaDTO> findMusicByFilter(String filtro);
}
