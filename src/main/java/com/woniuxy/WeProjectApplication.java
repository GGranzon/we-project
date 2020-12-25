package com.woniuxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.woniuxy.mapper")
@EnableTransactionManagement
public class WeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeProjectApplication.class, args);
    }

}
