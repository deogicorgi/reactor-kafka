package com.github.deogicorgi.reactive.consumer.listener;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 비동기 카프카 메시지 중개 클래스
@Component
public class MessageBroker {

    private final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(10, 50, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));


    public void distribute() {

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(new MessageTask(i));
        }
    }


}
