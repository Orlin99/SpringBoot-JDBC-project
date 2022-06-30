package org.example.Services.Models;

public class JWTUser {
    public final String nickname;
    public final String token;

    public JWTUser(String nickname, String token) {
        this.nickname = nickname;
        this.token = token;
    }
}
