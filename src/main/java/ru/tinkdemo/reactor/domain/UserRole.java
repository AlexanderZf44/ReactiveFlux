package ru.tinkdemo.reactor.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("role")
public class UserRole {

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
