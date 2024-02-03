package dev.whyneet.authsystem.api.model;

import dev.whyneet.authsystem.database.model.User;

import java.io.Serializable;

public class RegisterResult implements Serializable {
    private PublicUser user;
    private String token;

    public RegisterResult(PublicUser user, String token) {
        this.user = user;
        this.token = token;
    }

    public PublicUser getUser() {
        return user;
    }

    public void setUser(PublicUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
