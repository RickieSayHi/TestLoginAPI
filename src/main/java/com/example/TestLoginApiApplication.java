package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class TestLoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLoginApiApplication.class, args);
    }

}
