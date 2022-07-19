package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.PlayList;
import com.ciandt.summit.bootcamp2022.domain.PlaylistMusicas;

import javax.persistence.*;

@Entity
@Table(name = "PlaylistMusicas")
public class PlaylistMusicasEntity {

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


}
