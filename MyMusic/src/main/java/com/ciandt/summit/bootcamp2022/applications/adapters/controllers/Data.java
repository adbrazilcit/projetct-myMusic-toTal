package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;

import java.util.List;

public class Data<T> {
    private List<T> data;

    public Data(List<T> dataDTOS) {
        this.data= dataDTOS;
    }

    public List<T> getData() {
        return data;
    }

    public void setMusicaDTOS(List<T> dataDTOS) {
        this.data = dataDTOS;
    }
}
