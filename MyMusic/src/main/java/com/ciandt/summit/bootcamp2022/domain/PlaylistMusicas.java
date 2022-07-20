package com.ciandt.summit.bootcamp2022.domain;

import javax.persistence.Column;

public class PlaylistMusicas {

    private PlayList playlistId;

    private Musica musicaId;

    public PlaylistMusicas(){

    }
    public PlaylistMusicas(PlayList playlistId, Musica musicaId) {
        this.playlistId = playlistId;
        this.musicaId = musicaId;
    }

    public PlayList getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(PlayList playlistId) {
        this.playlistId = playlistId;
    }

    public Musica getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Musica musicaId) {
        this.musicaId = musicaId;
    }
}
