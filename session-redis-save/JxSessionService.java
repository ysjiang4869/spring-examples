package com.uvlab.cloud.site.service.session;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class JxSessionService {

    private RedisTemplate<Object,Object> sessionRedisTemplate;
    public static final String sessionpre="spring:session:sessions:"; //session key的前缀
    public static final String attributepre="sessionAttr:"; //session属性前缀
    public void init(RedisTemplate<Object,Object> Template){
        this.sessionRedisTemplate=Template;
    }
    public Object getAttribute(String id,String attrname){
        String sessionkey=sessionpre+id;
        return sessionRedisTemplate.opsForHash().get(sessionkey,attributepre+attrname);
    }
}
