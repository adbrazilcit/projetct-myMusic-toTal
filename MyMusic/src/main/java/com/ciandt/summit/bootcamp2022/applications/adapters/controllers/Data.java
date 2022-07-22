package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {


    @JsonProperty("data")
    private List<MusicaDTO> data;

    public Data() {
    }

    public Data(List<MusicaDTO> dataDTOS) {
        this.data = dataDTOS;
    }

    public List<MusicaDTO> getData() {
        return data;
    }

    public void setMusicaDTOS(List<MusicaDTO> dataDTOS) {
        this.data = dataDTOS;
    }
}
