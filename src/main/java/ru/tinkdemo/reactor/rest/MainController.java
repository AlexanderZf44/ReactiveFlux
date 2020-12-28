package ru.tinkdemo.reactor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.domain.Message;
import ru.tinkdemo.reactor.service.MessageService;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class MainController {

    private final MessageService messageService;

    /**
     * Эндпоинт для получения списка приветственных сообщений для пользователя
     */
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

    /**
     * Эндпоинт для получения списка приветственных сообщений из БД
     */
    @GetMapping("/list")
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {

        return messageService.getMessagesList();
    }

    /**
     * Эндпоинт для добавления приветственного сообщения в БД
     */
    @PostMapping("/add")
    public Mono<Message> list(@RequestBody Message requestMessage) {

        return messageService.addMessage(requestMessage);
    }
}
