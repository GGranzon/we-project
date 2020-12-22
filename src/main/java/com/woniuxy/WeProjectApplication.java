package com.woniuxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woniuxy.mapper")
public class WeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeProjectApplication.class, args);
    }

}
