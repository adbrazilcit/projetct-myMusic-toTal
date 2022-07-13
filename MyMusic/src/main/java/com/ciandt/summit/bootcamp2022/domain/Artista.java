package com.ciandt.summit.bootcamp2022.domain;

public class Artista {

    private String id;
    private String nome;

    public Artista(){
    }

    public Artista(String id, String nome) {
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
//    public Artista(ArtistaDTO artistaDTO){
//        this.id = artistaDTO.getId;
//        this.nome = artistaDTO.getNome;
//    }
//
//    public ArtistaDTO toArtistaDTO(){
//        return new ArtistaDTO(this.id, this.nome);
//    }
}

