package ru.tinkdemo.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@Data
@SuperBuilder
@Table("role")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {

    /**
     * Наименование ролей
     */
    private String name;

    /**
     * Описание ролей
     */
    private String description;
}
