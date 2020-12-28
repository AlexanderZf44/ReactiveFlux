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

/**
 * Класс для работы с JWT токенами авторизации
 */
@Component
public class JwtUtil {

    private static String SECRET;
    private static String EXPIRATION_DURABLE;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.SECRET = secret;
    }
    @Value("${jwt.expiration}")
    public void setExpirationDurable(String expirationDurable) {
        JwtUtil.EXPIRATION_DURABLE = expirationDurable;
    }

    /**
     * Получение username из токена авторизации
     */
    public static String extractUsername(String token) {

        return getClaimsFromToken(token)
                .getSubject();
    }

    /**
     * Проверка токена на актуальность
     */
    public static boolean validateToken(String token) {

        return getClaimsFromToken(token)
                .getExpiration()
                .after(new Date());
    }

    /**
     * Получение ключевых полей из токена
     */
    public static Claims getClaimsFromToken(String token) {
        String key = Base64.getEncoder().encodeToString(SECRET.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Генерация нового токена для пользователя,
     * время актуальности токена устанавливается в сутки
     */
    public static String generateToken(User user) {

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(
                "role",
                user.getRoles()
                        .stream()
                        .map(UserRole::getName)
                        .collect(Collectors.toList())
        );

        long expirationSeconds = Long.parseLong(EXPIRATION_DURABLE);
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

}
