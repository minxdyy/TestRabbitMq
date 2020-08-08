package com.myy.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Component
@RequestMapping("/rabbit")
public class TestRabbitMq {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @ResponseBody
    @RequestMapping("/1")
    public String test1(){
        rabbitTemplate.convertAndSend("queuedemo","hello world");
        return "1";
    }
    @ResponseBody
    @RequestMapping("/2")
    public String test2(){
        rabbitTemplate.convertAndSend("topicExchange","key.1","key.1 success");
        rabbitTemplate.convertAndSend("topicExchange","key.1.2","key.1.2 success");
        return "2";
    }
    @ResponseBody
    @RequestMapping("/3")
    public String test3(){
        //rabbitTemplate.convertAndSend("fanoutExchange","","fanout exchange message");
        //rabbitTemplate.convertAndSend("fanoutExchange","","hass");
        rabbitTemplate.convertAndSend("fanoutExchange","","hello world");
        /*for(int i=0;i<15;i++){
            rabbitTemplate.convertAndSend("fanoutExchange","","hi");
        }*/
        return "3";
    }
    @ResponseBody
    @RequestMapping("/4")
    public String test4(){
        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setHeader("heads1","value1");
        String headsMessage="heads exchange success";
        Message msg=new Message(headsMessage.getBytes(),messageProperties);
        rabbitTemplate.convertAndSend("headsExchange","",msg);
        return "4";
    }
}
