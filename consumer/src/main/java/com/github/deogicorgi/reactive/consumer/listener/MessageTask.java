package com.github.deogicorgi.reactive.consumer.listener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageTask implements Runnable {

    private TestService service = new TestService();

    private int i;

    public MessageTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(5);
            log.info("{}", i);
            service.test();

        } catch (Exception e) {
            log.error("run에서 잡은거야! {}", e.getMessage());
            throw new RuntimeException(Thread.currentThread().getName(), e);
        }
    }
}
