package com.github.deogicorgi.reactive.producer.service;

import com.github.deogicorgi.reactive.common.message.AbstractKafkaMessage;
import com.github.deogicorgi.reactive.common.model.KafkaProduceResult;
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

    public Mono<KafkaProduceResult> send(AbstractKafkaMessage message) {

        return producer.createOutbound()
                // 지정된 토픽으로 메시지 전송
                .send(Mono.just(new ProducerRecord<>(message.getTopic(), null, message.getRequestedMessage())))
                .then()
                // 에러 없이 전송이 완료 되었을 경우
                .thenReturn(new KafkaProduceResult(message))
                // 에러가 발생했을 경우
                .onErrorResume(e -> Mono.just(new KafkaProduceResult(message, e)));
    }
}
