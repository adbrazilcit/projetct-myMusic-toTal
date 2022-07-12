package com.ciandt.summit.bootcamp2022.infrastructure.adapters.entities;

import javax.persistence.*;

@Entity
@Table(name = "Artista")
public class ArtistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private String id;

    @OneToMany
    @Column(name = "Nome")
    private String nome;

    public ArtistaEntity(){
    }

    public ArtistaEntity(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}