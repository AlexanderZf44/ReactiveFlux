package ru.tinkdemo.reactor.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> index(ServerRequest request) {
        String name = request.queryParam("client")
                .orElse("little hope");

        return ServerResponse
                .ok()
                .render("index", Map.of("client", name));
    }
}
