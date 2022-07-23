package com.pn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.pn.mapper")
@EnableCaching
public class PnApplication {

    public static void main(String[] args) {
        SpringApplication.run(PnApplication.class, args);
    }
}