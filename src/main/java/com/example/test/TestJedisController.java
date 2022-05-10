package com.example.test;

import redis.clients.jedis.Jedis;

public class TestJedisController {
    public static void main(String[] args) {

        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {

        }
    }


    public void testJedis() {
        // 创建 Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 使用之后，关闭连接
        jedis.close();
    }
}
