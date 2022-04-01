package com.github.deogicorgi.reactor.kafka.producer.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaBodyProduceMessage extends AbstractKafkaProduceMessage {
    private String message;

    @Override
    public String getRequestedMessage() {
        return this.message;
    }
}
