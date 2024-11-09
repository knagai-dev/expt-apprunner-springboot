// src/main/java/com/example/exptapprunnerspringboot/Application.java

package com.example.exptapprunnerspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    // RedisTemplate を Inject
    private final RedisTemplate<String, String> redisTemplate;

    Application(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String root() {
        return "hello!! It's knagai's spring application!!\n";
    }


    // 以下3つの Mapping を追加
    @GetMapping("/health")
    public String getHealth() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return "Status: " + ops.get("health");
    }

    @GetMapping("/healthy")
    public String setHealty() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("health", "healthy");
        return "Status: " + ops.get("health");
    }

    @GetMapping("/unhealthy")
    public String setUnhealty() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("health", "unhealthy");
        return "Status: " + ops.get("health");
    }
}