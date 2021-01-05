package ru.tinkdemo.reactor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tinkdemo.reactor.dto.MessageDto;
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
    public Flux<MessageDto> hello(@RequestParam(defaultValue = "0") Long start,
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
                .map(MessageDto::new);
    }

    /**
     * Эндпоинт для получения списка приветственных сообщений из БД
     */
    @GetMapping("/list")
    public Flux<MessageDto> list(@RequestParam(defaultValue = "0") Long start,
                                 @RequestParam(defaultValue = "3") Long count) {

        return messageService.getMessagesList(start, count);
    }

    /**
     * Эндпоинт для добавления приветственного сообщения в БД
     */
    @PostMapping("/add")
    public Mono<MessageDto> list(@RequestBody MessageDto requestMessage) {

        return messageService.addMessage(requestMessage);
    }
}
