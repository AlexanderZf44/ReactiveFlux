package ru.tinkdemo.reactor.mappers;

import io.r2dbc.spi.Row;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.domain.UserRole;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMapper implements BiFunction<Row, Object, User> {

    /**
     * Маппер для конвертации данных из sql запроса в объект пользователя
     */
    @Override
    public User apply(Row row, Object o) {

        Set<UserRole> roles = Stream
                .of(
                        UserRole.builder()
                                .id(row.get("role_id", Long.class))
                                .name(row.get("role_name", String.class))
                                .description(row.get("role_desc", String.class))
                                .build()
                )
                .collect(Collectors.toSet());

        return User.builder()
                .id(row.get("user_id", Long.class))
                .username(row.get("user_name", String.class))
                .password(row.get("user_pass", String.class))
                .roles(roles)
                .build();
    }

}
