package com.github.deogicorgi.reactive.producer.message;

import com.github.deogicorgi.reactive.producer.value.ProduceMessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * JSON String message 카프카 메시지
 */
@Getter
@Setter
public class KafkaBodyProduceMessage extends AbstractKafkaProduceMessage {

    // 요청 메시지
    private String message;

    public KafkaBodyProduceMessage() {
        super.type = ProduceMessageType.Message;
    }

    @Override
    public String getRequestedMessage() {
        return this.message;
    }
}
