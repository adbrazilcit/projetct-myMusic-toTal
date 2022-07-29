package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception.MusicDetachedException;
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

    public PlaylistEntity(Playlist playlist) {
        this.id = playlist.getId();
    }

    public Playlist toPlayList() {
        return new Playlist(this.id);
    }

    public void adicionaMusicasNaPlaylist(MusicaEntity musica) {
        this.musicas.add(musica);
    }

    public void removeMusicasNaPlaylist(MusicaEntity musica) {
        if(this.musicas.contains(musica)){
            this.musicas.remove(musica);
        }else
            throw new MusicDetachedException("Música não tem vínculo com a playlist");
    }
}
