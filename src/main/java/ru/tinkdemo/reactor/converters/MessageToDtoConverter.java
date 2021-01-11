package ru.tinkdemo.reactor.converters;

import org.springframework.stereotype.Component;
import ru.tinkdemo.reactor.domain.Message;
import ru.tinkdemo.reactor.dto.MessageDto;

import java.util.function.Function;

@Component
public class MessageToDtoConverter implements Function<Message, MessageDto> {

    /**
     * Конвертер для преобразования сущности в dto для фронта
     */
    @Override
    public MessageDto apply(Message message) {

        return MessageDto.builder()
                .data(message.getData())
                .build();
    }
}
