package com.myy.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author myy
 * @Date 2019/12/5 14:00
 */
@Component
@ConfigurationProperties(prefix ="spring.redis" )
@Data
public class RedisConfig extends CachingConfigurerSupport {
    //@NacosValue(value ="${spring.redis.host}", autoRefreshed = true)
    @Value("${spring.redis.host}")
    private String host;
    //@NacosValue(value ="${spring.redis.port}", autoRefreshed = true)
    @Value("${spring.redis.port}")
    private int port;
   /* @NacosValue(value ="${spring.redis.password}", autoRefreshed = true)
    private String password;*/
   @Value("${spring.redis.timeout}")
    private String timeout;
    //@NacosValue(value ="${spring.redis.database}", autoRefreshed = true)
    @Value("${spring.redis.database}")
    private String database;

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
