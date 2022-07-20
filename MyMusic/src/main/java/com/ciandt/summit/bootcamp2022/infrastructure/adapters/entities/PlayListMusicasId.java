package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class PlayListMusicasId implements Serializable {


    private PlaylistEntity playlistId;


    private MusicaEntity musicaId;

    public PlayListMusicasId() {

    }

    public PlaylistEntity getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(PlaylistEntity playlistId) {
        this.playlistId = playlistId;
    }

    public MusicaEntity getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(MusicaEntity musicaId) {
        this.musicaId = musicaId;
    }
}
