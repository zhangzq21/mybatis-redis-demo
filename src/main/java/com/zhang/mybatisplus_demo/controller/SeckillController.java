package com.zhang.mybatisplus_demo.controller;

import com.zhang.mybatisplus_demo.config.Init;
import io.swagger.annotations.Api;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzq
 */
@RequestMapping("/seckill")
@RestController
@Api(tags = "秒杀测试")
public class SeckillController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @GetMapping
    public void seckillTest() {

        //1.获取redis中的库存数量
        String count = redisTemplate.opsForValue().get(Init.GOODS_KEY);
        if (Integer.parseInt(count) <= 0) {
            log.info("秒杀结束");
            return;
        }
        //设置锁
//        String lockKey = "key";
//        RLock lock = redisson.getLock(lockKey);
        //加锁
//        try {
//            lock.lock();
        //秒杀开始，基于原子性扣减库存
        Long stockCount = redisTemplate.boundValueOps(Init.GOODS_KEY).decrement(1);
        if (stockCount <= 0) {
            log.info("库存不足，扣减失败");
            return;
        }
        log.info("扣减成功，剩余库存：" + stockCount);
        //生成订单，执行业务逻辑

//        } finally {
//            //释放锁
//            lock.unlock();
//        }

        log.info("创建dev分支");
        log.info("test");
        log.info("冲突test");
        log.info("冲突解决");
    }
}
