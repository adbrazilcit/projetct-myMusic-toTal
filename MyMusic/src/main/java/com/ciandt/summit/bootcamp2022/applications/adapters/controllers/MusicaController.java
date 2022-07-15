package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicaServicePort;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Validated
public class MusicaController {

    @Autowired
    private  MusicaServicePort musicaServicePort;

    @GetMapping(value = "/musicas")
//    @GetMapping(value = {"/musicas", "/musicas{filtro}"})
//    List<MusicaDTO> findByFilter(@PathParam(required = false) @Size(min = 3) String filtro ){
//    List<MusicaDTO> findByFilter(@RequestParam(required = true)  Optional<String> filtro ){
    Data findByFilter(@RequestParam(required = false) Optional<String> filtro ){

        if(!filtro.isPresent()){
            return new Data(this.musicaServicePort.findAll());
        }
        if(filtro.get().length()<3)
            throw new ConstraintViolationException("teste",null);

        return new Data(this.musicaServicePort.findMusicByFilter(filtro.get()));

    }



}
