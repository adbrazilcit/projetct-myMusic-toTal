package com.ciandt.summit.bootcamp2022.config;

public class Data {
    private String nome;
    private String token;

    public Data(String nome, String token) {
        this.nome = nome;
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }
}
