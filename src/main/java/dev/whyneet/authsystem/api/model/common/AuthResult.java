package dev.whyneet.authsystem.api.model.common;

public class AuthResult {
    private PublicUser user;
    private String token;

    public AuthResult(PublicUser user, String token) {
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

