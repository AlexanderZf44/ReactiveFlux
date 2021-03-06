package ru.tinkdemo.reactor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.converters.MessageToDtoConverter;
import ru.tinkdemo.reactor.converters.MessageToEntityConverter;
import ru.tinkdemo.reactor.dto.MessageDto;
import ru.tinkdemo.reactor.repo.MessageRepository;
import ru.tinkdemo.reactor.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final MessageToDtoConverter toDtoConvertor;
    private final MessageToEntityConverter toEntityConvertor;

    /**
     * Получение списка приветственных сообщений из БД
     */
    public Flux<MessageDto> getMessagesList(Long start, Long count) {

        return repository.findAll()
                .skip(start)
                .take(count)
                .map(toDtoConvertor);
    }

    /**
     * Добавление приветственного сообщения в БД
     */
    public Mono<MessageDto> addMessage(MessageDto message) {

        return toEntityConvertor.apply(message)
                .flatMap(repository::save)
                .map(toDtoConvertor);
    }
}
