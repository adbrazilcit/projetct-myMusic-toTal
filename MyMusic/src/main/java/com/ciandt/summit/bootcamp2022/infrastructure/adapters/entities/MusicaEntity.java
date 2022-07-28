package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.Artista;
import com.ciandt.summit.bootcamp2022.domain.Musica;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Musicas")
public class MusicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ArtistaId")
    private ArtistaEntity artistas;

    @Column(name = "Nome")
    private String nome;

    public MusicaEntity() {
    }

    public MusicaEntity(String id, ArtistaEntity artistas, String nome) {
        this.id = id;
        this.artistas = artistas;
        this.nome = nome;
    }

    public MusicaEntity(Musica musica) {
        this.id = musica.getId();
        this.artistas = new ArtistaEntity(musica.getArtistaId());
        this.nome = musica.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicaEntity that = (MusicaEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Musica toMusica() {
        return new Musica(this.id, new Artista(artistas.getId(), artistas.getNome()), this.nome);
    }
}
