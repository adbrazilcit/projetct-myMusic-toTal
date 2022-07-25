package com.ciandt.summit.bootcamp2022.config;

public class User {
    private UserData data;

    public User(UserData data) {
        this.data = data;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
