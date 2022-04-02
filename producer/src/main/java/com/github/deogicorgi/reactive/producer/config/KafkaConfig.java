package com.github.deogicorgi.reactive.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Kafka 설정
 */
@Configuration
public class KafkaConfig {

    private String host;
    private String groupId;

    /******************************************************************
     ************************ Producer Options ************************
     ******************************************************************/

    @Bean("kafkaSender")
    public KafkaSender<String, Object> kafkaSender() {
        SenderOptions<String, Object> senderOptions = SenderOptions.create(getProducerProps());
        senderOptions.scheduler(Schedulers.parallel());
        senderOptions.closeTimeout(Duration.ofSeconds(5));

        return KafkaSender.create(senderOptions);
    }

    private Map<String, Object> getProducerProps() {
        return new HashMap<>() {{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 2000);
        }};
    }
}
