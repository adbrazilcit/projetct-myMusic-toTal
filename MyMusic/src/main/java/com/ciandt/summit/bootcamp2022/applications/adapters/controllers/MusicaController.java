package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Validated
public class MusicaController {

    @Autowired
    private  MusicaServicePort musicaServicePort;

    @GetMapping(value = "/musicas")
    @ApiOperation(value = "O endpoint retorna músicas e artistas ordenados por artista.")
    Data findByFilter(@RequestParam(required = false) Optional<String> filtro ){

        if(!filtro.isPresent()){
            return new Data(this.musicaServicePort.findAll());
        }

        if(filtro.get().length()<3)
            throw new ConstraintViolationException("Filtro menor que três caracteres ", null);

        return new Data(this.musicaServicePort.findMusicByFilter(filtro.get()));
    }

}
