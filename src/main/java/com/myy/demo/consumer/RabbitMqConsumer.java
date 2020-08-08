package com.myy.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "queuedemo")
@Slf4j
public class RabbitMqConsumer {
    @RabbitHandler
    public void process(String msg){
        log.error(msg);
    }
}
