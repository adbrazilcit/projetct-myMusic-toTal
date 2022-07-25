package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data<T> {

    @JsonProperty("data")
    private List<T> data;

    public Data() {
    }

    public Data(List<T> dataDTOS) {
        this.data = dataDTOS;
    }

    public List<T> getData() {
        return data;
    }

    public void setMusicaDTOS(List<T> dataDTOS) {
        this.data = dataDTOS;
    }
}
