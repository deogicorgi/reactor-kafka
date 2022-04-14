package com.github.deogicorgi.reactive.consumer.listener;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void test() {
        if (true) {
            throw new RuntimeException("test메서드에서 발생");
        }
    }
}
