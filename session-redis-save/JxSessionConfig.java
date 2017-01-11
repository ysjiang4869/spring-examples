package com.uvlab.cloud.site.service.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
@Configuration
@EnableRedisHttpSession
public class JxSessionConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    //可以获得spring session配置的RedisTemplate
//    @Autowired
//    private RedisTemplate<Object,Object> sessionRedisTemplate;
    /*@Bean(name = "sessionRedisTemplate")
    public RedisOperations<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //初始化序列化框架（Jackson2JsonRedisSerializer）
        Jackson2JsonRedisSerializer<Object> hashValueJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS);
        hashValueJackson2JsonRedisSerializer.setObjectMapper(om);
        //初始化序列化框架（StringRedisSerializer）
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //设置key的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //设置value的序列化方式
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        //设置hash key的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //设置hash value的序列化方式
        template.setHashValueSerializer(hashValueJackson2JsonRedisSerializer);

        return template;
    }*/
}
