package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Musica;

import javax.persistence.*;

@Entity
@Table(name = "Musicas")
public class MusicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private String id;
    @ManyToOne
    @Column(name="ArtistaId")
    private Artista artistaId;
    @Column(name="Nome")
    private String nome;

    public MusicaEntity() {
    }

    public MusicaEntity(String id, Artista artistaId, String nome) {
        this.id = id;
        this.artistaId = artistaId;
        this.nome = nome;
    }

    public Musica toMusica(){
        return new Musica(this.id, this.artistaId, this.nome);
    }
}
