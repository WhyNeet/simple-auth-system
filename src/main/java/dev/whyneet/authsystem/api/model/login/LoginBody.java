package dev.whyneet.authsystem.api.model.login;

import dev.whyneet.authsystem.api.validation.ValidUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginBody {
    @NotNull
    @Size(min = 2, max = 255)
    @ValidUsername
    private String username;

    @NotNull
    @Size(min = 8, max = 72)
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
