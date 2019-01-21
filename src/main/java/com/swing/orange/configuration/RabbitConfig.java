package com.swing.orange.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    final static String queueName = "hello";

    @Bean
    public Queue Queue() {
        return new Queue(queueName, false);
    }
}
