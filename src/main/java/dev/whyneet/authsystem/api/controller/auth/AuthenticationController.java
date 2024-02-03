package dev.whyneet.authsystem.api.controller.auth;

import dev.whyneet.authsystem.api.exception.ApiError;
import dev.whyneet.authsystem.api.model.common.AuthResult;
import dev.whyneet.authsystem.api.model.common.PublicUser;
import dev.whyneet.authsystem.api.model.login.LoginBody;
import dev.whyneet.authsystem.api.model.register.RegisterBody;
import dev.whyneet.authsystem.api.security.JwtCookieHelper;
import dev.whyneet.authsystem.api.security.JwtService;
import dev.whyneet.authsystem.api.security.UserDetails;
import dev.whyneet.authsystem.database.model.User;
import dev.whyneet.authsystem.database.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

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
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(new ApiError(String.format("user with username @%s already exists.", user.getUsername()), ex.toString()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ApiError("an internal error occurred.", ex.toString()));
        }

        String token = jwtService.generateToken(new UserDetails(user.getUsername()));

        AuthResult registerResult = new AuthResult(PublicUser.fromUser(user), token);

        httpServletResponse.addCookie(JwtCookieHelper.accessTokenCookie(token));

        return ResponseEntity.ok(registerResult);
    }

    @PostMapping("/login")
    private ResponseEntity<Object> loginUser(@Valid @RequestBody LoginBody loginBody, HttpServletResponse httpServletResponse) {
        User user = userRepository.getByUsername(loginBody.getUsername());

        if (!passwordEncoder.matches(loginBody.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new ApiError("wrong password provided.", ""));
        }

        String token = jwtService.generateToken(new UserDetails(user.getUsername()));

        httpServletResponse.addCookie(JwtCookieHelper.accessTokenCookie(token));

        return ResponseEntity.ok(new AuthResult(PublicUser.fromUser(user), token));
    }
}
