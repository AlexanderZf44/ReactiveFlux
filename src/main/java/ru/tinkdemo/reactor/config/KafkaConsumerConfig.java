package ru.tinkdemo.reactor.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private static final String TOPIC = "some.topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9093";
    private static final String GROUP_ID = "some.group.id";
    private static final String CLIENT_ID = "some.client.id";
    private static final String OFFSET_RESET = "latest";
    private static final String MAX_POLL = "10";
    private static final String SECURITY_PROTOCOL = "SASL_PLAINTEXT";
    private static final String SASL_MECHANISM = "SCRAM-SHA-512";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "pass";

    @Bean
    public KafkaReceiver<String, String> kafkaReceiver() {

        Map<String, Object> consumerProps = new HashMap<>();
        /* Конфигурация для consumer */
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET);
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);
        consumerProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL);
        /* Конфигурация для security */
        consumerProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SECURITY_PROTOCOL);
        consumerProps.put(SaslConfigs.SASL_MECHANISM, SASL_MECHANISM);
        consumerProps.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" " +
                        "password=\"%s\";",
                USERNAME,
                PASSWORD
        ));
        /* Формирование объекта конфигураций */
        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.<String, String>create(consumerProps)
                        .subscription(Collections.singleton(TOPIC));

        /* Формирование объекта receiver */
        return KafkaReceiver.create(receiverOptions);
    }
}
