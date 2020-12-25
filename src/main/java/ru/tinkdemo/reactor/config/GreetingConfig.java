package ru.tinkdemo.reactor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.tinkdemo.reactor.handlers.GreetingHandler;

@Configuration
public class GreetingConfig {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(
                        RequestPredicates.GET("/"),
                        greetingHandler::index
                );
    }
}
