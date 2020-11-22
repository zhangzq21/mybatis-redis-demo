package com.zhang.mybatisplus_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author zhang
 */
//mybatisPlus注解：在启动类上添加注解，扫描mapper包
@MapperScan("com.zhang.mybatisplus_demo.mapper")
@SpringBootApplication
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }

}
