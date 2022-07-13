package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1/music")
@Validated
public class MusicaController {

    @Autowired
    private  MusicaServicePort musicaServicePort;


    @GetMapping(value = "/musicas")
    MusicaDTO findMusicByFilter(@PathParam("filtro")  @Size(min = 3) String filtro ){
        Artista a = new Artista();
        a.setId("2");
        a.setNome("Bruno");

        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.setId("12345");
        musicaDTO.setNome("Umbrella");
        musicaDTO.setArtista(a);
        //this.musicaServicePort.findMusicByFilter(filtro);
        return musicaDTO;
    }


}
