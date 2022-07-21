package com.ciandt.summit.bootcamp2022.domain;

public class Playlist {
    private String id;


    public Playlist() {
    }

    public Playlist(String id) {
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
        return "Playlist{" +
                "id='" + id + '\'' +
                '}';
    }
}
