package ru.tinkdemo.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * Идентификатор сообщения для пользователя
     */
    @Id
    private Long id;

    /**
     * Сообщение для пользователя
     */
    private String data;

    public Message(String data) {
        this.id = null;
        this.data = data;
    }
}
