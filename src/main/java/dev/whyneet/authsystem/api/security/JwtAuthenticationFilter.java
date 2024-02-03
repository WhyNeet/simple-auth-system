package dev.whyneet.authsystem.api.security;

import dev.whyneet.authsystem.database.model.User;
import dev.whyneet.authsystem.database.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("jwt filtering");

        final Cookie[] cookies = request.getCookies();

        Optional<String> optionalAccessToken = Arrays.stream(cookies == null ? new Cookie[0] : cookies)
                .filter(cookie -> Objects.equals(cookie.getName(), "access_token"))
                .findAny().map(Cookie::getValue).or(() -> {
                    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

                    if (authorization == null || !authorization.startsWith("Bearer: ")) return Optional.empty();

                    return Optional.of(authorization.substring(8));
                });

        if (optionalAccessToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final String accessToken = optionalAccessToken.get();
        final Claims accessTokenClaims = jwtService.extractAllClaims(accessToken);

        if (jwtService.isTokenExpired(accessTokenClaims)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String username = jwtService.extractUsername(accessTokenClaims);

        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
