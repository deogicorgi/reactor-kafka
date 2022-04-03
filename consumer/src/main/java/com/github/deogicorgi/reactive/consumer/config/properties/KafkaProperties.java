package com.github.deogicorgi.reactive.consumer.config.properties;

import com.github.deogicorgi.reactive.consumer.config.model.KafkaReceiverProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties("kafka")
public class KafkaProperties {

    // 카프카 호스트
    private String hosts;

    // 리시버 프로퍼티 맵
    private Map<String, KafkaReceiverProperty> receiver = new HashMap<>();

    public void setReceiver(Map<String, KafkaReceiverProperty> receivers) {
        this.receiver = receivers;
    }

    public Optional<Map.Entry<String, KafkaReceiverProperty>> getProperty(String key) {
        return this.receiver.entrySet()
                .stream().filter(entry -> entry.getValue().getName().equals(key))
                .findFirst();
    }
}
