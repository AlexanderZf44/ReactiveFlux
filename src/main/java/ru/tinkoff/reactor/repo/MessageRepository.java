package ru.tinkoff.reactor.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.tinkoff.reactor.domain.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
