package com.ruanmou.zuul.userzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class UserZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserZuulApplication.class, args);
    }

}

