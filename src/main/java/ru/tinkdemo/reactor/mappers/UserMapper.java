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
        Long userId = row.get("user_id", Long.class);
        String userName = row.get("user_name", String.class);
        String userPassword = row.get("user_pass", String.class);

        Long roleId = row.get("role_id", Long.class);
        String roleName = row.get("role_name", String.class);
        String roleDescription = row.get("role_desc", String.class);

        Set<UserRole> roles = Stream
                .of(new UserRole(roleId, roleName, roleDescription))
                .collect(Collectors.toSet());

        User user = new User(userId, userName, userPassword, roles);

        return user;
    }

}
