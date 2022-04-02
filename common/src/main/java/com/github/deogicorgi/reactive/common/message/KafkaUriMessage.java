package com.github.deogicorgi.reactive.common.message;

import com.github.deogicorgi.reactive.common.value.ProduceMessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * URI 카프카 메시지
 */
@Getter
@Setter
public class KafkaUriMessage extends AbstractKafkaMessage {

    // 요청 URI
    private String uri;

    public KafkaUriMessage() {
        super.type = ProduceMessageType.URI;
    }

    @Override
    public String getRequestedMessage() {
        return this.uri;
    }
}
