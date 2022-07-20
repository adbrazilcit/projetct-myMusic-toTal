package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;
import com.ciandt.summit.bootcamp2022.domain.PlayList;

import javax.persistence.*;

@Entity
@Table(name = "Playlists")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "Id")
    private String id;

    public PlaylistEntity() {
    }

    public PlaylistEntity(String id) {
        this.id = id;

    }

    public PlayList toPlayList(){
        return new PlayList(this.id);
    }
}
