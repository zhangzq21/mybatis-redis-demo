package com.zhang.mybatisplus_demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redission配置类
 *
 * @author zhangzq
 */
@Configuration
public class RessionConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getRedisssion() {
        //1.创建配置
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + "6379");
        singleServerConfig.setPassword(password);
        //2.根据config创建redissionClient实例
        return Redisson.create(config);
    }
}
