package com.example;

import com.example.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class TestLoginApiApplicationTests {

    @Test
    void contextLoadsOne() {
    }

    @Resource
    StringRedisTemplate template;
    @Resource
    RedisService redisService;

    @Test
    void contextLoads() {
        template.opsForValue().set("a", "8888");
        System.out.println(template.opsForValue().get("a"));
    }


    @Test
    void testRedisService() {
        redisService.test();
    }

}
