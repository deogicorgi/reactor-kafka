package com.github.deogicorgi.reactive.consumer.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 카프카 리시버 설정 프로퍼티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaReceiverProperty {

    // 리시버 이름
    private String name;

    // 담당할 토픽
    private String topic;

    // 리시버 그룹 아이디
    private String groupId;
}
