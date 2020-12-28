package ru.tinkdemo.reactor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.repo.custom.CustomUserRepository;
import ru.tinkdemo.reactor.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CustomUserRepository userRepository;

    /**
     * Получение пользователя по username
     */
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .cast(UserDetails.class);
    }
}
