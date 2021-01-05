package ru.tinkdemo.reactor.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.Message;
import ru.tinkdemo.reactor.dto.MessageDto;
import ru.tinkdemo.reactor.repo.MessageRepository;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MessageToEntityConvertor implements Function<MessageDto, Mono<Message>> {

    private final MessageRepository repository;

    /**
     * Конвертер для преобразования dto с фронта в сущность для БД
     */
    @Override
    public Mono<Message> apply(MessageDto messageDto) {

        return repository.findByData(messageDto.getData())
                .defaultIfEmpty(
                        Message.builder()
                                .data(messageDto.getData())
                                .build()
                );
    }
}
