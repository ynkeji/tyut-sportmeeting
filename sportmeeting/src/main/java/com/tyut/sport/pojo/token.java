package com.tyut.sport.pojo;

import javax.persistence.Entity;

@Entity
public class token {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    private int useful;
}
