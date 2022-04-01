package com.github.deogicorgi.reactor.kafka.producer.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaUriProduceMessage extends AbstractKafkaProduceMessage {
    private String uri;

    @Override
    public String getRequestedMessage() {
        return this.uri;
    }
}
