package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Playlist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Playlists")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "Id")
    private String id;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PlaylistMusicas",
            joinColumns = {@JoinColumn(name = "PlaylistId")},
            inverseJoinColumns = {@JoinColumn(name = "MusicaId")}
    )
    private List<MusicaEntity> musicas = new ArrayList<>();


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

    public void adicionaMusicasNaPlaylist(MusicaEntity musica){
        this.musicas.add(musica);
    }
}
