package dev.whyneet.authsystem.api.model.common;

import dev.whyneet.authsystem.database.model.User;

import java.util.UUID;

public class PublicUser {
    private UUID id;
    private String username;
    private String fullName;

    public PublicUser(UUID id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    public static PublicUser fromUser(User user) {
        return new PublicUser(user.getId(), user.getUsername(), user.getFullName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
