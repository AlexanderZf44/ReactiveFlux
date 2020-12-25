package ru.tinkdemo.reactor.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.tinkdemo.reactor.domain.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
