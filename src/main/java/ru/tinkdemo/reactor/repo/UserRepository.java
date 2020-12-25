package ru.tinkdemo.reactor.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.repo.custom.CustomUserRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long>, CustomUserRepository {
}
