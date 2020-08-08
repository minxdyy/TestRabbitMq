package com.myy.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    private static final  String topicExchange="topicExchange";
    private static final  String topicRoutingkey="key.#";
    private static final  String fanoutExchange="fanoutExchange";
    private static final  String headsExchange="headsExchange";
    private static final  String deadExchange="deadExchange";
    private static final String deadRoutingkey="deadkey";
    @Bean
    public FanoutExchange fanoutExchange(){
        return  new FanoutExchange(fanoutExchange,true,false);
    }
    @Bean
    public DirectExchange deadExchange(){
        return  new DirectExchange(deadExchange,true,false);
    }
    @Bean
    public Queue maxLengthQueue(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-max-length",10);
        map.put("x-dead-letter-exchange","deadExchange");
        map.put("x-dead-letter-routing-key","deadkey");
        return  new Queue("maxLengthQueue",true,false,false,map);
    }
    @Bean
    public Queue messageTTlQueue(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-message-ttl",10000);
        map.put("x-dead-letter-exchange","deadExchange");
        map.put("x-dead-letter-routing-key","deadkey");
        return  new Queue("messageTTlQueue",true,false,false,map);
    }
    @Bean
    public Queue rejectQueue(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-dead-letter-exchange","deadExchange");
        map.put("x-dead-letter-routing-key","deadkey");
        return  new Queue("rejectQueue",true,false,false,map);
    }
    @Bean
    public Binding binding9(){
        return BindingBuilder.bind(rejectQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding binding8(){
        return BindingBuilder.bind(messageTTlQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding binding6(){
        return BindingBuilder.bind(maxLengthQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding binding7(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(deadRoutingkey);
    }

    @Bean
    public TopicExchange topicExchange(){
        return  new TopicExchange(topicExchange,true,false);
    }

    @Bean
    public HeadersExchange headersExchange(){
        return  new HeadersExchange(headsExchange,true,false);
    }
    @Bean
    public Queue queue1(){
        return  new Queue("queue1",true,false,false);
    }
    @Bean
    public Queue queue2(){
        return  new Queue("queue2",true,false,false);
    }
    @Bean
    public Queue fanoutqueue1(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-max-length",10);
        map.put("x-max-length-bytes",20);
        map.put("x-dead-letter-exchange","deadExchange");
        Queue queue=new Queue("fanoutqueue1",true,false,false,map);
        return  queue;

    }
    @Bean
    public Queue fanoutqueue2(){
        return  new Queue("fanoutqueue2",true,false,false);
    }
    @Bean
    public Queue headsqueue1(){
        return  new Queue("headsqueue1",true,false,false);
    }
    @Bean
    public Queue deadQueue(){

        return  new Queue("deadQueue",true,false,false);
    }
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(topicExchange()).with(topicRoutingkey);
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(topicExchange()).with(topicRoutingkey);
    }
    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(fanoutqueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding binding4(){
        return BindingBuilder.bind(fanoutqueue2()).to(fanoutExchange());
    }
    @Bean
    public Binding binding5(){
        Map<String,Object> map=new HashMap<>();
        //map.put("x-match","any");
        map.put("heads1","value1");
        map.put("heads2","value2");
        return BindingBuilder.bind(headsqueue1()).to(headersExchange()).whereAny(map).match();
    }


}





    /*@Bean
    public Queue queue(){
        return  new Queue("queuedemo",true,false,false);
    }*/
    /* @Bean
    public DirectExchange directExchange(){
        return  new DirectExchange(directexchange,true,false);
    }*/
    /*@Bean
    public Queue queue3(){
        return  new Queue("queue3",true,false,false);
    }
    @Bean
    public Queue queue4(){
        return  new Queue("queue4",true,false,false);
    }*/
   /* @Bean
    public Queue queuedirct1(){
        return  new Queue("queuedirct1",true,false,false);
    }
    @Bean
    public Queue queuedirct2(){
        return  new Queue("queuedirct2",true,false,false);
    }
    @Bean
    public Queue queuedirct3(){
        return  new Queue("queuedirct3",true,false,false);
    }*/


   /* @Bean
    public Binding binding3(){
        return BindingBuilder.bind(queue3()).to(topicExchange()).with(routingkey1);
    }*/
    /*@Bean
    public Binding binding4(){
        return BindingBuilder.bind(queuedirct1()).to(directExchange()).with(routingkey);
    }
    @Bean
    public Binding binding5(){
        return BindingBuilder.bind(queuedirct2()).to(directExchange()).with(routingkey1);
    }
    @Bean
    public Binding binding6(){
        return BindingBuilder.bind(queuedirct3()).to(directExchange()).with(routingkey);
    }*/


