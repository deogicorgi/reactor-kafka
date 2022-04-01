package com.github.deogicorgi.reactor.kafka.producer.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deogicorgi.reactor.kafka.exception.ProducerServiceException;

public abstract class AbstractKafkaProduceMessage {

    protected String message;

    public void setMessage(Object message) throws ProducerServiceException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.message = objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            throw new ProducerServiceException(e);
        }

    }
}
