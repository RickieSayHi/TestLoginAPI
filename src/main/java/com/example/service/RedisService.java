package com.example.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class RedisService {
    @Resource
    StringRedisTemplate template;

    @PostConstruct
    public void init() {
        template.setEnableTransactionSupport(true);  // 需要开启事务
    }

    @Transactional
    public void test() {
        template.multi();
        template.opsForValue().set("d", "xxxxxx");
        template.exec();
    }
}
