package com.myy.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/callback")
@Slf4j
public class TestRabbitMqCallBack  {
    @Autowired
    private RabbitTemplate rabbitTemplate;
//implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback
/*    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            log.info("消息发送确认成功");
        } else {
            log.info("消息发送失败:" +s);
        }
    }*/

/*    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info(message.getBody().toString());
        log.info(i+"");
        log.info( s);
        log.info( s1);
        log.info( s2);

    }*/

    @RequestMapping("/1")
    @ResponseBody
    public String test1(){
        rabbitTemplate.convertAndSend("fanoutExchange","","reject message ");
        return "3";
    }
}
