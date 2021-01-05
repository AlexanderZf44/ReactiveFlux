package ru.tinkdemo.reactor.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.dto.MessageDto;

public interface MessageService {

    Flux<MessageDto> getMessagesList(Long start, Long count);

    Mono<MessageDto> addMessage(MessageDto message);
}
