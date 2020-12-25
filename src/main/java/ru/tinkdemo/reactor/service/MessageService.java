package ru.tinkdemo.reactor.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.Message;

public interface MessageService {

    Flux<Message> getMessagesList();

    Mono<Message> addMessage(Message message);
}
