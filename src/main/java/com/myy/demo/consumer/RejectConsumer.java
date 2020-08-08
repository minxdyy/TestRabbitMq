package com.myy.demo.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "rejectQueue")
public class RejectConsumer {
    @RabbitHandler
    public void process(String s, Channel channel,Message message) throws Exception{

        log.info(s);
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);

    }
}
