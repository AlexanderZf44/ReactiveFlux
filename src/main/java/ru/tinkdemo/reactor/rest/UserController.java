package ru.tinkdemo.reactor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.service.UserService;
import ru.tinkdemo.reactor.utils.JwtUtil;

@RestController
@RequiredArgsConstructor
public class UserController {

    private static final ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Эндпоинт для проведения авторизации пользователей
     */
    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange exchange) {

        return exchange
                .getFormData()
                .flatMap(
                        credentials -> userService.findByUsername(
                                credentials.getFirst("username")
                        )
                                .cast(User.class)
                                .map(userDetails -> passwordEncoder.matches(
                                        credentials.getFirst("password"),
                                        userDetails.getPassword()
                                        ) ?
                                                ResponseEntity.ok(JwtUtil.generateToken(userDetails)) :
                                                UNAUTHORIZED
                                )
                                .defaultIfEmpty(UNAUTHORIZED)
                );
    }
}
