package com.github.deogicorgi.reactive.producer.web.service;

import com.github.deogicorgi.reactive.producer.message.AbstractKafkaProduceMessage;
import com.github.deogicorgi.reactive.producer.model.KafkaProduceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 프로듀싱 서비스
 * Kafka 프로듀싱 전 로직 처리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProduceService {

    private final KafkaService kafkaService;

    public Mono<KafkaProduceResult> produceMessage(AbstractKafkaProduceMessage message) {
        return kafkaService.send(message)
                .map(produceResult -> {
                    if (produceResult.hasError()) {
                        // TODO 카프카 프로듀싱 실패일 경우 처리
                        // ex ) 처리하지못한 요청을 몽고등에 저장 후 재시도, 로깅 등등
                        log.error("Kafka produce error : {}", produceResult.getErrorMessage());
                    }
                    return produceResult;
                });
    }
}
