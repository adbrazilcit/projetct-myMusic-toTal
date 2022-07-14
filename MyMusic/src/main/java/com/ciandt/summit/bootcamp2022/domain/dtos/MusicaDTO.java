package com.ciandt.summit.bootcamp2022.domain.dtos;

import com.ciandt.summit.bootcamp2022.domain.Artista;

public class MusicaDTO {
       String id;
       String nome;

       Artista artista;


       public Artista getArtista() {
              return artista;
       }

       public void setArtista(Artista artista) {
              this.artista = artista;
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

       public MusicaDTO(String id, String nome, Artista artista) {
              this.id = id;
              this.nome = nome;
              this.artista = artista;
       }
}
