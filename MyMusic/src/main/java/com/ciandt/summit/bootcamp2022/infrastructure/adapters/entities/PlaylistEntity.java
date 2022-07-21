package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Playlist;

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

    public Playlist toPlayList(){
        return new Playlist(this.id);
    }

    public PlaylistEntity(Playlist playlist){
        this.id = playlist.getId();
    }
}
