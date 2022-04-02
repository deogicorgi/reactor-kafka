package com.github.deogicorgi.reactive.producer.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.deogicorgi.reactive.producer.value.ProduceMessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 카프카 메시지 베이스
 * 프로듀서 내 에러 발생시 처리를 쉽게하기 위해 URI 형태와 Message 형태로 나눔
 */
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = KafkaUriProduceMessage.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KafkaUriProduceMessage.class, names = {"uri", "Uri", "URI"}),
        @JsonSubTypes.Type(value = KafkaBodyProduceMessage.class, names = {"message", "Message", "MESSAGE"})
})
public abstract class AbstractKafkaProduceMessage {

    // 요청 토픽
    protected String topic;

    // 메시지 타입 (uri , message)
    protected ProduceMessageType type;

    // 요청 시간
    protected LocalDateTime requestedAt;

    public abstract String getRequestedMessage();

}
