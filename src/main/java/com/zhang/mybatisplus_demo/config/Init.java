package com.zhang.mybatisplus_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 初始化类，系统初始化的时候秒杀缓存预热
 *
 * @author zhangzq
 */
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    StringRedisTemplate redisTemplate;

    public static final String GOODS_KEY = "goods:";

    /**
     * 缓存预热
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        int count = 50;
        redisTemplate.opsForValue().set(GOODS_KEY, String.valueOf(count));
    }
}
