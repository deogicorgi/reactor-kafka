package com.github.deogicorgi.reactor.kafka.producer.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = KafkaUriProduceMessage.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = KafkaUriProduceMessage.class, name = "uri"),
    @JsonSubTypes.Type(value = KafkaBodyProduceMessage.class, name = "message")
})
public abstract class AbstractKafkaProduceMessage {

    protected String topic;
    protected String type;
    protected LocalDateTime requestedAt;

    public abstract String getRequestedMessage();

}
