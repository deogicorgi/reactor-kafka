package com.github.deogicorgi.reactor.kafka.producer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.deogicorgi.reactor.kafka.producer.message.AbstractKafkaProduceMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KafkaProduceResult {
    private Boolean status = true;
    private String message;
    @JsonIgnore
    private Throwable error = null;
    private String errorMessage = null;
    private LocalDateTime requestedAt;
    private LocalDateTime producedAt;

    public KafkaProduceResult(AbstractKafkaProduceMessage message) {
        this.setMessage(message);
    }

    public KafkaProduceResult(AbstractKafkaProduceMessage message, Throwable e) {
        this.setMessage(message);
        this.status = false;
        this.error = e;
        this.errorMessage = e.getMessage();
    }

    public Boolean hasError() {
        return error != null;
    }

    private void setMessage(AbstractKafkaProduceMessage message) {
        this.message = message.getRequestedMessage();
        this.producedAt = LocalDateTime.now();
        this.requestedAt = message.getRequestedAt();
    }

}
