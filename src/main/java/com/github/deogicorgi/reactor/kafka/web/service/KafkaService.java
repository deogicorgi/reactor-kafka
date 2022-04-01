package com.github.deogicorgi.reactor.kafka.web.service;

import com.github.deogicorgi.reactor.kafka.producer.message.AbstractKafkaProduceMessage;
import com.github.deogicorgi.reactor.kafka.producer.message.KafkaUriProduceMessage;
import com.github.deogicorgi.reactor.kafka.producer.model.KafkaProduceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;

/**
 * 카프카 서비스
 * 실제 카프카로 메시지 프로듀싱
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaSender<String, Object> producer;

    public Mono<KafkaProduceResult> send(AbstractKafkaProduceMessage message) {
        return producer.createOutbound()
                .send(Mono.just(new ProducerRecord<>(message.getTopic(), null, message.getRequestedMessage())))  // 해당 topic으로 message 전송
                .then()
                .map(ret -> new KafkaProduceResult(message))
                .onErrorResume(e -> Mono.just(new KafkaProduceResult(message, e)));
    }

}
