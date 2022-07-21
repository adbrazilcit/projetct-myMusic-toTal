package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PlaylistMusicas")
@IdClass(PlayListMusicasId.class)
public class PlaylistMusicasEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="PlaylistId")
    private PlaylistEntity playlistId;

    @Id
    @ManyToOne
    @JoinColumn(name="MusicaId")
    private MusicaEntity musicaId;


    public PlaylistMusicasEntity() {
    }

    public PlaylistMusicasEntity(PlaylistEntity playlistId, MusicaEntity musicaId) {
        this.playlistId = playlistId;
        this.musicaId = musicaId;
    }

    public PlaylistMusicas toPlaylistMusicas(){
        return new PlaylistMusicas();
    }
}
