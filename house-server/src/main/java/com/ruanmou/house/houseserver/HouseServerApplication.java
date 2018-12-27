package com.ruanmou.house.houseserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ruanmou.house.houseserver.mapper")
public class HouseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseServerApplication.class, args);
    }

}

