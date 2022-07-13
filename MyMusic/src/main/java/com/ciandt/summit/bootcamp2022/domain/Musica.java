package com.ciandt.summit.bootcamp2022.domain;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicaDTO;

public class Musica {
    private String id;
    private Artista artistaId;
    private String nome;

    public Musica() {
    }

    public Musica(String id, Artista artistaId, String nome) {
        this.id = id;
        this.artistaId = artistaId;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Artista getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Artista artistaId) {
        this.artistaId = artistaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id='" + id + '\'' +
                ", artistaId=" + artistaId +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Musica(MusicaDTO musicaDTO){
        this.id = musicaDTO.getId();
        this.artistaId = musicaDTO.getArtista();
        this.nome = musicaDTO.getNome();
    }

//    public MusicaDTO toMusicaDTO(){
//
//        return new MusicaDTO(this.id, this.nome,this.artistaId);
//    }
}
