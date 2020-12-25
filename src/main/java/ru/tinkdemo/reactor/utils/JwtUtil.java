package ru.tinkdemo.reactor.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.domain.UserRole;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private static String secret;
    @Value("${jwt.expiration}")
    private static String expirationDurable;

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

    public static String generateToken(User user) {

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(
                "role",
                user.getRoles()
                        .stream()
                        .map(UserRole::getName)
                        .collect(Collectors.toList())
        );

        long expirationSeconds = Long.parseLong(expirationDurable);
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

}
