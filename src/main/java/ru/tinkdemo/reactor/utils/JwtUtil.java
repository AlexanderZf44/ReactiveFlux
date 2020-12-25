package ru.tinkdemo.reactor.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private static String secret;

    public static String extractUsername(String token) {

        return getClaimsFromToken(token)
                .getSubject();
    }

    public static boolean validateToken(String token) {

        return getClaimsFromToken(token)
                .getExpiration()
                .before(new Date());
    }

    public static Claims getClaimsFromToken(String token) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
