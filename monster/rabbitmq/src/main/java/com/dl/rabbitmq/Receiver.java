package com.dl.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */

@Component
@RabbitListener(queues = "hello")//对hello队列的监听
public class Receiver {
    @RabbitHandler //指定对消息的处理方式
    public void process(String hello){
        System.out.println("Receiver : " + hello);
    }
}
