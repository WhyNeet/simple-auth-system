package dev.whyneet.authsystem.api.model.register;

import dev.whyneet.authsystem.api.validation.ValidUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterBody {
    @NotNull
    @Size(min = 2, max = 255)
    @ValidUsername
    private String username;

    @NotNull
    @Size(min = 1, max = 255)
    private String fullName;

    @Size(min = 8, max = 72)
    private String password;

    public RegisterBody(String username, String fullName, String password) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
