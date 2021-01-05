package ru.tinkdemo.reactor.repo.custom.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.mappers.UserMapper;
import ru.tinkdemo.reactor.repo.custom.CustomUserRepository;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final DatabaseClient client;
    private final UserMapper mapper;

    /**
     * Кастомный запрос для получения пользователя по его username,
     * подгружает сущности role с отношением many-to-many
     */
    @Override
    public Mono<User> findByUsername(String username) {

        String query = "select u.id as user_id, u.username as user_name, u.password as user_pass, " +
                "r.id as role_id, r.name as role_name, r.description as role_desc " +
                "from usr u " +
                "left join user_role ur on u.id = ur.usr_id " +
                "left join role r on r.id = ur.role_id " +
                "where u.username = :username";

        return client.sql(query)
                .bind("username", username)
                .map(mapper)
                .all()
                .reduce(
                        (f, s) -> {
                            f.getRoles().addAll(s.getRoles());
                            return f;
                        }
                );
    }
}
