package com.truenorth.vhslab.models;

public class TokenModel {

    private String token;

    private String username;

    public TokenModel() {
    }

    public TokenModel(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
