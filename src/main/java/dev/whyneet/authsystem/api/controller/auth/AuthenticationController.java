package dev.whyneet.authsystem.api.controller.auth;

import dev.whyneet.authsystem.api.exception.ApiError;
import dev.whyneet.authsystem.api.model.PublicUser;
import dev.whyneet.authsystem.api.model.RegisterBody;
import dev.whyneet.authsystem.api.model.RegisterResult;
import dev.whyneet.authsystem.api.security.JwtCookieHelper;
import dev.whyneet.authsystem.api.security.JwtService;
import dev.whyneet.authsystem.api.security.UserDetails;
import dev.whyneet.authsystem.database.model.User;
import dev.whyneet.authsystem.database.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    private ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterBody registerBody, HttpServletResponse httpServletResponse) {
        User user = new User(registerBody.getUsername(), registerBody.getFullName(), passwordEncoder.encode(registerBody.getPassword()));

        try {
            userRepository.save(user);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body(new ApiError(String.format("user with username @%s already exists.", user.getUsername()), ex.toString()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiError("an internal error occurred.", ex.toString()));
        }

        String token = jwtService.generateToken(new UserDetails(user.getUsername()));

        RegisterResult registerResult = new RegisterResult(PublicUser.fromUser(user), token);

        httpServletResponse.addCookie(JwtCookieHelper.accessTokenCookie(token));

        return ResponseEntity.ok(registerResult);
    }
}
