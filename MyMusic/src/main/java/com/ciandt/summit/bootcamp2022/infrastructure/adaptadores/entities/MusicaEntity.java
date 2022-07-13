package com.ciandt.summit.bootcamp2022.infrastructure.adaptadores.entities;

import com.ciandt.summit.bootcamp2022.domain.Artista;
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
    @JoinColumn(name="ArtistaId")
    private ArtistaEntity artistas;
    @Column(name="Nome")
    private String nome;

    public MusicaEntity() {
    }

    public MusicaEntity(String id, ArtistaEntity artistas, String nome) {
        this.id = id;
        this.artistas = artistas;
        this.nome = nome;
    }

    public Musica toMusica(){
        return new Musica(this.id, new Artista(artistas.getId(),artistas.getNome()), this.nome);
    }
}
