package ru.tinkdemo.reactor.convertors;

import org.springframework.stereotype.Component;
import ru.tinkdemo.reactor.domain.Message;
import ru.tinkdemo.reactor.dto.MessageDto;

import java.util.function.Function;

@Component
public class MessageToDtoConvertor implements Function<Message, MessageDto> {

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
