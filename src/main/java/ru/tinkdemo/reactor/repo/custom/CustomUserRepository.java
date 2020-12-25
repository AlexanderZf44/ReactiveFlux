package ru.tinkdemo.reactor.repo.custom;

import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.User;

public interface CustomUserRepository {
    Mono<User> findByUsername(String username);
}
