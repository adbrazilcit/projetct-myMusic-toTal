package com.ciandt.summit.bootcamp2022.config;

public class UserData {
    private String name;
    private String token;

    public UserData(String name, String token) {
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
