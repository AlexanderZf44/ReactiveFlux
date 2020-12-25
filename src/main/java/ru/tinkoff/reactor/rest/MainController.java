package ru.tinkoff.reactor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkoff.reactor.domain.Message;
import ru.tinkoff.reactor.service.MessageService;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class MainController {

    private final MessageService messageService;

    @GetMapping("/hello")
    public Flux<Message> hello(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {

        return Flux
                .just(
                        "Hello, Spring!",
                        "Hello, Summer!",
                        "Hello, Autumn!",
                        "Hello, Winter!"
                )
                .skip(start)
                .take(count)
                .map(Message::new);
    }

    @GetMapping("/list")
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {

        return messageService.getMessagesList(); 
    }

    @PostMapping("/add")
    public Mono<Message> list(@RequestBody Message requestMessage) {

        return messageService.addMessage(requestMessage);
    }
}
