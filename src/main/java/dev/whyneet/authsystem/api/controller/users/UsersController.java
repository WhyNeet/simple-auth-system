package dev.whyneet.authsystem.api.controller.users;

import dev.whyneet.authsystem.api.model.common.PublicUser;
import dev.whyneet.authsystem.api.security.JwtService;
import dev.whyneet.authsystem.database.model.User;
import dev.whyneet.authsystem.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    private ResponseEntity<Object> me(@CookieValue("access_token") String accessToken) {
        String username = jwtService.extractUsername(jwtService.extractAllClaims(accessToken));

        User user = userRepository.getByUsername(username);

        return ResponseEntity.ok(PublicUser.fromUser(user));
    }
}
