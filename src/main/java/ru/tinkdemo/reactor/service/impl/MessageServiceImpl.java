package ru.tinkdemo.reactor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.Message;
import ru.tinkdemo.reactor.repo.MessageRepository;
import ru.tinkdemo.reactor.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    /**
     * Получение списка приветственных сообщений из БД
     */
    public Flux<Message> getMessagesList() {
        return repository.findAll();
    }

    /**
     * Добавление приветственного сообщения в БД
     */
    public Mono<Message> addMessage(Message message) {
        return repository.save(message);
    }
}
