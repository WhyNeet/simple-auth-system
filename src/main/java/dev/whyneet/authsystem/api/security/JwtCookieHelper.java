package dev.whyneet.authsystem.api.security;

import jakarta.servlet.http.Cookie;

public class JwtCookieHelper {
    public static Cookie accessTokenCookie(String token) {
        Cookie cookie = new Cookie("access_token", token);

        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
