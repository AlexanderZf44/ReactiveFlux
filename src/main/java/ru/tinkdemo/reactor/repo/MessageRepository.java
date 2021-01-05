package ru.tinkdemo.reactor.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {

    Mono<Message> findByData(String data);
}
