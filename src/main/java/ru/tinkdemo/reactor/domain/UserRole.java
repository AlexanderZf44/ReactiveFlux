package ru.tinkdemo.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    /**
     * Идентификатор роли
     */
    @Id
    private Long id;

    /**
     * Наименование ролей
     */
    private String name;

    /**
     * Описание ролей
     */
    private String description;
}
