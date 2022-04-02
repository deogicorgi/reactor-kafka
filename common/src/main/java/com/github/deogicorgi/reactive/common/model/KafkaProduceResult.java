package com.github.deogicorgi.reactive.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.deogicorgi.reactive.common.message.AbstractKafkaMessage;
import com.github.deogicorgi.reactive.common.value.ProduceMessageType;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 카프카 메시지 전송결과 클래스
 */
@Getter
public class KafkaProduceResult {

    // 메시지 전송 상태 - true : 전송완료, false : 전송실패
    private Boolean status = true;

    // 메시지 전송 토픽
    private String topic;

    // 요청받은 메시지 타입 (uri, message)
    private ProduceMessageType messageType;

    // 요청받은 메시지 - URI 또는 JSON String
    private String requestedMessage;

    // 에러 - 전송과정 중 발생된 에러, 전송완료 일 경우 null
    @JsonIgnore
    private Throwable error = null;

    // 에러 메시지 - 전송과정 중 발생된 에러, 전송완료 일 경우 null
    private String errorMessage = null;

    // 메시지를 요청받은 시간
    private LocalDateTime requestedAt;

    // 메시지를 처리한 시간
    private LocalDateTime producedAt;

    public KafkaProduceResult(AbstractKafkaMessage message) {
        this.setRequestedMessage(message);
    }

    public KafkaProduceResult(AbstractKafkaMessage message, Throwable e) {
        this.setRequestedMessage(message);
        this.status = false;
        this.error = e;
        this.errorMessage = e.getMessage();
        this.producedAt = null;
    }

    public Boolean hasError() {
        return error != null;
    }

    private void setRequestedMessage(AbstractKafkaMessage requestedMessage) {
        this.topic = requestedMessage.getTopic();
        this.messageType = requestedMessage.getType();
        this.requestedMessage = requestedMessage.getRequestedMessage();
        this.producedAt = LocalDateTime.now();
        this.requestedAt = requestedMessage.getRequestedAt();
    }

}
