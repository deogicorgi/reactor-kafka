package com.github.deogicorgi.reactive.common.message;

import com.github.deogicorgi.reactive.common.value.ProduceMessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * JSON String message 카프카 메시지
 */
@Getter
@Setter
public class KafkaBodyMessage extends AbstractKafkaMessage {

    // 요청 메시지
    private String message;

    public KafkaBodyMessage() {
        super.type = ProduceMessageType.Message;
    }

    @Override
    public String getRequestedMessage() {
        return this.message;
    }
}
