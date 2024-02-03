package dev.whyneet.authsystem.api.model;

import dev.whyneet.authsystem.database.model.User;

import java.io.Serializable;
import java.util.UUID;

public class PublicUser implements Serializable {
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
}
