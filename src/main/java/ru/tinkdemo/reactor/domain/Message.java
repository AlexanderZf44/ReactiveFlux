package ru.tinkdemo.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Message {

    @Id
    private final Long id;
    private final String data;

    public Message(String data) {
        this.id = null;
        this.data = data;
    }
}
