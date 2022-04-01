package com.github.deogicorgi.reactor.kafka.producer.message;

import com.github.deogicorgi.reactor.kafka.producer.values.ProduceMessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * URI 카프카 메시지
 */
@Getter
@Setter
public class KafkaUriProduceMessage extends AbstractKafkaProduceMessage {

    // 요청 URI
    private String uri;

    public KafkaUriProduceMessage () {
        super.type = ProduceMessageType.URI;
    }

    @Override
    public String getRequestedMessage() {
        return this.uri;
    }
}
