package ru.tinkdemo.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    /**
     * Идентификатор сущности в БД
     */
    @Id
    private Long id;
}
