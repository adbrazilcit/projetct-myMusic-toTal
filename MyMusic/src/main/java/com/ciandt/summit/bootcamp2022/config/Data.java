package com.ciandt.summit.bootcamp2022.config;

public class Data {
    private String name;
    private String token;

    public Data(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
