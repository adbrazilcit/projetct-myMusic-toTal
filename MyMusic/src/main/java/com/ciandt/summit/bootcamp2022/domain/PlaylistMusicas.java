package com.ciandt.summit.bootcamp2022.domain;

import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.MusicaEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities.PlaylistMusicasEntity;

public class PlaylistMusicas {

    private Playlist playlistId;

    private Musica musicaId;

    public PlaylistMusicas(){

    }
    public PlaylistMusicas(Playlist playlistId, Musica musicaId) {
        this.playlistId = playlistId;
        this.musicaId = musicaId;
    }

    public Playlist getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Playlist playlistId) {
        this.playlistId = playlistId;
    }

    public Musica getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Musica musicaId) {
        this.musicaId = musicaId;
    }

    public PlaylistMusicasEntity toEntity(){
        return new PlaylistMusicasEntity(new PlaylistEntity(this.playlistId), new MusicaEntity(this.musicaId));
    }
}
