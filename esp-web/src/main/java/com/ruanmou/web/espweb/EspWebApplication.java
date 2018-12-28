package com.ruanmou.web.espweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class EspWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EspWebApplication.class, args);
    }

}

