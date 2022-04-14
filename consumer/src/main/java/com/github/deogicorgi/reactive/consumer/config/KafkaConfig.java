package com.github.deogicorgi.reactive.consumer.config;

import com.github.deogicorgi.reactive.consumer.config.model.KafkaReceiverProperty;
import com.github.deogicorgi.reactive.consumer.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties properties;

    // deogicorgi-topic-1 리시버
    @Bean("deogicorgiTopic1")
    public KafkaReceiver<Integer, String> uriMessageReceiver() throws Exception {
        Map.Entry<String, KafkaReceiverProperty> propertyEntry = properties.getProperty("deogicorgi-topic-1").orElse(null);

        if (ObjectUtils.isEmpty(propertyEntry)) {
            throw new Exception("property is null");
        }

        KafkaReceiverProperty property = propertyEntry.getValue();

        ReceiverOptions<Integer, String> receiverOptions =
                ReceiverOptions.<Integer, String>create(getConsumerProps(property))
                        .subscription(Collections.singleton(property.getTopic()));

        return KafkaReceiver.create(receiverOptions);
    }

    // deogicorgi-topic-2 리시버
    @Bean("deogicorgiTopic2")
    public KafkaReceiver<Integer, String> messageReceiver() throws Exception {
        Map.Entry<String, KafkaReceiverProperty> propertyEntry = properties.getProperty("deogicorgi-topic-2").orElse(null);

        if (ObjectUtils.isEmpty(propertyEntry)) {
            throw new Exception("property is null");
        }

        KafkaReceiverProperty property = propertyEntry.getValue();

        ReceiverOptions<Integer, String> receiverOptions =
                ReceiverOptions.<Integer, String>create(getConsumerProps(property))
                        .subscription(Collections.singleton(property.getTopic()));

        return KafkaReceiver.create(receiverOptions);
    }

    /******************************************************************
     ************************ Consumer Options ************************
     ******************************************************************/

    // 컨슈머 옵션
    private Map<String, Object> getConsumerProps(KafkaReceiverProperty property) {
        return new HashMap<>() {{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getHosts());
            put(ConsumerConfig.GROUP_ID_CONFIG, property.getGroupId());
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 1000);
        }};
    }
}
