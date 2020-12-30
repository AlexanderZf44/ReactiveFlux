package ru.tinkdemo.reactor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import ru.tinkdemo.reactor.service.KafkaConsumerService;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final KafkaReceiver<String, String> kafkaConsumer;

    /**
     * Метод чтения сообщения из очереди кафки
     */
    @Override
    public void readMessage() {

        Flux<ReceiverRecord<String, String>> kafkaFlux = kafkaConsumer.receive();

        kafkaFlux.checkpoint("Start reading messages")
                .log()
                .doOnNext(record -> {
                    System.out.println(record); // Проводим какие-либо действия с записью
                    record.receiverOffset().acknowledge(); // Подтверждаем, что запись была получена
                })
                .checkpoint("Finish reading messages");
    }
}
