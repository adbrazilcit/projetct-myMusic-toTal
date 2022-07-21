package com.ciandt.summit.bootcamp2022.domain;

public class PlayList {
    private String id;

    public PlayList() {
    }

    public PlayList(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id='" + id + '\'' +
                '}';
    }
}
