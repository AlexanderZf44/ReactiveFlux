package ru.tinkoff.reactor.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkoff.reactor.domain.Message;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);

        Flux<Message> messageFlux = Flux
                .just(
                        "Hello, Spring!",
                        "Hello, Summer!",
                        "Hello, Autumn!",
                        "Hello, Winter!"
                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageFlux, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        String name = request.queryParam("client")
                .orElse("little hope");

        return ServerResponse
                .ok()
                .render("index", Map.of("client", name));
    }
}
