package ru.tinkdemo.reactor.mappers;

import io.r2dbc.spi.Row;
import ru.tinkdemo.reactor.domain.User;
import ru.tinkdemo.reactor.domain.UserRole;

import java.util.Set;
import java.util.function.BiFunction;

public class UserMapper implements BiFunction<Row, Object, User> {

    @Override
    public User apply(Row row, Object o) {
        Long userId = row.get("user_id", Long.class);
        String userName = row.get("user_name", String.class);
        String userPassword = row.get("user_pass", String.class);

        Long roleId = row.get("role_id", Long.class);
        String roleName = row.get("role_name", String.class);
        String roleDescription = row.get("role_desc", String.class);

        UserRole role = new UserRole(roleId, roleName, roleDescription);

        User user = new User(userId, userName, userPassword, Set.of(role));

        return user;
    }

}
