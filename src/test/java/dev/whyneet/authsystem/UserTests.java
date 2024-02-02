package dev.whyneet.authsystem;

import dev.whyneet.authsystem.database.model.User;
import dev.whyneet.authsystem.database.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void canCreateUser() {
        User user = new User("whyneet", "WhyNeet", "123");

        userRepository.save(user);

        Assertions.assertNotNull(user.getId());
    }
}
