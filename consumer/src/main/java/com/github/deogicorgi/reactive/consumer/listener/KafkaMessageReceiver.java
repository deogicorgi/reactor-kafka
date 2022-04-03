package com.github.deogicorgi.reactive.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;

import java.util.List;

/**
 * 카프카 리시버
 */
@Slf4j
@Component
public class KafkaMessageReceiver {

    /**
     * KafkaMessageReceiver가 생성될 때 모든 카프카 리시버 시작
     */
    public KafkaMessageReceiver(List<KafkaReceiver<Integer, String>> kafkaReceivers) {
        for (KafkaReceiver<Integer, String> receiver : kafkaReceivers) {
            this.start(receiver);
        }
    }

    public void start(KafkaReceiver<Integer, String> receiver) {
        receiver.receive().subscribe(record -> {
            log.info("Kafka Reciever result : Topic >> [{}], message >> [{}], Offset >> [{}]", record.topic(), record.value(), record.receiverOffset());
        });
    }
}
