package com.example.demo.receivers;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyReceiver {

    Logger logger = LoggerFactory.getLogger(MyReceiver.class);

    @Bean(name = "received")
    public Consumer<String> input() {
        return str -> logger.info("Received: {}", str);
    }
}