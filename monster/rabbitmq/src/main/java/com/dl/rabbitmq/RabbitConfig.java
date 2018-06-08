package com.dl.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置类
 */

@Configuration
public class RabbitConfig
{
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
