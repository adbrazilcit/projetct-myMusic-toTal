package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/music")
@Validated
public class MusicController {

    private  final MusicaServicePort musicaServicePort;

    public MusicController(MusicaServicePort musicaServicePort) {
        this.musicaServicePort = musicaServicePort;
    }

    @GetMapping(value = "/musicas")
    MusicaDTO findMusicByFilter(@RequestParam("filtro") @Min(3) String filtro ){
         //this.musicaServicePort.findMusicByFilter(filtro);
         Artista a = new Artista();

         MusicaDTO musicaDTO = new MusicaDTO();
         musicaDTO.setId("12345");
         musicaDTO.setNome("Umbrella");
         return musicaDTO;
    }


}
