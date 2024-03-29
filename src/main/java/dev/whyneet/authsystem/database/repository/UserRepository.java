package dev.whyneet.authsystem.database.repository;

import dev.whyneet.authsystem.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User getByUsername(String username);
}
